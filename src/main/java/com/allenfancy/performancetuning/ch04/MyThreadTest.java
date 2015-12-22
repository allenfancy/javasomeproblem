package com.allenfancy.performancetuning.ch04;

import java.util.Date;

public class MyThreadTest implements Runnable{

	public static final ThreadLocal<Date> localVar = new ThreadLocal<Date>();
	
	private long time;
	
	public MyThreadTest(long time){
		this.time = time;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		Date d = new Date(time);
		for(int i = 0 ; i < 50000;i++){
			localVar.set(d);
			if(localVar.get().getTime() != time){
				System.out.println("id="+ time + "localVar=" + localVar.get().getTime());
			}
		}
	}

}
