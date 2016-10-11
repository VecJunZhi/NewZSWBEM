package com.zst.test.rbac.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ThreadScopeShareData {
	private static Map<Thread, Integer> map = new HashMap<Thread, Integer>();
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
					map.put(Thread.currentThread(), data);//通过全局变量Map进行一个缓冲
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
			int data=map.get(Thread.currentThread());
			System.out.println(Thread.currentThread().getName()+" get the data : "+data);
		}
	}
	static class D{
		public void get(){
			int data=map.get(Thread.currentThread());
			System.out.println(Thread.currentThread().getName()+" get the data : "+data);
		}
	}
}
