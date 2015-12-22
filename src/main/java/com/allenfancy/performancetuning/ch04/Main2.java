package com.allenfancy.performancetuning.ch04;

import java.util.Map;
import java.util.Set;

import org.apache.poi.util.SystemOutLogger;

public class Main2 {

	public static void main(String[] args){
		Master m = new Master(new PlusWork(),5);
		for(int i = 0; i < 10;i ++){
			m.submit(i);			//提交100子任务
		}
		m.exeute();				//开始计算
		int re = 0;			   //最终计算的结果
		Map<String,Object> resultMap = m.getResultMap();
		while(resultMap.size() > 0 || !m.isComplete()){
			//不需要等待所有Worker都执行完，即可开始计算最终的结果
			Set<String> keys = resultMap.keySet();
			String key = null;
			for(String k :keys){
				key = k;
				break;
			}
			Integer i = null;
			if(key != null)
				i = (Integer) resultMap.get(key);
			if(i != null)
				re+=i;		//最终结果
			if(key !=null)
				resultMap.remove(key); //移除已经被计算过的项
		}
		
		System.out.println(re);
	}
}
