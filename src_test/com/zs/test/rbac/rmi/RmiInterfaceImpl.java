package com.zs.test.rbac.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/** 
 * Զ�̵Ľӿڵ�ʵ�� 
*/ 
public class RmiInterfaceImpl extends UnicastRemoteObject implements RmiInterface { 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 
     * ��ΪUnicastRemoteObject�Ĺ��췽���׳���RemoteException�쳣���������Ĭ�ϵĹ��췽������д�����������׳�RemoteException�쳣 
     * 
     * @throws RemoteException 
     */ 
    public RmiInterfaceImpl() throws RemoteException { 
    } 

    /** 
     * �򵥵ķ��ء�Hello World��"���� 
     * 
     * @return ���ء�Hello World��"���� 
     * @throws java.rmi.RemoteException 
     */ 
    public String helloWorld() throws RemoteException { 
        return "Hello World!"; 
    } 

    /** 
     * һ���򵥵�ҵ�񷽷������ݴ��������������Ӧ���ʺ��� 
     * 
     * @param someBodyName ���� 
     * @return ������Ӧ���ʺ��� 
     * @throws java.rmi.RemoteException 
     */ 
    public String sayHelloToSomeBody(String someBodyName) throws RemoteException { 
        return "��ã�" + someBodyName + "!"; 
    } 
}
