package com.allenfancy.jdk.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectDemo1 {

  public static void main(String[] args) {
    Class clazz = Person.class;
    Field[] fields = clazz.getDeclaredFields();
    for(Field f : fields){
      System.out.println(f.getName());
      Class<?> type = f.getType();
      System.out.println(type.getName());
    }
    Constructor[] css = clazz.getDeclaredConstructors();
    for(Constructor cs : css){
      Class<?>[] c1 = cs.getParameterTypes();
      for(Class cl : c1){
        System.out.println(cl.getSimpleName());
      }
    }
    Method[] ms = clazz.getDeclaredMethods();
    for(Method m : ms){
      System.out.println(m.getName());
    }
  }
}
