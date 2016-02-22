package com.allenfancy.middle.reactor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;
/**
 * 
 * @author allen
 * 反应器模式
 * 用于解决多用户访问并发问题
 * 
 * 举例子：餐厅服务问题
 * 传统线程池做法：来一个客人 请求 去一个服务器
 * 反应器模式做法：当客人点菜的时候，服务员就可以去招呼其他客人，等客人点好了才，直接在招呼服务员就行。
 *
 */
public class Reactor implements Runnable{

	public final Selector selector;
	public final ServerSocketChannel serverSocketChannel;
	public Reactor(int port) throws IOException{
		selector = Selector.open();
		serverSocketChannel = ServerSocketChannel.open();
		InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(),port);
		serverSocketChannel.socket().bind(inetSocketAddress);
		serverSocketChannel.configureBlocking(false);
		//向Selector注册该channel
		SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		//利用selectionKey的attache功能绑定Acceptor，如果有事儿，触发Acceptor
		selectionKey.attach(new Acceptor(this));
	}
	public void run() {
		// TODO Auto-generated method stub
		try{
			while(!Thread.interrupted()){
				selector.select();
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> it = selectionKeys.iterator();
				//Selector如果发现channel有哦OP_ACCEPT或READ事件发生，下列遍历就会进行
				while(it.hasNext()){
					//来一个出发一个
					SelectionKey selectionKey = it.next();
					dispatch(selectionKey);
					selectionKeys.clear();
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	void dispatch(SelectionKey key){
		Runnable r = (Runnable)(key.attachment());
		if(r != null){
			r.run();
		}
	}

}
