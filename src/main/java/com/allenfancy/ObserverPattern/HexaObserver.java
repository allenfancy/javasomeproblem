package com.allenfancy.ObserverPattern;

public class HexaObserver extends Observer{

	public HexaObserver(Subject subject) {
		// TODO Auto-generated constructor stub
		this.subject = subject;
		this.subject.attach(this);
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		System.out.println("Hex String : " + Integer.toHexString(subject.getState()).toUpperCase());
	}

}
