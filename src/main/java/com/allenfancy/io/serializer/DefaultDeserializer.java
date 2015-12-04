package com.allenfancy.io.serializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import org.springframework.core.NestedIOException;

public class DefaultDeserializer implements Deserializer<Object>{

	public Object deserializer(byte[] bytes) throws IOException {
		// TODO Auto-generated method stub
		ByteArrayInputStream is = new ByteArrayInputStream(bytes);
		ObjectInputStream objectInputStream = new ObjectInputStream(is);
		try{
			return objectInputStream.readObject();
		}catch(ClassNotFoundException ex){
			throw new NestedIOException("Failed to deserialize object type",ex);
		}
	}

}
