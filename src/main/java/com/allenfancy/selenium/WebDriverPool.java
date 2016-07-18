package com.allenfancy.selenium;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import javax.imageio.ImageIO;

import org.apache.commons.pool2.ObjectPool;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;

public class WebDriverPool {

	private ObjectPool<WebDriver> pool;
	
	private ReentrantLock lock = null;
	private Condition empty = null;
	private Condition full = null;
	
	public WebDriverPool(ObjectPool<WebDriver> pool){
		this.pool = pool;
		this.lock = new ReentrantLock();
		this.empty = lock.newCondition();
		this.full = lock.newCondition();
	}

	public WebDriver screenShot() throws Exception{
		WebDriver driver = null;
		try{
			
			driver = pool.borrowObject();
			System.out.println("借用出来的对象："+pool.getNumActive());
			System.out.println("空闲的对象：" + pool.getNumIdle());
			return driver;
		}catch(Exception e){
			
			return null;
		}finally{
			pool.returnObject(driver);
			driver.quit();
		}
		
	}
	
	private byte[] takeScreenshot(WebDriver driver) throws IOException {
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		return ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
	}

	private synchronized void createImage(WebDriver driver, String xpath) {
		try {
			WebElement webElement = driver.findElement(By.xpath(xpath));
			// 获得webElement的位置和大小。
			Point location = webElement.getLocation();
			Dimension size = webElement.getSize();
			// 创建全屏截图。
			BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(takeScreenshot(driver)));

			// 截取webElement所在位置的子图。
			BufferedImage croppedImage = originalImage.getSubimage(location.getX(), location.getY(), size.getWidth(),
					size.getHeight());
			//InputStream in = Tools.getImageStream(croppedImage);
			//ScreenShotWapper ssw = new ScreenShotWapper(screenShot, in);
			//String url = oSSScreenShotService.pushFile(ssw);
		//	return Constants.PRE_URL + url;
			ImageIO.write(croppedImage, "png", new File("/home/node/temp/result.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
