package com.allenfancy.netty.protocol.file;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
/**
 * 
 * @author allen
 *
 *FileChannel : 文件通道，用于对文件进行读写操作
 *Position:文件操作的指针位置，读取或者写入的起始点
 *Count：操作的总字节数
 */
public class FileServer {

	public void run(int port) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try{
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup,workerGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 100)
			.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					// TODO Auto-generated method stub
					ch.pipeline().addLast(
							new StringEncoder(CharsetUtil.UTF_8),
							new LineBasedFrameDecoder(1024),
							new StringDecoder(CharsetUtil.UTF_8),
							new FileServerHandler());
				}
				
			});
			ChannelFuture f = b.bind(port).sync();
			System.out.println("Start file server at port :" +port);
			f.channel().closeFuture().sync();
		}finally{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws Exception{
		int port = 8080;
		new FileServer().run(port);
	}
}
