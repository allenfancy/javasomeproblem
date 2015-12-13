package com.allenfancy.performancetuning.ch01;

public class LayzSingleton {

	private LayzSingleton() {
		System.out.println("LazySingleton is created");
	}

	private static LayzSingleton instance = null;

	public static synchronized LayzSingleton getInstance() {
		if (instance == null) {
			instance = new LayzSingleton();
		}
		return instance;
	}
}
