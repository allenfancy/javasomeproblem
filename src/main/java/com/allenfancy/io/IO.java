package com.allenfancy.io;

import java.io.File;

public class IO {

	public static void main(String[] args){
		//listAllFiles();
		//isDirectory();
		listAllContents();
	}
	/**
	 * 基本
	 */
	public static void basic(){
		File file = new File("/Users/allen/temp/hello.txt");
		try{
			if(!file.exists()){
				file.createNewFile();
				
			}else{
				file.delete();
				System.out.println("已经存在了");
			}
			System.out.println(File.separator);
			System.out.println(File.pathSeparator);
			System.out.println(File.separatorChar);
			System.out.println(File.pathSeparatorChar);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 列出指定目录的全部文件(包括隐藏文件)
	 */
	public static void listAllFile(){
		File file = new File("/Users/allen/temp");
		for(String str : file.list()){
			System.out.println(str);
		}
	}
	/**
	 * 列出指定目录的全部文件
	 * 包括路径
	 */
	public static void listAllFiles(){
		File file = new File("/Users/allen/temp");
		for(File f : file.listFiles()){
			System.out.println(f);
		}
	}
	/**
	 * 判断一个指定的路径是否为目录
	 */
	
	public static void isDirectory(){
		File file = new File("/Users/allen/temp");
		if(file.isDirectory()){
			System.out.println("是一个目录");
		}else{
			System.out.println("不是一个目录");
		}
	}
	/**
	 * 列出全部内容
	 */
	public static void listAllContents(){
		File file = new File("/Users/allen/temp");
		listAllContent(file);
	}
	/**
	 * 
	 * 采用递归的方式
	 */
	public static void listAllContent(File f){
		if(f != null){
			if(f.isDirectory()){
				File[] fileArray = f.listFiles();
				if(fileArray!=null){
					for(int i= 0;i < fileArray.length;i++){
						listAllContent(fileArray[i]);
					}
				}
			}else{
				System.out.println(f);
			}
		}
	}
}
