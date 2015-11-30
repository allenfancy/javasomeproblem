package com.allenfancy.ObserverPattern;

public class OctalObserver extends Observer{

	public OctalObserver(Subject subject){
		this.subject = subject;
		this.subject.attach(this);
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		System.out.println("Octal String : " + Integer.toBinaryString(subject.getState()));
	}

}
