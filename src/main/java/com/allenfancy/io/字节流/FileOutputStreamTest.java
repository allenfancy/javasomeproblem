package com.allenfancy.io.字节流;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileOutputStreamTest {

	public static void main(String[] args) throws IOException{
		//testWriteToFileByteByByte();
		//testWriteToFileAppend();
		//testFileInputStream();
		testFileInputStreamUserSuitSize();
	}
	
	/**
	 * 
	 * @throws IOException
	 * 一次性的全部写进入
	 */
	public static void testWriteToFileAll() throws IOException{
		File file = new File("/Users/allen/temp/hello1.txt");
		OutputStream out = new FileOutputStream(file);
		String str = "你好";
		byte[] b = str.getBytes();
		out.write(b);
		out.close();
	}
	
	/**
	 * 
	 * @throws IOException
	 * 按照一个一个字节流写入进去
	 */
	public static void testWriteToFileByteByByte() throws IOException{
		File file = new File("/Users/allen/temp/hello.txt");
		OutputStream out = new FileOutputStream(file);
		String str = "你好";
		byte[] b = str.getBytes();
		for(int i = 0;i < b.length;i++){
			out.write(b[i]);
		}
		out.close();
	}
	/**
	 * 向文件后面添加数据
	 * @throws IOException
	 */
	public static void testWriteToFileAppend() throws IOException{
		File file = new File("/Users/allen/temp/hello.txt");
		OutputStream out = new FileOutputStream(file,true);
		String str = "\nAllen";
		byte[] b = str.getBytes();
		for(int i = 0; i < b.length;i++){
			out.write(b[i]);
		}
		out.close();
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	public static void testFileInputStream() throws IOException{
		File file = new File("/Users/allen/temp/hello.txt");
		InputStream in = new FileInputStream(file);
		byte[] b = new byte[1024];
		int len = in.read(b);
		in.close();
		System.out.println("读取长度为："+len);
		System.out.println(new String(b,0,len));
	}
	/**
	 * 
	 * @throws IOException
	 */
	public static void testFileInputStreamUserSuitSize() throws IOException{
		File file = new File("/Users/allen/temp/hello.txt");
		InputStream in = new FileInputStream(file);
		byte []b = new byte[(int)file.length()];
		in.read(b);
		System.out.println("文件的长度：" +file.length());
		System.out.println(new String(b));
	}
}
