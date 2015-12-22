package com.allenfancy.performancetuning.ch04;

public class MyThread implements Runnable{

	protected String name;
	public MyThread(){
		
	}
	public MyThread(String name){
		this.name = name;
	}
	public void run() {
		// TODO Auto-generated method stub
		try{
			Thread.sleep(100);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
