package com.zs.test.rbac.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/** 
 * �ͻ��˲��ԣ��ڿͻ��˵���Զ�̶����ϵ�Զ�̷����������ؽ���� 
 * 
 * ����ͻ��������˲���ͬһ�������У���Ҫ������ӿ��ڿͻ��˹��������¶��壬���ɿͻ��˵Ĵ���������Ŷ���ͻ��˵��ó���ֱ�ӵ��ô���нӿڷ������Ϳ���ʵ��Զ�̵��á�
*/ 

public class RmiClient {
	public static void main(String[] args) {
		try { 
	        //��RMI����ע����в�������ΪRHello�Ķ��󣬲��������ϵķ��� 
	        RmiInterface rhello =(RmiInterface) Naming.lookup("rmi://localhost:8888/RHello"); 
	        System.out.println(rhello.helloWorld()); 
	        System.out.println(rhello.sayHelloToSomeBody("����")); 
	    } catch (NotBoundException e) { 
	        e.printStackTrace(); 
	    } catch (MalformedURLException e) { 
	        e.printStackTrace(); 
	    } catch (RemoteException e) { 
	        e.printStackTrace();   
	    }
	}
     


}

