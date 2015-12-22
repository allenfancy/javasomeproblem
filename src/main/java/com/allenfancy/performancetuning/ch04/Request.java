package com.allenfancy.performancetuning.ch04;

public class Request {

	private String  name;
	
	public Request(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	
	public String toString(){
		return "[ Request " + name + "]s";
	}
}
