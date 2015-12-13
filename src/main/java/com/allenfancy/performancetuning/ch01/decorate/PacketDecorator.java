package com.allenfancy.performancetuning.ch01.decorate;

public abstract class PacketDecorator implements IPacketCreator{

	IPacketCreator component;
	
	public PacketDecorator(IPacketCreator c){
		component = c;
	}
	public String handleContent() {
		// TODO Auto-generated method stub
		return null;
	}

}
