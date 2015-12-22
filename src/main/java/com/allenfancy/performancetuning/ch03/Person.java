package com.allenfancy.performancetuning.ch03;

import java.util.Vector;

public class Person implements Cloneable{
	
	private int id;
	private String name;
	private Vector courses;
	
	public Person(){
		try{
			Thread.sleep(1000);
			System.out.println("Student Construnctor called");
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector getCourses() {
		return courses;
	}

	public void setCourses(Vector courses) {
		this.courses = courses;
	}

	public Person newInstance(){
		try{
			return (Person) this.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
			return null;
		}
	}
	
}
