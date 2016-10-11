	package com.zst.test.rbac.javaFoundation;
/**
 * 堆和栈
 * 堆：对象在堆内存中
 * 栈：
 * 1 heap 和stack 的区别与联系
 * 	答：java内存分为两种，一种是堆内存，一种是栈内存，
 * 		当程序进入到一个方法时，会开辟一个存储空间在栈内存中，存放方法的局部变量，当方法执行完成后，分配给这个方法的栈会被释放，局部变量随之释放。
 * 		堆内存中存放创建的对象，当方法中的局部变量用final修饰后，也存放在堆中，而不是栈中。
 *
 */
public class StackAndHeap {
	StackAndHeap heap=new StackAndHeap();//实例变量保存在所属的对象中，位于堆上
	//如果实例变量是个对对象的引用，则引用和对象都在堆上。
	public static void main(String[] args) {
		
	}
	public static void heatpStack(){
		final int heap=9;//当方法中的局部变量用final修饰后，也存放在堆中，而不是栈中。
		System.out.println(heap);
	}
	public static void stack(){
		
	}
}
