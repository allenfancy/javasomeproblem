package com.allenfancy.netty.codec.serializable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TestUserInfo {

	public static void main(String[] args) throws IOException{
		UserInfo info = new UserInfo();
		info.buildUserID(100).buildUserName("Welcome to Netty");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(bos);
		os.writeObject(info);
		os.flush();
		os.close();
		byte[] b = bos.toByteArray();
		System.out.println("The JDK serializable length is :" + b.length);
		bos.close();
		System.out.println("---------------");
		System.out.println("The byte array serialzable length is  : " + info.codeC().length);
	}
}
