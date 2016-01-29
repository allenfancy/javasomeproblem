package com.allenfancy.complicating.chmap;

import java.util.HashMap;
import java.util.UUID;

public class Demo1 {

	public static void main(String[] args) throws InterruptedException{
		final HashMap<String,String> map = new HashMap<String,String>(2);
		
		Thread t = new Thread(new Runnable(){
			public void run(){
				for (int i = 0 ;i < 10000;i++){
					new Thread(new Runnable(){

						public void run() {
							// TODO Auto-generated method stub
							map.put(UUID.randomUUID().toString(), "");
						}
						
					},"ftf"+i).start();
				}
			}
		},"fmt");
		t.start();
		t.join();
	}
}
