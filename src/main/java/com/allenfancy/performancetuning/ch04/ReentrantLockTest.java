package com.allenfancy.performancetuning.ch04;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


public class ReentrantLockTest {

	private ReentrantLock lock = new ReentrantLock();
	public Runnable createTask(){
		//lock.lock();
		return new Runnable(){
			public void run(){
				while(true){
					try{
						if(lock.tryLock(500,TimeUnit.MICROSECONDS)){
							try{
								System.out.println("locked " + Thread.currentThread().getName());
								Thread.sleep(1000);
							}catch(Exception e){
								e.printStackTrace();
							}finally{
								lock.unlock();
								System.out.println("unlocked " + Thread.currentThread().getName());
								break;
							}
						}else{
							System.out.println("unable to lock : " + Thread.currentThread().getName());
						}
					}catch(Exception e){
						System.out.println(Thread.currentThread().getName() + "is Interrupted!");
						e.printStackTrace();
					}
				}
			}
		};
	}
	
	public static void main(String []args) throws InterruptedException{
		ReentrantLockTest rt = new ReentrantLockTest();
		Thread first = new Thread(rt.createTask(),"FirstThread");
		Thread second = new Thread(rt.createTask(),"SecondThread");
		
		first.start();
		second.start();
		Thread.sleep(600);
		second.interrupt();
	}
}
