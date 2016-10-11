package com.zst.test.rbac.javaRMI.rmiClient;

import java.rmi.Naming;

import com.zst.test.rbac.javaRMI.IHello;


public class Hello_RMI_Client {
	public static void main(String[] args) {
		try {
			 IHello hello = (IHello) Naming.lookup("rmi://localhost:1099/hello");
             System.out.println(hello.sayHello("zhangxianxin"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
