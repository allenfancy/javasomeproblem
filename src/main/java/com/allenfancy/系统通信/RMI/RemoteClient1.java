package com.allenfancy.系统通信.RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class RemoteClient1 {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		RemoteServiceInterface remoteServiceInterface = (RemoteServiceInterface) Naming.lookup("rmi://192.168.1.174:9090/queryAllUserInfo");
		List<UserInfo> users = remoteServiceInterface.queryAllUserinfo();
		System.out.println("users.size() = " + users.size());
	}
}
