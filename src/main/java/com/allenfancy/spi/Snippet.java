package com.allenfancy.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class Snippet {
  public static void main(String[] args) {
    ServiceLoader<IOperation> serviceLoader = ServiceLoader.load(IOperation.class);
    Iterator<IOperation> operationIterator = serviceLoader.iterator();
    while (operationIterator.hasNext()) {
      System.out.println(operationIterator.next());
      IOperation operation = operationIterator.next();
      System.out.println(operation.operation(6, 3));
    }
  }
}

