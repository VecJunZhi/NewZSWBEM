package com.zs.test.rbac.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 * 1 ʲô��RMI���������ʲô���⣿
 * 2 RMI �� RPC ����������ϵ��ʲô��
 * 3 ����RMI �Ĳ�������Щ��
 * 		1 ����һ��Զ�̽ӿڣ�����̳�Remote�ӿڣ�������ҪԶ�̵��õķ��������׳�RemoteException�쳣 
 * 		2 Զ�̵Ľӿڵ�ʵ��
 * 		3 ����RMIע�������RMI���񣬲���Զ�̶���ע�ᵽRMIע����С�
 * 		4 �ͻ��˲��ԣ��ڿͻ��˵���Զ�̶����ϵ�Զ�̷����������ؽ���� 
 * 4 RMI��dubbo����������ϵ�� 
 *
 */
public class RmiJava {
	public static void main(String[] args) {
		
	}

}
