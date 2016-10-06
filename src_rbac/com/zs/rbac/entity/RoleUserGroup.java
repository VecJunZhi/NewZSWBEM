package com.zs.rbac.entity;

public class RoleUserGroup {
	
	private String ruGID;
	private String roleID;
	private String groupID;

	public String getRuGID() {
		return ruGID;
	}

	public void setRuGID(String ruGID) {
		this.ruGID = ruGID;
	}

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String getGroupID() {
		return groupID;
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public RoleUserGroup(String ruGID, String roleID, String groupID) {
		super();
		this.ruGID = ruGID;
		this.roleID = roleID;
		this.groupID = groupID;
	}

	public RoleUserGroup() {
		super();
	}

	public RoleUserGroup(String roleID, String groupID) {
		super();
		this.roleID = roleID;
		this.groupID = groupID;
	}

}
