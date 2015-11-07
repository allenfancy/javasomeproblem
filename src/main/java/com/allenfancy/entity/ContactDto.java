package com.allenfancy.entity;

public class ContactDto {

	private String username;
	private String emailAddress;
	private String other;
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		final int prime = 31;
		int result = 1;
		result = prime *result
				+ emailAddress == null ? 0:emailAddress.hashCode();
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this == obj){
			return true;
		}
		if(obj==null){
			return false;
		}
		if(getClass()!=obj.getClass()){
			return false;
		}
		ContactDto dto = (ContactDto) obj;
		if(this.emailAddress ==null){
			if(dto.emailAddress!=null){
				return false;
			}
		}else if(!this.emailAddress.equals(dto.emailAddress)){
			return false;
		}
		
		return true;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	
	
}
