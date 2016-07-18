package com.allenfancy.selenium;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class Demo5 {

	public static byte[] takeScreenshot(WebDriver driver) throws IOException {
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		return ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
	}

	public static BufferedImage createElementImage(WebDriver driver, WebElement webElement) throws IOException {
		// 获得webElement的位置和大小。
		Point location = webElement.getLocation();
		Dimension size = webElement.getSize();
		// 创建全屏截图。
		BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(takeScreenshot(driver)));
		System.out.println(originalImage.getWidth()+" " +originalImage.getHeight()+" " +originalImage.getTileGridXOffset() 
		+ " " +originalImage.getTileGridYOffset() +" " +originalImage.getTileWidth()+" " + originalImage.getTileHeight());
	//	ImageIO.write(originalImage, "png", new File("/Users/allen/temp/all.png"));
		// 截取webElement所在位置的子图。
		
		System.out.println(location.getX() + " " + location.getY() + " " + size.getWidth() + " " + size.getHeight());
		BufferedImage croppedImage = originalImage.getSubimage(location.getX(), location.getY(), size.getWidth(),
				size.getHeight());
		
		System.out.println(croppedImage.getWidth()+" " +croppedImage.getHeight()+" " +croppedImage.getTileGridXOffset() 
		+ " " +croppedImage.getTileGridYOffset() +" " +croppedImage.getTileWidth()+" " + croppedImage.getTileHeight());
	
		return croppedImage;
	}

	

	public static void main(String[] args) throws Exception {
		//String serverUrl = "http://127.0.0.1:4444/wd/hub";
		System.setProperty("webdriver.chrome.driver", "/Users/allen/temp/chromedriver");
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setJavascriptEnabled(true);
		//capability.
		//WebDriver driver = new RemoteWebDriver(new URL(serverUrl), capability);
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(3, TimeUnit.SECONDS);
		String login = "https://www.baidu.com";
		driver.get(login);
		
		driver.manage().window().maximize();
	
		Thread.sleep(3000);
		driver.manage().timeouts().setScriptTimeout(3, TimeUnit.SECONDS);
		WebElement keyWord = driver.findElement(By.xpath("//input[@class='bg s_btn']"));
		BufferedImage inputbig = createElementImage(driver, keyWord);
		ImageIO.write(inputbig, "png", new File("/Users/allen/temp/result.png"));
		driver.quit();
	}
}
