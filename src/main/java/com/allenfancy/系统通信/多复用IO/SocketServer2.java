package com.allenfancy.系统通信.多复用IO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.Maps;

/*
 * 改进：增加缓存
 * 多复用IO的优缺点：
 * 	不用再使用多线程来进行IO处理(包括操作系统内核IO管理模块和应用程序进程而言)。当然实际业务的处理中，应用程序进程还是可以引入线程池技术
 *  同一个端口可以处理多种协议。例如：使用ServerSocketChannel测试的服务器端口监听，既可以处理TCP协议请求也可以处理UDP协议
 *  操作系统级别优化：多复用IO技术可以是操作系统级别在一个端口上能够同时接受多个客户端的IO事件。同时具有之前我们讲到的阻塞式同步IO和非阻塞模式同步IO的所有特点。Selector的一部分作用更相当于"轮询代理器"
 *	  
 */
public class SocketServer2 {

	private static final ConcurrentMap<Integer, StringBuffer> MESSAGEHASHCONTEXT = Maps.newConcurrentMap();

	public static void main(String[] args) throws IOException {
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		ServerSocket serverSocket = serverChannel.socket();
		serverSocket.setReuseAddress(true);
		serverSocket.bind(new InetSocketAddress(83));

		Selector selector = Selector.open();
		// 注意、服务器通道只能注册SelectionKey.OP_ACCEPT事件
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		try {
			while (true) {
				if (selector.select(100) == 0) {
					continue;
				}
				Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();
				while (selectionKeys.hasNext()) {
					SelectionKey readyKey = selectionKeys.next();
					selectionKeys.remove();
					SelectableChannel selectableChannel = readyKey.channel();
					if (readyKey.isValid() && readyKey.isAcceptable()) {
						System.out.println("== Channel通道已经准备好 ==");
						/*
						 * 当server socket channel通道已经准备好，就可以从server socket
						 * channel中获取socketchannel了 拿到socket
						 * channel后，要做的事情就是马上到selector注册这个socket channel感兴趣的事情。
						 * 否则无法监听到这个socket channel到达的数据
						 */
						ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectableChannel;
						SocketChannel socketChannel = serverSocketChannel.accept();
						registerSocketChannel(socketChannel, selector);
					} else if (readyKey.isValid() && readyKey.isConnectable()) {
						System.out.println("======socket channel 建立连接=======");
					} else if (readyKey.isValid() && readyKey.isReadable()) {
						System.out.println("======socket channel 数据准备完成，可以去读==读取=======");
						readSocketChannel(readyKey);
					}
				}
			}
		} catch (Exception e) {

		}

	}

	private static void readSocketChannel(SelectionKey readyKey) throws IOException {
		SocketChannel clientSocketChannel = (SocketChannel) readyKey.channel();
		InetSocketAddress sourceSocketeAddress = (InetSocketAddress) clientSocketChannel.getRemoteAddress();
		Integer resourcePort = sourceSocketeAddress.getPort();

		// 拿到这个socket channel使用的缓存区，准备读取数据
		// 在后文，将详细讲解缓存区的用法概念，实际上重要的就是三个元素capacity,position和limit。
		ByteBuffer contextBytes = (ByteBuffer) readyKey.attachment();
		// 将通道的数据写入到缓存区，注意是写入到缓存区。
		// 这次，为了演示buff的使用方式，我们故意缩小了buff的容量大小到50byte，
		// 以便演示channel对buff的多次读写操作
		int realLen = 0;
		StringBuffer message = new StringBuffer();
		// 这句话的意思是，将目前通道中的数据写入到缓存区
		// 最大可写入的数据量就是buff的容量
		while ((realLen = clientSocketChannel.read(contextBytes)) != 0) {
			// 一定要把buffer切换成“读”模式，否则由于limit = capacity
			// 在read没有写满的情况下，就会导致多读
			contextBytes.flip();
			int position = contextBytes.position();
			int capacity = contextBytes.capacity();
			byte[] messageBytes = new byte[capacity];
			contextBytes.get(messageBytes, position, realLen);
			String messageEncode = new String(messageBytes, 0, realLen, "UTF-8");
			message.append(messageEncode);
			// 再切换成“写”模式，直接情况缓存的方式，最快捷
			contextBytes.clear();
		}

		// 如果发现本次接收的信息中有over关键字，说明信息接收完了
		if (URLDecoder.decode(message.toString(), "UTF-8").indexOf("over") != -1) {
			// 则从messageHashContext中，取出之前已经收到的信息，组合成完整的信息
			Integer channelUUID = clientSocketChannel.hashCode();
			System.out.println("端口:" + resourcePort + "客户端发来的信息======message : " + message);
			StringBuffer completeMessage;
			// 清空MESSAGEHASHCONTEXT中的历史记录
			StringBuffer historyMessage = MESSAGEHASHCONTEXT.remove(channelUUID);
			if (historyMessage == null) {
				completeMessage = message;
			} else {
				completeMessage = historyMessage.append(message);
			}
			System.out.println("端口:" + resourcePort + "客户端发来的完整信息======completeMessage : "
					+ URLDecoder.decode(completeMessage.toString(), "UTF-8"));

			// ======================================================
			// 当然接受完成后，可以在这里正式处理业务了
			// ======================================================

			// 回发数据，并关闭channel
			ByteBuffer sendBuffer = ByteBuffer.wrap(URLEncoder.encode("回发处理结果", "UTF-8").getBytes());
			clientSocketChannel.write(sendBuffer);
			clientSocketChannel.close();
		} else {
			// 如果没有发现有“over”关键字，说明还没有接受完，则将本次接受到的信息存入messageHashContext
			System.out.println("端口:" + resourcePort + "客户端信息还未接受完，继续接受======message : "
					+ URLDecoder.decode(message.toString(), "UTF-8"));
			// 每一个channel对象都是独立的，所以可以使用对象的hash值，作为唯一标示
			Integer channelUUID = clientSocketChannel.hashCode();

			// 然后获取这个channel下以前已经达到的message信息
			StringBuffer historyMessage = MESSAGEHASHCONTEXT.get(channelUUID);
			if (historyMessage == null) {
				historyMessage = new StringBuffer();
				MESSAGEHASHCONTEXT.put(channelUUID, historyMessage.append(message));
			}
		}
	}

	private static void registerSocketChannel(SocketChannel socketChannel, Selector selector) throws IOException {
		socketChannel.configureBlocking(false);
		// socket通道可以且只可以注册三种事件SelectionKey.OP_READ | SelectionKey.OP_WRITE |
		// SelectionKey.OP_CONNECT
		// 最后一个参数视为 为这个socketchanne分配的缓存区
		socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(50));
	}
}
