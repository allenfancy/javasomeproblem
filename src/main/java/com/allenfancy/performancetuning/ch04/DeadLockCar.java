package com.allenfancy.performancetuning.ch04;

import java.util.concurrent.locks.ReentrantLock;

public class DeadLockCar extends Thread{

	protected Object myDirect;
	
	static ReentrantLock south = new ReentrantLock();
	static ReentrantLock north = new ReentrantLock();
	static ReentrantLock west = new ReentrantLock();
	static ReentrantLock east = new ReentrantLock();
	
	public DeadLockCar(Object obj){
		this.myDirect = obj;
		if(myDirect == south){
			this.setName("south");
		}
		if(myDirect == north){
			this.setName("north");
		}
		if(myDirect == west){
			this.setName("west");
		}
		if(myDirect == east){
			this.setName("east");
		}
	}
	
	public void run(){
		if(myDirect == south){
			try{
				west.lockInterruptibly();
				try{
					Thread.sleep(500);
				}catch(Exception e){
					e.printStackTrace();
				}
				south.lockInterruptibly();
				System.out.println("car to south has passed");
			}catch(Exception e){
				System.out.println("car to south is killed");
			}finally{
				if(west.isHeldByCurrentThread()){
					west.unlock();
				}
				if(south.isHeldByCurrentThread()){
					south.unlock();
				}
			}
		}
		
		if(myDirect == north){
			try{
				try{
					east.lockInterruptibly();
					try{
						Thread.sleep(500);
					}catch(Exception e){
						e.printStackTrace();
					}
					north.lockInterruptibly();//等待向北的路
					System.out.println("car to north has passed");
				}catch(Exception e){
					System.out.println("Car to north is killed");
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(north.isHeldByCurrentThread()){
					north.unlock();
				}
				if(east.isHeldByCurrentThread()){
					east.unlock();
				}
			}
		}
		if(myDirect == west){
			try{
				try{
					north.lockInterruptibly();
					try{
						Thread.sleep(500);
					}catch(Exception e){
						e.printStackTrace();
					}
					west.lockInterruptibly();//等待向北的路
					System.out.println("car to north has passed");
				}catch(Exception e){
					System.out.println("Car to north is killed");
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(north.isHeldByCurrentThread()){
					north.unlock();
				}
				if(west.isHeldByCurrentThread()){
					west.unlock();
				}
			}
		}
		
		if(myDirect == east){
			try{
				try{
					south.lockInterruptibly();
					try{
						Thread.sleep(500);
					}catch(Exception e){
						e.printStackTrace();
					}
					east.lockInterruptibly();//等待向北的路
					System.out.println("car to north has passed");
				}catch(Exception e){
					System.out.println("Car to north is killed");
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(south.isHeldByCurrentThread()){
					south.unlock();
				}
				if(east.isHeldByCurrentThread()){
					east.unlock();
				}
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException{
		DeadLockCar car2south = new DeadLockCar(south);
		DeadLockCar car2north = new DeadLockCar(north);
		DeadLockCar car2west = new DeadLockCar(west);
		DeadLockCar car2east = new DeadLockCar(east);
		
		car2south.start();
		car2north.start();
		car2west.start();
		car2east.start();
		
		Thread.sleep(1000); //此时4车死锁
		//car2north.interrupt();//强行剥夺任意小车的资源，解除死锁
	}
}
