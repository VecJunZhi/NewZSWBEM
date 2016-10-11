package com.zst.test.rbac.javaFoundation;

public class AbstractClass {
	public static void main(String[] args) {
		//OneClass classs=new OneClass();//拥有私有构造函数的类不能被实例化
		//new Abstracts();//抽象类不能被实例化，只能集成并重写抽象方法
		SecondClass secondClass=new SecondClass();//除了在堆内存中获得一个SecondClass对象外，还包括一个object对象在里面。
		secondClass.getClass();//
		Object obj=new Object();
		obj.getClass();
	}
}
abstract class Abstracts{
	public abstract void oneMethod();
}
final class OneClass{
	private OneClass(){}
}
final class SecondClass ///extends OneClass//用final修饰的类不能被集成
{
	 
}