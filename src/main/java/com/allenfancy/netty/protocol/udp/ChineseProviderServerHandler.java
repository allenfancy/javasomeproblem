package com.allenfancy.netty.protocol.udp;

import java.util.concurrent.ThreadLocalRandom;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

public class ChineseProviderServerHandler extends SimpleChannelInboundHandler<DatagramPacket>{

	private static final String[] DICTIONARY = {
			"持之以恒，方能取得成功",
			"旧时王谢堂前燕，飞入寻常百姓家",
			"洛阳亲友如相问，一片冰心在玉壶",
			"一寸光阴一寸金，寸金难买寸光阴",
			"老骥伏枥，志在千里。烈士暮年，壮心不已"
			
	};
	
	private String nextQuote(){
		int quoteId = ThreadLocalRandom.current().nextInt(DICTIONARY.length);
		return DICTIONARY[quoteId];
	}
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
		// TODO Auto-generated method stub
		String req = msg.content().toString(CharsetUtil.UTF_8);
		System.out.println(req);
		if ("谚语字典查询?".equals(req)) {
		    ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(
			    "谚语查询结果: " + nextQuote(), CharsetUtil.UTF_8), msg
			    .sender()));
		}
	}

	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) throws Exception {
		ctx.close();
		cause.printStackTrace();
	}
	
}
