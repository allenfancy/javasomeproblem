package com.allenfancy.zookeeper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.AsyncCallback.Children2Callback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class TestZkGroup implements Watcher {

	private static final int SESSION_TIMEOUT = 5000;

	private ZooKeeper zk;
	private CountDownLatch connectedSignal = new CountDownLatch(2);

	ChildWatcher childWatcher = new ChildWatcher();

	private void getThreadName(String funName) {
		Thread current = Thread.currentThread();
		System.out.println(funName + " is call in " + current.getName());
	}

	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub
		getThreadName("proccess");
		System.out.println(event.getType());

		if (event.getState() == KeeperState.SyncConnected) {
			connectedSignal.countDown();
		}
	}

	public Watcher wh = new Watcher() {
		public void process(WatchedEvent event) {
			getThreadName("Watcher::process");
			System.out.println("回调watcher实例：路径" + event.getPath() + "类型：" + event.getType());
		}
	};

	public void connect(String hosts) throws IOException {
		getThreadName("connect");
		zk = new ZooKeeper(hosts, SESSION_TIMEOUT, wh);

	}

	public void join(String groupName, String meberName) throws KeeperException, InterruptedException {
		String path = "/" + groupName + "/" + meberName;
		String creataPath = zk.create(path, null, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		System.out.println("join : " + creataPath);
	}

	public List<String> getChilds(String path) {
		if (zk != null) {
			zk.getChildren(path, true, new AsyncCallback.Children2Callback() {

				public void processResult(int arg0, String arg1, Object arg2, List<String> arg3, Stat arg4) {
					// TODO Auto-generated method stub
					System.out.println("****");
					for (int i = 0; i < arg3.size() - 1; i++) {
						System.out.println("mempath : " + arg3.get(i) + arg4);
					}
				}
			}, null);
		}

		return null;
	}

	public void create(String path) throws KeeperException, InterruptedException, IOException {
		// Ids.OPEN_ACL_UNSAFE 开放式ACL，允许任何客户端对znode进行读写
		// CreateMode.PERSISTENT 持久的znode，本次连接断开后还会存在，应该有持久化操作.
		// PERSISTENT_SEQUENTIAL 持久化顺序，这个由zookeeper来保证

		String createdpath = zk.create(path, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println("crateed : " + createdpath);
	}

	private void close() throws InterruptedException {
		if (zk != null) {
			zk.close();
		}
	}

	public static class ChildWatcher implements Children2Callback {
		public ChildrenCallback processResult;

		public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
			// TODO Auto-generated method stub
			System.out.println("**** path " + stat);
		}
	}

	public void delete(String groupname) throws InterruptedException, KeeperException {
		zk.delete(groupname, -1);
	}

	public Stat isexist(String groupname) throws InterruptedException, KeeperException {
		return zk.exists(groupname, true); // this
	}

	public void write(String path, String value) throws Exception {
		Stat stat = zk.exists(path, false);
		if (stat == null) {
			zk.create(path, value.getBytes("UTF-8"), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		} else {

			zk.setData(path, value.getBytes("UTF-8"), -1);
		}
	}

	public String read(String path, Watcher watch) throws Exception {
		byte[] data = zk.getData(path, watch, null);
		return new String(data, "UTF-8");
	}

	public static void main(String[] args) throws Exception {
		String hosts = "localhosts";
		String groupName = "zkTest";
		String meberName = String.valueOf(System.currentTimeMillis());
		String path = "/" + groupName;

		TestZkGroup test = new TestZkGroup();

		test.connect(hosts);

		if (null != test.isexist(path)) {
			test.delete(path);
		}

		test.isexist(path);
		test.create(path);

		test.isexist(path);
		test.write(path, "test");

		test.isexist(path);
		String result = test.read(path, test.wh);
		System.out.println(path + " value = " + result);

		int sum = 0;
		for (int j = 0; j < 10000; j++) {
			sum++;
			Thread.sleep(10);
		}
		System.out.println(sum);
		test.close();

		System.exit(2);
		// 一个本地连接的znode
		test.connect(hosts);
		test.join(groupName, meberName);

		// 遍历
		List<String> memlist = test.getChilds("/" + "zktest");
		if (memlist != null) {
			for (int i = 0; i < memlist.size() - 1; i++) {
				System.out.println("mempath = " + memlist.get(i));
			}
		}
	}
}
