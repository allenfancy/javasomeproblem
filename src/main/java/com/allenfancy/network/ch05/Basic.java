package com.allenfancy.network.ch05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Basic {

	public static void main(String[] args) throws IOException {
		URL url = new URL("https://www.baidu.com/");
		URLConnection u = url.openConnection();
		System.out.println(u.getContentEncoding());
		System.out.println(u.getContentLength());
		System.out.println(u.getURL());
		System.out.println(u.getExpiration());
		System.out.println(u.getContent());
		InputStream in = u.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String str = null;
		while((str = reader.readLine())!= null){
			System.out.println(str);
		}
		
	}
}
