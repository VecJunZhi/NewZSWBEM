package com.zs.rbac.core;

/**
 * 
 * @author JiaRui
 * @createTime 2015-11-14
 */
public class LoginBaseStatus {
	
	//用户类型常量
	public final static String ISUSERNAME = "userName";
	
	public final static String ISEMAIL = "eMail";
	
	public final static String ISMOBILE = "mobile";
	
	
	//认证状态常量
	public final static String UNKNOWNACCOUNT = "unknownAccount";					//无效的用户名
	
	public final static String LOCKEDACCOUNT = "lockedAccount";					//账户被锁定
	
	public final static String AUTHENTICATIONERROR = "authenticationError";			//认证失败
	
	public final static String AUTHENTICATIONSUCCESS = "authenticationSuccess";			//认证成功
	
}
