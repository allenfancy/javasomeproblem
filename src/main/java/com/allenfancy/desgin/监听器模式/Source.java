package com.allenfancy.desgin.监听器模式;

import java.util.Enumeration;
import java.util.Vector;

public class Source {

	private Vector repository = new Vector();
	
	public Source(){}
	
	public void addListener(DemoListener dl){
		repository.addElement(dl);
	}
	
	public void notifyDemoEvent(){
		Enumeration enums = repository.elements();
		while (enums.hasMoreElements()) {
			Object object = (Object) enums.nextElement();
			((DemoListener) object).handleEvent(new DemoEvent(this));
		}
	}
}
