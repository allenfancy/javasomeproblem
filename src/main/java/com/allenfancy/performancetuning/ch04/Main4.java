package com.allenfancy.performancetuning.ch04;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * 
 * @author allen
 *
 *生产者-消费者模式能够很好地对生产者线程和消费者线程进行解耦，优化了系统整体结构。同时，
 *由于缓冲区的作用，运行生产者线程和消费者线程存在执行上得性能差异，从一定程序缓解了性能瓶颈对系统性能的影响
 */
public class Main4 {

	public static void main(String[] args) throws InterruptedException{
		BlockingQueue<PCData> queue = new LinkedBlockingQueue<PCData>();
		Producer p1 = new Producer(queue);
		Producer p2 = new Producer(queue);
		Producer p3 = new Producer(queue);
		
		Consumer c1 = new Consumer(queue);
		Consumer c2 = new Consumer(queue);
		Consumer c3 = new Consumer(queue);
		
		ExecutorService service = Executors.newCachedThreadPool();
		
		service.execute(p1);
		service.execute(p2);
		service.execute(p3);
		
		service.execute(c1);
		service.execute(c2);
		service.execute(c3);
		
		Thread.sleep(1000);
		
		p1.stop();
		p2.stop();
		p3.stop();
		
		Thread.sleep(3000);
		
		service.shutdown();
	}
}
