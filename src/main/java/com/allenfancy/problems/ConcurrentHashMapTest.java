package com.allenfancy.problems;

import java.util.Map;


public class ConcurrentHashMapTest {

	public static void main(String[] args) {
		/*
		 * System.out.println(1>>30); System.out.println(Integer.MAX_VALUE);
		 * System.out.println(Integer.MAX_VALUE - 8);
		 */
		System.out.println(Runtime.getRuntime().freeMemory());
		System.out.println(Runtime.getRuntime().totalMemory());
		System.out.println(Runtime.getRuntime().maxMemory());
		System.out.println(Runtime.getRuntime().availableProcessors());
	}

	static class Node<K, V> implements Map.Entry<K, V> {
		final int hash;
		final K key;
		volatile V val;
		volatile Node<K, V> next;

		Node(int hash, K key, V val, Node<K, V> next) {
			this.hash = hash;
			this.key = key;
			this.val = val;
			this.next = next;
		}

		public K getKey() {
			// TODO Auto-generated method stub
			return key;
		}

		public V getValue() {
			// TODO Auto-generated method stub
			return val;
		}

		public V setValue(V value) {
			// TODO Auto-generated method stub
			return null;
		}

	}
}
