package com.allenfancy.apache.common.pool.demo1;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class WebPoolConfig extends GenericObjectPoolConfig {
	public WebPoolConfig() {
		setTestWhileIdle(true);
		setMinEvictableIdleTimeMillis(6000);//一分钟内，如果对象没有被使用，那么他就要被销毁
		setTimeBetweenEvictionRunsMillis(3000);
		setNumTestsPerEvictionRun(-1);
	}
}
