package com.allenfancy.performancetuning.ch03;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public class CheckRefQueue {

	public void start() {
		// TODO Auto-generated method stub
		ReferenceQueue softQueue = new ReferenceQueue();
		Reference<MyObject> obj = null;
		try{
			obj = (Reference<MyObject>) softQueue.remove();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
}
