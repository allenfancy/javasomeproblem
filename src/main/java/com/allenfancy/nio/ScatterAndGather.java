package com.allenfancy.nio;
/**
 * @author allen
 * 分散和聚集
 * 	分散scatter从Channel中读取是指在读操作时将读取的数据写入多个buffer中。因此，Channel将从Channel中读取的数据 分散 到多个Buffer中。
 * 	聚集(gather)写入Channel是指在写操作时将多个Buffer的数据写入同一个Channel，因此，Channel将多个Buffer中的数据 聚集 后发送到Channel。
 *  scatter / gather 经常用于需要将传输的数据分开处理的场合，例如传输一个由消息头和消息体组成的消息，您可能会将消息体和消息头分散到不同的Buffer中，这样您可以方便的处理消息和消息体
 *  
 *  Scattering Reads：
 *  	Scattering Reads 是指数据从一个channel读取到多个Buffer中。
 *  代码：
 *  	ByteBuffer header = ByteBuffer.allocate(128)
 *  	ByteBuffer body = ByteBuffer.allocate(1024)
 *  	ByteBuffer[] bufferArray = {header,body};
 *  	channel.read(bufferArray);
 *
 */
public class ScatterAndGather {

}
