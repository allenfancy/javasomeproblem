package com.allenfancy.email;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

import com.focussend.FocusEmail;
import com.focussend.FocusReceiver;
import com.focussend.FocusReport;
import com.focussend.FocusSendWebServiceSoapProxy;
import com.focussend.FocusTask;
import com.focussend.FocusUser;

public class WebServiceCaller {

	public static void main(String[] args) {
		// TODO
		FocusSendWebServiceSoapProxy uid = new FocusSendWebServiceSoapProxy();
		FocusUser fUser = new FocusUser();
		fUser.setEmail("xxxx.XXX@YYYYY.com");

		String pwd = "XXXX";
		fUser.setPassword(pwd);

		// 要发送的内容
		FocusEmail fEmail = new FocusEmail();
		fEmail.setBody("http://www.baidu.com");
		fEmail.setIsBodyHtml(true);

		// 一次发送作为一个任务,存储一些其他发送信息
		FocusTask fTask = new FocusTask();
		// 主题
		fTask.setSubject("test subject(AD)");
		// 任务名，建立存入数据库中
		//fTask.setTaskName("");
		// 设置回复人地址
		fTask.setReplyEmail("371200745@qq.com");
		fTask.setReplyName("fancy");
		
		String taskName = "allenTest-" +System.currentTimeMillis();
		fTask.setTaskName(taskName);
		System.out.println(taskName);
		// 发件人邮件
		fTask.setSenderEmail("sender@inboundmarketing.cn");
		fTask.setSenderName("allen");
		// 发件时间
		fTask.setSendDate(Calendar.getInstance());
		fTask.setDescription("This is Description");
	
		
		

		//
		ArrayList list = new ArrayList();

		FocusReceiver receiver = new FocusReceiver();
		receiver.setEmail("allen_520_fancy@163.com");
		list.add(receiver);

		receiver = new FocusReceiver();
		receiver.setEmail("598717394@qq.com");
		receiver.setCompany("春雨技术");
		receiver.setFullName("allen技术");
		receiver.setPhone("13122119298");
		receiver.setCity("SH");
		receiver.setReserved1("1334");
		list.add(receiver);

		receiver = new FocusReceiver();
		receiver.setEmail("allen.wu@babailiren.com");
		list.add(receiver);

		int size = list.size();

		String result = null;
		try {
				result = uid.batchSend(fUser, fEmail, fTask, (FocusReceiver[]) list.toArray(new FocusReceiver[size]));
				System.out.println(result);
				
		} catch (RemoteException exp) {

		}
	}
}
