package com.allenfancy.httpclient.method;

public class TestHttpClientMethod {

	public static void main(String[] args){
		HTTPPost http = new HTTPPost();
		String str = http.doGet("http://localhost:8080/contact/innerSystem/test/Get?name=吴涛");
		System.out.println(str);
		str = http.doDelete("http://localhost:8080/contact/innerSystem/test/delete?data=sssss");
		System.out.println(str);
		str = http.doPut("http://localhost:8080/contact/innerSystem/test/put");
		System.out.println(str);
		str = http.doPost("http://localhost:8080/contact/innerSystem/test/post");
		System.out.println(str);
		
	}
}
