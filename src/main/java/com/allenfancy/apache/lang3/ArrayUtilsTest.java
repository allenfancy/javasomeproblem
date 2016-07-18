package com.allenfancy.apache.lang3;

import java.lang.reflect.Field;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.apache.commons.lang3.reflect.Typed;

public class ArrayUtilsTest {

	public static void main(String[] args)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		try{
		String[] array = new String[] { "a", "b" };
		System.out.println(ArrayUtils.toString(array));
		System.out.println(ArrayUtils.hashCode(array));
		Person p = new Person();
		System.out.println(p.getClass().getName());
		
		Field[] feilds = p.getClass().getDeclaredFields();
		//属性
		for (int i = 0; i < feilds.length; i++) {
			Field f = feilds[i];
			if(!f.isAccessible()){
				f.setAccessible(true);
				System.out.println(f.getName());
				System.out.println(f.getType());
				f.setAccessible(false);
				
			}
		}
		System.out.println(p.getId());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
