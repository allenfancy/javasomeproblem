package com.allenfancy.io.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class DefaultSerializer implements Serializer<Object>{

	
	public byte[] serialize(Object object) throws IOException {
		// TODO Auto-generated method stub
		if(!(object instanceof Serializable)){
			throw new IllegalArgumentException(
					getClass().getSimpleName() + "requires a Serializable payload"
					+"but received an object of type [" + object.getClass().getName() + "]");
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);
		objectOutputStream.writeObject(object);
		objectOutputStream.flush();
		return os.toByteArray();
		
	}

}
