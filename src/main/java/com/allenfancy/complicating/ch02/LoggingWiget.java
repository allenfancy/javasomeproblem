package com.allenfancy.complicating.ch02;

public class LoggingWiget extends Wiget{

	public synchronized void doSomething(){
		System.out.println(": calling doSomething.");
		super.doSomething();
	}
}
