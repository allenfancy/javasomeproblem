package com.allenfancy.httpclient.HttpComponet;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;

import javax.net.ssl.SSLContext;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultHttpResponseParser;
import org.apache.http.impl.conn.DefaultHttpResponseParserFactory;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.HttpMessageParserFactory;
import org.apache.http.io.HttpMessageWriterFactory;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.LineParser;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;

public class ClientConfiguration {

	public static void main(String[] args) throws Exception {
		HttpMessageParserFactory<HttpResponse> responseParserFactory = new DefaultHttpResponseParserFactory() {
			public HttpMessageParser<HttpResponse> create(SessionInputBuffer buffer, MessageConstraints constraints) {
				LineParser lineParser = new BasicLineParser() {

					public Header parserHeader(final CharArrayBuffer buffer) {
						try {
							return super.parseHeader(buffer);
						} catch (ParseException e) {
							return new BasicHeader(buffer.toString(), null);
						}
					}
				};
				return new DefaultHttpResponseParser(buffer, lineParser, DefaultHttpResponseFactory.INSTANCE,
						constraints) {
					protected boolean reject(final CharArrayBuffer line, int count) {
						return false;
					}
				};
			}
		};

		HttpMessageWriterFactory<HttpRequest> requestWriterFactory = new DefaultHttpRequestWriterFactory();

		/**
		 * 使用自定义链接工厂定制的传输HTTP链接的初始化过程，
		 * 除了标准的链接配置参数的HTTP链接工厂可以定义消息
		 * 解析器/写入
		 * 由单个链接使用。
		 */
		HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory = new ManagedHttpClientConnectionFactory(
				requestWriterFactory, responseParserFactory);

		/**
		 * 当完全吃书画可以绑定到客户端的HTTP链接对象
		 * 任意网络套接字。网络接口初始化的过程中
		 * 它链接到远程地址，并绑定到本地一个是控制
		 * 由套接字链接工厂
		 * 
		 * 
		 * 用于安全链接的SSL上下文可以创建任何根据
		 * 系统或应用程序特定的属性
		 * 
		 */
		SSLContext sslContext = SSLContexts.createSystemDefault();

		/**
		 * 创建一个注册表的自定义连接套接字工厂 支持的协议方案
		 */
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", PlainConnectionSocketFactory.INSTANCE)
				.register("https", new SSLConnectionSocketFactory(sslContext)).build();
		DnsResolver dnsResolver = new SystemDefaultDnsResolver(){
			public InetAddress[] resolve(final String host) throws UnknownHostException{
				if(host.equalsIgnoreCase("myhost")){
					return new InetAddress[]{
							InetAddress.getByAddress(new byte[]{127,0,0,1})
					};
				}else{
					return super.resolve(host);
				}
			}
		};
		
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(
				socketFactoryRegistry,connFactory,dnsResolver);
		//create a connection manaer with custom configuration
		
		//create socket configuration
		SocketConfig socketConfig = SocketConfig.custom()
				.setTcpNoDelay(true)
				.build();
		
		//配置连接管理器使用的插槽配置无论是默认或特定主机。
		connManager.setDefaultSocketConfig(socketConfig);
		connManager.setSocketConfig(new HttpHost("somehost",80), socketConfig);
		//
		connManager.setValidateAfterInactivity(1000);
		
		MessageConstraints messageConstraints = MessageConstraints.custom()
				.setMaxHeaderCount(200)
				.setMaxLineLength(2000)
				.build();
		//
		ConnectionConfig connectionConfig = ConnectionConfig.custom()
				.setMalformedInputAction(CodingErrorAction.IGNORE)
				.setUnmappableInputAction(CodingErrorAction.IGNORE)
				.setCharset(Consts.UTF_8)
				.setMessageConstraints(messageConstraints)
				.build();
		connManager.setDefaultConnectionConfig(connectionConfig);
		connManager.setConnectionConfig(new HttpHost("somehost",80),ConnectionConfig.DEFAULT);
		
	}
}
