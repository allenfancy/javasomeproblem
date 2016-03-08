package com.allenfancy.apache.common.pool.demo1;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.openqa.selenium.WebDriver;

public class WebDriverPoolAbstract extends Pool<WebDriver> {

	public WebDriverPoolAbstract() {
		super();
	}

	public WebDriverPoolAbstract(GenericObjectPoolConfig poolConfig, PooledObjectFactory<WebDriver> factory) {
		super(poolConfig, factory);
	}

	@Override
	protected void returnBrokenResource(WebDriver resource) {
		super.returnBrokenResource(resource);
	}

	@Override
	protected void returnResource(WebDriver resource) {
		super.returnResource(resource);
	}
}
