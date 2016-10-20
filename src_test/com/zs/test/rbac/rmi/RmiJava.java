package com.zs.test.rbac.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 * 1 什么是RMI，用来解决什么问题？
 * 2 RMI 与 RPC 的区别与联系是什么？
 * 3 开发RMI 的步骤有哪些？
 * 		1 定义一个远程接口，必须继承Remote接口，其中需要远程调用的方法必须抛出RemoteException异常 
 * 		2 远程的接口的实现
 * 		3 创建RMI注册表，启动RMI服务，并将远程对象注册到RMI注册表中。
 * 		4 客户端测试，在客户端调用远程对象上的远程方法，并返回结果。 
 * 4 RMI与dubbo的区别与联系？ 
 *
 */
public class RmiJava {
	public static void main(String[] args) {
		
	}

}
