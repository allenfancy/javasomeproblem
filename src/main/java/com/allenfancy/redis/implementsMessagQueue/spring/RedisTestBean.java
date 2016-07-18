package com.allenfancy.redis.implementsMessagQueue.spring;

import java.io.Serializable;

public class RedisTestBean implements Serializable{

	private String name;
	private short seliry;
	private short old;
	private String[] manbers;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getSeliry() {
		return seliry;
	}

	public void setSeliry(short seliry) {
		this.seliry = seliry;
	}

	public short getOld() {
		return old;
	}

	public void setOld(short old) {
		this.old = old;
	}

	public String[] getManbers() {
		return manbers;
	}

	public void setManbers(String[] manbers) {
		this.manbers = manbers;
	}

}
