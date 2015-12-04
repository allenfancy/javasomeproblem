package com.allenfancy.spring.service;

import com.allenfancy.spring.model.User;

public interface IUserService {

	public void save(User user);
	
	public User getById(String id);
	
	public User deleteById(String id);
	
}
