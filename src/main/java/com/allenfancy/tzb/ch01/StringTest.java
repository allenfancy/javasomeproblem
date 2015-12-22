package com.allenfancy.tzb.ch01;

public class StringTest {

	public static void main(String[] args){
		test1();
	}
	
	private static String getA(){
		return "a";
	}
	
	public static void test1(){
		String a = "a";
		final String c = "a";
		String b = a + "b";
		String d = c + "b";
		String e = getA() + "b";
		String compare = "ab";
		
		System.out.println(b == compare);
		System.out.println(d == compare);
		System.out.println(e == compare);
	}
}
