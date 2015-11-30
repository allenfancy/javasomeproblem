package com.allenfancy.httpclient.httpClient;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

/**
 * 
 * @author allen
 *一个简单的异步请求
 */
public class AsyncClientProxyAuthentication {

	public static void main(String[] agrs) throws IOException, InterruptedException, ExecutionException{
		CredentialsProvider credProvider = new BasicCredentialsProvider();
		credProvider.setCredentials(new AuthScope("someproxy",8080), new UsernamePasswordCredentials("username","password"));
		CloseableHttpAsyncClient httpClient = HttpAsyncClients.custom()
				.setDefaultCredentialsProvider(credProvider)
				.build();
		try{
			httpClient.start();
			HttpHost proxy = new HttpHost("www.baidu.com",80);
			RequestConfig config = RequestConfig.custom()
					.setProxy(proxy)
					.build();
			HttpGet httpGet = new HttpGet("https://issues.apache.org/");
			httpGet.setConfig(config);;
			Future<HttpResponse> future = httpClient.execute(httpGet, null);
			HttpResponse response = future.get();
			System.out.println("Response : " + response.getStatusLine());
			System.out.println("Shutting down");
		}finally{
			httpClient.close();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
