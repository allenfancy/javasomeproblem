package com.allenfancy.netty.basic1;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClientHandler extends ChannelHandlerAdapter {
	private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());

	private AtomicInteger counter = new AtomicInteger(0);

	private byte[] req;

	public TimeClientHandler() {
		req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		ByteBuf message = null;
		for (int i = 0; i < 100; i++) {
			message = Unpooled.buffer(req.length);
			message.writeBytes(req);
			ctx.writeAndFlush(message);
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req, "UTF-8");
		System.out.println("Now is : " + body + " ; the counter is : " + counter.incrementAndGet());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// 释放资源
		logger.warn("Unexpected exception from downstream : " + cause.getMessage());
		ctx.close();
	}

}
