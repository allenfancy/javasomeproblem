package com.allenfancy.系统通信.RMI;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RemoteRegistryUnicastMain2 {

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		Registry registry = LocateRegistry.getRegistry("192.168.1.174",9090);
		RemoteUnicastServiceImpl remoteService = new RemoteUnicastServiceImpl();
		registry.bind("queryAllUserInfo1", remoteService);
	}
}
