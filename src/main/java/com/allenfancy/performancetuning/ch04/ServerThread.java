package com.allenfancy.performancetuning.ch04;

public class ServerThread extends Thread{

	private RequestQueue requestQueue;
	
	public ServerThread(RequestQueue requestQueue,String name){
		super(name);
		this.requestQueue = requestQueue;
	}
	
	public void run(){
		while(true){
			final Request request = requestQueue.getRequest();
			try{
				Thread.sleep(100);
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " handles " + request);
		}	
	}
}
