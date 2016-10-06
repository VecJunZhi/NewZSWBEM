package com.zs.rbac.entity;

/**
 * 用户角色关系
 * <p>User: zj
 * <p>Date: 15-9-5
 * <p>Version: 1.0
 */
public class RolePermission{

    private int rolePermissionID;
    private int roleID;
    private int permissionID;
	public int getRolePermissionID() {
		return rolePermissionID;
	}
	public void setRolePermissionID(int rolePermissionID) {
		this.rolePermissionID = rolePermissionID;
	}
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	public int getPermissionID() {
		return permissionID;
	}
	public void setPermissionID(int permissionID) {
		this.permissionID = permissionID;
	}
	public RolePermission(int roleID, int permissionID) {
		super();
		this.roleID = roleID;
		this.permissionID = permissionID;
	}
	public RolePermission() {
		super();
	}
    
   
	
}
