package com.allenfancy.thread;

public interface Callable<V> {

	V call() throws Exception;
}
