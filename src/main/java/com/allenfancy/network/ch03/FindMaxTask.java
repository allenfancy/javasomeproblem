package com.allenfancy.network.ch03;

import java.util.concurrent.Callable;

public class FindMaxTask implements Callable<Integer> {

	private int[] data;
	private int start;
	private int end;

	public FindMaxTask(int[] data, int start, int end) {
		this.data = data;
		this.start = start;
		this.end = end;
	}

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		int max = Integer.MIN_VALUE;
		for (int i = start; i < end; i++) {
			if (data[i] > max)
				max = data[i];
		}
		return max;
	}

}
