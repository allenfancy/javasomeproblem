package com.allenfancy.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BufferIO {

	public static void main(String[] args){
		BufferedReaderDemo();
	}
	
	
	public static void BufferedReaderDemo(){
		BufferedReader  buf = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
		System.out.println("请输入内容:");
		try{
			str = buf.readLine();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("您输入的内容是：" + str);
	}
}
