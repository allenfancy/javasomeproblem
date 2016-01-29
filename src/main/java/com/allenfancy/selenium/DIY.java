package com.allenfancy.selenium;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DIY {

	private static final List<WebDriver> set = new ArrayList<WebDriver>();
	
	static{
		
		for(int i = 0;i < 10;i++){
			System.setProperty("webdriver.chrome.driver", "/Users/allen/temp/chromedriver");	
			WebDriver d =  new ChromeDriver();
			set.add(d);
		}
	}
	
	
	public static void main(String [] args){
		for(int i = 0; i < 20; i++){
			final Random r = new Random();
			new Thread(new Runnable(){
				public void run() {
					// TODO Auto-generated method stub
					WebDriver d;
					try {
						int  index = r.nextInt(set.size());
						System.out.println(Thread.currentThread().getName() +" : "+index );
						d = set.get(index);
					//	d = webDriver.screenShot();
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
