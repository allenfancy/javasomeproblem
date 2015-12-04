package com.allenfancy.spring.orign;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

public class FileSystemXmlApplicationContext1
		extends org.springframework.context.support.FileSystemXmlApplicationContext {

	private final List xmlPreprocessors;

	public FileSystemXmlApplicationContext1(String configLocation) throws BeansException {
	        this(new String[] {configLocation}, true, null, Collections.EMPTY_LIST);
	    }

	public FileSystemXmlApplicationContext1(String[] configLocations) throws BeansException {
	        this(configLocations, true, null, Collections.EMPTY_LIST);
	    }

	public FileSystemXmlApplicationContext1(String[] configLocations, boolean refresh) throws BeansException {
	        this(configLocations, refresh, null, Collections.EMPTY_LIST);
	    }

	public FileSystemXmlApplicationContext1(String[] configLocations, ApplicationContext parent) throws BeansException {
	        this(configLocations, true, parent, Collections.EMPTY_LIST);
	    }

	public FileSystemXmlApplicationContext1(String[] configLocations, boolean refresh, ApplicationContext parent) throws BeansException {
	        this(configLocations, refresh, parent, Collections.EMPTY_LIST);
	    }

	public FileSystemXmlApplicationContext1(String configLocation, List xmlPreprocessors) throws BeansException {
	        this(new String[] {configLocation}, true, null, xmlPreprocessors);
	    }

	public FileSystemXmlApplicationContext1(String[] configLocations, List xmlPreprocessors) throws BeansException {
	        this(configLocations, true, null, xmlPreprocessors);
	    }

	public FileSystemXmlApplicationContext1(String[] configLocations, boolean refresh, List xmlPreprocessors) throws BeansException {
	        this(configLocations, refresh, null, xmlPreprocessors);
	    }

	public FileSystemXmlApplicationContext1(String[] configLocations, ApplicationContext parent, List xmlPreprocessors) throws BeansException {
	        this(configLocations, true, parent, xmlPreprocessors);
	    }

	public FileSystemXmlApplicationContext1(String[] configLocations, boolean refresh, ApplicationContext parent, List xmlPreprocessors) throws BeansException {
	        super(configLocations, false, parent);
	        this.xmlPreprocessors = xmlPreprocessors;
	        if (refresh) {
	            refresh();
	        }
	    }

	protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException, IOException {
		XmlBeanDefinitionReader beanDefinitionReader = XBeanHelper.createBeanDefinitionReader(this, beanFactory,
				xmlPreprocessors);
		beanDefinitionReader.setResourceLoader(this);
		beanDefinitionReader.setEntityResolver(new ResourceEntityResolver(this));
		initBeanDefinitionReader(beanDefinitionReader);
		loadBeanDefinitions(beanDefinitionReader);
	}
	
	protected void loadBeanDefinitions(XmlBeanDefinitionReader reader) throws BeansException,IOException{
		Resource[] configResources = getConfigResources();
		if (configResources != null) {
			reader.loadBeanDefinitions(configResources);
		}
		String[] configLocations = getConfigLocations();
		if (configLocations != null) {
			reader.loadBeanDefinitions(configLocations);
		}
	}
}
