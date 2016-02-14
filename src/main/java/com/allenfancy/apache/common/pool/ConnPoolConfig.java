package com.allenfancy.apache.common.pool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class ConnPoolConfig extends GenericObjectPoolConfig{

	public ConnPoolConfig(){
		setMinIdle(5);
		setTestOnBorrow(true);
		setMaxWaitMillis(1000);
	}
}
