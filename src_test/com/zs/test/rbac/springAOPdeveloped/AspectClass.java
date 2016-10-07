package com.zs.test.rbac.springAOPdeveloped;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

/**
 * ����������
 * @author zsjr
 *
 */
@Aspect
public class AspectClass {
	
	//������ǿ���ܲ���������(Ҳ�����е㣬���ã��ù�����������Щ�ط�)
	@Pointcut("execution(* com.zs.test.rbac.*.*.memcached*(..))")
	public void checkMemcached(){
		System.out.println("��黺�档����������");
	}
	@Pointcut("execution(* com.zs.common.web.controller.*.memcached*(..))")
	public void sendDDMessage(){
		System.out.println("����DD����");
	}
	//����ʲôʱ��ִ����ǿ���� 
	@Before("sendDDMessage()")
	private void beforeCheckMem() {
		// TODO Auto-generated method stub
		System.out.println("׼����黺��");
		

	}
	@After(value = "checkMemcached()")
	private void afterCheckMem() {
		// TODO Auto-generated method stub
		System.out.println("��������ɺ�");

	}
	@Around(value = "sendDDMessage()")
	private void aroundCheckMem(ProceedingJoinPoint point) {
		// TODO Auto-generated method stub
		System.out.println("memcTest����ִ��֮ǰ������");
		try {
			point.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("memcTest����ִ��֮��");
	}
	
	
	

}
