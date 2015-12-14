package com.allenfancy.performancetuning.ch03;

public class MyObject {

	protected void finalize() throws Throwable{
		super.finalize();
		//被回收时输出
		System.out.println("MyObject`s finalize called");
	}
	public String toString(){
		return "I am MyObject";
	}
}
