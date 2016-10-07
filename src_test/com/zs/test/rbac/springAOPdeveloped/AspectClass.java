package com.zs.test.rbac.springAOPdeveloped;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

/**
 * 定义切面类
 * @author zsjr
 *
 */
@Aspect
public class AspectClass {
	
	//定义增强功能并定义横截面(也就是切点，作用：该功能作用在哪些地方)
	@Pointcut("execution(* com.zs.test.rbac.*.*.memcached*(..))")
	public void checkMemcached(){
		System.out.println("检查缓存。。。。。。");
	}
	@Pointcut("execution(* com.zs.common.web.controller.*.memcached*(..))")
	public void sendDDMessage(){
		System.out.println("发送DD提醒");
	}
	//定义什么时候执行增强功能 
	@Before("sendDDMessage()")
	private void beforeCheckMem() {
		// TODO Auto-generated method stub
		System.out.println("准备检查缓存");
		

	}
	@After(value = "checkMemcached()")
	private void afterCheckMem() {
		// TODO Auto-generated method stub
		System.out.println("缓存检查完成后");

	}
	@Around(value = "sendDDMessage()")
	private void aroundCheckMem(ProceedingJoinPoint point) {
		// TODO Auto-generated method stub
		System.out.println("memcTest方法执行之前是是是");
		try {
			point.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("memcTest方法执行之后");
	}
	
	
	

}
