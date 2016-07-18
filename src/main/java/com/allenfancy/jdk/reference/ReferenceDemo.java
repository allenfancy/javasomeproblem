package com.allenfancy.jdk.reference;

public class ReferenceDemo {

  public int i;

  public static void main(String[] args) {
    ReferenceDemo rd = new ReferenceDemo();
    rd.i = 0;
    System.out.println("Before changeInteger :" + rd.i);
    changeInteger(rd);
    System.out.println("After changeInteger :" + rd.i);

    rd.i = 0;
    System.out.println("Before changeReference:" + rd.i);
    changeReference(rd);
    System.out.println("After changeReference:" + rd.i);

  }

  public static void changeReference(ReferenceDemo rd) {
    //新建了一个引用
    rd = new ReferenceDemo();
    rd.i = 5;
    System.out.println("In changeReference : " + rd.i);
  }

  public static void changeInteger(ReferenceDemo rd) {
    rd.i = 5;
    System.out.println("In changeInteger : " + rd.i);
  }
}
