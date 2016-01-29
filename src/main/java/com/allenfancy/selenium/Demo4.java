package com.allenfancy.selenium;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
/**
 * Selenium：
 * 1.WebDriver(打开一个浏览器)类型
 *   FireFox浏览器
 *   WebDriver driver = new FirefoxDriver();
 *   IE浏览器
 *   WebDriver driver = new InternetExplorerDriver()
 *   HtmlUnit浏览器
 *   WebDriver driver = new HtmlUnitDriver()
 *   Chrome浏览器
 *   WebDriver driver = new ChromeDriver()
 *   
 * 2.最大化浏览器
 * 	WebDriver driver = new FirefoxDriver()
 *  driver.manage().window().maximize()
 * 3.关闭浏览器
 *   driver.close()
 *   driver.quit()
 * 4.打开测试页面
 * 	 driver.get("http://www.goole.con")
 *   driver.navigate().to("http://www.baidu.com")//navigate方法会产生1个Navigator对象，其封装与导航相关的一些方法，如前进后退等
 * 5.页面元素定位
 * 	 WebDriver提供下面俩种方法来定位页面元素，参数是By对象，最常用的是By.id和By.name查找
 *   findElement  定位某个元素，如果没有找到元素会抛出异常:NoSuchElementException
 *   findElements 定位一组元素
 *   <input class="input_class" type="text" name="passwd" id="passwd-id" /> 
 *   By.id        :      WebElement element = driver.findElement(By.id("passwd-id"))
 *   By.name      :      WebElement element = driver.findElement(By.name("passwd"))
 *   By.xpath     :      WebElement element = driver.findElement(By.xpath("//input[@id='passwd-id']"))
 *   By.className :      WebElement element = driver.findElement(By.className("input_class"))
 *   By.cssSelector :    WebElement element = driver.findElement(By.cssSelector(".input_class"))
 *   By.linkText  :
 *   	WebDriver driver = new FirefoxDriver();
 *   	driver.get("http://www.baidu.com/")
 *      WebElement element = driver.findElement(By.linkText("百科"))
 *   By.partialLinkText:
 *      //模糊查询
 *      WebDriver driver = new FireFoxDriver();
 *      driver.get("http://www.baidu.com")
 *      WebElement element = driver.findElement(By.partialLinkText("hao"))
 *   By.tagName
 *   	WebDriver driver = new FireFoxDriver();
 *      driver.get("http://www.baidu.com")
 *     String test = driver.findElement(By.tagName("form")).getAttribute("name");
 *     System.out.println(test)
 * 6.对页面元素进行操作
 * 		1.输入框(text field or textarea)
 * 		WebElement element = driver.findElement(By.id("passwd-id"))
 * 		element.sendKeys("test")   //在输入框中输入内容
 *      element.clear();    //将输入框清空
 *      element.getText();  //将获取输入框的文本内容
 *      2.下拉选择框(Select)
 *      Select select = new Select(driver.findElement(By.id("select")))
 *      	select.selectByValue("A")
 *          select.selectByValue("1")
 *          select.deselectAll();
 *          select.deselectByValue("1")
 *          select.deselectByVisibleText("A")
 *          select.getAllSelectedOptions()
 *          select.getFirstSelectedOption();
 *      3.单选框(Radio Button)
 *      	WebElement radio = driver.findElement(By.id("BookMode"))
 *      		radio.click()			//选择某个单选项
 *      		radio.clear()           //清空某个单选项
 *      		radio.isSelected()		//判断某个单选框是否已经被选择
 *      4.多选框(checkBox)
 *      	WebElement checkbox = driver.findElement(By.id("myCheckbox."));
 *			checkbox.click();
 *			checkbox.clear();
 *			checkbox.isSelected();
 *			checkbox.isEnabled();
 *      5.按钮
 *      	WebElement btn = driver.findElement(By.id("save"))
 *      	btn.click()       //点击按钮
 *      	btn.isEnable()    //判断按钮是否enable
 *      6.弹出框对话(Popup dialogs)
 *      	Alert alert = driver.switchTo().alert()
 *      	alert.accept();//确认
 *      	alert.dismiss()//取消
 *      	alert.getText();//获取文本
 *      7.Form中的元素的操作和其他的元素操作一样，对元素操作完成后对表单的提交可以：
 *      	WebElement approve = driver.findElement(By.id("approve"));
 *      	approve.click();
 *      	approve.submit();//只适合于提交表单
 *      8.上传文件
 *      	WebElement adFileUpload = driver.findElement(By.id("WAP-upload"));
 *      	String filePath = "fdskfjhsadkjg";
 *      	adFileUpload.sendKeys(filePath);
 *      
 *      9.Windows和Frames之间的切换
 *      	driver.switchTo().defaultContent()   //返回到最顶层的frame/iFrame
 *          driver.switchTo().frame("leftFrame") 	//切换到某个frame
 *          driver.switchTo().window("windowName") //切换到某个window
 *      10.调用Java Script
 *      	Web driver对JavaScript的调用通过JavaScriptExecutor来实现的。
 *      	JavascriptExecutor js = (JavascriptExecutor)driver;
 *          js.executeScript("JS脚本")
 *      11.设置超时
 *      	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);//识别元素时的超时时间
 *          driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);//页面加载时的超时时间
 *          driver.manage().timeouts().setScriptTimeouot(10,TimeUnit.SENCONDS);//异步脚本的超时时间
 *      		
 */
