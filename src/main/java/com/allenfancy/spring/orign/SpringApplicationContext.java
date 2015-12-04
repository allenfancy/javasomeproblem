package com.allenfancy.spring.orign;

import java.util.List;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ResourceLoader;

public interface SpringApplicationContext extends ConfigurableApplicationContext,DisposableBean,
	ResourceLoader{

	void setDisplayName(String displayName);
	
	List getBeanFactoryPostProcessors();
	
	void setClassLoader(ClassLoader classLoader);
	
	ClassLoader getClassLoader();
}
