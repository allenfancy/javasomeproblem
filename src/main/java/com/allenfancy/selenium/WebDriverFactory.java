package com.allenfancy.selenium;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory extends BasePooledObjectFactory<WebDriver> {

	
	
	@Override
	public WebDriver create() throws Exception {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "/Users/allen/temp/chromedriver");	
		return new ChromeDriver();
	}

	@Override
	public PooledObject<WebDriver> wrap(WebDriver webDriver) {
		// TODO Auto-generated method stub
		return new DefaultPooledObject<WebDriver>(webDriver);
	}
}
