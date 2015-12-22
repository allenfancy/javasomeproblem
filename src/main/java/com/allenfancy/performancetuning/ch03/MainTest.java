package com.allenfancy.performancetuning.ch03;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class MainTest {

	//public static int a = 0;
	public static void main(String[] args){
		long begin = System.currentTimeMillis();
		int a = 0;
		for(int i = 0;i < 100000000;i++){
			a++;
		}
		System.out.println(System.currentTimeMillis() - begin);
	}
}
