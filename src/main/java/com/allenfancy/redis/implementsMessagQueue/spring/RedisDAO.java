package com.allenfancy.redis.implementsMessagQueue.spring;

import java.io.Serializable;

public interface RedisDAO {
	public void sendMessage(String channel, Serializable message);
}
