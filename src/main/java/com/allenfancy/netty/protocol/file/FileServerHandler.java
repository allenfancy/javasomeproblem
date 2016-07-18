package com.allenfancy.netty.protocol.file;

import java.io.File;
import java.io.RandomAccessFile;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;
import io.netty.channel.SimpleChannelInboundHandler;

public class FileServerHandler extends SimpleChannelInboundHandler<String> {

	private static final String CR = System.getProperty("line.separator");

	public void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println(msg);
		File file = new File(msg.trim());
		if (file.exists()) {
			if (!file.isFile()) {
				ctx.writeAndFlush("Not a file : " + file + CR);
				return;
			}
			ctx.write(file + " " + file.length() + CR);
			RandomAccessFile randomAccessFile = new RandomAccessFile(msg.trim(), "r");
			FileRegion region = new DefaultFileRegion(randomAccessFile.getChannel(), 0, randomAccessFile.length());
			ctx.write(region);
			ctx.writeAndFlush(CR);
			randomAccessFile.close();
		} else {
			ctx.writeAndFlush("File not found: " + file + CR);
		}
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
