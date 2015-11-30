package com.allenfancy.httpclient.HttpComponet;

import java.io.File;
import java.io.FileInputStream;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ClientChunkEncodedPost {

	public static void main(String[] args) throws Exception{
		if(args.length != 1){
			System.out.println("File path not given");
			System.exit(1);
		}
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try{
			HttpPost httpPost = new HttpPost("http://httpbin.org/post");
			File file = new File(args[0]);
			InputStreamEntity reqEntity = new InputStreamEntity(
					new FileInputStream(file),-1,ContentType.APPLICATION_OCTET_STREAM);
			reqEntity.setChunked(true);
			httpPost.setEntity(reqEntity);
			System.out.println("Executing request : " + httpPost.getRequestLine());
			CloseableHttpResponse response = httpClient.execute(httpPost);
			System.out.println("----------------");
			System.out.println(response.getStatusLine());
			System.out.println(EntityUtils.toString(response.getEntity()));
			response.close();
		}finally{
			httpClient.close();
		}
	}
}
