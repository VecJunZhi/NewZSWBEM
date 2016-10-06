package com.zs.crm.web.vo;

import java.io.Serializable;
import java.util.List;

public class XsTeamGroupVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String userName;
	private String property;
	private String userId;
	private String value;
	private String description;
	private String teamGroupId;
	private String projectId;
	private String groupName;
	private String isProjectAdmin;
	private String userLevelId;
	private int startLength;
	private int rows;
	private String sidx;
	private String sord;
	private String[] array;
	private List<XsTeamGroupVo> list;
	private String groupType;//2016.3.23
	private String projectName;
	private int available;
	private int length;
	private int startIndex;//增加分页2016.3.28
	private String mobileTel;//搜索条件
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getUserId() {
		return userId;
	}
	public String[] getArray() {
		return array;
	}
	public void setArray(String[] array) {
		this.array = array;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTeamGroupId() {
		return teamGroupId;
	}
	public void setTeamGroupId(String teamGroupId) {
		this.teamGroupId = teamGroupId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getIsProjectAdmin() {
		return isProjectAdmin;
	}
	public void setIsProjectAdmin(String isProjectAdmin) {
		this.isProjectAdmin = isProjectAdmin;
	}
	public String getUserLevelId() {
		return userLevelId;
	}
	public void setUserLevelId(String userLevelId) {
		this.userLevelId = userLevelId;
	}
	public int getStartLength() {
		return startLength;
	}
	public void setStartLength(int startLength) {
		this.startLength = startLength;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public List<XsTeamGroupVo> getList() {
		return list;
	}
	public void setList(List<XsTeamGroupVo> list) {
		this.list = list;
	}
	
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
	public String getMobileTel() {
		return mobileTel;
	}
	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}
	public XsTeamGroupVo() {
		super();
	}
	public XsTeamGroupVo(String userId, String description, String teamGroupId,
			String userLevelId) {
		super();
		this.userId = userId;
		this.description = description;
		this.teamGroupId = teamGroupId;
		this.userLevelId = userLevelId;
	}
	

}
