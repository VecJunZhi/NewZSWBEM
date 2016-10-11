package com.zst.test.rbac.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ThreadLocalTest {
	private static Map<Thread, Integer> map = new HashMap<Thread, Integer>();
	private static ThreadLocal<Integer> x = new ThreadLocal<Integer>();
	private static ThreadLocal<MyThreadScopeData> myThreadScopeData = new ThreadLocal<MyThreadScopeData>();
	public static void main(String[] args) {
		//这种方法是局部变量，可以保证数据的正确性。
//		for (int i = 0; i < 3; i++) {
//			new Thread(new Runnable() {
//				
//				@Override
//				public void run() {
//					int data=new Random().nextInt();
//					System.out.println(Thread.currentThread().getName()+" random data is "+data);
//					new A().get(data);
//					new B().get(data);
//				}
//			}).start();
//		}
		//共享变量在线程间保证数据的正确性。
		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					int data=new Random().nextInt();
					System.out.println(Thread.currentThread().getName()+" random data is "+data);
					//map.put(Thread.currentThread(), data);//通过全局变量Map进行一个缓冲
					x.set(data);
					MyThreadScopeData my =new MyThreadScopeData();
					my.setName("name"+data);
					my.setSex("sex"+data);
					myThreadScopeData.set(my);
					new C().get();
					new D().get();
				}
			}).start();
		}
		
	}
	
	static class A{
		public void get(int data){
			System.out.println(Thread.currentThread().getName()+" get the data : "+data);
		}
	}
	static class B{
		public void get(int data){
			System.out.println(Thread.currentThread().getName()+" get the data : "+data);
		}
	}
	static class C{
		public void get(){
			int data=x.get();
			//System.out.println(Thread.currentThread().getName()+" get the data : "+data);
			MyThreadScopeData my =myThreadScopeData.get();
			System.out.println(Thread.currentThread().getName()+" name is "+my.getName()+" sex is "+my.getSex());
			
		}
	}
	static class D{
		public void get(){
			int data=x.get();
			//System.out.println(Thread.currentThread().getName()+" get the data : "+data);
			MyThreadScopeData my =myThreadScopeData.get();
			System.out.println(Thread.currentThread().getName()+" name is "+my.getName()+" sex is "+my.getSex());
		}
	}
}
class MyThreadScopeData{
	private String name;
	private String sex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
