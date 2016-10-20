package com.zs.test.rbac.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/** 
 * 客户端测试，在客户端调用远程对象上的远程方法，并返回结果。 
 * 
 * 如果客户端与服务端不再同一个工程中，就要将服务接口在客户端工程中重新定义，生成客户端的存根，紧接着定义客户端调用程序，直接调用存根中接口方法，就可以实现远程调用。
*/ 

public class RmiClient {
	public static void main(String[] args) {
		try { 
	        //在RMI服务注册表中查找名称为RHello的对象，并调用其上的方法 
	        RmiInterface rhello =(RmiInterface) Naming.lookup("rmi://localhost:8888/RHello"); 
	        System.out.println(rhello.helloWorld()); 
	        System.out.println(rhello.sayHelloToSomeBody("熔岩")); 
	    } catch (NotBoundException e) { 
	        e.printStackTrace(); 
	    } catch (MalformedURLException e) { 
	        e.printStackTrace(); 
	    } catch (RemoteException e) { 
	        e.printStackTrace();   
	    }
	}
     


}

