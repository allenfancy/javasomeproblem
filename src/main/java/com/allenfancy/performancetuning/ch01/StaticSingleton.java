package com.allenfancy.performancetuning.ch01;
/**
 * 
 * @author allen
 * 当StaticSingleton被加载时，其内部类并不会被初始化，因此能保证StaticSingleton类被载入JVM时，不会初始化单例类，
 * 而是当调用getInstance()方法时，才会加载SingletonHolder，从而初始化instance.
 * 同时，由于实例的建立是在类加载时完成，因此对多线程友好，getInstance()方法不需要使用同步关键字。
 */
public class StaticSingleton {

	private StaticSingleton(){
		System.out.println("Static Singleton is created");
	}
	
	private static class SingletonHolder{
		private static StaticSingleton instance = new StaticSingleton();
	}
	
	public static StaticSingleton getInstance(){
		return SingletonHolder.instance;
	}
}
