package com.allenfancy.io.DataOperStream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataOperationStream {

	public static void main(String[] args) throws IOException{
		//DataOutputStreamDemo();
		DataInputStreamDemo();
	}
	
	public static void DataOutputStreamDemo() throws IOException{
		File f = new File("/Users/allen/temp/test3.txt");
		char [] ch = {'A','B','C'};
		DataOutputStream out = null;
		out = new DataOutputStream(new FileOutputStream(f));
		
		for(char temp : ch){
			out.writeChar(temp);
		}
		out.close();
	}
	
	public static void DataInputStreamDemo() throws IOException{
		File f = new File("/Users/allen/temp/test3.txt");
		FileInputStream fileInputStream = new FileInputStream(f);
		DataInputStream input = new DataInputStream(fileInputStream);
		
		char []ch = new char[10];
		int count = 0;
		char temp ;
		while((temp = input.readChar())!= 'C'){
			ch[count++] = temp;
		}
		System.out.println(ch);
	}
}
