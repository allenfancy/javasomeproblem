package com.allenfancy.desgin.监听器模式;

public class DemoListener1 implements DemoListener{

	@Override
	public void handleEvent(DemoEvent event) {
		// TODO Auto-generated method stub
		System.out.println("Inside listener1 ... ");
		event.say();//回调
	}

}
