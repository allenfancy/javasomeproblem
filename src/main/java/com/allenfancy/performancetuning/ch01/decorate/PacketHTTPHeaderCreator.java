package com.allenfancy.performancetuning.ch01.decorate;

public class PacketHTTPHeaderCreator extends PacketDecorator{

	public PacketHTTPHeaderCreator(IPacketCreator c) {
		super(c);
		// TODO Auto-generated constructor stub
	}
	
	
	public String handleContent(){
		StringBuffer sb = new StringBuffer();
		sb.append("Cache-Control:no-cache\n");
		sb.append("Date:Mon,31 dec 20141211\n");
		sb.append(component.handleContent());
		return sb.toString();
	}

}
