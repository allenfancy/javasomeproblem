package com.allenfancy.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapError {

	public static void main(String[] args){
		Map<String,Object> collection = new TreeMap<String, Object>();
		//System.out.println(collection.compute("foo", (k,v)->(v==null)?new ArrayList<Object>():((List)v).add("bar")));
	}
}
