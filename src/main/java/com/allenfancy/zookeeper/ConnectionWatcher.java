package com.allenfancy.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

public class ConnectionWatcher implements Watcher {
	
	public ZooKeeper zk;
	private CountDownLatch connectedSignal = new CountDownLatch(1);
	public void connect(String hosts) throws IOException,InterruptedException{
		zk = new ZooKeeper(hosts,5000,this);
		connectedSignal.await();
	}
	
	@Override
	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub
		if(event.getState() == KeeperState.SyncConnected){
			connectedSignal.countDown();
			System.out.println("processing ... ");
		}
	}
	
	public void close() throws InterruptedException{
		zk.close();
	}
}
