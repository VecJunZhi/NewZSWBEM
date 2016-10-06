package com.zs.crm.entity.tableStructure;

import java.io.Serializable;


public class XsEmployeeTableEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userName;
	private String mobilePhone;
	private String userGuid;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getUserGuid() {
		return userGuid;
	}

	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}

	
	
}
