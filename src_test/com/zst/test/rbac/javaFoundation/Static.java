package com.zst.test.rbac.javaFoundation;

import org.junit.Test;


public class Static {

		//计算登录的人数，没创建一个实例代表一个用户
	private static int peopleCount=0;
	private static final int maxCount;//常量的两种赋值方式1
	static{
		maxCount=100;
	}
	private static final int minCount=10;//常量的两种赋值方式2
	
	public  Static(){//构造函数不能被static修饰
		peopleCount++;
		System.out.println(peopleCount);
	}
	public static void main(String[] args) {
		PeopleClient.createInstense();
		PeopleClient.createInstense();
		PeopleClient.createInstense();
		//new Static();
	}
	//数字格式化
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
	//自动装箱和拆箱
	public void autoBoxing(){
		//主类型可以自动包装成对象
		//对象自动拆箱成主类型。
		//类型间的相互转化
		Integer.parseInt("2");
		Boolean.parseBoolean("true");
		Double.parseDouble("420.236");
	}
	
}