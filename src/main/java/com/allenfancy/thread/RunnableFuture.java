package com.allenfancy.thread;

public interface RunnableFuture<V> extends Runnable,Future<V> {

	void run();
}
