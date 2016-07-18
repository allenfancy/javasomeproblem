package com.allenfancy.distributedesgin.suanfa.二叉树;

import java.util.Stack;

public class Tree {

	private Node root;

	public Tree() {
		root = null;
	}

	public Node find(int key) {
		Node current = root;
		while (current.Data != key) {
			if (key < current.Data) {
				current = current.leftChild;
			} else {
				current = current.rightChild;
			}
			if (current == null)
				return null;
		}
		return current;
	}

	public void insert(int id) {
		Node newNode = new Node();
		newNode.Data = id;
		if (root == null)
			root = newNode;
		else {
			Node current = root;
			Node parent;
			while (true) {
				parent = current;
				if (id < current.Data) {
					current = current.leftChild;
					if (current == null) {
						parent.leftChild = newNode;
						return;
					}
				} else {
					current = current.rightChild;
					if (current == null) {
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}

	public boolean delete(int key) {
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;
		while (current.Data != key) {
			parent = current;
			if (key < current.Data) {
				isLeftChild = true;
				current = current.leftChild;
			} else {
				isLeftChild = false;
				current = current.rightChild;
			}
			if (current == null)
				return false;
			// 没有任何子节点
			if (current.leftChild == null && current.rightChild == null) {
				if (current == root) {
					root = null;
				} else if (isLeftChild) {
					parent.leftChild = null;
				} else {
					parent.rightChild = null;
				}
			} else if (current.rightChild == null) {
				if (current == root) {
					root = current.leftChild;
				} else if (isLeftChild) {
					parent.leftChild = current.leftChild;
				} else {
					parent.rightChild = current.leftChild;
				}
			} else if (current.leftChild == null) {
				if (current == root)
					root = current.rightChild;
				else if (isLeftChild)
					parent.leftChild = current.rightChild;
				else
					parent.rightChild = current.rightChild;
			} else {// 都存在
				Node successor = getSuccessor(current);
				if (current == root)
					root = successor;
				else if (isLeftChild)
					parent.leftChild = successor;
				else
					parent.rightChild = successor;
				successor.leftChild = current.leftChild;
			}
		}
		return true;
	}

	public Node getSuccessor(Node delNode) {
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild;
		while (current != null) {
			successorParent = successor;
			successor = current;
			current = current.leftChild;
		}
		if (successor != delNode.rightChild) {
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}

	public void traverser(int type) {
		switch (type) {
		case 1:
			System.out.print("前遍历:");
			preOrder(root);
			break;
		case 2:
			System.out.print("中遍历:");
			middleOrder(root);
			break;
		case 3:
			System.out.print("后遍历:");
			afterOrder(root);
			break;
		default:
			break;
		}
		System.out.println();
	}

	private void preOrder(Node localRoot) {
		if(localRoot != null){
			System.out.print(localRoot.Data + " ");
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}

	private void middleOrder(Node localRoot) {
		if(localRoot != null){
			middleOrder(localRoot.leftChild);
			System.out.print(localRoot.Data + " ");
			middleOrder(localRoot.rightChild);
		}
	}

	private void afterOrder(Node localRoot) {
		if(localRoot != null){
			afterOrder(localRoot.leftChild);
			afterOrder(localRoot.rightChild);
			System.out.print(localRoot.Data + " ");
		}
	}
	
	public void displayTree(){
		Stack globalStack = new Stack();
		globalStack.push(root);
		int n = 32;
		boolean isE = false;
		System.out.println("...........");
		while(isE==false){
			Stack localStack = new Stack();
			isE = true;
			for(int i = 0 ; i < n;i++)
				System.out.print( " ");
			while(globalStack.isEmpty() ==false){
				Node temp = (Node) globalStack.pop();
				if(temp != null ){
					System.out.println(temp.Data);
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);
					if(temp.leftChild != null || temp.rightChild != null)
						isE = false;
					else
						System.out.print("--");
						localStack.push(null);
						localStack.push(null);
					for(int i = 0 ;i < n *2-2 ;i ++){
						System.out.print(' ');
					}
					System.out.println();
					n /=2;
					while (localStack.isEmpty() == false) {
						globalStack.push(localStack.pop());
					}
					System.out.println(" ---------------------------- ");
				}
			}
		}
	}
}
