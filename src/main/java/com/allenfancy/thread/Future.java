package com.allenfancy.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public interface Future<V> {

	boolean cancel(boolean mayInterruptIfRunning);
	
	boolean isCanceled();
	
	boolean isDone();
	
	V get() throws InterruptedException,ExecutionException;
	
	V get(long timeout, TimeUnit unit)
	        throws InterruptedException, ExecutionException, TimeoutException;
	
}
