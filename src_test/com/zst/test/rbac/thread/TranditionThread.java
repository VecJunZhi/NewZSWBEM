package com.zst.test.rbac.thread;

public class TranditionThread {

	public static void main(String[] args) {
		
		Thread thread =new Thread(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("a: "+Thread.currentThread().getName());
					System.out.println("b: "+this.getName());
				}
			}
		};//创建子类，不同于 Thread thread =new Thread()；//创建本类实例
		thread.start();
		
		
		Thread thread2 =new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("a: "+Thread.currentThread().getName());
				}
			}
		});
		thread2.start();
		
		//两种方式的区别：
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("runnable:"+Thread.currentThread().getName());
				}				
			}
		}){
			public void run() {
				while(true){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("thread: "+Thread.currentThread().getName());
				}
			};
		}.start();
	}
}
