package com.allenfancy.performancetuning.ch03;

import java.util.Vector;

public class TMain {

	public static void main(String[] args){
		Person  stu1 = new Person();
		Vector cs = new Vector();
		cs.add("java");
		stu1.setId(1);
		stu1.setName("xiaoming");
		stu1.setCourses(cs);
		
		Person  stu2 = stu1.newInstance();
		
		stu1.setId(2);
		stu1.setName("xiaodong");
		stu1.getCourses().add("C#");
		
		
		System.out.println(stu1.getName());
		System.out.println(stu2.getName());
		
		System.out.println(stu1.getCourses() == stu2.getCourses());
		
	}
}
