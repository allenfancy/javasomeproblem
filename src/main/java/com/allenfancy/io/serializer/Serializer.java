package com.allenfancy.io.serializer;

import java.io.IOException;

public interface Serializer<T> {

	byte[] serialize(T object) throws IOException;
}
