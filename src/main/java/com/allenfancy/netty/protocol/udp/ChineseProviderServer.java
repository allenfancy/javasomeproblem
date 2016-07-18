package com.allenfancy.netty.protocol.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class ChineseProviderServer {

	public void run(int port) throws  Exception{
		EventLoopGroup group = new NioEventLoopGroup();
		try{
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioDatagramChannel.class)
			.option(ChannelOption.SO_BROADCAST, true)
			.handler(new ChineseProviderServerHandler());
			b.bind(port).sync().channel().closeFuture().await();
		}finally{
			group.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws Exception{
		int port = 8080;
		new ChineseProviderServer().run(port);
	}
}
