package com.allenfancy.netty.echo.Deli;



import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class EchoServer {

	
	public void bind(int port) throws Exception {
		//配置服务端的NIO线程组
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try{
			ServerBootstrap sb = new ServerBootstrap();
			sb.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG,100)
			.handler(new LoggingHandler(LogLevel.INFO))
			.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					// TODO Auto-generated method stub
					//首先创建分隔符缓冲对象ByteBuf,DelimiterBasedFrameDecoder对象，将其加入到ChannelPipeline中。
					//表示单条消息的最大长度，当达到该长度后仍然没有查找到分割符，就抛出TooLongFrameException异常，这就是Netty解码器的可靠性保护
					//第二个参数就是分隔符缓冲对
					ByteBuf delimiter = Unpooled.copiedBuffer("&_".getBytes());
					ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
					ch.pipeline().addLast(new StringDecoder());
					ch.pipeline().addLast(new EchoServerHandler());
				}
			});
			
			//等待服务端端口关闭
			ChannelFuture f = sb.bind(port).sync();
			
			f.channel().closeFuture().sync();
		}finally{
			//优雅退出，释放线程池资源
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws Exception{
		int port = 8080;
		new EchoServer().bind(port);
	}
}
