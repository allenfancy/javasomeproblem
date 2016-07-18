package com.allenfancy.csv.google.utils;

import java.io.Closeable;
import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		SpringUtils.applicationContext = applicationContext;
	}

	public static <T> T getBean(String name) {
		return (T) applicationContext.getBean(name);

	}

	public static <T> T getBean1(Class<T> requiredType) {
		return applicationContext.getBean(requiredType);
	}

	public static void close() throws IOException {
		if (Closeable.class.isInstance(applicationContext)) {
			Closeable.class.cast(applicationContext).close();
		}
	}

	public static void init() {
		new ClassPathXmlApplicationContext(new String[] { "classpath:spring/main.xml", "classpath:spring/mongo.xml" });
	}

}
