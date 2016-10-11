package com.zst.test.rbac.javaFoundation;


public   class Polymorphic {//类被pubic修饰，在不同的包中可以创建对象，否则不写只能在同一个包中创建对象，
//用final修饰不能再被集成。private 不能用来修饰类。
//
	public static void main(String[] args) {
		Parent firstSon=new FirstSon(1);//创建子类对象时候，所有被集成下来的构造函数都会被执行。
		//firstSon.say();
		Parent secondSon=new SecondSon();
		secondSon.say();
	}
	private  void Set(){//方法被private修饰意味着私有，只能在本类中被调用。其他的不能被调用
		System.out.println("poly set..");
	}
	public  void Get(){
		System.out.println("poly get..");
			Set();
	}
}
class Parent //extends Polymorphic
{
	public void say(){
		System.out.println("parent saying...");
		
	}
	public Parent(){
		System.out.println("parent constructor");
	}
	public Parent(int index){
		System.out.println("parent constructor "+index);
		
	}
	//类不能被继承：1拥有私有构造函数，2 用final修饰符修饰。
}
class FirstSon extends Parent{
	public void say(){
		System.out.println("FirstSon saying...");
		super.say();
	}
	private FirstSon(){//用private修饰的方法只能在本类中可以调用。
		System.out.println("firstSon constructor");
	}
	public  FirstSon(int index){
		//super(1);//如果不写的话，默认掉初始的super（）；。必须位于第一行
		//this();//从某一个构造函数调用同一个类的另一个构造函数，必须是第一行语句。super()与this()不能同时使用。
		System.out.println("firstSon constructor "+index);
		new FirstSon();
	}
}
class SecondSon extends Parent{
	public void say(){
		System.out.println("SecondSon saying...");
		new FirstSon(1);
	}
	public void talk(){
		System.out.println("SecondSon talking...");
	}
	
}