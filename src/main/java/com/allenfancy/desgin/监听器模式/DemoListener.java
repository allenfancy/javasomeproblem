package com.allenfancy.desgin.监听器模式;

import java.util.EventListener;

public interface DemoListener extends EventListener{
	
	public void handleEvent(DemoEvent event);

}
