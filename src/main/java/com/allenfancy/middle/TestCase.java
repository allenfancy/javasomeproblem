package com.allenfancy.middle;

import java.util.concurrent.ConcurrentHashMap;

public class TestCase {

	private ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<String,Integer>();
	
	public void add(String key){
		Integer value = map.get(key);
		if(value == null){
			map.put(key, 1);
		}else{
			map.put(key, value+1);
		}
	}
}
