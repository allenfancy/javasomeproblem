package com.allenfancy.apache.common.pool;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.StackObjectPool;

public class TestPool {

	public static void main(String[] args) {
		BigObject bo = null;
		PoolableObjectFactory<BigObject> factory = new TestFactory();
		StackObjectPool<BigObject> pool = new StackObjectPool<BigObject>(factory);

		try {
			for (long i = 0; i < 10; i++) {
				System.out.println("== " + i + " ==");
				bo = (BigObject) pool.borrowObject();
				System.out.println(bo);
				System.out.println(pool.getNumActive());
				if ((i & 1) == 0) {
					pool.returnObject(bo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bo != null) {// 避免将一个对象归还两次
					pool.returnObject(bo);
				}
				pool.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
