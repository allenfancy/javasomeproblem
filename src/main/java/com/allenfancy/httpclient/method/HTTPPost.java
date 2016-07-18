package com.allenfancy.httpclient.method;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.google.common.collect.Lists;
/**
 *<p>
 *HttpClient详解：
 *	HttpClinet有一个对链接初始化和终止，还有在活动链接上I/O操作的完整控制。而链接操作的很多方面可以使用一些参数来控制
 *  链接参数：
 *  	http.socket.timeout : 定义了套接字的毫秒超时时间(SO_TIMEOUT).在俩个连续的数据包之间最大的空闲时间。如果超时时间是0就解释为是一个无限大的超时时间。
 *      http.tcp.nodelay : 决定是否使用Nagle算法。Nagle算法视图通过最小化发送的分组数量来节省宽带。当应用程序希望降低网络延迟并提高性能时，可以关闭Nagle算法。
 * 		http.tcp.buffer-size : 决定内部套接字缓存使用的大小。如果这个参数没有被设置，那么HttpClient将会分配8192字节的套接字缓存
 * 	    http.socket.linger : 使用指定的秒数拖延时间来设置SO_LINGER。最大的链接超时值是平台指定的。值0暗示了这个选项是关闭的。值-1按时使用JRE默认的。
 *      http.connection.timeout: 决定直到链接建立时的毫秒级超时时间。超时时间的值为0解释为一个无限大的时间。
 *      
 *   安全的HTTP链接
 *   	如果信息在俩个不能由非认证的第三方进行读取或修改的终端之间的传输，http链接可以被认为是安全的。SSL/TLS协议是用来保证HTTP传输安全使用。而其他加密技术也可以被使用。
 *      HTTP传输是在SSL/TLS加密链接之上分层的。
 *      套接字工厂：
 *      	LayeredSocketFactory是SocketFactory接口的扩展。分层的套接字工厂可HTTP链接内部使用java.net.Socket对象来处理数据在线程路上的传输。它们依赖SocketFactory接口来创建，初始化和链接套接字。
 *      这会使得HttpClient的用户可以提供在运行时指定套接字初始化代码的应用程序。
 *      SSL/TLS的定制：
 *      	HttpClient使用SSLSocketFactory来创建SSL链接。SSLSocketFactory允许允许高度定制。它可以使用javax.net.SSLContext的实例作为参数。，
 *      并使用它来创建定制的SSL链接
 * </p>
 */
public class HTTPPost {
	
	public void testSSL_TLS(){
	}
	
	public void test() throws ConnectTimeoutException, UnknownHostException, IOException{
		PlainSocketFactory sf = PlainSocketFactory.getSocketFactory();
		Socket socket = sf.createSocket();
		HttpParams params = new BasicHttpParams();
		params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,1000L);
		sf.connectSocket(socket, "localhost", 8080, null,-1,params);
		
	}

	public  String doPost(String url) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		// 用于添加value值
		List<NameValuePair> paramList = Lists.newArrayList();
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
			HttpResponse resp = httpClient.execute(httpPost);
			int code = resp.getStatusLine().getStatusCode();
			if (code == HttpStatus.SC_OK) {
				String str = EntityUtils.toString(resp.getEntity(), "UTF-8");
				if (StringUtils.isNotEmpty(str)) {
					return str;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpPost.releaseConnection();
		}
		return null;
	}

	public String doPut(String url) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut(url);
		List<NameValuePair> paramList = Lists.newArrayList();
		try {
			httpPut.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
			HttpResponse resp = httpClient.execute(httpPut);
			int code = resp.getStatusLine().getStatusCode();
			if (code == HttpStatus.SC_OK) {
				String str = EntityUtils.toString(resp.getEntity(), "UTF-8");
				if (StringUtils.isNotEmpty(str)) {
					return str;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpPut.releaseConnection();
		}
		return null;
	}

	public String doGet(String url) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse resp = httpClient.execute(httpGet);
			int code = resp.getStatusLine().getStatusCode();
			if (code == HttpStatus.SC_OK) {
				String str = EntityUtils.toString(resp.getEntity(), "UTF-8");
				if (StringUtils.isNotEmpty(str)) {
					return str;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {

		} finally {
			httpGet.releaseConnection();
		}
		return null;
	}

	public String doDelete(String url) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpDelete httpDelete = new HttpDelete(url);
		try {
			HttpResponse resp = httpClient.execute(httpDelete);
			int code = resp.getStatusLine().getStatusCode();
			if (code == HttpStatus.SC_OK) {
				String str = EntityUtils.toString(resp.getEntity(), "UTF-8");
				if (StringUtils.isNotEmpty(str)) {
					return str;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			httpDelete.releaseConnection();
		}
	}
}
