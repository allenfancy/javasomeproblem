package com.allenfancy.spring.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.allenfancy.spring.model.User;
import com.allenfancy.spring.service.IUserService;

public class UserService implements IUserService{

	private static final Map<String,User> maps = new HashMap<String,User>();
	
	public void save(User user) {
		// TODO Auto-generated method stub
		Random r = new Random();
		maps.put(String.valueOf(r.nextInt(255)), user);
	}

	public User getById(String id) {
		// TODO Auto-generated method stub
		User u = maps.get(id);
		return u;
	}

	public User deleteById(String id) {
		// TODO Auto-generated method stub
		User u = maps.get(id);
		return u;
	}

}
