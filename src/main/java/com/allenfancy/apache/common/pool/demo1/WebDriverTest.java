package com.allenfancy.apache.common.pool.demo1;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.openqa.selenium.WebDriver;

public class WebDriverTest {
	
	ExecutorService es = Executors.newFixedThreadPool(20);
	WebDriverPool pool = new WebDriverPool(new WebPoolConfig());
	
	public static void main(String[] args) {

		
		WebDriverTest wdt = new WebDriverTest();
		wdt.ex();
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(convert(System.currentTimeMillis()));
			}
		}, 60000);

	}
	
	
	public void ex(){
		for (int i = 0; i < 5; i++) {
			final WebDriver d = pool.getResource();
			final String url = "http://www.baidu.com";
			es.submit(new Runnable() {
				public void run() {
					d.get(url);
					System.out.println(url);
				}
			});
			pool.returnResourceObject(d);
		}
	}
	private static String convert(Long time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
		return sdf.format(time);
	}
}
