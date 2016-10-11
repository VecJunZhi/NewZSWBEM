package com.zst.test.rbac.javaFoundation;


public   class Polymorphic {//�౻pubic���Σ��ڲ�ͬ�İ��п��Դ������󣬷���дֻ����ͬһ�����д�������
//��final���β����ٱ����ɡ�private �������������ࡣ
//
	public static void main(String[] args) {
		Parent firstSon=new FirstSon(1);//�����������ʱ�����б����������Ĺ��캯�����ᱻִ�С�
		//firstSon.say();
		Parent secondSon=new SecondSon();
		secondSon.say();
	}
	private  void Set(){//������private������ζ��˽�У�ֻ���ڱ����б����á������Ĳ��ܱ�����
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
	//�಻�ܱ��̳У�1ӵ��˽�й��캯����2 ��final���η����Ρ�
}
class FirstSon extends Parent{
	public void say(){
		System.out.println("FirstSon saying...");
		super.say();
	}
	private FirstSon(){//��private���εķ���ֻ���ڱ����п��Ե��á�
		System.out.println("firstSon constructor");
	}
	public  FirstSon(int index){
		//super(1);//�����д�Ļ���Ĭ�ϵ���ʼ��super������������λ�ڵ�һ��
		//this();//��ĳһ�����캯������ͬһ�������һ�����캯���������ǵ�һ����䡣super()��this()����ͬʱʹ�á�
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