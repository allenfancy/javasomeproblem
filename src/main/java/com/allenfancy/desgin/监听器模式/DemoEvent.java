package com.allenfancy.desgin.监听器模式;

import java.util.EventObject;

public class DemoEvent  extends EventObject{

	public DemoEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	public void say(){
		System.out.println("This is say method ... ");
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
