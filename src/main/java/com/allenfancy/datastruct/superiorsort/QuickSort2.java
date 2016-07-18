package com.allenfancy.datastruct.superiorsort;

public class QuickSort2 {

	public static void main(String[] args) {
		int maxSize = 16;
		ArrayIns arr = new ArrayIns(maxSize);
		
		for(int i = 0; i < maxSize;i++){
			int n = (int)(Math.random()*99);
			arr.insert(n);
		}
		arr.display();
		arr.quickSort();
		arr.display();
	}
}

class ArrayIns{
	private int [] theArray;
	private int eElems;
	
	public ArrayIns(int max){
		theArray = new int[max];
		eElems = 0;
	}
	
	public void insert(int value){
		theArray[eElems] = value;
		eElems++;
	}
	public void display(){
		System.out.print("A = ");
		for(int i = 0; i< eElems;i++){
			System.out.print(theArray[i] + " ");
		}
		System.out.println("");
	}
	public void quickSort(){
		recQuickSort(0, eElems-1);
	}
	public void recQuickSort(int left,int right){
		int size = right - left + 1;
		if(size <= 3){
			manualSort(left,right);
		}else{
			int median = mediaOf3(left,right);
			int parition = paritionIt(left,right,median);
			recQuickSort(left, parition-1);
			recQuickSort(parition+1, right);
		}
	}
	
	public int mediaOf3(int left,int right){
		int center = (left+right)/2;
		if(theArray[left] > theArray[center]){
			swap(left,center);
		}
		if(theArray[left] > theArray[right]){
			swap(left, right);
		}
		if(theArray[center] > theArray[right]){
			swap(center,right);
		}
		swap(center,right-1);
		return theArray[right-1];
	}
	
	public void swap(int index1,int index2){
		int temp = theArray[index1];
		theArray[index1] =  theArray[index2];
		theArray[index2] = temp;
	}
	
	public int paritionIt(int left,int right,int privot){
		int lefPtr = left;
		int rightPrt = right - 1;
		while(true){
			while(theArray[++lefPtr] < privot);
			while(theArray[--rightPrt] > privot);
			if(lefPtr >= rightPrt)
				break;
			else
				swap(lefPtr, rightPrt);
		}
		swap(lefPtr, right - 1);
		return lefPtr;
	}
	
	
	public void manualSort(int left,int right){
		int size = right - left +1;
		if(size <=1)
			return ;
		if(size == 2){
			if(theArray[left] > theArray[right]){
				swap(left, right);
			}
			return;
		}else{
			if(theArray[left] > theArray[right - 1])
				swap(left, right-1);
			if(theArray[left] > theArray[right])
				swap(left, right);
			if(theArray[right-1] > theArray[right])
				swap(right-1, right);
		}
			
	}
	
	
	
	
	
	
}
