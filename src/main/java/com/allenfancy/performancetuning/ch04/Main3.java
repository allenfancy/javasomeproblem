package com.allenfancy.performancetuning.ch04;

public class Main3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RequestQueue requestQueue = new RequestQueue();

		for (int i = 0; i < 10; i++) {
			new ClientThread(requestQueue, "ServerThread" + i).start();
		}
		
		for(int i = 0; i < 10; i++){
			new ClientThread(requestQueue,"ClientThread" + i).start();
		}
	}

}
