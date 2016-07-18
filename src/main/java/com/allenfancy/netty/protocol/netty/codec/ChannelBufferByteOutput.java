package com.allenfancy.netty.protocol.netty.codec;

import java.io.IOException;

import org.jboss.marshalling.ByteOutput;

import io.netty.buffer.ByteBuf;

public class ChannelBufferByteOutput implements ByteOutput {

	private final ByteBuf buffer;

	public ChannelBufferByteOutput(ByteBuf buffer) {
		this.buffer = buffer;
	}

	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	public void flush() throws IOException {
		// TODO Auto-generated method stub

	}

	public void write(int arg0) throws IOException {
		// TODO Auto-generated method stub
		buffer.writeByte(arg0);
	}

	public void write(byte[] arg0) throws IOException {
		// TODO Auto-generated method stub
		buffer.writeBytes(arg0);
	}

	public void write(byte[] arg0, int arg1, int arg2) throws IOException {
		// TODO Auto-generated method stub
		buffer.writeBytes(arg0, arg1, arg2);
	}

	ByteBuf getBuffer() {
		return buffer;
	}
}
