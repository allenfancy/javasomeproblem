package com.allenfancy.performancetuning.ch04;

import java.util.concurrent.Semaphore;


/**
 * 信号量对锁的概念进行了扩展，它可以限定对某一个具体资源的最大可访问线程数
 * @author allen
 *
 */
public class Pool {

	private static final int  MAX_AVAILABLE = 100;
	
	private final Semaphore avavilable = new Semaphore(MAX_AVAILABLE,true);
	
	//获得一个池内的对象
	public Object getItem() throws InterruptedException{
		//申请一个许可
		avavilable.acquire();
		//同时只能有100个线程进入取得
		//可用项，超过100个则需要等待
		return getNextAvailableItem();
	}
	
	public void putItem(Object x){
		if(markAsUnused(x)){ //将给定项放回池内，标记为未被使用
			avavilable.release();//新增一个可用项，释放一个许可，请求资源的线程被激活一个
		}
	}
	
	protected Object[] items = new Object[MAX_AVAILABLE]; //这咯存放对象池中的复用对象
	
	protected boolean[] used = new boolean[MAX_AVAILABLE];//用于标记池中的项是否正在被使用
	
	protected synchronized Object getNextAvailableItem(){
		for(int i = 0 ; i < MAX_AVAILABLE;++i){
			if(!used[i]){				//如果当前项未被使用，则获得它
				used[i] = true;			//将当前项标记为已经使用
				return items[i];
			}
		}
		return null;
	}
	
	protected synchronized boolean markAsUnused(Object item){
		for(int i = 0 ; i < MAX_AVAILABLE;++i){
			if(item == items[i]){			//找到给定项的索引
				if(used[i]){
					used[i] = false;		//将给定项标记为未被使用
					return true;
				}else{
					return false;
				}
			}
		}
		return false;
	}
}
