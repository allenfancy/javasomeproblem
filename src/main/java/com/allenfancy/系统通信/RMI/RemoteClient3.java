package com.allenfancy.系统通信.RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class RemoteClient3 {

	 private static Object WAITOBJECT = new Object();
	 public static void main(String[] args) throws RemoteException, InterruptedException, MalformedURLException, NotBoundException {
		RemoteServiceInterface remoteServiceInterface  = (RemoteServiceInterface) Naming.lookup("rmi://192.168.1.174:9090/queryAllUserInfo1");
		while(true){
			List<UserInfo> users = remoteServiceInterface.queryAllUserinfo();
			System.out.println("users.size() = " + users.size());
			synchronized (RemoteClient3.WAITOBJECT) {
				RemoteClient3.WAITOBJECT.wait(1000);
				
			}
		}
	}
}
