package com.allenfancy.distributedesgin.suanfa.二叉树;

import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;

public class Node {

	public int Data;
	public Node leftChild;
	public Node rightChild;
	
	public void displayNode(){
		System.out.print('{');
		System.out.print(Data);
		System.out.print(',');
		System.out.print('}');
	}
}
