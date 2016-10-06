package com.zs.rbac.entity;

/**
 * 用户角色关系
 * <p>
 * User: zj
 * <p>
 * Date: 14-1-28
 * <p>
 * Version: 1.0
 */
public class UserRole {

	private int urID;
	private int userID;
	private int roleID;
	public int getUrID() {
		return urID;
	}
	public void setUrID(int urID) {
		this.urID = urID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	public UserRole() {
		super();
	}
	public UserRole(int userID, int roleID) {
		super();
		this.userID = userID;
		this.roleID = roleID;
	}
	
}
