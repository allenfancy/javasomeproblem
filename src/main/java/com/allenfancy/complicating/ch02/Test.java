package com.allenfancy.complicating.ch02;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
/**
 * @author allen
 * 对于可能被多个线程同时访问的可变状态变量，在访问它时需需要持有同一个锁，在这种情况下，我们称为状态变量是由这个锁保护的。
 * 每个共享的和可变的变量都应该只由一个锁来保护，从而使维护人员知道是哪一个锁。
 */
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AtomicReference<BigInteger> aiR = new AtomicReference<BigInteger>();
		AtomicInteger ai = new AtomicInteger();
		for(int i = 0;i<10;i++){
			ai.incrementAndGet();
		}
		
		System.out.println(ai.get());
	}

}
