package com.allenfancy.netty.protocol.netty.codec;

import java.io.IOException;

import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.marshalling.Unmarshaller;

public class MarshallingCodecFactory {

	protected static Marshaller buildMarshalling() throws IOException {
		final MarshallerFactory marshallerFactory = Marshalling.getMarshallerFactory("serial");
		final MarshallingConfiguration configuration = new MarshallingConfiguration();
		configuration.setVersion(5);
		Marshaller marshaller = marshallerFactory.createMarshaller(configuration);
		return marshaller;
	}

	protected static Unmarshaller buildUnMarshaller() throws IOException {
		final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
		final MarshallingConfiguration configuration = new MarshallingConfiguration();
		configuration.setVersion(5);
		final Unmarshaller unmarshaller = marshallerFactory.createUnmarshaller(configuration);
		return unmarshaller;
	}

}
