package com.allenfancy.spring.ioc;

import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.core.io.Resource;

public class ResourceSetUpBeforeAdvice implements MethodBeforeAdvice{

	private Resource resource;
	public ResourceSetUpBeforeAdvice(Resource resource){
		this.resource = resource;
	}
	
	public void before(Method method, Object[] args, Object target) throws Throwable {
		// TODO Auto-generated method stub
		if(!resource.exists()){
			FileUtils.forceMkdir(resource.getFile());
		}
	}

}
