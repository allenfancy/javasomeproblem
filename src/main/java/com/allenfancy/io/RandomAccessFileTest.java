package com.allenfancy.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {

	public static void main(String[] args) throws IOException{
		File file = new File("/Users/allen/temp/hello.txt");
		RandomAccessFile demo = new RandomAccessFile(file, "rw");
		demo.writeBytes("asdsad");
		demo.writeInt(12);
		demo.writeBoolean(true);
		demo.writeChar('A');
		demo.writeFloat(1.21f);
		demo.writeDouble(12.123);
		demo.close();
	}
}
