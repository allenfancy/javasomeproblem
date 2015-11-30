package com.allenfancy.distributed.ch01;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class Consumer {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IOException, IOException, ClassNotFoundException{
		String interfacename = SayHelloService.class.getName();
		
		Method method = SayHelloService.class.getMethod("sayHello", java.lang.String.class);
		
		Object[] arguments = {"hello"};
		
		Socket socket = new Socket("127.0.0.1",8080);
		
		 
		ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
		output.writeUTF(interfacename);//接口名
		output.writeUTF(method.getName());//方法名
		
		output.writeObject(method.getParameterTypes());
		
		output.writeObject(arguments);
		
		//从远端读取方法执行结果
		ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
		Object result = input.readObject();
		System.out.println(result);
	}
}
