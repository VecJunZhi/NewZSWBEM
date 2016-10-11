package com.zst.test.rbac.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.beans.factory.annotation.Required;

public class ProxyJava {
	
	interface CommFace{
		void getName();
	}
	class Subject implements CommFace {

		@Override
		@Required
		public void getName() {
			// TODO Auto-generated method stub
			System.out.println("getName invoke...");
		}
		
	}
	class invokeHandle implements InvocationHandler{
		Subject subject;
		public invokeHandle(Subject subject){
			this.subject=subject;
		}
		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			try {
				System.out.println("开始前做点事情.....");
				method.invoke(subject, args);
				System.out.println("结束后做点事情......");
			} catch (Exception e) {
				// TODO: handle exception
			}
			return null;
		}
		
	}
	
	public  CommFace getPorxyInstance(Subject subject){
		return (CommFace)Proxy.newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(), new invokeHandle(subject));
	}
	public static void main(String[] args) {
		ProxyJava java = new ProxyJava();
		Subject subject =java.new Subject();
		java.getPorxyInstance(subject).getName();
	}

	
}
