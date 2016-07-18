package com.allenfancy.HtmlToImage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.fit.cssbox.demo.ImageRenderer;
import org.xml.sax.SAXException;

public class Demo3 {

	public static void main(String[] args) throws IOException, SAXException {
		// TODO Auto-generated method stub
		ImageRenderer imageRenderer = new ImageRenderer();
		System.out.println("start");
		String url = "file:///Users/allen/temp/untitled.html";
		FileOutputStream out = new FileOutputStream(new File("/Users/allen/temp/baidu.png"));
		imageRenderer.renderURL(url, out, ImageRenderer.TYPE_PNG);
		System.out.println("OK");
	}

}
