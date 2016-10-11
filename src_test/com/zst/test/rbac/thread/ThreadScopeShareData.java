package com.zst.test.rbac.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ThreadScopeShareData {
	private static Map<Thread, Integer> map = new HashMap<Thread, Integer>();
	public static void main(String[] args) {
		//���ַ����Ǿֲ����������Ա�֤���ݵ���ȷ�ԡ�
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
		//����������̼߳䱣֤���ݵ���ȷ�ԡ�
		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					int data=new Random().nextInt();
					System.out.println(Thread.currentThread().getName()+" random data is "+data);
					map.put(Thread.currentThread(), data);//ͨ��ȫ�ֱ���Map����һ������
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
