package com.allenfancy.performancetuning.ch01.享元模式;
/**
 * @author allen
 * 享元模式
 * 角色			作用
 * 享元工厂		用以创建具体享元类，维护相同的享元对象。它保证相同的享元对象可以被系统共享。在其内部使用了类似于单例模式算法，当请求对象已经存在时，直接返回对象，不存在时，再创建对象
 * 抽象享元		定义需要共享的对象的业务接口，享元类被创建出来总是为了实现某些特定的业务逻辑，而抽象享元便定义这些逻辑的语义行为
 * 具体享元类		实现抽象享元类的接口，完成某一具体逻辑
 * 
 * 享元模式和对象池的最大不同在于：
 * 	享元对象是不可以相互替代的，他们各自都有各自的含义和用途；而对象池中的对象都是等价的。如数据库连接池中得数据连接对象。
 */
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IReportManager rm  = null;
		ReportManagerFactory rmf = new ReportManagerFactory();
		 rm = rmf.getFinancialReportManager("A");
		System.out.println(rm.createReport());
		 rm = rmf.getEmployeeReportManager("A");
		System.out.println(rm.createReport());
	}

}
