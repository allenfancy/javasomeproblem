package com.allenfancy.selenium;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class Demo6 {

	public static void userDocumentGetElementById(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Long xValue = (Long) jse
				.executeScript("oRect = document.getElementById(\"CTA\").getBoundingClientRect();"
						+ "return oRect.left+window.screenLeft + document.documentElement.scrollLeft - document.documentElement.clientLeft;");
		Long yValue = (Long) jse
				.executeScript("oRect = document.getElementById(\"CTA\").getBoundingClientRect();"
						+ "return oRect.top+ window.screenTop + document.documentElement.scrollTop - document.documentElement.clientTop;");
		Double width = (Double) jse.executeScript(
				"oRect = document.getElementById(\"CTA\").getBoundingClientRect();  return oRect.width;");
		Double height = (Double) jse.executeScript(
				"oRect = document.getElementById(\"CTA\").getBoundingClientRect();  return oRect.height;");
		System.out.println(xValue +" " + yValue + " " +width +" " +height);
		captureScreen("test111"
			     ,Integer.parseInt(String.valueOf(xValue))
			     ,Integer.parseInt(String.valueOf(yValue))
			     ,Integer.parseInt(new java.text.DecimalFormat("0").format(width))  
			     ,Integer.parseInt(new java.text.DecimalFormat("0").format(height)) 
			     );   
	}

	public static void captureScreen(String screenName, int x, int y,
			   int width, int height) {
			  try {
			   BufferedImage capture = null;
			   Rectangle area = new Rectangle(x, y, width, height);
			   Robot robot = new Robot();
			   capture = robot.createScreenCapture(area);
			   String fn = "/Users/allen/temp/" + screenName + ".jpg";
			   FileOutputStream out = new FileOutputStream(fn);
			   JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			   encoder.encode(capture);
			   out.flush();
			   out.close(); 
			  } catch (Exception e) {
			   e.printStackTrace();
			  }
	}
	public static void main(String[] args) throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", "/Users/allen/temp/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(3, TimeUnit.SECONDS);
		String login = "https://www.baidu.com";
		driver.get(login);
		
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.manage().timeouts().setScriptTimeout(3, TimeUnit.SECONDS);
		userDocumentGetElementById(driver);
		driver.quit();
	}
}
