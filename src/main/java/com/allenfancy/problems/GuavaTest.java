package com.allenfancy.problems;

import java.util.Set;

import com.allenfancy.entity.Person;
import com.google.common.collect.Sets;

public class GuavaTest {

	public static void main(String[] args){
		
	}
	
	public static Set<Person> getBestFriendsClique(Person person){
		Set<Person> result = Sets.newHashSet(person);
		while((person.bestFriend != null) && (result.add(person.bestFriend))){
			person = person.bestFriend;
		}
		return result;
	}
}
