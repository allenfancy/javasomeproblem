package com.allenfancy.network.ch04;

import java.net.InetAddress;
import java.util.concurrent.Callable;

public class LookUpTask implements Callable<String>{

	private String line;
	
	public LookUpTask(String line) {
		// TODO Auto-generated constructor stub
		this.line = line;
	}
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		int index = line.indexOf(" ");
		String address = line.substring(0,index);
		String theRest = line.substring(index);
		String hostname = InetAddress.getByName(address).getHostName();
		return hostname + " " + theRest;
	}

}

