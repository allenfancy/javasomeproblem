package com.allenfancy.distributedesgin.suanfa.二叉树;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TreeApp {
	public static void main(String[] args) {
		int value;
		Tree theTree = new Tree();

		theTree.insert(50);
		theTree.insert(25);
		theTree.insert(75);
		theTree.insert(12);
		theTree.insert(37);
		theTree.insert(43);
		theTree.insert(30);
		theTree.insert(33);
		theTree.insert(87);
		theTree.insert(93);
		theTree.insert(97);
		theTree.traverser(2);
		/*while (true) {
			System.out.print("Enter first letter of show, ");
			System.out.print("insert, find, delete, or traverse :");
			int choice = getChar();
			switch (choice) {
			case 's':
				theTree.displayTree();
				break;
			case 'i':
				System.out.print("Enter value to insert : ");
				value = getInt();
				theTree.insert(value);
				break;
			case 'f':
				System.out.print("Enter value to find : ");
				value = getInt();
				Node found = theTree.find(value);
				if(found != null){
					System.out.println("Found: " );
					found.displayNode();
					System.out.println();
				}else{
					System.out.println("can not find !" +value);
					
				}
				break;
			}
		}*/

	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static char getChar() {
		try {
			String s = getString();
			return s.charAt(0);
		} catch (Exception e) {
			e.printStackTrace();
			return ' ';
		}
	}

	public static int getInt() {
		try {
			String s = getString();
			return Integer.parseInt(s);
		} catch (Exception e) {
			e.printStackTrace();
			return ' ';
		}
	}
}
