package com.allenfancy.network.ch04;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		InetAddress address = InetAddress.getByName("baidu.com");
		System.out.println(address.getHostAddress());
		System.out.println(address.getAddress());
	}
}
