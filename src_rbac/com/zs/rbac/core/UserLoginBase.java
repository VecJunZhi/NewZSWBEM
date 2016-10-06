package com.zs.rbac.core;

/**
 * 用户登录实体
 * @author jiarui
 */
public class UserLoginBase {
	
	private String userName;

	private String password;
	
	private boolean rememberMe;			//记住我   自动登录
	
	
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
