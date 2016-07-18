package com.allenfancy.redis.implementsMessagQueue;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestRedisQuene {

	private static final String redis_key = "allen";

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("线程开始执行 ---  ");
					byte[] bytes = JedisUtil.rpop((redis_key).getBytes());
					Message msg = null;
					try {
						msg = (Message) ObjectUtil.bytesToObject(bytes);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (msg != null) {
						System.out.println(msg.getId() + "   " + msg.getContent());
					}
				}
			}).start();
		}
		Thread.sleep(100000);
	}
}