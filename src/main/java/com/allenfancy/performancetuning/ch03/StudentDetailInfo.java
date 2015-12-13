package com.allenfancy.performancetuning.ch03;

public abstract class StudentDetailInfo {

	Student s;
	
	public StudentDetailInfo(Student s){
		this.s = s;
	}
	
	public String toString(){
		return s.name
				+ " 's detail infomation";
	}
}
