package com.allenfancy.webpower;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;


public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedBlockingQueue<String> lin = new LinkedBlockingQueue<String>(3);
		lin.add("2");
		lin.add("2");
		lin.add("2");
		lin.add("2");
		System.out.println(lin.size());
	}

}
