package com.allenfancy.apache.common.pool;


import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.openqa.selenium.WebDriver;

public class WebDriverPool extends GenericObjectPool<WebDriver>{
	
	public WebDriverPool(GenericObjectPoolConfig config){
		//System.out.println("Come into WebDriverFactory");
		super(new WebDriverFactory(),config);
	}
}
