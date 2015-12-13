package com.allenfancy.performancetuning.ch01.proxy;
/**
 * @author allen
 * 代理模式：
 * 角色				作用：
 * 主题接口		定义代理类和真实主题的公共对外方法，也是代理类代理真实主题的方法
 * 真实主题		真正实现业务逻辑的类
 * 代理类		用来代理和封装真实主题
 * Main			客户端，使用代理类和主题接口完成一些事儿
 * 
 * 	
 *
 */
public class Test {

	private static final int CR = 30000;
	public static void main(String[] args) throws InstantiationException, IllegalAccessException{
		/*IDBQuery q = new DBQueryProxy();
		System.out.println(q.request());*/
		IDBQuery q = null;
		long begin = System.currentTimeMillis();
		q = JDKDBQueryHandler.createJdkProxy();
		System.out.println("CreateJDKProxy:"+ (System.currentTimeMillis() - begin));
		System.out.println("JdkProxy : " + q.getClass().getName());
		begin = System.currentTimeMillis();
		for(int i = 0;i<CR;i++){
			q.request();
		}
		System.out.println("callJdkProxy : " + (System.currentTimeMillis() - begin));
		
		//测试CGLIB动态代理
		begin = System.currentTimeMillis();
		CglibDBQueryInterceptor cg = new CglibDBQueryInterceptor();
		q = (IDBQuery) cg.createCglibProxy(new DBQuery());
		System.out.println("CreateCGLIBProxy:"+ (System.currentTimeMillis() - begin));
		System.out.println("CGLIBProxy : " + q.getClass().getName());
		begin = System.currentTimeMillis();
		for(int i = 0;i<CR;i++){
			q.request();
		}
		System.out.println("callCGLIBProxy : " + (System.currentTimeMillis() - begin));
		
		//测试JAVASSIST
		begin = System.currentTimeMillis();
		q = JavassistDyDbQueryHandler.createJavassistDynProxy();
		System.out.println("CreateJavassistProxy:"+ (System.currentTimeMillis() - begin));
		System.out.println("JavassistProxy : " + q.getClass().getName());
		begin = System.currentTimeMillis();
		for(int i = 0;i<CR;i++){
			q.request();
		}
		System.out.println("callJavassistProxy : " + (System.currentTimeMillis() - begin));
	}
}
