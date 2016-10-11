package com.zst.test.rbac.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.Cookie;

/**
 * Executors的使用：
 * sleep wait notify 方法实例说明
 * 3 class Test
		{
		synchronized static void sayHello3()//这个方法同步，静态锁一样都是类字节码
				{
				
				}	
			
				synchronized void getX(){}//这个方法不同步，指定同步锁 如果使用的对象锁是同一个的话，就同步，如果不是，就不同步。
		}

 * 4 什么是线程，线程状态是什么，线程运行过程
 * 一个程序中可以有多条执行线索同时执行，一个线程就是程序中的一条执行线索，每个线程上都关联有要执行的代码，即可以有多段程序代码同时运行，
 * 每个程序至少都有一个线程，即main方法执行的那个线程。如果只是一个cpu，它怎么能够同时执行多段程序呢？这是从宏观上来看的，cpu一会执行a线索，
 * 一会执行b线索，切换时间很快，给人的感觉是a,b在同时执行，好比大家在同一个办公室上网，只有一条链接到外部网线，其实，这条网线一会为a传数据，一会为b传数据，
 * 由于切换时间很短暂，所以，大家感觉都在同时上网。 
  	状态：就绪，运行，synchronize阻塞，wait和sleep挂起，结束。wait必须在synchronized内部调用。
  	调用线程的start方法后线程进入就绪状态，线程调度系统将就绪状态的线程转为运行状态，遇到synchronized语句时，由运行状态转为阻塞，
  	当synchronized获得锁后，由阻塞转为运行，在这种情况可以调用wait方法转为挂起状态，当线程关联的代码执行完后，线程变为结束状态。

 *5 synchronized 与 Lock的区别与联系
 *  synchronzied自动释放锁，
 *  Lock需要手动释放锁，在finally中释放锁
 * 
 * 
 * 6 子线程循环10次，接着主线程循环100，接着又回到子线程循环10次，接着再回到主线程又循环100，如此循环50次，请写出程序。？
 * 
 * 
 */
public class ThreadJava {
	public static void main(String[] args) {
		//线程池类
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
