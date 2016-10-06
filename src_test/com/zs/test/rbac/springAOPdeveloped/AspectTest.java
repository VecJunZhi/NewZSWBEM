package com.zs.test.rbac.springAOPdeveloped;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class AspectTest {
	
	public static void main(String[] args) {
		BeanFactory factory=new ClassPathXmlApplicationContext("spring-aop-develop.xml");
		AimClass control=(AimClass)factory.getBean("sysCon");
		control.memcachedTest();
	}

}
