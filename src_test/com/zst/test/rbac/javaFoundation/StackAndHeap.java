	package com.zst.test.rbac.javaFoundation;
/**
 * �Ѻ�ջ
 * �ѣ������ڶ��ڴ���
 * ջ��
 * 1 heap ��stack ����������ϵ
 * 	��java�ڴ��Ϊ���֣�һ���Ƕ��ڴ棬һ����ջ�ڴ棬
 * 		��������뵽һ������ʱ���Ὺ��һ���洢�ռ���ջ�ڴ��У���ŷ����ľֲ�������������ִ����ɺ󣬷�������������ջ�ᱻ�ͷţ��ֲ�������֮�ͷš�
 * 		���ڴ��д�Ŵ����Ķ��󣬵������еľֲ�������final���κ�Ҳ����ڶ��У�������ջ�С�
 *
 */
public class StackAndHeap {
	StackAndHeap heap=new StackAndHeap();//ʵ�����������������Ķ����У�λ�ڶ���
	//���ʵ�������Ǹ��Զ�������ã������úͶ����ڶ��ϡ�
	public static void main(String[] args) {
		
	}
	public static void heatpStack(){
		final int heap=9;//�������еľֲ�������final���κ�Ҳ����ڶ��У�������ջ�С�
		System.out.println(heap);
	}
	public static void stack(){
		
	}
}
