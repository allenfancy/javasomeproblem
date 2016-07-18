package com.allenfancy.redis.implementsMessagQueue.spring;

import java.io.Serializable;

public interface MessageDelegateListener {
	public void handleMessage(Serializable message);
}
