package com.allenfancy.jdk.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class ReferenceDemo1 {

  public static void main(String[] args) {
    Object obj = new Object();
    ReferenceQueue<Object> refQueue = new ReferenceQueue<Object>();
    //弱引用
    WeakReference<Object> weakRef = new WeakReference<Object>(obj,refQueue);
    System.out.println(weakRef.get());
    System.out.println(refQueue.poll());
    obj = null;
    System.gc();
    System.out.println(weakRef.get());
    System.out.println(refQueue.poll());
  }
}
