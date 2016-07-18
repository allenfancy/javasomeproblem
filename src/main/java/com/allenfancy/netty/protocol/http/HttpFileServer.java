package com.allenfancy.netty.protocol.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpFileServer {

	public static final String DEFAULT_URL = "/src/com/allenfancy/netty/";
	
	public void run(final int port,final String url) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try{
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup,workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						// TODO Auto-generated method stub
						ch.pipeline().addLast("http-decoder",new HttpRequestDecoder());
						ch.pipeline().addLast("http-aggregtor",new HttpObjectAggregator(65536));
						ch.pipeline().addLast("http-decoder",new HttpResponseDecoder());
						ch.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
						ch.pipeline().addLast("fileServerHandler",new HttpFileServerHandler(url));
					}
				});
			ChannelFuture future = b.bind("127.0.0.1",port).sync();
			System.out.println("HTTP 文件目录服务器启动，网址是 ： " + "http://127.0.0.1:" + port + url);
			future.channel().closeFuture().sync();
		}catch(Exception e){
			
		}finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	public static void main(String [] args) throws Exception {
		int port = 8080;
		String url = DEFAULT_URL;
		new HttpFileServer().run(port, url);
	}
}
