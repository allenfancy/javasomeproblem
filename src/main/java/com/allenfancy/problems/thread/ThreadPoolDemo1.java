package com.allenfancy.problems.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo1 {

	public static void main(String[] args){
		ExecutorService pool = Executors.newFixedThreadPool(2);
		Thread ta = new MyThread1();
		Thread tb = new MyThread1();
		Thread tc = new MyThread1();
		Thread td = new MyThread1();
		Thread te = new MyThread1();
		
		pool.execute(ta);
		pool.execute(tb);
		pool.execute(tc);
		pool.execute(td);
		pool.execute(te);
		
		pool.shutdown();
	}
}
class MyThread1 extends Thread{
	public void run(){
		System.out.println(Thread.currentThread().getName()+ " is running! "  );
	}
}
