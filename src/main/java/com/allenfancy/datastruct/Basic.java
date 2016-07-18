package com.allenfancy.datastruct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basic {

	public static void main(String[] args) {
		//List 扩容
		List<String> lists = new ArrayList<String>(4); // 如果初始化为4，那么第一次扩容：就编程6，下一次就编程9
		for(int i = 0 ; i < 7;i++){
			
			lists.add(String.valueOf(i));
		}
		System.out.println(lists.size());
		//Map通过负载因子
		Map<Integer,Integer> map = new HashMap<Integer,Integer>(4);
		for(int i = 0 ;i < 18 ; i++){
			map.put(i, i);
		}
		System.out.println(map.size());
	}
	
	
}
