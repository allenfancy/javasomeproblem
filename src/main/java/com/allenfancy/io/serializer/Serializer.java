package com.allenfancy.io.serializer;

import java.io.IOException;
import java.io.OutputStream;

public interface Serializer<T> {

	byte[] serialize(T object) throws IOException;
}
