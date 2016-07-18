package com.allenfancy.apache.common.pool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.openqa.selenium.WebDriver;

public class WebDriverPoolTest {
	

	public static void main(String[] args) throws Exception {
		/*for (int i = 0; i < 4; i++) {
			new Thread(new Runnable() {
				public void run() {
					// TODO Auto-generated method stub
					try {
						SoftReferenceObjectPool<WebDriver> pool = new SoftReferenceObjectPool<WebDriver>(
								new WebDriverFactory());
						WebDriver driver = pool.borrowObject();
						Long start = System.currentTimeMillis();
						driver = pool.borrowObject();
						driver.manage().window().maximize();
						System.out.println(Thread.currentThread().getName() + ",实例化一个Chrome时间 ：" + (System.currentTimeMillis() - start));
						driver.get("http://www.baidu.com");
						System.out.println("我在做事儿呢");
						pool.returnObject(driver);
						System.out.println("截图耗时：" + (System.currentTimeMillis() - start));
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}*/
		WebDriverPool pool = new WebDriverPool(new GenericObjectPoolConfig());
		Long start = System.currentTimeMillis();
		WebDriver driver = pool.borrowObject();
		driver.manage().window().maximize();
		System.out.println(Thread.currentThread().getName() + ",实例化一个Chrome时间 ：" + (System.currentTimeMillis() - start));
		driver.get("http://www.baidu.com");
		System.out.println("我在做事儿呢");
		pool.returnObject(driver);
		System.out.println("截图耗时：" + (System.currentTimeMillis() - start));
		
	}

}
