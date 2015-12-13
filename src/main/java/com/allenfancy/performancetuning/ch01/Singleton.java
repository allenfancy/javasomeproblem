package com.allenfancy.performancetuning.ch01;

public class Singleton {

	private Singleton(){
		System.out.println("Singleton is create");
	}
	private static Singleton instance = new Singleton();
	
	public static Singleton getInstance(){
		return instance;
	}
}
