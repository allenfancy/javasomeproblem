
package com.allenfancy.netty.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * 
 * @author allen
 * 
 */
public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler> {
	/**
	 * 为何还要继续调用accept方法呢？
	 * 当我们调用AsynchronousSocketChannel的accept方法后，如果有新的客户端连接接入，系统将回调我们传入的CompletionHandler实例的completed方法。
	 * 表示新的客户端已经接入成功，因为一个AsynchronousSocketChannel可以接收成千上万个客户端，所以我们要继续调用它的accept方法，接收其他客户端连接，最终形成一个循环、
	 * 每当接收一个客户端连接成功之后，再异步接收新的客户端连接。
	 * ByteBuffer dst: 接收缓冲区，用于从异步Channels中读取数据包
	 * A attachment:异步channel携带的福建，通知回调的时候作为入口参数使用
	 * CompletionHandler<Integer,? super A>:接收通知回调的业务Handler，
	 */
	public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {
		attachment.asynchronousServerSocketChannel.accept(attachment, this);
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		result.read(buffer, buffer, new ReadCompletionHandler(result));
	}

	public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
		exc.printStackTrace();
		attachment.latch.countDown();
	}

}
