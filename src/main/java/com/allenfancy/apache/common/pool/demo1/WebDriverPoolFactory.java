package com.allenfancy.apache.common.pool.demo1;

import java.net.URL;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverPoolFactory implements PooledObjectFactory<WebDriver> {

	public PooledObject<WebDriver> makeObject() throws Exception {
		// TODO Auto-generated method stub
		WebDriver webDriver = null;
		try {
			/*String serverUrl = "http://192.168.179.142:4444/wd/hub";
			DesiredCapabilities capability = DesiredCapabilities.firefox();
			capability.setJavascriptEnabled(true);
			webDriver = new RemoteWebDriver(new URL(serverUrl), capability);*/
			webDriver = new FirefoxDriver();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new DefaultPooledObject<WebDriver>(webDriver);
	}

	public void destroyObject(PooledObject<WebDriver> p) throws Exception {
		// TODO Auto-generated method stub
		final WebDriver webDriver = p.getObject();
		try {
			webDriver.quit();
		} catch (Exception e) {

		}

	}

	public boolean validateObject(PooledObject<WebDriver> p) {
		// TODO Auto-generated method stub
		WebDriver webDriver = p.getObject();
		if (webDriver != null) {
			return true;
		} else {
			return false;
		}

	}

	public void activateObject(PooledObject<WebDriver> p) throws Exception {
		// TODO Auto-generated method stub
	}

	public void passivateObject(PooledObject<WebDriver> p) throws Exception {
		// TODO Not sure how to write
	}

}
