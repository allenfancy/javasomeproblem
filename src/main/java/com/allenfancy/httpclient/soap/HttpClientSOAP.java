package com.allenfancy.httpclient.soap;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

/***
 * 使用common-httpclient 方式去调用SOAP方式，返回的信息是XML格式的string类型
 * @author HP
 *
 */
public class HttpClientSOAP {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String soapRequestData = "<?xml version=\"1.0\" encoding=\"utf-8\"?> "
				+ "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">"
				+ "<soap12:Body>" + "<Login xmlns=\"http://tempuri.org/\">"
				+ "<userName>treely_web</userName>"
				+ "<password>treely_web70</password>" + "</Login> "
				+ "</soap12:Body>" + "</soap12:Envelope>";
		
		System.out.println(soapRequestData);
		//设置PostMethod并且在里面设置地址，如果没有设置，则返回错误信息如：java.lang.IllegalArgumentException: host parameter is null
		PostMethod postMethod = new PostMethod("http://123.57.83.13:9090/WebPDSGet.asmx");

		// 然后把Soap请求数据添加到PostMethod中
		byte[] b = soapRequestData.getBytes("utf-8");
		InputStream is = new ByteArrayInputStream(b, 0, b.length);
		String headers = "application/soap+xml; charset=utf-8";
		RequestEntity re = new InputStreamRequestEntity(is, b.length,headers);
		postMethod.setRequestEntity(re);

		// 最后生成一个HttpClient对象，并发出postMethod请求
		HttpClient httpClient = new HttpClient();
		int statusCode = httpClient.executeMethod(postMethod);

		if (statusCode == 200) {
			System.out.println("调用成功！");
			//返回的是一个String的XML形式
			String soapResponseData = postMethod.getResponseBodyAsString();
			System.out.println(soapResponseData);
			//使用正则表达式中获取数据信息
			//也可以使用DOM4j等解析问题
			String strs = "<LoginResult>(.*?)</LoginResult>";
			Pattern p = Pattern.compile(strs);
			Matcher m = p.matcher(soapResponseData);
			StringBuffer sb = new StringBuffer();
			while (m.find()) {
				sb.append(m.group(1));
			}
			System.out.println(sb.toString());
		} else {
			System.out.println("调用失败！错误码：" + statusCode);
		}
	}

}
