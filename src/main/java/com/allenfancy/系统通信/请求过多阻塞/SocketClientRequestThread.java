package com.allenfancy.系统通信.请求过多阻塞;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class SocketClientRequestThread implements Runnable {

	private CountDownLatch countDownLatch;
	private Integer clientIndex;

	public SocketClientRequestThread(CountDownLatch countDownLatch, Integer clientIndex) {
		this.countDownLatch = countDownLatch;
		this.clientIndex = clientIndex;
	}

	@Override
	public void run() {
		Socket socket = null;
		OutputStream clientRequest = null;
		InputStream clientResponse = null;
		try {
			socket = new Socket("127.0.0.1", 9090);
			clientRequest = socket.getOutputStream();
			clientResponse = socket.getInputStream();
			this.countDownLatch.await();
			// 发送请求
			clientRequest.write(("这是第" + this.clientIndex + "个客户端的请求。").getBytes());
			clientRequest.flush();
			// 在这里等待，直到服务器返回信息
			System.out.println("第" + this.clientIndex + "个客户端的请求发送完请求，等待服务器返回信息");
			int maxLen = 1024;
			byte[] contextBytes = new byte[maxLen];
			int realLen;
			String message = "";
			while ((realLen = clientResponse.read(contextBytes, 0, maxLen)) != -1) {
				message += new String(contextBytes, 0, realLen);
			}
			System.out.println("接收到来自服务器的信息:"+message);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (clientRequest != null) {
					clientRequest.close();
				}
				if (clientResponse != null) {
					clientResponse.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
