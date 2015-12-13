package com.allenfancy.performancetuning.ch03;

public abstract class Student implements Comparable<Student>{

	String name;
	int score;
	
	public Student(String name,int s){
		this.name = name;
		this.score = s;
	}
	
	public int compareTO(Student o){
		if(o.score < this.score){
			return 1;
		}else if(o.score > this.score){
			return -1;
		}else{
			return 0;
		}
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("name : ");
		sb.append(name);
		sb.append(" ");
		sb.append("score : ");
		sb.append(score);
		return sb.toString();
	}
}
