package com.allenfancy.performancetuning.ch04;

public class TestJavap {

	public static void main(String[] args){
		int a = 1,b = 1,c = 1,d = 1;
		
		a++;
		++b;
		c = c++;
		d = ++d;
		
		System.out.println(a + "\t" + b + "\t" +c + "\t"  + d);
		
	//	test1();
		
	}
	
	/*public static void test1(){
		String a = "a" + "b" + 1;
		String b = "ab1";
		System.out.println(a == b);
	}*/
}
