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
/**
 * @author allen
 * serverChannel.register(Selector sel,int ops,Object att):实际上register(Selector sel,int ops,Object att)方法是ServerSocketChannel
 * 类的父类AbstractSelectableChannel提供的一个方法，表示只要继承了AbstractSelectableChannel类的子类都可以注册到选择器中。通过观察整个AbstractSelectableChannel继承关系
 * AbstractInterruptibleChannel
 * 	--SelectableChannel
 *   --AbstractSelectableChannel
 *   	-- DatagramChannel
 *   	 -- DatagranChannelImpl
 *   	-- SctpChannel
 *   	 -- SctpChannelImpl
 *   	-- SctpMultiChannel
 *   	 -- SctpMultiChannelImpl
 *      -- SctpServerChannel
 * 		 -- SctpServerChannelImpl
 * 		-- ServerSocketChannel
 * 		 -- ServerSocketChannelImpl
 * 		-- SinkChannel
 * 		 -- SinkChannelImpl
 * 		-- SocketChannel
 * 		 -- socketChannelImpl
 * 		  -- RendezvoursChannelUDT
 * 		-- SourceChannel
 *  	  -- SourceChannelImpl
 *  SelectionKey.OP_ACCEPT: 不同的Channel对象可以注册的"我关心的事件"是一样的。
 *  通道类					通道作用						可关注的事件
 *  ServerSocketChannel 	服务器端通道			SelectionKey.OP_ACCEPT
 *  DatagramChannel			UDP协议通道			SelectionKey.OP_READ,SelectionKey.OP_WRITE
 *  SocketChannel			TCP协议通道			SelecitonKey.OP_READ,SelecitonKey.OP_WRITE,SelecitonKey.OP_CONNECT
 *  
 *  实际上通过一个AbstractSelectableChannel子类所收到操作系统的IO操作事件后，它的selectKeys将在下一次轮询操作中，接收到这些事件的关键描述字(不同的channel，就算关键字一样，会生成俩个对象)、
 *  但是每一个事件关键字被处理后都必须移除，否则下一次轮询的时候，这个事件会被重复处理
 */      
public class SocketServer1 {

	public static void main(String[] args) throws IOException {
		//
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		//
		ServerSocket serverSocket = serverChannel.socket();
		serverSocket.setReuseAddress(true);
		serverSocket.bind(new InetSocketAddress(83));
		Selector selector = Selector.open();
		//服务器通道只能注册SelectionKey.OP_ACCEPT事件
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		try{
			while(true){
				//多路复用IO支持阻塞模式和非阻塞模式俩种
				if(selector.select(100)==0){
					//业务操作
					continue;
				}
				//询问操作系统，锁获取的关心的事件的事件类型(每一个通道都是独立的)
				Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();
				while(selectionKeys.hasNext()){
					SelectionKey readyKey = selectionKeys.next();
					//处理的readyKey一定要移除。如果不移除，就会一直存在selector.selectionKeys集合总
					//待到下次Selector.select() > 0时，这个readykey又会被处理一次
					selectionKeys.remove();
					SelectableChannel selectableChannel = readyKey.channel();
					if(readyKey.isValid() && readyKey.isAcceptable()){
						System.out.println("=== channel 通道已经准备好 ===");
						//当server socket channel通道已经准备好，就可以从server Socket channel中过去socketChannel
						//拿到socket channel后，要做的事情就是马上到selector注册这个socket channel 感到兴趣的事情
						//否则无法监听到这个socket channel 到达的数据
						ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectableChannel;
						SocketChannel socketChannel = serverSocketChannel.accept();
						registerSocketChannel(socketChannel , selector);
					}else if(readyKey.isValid() && readyKey.isConnectable()){
						System.out.println("== socket channel 建立连接 ==");
					}else if(readyKey.isValid() && readyKey.isReadable()){
						System.out.println("== socket channel 数据准备完成可以去读数据 ==");
						readSocketChannel(readyKey);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			serverSocket.close();
		}
		
	}

	private static void readSocketChannel(SelectionKey readyKey) throws IOException {
		SocketChannel clientSocketChannel = (SocketChannel) readyKey.channel();
		//获取客户端使用端口
		InetSocketAddress sourceSocketAddress = (InetSocketAddress) clientSocketChannel.getRemoteAddress();
		Integer resourcePort = sourceSocketAddress.getPort();
		//拿到这个 socket channel使用的缓存区，准备读取数据
		ByteBuffer contextBytes = (ByteBuffer) readyKey.attachment();
		int readlLen = -1;
		try{
			readlLen = clientSocketChannel.read(contextBytes);
		}catch(Exception e){
			e.printStackTrace();
			clientSocketChannel.close();
			return ;
		}
		//将缓存区从写状态切换为读状态(实际上这个方法是读写模式切换)
		//这是java nio 框架中的这个socket channel的写请求全部等待
		contextBytes.flip();
		//注意中文乱码的问题，使用URLDecoder/URLEncoder，进行解编码
		byte[] messageBytes = contextBytes.array();
		String messageEncode = new String(messageBytes,"UTF-8");
		String message = URLDecoder.decode(messageEncode,"UTF-8");
		
		//如果收到了 "over" 关键字，才会情况buffer，并发数据；
		//否则不清空缓存，还要还原buffer的"写状态"
		if(message.indexOf("over") != -1){
			//清空已经读取的缓存，并从新切换为写状态(这里要注意clear()和capacity()两个方法的区别)
			contextBytes.clear();
			System.out.println("端口:" + resourcePort + "客户端发来的信息======message : " + message);
			
			//正式处理业务
			//回发数据，并关闭channel
			ByteBuffer sendBuffer = ByteBuffer.wrap(URLEncoder.encode("回发处理结果", "UTF-8").getBytes());
			clientSocketChannel.write(sendBuffer);
            clientSocketChannel.close();
		}else{
			System.out.println("端口:" + resourcePort + "客户端信息还未接受完，继续接受======message : " + message);
            //这是，limit和capacity的值一致，position的位置是realLen的位置
            contextBytes.position(readlLen);
            contextBytes.limit(contextBytes.capacity());
		}
	}

	/**
	 * 在 server Socket channel接收到/准备好 一个新的TCP连接后。
	 * 就会向程序返回一个新的socketChannel
	 * 但是这个新的socket channel 并没有在selector "选择器/代理器"中注册。
	 * 所有程序还没法通过selector通知这个Socket channel的事件。
	 * 
	 * 
	 * @param socketChannel
	 * @param selector
	 * @throws IOException 
	 */
	private static void registerSocketChannel(SocketChannel socketChannel, Selector selector) throws IOException {
		//设置非阻塞
		socketChannel.configureBlocking(false);
		//注册
		socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(2048));
	}
}
