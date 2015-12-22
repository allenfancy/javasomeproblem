package com.allenfancy.performancetuning.ch04;

public class FutureData implements Data {

	protected RealData realData = null;
	
	protected boolean isReady = false;
	
	public synchronized void setRealData(RealData realData){
		if(isReady){
			return;
		}
		this.realData = realData;
		isReady = true;
		notifyAll();//RealData已经被注入，通知getResult
	}
	public synchronized String getResult() {
		// TODO Auto-generated method stub
		while(!isReady){
			try{
				wait();
			}catch(Exception e){
				
			}
		}
		return realData.result;
	}

}
