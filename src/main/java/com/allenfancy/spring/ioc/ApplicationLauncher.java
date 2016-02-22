package com.allenfancy.spring.ioc;

import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class ApplicationLauncher {

	public static void main(String[] args){
		BasicConfigurator.configure();
		BeanFactory container = new XmlBeanFactory(new ClassPathResource(""));
		container.getBean("");
	}
}
