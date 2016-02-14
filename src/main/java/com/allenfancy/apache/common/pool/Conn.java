package com.allenfancy.apache.common.pool;

import org.apache.log4j.spi.LoggerFactory;

public class Conn {
	/**
	 * 记住创建时间
	 */
	private long createTime;
	
	public Conn() throws InterruptedException{
		Thread.sleep(500);
		createTime = System.currentTimeMillis();
		System.out.println("init conn suc ..." + createTime);
	}
	//报告对象
	public void report(){
		System.out.println("this is a available conn " +createTime);
	}
}
