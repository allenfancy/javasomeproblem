package com.allenfancy.problems;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {

	public static void main(String[] args){
		/**
		 * corePoolSize:核心池大小
		 * maximumPoolSize:线程池最大能创建的线程数目大小
		 * keepAliveTime:活动时间
		 * long:
		 * workQueue:阻塞队列
		 */
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
		
		for(int i = 0; i < 15; i++){
			MyTask myTask = new MyTask(i);
			Future f = executor.submit(myTask);
			try {
				System.out.println(f.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("线程池中线程池数目：" + executor.getPoolSize() + ",队列中等待执行的任务数目：" +
					executor.getQueue().size() + ",已执行完别的任务数目：" + executor.getCompletedTaskCount());
		}
		executor.shutdown();
	}
}


class MyTask implements Runnable{
	private int taskNum;
	
	public MyTask(int num){
		this.taskNum = num;
	}
	
	public void run(){
		System.out.println("正在执行task "+ taskNum);
		try{
			Thread.currentThread().sleep(1000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("task " + taskNum + "执行完毕");
	}
}