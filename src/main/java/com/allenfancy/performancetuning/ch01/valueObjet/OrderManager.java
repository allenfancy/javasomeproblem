package com.allenfancy.performancetuning.ch01.valueObjet;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class OrderManager extends UnicastRemoteObject implements IOrderManager{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected OrderManager() throws RemoteException{
		super();
	}
	
	public String getClientName(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return "allen";
	}

	public String getProdName(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return "desk";
	}

	public int getNumber(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return 20;
	}

	public Order getOrder(int id) throws RemoteException {
		// TODO Auto-generated method stub
		Order o = new Order();
		o.setClientName("allen");
		o.setNumber(20);
		o.setProductName("desk");
		return o;
	}

}
