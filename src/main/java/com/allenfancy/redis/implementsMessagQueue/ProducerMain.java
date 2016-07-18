package com.allenfancy.redis.implementsMessagQueue;

import java.util.Random;

public class ProducerMain {

	private static final String redis_key = "allen";

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					int  i = new Random().nextInt(10000);
					Message msg1 = new Message(i, "内容" + i);
					try {
						JedisUtil.lpush((redis_key).getBytes(), ObjectUtil.objectToBytes(msg1));
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("生成完成"+i);
					
				}
			}).start();
		}
		
		Thread.sleep(100000);
	}
}
