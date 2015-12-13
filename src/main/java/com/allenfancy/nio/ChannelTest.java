package com.allenfancy.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class ChannelTest {

	public static void main(String[] args) throws IOException{
		RandomAccessFile aFile = new RandomAccessFile("/Users/allen/temp/hello.txt","rw");
		System.out.println(Charset.defaultCharset());
		FileChannel inChannel = aFile.getChannel();
		
		ByteBuffer buf = ByteBuffer.allocate(48);
		
		int byteRead = inChannel.read(buf);
		
		while(byteRead != -1){
			
			System.out.println("Read " + byteRead);
			buf.flip();
			
			while(buf.hasRemaining()){
				System.out.println((char) buf.get());
			}
			
			buf.clear();
			
			byteRead = inChannel.read(buf);
			
		}
		aFile.close();
	}
}
