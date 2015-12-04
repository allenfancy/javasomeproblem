package com.allenfancy.io.合并流;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;

public class SequenceInputStreamTest {

	public static void main(String[] args) throws IOException{
		SequenceInputStreamDemo();
	}
	
	public static void SequenceInputStreamDemo() throws IOException{
		File file1 = new File("/Users/allen/temp/test1.txt");
        File file2 = new File("/Users/allen/temp/test2.txt");
        File file3 = new File("/Users/allen/temp/test.txt");
        
        InputStream input1 = new FileInputStream(file1);
        InputStream input2 = new FileInputStream(file2);
        OutputStream output = new FileOutputStream(file3);
        //合并流
        SequenceInputStream sis = new SequenceInputStream(input1, input2);
        int temp = 0;
        while((temp = sis.read()) != -1){
        	output.write(temp);
        }
        input1.close();
        input2.close();
        output.close();
        sis.close();
	}
}
