package com.allenfancy.performancetuning.ch04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestLockFreeList {

	private static final int MAX_THREADSS = 2000;
	
	private static final int TASK_COUNT = 4000;
	
	List list;
	
	class AccessListThread implements Runnable{
		protected String name;
		Random rand = new Random();
		
		public AccessListThread(){
			
		}
		
		public AccessListThread(String name){
			this.name = name;
		}
		
		public void run(){
			try{
				for(int i = 0; i < 1000; i++)
					handleList(rand.nextInt(1000));
				Thread.sleep(rand.nextInt(100));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	class CounterPoolExecutor extends ThreadPoolExecutor{
		private AtomicInteger count = new AtomicInteger(0);
		public long startTime = 0;
		public String funcname = "";
		
		public CounterPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,BlockingQueue<Runnable> workQueue){
			super(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue);
		}
		
		protected void afterExecute(Runnable r,Throwable t){
			int l = count.addAndGet(1);
			if(l == TASK_COUNT){
				System.out.println(funcname + " spend time : " + (System.currentTimeMillis() - startTime));
			}
		}
	}
	
	public Object handleList(int index){
		list.add(index);
		list.remove(index%list.size());
		return null;
	}
	
	public void initLinkedList(){
		List l = new ArrayList();
		for(int i = 0; i < 1000;i++){
			l.add(i);
		}
		list = Collections.synchronizedList(new LinkedList(l));
	}
	
	public void initFreeLockList(){
		List l = new ArrayList();
		for(int i = 0 ; i < 1000;i++)
			l.add(i);
		//list = new LockFreeList(l);
	}
	
	public void initFreeLockVector(){
	//	list = new LockFreeVector();
		for(int i = 0; i < 10000;i++)
			list.add(i);
	}
}
