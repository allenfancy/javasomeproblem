package com.allenfancy.io.pipe;

import java.io.PipedOutputStream;

public class Send implements Runnable{

	private PipedOutputStream out = null;
	
	public Send(){
		out = new PipedOutputStream();
	}
	
	public PipedOutputStream getOut(){
		return this.out;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		String message = "Hello, Allen";
		try{
			out.write(message.getBytes());
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try{
				out.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
