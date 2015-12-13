package com.allenfancy.performancetuning.ch02;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.poi.hssf.record.formula.functions.T;

public class PoolableObjectFactoryDemo implements PoolableObjectFactory<Object>{

	private static AtomicInteger counter = new AtomicInteger(0);
	//创建对象
	public Object makeObject() throws Exception {
		// TODO Auto-generated method stub
		Object obj = String.valueOf(counter.getAndIncrement());
		System.out.println("Create Object :" + obj );
		return obj;
	}
	//销毁对象
	public void destroyObject(Object obj) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("销毁对象：" +obj);
	}
	//校验对象
	public boolean validateObject(Object obj) {
		// TODO Auto-generated method stub
		return true;
	}
	//在提取前调用
	public void activateObject(Object obj) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("before borrow : "+obj);
	}

	//当对象返回池中时被调用
	public void passivateObject(Object obj) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("return  : " + obj);
	}

}
