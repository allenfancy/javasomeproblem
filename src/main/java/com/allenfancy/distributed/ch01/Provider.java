package com.allenfancy.distributed.ch01;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Provider {

	public static void main(String[] args){
		try {
			ServerSocket server = new ServerSocket(8080);
			while(true){
				Socket socket = server.accept();
				
				//读取服务信息
				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
				String interfaceName = input.readUTF();//接口名称
				String methodName = input.readUTF();//方法名称
				Class<?>[] parameteTypes = (Class<?>[])input.readObject();//参数类型
				Object[] arguments = (Object[]) input.readObject();
				
				Class serviceInterfaceClass = Class.forName(interfaceName);
			//	Object service = services.get(interfaceName);//接口的class
				
				Method method = serviceInterfaceClass.getMethod(methodName, parameteTypes);
				
				Object result = method.invoke(null, arguments);
				
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
				
				output.writeObject(result);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