public class Demo4 {

	public static void snapshot(TakesScreenshot drivername, String filename) {
		// this method will take screen shot ,require two parameters ,one is
		// driver name, another is file name

		File scrFile = drivername.getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy
		// somewhere
		try {
			System.out.println("save snapshot path is:E:/" + filename);
			FileUtils.copyFile(scrFile, new File("E:\\" + filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't save screenshot");
			e.printStackTrace();
		} finally {
			System.out.println("screen shot finished");
		}
	}

	public static void snapshot2(WebDriver drivername, String filename) {
		// this method will take screen shot ,require two parameters ,one is
		// driver name, another is file name

		// File scrFile = drivername.getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy
		// somewhere
		try {
			WebDriver augmentedDriver = new Augmenter().augment(drivername);
			File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			File file = new File("E:\\" + filename);
			FileUtils.copyFile(screenshot, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't save screenshot");
			e.printStackTrace();
		} finally {
			System.out.println("screen shot finished");
		}
	}

	public static byte[] takeScreenshot(WebDriver driver) throws IOException {
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		return ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
		// TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		// return takesScreenshot.getScreenshotAs(OutputType.BYTES);
	}

	public static BufferedImage createElementImage(WebDriver driver, WebElement webElement) throws IOException {
		// 获得webElement的位置和大小。
		Point location = webElement.getLocation();
		Dimension size = webElement.getSize();
		// 创建全屏截图。
		BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(takeScreenshot(driver)));
		// 截取webElement所在位置的子图。
		BufferedImage croppedImage = originalImage.getSubimage(location.getX(), location.getY(), size.getWidth(),
				size.getHeight());
		return croppedImage;
	}

	public static void login(WebDriver driver) throws Exception {
		// 新浪微博自己发出的评论地址
		// http://weibo.com/comment/outbox?wvr=1
		String login = "https://login.sina.com.cn/signup/signin.php?entry=homepage";
		driver.get(login);
		Thread.sleep(2000);
		WebElement keyWord = driver.findElement(By.xpath("//input[@name='username']"));
		keyWord.clear();
		keyWord.sendKeys("nbr987654321@163.com");
		keyWord = driver.findElement(By.xpath("//input[@name='password']"));
		keyWord.clear();
		keyWord.sendKeys("XXX");
		try {
			keyWord = driver.findElement(By.xpath("//img[@id='check_img']"));
			if (keyWord != null) {

				String src = keyWord.getAttribute("src");
				System.out.println("验证码地址 =====" + src);
				if (src != null && !"".equals(src)) {
					BufferedImage inputbig = createElementImage(driver, keyWord);
					ImageIO.write(inputbig, "png", new File("e://testasdasd.png"));
					String key = "";
					// 验证码输入框
					keyWord = driver.findElement(By.xpath("//input[@id='door']"));
					keyWord.clear();
					keyWord.sendKeys(key);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		keyWord = driver.findElement(By.xpath("//input[@class='btn_submit_m']"));
		keyWord.click();
		Thread.sleep(2000);
		try {
			keyWord = driver.findElement(By.xpath("//img[@id='check_img']"));
			if (keyWord != null) {
				String src = keyWord.getAttribute("src");
				System.out.println("验证码地址 =====" + src);
				if (src != null && !"".equals(src)) {
					snapshot2(driver, "open_baidu_sinaweibo1111111111.png");

					BufferedImage inputbig = createElementImage(driver, keyWord);
					ImageIO.write(inputbig, "png", new File("e://testasdasd.png"));

					String key = "";
					// 验证码输入框
					keyWord = driver.findElement(By.xpath("//input[@id='door']"));
					keyWord.clear();
					keyWord.sendKeys(key);
					// 在提交
					keyWord = driver.findElement(By.xpath("//input[@class='btn_submit_m']"));
					keyWord.click();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(2000);
	}

	public static void main(String[] args) throws Exception {

		String serverUrl = "http://192.168.1.107:4444/wd/hub";
		String URL = "http://weibo.com/comment/outbox?wvr=1";// http://coral.qq.com/1008591939
		// System.setProperty("webdriver.chrome.driver",
		// "d:\\ie\\chromedriver.exe");
		// WebDriver driver = new ChromeDriver();
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setJavascriptEnabled(true);
		WebDriver driver = new RemoteWebDriver(new URL(serverUrl), capability);
		// 如果3s内还定位不到则抛出异常
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		// 页面加载超时时间设置为5s
		// driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		// driver.manage.script_timeout = 3 #seconds
		driver.manage().timeouts().setScriptTimeout(3, TimeUnit.SECONDS);
		login(driver);
		snapshot2(driver, "open_baidu_sinaweibo1.png");
		// 跳转新页面
		// driver.navigate().to(URL);
		driver.get(URL);
		// max size the browser
		driver.manage().window().maximize();
		/*
		 * Navigation navigation = driver.navigate(); navigation.to(URL);
		 */
		Thread.sleep(2000);
		// snapshot((TakesScreenshot)driver,"open_baidu.png");
		snapshot2(driver, "open_baidu_sinaweibo.png");

		driver.quit();

	}
}
