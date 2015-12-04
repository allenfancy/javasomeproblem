package com.allenfancy.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.allenfancy.spring.service.IUserService;

public class TestMain {

	public static void main(String[] args){
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext_spring01.xml");
		IUserService userService  = (IUserService) ac.getBean("userService");
		IUserService userService1 = (IUserService) ac.getBean("userService");
		System.out.println(userService);
		System.out.println(ac.isPrototype("userService"));
		System.out.println(userService.equals(userService1));
		System.out.println(ac.getAliases("userService"));
		System.out.println(ac.getId());
		ApplicationContext acParent = ac.getParent();
		System.out.println(acParent);
		
		IUserService userService2 = ac.getBean("userService", IUserService.class);
		
	}
}
