package com.allenfancy.performancetuning.ch04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayBlockingQueueTest {

	private  ReentrantLock lock;
	
	private Condition notEmpty;
	
	private  Condition notFull;
	
	/**
	 * 如果是true则创建非公平锁，否则创建公平锁
	 */
	private  boolean fair = true;
	
	public void ArrayLockingQueueTest(){
		lock = new ReentrantLock(fair);
		notEmpty = lock.newCondition();
		notFull  = lock.newCondition();
	}
	
	
	
}
