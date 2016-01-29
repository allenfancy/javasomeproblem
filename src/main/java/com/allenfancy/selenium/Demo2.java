package com.allenfancy.selenium;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Demo2 {

	public static void snapshot(TakesScreenshot drivername,String filename){
		File srcFile = drivername.getScreenshotAs(OutputType.FILE);
		try{
			FileUtils.copyFile(srcFile, new File("/Users/allen/temp/"+filename));
		}catch(Exception e){
			System.out.println("Can not save screenshot");
			e.printStackTrace();
		}finally{
			System.out.println("screen shot finished");
		}
	}
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String URL = "http://www.baidu.com";
		//System.setProperty("webdriver.chrome.driver", "/Applications/Google\\ Chrome.app/Contents/MacOS/Google\\ Chrome");
		WebDriver driver = new FirefoxDriver();
		
		driver.get(URL);
		driver.manage().window().maximize();
		
		Thread.sleep(2000);
		snapshot((TakesScreenshot)driver, "open_baidu.png");
		
		WebElement keyWord = driver.findElement(By.xpath("//input[@id='kw']"));
		keyWord.clear();
		keyWord.sendKeys("Selenium");
		
		Thread.sleep(3000);
		
		snapshot((TakesScreenshot)driver, "input_keyWord.png");
		
		WebElement submit = driver.findElement(By.id("su1"));
		
		System.out.println(submit.getLocation());
		
		submit.click();
		System.out.println(driver.getWindowHandle());
		Thread.sleep(5000);
		
		String pageSource = driver.getPageSource();
		System.out.println(pageSource);
		
		WebElement link = driver.findElement(By.xpath("//*[@id=\"1\"]/h3/a"));
		link.click();
		Thread.sleep(5000);
		driver.switchTo().window(driver.getWindowHandles().toArray(new String[0])[1]);
		
		System.out.println(driver.getTitle());
		
		Thread.sleep(5000);
		snapshot((TakesScreenshot)driver,"open_bake.png");
		
		System.out.println(driver.getTitle()+"\n"+driver.getCurrentUrl());
        driver.quit();
        
	}

}
