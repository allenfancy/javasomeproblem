package com.allenfancy.系统通信.RMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class SignalRegistry {

	private static final Object WAITOBJECT  = new Object();
	
	public static void main(String[] args) throws RemoteException, InterruptedException {
		LocateRegistry.createRegistry(9090);
		synchronized (WAITOBJECT) {
			WAITOBJECT.wait();
		}
	}
}
