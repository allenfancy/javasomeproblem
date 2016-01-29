package com.allenfancy.thread;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTask<V> implements RunnableFuture<V> {

	private volatile int state;
	private static final int NEW = 0;
	private static final int COMPLETING = 1;
	private static final int NORMAL = 2;
	private static final int EXCEPTIONAL = 3;
	private static final int CANCELLED = 4;
	private static final int INTERRUPTING = 5;
	private static final int INTERRUPTED = 6;

	private Callable<V> callable;
	
	private Object outcome;
	
	private volatile Thread runner;
	
	private volatile WaitNode waiters;
	
	private V report(int s) throws ExecutionException{
		Object x  = outcome;
		if(s == NORMAL)
			return (V) x;
		if(s >= CANCELLED)
			throw new CancellationException();
		throw new ExecutionException((Throwable)x);
	}
	
	public FutureTask(Callable<V> callable){
		if(callable == null)
			throw new NullPointerException();
		this.callable = callable;
		this.state = NEW;
	}
	
	
	public boolean cancel(boolean mayInterruptIfRunning) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isCanceled() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isDone() {
		// TODO Auto-generated method stub
		return false;
	}

	public V get() throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	public void run() {
		// TODO Auto-generated method stub

	}

	
	static final class WaitNode{
		volatile Thread thread;
		volatile WaitNode next;
		
		WaitNode() {
			thread = Thread.currentThread();
		}
		
	}
}
