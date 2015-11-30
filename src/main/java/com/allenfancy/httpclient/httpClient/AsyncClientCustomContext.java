package com.allenfancy.httpclient.httpClient;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

public class AsyncClientCustomContext {

	public static void main(String[] args) throws IOException{
		CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
		try{
			//创建一个本地cookie实例存储
			CookieStore cookieStore = new BasicCookieStore();
			//创建本地Http context
			HttpClientContext localContext = HttpClientContext.create();
			localContext.setCookieStore(cookieStore);
			HttpGet httpGet = new HttpGet("http://www.baidu.com");
			System.out.println("Executing request:" + httpGet.getRequestLine());
			
			httpClient.start();
			Future<HttpResponse> future = httpClient.execute(httpGet, localContext,null);
			HttpResponse response = future.get();
			System.out.println("Response: " + response.getStatusLine());
			
			List<Cookie> cookies = cookieStore.getCookies();
			for(int i = 0; i < cookies.size() ; i++){
				System.out.println("Local cookie :" + cookies.get(i));
			}
			System.out.println("Shutting down");
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			httpClient.close();
		}
	}
}
