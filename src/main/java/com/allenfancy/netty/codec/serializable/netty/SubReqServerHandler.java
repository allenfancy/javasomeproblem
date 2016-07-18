package com.allenfancy.netty.codec.serializable.netty;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqServerHandler extends ChannelHandlerAdapter{

	
	public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception {
		SubscribeReq req = (SubscribeReq) msg;
		
		if("allen.wu".equalsIgnoreCase(req.getUserName())){
			System.out.println("Service accept client subscribe req : [" + req.toString() +"]");
			ctx.writeAndFlush(resp(req.getSubReqID()));
		}
	}
	private SubscribeResp resp(int subReqID){
		SubscribeResp resp = new SubscribeResp();
		resp.setSubReqID(subReqID);
		resp.setRespCode(0);
		resp.setDesc("Netty book order succeed,3 days later ,sent to the designated address");
		return resp;
	}
	
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
		cause.printStackTrace();
		ctx.close();
	}
	
}
