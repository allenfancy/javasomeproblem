package com.allenfancy.io.字节流;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class FileInputStreamCommon {

	
	public static void main(String[] args) throws IOException{
		//testCommonInputStream();
		//fileWrite();
		//fileReader();
		//fileReaderFlow();
		//FileOutputStreamToWriter();
		//FileInputStreamToWriter();
		testByteArray();
	}
	
	public static void testCommonInputStream() throws IOException{
		File file = new File("/Users/allen/temp/ElementalReverseProxy.java");
		InputStream in = new FileInputStream(file);
		byte []b = new byte[1024];
		int count = 0;
		int temp = 0;
		while((temp = in.read())!=-1){
			b[count++] = (byte) temp;
			System.out.println(new String(b));
			if(count == 1024 ){
				count = 0;	
			}
		}
		
		in.close();
	}
	
	/**
	 * 向文件中写入数据
	 * @throws IOException
	 */
	public static void fileWrite() throws IOException{
		File file = new File("/Users/allen/temp/test.txt");
		Writer out = new FileWriter(file);
		String str = "nin hao ";
		out.write(str);
		out.close();
	}
	/**
	 * 从文件中读取内容
	 * @throws IOException 
	 */
	public static void fileReader() throws IOException{
		File file = new File("/Users/allen/temp/test.txt");
		char []ch = new char[(int) file.length()];
		Reader reader = new FileReader(file);
		int count = reader.read(ch);
		reader.close();
		System.out.println("读入的长度为：" +count);
		System.out.println("内容为：" + new String(ch,0,count));
	}
	/**
	 * 循环读取
	 * @throws IOException 
	 */
	public static void fileReaderFlow() throws IOException{
		File file = new File("/Users/allen/temp/ElementalReverseProxy.java");
		char[] ch = new char[(int)file.length()];
		Reader reader = new FileReader(file);
		int temp = 0 ;
		int count = 0;
		while((temp=reader.read())!=-1){
			ch[count++] = (char) temp;
		}
		reader.close();
		System.out.println(new String(ch,0,count));
	}
	/**
	 * 将字节输出流 转为字符输出流
	 * @throws IOException 
	 */
	public static void FileOutputStreamToWriter() throws IOException{
		File file = new File("/Users/allen/temp/test1.txt");
		Writer writer = new OutputStreamWriter(new FileOutputStream(file));
		writer.write("您好");
		writer.close();
	}
	
	/**
	 * 将字符输入流 变为 字符输入流 
	 * @throws IOException 
	 */
	public static void FileInputStreamToWriter() throws IOException{
		File file = new File("/Users/allen/temp/test1.txt");
		Reader reader = new InputStreamReader(new FileInputStream(file));
		char [] b  = new char[100];
		int len = reader.read(b);
		System.out.println(new String(b,0,len));
		reader.close();
	}
	
	/**
	 * 内存操作流:
	 * 将一个大写字母转化为小写字符
	 * @throws IOException 
	 */
	public static void testByteArray() throws IOException{
		String str = "HELLO ALLENIVERSON";
		ByteArrayInputStream input = new ByteArrayInputStream(str.getBytes());
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		int temp = 0;
		while((temp = input.read())!=-1){
			char ch = (char) temp;
			output.write(Character.toLowerCase(ch));
		}
		String outStr = output.toString();
		input.close();
		output.close();
		System.out.println(outStr);
	}
}
