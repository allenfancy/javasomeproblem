package com.allenfancy.complicating.ch02;

import java.util.HashSet;
import java.util.Set;

import org.apache.http.annotation.ThreadSafe;

import com.allenfancy.entity.Person;

@ThreadSafe
public class PersonSet {

	private final Set<Person> mySet = new HashSet<Person>();
	
	public synchronized void addPerson(Person p){
		mySet.add(p);
	}
	
	public synchronized boolean containPerson(Person p){
		return mySet.contains(p);
	}
}
