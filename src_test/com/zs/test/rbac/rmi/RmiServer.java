package com.zs.test.rbac.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RmiServer {
	 public static void main(String args[]) { 

         try { 
             //����һ��Զ�̶��� 
             RmiInterface rhello = new RmiInterfaceImpl(); 
             //���������ϵ�Զ�̶���ע���Registry��ʵ������ָ���˿�Ϊ8888����һ���ز����٣�JavaĬ�϶˿���1099�����ز���ȱ��һ����ȱ��ע����������޷��󶨶���Զ��ע����� 
             LocateRegistry.createRegistry(8888); 

             //��Զ�̶���ע�ᵽRMIע��������ϣ�������ΪRHello 
             //�󶨵�URL��׼��ʽΪ��rmi://host:port/name(����Э��������ʡ�ԣ���������д��������ȷ�ģ� 
             Naming.bind("rmi://localhost:8888/RHello",rhello); 
//             Naming.bind("//localhost:8888/RHello",rhello); 

             System.out.println(">>>>>INFO:Զ��IHello����󶨳ɹ���"); 
         } catch (RemoteException e) { 
             System.out.println("����Զ�̶������쳣��"); 
             e.printStackTrace(); 
         } catch (AlreadyBoundException e) { 
             System.out.println("�����ظ��󶨶����쳣��"); 
             e.printStackTrace(); 
         } catch (MalformedURLException e) { 
             System.out.println("����URL�����쳣��"); 
             e.printStackTrace(); 
         } 
     } 

}
