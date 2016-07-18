package com.allenfancy.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZK1 {

	public static void main(String[]args) throws IOException, KeeperException, InterruptedException{
		ZooKeeper zooKeeper = new ZooKeeper("localhost",5000,null);
		/**
		 * 创建节点:通过ZooKeeper的API新增一个znode节点，节点在创建时，需要指定节点的路径 包含的字节数九，访问权限(如果不想设置权限，则指定为Ids.OPEN_ACL_UNSAFE)
		 * 以及创建的节点类型
		 * 节点类型：
		 * CreateMode.PERSISTENT					持久节点，该节点客户端断开连接后不会删除
		 * CreateMode.EPHEMERAL						临时节点，该节点将在客户端断开连接后删除
		 * CreateMode.PERSISTENT_SEOUENTIAL			在持久节点，该节点子啊客户端断开后不会删除，并将在其名下附加一个单调递增数
		 * CreateMode.EPHENMERAL_SEQUENTIAL			临时节点，该节点在客户端断开连接后删除，并将在其名下附加一个单调递增
		 */
		//zooKeeper.create("/root", "root data".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		
		//删除节点
		//zooKeeper.delete("/root", -1);
		//设置
		//zooKeeper.setData("/root", "hello".getBytes(), -1);
		
		Stat stat = new Stat();
		
		byte[] data = zooKeeper.getData("/root", false, stat);
		System.out.println(String.valueOf(data));
		
		//判断节点是否存在
		Stat s = zooKeeper.exists("/root/child1",false);
		if(s == null) {
			System.out.println("节点不存在");
		}else{
			System.out.println("节点存在");
		}
	}
}
