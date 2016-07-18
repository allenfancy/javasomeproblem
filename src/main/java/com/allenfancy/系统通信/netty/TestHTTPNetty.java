package com.allenfancy.系统通信.netty;

import java.net.InetSocketAddress;
import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.ThreadFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.DefaultThreadFactory;

public class TestHTTPNetty {

	public static void main(String[] args) {
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
		ThreadFactory threadFactory = new DefaultThreadFactory("work thread pool");
		int processorsNumber = Runtime.getRuntime().availableProcessors();
		EventLoopGroup workLoopGrop = new NioEventLoopGroup(processorsNumber * 2, threadFactory,
				SelectorProvider.provider());
		serverBootstrap.group(bossLoopGroup, workLoopGrop);
		// 设置通道类型
		serverBootstrap.channel(NioServerSocketChannel.class);
		// 设置处理器
		serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {

			@Override
			protected void initChannel(NioSocketChannel ch) throws Exception {
				// TODO Auto-generated method stub
				ch.pipeline().addLast(new HttpResponseEncoder());
				ch.pipeline().addLast(new HttpRequestDecoder());
				ch.pipeline().addLast(new HttpServerHandler());
			}
		});
		serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
		serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
		serverBootstrap.bind(new InetSocketAddress("0.0.0.0", 83));
	}
}

class HttpServerHandler extends ChannelInboundHandlerAdapter {

	private static AttributeKey<StringBuffer> CONNTENT = AttributeKey.valueOf("content");

	 /*
     * 在测试中，我们首先取出客户端传来的参数、URL信息，并且返回给一个确认信息。
     * 要使用HTTP服务，我们首先要了解Netty中http的格式，如下：
     * ----------------------------------------------
     * | http request | http content | http content |
     * ----------------------------------------------
     * 
     * 所以通过HttpRequestDecoder channel handler解码后的msg可能是两种类型：
     * HttpRquest：里面包含了请求head、请求的url等信息
     * HttpContent：请求的主体内容
     * */
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		if(msg instanceof HttpRequest){
			HttpRequest request = (HttpRequest) msg;
			io.netty.handler.codec.http.HttpMethod method = request.getMethod();
			String methodName = method.name();
			String url = request.getUri();
			System.out.println("methodName = " + methodName + " && url = " + url);
		}
		
		if(msg instanceof HttpContent){
			StringBuffer content = ctx.attr(HttpServerHandler.CONNTENT).get();
			if(content == null ){
				content = new StringBuffer();
				ctx.attr(HttpServerHandler.CONNTENT).set(content);
			}
			
			HttpContent httpContent = (HttpContent)msg;
			ByteBuf contentBuf = httpContent.content();
			String preContent = contentBuf.toString(CharsetUtil.UTF_8);
			content.append(preContent);
		}
	}

	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		StringBuffer content = ctx.attr(HttpServerHandler.CONNTENT).get();
		System.out.println("http客户端传送来的信息为:" +content);
		//开始返回信息了
		String returnValue  = "return response";
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK);
		HttpHeaders httpHeaders = response.headers();
		httpHeaders.add("param","value");
		response.headers().set(HttpHeaders.Names.CONTENT_TYPE,"text/plain");
		response.headers().set(HttpHeaders.Names.CONTENT_TYPE,returnValue.length());
		
		ByteBuf responseContent = response.content();
		responseContent.writeBytes(returnValue.getBytes("UTF-8"));
		//开始返回
		ctx.writeAndFlush(response);
	}
}