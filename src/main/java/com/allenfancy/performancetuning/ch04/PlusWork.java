package com.allenfancy.performancetuning.ch04;

public class PlusWork extends Worker {

	public Object handle(Object input){
		Integer i = (Integer) input;
		return i;
	}
}
