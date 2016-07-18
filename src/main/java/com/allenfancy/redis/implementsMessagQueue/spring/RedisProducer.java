package com.allenfancy.redis.implementsMessagQueue.spring;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class RedisProducer {

	private RedisDAO redisDAO;
	FileSystemXmlApplicationContext fs = new FileSystemXmlApplicationContext("/Users/allen/git/javasomeproblem/src/main/java/com/allenfancy/redis/implementsMessagQueue/spring/Spring-redis.xml");
	static{
		
	}
	public static void main(String[] args) {
		FileSystemXmlApplicationContext fs = new FileSystemXmlApplicationContext("/Users/allen/git/javasomeproblem/src/main/java/com/allenfancy/redis/implementsMessagQueue/spring/Spring-redis.xml");
		RedisProducer rc = new RedisProducer();
		rc.producer();
	}
	

	public void producer() {
		String msg = "Hello, Redis!";
		redisDAO.sendMessage("java", msg); // 发布字符串消息

		RedisTestBean bean = new RedisTestBean();
		bean.setName("ZhenQin");
		bean.setOld((byte) 23);
		bean.setSeliry((short) 4000);
		bean.setManbers(new String[] { "234567", "3456789" });
		redisDAO.sendMessage("java", bean); // 发布一个普通的javabean消息

		Integer[] values = new Integer[] { 21341, 123123, 12323 };
		redisDAO.sendMessage("java", values); // 发布一个数组消息
	}
}
