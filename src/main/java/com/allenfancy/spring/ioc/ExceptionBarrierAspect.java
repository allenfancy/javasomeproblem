package com.allenfancy.spring.ioc;

import org.springframework.mail.javamail.JavaMailSender;
//@Aspect
public class ExceptionBarrierAspect {

	private JavaMailSender mailSender;
	private String[] receiptions;
	
	//@AfterThrowing(pointcut="execution(boolean *.execution(String,..))*",throwing="e")
	public void afterThrowing(RuntimeException e){
		
	}
}
