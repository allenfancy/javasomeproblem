package com.allenfancy.apache.common.pool;

public class ConnDemo {

	public static void main(String[] args) throws Exception {
		ConnPoolConfig connPoolConfig = new ConnPoolConfig();
		connPoolConfig.setMinIdle(5);
		connPoolConfig.setMaxIdle(8);
		connPoolConfig.setMaxWaitMillis(100000);
		final ConnPool connPool = new ConnPool(connPoolConfig);
		
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {

				public void run() {
					// TODO Auto-generated method stub
					try {
						synchronized (this) {

							Conn conn1 = connPool.borrowObject();

							/*
							 * System.out.println(Thread.currentThread().getName
							 * () + ",创建的个数:" + connPool.getCreatedCount() +
							 * " 借出来的个数：" + connPool.getBorrowedCount() +
							 * " 正在被使用的个数：" + connPool.getNumActive() +
							 * " 空闲的个数：" + connPool.getNumIdle());
							 */
							conn1.report();
							connPool.returnObject(conn1);

							System.out.println(Thread.currentThread().getName() + ",创建的个数:" + connPool.getCreatedCount()
									+ " 借出来的个数：" + connPool.getBorrowedCount() + " 正在被使用的个数：" + connPool.getNumActive()
									+ " 空闲的个数：" + connPool.getNumIdle());

						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}
				}

			}).start();
		}
	}
}
