package com.allenfancy.network.ch04;

import java.util.concurrent.Future;

public class LogEntry {

	private String original;
	private Future<String> future;

	public LogEntry(String original, Future<String> future) {
		this.original = original;
		this.future = future;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public Future<String> getFuture() {
		return future;
	}

	public void setFuture(Future<String> future) {
		this.future = future;
	}

}
