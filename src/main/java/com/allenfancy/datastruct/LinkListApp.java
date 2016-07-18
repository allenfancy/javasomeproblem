package com.allenfancy.datastruct;

public class LinkListApp {

	public static void main(String[] args){
		LinkList theList = new LinkListApp.LinkList();
		theList.insertFist(1, 1.1);
		theList.insertFist(2, 2.2);
		theList.insertFist(3, 3.3);
		theList.insertFist(4, 4.4);
		theList.displayList();
		
		Link l = theList.find(2);
		System.out.println(l.dData + " " + l.iData);
		
	}
	
	static class Link{
		public int iData;
		public double dData;
		public Link next;
		
		public Link(int id,double dd){
			iData = id;
			dData = dd;
		}
		
		public void display(){
			System.out.println(iData + " " + dData);
		}
	}
	
	static class LinkList{
		private Link first;
		
		public LinkList(){
			first = null;
		}
		public boolean isEmpty(){
			return first==null;
		}
		
		public void insertFist(int id,double dd){
			Link newLink = new Link(id,dd);
			newLink.next = first;
			first = newLink;
		}
		public Link deleteFirst(){
			Link temp = first;
			first = first.next;
			return temp;
		}
		public void displayList(){
			System.out.println("List (first ---> last )");
			Link current = first;
			while(current != null){
				current.display();
				current = current.next;
			}
			System.out.println(" ");
		}
		//返回当前的链接点
		public Link find(int d){
			Link current = first;
			while(current.iData != d){
				if(current.next == null)
					return null;
				else
					current = current.next;
			}
			return current;
		}
	}
}

