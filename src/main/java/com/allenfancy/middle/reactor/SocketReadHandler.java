package com.allenfancy.middle.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class SocketReadHandler implements Runnable{

	private SocketChannel socketChannel;
	
	public SocketReadHandler(Selector selector,SocketChannel socketChannel) throws IOException{
		this.socketChannel = socketChannel;
		socketChannel.configureBlocking(false);
		
		SelectionKey selectionKey = socketChannel.register(selector, 0);
		
		//将SelectionKey绑定为Handler 下一步有时间触发时，将调用本类的run方法、
		//参看dispatch(SelectionKey key)
		selectionKey.attach(this);
		
		//同时将SelectionKey标记为可读，以便读取
		selectionKey.interestOps(SelectionKey.OP_READ);
		selector.wakeup();
	}

	public void run() {
		// TODO Auto-generated method stub
		ByteBuffer inputBuffer = ByteBuffer.allocate(1024);
		inputBuffer.clear();
		try{
			socketChannel.read(inputBuffer);
			//激活线程池 处理这些request
			//requestHandle(new Request(socket,btt));
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
