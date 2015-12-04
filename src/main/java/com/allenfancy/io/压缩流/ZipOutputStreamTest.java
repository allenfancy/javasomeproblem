package com.allenfancy.io.压缩流;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipOutputStreamTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//ZipOutputStreamDemo();
		//ZipOutputStreamDemo1();
		testZipDemo();
	}
	
	public static void ZipOutputStreamDemo() throws IOException{
		File file = new File("/Users/allen/temp/test1.txt");
		File zipFile = new File("/Users/allen/temp/zip.zip");
		InputStream input = new FileInputStream(file);
		ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
		
		zipOut.putNextEntry(new ZipEntry(file.getName()));
		//设置注释
		zipOut.setComment("hello");
		
		int temp = 0;
		while((temp = input.read())!=-1){
			zipOut.write(temp);
		}
		input.close();
		zipOut.close();
	}
	
	public static void ZipOutputStreamDemo1() throws IOException {
		File file = new File("/Users/allen/temp/temp1");
		File zipFile = new File("/Users/allen/temp/zip.zip");
		
		InputStream input = null;
		
		ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
		zipOut.setComment("hello");
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(int i = 0 ; i< files.length;++i){
				input = new FileInputStream(files[i]);
				zipOut.putNextEntry(new ZipEntry(file.getName()
						+ File.separator + files[i].getName()));
				int temp = 0;
				while((temp = input.read())!=-1){
					zipOut.write(temp);
				}
				input.close();
			}
		}
		zipOut.close();
	}

	/**
	 * 
	 * @throws ZipException
	 * @throws IOException
	 */
	public static void testZipDemo() throws ZipException, IOException{
		File file = new File("/Users/allen/temp/zip.zip");
		ZipFile zipFile = new ZipFile(file);
		System.out.println("压缩文件的名称为:" + zipFile.getName());
	}
}
