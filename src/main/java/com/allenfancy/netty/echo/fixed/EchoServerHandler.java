package com.allenfancy.netty.echo.fixed;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
/**
 * 
 * @author allen
 * 利用FixedLengthFrameDecoder解码器，无论一次接收到多少数据报，它都会按照构造函数中设置的固定长度进行解码
 * 如果是半包消息，FixedLengthFrameDecoder 会缓存半包消息并等待下一个包到达进行拼包，直到读取到一个完整的包。
 * 
 *
 *如何调试fixed：
 *启动Sever, 在命令端中，使用telnet localhost 8080
 *
 *
 *总结：
 *解码器：DelimiterBasedFrameDecoder 和 FixedLengthFrameDecoder.
 *DelimiterBasedFrameDecoder:用于对使用分割符结尾的消息进行自动解码，
 *FixedLengthFrameDecoder：用于固定长度的消息进行自动解码。
 *有了上述俩种解码器，再结合其他的解码器，如果字符串解码器等，可以轻松地完成对很多消息的自动解码
 *而且不需要考虑TCP粘包 拆包导致的读半包问题，极大地提升了开发效率。
 *应用DelimiterBasedFrameDecoder 和 FixedLengthFrameDecoder进行开发非常简单。只要将DelimiterBasedFrameDecoder
 *或FixedLengthFrameDecoder添加到对应ChannelPipeline的起始位即可。
 *
 */
public class EchoServerHandler extends ChannelHandlerAdapter{

	
	public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
		System.out.println("Receive client : [" + msg + "]");
	}
	
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
		cause.printStackTrace();
		ctx.close();//发生异常关闭。关闭链路
	}
}
