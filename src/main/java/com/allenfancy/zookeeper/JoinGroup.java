package com.allenfancy.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;

public class JoinGroup extends ConnectionWatcher{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void join(String groupName,String memberName) throws KeeperException, InterruptedException{
		String path = "/" + groupName + "/"+ memberName;
		String createPath = zk.create(path, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println("created  : " + createPath);
	}
	
	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		JoinGroup joinGroup = new JoinGroup();
		joinGroup.connect("localhost:2181");
		joinGroup.join("fancyTest", "allen");
		joinGroup.close();
	}
}
