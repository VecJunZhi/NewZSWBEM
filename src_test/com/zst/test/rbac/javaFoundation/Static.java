package com.zst.test.rbac.javaFoundation;

import org.junit.Test;


public class Static {

		//�����¼��������û����һ��ʵ������һ���û�
	private static int peopleCount=0;
	private static final int maxCount;//���������ָ�ֵ��ʽ1
	static{
		maxCount=100;
	}
	private static final int minCount=10;//���������ָ�ֵ��ʽ2
	
	public  Static(){//���캯�����ܱ�static����
		peopleCount++;
		System.out.println(peopleCount);
	}
	public static void main(String[] args) {
		PeopleClient.createInstense();
		PeopleClient.createInstense();
		PeopleClient.createInstense();
		//new Static();
	}
	//���ָ�ʽ��
	@Test
	public void  dataFormat(){
		String string=String.format("%,d",1000000000);
		System.out.println(string);
	}
	
}
class PeopleClient{
	public static PeopleClient createInstense(){
		return new PeopleClient();
	}
	
}

class AutoBoxing{
	//�Զ�װ��Ͳ���
	public void autoBoxing(){
		//�����Ϳ����Զ���װ�ɶ���
		//�����Զ�����������͡�
		//���ͼ���໥ת��
		Integer.parseInt("2");
		Boolean.parseBoolean("true");
		Double.parseDouble("420.236");
	}
	
}