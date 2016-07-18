package com.allenfancy.spring.orign;

import java.lang.reflect.Constructor;
import java.util.List;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class XBeanHelper {

	public static XmlBeanDefinitionReader createBeanDefinitionReader(
			FileSystemXmlApplicationContext1 fileSystemXmlApplicationContext1, BeanDefinitionRegistry registry,
			List xmlPreprocessors) {
		String version = "2.0";
		try {
			Class spring20Clazz = Class.forName("org.springframework.core.AttributeAccessorSupport");
			version = "2.0";
		} catch (ClassNotFoundException e) {
			version = "1.2.8";
		}

		String className = "org.apache.xbean.spring.context.v" + version.charAt(0) + ".XBeanXmlBeanDefinitionReader";
		try {
			Class cl = Class.forName(className);
			Constructor cstr = cl.getConstructor(
					new Class[] { SpringApplicationContext.class, BeanDefinitionRegistry.class, List.class });
			return (XmlBeanDefinitionReader) cstr
					.newInstance(new Object[] { fileSystemXmlApplicationContext1, registry, xmlPreprocessors });
		} catch (Exception e) {
			throw (IllegalStateException) new IllegalStateException("Could not find valid implmentation for:" + version)
					.initCause(e);
		}
	}
}
