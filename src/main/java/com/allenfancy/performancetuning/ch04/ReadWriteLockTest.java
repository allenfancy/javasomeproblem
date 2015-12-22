package com.allenfancy.performancetuning.ch04;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

	private static Lock lock = new ReentrantLock();

	private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private static Lock readLock = readWriteLock.readLock();

	private static Lock writeLock = readWriteLock.writeLock();

	private int value = 0;
	/**
	 * 使用重入锁同步读写操作
	 * @return
	 * @throws InterruptedException
	 */
	public Object handleRead() throws InterruptedException {
		try {
			lock.lock();
			Thread.sleep(1);
			return value;
		} finally {
			lock.unlock();
		}
	}
	
	public void handleWrite(int index){
		try{
			lock.lock();
			Thread.sleep(1);
			value = index;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	/***
	 * 使用读写锁同步读写操作
	 */
	public Object handleRead2(){
		try{
			readLock.lock(); //读锁
			Thread.sleep(1);
			return value;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally {
			readLock.unlock();
		}
	}
	
	public void hanleWrite2(int index){
		try{
			writeLock.lock();//写锁
			Thread.sleep(1);
			value = index;
		}catch(Exception e){
			
		}finally{
			writeLock.unlock();
		}
	}
}
