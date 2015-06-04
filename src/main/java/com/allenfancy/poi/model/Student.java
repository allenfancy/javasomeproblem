package com.allenfancy.poi.model;

import java.util.Date;

public class Student {

	private String name;
	private int age;
	private Date brithday;
	private String grade;
	private String clazz;
	
	public Student(){}
	
	
	
	public Student(String name, int age, Date brithday, String grade,
			String clazz) {
		this.name = name;
		this.age = age;
		this.brithday = brithday;
		this.grade = grade;
		this.clazz = clazz;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBrithday() {
		return brithday;
	}
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	
	
	
}
