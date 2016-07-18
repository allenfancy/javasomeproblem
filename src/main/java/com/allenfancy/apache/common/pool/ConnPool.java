package com.allenfancy.apache.common.pool;

import org.apache.commons.pool2.impl.GenericObjectPool;

public class ConnPool extends GenericObjectPool<Conn> {

	public ConnPool() {
		super(new ConnFactory(), new ConnPoolConfig());
	}

	public ConnPool(ConnPoolConfig connPoolConfig) {
		
		super(new ConnFactory(), connPoolConfig);
	}

}
