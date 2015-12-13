package com.allenfancy.performancetuning.ch01.decorate;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.crypto.Data;

/**
 * 
 * @author allen
 *装饰者模式：
 *	组件接口				组件接口是装饰者和被装饰者的超类或者接口。它定义了被装饰者的核心功能和装饰者需要加强的功能点
 *	具体组件				具体组件实现了组件接口的核心方法，完成某一个具体的业务逻辑。它也是被装饰的对象	
 *	装饰者				实现组件接口，并持有一个具体的被装饰着对象
 *	具体装饰者			具体实现装饰的业务逻辑，即实现了被分离的各个增强功能点。各个具体装饰者是可以相互叠加的，从而可以构成一个个功能更强大的组件对象。
 *
 *核心思想：
 *	
 *
 *在JDK中的使用：
 *	在OutputStream FileOutputStream BufferedOutputStream DataOutputStream
 *  使用了对应的装饰模式
 *  
 *  将性能模块和功能模块分离的一种实现。
 */
public class Test {

	public static void main(String[] args){
		//IPacketCreator pc = new PacketHTTPHeaderCreator(new PacketHTMLHeaderCreator(new PacketBodyCreator()));
		//System.out.println(pc.handleContent());
		try {
			test();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void test() throws IOException{
		//带有缓冲功能的输出流对象
		DataOutputStream dataOutputStream = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream(new File("/Users/allen/temp/hello1.txt"))));
		//没有缓冲功能的输出流对象
		//DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(new File("/Users/allen/temp/hello1.txt")));
		long begin = System.currentTimeMillis();
		for(int i = 0 ; i < 10000000;i++)
			dataOutputStream.writeLong(i);
		System.out.println("spending : " +(System.currentTimeMillis() - begin));
	}
	
}
