package com.allenfancy.spring.ioc;

import org.springframework.core.Ordered;

public class AnotherAspect implements Ordered{

	//@Before()
	public void doBefore(){
		System.out.println("before in AnotherAspect");
	}
	public int getOrder() {
		// TODO Auto-generated method stub
		return 100;
	}

}
