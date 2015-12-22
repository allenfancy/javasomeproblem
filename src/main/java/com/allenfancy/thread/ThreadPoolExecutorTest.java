package com.allenfancy.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author allen
 * 分别：
 * 	newCachedThreadPool 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若可回收，则新建立线程
 *  newFixedThreadPool	创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 *  newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行
 *  newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO,LIFO,优先级)执行、
 *  
 */
public class ThreadPoolExecutorTest {

	public static void main(String[] args){
		//testCachedThreadPool();
		//testFixedThreadPool();
		//testScheduledThreadPool();
		//testScheduledThreadPoolDelay();
		//testSingleThreadExecutor();
		test();
	}
	/**
	 * newCachedThreadPool:
	 * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空线程，若无可回收，则新建线程。
	 */
	public static void testCachedThreadPool(){
		ExecutorService cahedThreadPool = Executors.newCachedThreadPool();
		for(int i = 0; i< 10; i++){
			final int index = i;
			try{
				Thread.sleep(index * 1000);
			}catch(Exception e){
				
			}
			cahedThreadPool.execute(new Runnable(){
				public void run(){
					System.out.println(index);
				}
			});
		}
	}
	/**
	 * 创建一个定长线程池，可控制线程最大并发数。超出的线程会在队列中等待。
	 */
	public static void testFixedThreadPool(){
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
		for(int i = 0 ;i < 10; i++){
			final int index = i;
			fixedThreadPool.execute(new Runnable(){
				public void run(){
					try{
						System.out.println(index);
						Thread.sleep(2000);
						System.out.println("---------");
					}catch(Exception e){
						
					}
				}
			});
		}
	}
	/**
	 * 延迟3秒
	 */
	public static void testScheduledThreadPool(){
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		scheduledThreadPool.schedule(new Runnable(){
			public void run(){
				System.out.println("delay 3 seconds ");
			}
		}, 3, TimeUnit.SECONDS);
	}
	/**
	 * 表示延迟1秒后，每3秒执行一次
	 */
	public static void testScheduledThreadPoolDelay(){
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		scheduledThreadPool.scheduleAtFixedRate(new Runnable(){
			public void run(){
				System.out.println("delay 1 seconds,and excute every 3 seconds");
			}
		}, 1, 3, TimeUnit.SECONDS);
	}
	/***
	 * 创建单线程化的线程池，它只会用唯一的工作线程来执行任务，确保所有任务按照指定的(FIFO,LIFO，优先级)执行。
	 */
	public static void testSingleThreadExecutor(){
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		for(int i = 0 ; i < 10; i++){
			final int index = i;
			singleThreadExecutor.execute(new Runnable(){
				public void run(){
					try{
						System.out.println(index);
						Thread.sleep(2000);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			});
		}
	}
	
	public static void test(){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 200, 5000, TimeUnit.DAYS, new ArrayBlockingQueue<Runnable>(5));
		for(int i = 0; i < 200; i++){
			MyTask myTask = new MyTask(i);
			executor.execute(myTask);
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
