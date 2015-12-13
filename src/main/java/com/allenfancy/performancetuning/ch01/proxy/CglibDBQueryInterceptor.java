package com.allenfancy.performancetuning.ch01.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;



public class CglibDBQueryInterceptor implements MethodInterceptor {

	Object obj;


	public  Object createCglibProxy(Object target) {
		this.obj = target;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.obj.getClass());
		enhancer.setCallback(this);
		enhancer.setClassLoader(target.getClass().getClassLoader());
		return enhancer.create();
	}

	public Object intercept(Object obj, Method method, Object[] params, MethodProxy porxy) throws Throwable {
		// TODO Auto-generated method stub
		Object result  = null;
		
		result = porxy.invokeSuper(obj, params);
		
		return result;
	}
}
