package com.allenfancy.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class CreateGroup implements Watcher{

	private ZooKeeper zk;
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
	
	public void create(String groupName) throws KeeperException, InterruptedException{
		String path = "/" + groupName;
		String createPath = zk.create(path, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println(createPath);
	}
	
	public void close() throws InterruptedException{
		zk.close();
	}

	public static void main(String[] args) throws InterruptedException, KeeperException, IOException {
		CreateGroup createGroup = new CreateGroup();
		createGroup.connect("localhost:2181");
		createGroup.create("fancyTest");
		createGroup.close();
	}
}
