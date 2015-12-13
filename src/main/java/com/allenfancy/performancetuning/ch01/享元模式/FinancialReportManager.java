package com.allenfancy.performancetuning.ch01.享元模式;

public class FinancialReportManager implements IReportManager{

	protected String tenantId = null;
	
	public FinancialReportManager(String tenantId){
		this.tenantId = tenantId;
	}
	
	public String createReport() {
		// TODO Auto-generated method stub
		return "This is a financail  report";
	}

}
