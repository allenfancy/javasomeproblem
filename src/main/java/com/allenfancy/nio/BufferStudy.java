package com.allenfancy.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/***
 * 
 * @author allen
 * Buffer读写数据一般循序下面的四个规则
 * 1.写入数据到Buffer
 * 2.调用flip()方法
 * 3.从Buffer中读写数据
 * 4.调用Clear()方法或者compact()方法
 * 
 * 当向Buffer写入数据时，Buffer会记录下写了多少数据。一旦要读取数据，需要通过flip()方法将Buffer从写模式切换到读模式。在读模式下,可以读取之前写入到Buffer的所有数据。
 * 一旦读完了所有的数据，就需要清空整个缓冲区。让它可以再次被写入。有俩种方式能清空缓冲区：调用clear()或者compact()方法。clear()方法会清空整个缓冲区。compact()方法只会清
 * 清除已经读取过的数据。任何未读取的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面。
 *
 *Buffer的capacity,position和limit
 *缓冲区本质上是一块可以写入数据，然后可以从中读取数据的内存。这块内存被包装成NIO Buffer对象，并提供了一组方法，用来方便的访问该块内存。
 *为了理解Buffer的工作原理，三个属性：
 *capacity
 *position
 *limit
 *position 和limit的含义取决于Buffer处在读模式还是写模式。不管Buffer处于什么模式，capacity的含义总是一样的。
 *
 *capacity：
 *	作为一个内存块，buffer有一个固定的大笑之，也叫『capacity』。您只能往这里写capacity个byte long char等类型。一旦Buffer满了，需要将其清空(
 *	通过读数据或者直接清楚数据)才能继续往里面写数据
 *
 *position
 *	当你写数据库到Buffer中时，position表示当前的位置，初始的position值为0，当一个byte long等数据写到Buffer后，position会向前移动到下一个可插入数据的Buffer单元。position最大可谓capacity-1
 *	当读取数据时，也是从某个特定的位置读。当将Buffer从写模式切换到读模式，position会被重置为0，当从Buffer的position处读取数据时，position向前移动到下一个可读的位置
 *
 *limit
 *	在写模式下，Buffer的limit表示您最多能往Buffer中写多少数据。写模式下，limit等于Buffer的capacity。
 *	当切换Buffer到读模式时，limit表示您最多能读到多少数据。因此，当切换Buffer到读模式时，limit会被设置成写模式下的position值，换句话说，您能读到的数据就是之前写入的数据。
 *Buffer的类型
 *Java NIO有以下Buffer类型：
 *	ByteBuffer
 *	MappedByteBuffer
 *	CharBuffer
 *	DoubleBuffer
 *	FloatBuffer
 *	LongBuffer
 *	IntBuffer
 *	ShortBuffer
 *	
 * 向Buffer中写数据
 * 	写数据到Buffer有俩种方式：
 * 		从Channel写到Buffer	
 * 		通过Buffer的put方法写到Buffer里
 * 	从Channel写到Buffer的例子：
 * 		int bytesRead  = inChannel.read(buf);//read into Buffer
 *  通过put方法写Buffer的例子
 *  	buf.put(127)
 *  
 *  flip()方法：
 *  	flip方法将Buffer从写模式切换到读模式。调用flip()方法会将position设置0.并将limit设置成之前position 的值、
 *  
 *  从Buffer中读取数据
 *  	从Buffer读取数据到Channel
 *  		int byteWritten = inChannel.write(buf)
 *  	使用get方法从Buffer中读取数据	
 *  		byte aByte = buf.get();
 * 	rewind()方法：
 * 		Buffer.rewind()将position设回0，所以你可以重读Buffer中的所有数据。limit保持不变，任然表示能从Buffer中读取多少个元素。
 *  clear()和compact()方法
 *  	一旦读完Buffer中的数据，需要让Buffer准备好再次读写入。可以通过clear()和compact()方法来完成。
 *  	如果调用的是clear()方法，position将被设置回0.limit被设置成为capacity的值。即Buffer被清空了。Buffer中的数据并未清除，只是这些标记告诉我们可以从哪里开始往Buffer里写数据
 *  	如果Buffer中有一些未读的数据，调用clear()方法，数据将被遗忘，意味着不在有任何标记会高武您哪些数据被度过，哪些没有被度过
 *  如果Buffer中仍有未读的数据，且后续还需要这些数据，但是此时想要先先写些数据，那么使用compact()方法。
 *	compact()方法将所有未读的数据拷贝到Buffer起始处。然后将position设到最后一个未读元素正后面。limit属性依然像clear()方法一样，设置成capacity。
 *	现在Buffer准备好写数据了，但是不会覆盖未读的数据。 
 *	
 * mark()与reset()方法
 * 	使用Buffer.mark()方法，可以标记Buffer中的一个特定的position。之后可以通过使用Buffer.reset()方法恢复到这个position。
 * 	buffer.mark()
 * 	//call buffer.get() a couple of times.
 * 	buffer.reset()//set position back to mark
 */
public class BufferStudy {

	public static void main(String[] args){
		try{
			testBuffer();
		}catch(Exception e){
			
		}
	}
	
	
	public static void testBuffer() throws IOException{
		RandomAccessFile aFile = new RandomAccessFile("/Users/allen/temp/CTA-软文-大号分享.txt","rw");
		FileChannel inChannel = aFile.getChannel();
		
		ByteBuffer buf = ByteBuffer.allocate(1024);
		while((inChannel.read(buf))!= -1){
			buf.flip();
			while(buf.hasRemaining()){
				System.out.println((char)buf.get());
			}
			
			buf.clear();
		}
		aFile.close();
	}
	
	
	
	
	
	
	
	
}
