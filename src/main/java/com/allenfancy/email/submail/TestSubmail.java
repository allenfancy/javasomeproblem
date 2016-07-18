package com.allenfancy.email.submail;

import com.allenfancy.email.submail.util.ConfigLoader;
import com.babailiren.config.AppConfig;
import com.babailiren.service.MailSend;
import com.babailiren.service.MailXSend;
import com.babailiren.service.MessageXsend;

public class TestSubmail {

	public static void main(String[] args){
		testSendMail();
	}
	
	public static void testSendMail(){
		AppConfig config = ConfigLoader.load(ConfigLoader.ConfigType.Mail);
		MailSend submail = new MailSend(config);
		submail.addTo("598717394@qq.com","allen");
		submail.addCc("allen.wu@mktkey.com", "allen.wu");
		submail.addBcc("leo@drinkfans.com", "");
		submail.setSender("allen_520_fancy@163.com","吴涛");
		submail.setReply("371200745@qq.com");
		submail.setSubject("test SDK");
		submail.setText("test SDK text");
		submail.setHtml("test SDK html @var(name),@var(age) <a href=\"var://@link(test)\">testLink</a> <a href=\"var://@link(test2)\">testLink2</a>");
		submail.addVar("name", "leo");
		submail.addVar("age", "32");
		submail.addLink("developer", "http://submail.cn/chs/developer");
		submail.addLink("store", "http://submail.cn/chs/store");
		submail.addHeaders("X-Accept", "zh-cn");
		submail.addHeaders("X-Mailer", "leo App");
		submail.send();
	}
	
	public static void testXSendMail(){
		AppConfig config = ConfigLoader.load(ConfigLoader.ConfigType.Mail);
		MailXSend submail = new MailXSend(config);
		submail.addTo("leo@submail.cn","leo");
//		submail.setSender("no-reply@submail.cn","SUBMAIL");
		submail.setProject("uigGk1");
		submail.addVar("name", "leo");
		submail.addVar("age", "32");
		submail.addLink("developer", "http://submail.cn/chs/developer");
		submail.addLink("store", "http://submail.cn/chs/store");
		submail.addHeaders("X-Accept", "zh-cn");
		submail.addHeaders("X-Mailer", "leo App");
		submail.xsend();
	}
	
	public static void testXSendMessage(){
		AppConfig config = ConfigLoader.load(ConfigLoader.ConfigType.Message);
		MessageXsend submail = new MessageXsend(config);
		submail.addTo("13122119298");
		submail.setProject("MApf82");
		submail.addVar("code", "a你好aaaa");
		submail.xsend();
	}
}
