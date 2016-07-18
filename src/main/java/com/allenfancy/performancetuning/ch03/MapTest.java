package com.allenfancy.performancetuning.ch03;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapTest {

	public static void main(String[] args){
		testHashMap();
	}
	
	/**
	 * 有序的Map
	 */
	public static void testLinkedHashMap(){
		Map<String,String> maps = new LinkedHashMap<String,String>();
		maps.put("1", "aa");
		maps.put("2", "bb");
		maps.put("3", "cc");
		maps.put("4", "dd");
		
		System.out.println(maps.get("3"));
		for(java.util.Iterator<String> iterator = maps.keySet().iterator();iterator.hasNext();){
			String name = iterator.next();
			System.out.println(name + " -> " + maps.get(name));
		}
	}
	
	/**
	 * 无序的Map
	 */
	public static void testHashMap(){
		Map<String,String> maps = new HashMap<String,String>();
		maps.put("1", "aa");
		maps.put("2", "bb");
		maps.put("3", "cc");
		maps.put("4", "dd");
		maps.put("5", "ee");
		maps.put("6", "ff");
		maps.put("7", "gg");
		System.out.println(maps.get("3"));
		for(java.util.Iterator<String> iterator = maps.keySet().iterator();iterator.hasNext();){
			String name = iterator.next();
			System.out.println(name + " -> " + maps.get(name));
		}
	}
	
	/*public static void testTreeMap(){
		Map maps = new TreeMap();
		Student s1 = new Student("Billy",70);
		Student s2 = new Student("David",85);
		Student s3 = new Student("kite",92);
		Student s4 = new Student("Cissy",68);
		
		maps.put(s1, new StudentDetailInfo(s1));
		maps.put(s2, new StudentDetailInfo(s2));
		maps.put(s3, new StudentDetailInfo(s3));
		maps.put(s4, new StudentDetailInfo(s4));
	}*/
}
