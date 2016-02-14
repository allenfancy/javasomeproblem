package com.allenfancy.selenium;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Demo7 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebDriver driver = new HtmlUnitDriver();
		//driver.
		driver.get("http://www.baidu.com/");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		FileUtils.copyFile(scrFile, new File("/Users/allen/temp/screenshot.png"));
	}

}
