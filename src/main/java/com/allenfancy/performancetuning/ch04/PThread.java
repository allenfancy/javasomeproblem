package com.allenfancy.performancetuning.ch04;

import com.sun.swing.internal.plaf.synth.resources.synth;

public class PThread  extends Thread{

	private ThreadPool pool;
	
	private Runnable target;
	
	private boolean isShutDown = false;
	
	private boolean isIdle = false;
	
	//构造函数
	
	public PThread(Runnable target,String name,ThreadPool pool){
		super(name);
		this.pool = pool;
		this.target = target;
	}
	
	public Runnable getTarget(){
		return target;
	}
	public boolean isIdle(){
		return isIdle;
	}
	
	public void run(){
		while(!isShutDown){
			isIdle = false;
			if(target != null){
				target.run();
			}
			isIdle = true;
			try{
				pool.repool(this);
				synchronized (this) {
					wait();
				}
			}catch(Exception e){
				
			}
			isIdle = false;
		}
	}
	
	public synchronized void setTarget(java.lang.Runnable newTarget){
		target = newTarget;
		notifyAll();
	}
	
	public synchronized void shutDown(){
		isShutDown = true;
		notifyAll();
	}
}
