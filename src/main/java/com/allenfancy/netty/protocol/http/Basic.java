package com.allenfancy.netty.protocol.http;
/**
 * <p>
 * HTTP协议的主要特点：
 * 	1.支持Client/Server模式
 * 	2.简单-客户向服务器请求服务时，只需要指定服务URL，携带必要的请求参数或者消息体
 * 	3.灵活-HTTP允许传输任意类型的数据对象，传输的内容类型由HTTP消息头中的Content-Type加以标记
 *  4.无状态-HTTP协议是无状态协议，无状态是指协议对于事务处理没有记忆能力。
 * HTTP请求由三部分组成：
 * 	1.HTTP请求行
 * 	2.HTTP消息头
 * 	3.HTTP请求正文
 * 请求行以一个方法符开头，以空格分开，后面跟着请求的URI和协议的版本，格式为：Method Request-URI HTTP-Version CRLF。
 * 其中Method表示请求方法，Request-URI是一个统一资源标示符，HTTP-Version表示请求的HTTP协议版本，CRLF表示回车和换行。
 * 请求方法有多种，各方法的作用如下：
 * GET
 * POST
 * HEAD ： 请求获取由Request-URI所标识的资源的响应消息报头
 * PUT ： 请求服务器存储一个资源，并Request-URI作为其标识
 * DELETE
 * TRACE ： 请求服务器回送收到的请求信息，主要用于测试或者诊断
 * CONNECT
 * OPTIONS：请求查询服务器的性能，或者查询与资源相关的选项和需求
 * 
 * </p>
 * 
 *
 */
public class Basic {

}
