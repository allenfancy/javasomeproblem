package com.allenfancy.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

/**
 * @author allen
 * Buffer的基本原理：
 * 	Buffer中有3个重要的参数：位置(position),容器(capacity)和上限(limit)
 * 		参 数					写 模 式 											读 模 式
 *  位置(position)		当前缓冲区的位置，将从position的下一个位置写数据				当前缓冲区读取的位置，将从此位置后，读取数据
 *  容量(capactiy)		缓冲区的总容量上限											缓冲区的总容量上限
 *  上限(limit)			缓冲区的实际上限，它总是小于等于容量。通常情况下，和容量相等	
 */
public class BufferTest {

	public static void main(String[] args) throws IOException{
		//testBuffer();
		testBuffer2();
	}
	
	public static void testBuffer(){
		ByteBuffer b = ByteBuffer.allocate(15);
		System.out.println("limit = " + b.limit() + " capacity="+b.capacity() + " position = " + b.position());
		
		for(int i = 0; i < 10; i++){
			b.put((byte)i);
		}
		System.out.println("limit = " + b.limit() + " capacity="+b.capacity() + " position = " + b.position());
		b.flip();//重置position
		System.out.println("limit = " + b.limit() + " capacity="+b.capacity() + " position = " + b.position());
		for(int i = 0; i < 5; i++){
			System.out.print(b.get());
		}
		System.out.println();
		System.out.println("limit = " + b.limit() + " capacity="+b.capacity() + " position = " + b.position());
		b.flip();//重置position
		System.out.println("limit = " + b.limit() + " capacity="+b.capacity() + " position = " + b.position());
	}
	
	public static void testBuffer2() throws IOException{
		RandomAccessFile file = new RandomAccessFile("/Users/allen/temp/hello.txt","rw");
		FileChannel inChannel = file.getChannel();
		
		ByteBuffer buf = ByteBuffer.allocate(48);
		
		int bytesRead = inChannel.read(buf);
		
		while(bytesRead != -1){
			buf.flip();
			
			while(buf.hasRemaining()){
				System.out.print((char) buf.get());
			}
			buf.clear();
			bytesRead = inChannel.read(buf);
		}
		file.close();
	}
	/**
	 * 将数据从源通道传输到FileChannel中。
	 * @throws IOException
	 */
	public static void testTransferFrom() throws IOException{
		RandomAccessFile fromFile = new RandomAccessFile("","rw");
		FileChannel formChannel = fromFile.getChannel();
		
		RandomAccessFile toFile = new RandomAccessFile("","rw");
		FileChannel toChannel = toFile.getChannel();
		
		long position = 0;
		long count = formChannel.size();
		
		formChannel.transferFrom(toChannel, position, count);
	}
	
	/***
	 * 将数据从FileChannel传输到其他的channel中。
	 * @throws IOException
	 */
	public static void testTransferTo() throws IOException{
		RandomAccessFile fromFile = new RandomAccessFile("", "rw");
		FileChannel fromChannel = fromFile.getChannel();
		
		RandomAccessFile toFile = new RandomAccessFile("","rw");
		FileChannel toChannel = toFile.getChannel();
		
		long position = 0;
		long count = fromChannel.size();
		toChannel.transferTo(position, count, toChannel);
	}
	/**
	 * Selector
	 * @throws IOException 
	 */
	public static void testSelector() throws IOException{
		

	}
}
