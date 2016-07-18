package com.allenfancy.系统通信.RMI;
/**
 * 系统间通信管理：
 * 由RMI引出一个重要的系统间通信的管理规范RPC以及RPC的实现；再通过分析RPC的技术特点，引出另一种系统间通信的管理规范ESB
 * 并介绍ESB的一些具体实现。最后我们介绍SOA：面向服务的软件架构。
 * RMI基本使用：
 * 	RMI(Remote Method Invocation,远程方法调用)，是Java提供的JVM与JVM之间进行对象方法调用的技术框架的实现
 *  通过RMI技术，某些本地的JVM可以调用存在另外一个JVM中的对象方法，就好像它仅仅是在调用本地JVM中某个对象一样。
 *  例如：RMI客户端中的如下调用：
 *  	List<UserInfo> users = remoteServiceInterface.queryAllUserInfo();
 *  看似一样，但实际上remoteServiceInterface对象的具体方法实现却不在本地的JVM中，而是在某个远程的JVM(这个远程JVM可以是RMI客户端同属于一台物理机，也可以属于不同的物理机)
 *  
 *  RMI使用场景：
 *  	RMI适用于俩个系统都是使用JAVA语言进行构造，不需要考虑跨语言支持的情况。并且对这俩个JAVA系统的通讯速度有要求的情况。
 *  	RMI是一个良好的，特殊的RPC实现：使用JRMP协议承载数据描述，可以使用BIO和NIO俩种IO通信模型。RMI框架是可以在大规模集群系统中使用的。
 *  	当然是不是使用RMI技术，还要看技术背景的。
 *  RMI框架的基本组成
 *  	要定义和使用一套基于RMI框架工作的系统，您至少需要做一下几个工作：
 *  	1.定义RMI Remote接口
 *  	2.实现这个RMI Remote接口
 *  	3.生成Stub 和 Skeleton。这一步的具体操作JDK版本不同。RMI注册表的工作方式也会影响 Stub是否需要命令行生成 这个问题。
 *  	4.向 RMI注册表 注册在第二步我们实现的RMI Remote接口
 *  	5.创建一个Remote客户端，通过java 命令服务 在 RMI注册表所在的IP：PORT寻找注册号的RMI服务。
 *  	6.Remote客户端向调用存在本地JVM中对象那样，调用存在于远程JVM上的RMI接口
 * 
 * RMI工作原理：
 * 	1.Registry 和 Stub Skeleton的关系
 * 	  .一定要说明，在RMI Client试试正式的RMI调用前，它必须通过LocateRegister或者Naming方式到RMI注册表寻找要调用RMI注册信息。找到RMI事务注册信息后，Client会从RMI注册表获取RMI Remote Service的Stub信息。这个过程成功后，RMI才会开始正式的调用过程。
 * 	  .另外要说明的是RMI Client正式调用过程，也不是由RMI Client直接访问Remote Service，而是由客户端获取的Stub作为RMI Client的代理访问Remote Service的代理Skeleton。真是的请求调用是在Stub-Skeletion之间进行的。
 * 	  .Registry并不参与具体的Stub-Skeleton的调用过程，只负责记录"哪个服务器"使用哪一个Stub，并在Remote Client询问它时将这个Stub拿给Client
 * 	  .为了验证上下文描述的调用过程.
 * 
 * Remote-Service线程管理：	
 * 	sun.rmi.transport.tcp.maxConnectionThreads:连接池的大小，默认无限制。无限的大小肯定有问题，按照Linux单进程可打开的最大文件数量限制，建议的设置值为65535。如果同一时间连接池中的线程数量达到了最大值，那么后续的Client请求将会报错。
 *  sun.rmi.transport.tcp.threadKeepAliveTime:如果当线程池中有闲置的线程资料的话，那么这个闲置线程资源多久被注销，默认为1分钟
 * 如果在Linux 或者 window的命令控制台执行的话，您可以通过类似如下语句进行参数设置：
 * 	Java -Dsun.rmi.transport.tcp.maxConnectionThreads = 2 -Dsun.rmi.transport.tcp.threadKeepAliveTime = 1000 testRMI.RemoteRegistryUnicastMain
 * 
 * Registry 和 Naming
 *  Registry 和 Naming都可以进行RMI服务的bind/rebind/unbind,都可以用lookup方法查询RMI服务。
 *  Naming实际上是对Registry的封装。使用完整的URL方式对已注册的服务名进行查找。我们通过Naming类中lookup方法的源代码对Naming的工作方式进行说明。
 *
 */
public class packageInfo {

}
