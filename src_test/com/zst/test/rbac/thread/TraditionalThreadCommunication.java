package com.zst.test.rbac.thread;

public class TraditionalThreadCommunication {
	public static void main(String[] args) {
		final SynchClass synch=new SynchClass();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				/*for (int i = 0; i < 10; i++) {
					System.out.println("sub thread sequece of "+i);
				}*/
				for (int i = 0; i < 5; i++) {
					synch.subinit(5);
					
				}
				
			}
		}).start();
		new  Thread(new Runnable() {
			
			@Override
			public void run() {
				/*for (int i = 0; i < 100; i++) {
					System.out.println("main thread sequece of "+i);
				}*/
				for (int i = 0; i < 5; i++) {
					synch.maininit(10);
				}
				
			}
		}).start();
	}
}
//将要同步的代码写在一个资源（一个类中）
class SynchClass{
	private boolean flg=true;
	public synchronized void subinit(int k){
		while(!flg){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = 0; i < k; i++) {
			System.out.println("sub thread sequece of "+i);
		}
		flg=false;
		this.notify();
	}
	public synchronized void maininit(int k){
		while(flg){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = 0; i < k; i++) {
			System.out.println("main thread sequece of "+i);
		}
		flg=true;
		this.notify();
	}
}