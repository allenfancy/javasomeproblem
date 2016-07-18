package com.allenfancy.io.serializer;

import com.allenfancy.json.User;

public class TestMain {

	public static void main(String[] args) throws Exception, IllegalAccessException{
		User user = new User();
		user.setAddress("上海");
		user.setName("吴涛");
		user.setId(123);
		user.setOther("其他");
		DefaultSerializer ds = DefaultSerializer.class.newInstance();
		byte[] bytes  = ds.serialize(user);
		DefaultDeserializer dds = DefaultDeserializer.class.newInstance();
		
		User u = (User) dds.deserializer(bytes);
		System.out.println(u.getAddress());
	}
}
