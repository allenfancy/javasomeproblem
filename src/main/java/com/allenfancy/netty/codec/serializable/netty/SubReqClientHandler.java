package com.allenfancy.netty.codec.serializable.netty;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqClientHandler extends ChannelHandlerAdapter {

	public SubReqClientHandler() {

	}

	public void channelActive(ChannelHandlerContext ctx) {
		for (int i = 0; i < 10; i++) {
			ctx.write(subReq(i));
		}
		ctx.flush();
	}

	private SubscribeReq subReq(int i) {
		SubscribeReq req = new SubscribeReq();
		req.setAddress("上海市黄浦区");
		req.setPhoneNumber("1312XXXXXX");
		req.setProductName("Netty ");
		req.setSubReqID(i);
		req.setUserName("allen.wu");
		return req;
	}

	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("Receive server response : [" + msg + "]");
	}

	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
