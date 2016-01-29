package com.allenfancy.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class SpecificClassExclusionStrategy implements ExclusionStrategy{
	
	private final Class<?> excludedThisClass;
	
	public SpecificClassExclusionStrategy(Class<?> excludedThisClass){
		this.excludedThisClass = excludedThisClass;
	}
	
	/**
	 * 排除策略，排除类
	 * 如果返回true，则忽略
	 * 如果返回false，则不忽略
	 */
	public boolean shouldSkipClass(Class<?> excludedThisClass) {
		// TODO Auto-generated method stub
		return excludedThisClass.equals(excludedThisClass);
	}
	
	/**
	 * 排除字段，排除字段
	 * 如果返回true,则忽略
	 * 如果返回false,则不忽略
	 */
	public boolean shouldSkipField(FieldAttributes f) {
		// TODO Auto-generated method stub
		return excludedThisClass.equals(f.getDeclaredClass());
	}

}
