package com.allenfancy.jdk.reflect;

public class Person {

  private String name;
  private int age;
  private String address;
  private double money;
  private long end;
  private char character;
  private float f;

  public Person() {
    // TODO Auto-generated constructor stub
  }
  public Person(String name,String address,long end){
    this.name = name;
    this.address = address;
    this.end  = end;
  }
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public double getMoney() {
    return money;
  }

  public void setMoney(double money) {
    this.money = money;
  }

  public long getEnd() {
    return end;
  }

  public void setEnd(long end) {
    this.end = end;
  }

  public char getCharacter() {
    return character;
  }

  public void setCharacter(char character) {
    this.character = character;
  }

  public float getF() {
    return f;
  }

  public void setF(float f) {
    this.f = f;
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append(this.name).append(",").append(this.address);
    return sb.toString();

  }
}
