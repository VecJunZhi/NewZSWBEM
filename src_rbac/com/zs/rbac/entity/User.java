package com.zs.rbac.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>User: zj
 * <p>Date: 15-9-5
 * <p>Version: 1.0
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private int userID;
    private String username;
    private String password;
    private String mobile;
    private String realName;              
    private String email;   
    private String loginType;
    private int    uStatus;
    private Boolean locked ;//= Boolean.FALSE;
    private String createTime;
    private String lastTime;
    private String description;
   
    private Map<String,String> extInfo;
    
    private List<UserExt> userList;
    
    private int startLength;
	private int rows;
	private String sidx;
	private String sord;
	private int serialNum;
	
    public int getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}

	public String getExtInfo(String property) {
		return extInfo.get(property);
	}

	public List<UserExt> getUserList() {
		return userList;
	}

	public void setUserList(List<UserExt> userList) {
		Map<String,String> map = new HashMap<String,String>();
		for(UserExt userExt:userList){
			map.put(userExt.getProperty(), userExt.getValue());
		}
		this.userList = userList;
		this.extInfo = map;
	}

	public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    


	public User(int userID, String username, String password, String mobile,
			String realName, String eMail, String loginType, int uStatus,
			Boolean locked, String createTime, String lastTime,
			String description) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.mobile = mobile;
		this.realName = realName;
		this.email = eMail;
		this.loginType = loginType;
		this.uStatus = uStatus;
		this.locked = locked;
		this.createTime = createTime;
		this.lastTime = lastTime;
		this.description = description;
	}
	

	public User(String username, String password, String mobile,
			String realName, String eMail, String loginType, int uStatus,
			Boolean locked, String createTime, String lastTime,
			String description) {
		super();
		this.username = username;
		this.password = password;
		this.mobile = mobile;
		this.realName = realName;
		this.email = eMail;
		this.loginType = loginType;
		this.uStatus = uStatus;
		this.locked = locked;
		this.createTime = createTime;
		this.lastTime = lastTime;
		this.description = description;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	



	public int getuStatus() {
		return uStatus;
	}

	public void setuStatus(int uStatus) {
		this.uStatus = uStatus;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}


	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
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

}
