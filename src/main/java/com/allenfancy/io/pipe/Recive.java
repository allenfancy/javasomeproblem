package com.allenfancy.io.pipe;

import java.io.PipedInputStream;

/**
 * 接收消息类
 * @author allen
 *
 */
public class Recive implements Runnable{

	private PipedInputStream input = null;
	public Recive(){
		this.input = new PipedInputStream();
	}
	
	public PipedInputStream getInput() {
		return this.input;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		byte[] b = new byte[1000];
		int len = 0;
		try{
			len = this.input.read(b);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try{
				input.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println("接受的内容为 " + (new String(b,0,len)));
	}

}
