package com.allenfancy.apache.common.pool.demo1;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.openqa.selenium.WebDriver;

public class WebDriverPool extends WebDriverPoolAbstract {

	public WebDriverPool(final GenericObjectPoolConfig poolConfig) {
		super(poolConfig, new WebDriverPoolFactory());
	}

	@Override
	public WebDriver getResource() {
		WebDriver webDriver = super.getResource();
		return webDriver;
	}

	@Override
	protected void returnBrokenResource(final WebDriver resource) {
		if (resource != null) {
			returnBrokenResourceObject(resource);
		}
	}

	@Override
	protected void returnResource(final WebDriver resource) {
		if (resource != null) {
			try {
				//resource.close();
				returnResourceObject(resource);
			} catch (Exception e) {
				returnBrokenResource(resource);
				throw new RuntimeException("Could not return the resource to the pool", e);
			}
		}
	}
}
