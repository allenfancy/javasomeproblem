package com.allenfancy.performancetuning.ch04;

public class Client {

	public Data request(final String queryStr){
		final FutureData futureData = new FutureData();
		new Thread(){
			public void run(){
				RealData realData = new RealData(queryStr);
				futureData.setRealData(realData);
			}
		}.start();
		return futureData;
	}
}
