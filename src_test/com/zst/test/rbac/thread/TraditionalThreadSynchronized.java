package com.zst.test.rbac.thread;


public class TraditionalThreadSynchronized {
	
	public static void main(String[] args) {
		//new TraditionalThreadSynchronized().init();
		new TraditionalThreadSynchronized().init2();
	}
	private void init(){
		final Outputer outputer=new Outputer();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					outputer.output("zhangxiaoxiang");
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					outputer.output2("lihuoming");
					//new Outputer().output("lihuoming");
				}
			}
		}).start();
	}
	class Outputer{
		public void output(String name){
			int len=name.length();
			synchronized (this/**必须是唯一的对象，所有人都用这一个对象**/) {//没有他，出现 zhalihngxiuoaoxiang  ming这种情况
				for (int i = 0; i < len; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}
			
		}
		//同步到方法上锁对象为this
		public synchronized void output2(String name){
			int len=name.length();
				for (int i = 0; i < len; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			
		}
	}
//静态类同步
	private void init2(){
		final Outputer2 outputer=new Outputer2();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					outputer.output("zhangxiaoxiang");
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					Outputer2.output2("lihuoming");
					//new Outputer().output("lihuoming");
				}
			}
		}).start();
	}
	static class Outputer2{
		public void output(String name){
			int len=name.length();
			synchronized (Outputer2.class/**必须是唯一的对象，所有人都用这一个对象,静态的只能用到class字节**/) {//没有他，出现 zhalihngxiuoaoxiang  ming这种情况
				for (int i = 0; i < len; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}
			
		}
		//同步到方法上锁对象为this
		public static synchronized void output2(String name){
			int len=name.length();
				for (int i = 0; i < len; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			
		}
	}
}
