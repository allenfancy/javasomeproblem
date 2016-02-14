package com.allenfancy.apache.common.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;


public class ConnFactory extends BasePooledObjectFactory<Conn>{

	public Conn create() throws Exception {
		// TODO Auto-generated method stub
		return new Conn();
	}
	public PooledObject<Conn> wrap(Conn obj) {
		// TODO Auto-generated method stub
		return new DefaultPooledObject<Conn>(obj);
	}

}
