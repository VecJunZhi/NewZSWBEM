package com.zst.test.rbac.javaFoundation;

public class AbstractClass {
	public static void main(String[] args) {
		//OneClass classs=new OneClass();//ӵ��˽�й��캯�����಻�ܱ�ʵ����
		//new Abstracts();//�����಻�ܱ�ʵ������ֻ�ܼ��ɲ���д���󷽷�
		SecondClass secondClass=new SecondClass();//�����ڶ��ڴ��л��һ��SecondClass�����⣬������һ��object���������档
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
final class SecondClass ///extends OneClass//��final���ε��಻�ܱ�����
{
	 
}