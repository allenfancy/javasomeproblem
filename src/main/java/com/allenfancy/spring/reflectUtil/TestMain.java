package com.allenfancy.spring.reflectUtil;

import java.lang.reflect.Field;


public class TestMain {

	public static void main(String[] args) {
		System.out.println(findField(Perosn.class,"name",null));
	}
	
	private static Field findField(Class<?> clazz, String name, Class<?> type){
		Class<?> searchType = clazz;
		while(!Object.class.equals(searchType) && searchType != null){
			Field[] fields = searchType.getDeclaredFields();
			for(Field field : fields){
				if(name ==null || name.equals(field.getName())
						&& (type == null || type.equals(field.getType()))){
					return field;
				}
			}
			searchType = searchType.getSuperclass();
		}
		return null;
	}
}
