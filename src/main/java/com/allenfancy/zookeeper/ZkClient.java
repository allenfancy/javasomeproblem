package com.allenfancy.zookeeper;

import java.util.List;

import org.apache.zookeeper.CreateMode;

public class ZkClient {

	public static void main(String[] args){
		String Path = "/allen";
		org.I0Itec.zkclient.ZkClient zkClient = new org.I0Itec.zkclient.ZkClient("localhost");
		zkClient.createPersistent(Path);
		zkClient.create(Path + "/child","child znode", CreateMode.EPHEMERAL);
		
		List<String> children = zkClient.getChildren(Path);
		for(String str : children){
			System.out.println(str);
		}
		int childCount = zkClient.countChildren(Path);
		System.out.println(childCount);
		zkClient.exists(Path);
		zkClient.writeData(Path+"/child", "hello everyone");
		
		Object obj = zkClient.readData(Path+"/child");
		System.out.println(obj);
		zkClient.delete(Path+"/child");
	}
}
