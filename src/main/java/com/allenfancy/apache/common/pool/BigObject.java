package com.allenfancy.apache.common.pool;

public class BigObject {

	private boolean active;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public BigObject(){
		active = true;
		System.out.println("i am a big object!!!");
	}
	
	public String toString(){
		return "big object " + this.hashCode();
		
	}
}
