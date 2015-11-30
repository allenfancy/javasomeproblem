package com.allenfancy.distributed.ch01;

public class SayHelloServiceImpl implements SayHelloService{

	public String sayHello(String helloArg) {
		// TODO Auto-generated method stub
		if(helloArg.equals("hello")){
			return "hello";
		}else{
			return "bye bye";
		}
	}

}
