package com.zs.rbac.entity;

import java.io.Serializable;

public class SubSystem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int subSystemId;		//子系统id
	
	private String subSystemName;	//子系统名称
	
	private String subSystemURL;		//子系统URL
	
	private boolean available;		//子系统是否可用
	
	private String subSystemDesc;		//子系统描述

	public int getSubSystemId() {
		return subSystemId;
	}

	public void setSubSystemId(int subSystemId) {
		this.subSystemId = subSystemId;
	}

	public String getSubSystemName() {
		return subSystemName;
	}

	public void setSubSystemName(String subSystemName) {
		this.subSystemName = subSystemName;
	}

	public String getSubSystemURL() {
		return subSystemURL;
	}

	public void setSubSystemURL(String subSystemURL) {
		this.subSystemURL = subSystemURL;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getSubSystemDesc() {
		return subSystemDesc;
	}

	public void setSubSystemDesc(String subSystemDesc) {
		this.subSystemDesc = subSystemDesc;
	}


}
