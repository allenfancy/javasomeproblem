package com.allenfancy.middle.reactor;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public class Acceptor implements Runnable{

	private Reactor reactor;
	public Acceptor(Reactor reactor){
		this.reactor = reactor;
	}
	public void run() {
		// TODO Auto-generated method stub
		try{
			SocketChannel socketChannel = reactor.serverSocketChannel.accept();
			if(socketChannel != null)
				new SocketReadHandler(reactor.selector,socketChannel);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
