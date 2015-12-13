package com.allenfancy.performancetuning.ch01.valueObjet;

import java.rmi.Naming;

public class OrderManagerClient {

	public static void main(String[] args) {
		try {
			IOrderManager userManager = (IOrderManager) Naming.lookup("OrderManager");
			long begin = System.currentTimeMillis();
			for (int i = 0; i < 1000; i++) {
				userManager.getOrder(i);
			}
			System.out.println("getOrder spend:" + (System.currentTimeMillis() - begin));
			begin = System.currentTimeMillis();
			
			for(int i = 0 ; i < 1000; i++){
				userManager.getClientName(i);
				userManager.getProdName(i);
				userManager.getNumber(i);
			}
			System.out.println("3 Method call spend ： "+(System.currentTimeMillis() - begin));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
