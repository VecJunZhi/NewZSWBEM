package com.zs.rbac.core;

/**
 * 
 * @author JiaRui
 * @createTime 2015-11-14
 */
public class LoginBaseStatus {
	
	//�û����ͳ���
	public final static String ISUSERNAME = "userName";
	
	public final static String ISEMAIL = "eMail";
	
	public final static String ISMOBILE = "mobile";
	
	
	//��֤״̬����
	public final static String UNKNOWNACCOUNT = "unknownAccount";					//��Ч���û���
	
	public final static String LOCKEDACCOUNT = "lockedAccount";					//�˻�������
	
	public final static String AUTHENTICATIONERROR = "authenticationError";			//��֤ʧ��
	
	public final static String AUTHENTICATIONSUCCESS = "authenticationSuccess";			//��֤�ɹ�
	
}
