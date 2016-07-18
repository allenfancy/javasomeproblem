package com.allenfancy.netty.protocol.netty.codec;

import java.io.IOException;

import org.jboss.marshalling.ByteInput;

import io.netty.buffer.ByteBuf;

public class ChannelBufferByteInput implements ByteInput {

	private final ByteBuf buffer;

	public ChannelBufferByteInput(ByteBuf buffer) {
		this.buffer = buffer;
	}

	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	public int available() throws IOException {
		// TODO Auto-generated method stub
		return buffer.readableBytes();
	}

	public int read() throws IOException {
		// TODO Auto-generated method stub
		if (buffer.isReadable()) {
			return buffer.readByte() & 0xff;
		}
		return -1;
	}

	public int read(byte[] arg0) throws IOException {
		// TODO Auto-generated method stub
		return read(arg0, 0, arg0.length);
	}

	public int read(byte[] dst, int dstIndex, int length) throws IOException {
		// TODO Auto-generated method stub
		int available = available();
		if (available == 0) {
			return -1;
		}

		length = Math.min(available, length);
		buffer.readBytes(dst, dstIndex, length);
		return length;

	}

	public long skip(long bytes) throws IOException {
		// TODO Auto-generated method stub
		int readable = buffer.readableBytes();
		if (readable < bytes) {
			bytes = readable;
		}
		buffer.readerIndex((int) (buffer.readerIndex() + bytes));
		return bytes;
	}

}
