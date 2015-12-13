package com.allenfancy.performancetuning.ch03;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args){
		
		List<String> handler = new ArrayList<String>();
		
		for(int i = 0; i < 1000; i++){
			HugeStr h = new HugeStr();
			//ImprovedHugeStr h = new ImprovedHugeStr();
			handler.add(h.getSubString(1, 5));
		}
		System.out.println(handler.size());
	}
	
	static class HugeStr{
		private String str = new String(new char[100000]);
		
		public String getSubString(int begin,int end){
			return str.substring(begin,end);
		}
	}
	
	static class ImprovedHugeStr{
		private String str = new String(new char[100000]);
		public String getSubString(int begin,int end){
			return new String(str.substring(begin,end));
		}
	}
}
