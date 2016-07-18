package com.allenfancy.distributedesgin.suanfa.希尔排序;

public class ShellShortApp {

	public static void main(String[] args) {
		int maxSize = 10;
		ArrayShell arr ;
		arr = new ArrayShell(maxSize);
		
		for(int i = 0 ; i< maxSize;i++){
			long n = (int)(java.lang.Math.random()*99);
			arr.insert(n);
		}
		arr.display();
		arr.shellSort();
		arr.display();
	}
}
