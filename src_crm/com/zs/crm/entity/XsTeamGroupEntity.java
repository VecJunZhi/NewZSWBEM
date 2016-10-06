package com.zs.crm.entity;

import java.io.Serializable;

public class XsTeamGroupEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String userId;
	private String userName;
	private String realName;
	private String mobile;
	private String teamGroupId;
	private String groupName;
	private String userLevelId;
	private String description;
	private String projectId;
	private String isProjectAdmin;
	private String userTeamId;
	private String userGUID;
	
	private String groupType;
	private String projectName;
	private String available;
	private String value;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTeamGroupId() {
		return teamGroupId;
	}
	public void setTeamGroupId(String teamGroupId) {
		this.teamGroupId = teamGroupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getUserLevelId() {
		return userLevelId;
	}
	public void setUserLevelId(String userLevelId) {
		this.userLevelId = userLevelId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getIsProjectAdmin() {
		return isProjectAdmin;
	}
	public void setIsProjectAdmin(String isProjectAdmin) {
		this.isProjectAdmin = isProjectAdmin;
	}
	public String getUserTeamId() {
		return userTeamId;
	}
	public void setUserTeamId(String userTeamId) {
		this.userTeamId = userTeamId;
	}
	public String getUserGUID() {
		return userGUID;
	}
	
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public void setUserGUID(String userGUID) {
		this.userGUID = userGUID;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
