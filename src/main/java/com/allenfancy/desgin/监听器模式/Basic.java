package com.allenfancy.desgin.监听器模式;
/**
 * 事件源经过事件的封装传递给监听器，当事件源触发后，监听器接收到事件对象可以回调事件的方法
 * 
 *
 */
public class Basic {
	Source ds; 
	public Basic(){
		ds = new Source();
		DemoListener1 listener1 = new DemoListener1();
		ds.addListener(listener1);
		ds.addListener(new DemoListener() {
			
			@Override
			public void handleEvent(DemoEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Method come from 匿名类");
			}
		});
		ds.notifyDemoEvent();
	}
	public static void main(String[] args) {
		new Basic();
		//System.out.println(30>>1);
		System.out.println(Integer.highestOneBit((16 - 10)));
	}
}
