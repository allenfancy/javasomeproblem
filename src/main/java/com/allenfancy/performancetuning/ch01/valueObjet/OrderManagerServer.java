package com.allenfancy.performancetuning.ch01.valueObjet;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class OrderManagerServer {

	public static void main(String[] args){
		try{
			//注册RMI端口
			LocateRegistry.createRegistry(1099);
			//RMI远程对象
			IOrderManager userManager = new OrderManager();
			//绑定RMI对象
			Naming.rebind("OrderManager", userManager);
			System.out.println("OrderManager is ready");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
