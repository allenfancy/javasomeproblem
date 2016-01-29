package com.allenfancy.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;

public class ZKWatcher implements Watcher{

	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub
		if(event.getType() == EventType.NodeDeleted){
			System.out.println("node delete");
		}
		
		if(event.getType() == EventType.NodeChildrenChanged){
			System.out.println("node NodeChildrenChanged");
		}
		
		if(event.getType() == EventType.NodeCreated){
			System.out.println("node NodeCreated");
		}
		if(event.getType() == EventType.NodeDataChanged){
			System.out.println("node NodeDataChanged");
		}
	}

}
