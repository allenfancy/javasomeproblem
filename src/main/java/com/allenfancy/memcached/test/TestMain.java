package com.allenfancy.memcached.test;

import java.util.Map;

import com.danga.MemCached.MemCachedClient;

public class TestMain {

	public static void main(String[] args){
		MemCachedClient memCachedClient = new MemCachedClient();
		boolean b = memCachedClient.add("a", 1);
		System.out.println(b);
		boolean b1 = memCachedClient.set("b", 2);
		System.out.println(b1);
		boolean b2 = memCachedClient.replace("c",3);
		System.out.println(b2);
		System.out.println(memCachedClient.get("key"));
		
		String[] keys = {"key1","key2"};
		Map<String,Object> values = memCachedClient.getMulti(keys);
		System.out.println(values.size());
	}
}
