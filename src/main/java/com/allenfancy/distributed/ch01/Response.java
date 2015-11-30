package com.allenfancy.distributed.ch01;

public class Response {

	
	/**
	 * 编码
	 */
	private byte encode;
	/**
	 * 响应长度
	 */
	private int responseLength;
	
	/**
	 * 响应
	 */
	private String repsone;

	public byte getEncode() {
		return encode;
	}

	public void setEncode(byte encode) {
		this.encode = encode;
	}

	public int getResponseLength() {
		return responseLength;
	}

	public void setResponseLength(int responseLength) {
		this.responseLength = responseLength;
	}

	public String getRepsone() {
		return repsone;
	}

	public void setRepsone(String repsone) {
		this.repsone = repsone;
	}
	
	
}
