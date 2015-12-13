package com.allenfancy.performancetuning.ch01;

import java.io.Serializable;

public class SerSingleton implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	
	private SerSingleton(){
		System.out.println("Singleton is create");
		name  = "SerSingleton";
	}
	
	private static SerSingleton instance = new SerSingleton();
	
	public static SerSingleton getInstance(){
		return instance;
	}

	public static void createString(){
		System.out.println("creating in Singleton.");
	}
	
	private Object readResolve(){
		return instance;
	}
}
