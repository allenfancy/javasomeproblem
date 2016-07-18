package com.allenfancy.datastruct.superiorsort;

/**
 * @Description: 快递排序的思想： 1.首先把数组的第一个数拿出来作为一个key，在前后分别设置一个i,j作为标志。
 *               2.拿这个key对这个数组从后面网前面遍历，即j--，直达找到第一个小于这个key的那个数，然后交换这俩个值，交换完成后
 *               3.拿着这个key从i往后遍历，即i++，一直循环到i=j。
 *               4.当这里结束后，会发现大于这个key的值会跑到这个key的后面，小于key的值会跑到前面，在递推处理
 *               <p>
 * 
 *               </p>
 * @author allen
 *
 *此代码存在BUG
 */
public class QuickSort {

	public void quick_sort(int[] arrays, int length) {
		if (null == arrays || length < 1) {
			System.out.println("input error!");
			return;
		}
		_quick_sort(arrays, 0, length - 1);
	}

	public void _quick_sort(int[] arrays, int start, int end) {
		if (start >= end)
			return;
		int i = start;
		int j = end;
		int value = arrays[i];
		boolean flag = true;
		while (i != j) {
			if (flag) {
				if (value > arrays[j]) {
					swap(arrays, i, j);
					flag = false;
				} else {
					j--;
				}
			} else {
				if (value < arrays[i]) {
					swap(arrays, i, j);
					flag = true;
				} else {
					i++;
				}
			}
		}
		snp(arrays);
		_quick_sort(arrays, start, j - 1);
		_quick_sort(arrays, i + 1, end);
	}

	public void snp(int[] arrays) {
		for (int i = 0; i < arrays.length; i++) {
			System.out.print(arrays[i] + " ");
		}
		System.out.println();
	}

	private void swap(int[] arrays, int i, int j) {
		int temp;
		temp = arrays[i];
		arrays[i] = arrays[j];
		arrays[j] = temp;
	}

	public static void main(String args[]) {
		QuickSort q = new QuickSort();
		int[] a = { 49, 38, 65, 49, 45, 5,12 };
		q.quick_sort(a, 6);
	}
}
