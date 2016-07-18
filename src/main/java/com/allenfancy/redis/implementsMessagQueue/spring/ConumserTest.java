package com.allenfancy.redis.implementsMessagQueue.spring;

import java.util.Date;

import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class ConumserTest {

	public static void main(String[] args) {
		FileSystemXmlApplicationContext fs = new FileSystemXmlApplicationContext("/Users/allen/git/javasomeproblem/src/main/java/com/allenfancy/redis/implementsMessagQueue/spring/Spring-redis.xml");
        while (true) { //这里是一个死循环,目的就是让进程不退出,用于接收发布的消息
            try {
                System.out.println("current time: " + new Date());

                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}
}
