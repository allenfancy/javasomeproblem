package com.allenfancy.httpclient.HttpComponet;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class ClientAbortMethod {

	public static void main(String[] args) throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://httpbin.org/get");
		System.out.println("Executing request : " + httpGet.getURI());
		CloseableHttpResponse response = httpClient.execute(httpGet);
		 System.out.println("----------------------------------------");
         System.out.println(response.getStatusLine());
         // Do not feel like reading the response body
         // Call abort on the request object
         httpGet.abort();
	}
}
