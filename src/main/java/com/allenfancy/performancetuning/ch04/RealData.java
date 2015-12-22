package com.allenfancy.performancetuning.ch04;

public class RealData implements Data{
	
	protected final String result;
	public RealData(String param){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < 10;i++){
			sb.append(param);
			try{
				Thread.sleep(100);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		result = sb.toString();
	}
	public String getResult() {
		// TODO Auto-generated method stub
		
		return result;
	}

}
