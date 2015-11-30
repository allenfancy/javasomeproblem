package com.allenfancy.distributed;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * 
 * @author allen
 *使用IO流实现序列化  和 反序列化
 */
public class IOSerialization {

	/**
	 * 序列化
	 * @param object
	 * @return
	 * @throws IOException 
	 */
	public static byte[] Serialization(Object object) throws IOException{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(os);
		out.writeObject(object);
		return os.toByteArray();
	}
	/**
	 * 反序列化
	 * @param bytes
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static Object Deserialization(byte[] bytes) throws ClassNotFoundException, IOException{
		ByteArrayInputStream is = new ByteArrayInputStream(bytes);
		ObjectInputStream in = new ObjectInputStream(is);
		return in.readObject();
	}
	
}
