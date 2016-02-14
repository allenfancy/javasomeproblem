package com.allenfancy.selenium;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.openqa.selenium.WebDriver;


public class WebDriverClient {

	public static void main(String[] args) throws Exception{
		GenericObjectPool<WebDriver> gop = new GenericObjectPool<WebDriver>(new WebDriverFactory());
		gop.setMaxIdle(5);
		gop.setMaxTotal(15);
		gop.setTestOnBorrow(true);
		gop.setTestOnReturn(true);
	
		final WebDriverPool webDriver = new WebDriverPool(gop);
		for(int i = 0; i < 20; i++){
		new Thread(new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				WebDriver d;
				try {
					d = webDriver.screenShot();
					System.out.println(Thread.currentThread().getName() +" : "+d.getClass().getSimpleName());
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			
		}).start();
		
		}
	}
}
