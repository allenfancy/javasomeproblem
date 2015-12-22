package com.allenfancy.complicating.ch02.origin;

import java.util.Map;

public interface ConcurrentMap<K,V> extends Map<K,V>{
	
	//仅当K没有相应的映射值时才插入
	V putIfAbsent(K key,V value);
	
	//仅当K被映射到V时才移除
	boolean remvoe(K key, V value);
	
	//仅当K被映射到oldValue时才替换为newValue
	boolean replace(K key,V oldValue,V newValue);
	
	//仅当K被映射到某个值时才替换为newValue
	V replace(K key,V newValue);

}
