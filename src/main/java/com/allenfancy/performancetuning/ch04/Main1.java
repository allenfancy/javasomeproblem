package com.allenfancy.performancetuning.ch04;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Main1 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		long begin = System.currentTimeMillis();
		FutureTask<String> future = new FutureTask<String>(new RealData1("allen"));
		ExecutorService excutor = Executors.newFixedThreadPool(10);
		excutor.submit(future);
		System.out.println("请求完成");
		Thread.sleep(2000);
		System.out.println("数据 = " + future.get());
		System.out.println("花费时间: " + (System.currentTimeMillis() - begin));
	}

}
