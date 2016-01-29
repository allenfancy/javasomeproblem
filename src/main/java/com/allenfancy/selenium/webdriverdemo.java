package com.allenfancy.selenium;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.bson.types.ObjectId;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class webdriverdemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.navigate().to("http://www.calculator.net/");
		
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath(".//*[@id='menu']/div[3]/a"));
		
		driver.findElement(By.id("cpar1")).sendKeys("10");
		
		driver.findElement(By.id("cpar2")).sendKeys("50");
		
		driver.findElement(By.xpath(".//*[@id='content']/table/tbody/tr/td[2]/input")).click();
		
		String result = driver.findElement(By.xpath(".//*[@id='content']/p[2]/span/font/b")).getText();
		
		File screenshot =  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(screenshot, new File("/Users/allen/temp/"+ObjectId.get().toString()+".jpg"));
		
		System.out.println("The result is : " + result);
		
		driver.close();
	}

}
