package com.allenfancy.io.printStreams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class PrintStreamTest {

	public static void main(String[] args) throws IOException{
		//testOutputStreamtestPrintStreamFormat();
		//testOutputStream();
		testSystemDemo();
	}
	
	public static void testPrintStream() throws IOException{
		PrintStream print = new PrintStream(
				new FileOutputStream(new File("/Users/allen/temp/test2.txt")));
		print.print(true);
		print.println("Rollen");
		print.close();
	}
	
	public static void testPrintStreamFormat() throws IOException{
		PrintStream print = new PrintStream(
				new FileOutputStream(new File("/Users/allen/temp/test2.txt")));
		print.print(true);
		String name = "allen";
		int age = 20;
		print.printf("姓名: %s.年龄:%d",name,age);
		print.close();
	}
	/**
	 * 
	 * @throws IOException
	 */
	public static void testOutputStream() throws IOException{
		PrintStream outer = System.out;
		try{
			outer.write("hello".getBytes());
		}catch(Exception e){
			
		}
	}
	
	/**
	 * 
	 */
	public static void testSystemDemo(){
		System.out.println("Hello ");
		File f = new File("/Users/allen/temp/test2.txt");
		try{
			System.setOut(new PrintStream(new FileOutputStream(f)));
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("这些内容只能在文件中才能看到");
	}
	/**
	 * @throws FileNotFoundException 
	 * 
	 */
	public static void testsystemErr() throws FileNotFoundException{
		File f = new File("/Users/allen/temp/test2.txt");
		 System.setErr(new PrintStream(new FileOutputStream(f)));
	}
	
}
