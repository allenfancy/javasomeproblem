package com.allenfancy.apache.common.pool.demo1;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class Pool<T> {

	protected GenericObjectPool<T> internalPool;

	public Pool() {

	}

	public Pool(final GenericObjectPoolConfig poolConfig, PooledObjectFactory<T> factory) {
		initPool(poolConfig, factory);
	}

	public boolean isClosed() {
		return this.internalPool.isClosed();
	}

	public void initPool(final GenericObjectPoolConfig poolConfig, PooledObjectFactory<T> factory) {
		if (this.internalPool != null) {
			try {
				closeInternalPool();
			} catch (Exception e) {

			}
		}
		this.internalPool = new GenericObjectPool<T>(factory, poolConfig);
	}

	public T getResource() {
		try {
			return internalPool.borrowObject();
		} catch (Exception e) {
			throw new RuntimeException("Could not get a rosource from the pool", e);
		}
	}

	public void returnResourceObject(final T resource) {
		if (null == resource) {
			return;
		}
		try {
			internalPool.returnObject(resource);
		} catch (Exception e) {
			throw new RuntimeException("Could not return resource to the pool", e);
		}
	}

	public int getNumActive() {
		if (poolInactive()) {
			return -1;
		}
		return this.internalPool.getNumActive();
	}

	public int getNumbIdle() {
		if (poolInactive()) {
			return -1;
		}
		return this.internalPool.getNumIdle();
	}

	public int getNumWaiters() {
		if (poolInactive()) {
			return -1;
		}

		return this.internalPool.getNumWaiters();
	}

	public long getMeanBorrowWaitTimeMillis() {
		if (poolInactive()) {
			return -1;
		}

		return this.internalPool.getMeanBorrowWaitTimeMillis();
	}

	public long getMaxBorrowWaitTimeMillis() {
		if (poolInactive()) {
			return -1;
		}

		return this.internalPool.getMaxBorrowWaitTimeMillis();
	}

	protected void returnResource(final T resource) {
		if (resource != null) {
			returnResourceObject(resource);
		}
	}

	public void destroy() {
		closeInternalPool();
	}

	protected void returnBrokenResource(final T resource) {
		if (resource != null) {
			returnBrokenResourceObject(resource);
		}
	}

	protected void returnBrokenResourceObject(final T resource) {
		try {
			internalPool.invalidateObject(resource);
		} catch (Exception e) {
			throw new RuntimeException("Could not return the resource to the pool", e);
		}
	}

	protected void closeInternalPool() {
		try {
			internalPool.close();
		} catch (Exception e) {
			throw new RuntimeException("Could not destroy the pool", e);
		}
	}

	private boolean poolInactive() {
		return this.internalPool == null || this.internalPool.isClosed();
	}

	public void addObjects(int count) {
		try {
			for (int i = 0; i < count; i++) {
				this.internalPool.addObject();
			}
		} catch (Exception e) {
			throw new RuntimeException("Error trying to add idle objects", e);
		}
	}
}
