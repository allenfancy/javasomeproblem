package com.allenfancy.系统通信.异步IO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 
 * JAVA AIO框架中，只实现了俩种网络IO通道"AsynchronousServerSocketChannel"(服务器监听通道)，"AsynchronousSocketChannel"(socket套接字通道).但是无论哪种通道他们都有独立的fileDescriptor(文件标示符)
 * attachment(附件，附件可以使任意对象，类似"通道上下文"),并被独立的SocketChannelReadHandle类实例引用。
 * 我们通过debug操作看结构：
 * 
 * 为什么还有Netty
 *  虽然JAVA NIO 和 JAVA AIO框架提供了 多路复用IO/异步IO的支持，但是并没有提供上层“信息格式”的良好封装。例如前两者并没有提供针对 Protocol Buffer、JSON这些信息格式的封装，但是Netty框架提供了这些数据格式封装（基于责任链模式的编码和解码功能）
 *	要编写一个可靠的、易维护的、高性能的（注意它们的排序）NIO/AIO 服务器应用。除了框架本身要兼容实现各类操作系统的实现外。更重要的是它应该还要处理很多上层特有服务，例如：客户端的权限、还有上面提到的信息格式封装、简单的数据读取。这些Netty框架都提供了响应的支持。
 *	JAVA NIO框架存在一个poll/epoll bug：Selector doesn’t block on Selector.select(timeout)，不能block意味着CPU的使用率会变成100%（这是底层JNI的问题，上层要处理这个异常实际上也好办）。当然这个bug只有在Linux内核上才能重现。
 *	这个问题在JDK 1.7版本中还没有被完全解决：http://bugs.java.com/bugdatabase/view_bug.do?bug_id=2147719。虽然Netty 4.0中也是基于JAVA NIO框架进行封装的（上文中已经给出了Netty中NioServerSocketChannel类的介绍），但是Netty已经将这个bug进行了处理。
 *	其他原因，用过Netty后，您就可以自己进行比较了。
 *
 */
public class SocketServer {

	private static final Object waitObject = new Object();

	public static void main(String[] args) throws IOException, InterruptedException {
		/**
		 * 对于使用的线程池技术：
		 * 1.Executors是线程池生成具体，通过这个工具我们可以很轻松的生成"固定大小的线程"、"调度池"、"可伸缩线程数量的池"
		 * 2.当然您可以通过ThreadPoolExecutor直接生成池。
		 * 3.这个线程池是用来得到操作系统的"IO事件通知"的，不是用来进行"得到IO数据后的业务处理的"。要进行后者的操作，您可以再使用一个池(
		 * 最好不要混用)
		 * 4.您也可以不使用线程池(不推荐)，如果决定不使用线程池，直接AsynchronousServerSocketChannel.open()
		 * 就行了。
		 */
		ExecutorService threadPool = Executors.newFixedThreadPool(20);
		AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(threadPool);
		final AsynchronousServerSocketChannel serverSocket = AsynchronousServerSocketChannel.open(group);

		// 设置要监听的端口"0.0.0.0"代表本机所有IP设备
		serverSocket.bind(new InetSocketAddress("0.0.0.0", 83));
		// 为AsynchronousServerSocketeChannel注册监听，注意只是为AsynchronousServerSocketChannel通道注册监听
		// 并不包括为 随后客户端和服务器 socketChannel通道注册的监听
		serverSocket.accept(null, new ServerSocketChannelHandle(serverSocket));
		// 等待，以便观察现象
		synchronized (waitObject) {
			waitObject.wait();
		}
	}

}

class ServerSocketChannelHandle implements CompletionHandler<AsynchronousSocketChannel, Void> {

	private AsynchronousServerSocketChannel serverSocketChannel;

	public ServerSocketChannelHandle(AsynchronousServerSocketChannel serverSocketChannel) {
		this.serverSocketChannel = serverSocketChannel;
	}

	/**
	 * 注意：我们分别观察 this,socketChannel,attachment三个对象的id。
	 * 来观察不同客户端连接到达时，这三个对象的变化，以说明ServerSocketChannelHandler的监听模式
	 */
	@Override
	public void completed(AsynchronousSocketChannel socketChannel, Void attachment) {
		// 每次都要重新注册监听(一次注册，一次响应)，但是由于 文件状态标示符是独享的，所以不需要担心有漏掉的事件
		this.serverSocketChannel.accept(attachment, this);
		// 为了这个新的socketChannel注册 read 事件，以便操作系统在收到数据并准备好后，主动通知应用程序
		// 在这里，由于我们要将这个客户端多次传输的数据累加起来一起处理，所以我们将一个StringBuffer对象作为一个附件依附在这个channel上
		ByteBuffer readBuffer = ByteBuffer.allocate(50);
		socketChannel.read(readBuffer, new StringBuffer(), new SocketChannelReadHandle(socketChannel, readBuffer));
	}

	@Override
	public void failed(Throwable exc, Void attachment) {
		System.out.println("失败");
	}
}

class SocketChannelReadHandle implements CompletionHandler<Integer, StringBuffer> {

	private AsynchronousSocketChannel socketChannel;
	/**
	 * 专门用于进行这个通道数据缓存操作的ByteBuffer<br>
	 * 当然，您也可以作为CompletionHandler的attachment形式传入。<br>
	 * 这是，在这段示例代码中，attachment被我们用来记录所有传送过来的Stringbuffer了。
	 */
	private ByteBuffer byteBuffer;

	public SocketChannelReadHandle(AsynchronousSocketChannel socketChannel, ByteBuffer byteBuffer) {
		this.socketChannel = socketChannel;
		this.byteBuffer = byteBuffer;
	}

	@Override
	public void completed(Integer result, StringBuffer historyContext) {
		//如果条件成立，说明客户端主动终止了TCP套接字，这时服务端终止就可以了
		if(result == -1){
			try{
				this.socketChannel.close();
			}catch(Exception e){
				
			}
			return ;
		}
		System.out.println("completed(Integer result, Void attachment) : 然后我们来取出通道中准备好的值");
		/**
		 * 实际上，由于我们从Integer result知道了本次channel从操作系统获取数据总长度
		 * 所以实际上，我们不需要切换成"读模式"的，但是为了保证编码的规范性，还是建议进行切换.
		 * 另外，无论Java AIO框架还是Java NIO框架，都会出现 buffere的总容量 小于 当前从操作系统获取到总数量
		 * 区别是：JAVA AIO框架中，我们不要专门考虑处理这样的情况，因为JAVA AIO框架已经帮助我做了处理。(做成了多次通知)
		 */
		this.byteBuffer.flip();
		byte[] contexts = new byte[1024];
		this.byteBuffer.get(contexts,0,result);
		this.byteBuffer.clear();
		try{
			String newContent = new String(contexts,0,result,"UTF-8");
			historyContext.append(newContent);
			System.out.println("========目前的传输结果:"+historyContext);
		}catch(Exception e){
			e.printStackTrace();
		}
		//如果条件成立，说明还没有接收到结束标记
		if(historyContext.indexOf("over") == -1){
			return;
		}
		
		System.out.println("== 收到完整信息,开始处理业务 ==");
		historyContext = new StringBuffer();
		
		//还要继续监听(一次监听一次通知)
		this.socketChannel.read(this.byteBuffer, historyContext, this);
		
	}

	@Override
	public void failed(Throwable exc, StringBuffer attachment) {
		try {
			System.out.println("=====发现客户端异常关闭，服务器将关闭TCP通道");
			this.socketChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
