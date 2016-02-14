package com.allenfancy.selenium;

import org.apache.commons.pool2.impl.SoftReferenceObjectPool;
import org.openqa.selenium.WebDriver;

public class WebDriverDemo1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SoftReferenceObjectPool<WebDriver> driver = new SoftReferenceObjectPool<WebDriver>(new WebDriverFactory());
		WebDriver d = driver.borrowObject();
		
	}

}
