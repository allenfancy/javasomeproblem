package com.allenfancy.系统通信.netty;
/**
 *Netty框架并不只是封装了多路复用的IO模型，也包括提供了传统的阻塞式/非阻塞式 同步IO的模式封装。 
 *
 *在Netty中，这里的部分工作就是交给BOSS线程做。BOSS线程负责发现连接到服务器的新的channel(SocketServerChannel的ACCEPT事件),并将这个
 *channel经过检查后注册到WORK连接池的某个EventLoop线程中。
 *而当WORK线程发现操作系统有一个它感兴趣的IO事件时则调用相应的ChannelHandler事件。当某个channel失效后，这个channel将从绑定的EventLoopLoop中
 *被剔除。
 *一个Work线程池的线程将按照底层JAVA NIO的Selector的事件状态，决定执行ChannelHandler中的哪一个事件方法(Netty中的事件，包括了
 *channelRegistered,channelUnregistered,channelActive,channelInactive等事件方法)。执行完成后，work线程将一直轮询直到操作系统回复下一个他所管理的channel发生了新的IO事件。
 *
 *1.ByteBuf
 *2.Chanenl，通道。您可以使用JAVA NIO中的ChannelHandler去初始理解它，但实际上它的意义和JAVA NIO中的通道意义不一样。
 *3.ChannelPipeline 和 ChannelHandler
 *  Netty中的每一个Channel，都有一个独立的ChannelPipeline，中文为"通道水管"。只不过这个水管是双向的里面汤流着数据，数据可以通过这个piple流程到服务器，也可以通过这个水管从服务器流出
 *  在ChannelPipeline中，有若干的过滤器。我们成为ChannelHandler 处理器或者过滤器。同流入和流出的概念向对应：
 *  用于处理/过滤 流入数据的ChannelHandler，称为ChannelInboundHandler；用于处理 过滤 流出数据的ChannelHandler称为ChannelOutBoundHandler
 *  
 *  责任链和适配器的应用：
 *  1.数据在ChannelPipeline中有一个个的Handler进行处理，并形成一个新的数据状态。这是典型"责任链"模式
 *  2.需要注意，虽然数据管道中的Handler是按照顺序执行的，但不代表某偶一个Handler会处理任何一种由 上一个handler发送过来的数据。某些Handler会检查传来的数据是否合服要求，如果不符合自己处理的要求，则不进行处理。
 *  3.我们可以实现ChannelInboundHandler接口或者ChannelOutBoundHandler接口，来实现我们自己业务的 数据流如处理器 或者 数据流出 处理器。
 *  但是这俩个接口的事件方法是比较多的，例如ChannelInboundHandler接口一共有11个需要实现的接口方法(包括父级ChannelHandler的，我们在下一节讲解Channel的生命周期时)，一般情况下我们不需要把这些方法全部实现。
 *  所以Netty中增加了俩个适配器"ChannelInboundHandlerAdapter"和"ChannelOutboundHandlerAdapter"来帮助我们实现我们只需要实现的事件方法。其他的事件方法我们就不需要关心：
 *  ChannelInboundHandler类举例：
 *  	1.HttpRequestDecoder : 实现了http协议的数据输入格式的解析。这个类将数据编码为HttpMessage对象，并交由下一个ChannelHandler进行处理。
 *  	2.ByteArrayDecoder : 最基本的数据流输入处理器，将所有的byte转换为ByteBuf对象
 *  	3.DelimiterBasedFrameDecoder： 这个数据流输入处理器，会按照外部传入的数据中给定的某个关键字符/关键字符串，重新将数据组装为新的段落并发送给下一个Handler处理器。后文中，我们将使用这个树立起进行TCP半包的问题。
 *  	4.还有很多直接支持标准数据格式解析的处理器，例如支持Google Protocol Buffers 数据格式解析的 ProtobufDecoder和 ProtobufVarint32FrameDecoder处理器。
 *  ChannelOutboundHandler类举例
		HttpResponseEncoder：这个类和HttpRequestDecoder相对应，是将服务器端HttpReponse对象的描述转换成ByteBuf对象形式，并向外传播。
		ByteArrayEncoder：这个类和ByteArrayDecoder，是将服务器端的ByteBuf对象转换成byte数组的形式，并向外传播。一般也和ByteArrayDecoder对象成对使用。
		还有支持标准的编码成Google Protocol Buffers格式、JBoss Marshalling 格式、ZIP压缩格式的ProtobufEncoder、ProtobufVarint32LengthFieldPrepender、MarshallingEncoder、JZlibEncoder等
 *NettyChannel 完整生命周期 ： 
 *			BOSS线程工作					连接建立段					数据传输段			连接终止段
 *		selector.select ------->	 handler added
 *										|当这个channel注册到
 * 										|某个eventLoop后
 * 										V
 *									channel Active
 * 										|某个eventLoop后
 * 										V
 * 									
 * 				
 * IO模型的封装：					
 * 		IO模型：
 * 	     阻塞和非阻塞：这个概念是针对应用程序而言，是指应用程序中的线程在向操作系统发送IO请求后，是否一直等待操作系统的IO响应。如果是那么就是阻塞式的；
 * 					 如果不是，那么应用程序一般会以轮询的方式以一定周期询问操作系统，直到某次获得了IO响应为止(轮询间隔应用程序线程可以做一些其他工作)。
 * 		同步和异步：	IO操作都是由操作系统进行的(这里的IO操作是个广泛概念：磁盘IO，网络IO都算)，不同的操作系统对不同设备的IO操作都不同模式。同步和异步这俩个概念都指代的操作烯烃级别，同步IO是指操作系统和设备进行交互时，必须等待一次完整的请求-响应完成，才能进行下一次操作
 * 					异步IO是指操作系统和设备进行交互时，不必等待本次得到响应，就可以直接进行下一次操作请求。设备处理器完某次请求后，会主动给操作系统相应的响应通知。
 * 		多路复用IO：	多路复用IO，从本质上看还是一种同步，因为它没有100%消除IO_WAIT,操作系统也没它提供 主动通知 机制。但是多路复用IO的处理速度已经相当快了，利用设备执行IO操作的时间,操作系统可以继续执行IO请求。并同样采用周期性轮询的方式，获取一批IO操作请求的执行响应。操作系统支持的多路复用IO技术主要有
 * 					select poll epoll kqueue
 * 		阻塞式同步IO模型：从字面上就很好理解了，应用程序请求IO操作，并一直等待处理结果；操作系统同时也进行IO操作，并等待设备的处理结果；可以看出，应用程序的请求线程和操作系统的内核线程都是等待状态。
 * 		非阻塞同步IO模型：应用程序请求IO，并且不用一直等待返回结果就去做其他事情。隔一定的周期，再去询问操作系统上次IO操作有没有结果，直到某一次询问从操作系统拿到IO结果；操作系统内核线程在进行IO操作时，还是处理一直等待设备返回操作结果的状态。
 * 		非阻塞式多路复用IO模型：应用程序请求IO的工作采用非阻塞方式进行；操作系统采用多路复用模式工作。
 * 		非阻塞式异步IO模型：应用程序请求IO的工作采用非阻塞方式进行，但是不需要轮询了，因为操作系统异步IO其中一个主要的性：可以在有IO响应结果的时候，主动进行通知。
 * 
 * 对IO模型的再次封装：
 * 		在JAVA中都能够找到对应的支持：传统的JAVA Socket套接字支持阻塞/非阻塞模式下的同步IO(有的技术资料也称为OIO或者BIO)
 * 		JAVA NIO框架在不同操作系统下支持不同种类的多路复用IO技术；JAVA AIO框架支持异步IO
 * 实际上 Netty是对JAVA BIO，JAVA NIO框架的再次封装。让我们不再纠结于选用哪种底层实现。您可以理解成Netty/MINA框架是一个面向上层业务实现进行封装的业务层框架。而Java Socket框架 Java NIO框架 Java AIO框架更加偏向于对下层技术实现的封装，即面向 【技术层】 的框架。
 * 数据信息格式的封装：
 * 	技术层 框架本身只对IO模型技术实现进行了封装，并不关心IO模型中流淌的数据格式；业务层框架对数据格式也进行了处理，让我们可以抽出精力关注本身。
 *  Protobuf数据协议的集成：Netty利用自身的Channelpipeline的设计（在《架构设计：系统间通信（6）——IO通信模型和Netty 上篇》中讲过），对Protobuf数据协议进行了无缝结合。
 *	JBoss Marshalling数据协议的集成：JBoss Marshalling 是一个Java对象的序列化API包，修正了JDK自带的序列化包的很多问题，又保持跟 java.io.Serializable 接口的兼容。Netty通过封装这个协议，可以帮助我们在客户端-服务端简便的进行对象系列化和反序列化。
 *	HTTP Request / HTTP Response 协议的集成：在Netty中，可以方便的接受和发送Http协议。也就是说，我们可以使用Netty搭建自己的WEB服务器，当然您还可以根据自己的业务要求，方便的设计类似于Struts、spring MVC这样的WEB框架。
 *  
 *  Netty中使用： FixedLengthFrameDecoder、DelimiterBasedFrameDecoder、LineBasedFrameDecoder来解决半包/粘包的问题。
 * 	 FixedLengthFrameDecoder:解码处理器将TCP/IP的数据按照指定的长度进行重新拆分，如果接受到的数据不满足设置的固定的长度，Netty将等待新的数据到达：
 * 	 serverBootStrap.childHandler(new ChannelInitializer<NioSocketChannel>(){
 * 		protected void  initChannel(NioSocketChannel ch) throws Exception{
 * 			ch.pipeline().addLast(new ByteArrayEncoder());
 * 			ch.pipeline().addLast(new FxiedLengthFrameDecoder(20));
 * 			ch.pipeline().addLast(new TCPServerHandler());
 * 			ch.pipeline().addLast(new ByteArrayDecoder());
 * 		}
 * 	 });
 * Netty上层的channelRead事件方法将在Channel接收到20个字符的情况下被触发；而如果剩余的内容不到20个字符，channelRead方法将不会被触发(但注意channelReadComplete方法触发的拉)。
 * 
 * 使用LineBasedFrameDecoder解决问题
 * 	LineBasedFrameDecoder解码器，基于最简单的 "换行符" 进行接收到的信息的再组织。windows和linux俩个操作系统的"换行符"是不一样的，
 *  LineBasedFrameDecoder解码器都支持。当然这个解码器没有我们后面介绍的DelimiterBasedFrameDecoder解码器灵活。
 *  serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>(){
 *  	protected void initChannel(NioSocketChannel ch) throws Exception{
 *  		ch.pipeline().addLast(new ByteArrayEncoder());
 *  		ch.pipeline().addLast(new LineBasedFrameDecoder(100));
 *  		ch.pipeline().addLast(new TCPServerHandler());
 *  		ch.pipeline().addLast(new ByteArrayDecoder());
 *  	}
 *  });
 *  使用DelimiterBasedFrameDecoder解决问题
 *  	DelimiterBasedFrameDecoder是按照“自定义”分隔符（也可以是“回车符”或者“空字符”注意windows系统中和linux系统中“回车符”的表示是不一样的）进行信息的重新拆分。
 *  	serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>(){
 *  	protected void initChannel(NioSocketChannel ch) throws Exception{
 *  		ch.pipeline().addLast(new ByteArrayEncoder());
 *  		ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1500, false, Delimiters.lineDelimiter()));
 *  		ch.pipeline().addLast(new TCPServerHandler());
 *  		ch.pipeline().addLast(new ByteArrayDecoder());
 *  	}
 *  });
 */
public class packageInfo {

}
