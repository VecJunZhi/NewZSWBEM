package com.zst.test.rbac.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.Cookie;

/**
 * Executors��ʹ�ã�
 * sleep wait notify ����ʵ��˵��
 * 3 class Test
		{
		synchronized static void sayHello3()//�������ͬ������̬��һ���������ֽ���
				{
				
				}	
			
				synchronized void getX(){}//���������ͬ����ָ��ͬ���� ���ʹ�õĶ�������ͬһ���Ļ�����ͬ����������ǣ��Ͳ�ͬ����
		}

 * 4 ʲô���̣߳��߳�״̬��ʲô���߳����й���
 * һ�������п����ж���ִ������ͬʱִ�У�һ���߳̾��ǳ����е�һ��ִ��������ÿ���߳��϶�������Ҫִ�еĴ��룬�������ж�γ������ͬʱ���У�
 * ÿ���������ٶ���һ���̣߳���main����ִ�е��Ǹ��̡߳����ֻ��һ��cpu������ô�ܹ�ͬʱִ�ж�γ����أ����ǴӺ���������ģ�cpuһ��ִ��a������
 * һ��ִ��b�������л�ʱ��ܿ죬���˵ĸо���a,b��ͬʱִ�У��ñȴ����ͬһ���칫��������ֻ��һ�����ӵ��ⲿ���ߣ���ʵ����������һ��Ϊa�����ݣ�һ��Ϊb�����ݣ�
 * �����л�ʱ��ܶ��ݣ����ԣ���Ҹо�����ͬʱ������ 
  	״̬�����������У�synchronize������wait��sleep���𣬽�����wait������synchronized�ڲ����á�
  	�����̵߳�start�������߳̽������״̬���̵߳���ϵͳ������״̬���߳�תΪ����״̬������synchronized���ʱ��������״̬תΪ������
  	��synchronized�������������תΪ���У�������������Ե���wait����תΪ����״̬�����̹߳����Ĵ���ִ������̱߳�Ϊ����״̬��

 *5 synchronized �� Lock����������ϵ
 *  synchronzied�Զ��ͷ�����
 *  Lock��Ҫ�ֶ��ͷ�������finally���ͷ���
 * 
 * 
 * 6 ���߳�ѭ��10�Σ��������߳�ѭ��100�������ֻص����߳�ѭ��10�Σ������ٻص����߳���ѭ��100�����ѭ��50�Σ���д�����򡣣�
 * 
 * 
 */
public class ThreadJava {
	public static void main(String[] args) {
		//�̳߳���
		ExecutorService pool =Executors.newCachedThreadPool();
		for (int j = 0; j < 10; j++) {
			//System.out.println(j);
			pool.execute(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					//System.out.println("hellohellohellohellohellohellohellohello");
				}
			});
		}
		//
		final ThreadJava thread=new ThreadJava();
		for (int i = 0; i <10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(120);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					thread.shared2();
				}
			}).start();
		}
		
		for (int i = 0; i <15; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(120);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					shared();
				}
			}).start();
		}
	
		
		
		
	}
	public   static  void shared(){
		synchronized (Cookie.class) {
			String abc="hellohelloohelllllllllllllllsssssssssssssssssllllllllllll";
			for (int i = 0; i < abc.length(); i++) {
				System.out.print(abc.charAt(i));
			}
			System.out.println();
		}
	}
	public  synchronized  void shared2(){
		//synchronized (Cookie.class) {
			String abc="abcddsfgraefdsafffffffffffffffffffffffffffffffffgfgfadefgkj";
			for (int i = 0; i < abc.length(); i++) {
				System.out.print(abc.charAt(i));
			}
			System.out.println();
		//}
	}
	public void lock(){
		
	}

}
