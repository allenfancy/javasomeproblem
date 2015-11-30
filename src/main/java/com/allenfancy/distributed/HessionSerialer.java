package com.allenfancy.distributed;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

/**
 * 
 * @author allen
 * Hessian 进行序列化 和 反序列化
 */
public class HessionSerialer {

	/**
	 * 序列化
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public static byte[] Srialization(Object object) throws IOException{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		HessianOutput hessianOut = new HessianOutput(os);
		hessianOut.writeObject(object);
		byte[] bytes = os.toByteArray();
		return bytes;
	}
	/**
	 * 反序列化
	 * @param bytes
	 * @return
	 * @throws IOException
	 */
	public static Object Deserialization(byte[] bytes) throws IOException{
		ByteArrayInputStream is = new ByteArrayInputStream(bytes);
		HessianInput hessianInput = new HessianInput(is);
		return hessianInput.readObject();
	}
}
