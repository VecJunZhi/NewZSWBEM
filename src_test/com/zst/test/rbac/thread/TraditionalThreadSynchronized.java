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
			synchronized (this/**������Ψһ�Ķ��������˶�����һ������**/) {//û���������� zhalihngxiuoaoxiang  ming�������
				for (int i = 0; i < len; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}
			
		}
		//ͬ����������������Ϊthis
		public synchronized void output2(String name){
			int len=name.length();
				for (int i = 0; i < len; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			
		}
	}
//��̬��ͬ��
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
			synchronized (Outputer2.class/**������Ψһ�Ķ��������˶�����һ������,��̬��ֻ���õ�class�ֽ�**/) {//û���������� zhalihngxiuoaoxiang  ming�������
				for (int i = 0; i < len; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}
			
		}
		//ͬ����������������Ϊthis
		public static synchronized void output2(String name){
			int len=name.length();
				for (int i = 0; i < len; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			
		}
	}
}
