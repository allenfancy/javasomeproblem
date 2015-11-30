package com.allenfancy.httpclient.httpClient;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

public class AsyncClientHttpExchange {

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException{
		CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
		try{
			httpClient.start();
			HttpGet request = new HttpGet("http://www.apache.org/");
			Future<HttpResponse> future = httpClient.execute(request, null);
			HttpResponse response = future.get();
			System.out.println("Response : " + response.getStatusLine());
			System.out.println("Shutting down");
		}finally{
			httpClient.close();
		}
		System.out.println("Done");
	}
}
