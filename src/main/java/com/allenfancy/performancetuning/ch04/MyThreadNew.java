package com.allenfancy.performancetuning.ch04;

public class MyThreadNew implements Runnable,Comparable<MyThreadNew>{

	protected String name;
	
	public MyThreadNew(){
		
	}
	
	public MyThreadNew(String name){
		this.name = name;
	}
	
	public int compareTo(MyThreadNew o) {
		// TODO Auto-generated method stub
		int me = Integer.parseInt(this.name.split("_")[1]);
		int other = Integer.parseInt(o.name.split("_")[1]);
		if(me > other) return 1;
		else if(me < other) return -1;
		else return 0;
	}

	public void run() {
		// TODO Auto-generated method stub
		try{
			Thread.sleep(100);
			System.out.println(name + " ");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
