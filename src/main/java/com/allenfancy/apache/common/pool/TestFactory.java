package com.allenfancy.apache.common.pool;

import org.apache.commons.pool.PoolableObjectFactory;
public class TestFactory implements PoolableObjectFactory<BigObject>{

	public BigObject makeObject() throws Exception {
		// TODO Auto-generated method stub
		BigObject bo = new BigObject();
		return bo;
	}

	public void destroyObject(BigObject obj) throws Exception {
		// TODO Auto-generated method stub
		obj = null;
	}

	public boolean validateObject(BigObject obj) {
		if(((BigObject)obj).isActive()){
			return true;
		}else{
			return false;
		}
	}

	public void activateObject(BigObject obj) throws Exception {
		// TODO Auto-generated method stub
		((BigObject)obj).setActive(true);
	}

	public void passivateObject(BigObject obj) throws Exception {
		// TODO Auto-generated method stub
		((BigObject)obj).setActive(false);
	}

	

}
