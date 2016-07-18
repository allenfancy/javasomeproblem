package com.allenfancy.系统通信.正常通信;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketServer1 {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket =null;
		try {
			serverSocket = new ServerSocket(9090);
			while (true) {
				System.out.println("获取到接收流");
				Socket socket = serverSocket.accept();
				// 读取信息
				
				InputStream in = socket.getInputStream();
				OutputStream out = socket.getOutputStream();
				Integer sourcePort = socket.getPort();
				int maxLen = 2048;
				byte[] contextBytes = new byte[maxLen];
				int realLen = in.read(contextBytes, 0, maxLen);
				String message = new String(contextBytes, 0, realLen);

				System.out.println("服务器收到来自于端口：" + sourcePort + "的信息：" + message);

				// 下面开始发送信息
				out.write("回发响应信息！".getBytes());

				// 关闭
				out.close();
				in.close();
				socket.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null) {
				serverSocket.close();
			}
		}
	}
}
