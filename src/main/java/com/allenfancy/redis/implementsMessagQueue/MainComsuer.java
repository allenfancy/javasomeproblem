package com.allenfancy.redis.implementsMessagQueue;

import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainComsuer {
	public static void main(String[] args) {
        new ClassPathXmlApplicationContext("./Spring-redis.xml");;
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
