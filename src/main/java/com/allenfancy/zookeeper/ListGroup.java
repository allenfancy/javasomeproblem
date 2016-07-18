package com.allenfancy.zookeeper;

import java.io.IOException;
import java.util.List;

public class ListGroup extends ConnectionWatcher{

	public void list(String groupName){
		String path = "/"  + groupName;
		try{
			List<String> children = zk.getChildren(path, false);
			if(children.isEmpty()){
				System.out.println("No members in group :" +groupName);
				System.exit(1);
			}
			for(String child : children){
				System.out.println(child);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		ListGroup listGroup = new ListGroup();
		listGroup.connect("localhost:2181");
		listGroup.list("");
	} 
}
