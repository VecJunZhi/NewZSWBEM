package com.zst.test.rbac.javaRMI;

import java.io.Serializable;
import java.rmi.Remote;

public interface IHello extends Remote ,Serializable{
	
	public String sayHello(String name) throws java.rmi.RemoteException;
	
}
