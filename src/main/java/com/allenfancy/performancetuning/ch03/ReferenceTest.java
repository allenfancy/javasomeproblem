package com.allenfancy.performancetuning.ch03;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Queue;

/**
 * @author allen
 * 强引用：
 * 	StringBuffer sb = new StringBuffer(str);
 *  StringBuffer sb1 = sb;
 *  这都是强引用，特点：
 *  1.强引用可以直接访问目标对象
 *  2.强引用所指向的对象在任何时候都不会被系统回收。JVM宁愿抛出OOM异常。
 *  3.强引用可能导致内存泄露
 * 软引用：
 * 		
 * 
 * 
 *
 */
public class ReferenceTest {

	public static void main(String[] args){
		ReferenceQueue softQueue = new ReferenceQueue();
		MyObject obj  = new MyObject();
		
		SoftReference softRef = new SoftReference<MyObject>(obj,softQueue);
		
		new CheckRefQueue().start();
		
		obj = null;
		System.gc();
		System.out.println("After GC:Soft Get = " + softRef);
		System.out.println("分配大块内存");
		byte[] b = new byte[4*1024*925];
		System.out.println("After new byte[] : Soft Get = " + softRef.get());
	}
	
	
	
	
}
