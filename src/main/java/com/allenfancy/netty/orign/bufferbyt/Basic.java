package com.allenfancy.netty.orign.bufferbyt;
/**
 *<p>
 * ByteBuf：从内存角度而言，分为俩类：
 * 			堆内存：HeapByteBuf字节缓冲区：特点是内存的分配和回收速度快，可以被JVM自动回收；缺点：如果进行Socket的I/O读写，需要额外做一次内存复制，将堆内存对应的缓冲区复制到内核channel中，性能就会下降。
 * 			直接内存:DirectByteBuf字节缓冲区：非堆内存，它在堆外进行内存分配，相比于堆内存，它的分配和回收速度会慢些，但是将它写入或者从Socket Channels中读取时，少一次内存复制，速度比堆内存块。
 * 最佳实战：在I/O通信线程的读写缓冲区使用DirectByteBuf，后端业务消息的编程解码模块使用HeapByteBuf，这样组合性能最佳。 
 *</p>
 */
public class Basic {

}
