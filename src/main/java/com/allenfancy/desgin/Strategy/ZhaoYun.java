package com.allenfancy.desgin.Strategy;

public class ZhaoYun {

	/**
	 * 赵云出场了，他根据诸葛亮给他的交代，依次拆开妙计
	 */
	public static void main(String[] args) {
		Context context;

		// 刚到吴国的时候拆开第一个
		System.out.println("----------刚刚到吴国的时候拆开第一个---------------");
		context = new Context(new BackDoor());
		context.operate();// 拆开执行
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");

		// 当刘备乐不思蜀时，拆开第二个
		System.out.println("----------刘备乐不思蜀，拆第二个了---------------");
		context = new Context(new GivenGreenLight());
		context.operate();// 拆开执行
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");

		// 孙权的小追兵了，咋办？拆开第三个锦囊
		System.out.println("----------孙权的小追兵了，咋办？拆开第三个锦囊---------------");
		context = new Context(new BlackEnemy());
		context.operate();// 拆开执行
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}

}