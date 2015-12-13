package com.allenfancy.performancetuning.ch01.proxy;

import java.lang.reflect.Method;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtNewConstructor;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;


public class JavassistDyDbQueryHandler implements MethodHandler{

	IDBQuery real = null;
	
	public Object invoke(Object arg0, Method arg1, Method arg2, Object[] arg3) throws Throwable {
		// TODO Auto-generated method stub
		if(real == null){
			real = new DBQuery();
		}
		return real.request();
	}
	
	public static IDBQuery createJavassistDynProxy() throws InstantiationException, IllegalAccessException {
		ProxyFactory proxyFactory = new ProxyFactory();
		//设置指定的接口
		proxyFactory.setInterfaces(new Class[]{IDBQuery.class});
		
		Class proxyClass = proxyFactory.createClass();
		
		IDBQuery javassistProxy = (IDBQuery) proxyClass.newInstance();
		//这是Handler处理器。
		((ProxyObject)javassistProxy).setHandler(new JavassistDyDbQueryHandler());
		return javassistProxy;
	}
	
	public static IDBQuery createJavassistBytecodeDynamicProxy() throws NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException{
		
		ClassPool mPool = new ClassPool(true);
		
		CtClass mCtc = mPool.makeClass(IDBQuery.class.getName() + "Javaassist-BytecodeProxy");
		
		//需要实现的接口
		mCtc.addInterface(mPool.get(IDBQuery.class.getName()));
		
		//添加构造函数
		mCtc.addConstructor(CtNewConstructor.defaultConstructor(mCtc));
		
		//添加类的字段信息，使用动态Java代码
		mCtc.addField(CtField.make("public " +IDBQuery.class.getName() + "real", mCtc));
		String dbqueryName = DBQuery.class.getName();
		//添加方法，这里使用动态Java代码指定内部逻辑
		mCtc.addMethod(CtNewMethod.make("public String request() {if(real == null) real = new "+dbqueryName+"();return real.request();}",mCtc));
		
		//基于以上信息，生成动态类
		Class pc = mCtc.toClass();
		
		//生成动态类的实例
		IDBQuery byteCodeProxy = (IDBQuery) pc.newInstance();
		
		return byteCodeProxy;
		
	}
}
