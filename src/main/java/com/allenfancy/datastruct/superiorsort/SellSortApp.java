package com.allenfancy.datastruct.superiorsort;

public class SellSortApp {

	public static void main(String[] args) {
		int maxSize = 15;
		ArraySh arr;
		arr = new ArraySh(maxSize);
		
		for(int i = 0; i < maxSize;i++){
			long n = (int) (Math.random()*99);
			arr.insert(n);
		}
		
		arr.display();
		arr.shellSort();
		arr.display();
	}
}

class ArraySh{
	private Long[] theArray;
	private int nElems;
	
	public ArraySh(int max){
		theArray = new Long[max];
		nElems = 0;
	}
	
	public void insert(long value){
		theArray[nElems] = value;
		nElems++;
	}
	
	public void display(){
		System.out.print("A");
		for(int i = 0; i < nElems;i++){
			System.out.print(theArray[i] + " ");
		}
		System.out.println("");
	}
	
	public void shellSort(){
		int inner,outer;
		long temp;
		int h = 1;
		while(h <= nElems/3){
			h = 3*h + 1;
		}
		while(h > 0){
			for(outer = h;outer < nElems;outer++){
				temp = theArray[outer];
				inner = outer;
				while(inner > h-1 && theArray[inner - h] >= temp){
					theArray[inner] = theArray[inner - h];
					inner -= h;
				}
				theArray[inner] = temp;
			}
			h = (h-1)/3;
		}
	}
	
	
	
	
}
