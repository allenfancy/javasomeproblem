package com.allenfancy.memcached.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.allenfancy.memcached.demo.MemcachedUtils;
import com.allenfancy.memcached.model.User;

public class MemcahedTest {

	private static ClassPathXmlApplicationContext ac = null;
	
	@Before
	public void beforeTest(){
		ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		ac.start();
	}
	
	@Test
	public void testMemcache(){
		MemcachedUtils.set("key", "value",new Date(1000*60));
		Object obj = MemcachedUtils.get("key");
		System.out.println("**********************************");
		System.out.println(obj.toString());
	}
	
	@Test
	public void testMemcache1(){
		User user = new User();
		user.setName("吴涛");
		user.setAge(35);
		user.setFlag(Boolean.FALSE);
		user.setPrice(23.3);
		user.setPassword("****");
		user.setId("123432");
		user.setSinglePrice(15.5f);
		MemcachedUtils.set("user",user,new Date(1000*600));
		User u = (User)MemcachedUtils.get("user");
		System.out.println("**********************************");
		System.out.println(u.getName());
	}
	
	@Test
	public void testMemcache2(){
		User user = new User();
		user.setName("吴涛");
		user.setAge(35);
		user.setFlag(Boolean.FALSE);
		user.setPrice(23.3);
		user.setPassword("****");
		user.setId("123432");
		user.setSinglePrice(15.5f);
		List<User> lists = new ArrayList<User>();
		MemcachedUtils.set("listAllen",lists,new Date(1000*600));
		List<User> list = (List<User>)MemcachedUtils.get("listAllen");
		System.out.println("**********************************");
		System.out.println(list.size());
	}
	
	@After
	public void afterTest(){
		ac.destroy();
	}
}
