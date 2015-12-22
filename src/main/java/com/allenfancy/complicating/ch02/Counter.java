package com.allenfancy.complicating.ch02;

import org.apache.http.annotation.GuardedBy;
import org.apache.http.annotation.ThreadSafe;
/**
 * 使用Java监听器模式的线程安全计数器
 * @author allen
 *
 */
@ThreadSafe   
public class Counter {
	@GuardedBy("this") private long value = 0;
	
	public synchronized long getValue(){
		return value;
	}
	
	public synchronized long increment(){
		if(value == Long.MAX_VALUE)
			throw new IllegalStateException("Counter overflow");
		return ++value;
	}
}
