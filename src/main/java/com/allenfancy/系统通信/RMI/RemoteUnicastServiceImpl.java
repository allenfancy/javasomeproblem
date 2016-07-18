package com.allenfancy.系统通信.RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.google.common.collect.Lists;

public class RemoteUnicastServiceImpl extends UnicastRemoteObject implements RemoteServiceInterface{

	protected RemoteUnicastServiceImpl() throws RemoteException {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<UserInfo> queryAllUserinfo() throws RemoteException {
		List<UserInfo> users = Lists.newArrayList();
		UserInfo user1 = new UserInfo();
		user1.setUserAge(21);
		user1.setUserDesc("userDesc1");
		user1.setUserName("userName1");
		user1.setUserSex(true);
		users.add(user1);
		
		UserInfo user2 = new UserInfo();
		user2.setUserAge(25);
		user2.setUserDesc("userDesc2");
		user2.setUserName("userName2");
		user2.setUserSex(true);
		users.add(user2);
		return users;
	}

}
