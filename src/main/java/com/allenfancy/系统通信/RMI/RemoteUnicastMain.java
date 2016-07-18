package com.allenfancy.系统通信.RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RemoteUnicastMain {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		/**
		 * Locate registry 理解为RMI服务注册表，或者RMI服务仓库位置。
		 * 主要的作用是维护一个"可以正常提供RMI具体服务的所在位置" 每一个具体的RMI服务提供者，都会讲自己的Stub注册到Locate
		 * register 中，以表示自己 可以提供服务 有俩种方式可以管理 Locate register 一种是通过操作系统的命令启动注册表
		 * 另一种是在代码中使用LocateRegistry类
		 * LocateRegistry类中有一个createRegistry方法，可以在这台物理机上创建一个“本地RMI注册表”
		 * 
		 * 
		 */
		LocateRegistry.createRegistry(9090);

		// 以下是向LocateRegistry注册（绑定/重绑定）RMI Server实现。
		RemoteUnicastServiceImpl remoteService = new RemoteUnicastServiceImpl();
		// 通过java 名字服务技术，可以讲具体的RMI Server实现绑定一个访问路径。注册到LocateRegistry中
		Naming.rebind("rmi://192.168.1.174:9090/queryAllUserInfo", remoteService);

		/**
		 * 在 已经拥有某个可访问的远程RMI注册表的情况下 下面这句代码就是向远程注册表注册RMI Server。
		 * 当然远程RMI注册表的JVM-classpath中一定要有这个Server的Stub存在
		 * 
		 * 另运行在另外一个JVM上的RMI注册表，可能是同一台物理机也可能不是同一台物理机）
		 * Naming.rebind("rmi://192.168.61.1:1099/queryAllUserinfo",
		 * remoteService);
		 */
	}
}
