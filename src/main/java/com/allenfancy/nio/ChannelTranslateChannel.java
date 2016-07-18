package com.allenfancy.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @author allen
 *
 *通道之间的数据传输：
 *	在Java NIO中，如果俩个通道中有一个是FileChannel，那你可以直接将数据从一个channel传输到另外一个channel。
 *
 *	transferForm():
 *		FileChannel的transferFrom()方法可以将数据从源通道传输到FileChannel中。
 *	方法的输入参数position表示从position处开始向目标文件写入数据，count表示最多传输的字节数。
 *
 *	
 */
public class ChannelTranslateChannel {
	
	public static void main(String[] args) throws Exception{
		transferChannelToChannelFrom();
		//transferChannelToChannelTo();
	}
	/**
	 * transferFrom()方法可以将数据从源通道传输到FileChannel中。
	 * @throws Exception
	 * 方法的输入参数position表示从position处开始向目标文件写入数据，count表示最多传输的字节数。如果源通道的摄于空间小于count字节，则所传输的字节数要小于请求的字节数。
	 */
	public static void transferChannelToChannelFrom() throws Exception {
		RandomAccessFile fromFile = new RandomAccessFile("/Users/allen/temp/CTA-软文-大号分享.txt","rw");
		FileChannel fromChannel = fromFile.getChannel();
		
		RandomAccessFile toFile = new RandomAccessFile("/Users/allen/temp/CTA-软文-大号分享2.txt","rw");
		FileChannel toChannel = toFile.getChannel();
		
		long position = 0;
		long count = fromChannel.size();
		
		toChannel.transferFrom(fromChannel, count, position);
	}
	
	/**
	 * 
	 * 将数据从FileChannel传输到其他的channel中。
	 * 关于SocketChannel的问题在与TransferTo()方法中同样存在。SocketChannel会一直传输数据直到目标buffer被填满
	 * @throws Exception
	 */
	public static void transferChannelToChannelTo() throws Exception {
		RandomAccessFile fromFile = new RandomAccessFile("/Users/allen/temp/CTA-软文-大号分享.txt","rw");
		FileChannel fromChannel = fromFile.getChannel();
		
		RandomAccessFile toFile = new RandomAccessFile("/Users/allen/temp/CTA-软文-大号分享4.txt","rw");
		FileChannel toChannel = toFile.getChannel();
		
		long position = 0;
		long count = fromChannel.size();
		
		fromChannel.transferTo(position, count, toChannel);
	}
	
	
}
