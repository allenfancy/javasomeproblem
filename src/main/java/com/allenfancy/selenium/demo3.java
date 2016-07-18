package com.allenfancy.selenium;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class demo3 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.baidu.com/");
		// File scrFile =
		// ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy
		// somewhere
		// FileUtils.copyFile(scrFile, new
		// File("/Users/allen/temp/screenshot.png"));
		Set<String> set = driver.getWindowHandles();
		for (String str : set) {
			System.out.println(str);
		}
		Set<Cookie> cookies = driver.manage().getCookies();
		for (Cookie c : cookies) {
			System.out.println(c.getDomain() + " " +c.getName() + " " +c.getPath() + " " +c.getValue() + " " +c.getExpiry());
		}
		WebDriver wd = driver.switchTo().defaultContent();
		System.out.println(wd.getCurrentUrl());
		
	}
}
