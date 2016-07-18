package com.allenfancy.selenium;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Demo1 {

	
	public static WebDriver driver;
	public String URL,Node;
	protected ThreadLocal<RemoteWebDriver> threadDriver = null;
	
	
	
	public static void main(String[] args){
		try {
			launchapp("firefox");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void launchapp(String browser) throws IOException {
		
		String URL = "http://www.baidu.com";
		if(browser.equalsIgnoreCase("firefox")){
			System.out.println("Executing on FireFox");
			String Node = "http://www.baidu.com";
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setBrowserName("firefox");
			
			driver = new RemoteWebDriver(new URL(Node),cap);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			driver.navigate().to(URL);
			driver.manage().window().maximize();
		}else{
			
			System.out.println("The Browser Type is Undefined");
		}
	}
}
