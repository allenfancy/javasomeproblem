package com.allenfancy.performancetuning.ch01.proxy;

public class DBQueryProxy implements IDBQuery{

	private DBQuery dbQuery = null;
	
	public String request() {
		// TODO Auto-generated method stub
		if(dbQuery == null){
			dbQuery = new DBQuery();
		}
		return dbQuery.request();
	}

}
