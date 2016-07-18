package com.allenfancy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author allen Selector(选择器)是Java NIO中能够检测一到多个NIO通道，并能够知晓通道是否为诸如读写事件做好准备的组件。
 *         这样，一个单独的线程可以管理多个channel，从而管理多个网络连接。 1.为什么使用Selector?
 *         仅用单个线程来处理多个Channels的好处是，只需要更少的线程来处理通道。实际上，可以只用一个线程处理所有的通道。
 *         对于操作系统来说，线程之间上下文切换的开销很大，而且每个线程都要占用系统的一些资源。因此，使用的线程越少越好。
 *         但是，需要记住，现代的操作系统和CPU在多个任务方面表现的越来越好，所以多线程的开销随着时间的推移，变得越来越小。
 *         实际上，如果一个CPU有多个内核，不使用多任务可能是在浪费CPU能力。不管怎么说，关于那种设计的讨论应该放在另一篇不同的文章中。
 *         单线程使用一个Selector处理3个channel的实例图： 2 Selector的创建
 *         通过Selector.open()方法创建一个Selector，如下： Selector selector =
 *         Selector.open() 向Selector注册通道
 *         为了将Channels和Selector配合使用，必须channel注册到Selector上，通过SelectableChannel.
 *         register()方法来实现，如下： channel.configureBlocking(false) SelectionKey key
 *         = channel.register(selector,SelectionKey.OP_READ)
 *         与Selector一起使用时，Channels必须处于非阻塞模式下。这意味不能将FileChannel与Selector一起使用，
 *         因为FileChannel不能切换到非阻塞模式。而套接字通道都可以。
 *         注意register()方法的第二个参数。这是一个，意思是在通过Selector监听Channels时对什么事件感兴趣。
 *         可以监听四种不同类型的事件： Connect Accept Read Write
 *         通道触发了一个事件意思是该事件已经就绪。所以，某个channel成功连接到另一个服务器称为「连接就绪」。一个server socket
 *         channel准备好接收新进入的连接称为『连接就绪』。 一个有数据可读的通道可以说是"读就绪"。等待写数据的通道可以说是"写就绪"
 *
 */
public class SelectorTest {

	public static void main(String[] args) throws IOException {
		Selector selector = Selector.open();
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.configureBlocking(false);
		channel.socket().bind(new InetSocketAddress(8080), 1024);
		SelectionKey key1 = channel.register(selector, SelectionKey.OP_READ);
		while (true) {
			int readyChannels = selector.select();
			if (readyChannels == 0)
				continue;
			Set selectedKeys = selector.selectedKeys();
			Iterator keyIterator = selectedKeys.iterator();
			while (keyIterator.hasNext()) {
				SelectionKey key = (SelectionKey) keyIterator.next();
				if (key.isAcceptable()) {
					// a connection was accepted by a ServerSocketChannel.
				} else if (key.isConnectable()) {
					// a connection was established with a remote server.
				} else if (key.isReadable()) {
					// a channel is ready for reading
				} else if (key.isWritable()) {
					// a channel is ready for writing
				}
				// keyIterator.<tuihighlight class="tuihighlight"><a
				// href="javascript:;"
				// style="display:inline;float:none;position:inherit;cursor:pointer;color:#7962D5;text-decoration:underline;"
				// onclick="return false;">remove</a></tuihighlight>();
			}

		}
	}
}
