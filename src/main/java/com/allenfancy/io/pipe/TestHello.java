package com.allenfancy.io.pipe;

public class TestHello {

	public static void main(String[] args){
		Send send = new Send();
		Recive recive = new Recive();
		
		try{
			send.getOut().connect(recive.getInput());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		new Thread(send).start();
		new Thread(recive).start();
	}
}
