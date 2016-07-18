package com.allenfancy.redis.implementsMessagQueue;

import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = 7792729L;
	private int id;
	private String content;

	public Message(int id, String content) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}