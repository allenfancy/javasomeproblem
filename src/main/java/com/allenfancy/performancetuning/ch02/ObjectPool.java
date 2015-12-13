package com.allenfancy.performancetuning.ch02;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

public class ObjectPool {

	static PoolableObjectFactory factory = new PoolableObjectFactoryDemo();
	static GenericObjectPool<Object> pool = new GenericObjectPool<Object>(factory);
	
	private static AtomicInteger endCount = new AtomicInteger(0);
	
	public static class PoolThread extends Thread{
		public void run(){
			Object obj = null;
			try{
				for(int i = 0; i< 100;i++){
					System.out.println("== " + i + " ==");
					obj = pool.borrowObject();
					System.out.println(obj + " is get");
					pool.returnObject(obj);
				}
			}catch(Exception e){
				
			}
		}
	}
	
	public static void main(String[] args){
		new PoolThread().start();
		new PoolThread().start();
		new PoolThread().start();
		try{
			while(endCount.get() == 3){
				pool.close();
				break;
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
