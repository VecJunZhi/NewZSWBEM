package com.zs.rbac.core;

/**
 * �û���¼ʵ��
 * @author jiarui
 */
public class UserLoginBase {
	
	private String userName;

	private String password;
	
	private boolean rememberMe;			//��ס��   �Զ���¼
	
	
	public boolean isRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
