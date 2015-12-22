package com.allenfancy.performancetuning.ch04;

import java.util.concurrent.Callable;

public class RealData1 implements Callable<String>{

	private String para;
	
	public RealData1(String para){
		this.para = para;
	}
	public String call() throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < 10;i++){
			sb.append(para+ " ");
			/*try{
				Thread.sleep(100);
			}catch(Exception e){
				e.printStackTrace();
			}*/
		}
		return sb.toString();
	}

}
