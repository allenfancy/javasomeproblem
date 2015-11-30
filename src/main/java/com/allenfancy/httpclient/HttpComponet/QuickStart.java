package com.allenfancy.httpclient.HttpComponet;

import java.util.ArrayList;
import java.util.List;


import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class QuickStart {

	public static void main(String [] args) throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try{
			//GET请求
			HttpGet httpGet = new HttpGet("http://httpbin.org/get");
			CloseableHttpResponse response1 = httpClient.execute(httpGet);
			System.out.println(response1.getStatusLine());
			HttpEntity entity = response1.getEntity();
			EntityUtils.consume(entity);
			
		}finally{
			httpClient.close();
		}
		
		
	}
}
