package com.allenfancy.performancetuning.ch04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main5 {

	public static void main(String[] args) {
		/*ExecutorService exe = new ThreadPoolExecutor(100, 200, 0L, TimeUnit.SECONDS,
				new PriorityBlockingQueue<Runnable>());
		for (int i = 0; i < 1000; i++) {
			exe.execute(new MyThreadNew("testThreadPoolExecutor3_" + Integer.toString(999 - i)));
		}*/
		//CPU的数量
		System.out.println(Runtime.getRuntime().availableProcessors());

	}
}
