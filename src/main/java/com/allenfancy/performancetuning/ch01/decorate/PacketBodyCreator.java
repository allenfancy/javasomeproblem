package com.allenfancy.performancetuning.ch01.decorate;
/**
 * 用于返回数据包得核心数据
 * @author allen
 *
 */
public class PacketBodyCreator implements IPacketCreator{

	public String handleContent() {
		// TODO Auto-generated method stub
		return "Content of Packet";//构造核心的数据，但不包括各式
	}

}
