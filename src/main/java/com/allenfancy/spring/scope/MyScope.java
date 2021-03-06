package com.allenfancy.spring.scope;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class MyScope implements Scope{
	
	private final ThreadLocal threadScope = new ThreadLocal(){
		protected Object initialValue(){
			return new HashMap();
		}
	};

	public Object get(String name, ObjectFactory<?> objectFactory) {
		// TODO Auto-generated method stub
		Map scope = (Map) threadScope.get();
		Object object = scope.get(name);
		if(object == null){
			object = objectFactory.getObject();
			scope.put(name, object);
		}
		return object;
	}

	public Object remove(String name) {
		// TODO Auto-generated method stub
		Map scope = (Map) threadScope.get();
		return scope.remove(name);
	}

	public void registerDestructionCallback(String name, Runnable callback) {
		// TODO Auto-generated method stub
		
	}

	public Object resolveContextualObject(String key) {
		// TODO Auto-generated method stub
		
		return null;
	}

	public String getConversationId() {
		// TODO Auto-generated method stub
		return null;
	}

}
