package com.allenfancy.io.serializer;

import java.io.IOException;


public interface Deserializer<T>{

	T deserializer(byte[] bytes) throws IOException;
}
