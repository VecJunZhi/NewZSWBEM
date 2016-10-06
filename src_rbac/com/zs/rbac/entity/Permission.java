package com.zs.rbac.entity;

import java.io.Serializable;

/**
 * <p>User: zj
 * <p>Date: 15-9-5
 * <p>Version: 1.0
 */
public class Permission implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int permissionID;
    private String permissionMark; //权限标识 程序中判断使用,如"user:create"
    private String description; //权限描述性语言
    private Boolean available = Boolean.FALSE; //是否可用,如果不可用将不会添加给用户
    private String permissionName;//权限名称，,UI界面显示使用
    private String permissionType;//资源类型，用于功能分类         
    private String parentID; //用于模块--菜单--功能之间的关联       
    private String url;//存储URL，根据权限动态加载URL，来区分不同权限的页面显示        
    private int priority;//优先级            
    private String createTime;//创建时间               
    private String lastTime;//上次时间  
    
    private int rows;
    private int startLength;
    private String sidx;
    private String sord;
    
    public Permission() {
    }

	
	public Permission(int permissionID, String permissionMark,
			String description, Boolean available, String permissionName,
			String permissionType, String parentID, String url,
			int priority, String createTime, String lastTime) {
		super();
		this.permissionID = permissionID;
		this.permissionMark = permissionMark;
		this.description = description;
		this.available = available;
		this.permissionName = permissionName;
		this.permissionType = permissionType;
		this.parentID = parentID;
		this.url = url;
		this.priority = priority;
		this.createTime = createTime;
		this.lastTime = lastTime;
	}
	
	


	public Permission(String permissionMark, String description,
			Boolean available, String permissionName, String permissionType,
			String parentID, String url, int priority, String createTime,
			String lastTime) {
		super();
		this.permissionMark = permissionMark;
		this.description = description;
		this.available = available;
		this.permissionName = permissionName;
		this.permissionType = permissionType;
		this.parentID = parentID;
		this.url = url;
		this.priority = priority;
		this.createTime = createTime;
		this.lastTime = lastTime;
	}


	public Permission(String permissionMark, Boolean available,
			String permissionName) {
		super();
		this.permissionMark = permissionMark;
		this.available = available;
		this.permissionName = permissionName;
	}


	public int getPermissionID() {
		return permissionID;
	}


	public void setPermissionID(int permissionID) {
		this.permissionID = permissionID;
	}


	public String getPermissionMark() {
		return permissionMark;
	}


	public void setPermissionMark(String permissionMark) {
		this.permissionMark = permissionMark;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	

	public String getPermissionType() {
		return permissionType;
	}


	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}


	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}


	public int getRows() {
		return rows;
	}


	public void setRows(int rows) {
		this.rows = rows;
	}


	public int getStartLength() {
		return startLength;
	}


	public void setStartLength(int startLength) {
		this.startLength = startLength;
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
	
}
