package com.allenfancy.apache.common.pool;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DIYObjectPool<E> {

	private Set<Object> activeSet = Collections.synchronizedSet(new HashSet<Object>());
	private Set<Object> idleSet = Collections.synchronizedSet(new HashSet<Object>());// 空闲的对象的集合，已被同步

	private Integer maxObjetc = 100;// 最大对象数，默认值100
	private Class<E> cls;// 对象池 的类,因为java不能 直接使用泛型创建对象 T t = new T();
	private Object lock = new Object();// 线程等待监视器

	public DIYObjectPool(Integer maxObjetc, Class<E> cls) {

		this.maxObjetc = maxObjetc;

		this.cls = cls;

	}

	public synchronized <E> E borrowObject() throws Exception {

		Object obj = null;

		if (idleSet.size() > 0) {

			Iterator<Object> iterator = idleSet.iterator();

			obj = iterator.next();

		}

		if (obj != null) {

			idleSet.remove(obj);

			activeSet.add(obj);

		} else {

			int size = activeSet.size() + idleSet.size();

			if (size >= maxObjetc) {

				synchronized (lock) {

					System.out.println("-----池中无对象，线程等待-----");

					lock.wait();

				}

				return borrowObject();

			} else {

				obj = cls.newInstance();

				activeSet.add(obj);

			}

		}

		System.out.println("池中总对象数： " + (activeSet.size() + idleSet.size()) + " ，使用中：" + activeSet.size() + " ，空闲中："
				+ idleSet.size());

		clearObject(obj);// 有状态对象恢复默认初始化

		return (E) obj;

	}

	public void returnObject(Object obj) {

		if (obj != null) {

			activeSet.remove(obj);

			idleSet.add(obj);

			synchronized (lock) {

				System.out.println("唤醒等待线程");

				lock.notify();

				// lock.notifyAll();

			}

		}

	}

	public void clearObject(Object obj) throws Exception {

		Class<?> cls = obj.getClass();

		Field[] fields = cls.getDeclaredFields();

		for (Field field : fields) {

			field.setAccessible(true);

			field.set(obj, null);

		}

	}

	public static void main(String[] args) throws Exception {

		// 初始化线程池

		int max = 1000;

		DIYObjectPool<Object> pool = new DIYObjectPool<Object>(max, Object.class);

		// 自定义运行线程

		class TestThread extends Thread {

			DIYObjectPool objectPool;

			public TestThread(DIYObjectPool objectPool) {

				this.objectPool = objectPool;

			}

			@Override

			public void run() {

				try {

					Object obj = objectPool.borrowObject();

					Thread.sleep(3000);// 假设对象被一个线程使用的3秒钟

					objectPool.returnObject(obj);

				} catch (Exception e) {

					e.printStackTrace();

				}

			}

		}

		// 并发max*2个线程

		max = max * 2;

		for (int i = 0; i < max; i++) {

			new TestThread(pool).start();

		}

	}

}
