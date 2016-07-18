package com.allenfancy.jdk.reference;

import java.lang.ref.SoftReference;

public class ReferenceDemo2 {

  public static void main(String[] args) {
    Object obj = new Object();
    //软引用
    SoftReference<Object> softReference = new SoftReference<Object>(obj);
    System.out.println(softReference.get());
    System.out.println(softReference.enqueue());
    System.out.println(softReference.isEnqueued());
  }
}
