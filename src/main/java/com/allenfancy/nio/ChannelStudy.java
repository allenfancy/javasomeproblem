package com.allenfancy.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *@author allen
 *
 *NOTE :
 *	Java NIO的通道类似流，但有些不同：
 *		即可以从通道中读取数据，又可以写数据到通道。但流的读写通常是单向的
 *		通道可以异步地读写。
 *		通道中的数据总是要先读到一个buffer，或者总是要从一个Buffer写入
 *
 */
public class ChannelStudy {

	public static void main(String[] args){
		try {
			test1();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void test1() throws IOException{
		RandomAccessFile aFile = new RandomAccessFile("/Users/allen/temp/CTA-软文-大号分享.txt","rw");
		FileChannel fileChannel = aFile.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(1024);
		int bytesRead ;
		while((bytesRead = fileChannel.read(buf))!= -1) {
			System.out.println("Read " + bytesRead);	
			buf.flip();
		}
		
		System.out.println("Buf.hasRemaining()");
		while(buf.hasRemaining()){
			System.out.println((char) buf.get());
		}
		buf.clear();
		bytesRead = fileChannel.read(buf);
		aFile.close();
	}
}
