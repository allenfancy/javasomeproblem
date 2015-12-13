package com.allenfancy.nio;

import java.nio.ByteBuffer;

/**
 * @author allen
 * Buffer的基本原理：
 * 	Buffer中有3个重要的参数：位置(position),容器(capacity)和上限(limit)
 * 		参 数					写 模 式 											读 模 式
 *  位置(position)		当前缓冲区的位置，将从position的下一个位置写数据				当前缓冲区读取的位置，将从此位置后，读取数据
 *  容量(capactiy)		缓冲区的总容量上限											缓冲区的总容量上限
 *  上限(limit)			缓冲区的实际上限，它总是小于等于容量。通常情况下，和容量相等	
 */
public class BufferTest {

	public static void main(String[] args){
		testBuffer();
	}
	
	public static void testBuffer(){
		ByteBuffer b = ByteBuffer.allocate(15);
		System.out.println("limit = " + b.limit() + " capacity="+b.capacity() + " position = " + b.position());
		
		for(int i = 0; i < 10; i++){
			b.put((byte)i);
		}
		System.out.println("limit = " + b.limit() + " capacity="+b.capacity() + " position = " + b.position());
		b.flip();//重置position
		System.out.println("limit = " + b.limit() + " capacity="+b.capacity() + " position = " + b.position());
		for(int i = 0; i < 5; i++){
			System.out.print(b.get());
		}
		System.out.println();
		System.out.println("limit = " + b.limit() + " capacity="+b.capacity() + " position = " + b.position());
		b.flip();//重置position
		System.out.println("limit = " + b.limit() + " capacity="+b.capacity() + " position = " + b.position());
	}
}
