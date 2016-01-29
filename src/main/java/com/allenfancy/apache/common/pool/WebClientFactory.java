package com.allenfancy.apache.common.pool;

import org.apache.commons.pool.BaseKeyedPoolableObjectFactory;

import com.gargoylesoftware.htmlunit.WebClient;

public class WebClientFactory extends BaseKeyedPoolableObjectFactory<WebClient, WebClient>{

	@Override
	public WebClient makeObject(WebClient key) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
