package com.allenfancy.系统通信.请求过多阻塞;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketServer2 {

	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(9090);

		try {
			while (true) {
				Socket socket = serverSocket.accept();
				// 当然业务处理过程可以交给一个线程（这里可以使用线程池）,并且线程的创建是很耗资源的。
				// 最终改变不了.accept()只能一个一个接受socket的情况,并且被阻塞的情况
				SocketServerThread socketServerThread = new SocketServerThread(socket);
				new Thread(socketServerThread).start();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (serverSocket != null) {
				serverSocket.close();
			}
		}
	}
}

/**
 * 当然，接收到客户端的socket后，业务的处理过程可以交给一个线程来做。 但还是改变不了socket被一个一个的做accept()的情况。
 * 
 */
class SocketServerThread implements Runnable {

	private Socket socket;

	public SocketServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		InputStream in = null;
		OutputStream out = null;
		try {
			// 下面我们收取信息
			in = socket.getInputStream();
			out = socket.getOutputStream();
			Integer sourcePort = socket.getPort();
			int maxLen = 1024;
			byte[] contextBytes = new byte[maxLen];
			// 使用线程，同样无法解决read方法的阻塞问题，
			// 也就是说read方法处同样会被阻塞，直到操作系统有数据准备好
			int realLen = in.read(contextBytes, 0, maxLen);
			// 读取信息
			String message = new String(contextBytes, 0, realLen);

			// 下面打印信息
			System.out.println("服务器收到来自于端口：" + sourcePort + "的信息：" + message);

			// 下面开始发送信息
			out.write("回发响应信息！".getBytes());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// 试图关闭
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
				if (this.socket != null) {
					this.socket.close();
				}
			} catch (IOException e) {
			}
		}
	}
}
