package com.allenfancy.network.ch03;

import java.io.File;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class ReturnDigest extends Thread {

	private String filename;
	private byte[] digest;

	public ReturnDigest(String filename) {
		this.filename = filename;
	}

	public void run() {
		try {
			FileInputStream in = new FileInputStream(new File(filename));
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			DigestInputStream din = new DigestInputStream(in, sha);
			int i ;
			StringBuffer sb = new StringBuffer();
			while((i = din.read()) != -1){
				sb.append((char) i);
			}
			System.out.println(sb.toString());
			din.close();
			digest = sha.digest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public byte[] getDigest() {
		return digest;
	}
}
