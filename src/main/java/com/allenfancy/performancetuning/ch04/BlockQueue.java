package com.allenfancy.performancetuning.ch04;

import java.util.ArrayList;
import java.util.List;

public class BlockQueue {

	private static List list = new ArrayList();
	
	public synchronized Object pop() throws InterruptedException{
		while(list.size() == 0)
			this.wait();
		if(list.size() >0){
			return list.remove(0);
		}else{
			return null;
		}
	}
	
	public synchronized void put(Object obj){
		list.add(obj);
		this.notify();
	}
}
