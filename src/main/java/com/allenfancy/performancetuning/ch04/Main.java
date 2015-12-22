package com.allenfancy.performancetuning.ch04;

public class Main {

	public static void  main(String[] args){
		Client client = new Client();
		
		Data data = client.request("name");
		
		System.out.println("请求完成");
		
		try{
			Thread.sleep(2000);
		}catch(Exception e){
			
		}
		
		System.out.println("Data = " +data.getResult());
	}
}
