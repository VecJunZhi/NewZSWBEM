package com.zs.rbac.entity;

import java.io.Serializable;

/**
 * <p>User: zj
 * <p>Date: 15-9-5
 * <p>Version: 1.0
 */
public class Role implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int roleID;
    private String roleName; //角色标识 程序中判断使用,如"admin"
    private String description; //角色描述,UI界面显示使用 如 系统管理员
    private Boolean available;// = Boolean.FALSE; //是否可用,如果不可用将不会添加给用户
    private String createTime;
    private String lastTime;
    private String priority;
    private int orgid;//与组织架构的关联
    private String projGuid;
    private int serialNum;//列的序列号，用来分页
    private int rows;
    private int startLength;
    private int userID;
    private String url;
    
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
    public String getRoleName() {
		return roleName;
	}
    public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public int getOrgid() {
		return orgid;
	}

	public void setOrgid(int orgid) {
		this.orgid = orgid;
	}

	public String getProjGuid() {
		return projGuid;
	}
	public void setProjGuid(String projGuid) {
		this.projGuid = projGuid;
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
	public Role() {
		super();
	}

	public int getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}
	public Role(int roleID, String roleName, String description,
			Boolean available, String createTime, String lastTime,
			String priority, int orgid,int serialNum) {
		super();
		this.roleID = roleID;
		this.roleName = roleName;
		this.description = description;
		this.available = available;
		this.createTime = createTime;
		this.lastTime = lastTime;
		this.priority = priority;
		this.orgid = orgid;
		this.serialNum=serialNum;
	}

	public Role(String roleName, String description, Boolean available,
			String createTime, String lastTime, String priority, int orgid,int serialNum) {
		super();
		this.roleName = roleName;
		this.description = description;
		this.available = available;
		this.createTime = createTime;
		this.lastTime = lastTime;
		this.priority = priority;
		this.orgid = orgid;
		this.serialNum=serialNum;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	

    

    
}
