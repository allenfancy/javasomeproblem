package com.allenfancy.io.Demo1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileCount {

	public static void main(String[] args) throws IOException{
		int count = 0;
		InputStream streamReader = null;
		try{
			streamReader = new FileInputStream(new File("/Users/allen/temp/test.jpg"));
			while(streamReader.read() != -1){
				count ++;
			}
			System.out.println("长度：" +count+ "字节");
		}catch(Exception e){
			
		}finally{
			streamReader.close();
		}
	}
}
