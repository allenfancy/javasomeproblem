package com.allenfancy.performancetuning.ch01;

public class Test {

	public static void main(String[] args) {
		final long beginTime = System.currentTimeMillis();
		// TODO Auto-generated method stub
		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {

				public void run() {
					// TODO Auto-generated method stub
					for (int i = 0; i < 100000; i++) {
						//Singleton.getInstance();
						LayzSingleton.getInstance();
						//StaticSingleton.getInstance();
					}
					System.out.println(System.currentTimeMillis() - beginTime);
				}
			}).start();
		}
		
	}

}
