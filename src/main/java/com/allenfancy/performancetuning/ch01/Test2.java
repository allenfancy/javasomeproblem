package com.allenfancy.performancetuning.ch01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test2 {

	public static void main(String[] args) throws IOException, ClassNotFoundException{
		SerSingleton s1 = null;
		SerSingleton s = SerSingleton.getInstance();
		
		//先讲实例串行化到文件
		FileOutputStream fos = new FileOutputStream("/Users/allen/temp/hello1.txt");
		ObjectOutputStream oss = new ObjectOutputStream(fos);
		oss.writeObject(s);
		oss.flush();
		oss.close();
		//将文件读取处原来的单例类
		FileInputStream fis = new FileInputStream("/Users/allen/temp/hello1.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		s1 = (SerSingleton) ois.readObject();
		System.out.println(s1.equals(s));
		
		System.out.println(s1==s);
		
	}
}
