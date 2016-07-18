package com.allenfancy.datastruct.superiorsort;

//拿最右边的作为枢纽节点
public class QuickSort3 {
	public static void main(String[] args) {
		int maxSize = 16;
		ArrayIns2 arr = new ArrayIns2(maxSize);
		for (int i = 0; i < maxSize; i++) {
			int n = (int) (Math.random() * 99);
			arr.insert(n);
		}
		arr.display();
		arr.quickSort();
		arr.display();
	}
}

class ArrayIns2 {
	private int[] theArray;
	private int eElems;

	public ArrayIns2(int max) {
		theArray = new int[max];
		eElems = 0;
	}

	public void insert(int value) {
		theArray[eElems] = value;
		eElems++;
	}

	public void display() {
		System.out.print("A = ");
		for (int i = 0; i < eElems; i++) {
			System.out.print(theArray[i] + " ");
		}
		System.out.println("");
	}

	public void quickSort() {
		recQuickSort(0, eElems - 1);
	}

	public void recQuickSort(int left, int right) {
		int size = right - left;
		if (size <= 0) {
			return;
		} else {
			int pivot = theArray[right];
			int parition = paritionIt(left, right, pivot);
			recQuickSort(left, parition - 1);
			recQuickSort(parition + 1, right);
		}
	}

	public void swap(int index1, int index2) {
		int temp = theArray[index1];
		theArray[index1] = theArray[index2];
		theArray[index2] = temp;
	}

	public int paritionIt(int left, int right, int privot) {
		int lefPtr = left - 1;
		int rightPrt = right;
		while (true) {
			while (theArray[++lefPtr] < privot)
				;
			while (rightPrt > 0 && theArray[--rightPrt] > privot)
				;
			if (lefPtr >= rightPrt)
				break;
			else
				swap(lefPtr, rightPrt);
		}
		swap(lefPtr, right);
		return lefPtr;
	}
}
