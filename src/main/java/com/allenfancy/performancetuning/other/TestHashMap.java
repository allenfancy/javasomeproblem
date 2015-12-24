package com.allenfancy.performancetuning.other;

import java.util.HashMap;
import java.util.Map;

public class TestHashMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String,String> maps = new HashMap<String,String>();
		
		maps.put("a", "1");
		maps.put("b", "1");
		maps.put("c", "1");
		maps.put("d", "1");
		maps.put("e", "1");
		maps.put("s", "1");
		maps.put("u", "1");
		
		String a = maps.get("a");
		
		int h = 2;
		int c = h^20;
		System.out.println(c);
	}

}
