package com.allenfancy.jdk.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class ReferenceDemo3 {

  public static void main(String[] args) {
    Object obj = new Object();
    ReferenceQueue<Object> refQueue = new ReferenceQueue<Object>();
    //虚引用
    PhantomReference<Object> phanRef = new PhantomReference<Object>(obj, refQueue);
    System.out.println(phanRef.get());
    System.out.println(refQueue.poll());
    obj = null;
    System.gc();
    System.out.println(phanRef.get());
    System.out.println(refQueue.poll());
  }
}
