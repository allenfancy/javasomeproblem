package com.allenfancy.performancetuning.ch01.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKDBQueryHandler implements InvocationHandler{

	IDBQuery real = null;
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		if(real == null)
			real = new DBQuery();
		return real.request();
	}

	public static IDBQuery createJdkProxy(){
		IDBQuery jdkProxy = (IDBQuery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{IDBQuery.class}, 
				new JDKDBQueryHandler());
		return jdkProxy;
	}
}
