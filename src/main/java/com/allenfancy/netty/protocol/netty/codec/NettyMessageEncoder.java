package com.allenfancy.netty.protocol.netty.codec;

import java.io.IOException;
import java.util.List;

import com.allenfancy.netty.protocol.netty.st.NettyMessage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;

public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage>{

	MarshallingEncoder marshallingEncoder;
	
	public NettyMessageEncoder() throws IOException {
		this.marshallingEncoder = new MarshallingEncoder(null);
	}
	@Override
	protected void encode(ChannelHandlerContext ctx, NettyMessage msg, List<Object> out) throws Exception {
		
	}

}
