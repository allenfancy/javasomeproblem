package com.allenfancy.netty.protocol.udp;

import org.jboss.netty.util.CharsetUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

public class ChineseProviderClientHandler extends SimpleChannelInboundHandler<DatagramPacket>{

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
		// TODO Auto-generated method stub
		String response = msg.content().toString(CharsetUtil.UTF_8);
		if(response.startsWith("谚语查询结果")){
			System.out.println(response);
			ctx.close();
		}
	}

	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
