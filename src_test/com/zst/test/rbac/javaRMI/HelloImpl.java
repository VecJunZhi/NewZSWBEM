package com.zst.test.rbac.javaRMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import com.zst.test.rbac.javaFoundation.Polymorphic;


public class HelloImpl extends UnicastRemoteObject implements IHello {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected HelloImpl() throws RemoteException {
		super();
	}

	@Override
	public String sayHello(String name) throws RemoteException {
		return "hello i am  :"+name;
	}
	public static void main(String[] args) {
		try {
			IHello hello = new HelloImpl();
			LocateRegistry.createRegistry(1099);//���ϴ˳��򣬾Ϳ��Բ�Ҫ�ڿ���̨�Ͽ���RMI��ע�����1099��RMI������ӵ�Ĭ�϶˿�
            java.rmi.Naming.rebind("rmi://localhost:1099/hello", hello);
            System.out.print("Ready");
            new Polymorphic().Get();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
