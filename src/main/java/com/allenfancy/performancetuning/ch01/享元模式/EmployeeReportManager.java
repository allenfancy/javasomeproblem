package com.allenfancy.performancetuning.ch01.享元模式;

public class EmployeeReportManager  implements IReportManager{

	protected String tenantId = null;
	public EmployeeReportManager(String tenantId){
		this.tenantId = tenantId;
	}
	
	public String createReport() {
		// TODO Auto-generated method stub
		return "This is a employee report";
	}

}
