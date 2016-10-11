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
			LocateRegistry.createRegistry(1099);//加上此程序，就可以不要在控制台上开启RMI的注册程序，1099是RMI服务监视的默认端口
            java.rmi.Naming.rebind("rmi://localhost:1099/hello", hello);
            System.out.print("Ready");
            new Polymorphic().Get();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
