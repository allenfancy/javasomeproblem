package com.allenfancy.network.ch03;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThreadMaxFinder {

	public static int max(int[] data) throws InterruptedException, ExecutionException {
		if (data.length == 1) {
			return data[0];
		} else if (data.length == 0) {
			throw new IllegalArgumentException();
		}

		FindMaxTask task1 = new FindMaxTask(data, 0, data.length / 2);
		FindMaxTask task2 = new FindMaxTask(data, data.length / 2, data.length);
		ExecutorService service = Executors.newFixedThreadPool(2);

		Future<Integer> f1 = service.submit(task1);
		Future<Integer> f2 = service.submit(task2);
		System.out.println(f1.get() + "  " + f2.get());
		return Math.max(f1.get(), f2.get());
	}
}
