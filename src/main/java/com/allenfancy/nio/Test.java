package com.allenfancy.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test {

	public static void main(String[] args){
		//fileTest();
		try {
			nioCopyFile("/Users/allen/temp/hello.txt","/Users/allen/temp/hello1.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void fileTest(){
		try {
			FileInputStream fin = new FileInputStream(new File("/Users/allen/temp/hello.txt"));
			FileChannel fc = fin.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
			fc.read(byteBuffer);
			fc.close();
			byteBuffer.flip();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 使用NIO的进行复制操作
	 * @param resource
	 * @param destination
	 * @throws IOException
	 */
	public static void nioCopyFile(String resource,String destination) throws IOException{
		FileInputStream fis = new FileInputStream(resource);
		FileOutputStream fos = new FileOutputStream(destination);
		FileChannel readChannel = fis.getChannel();
		FileChannel writeChannel = fos.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while(true){
			buffer.clear();
			int len = readChannel.read(buffer);
			if(len == -1){
				break;
			}
			buffer.flip();
			writeChannel.write(buffer);
		}
		readChannel.close();
		writeChannel.close();
	}
}
