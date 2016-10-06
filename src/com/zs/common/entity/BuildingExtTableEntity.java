package com.zs.common.entity;

import java.io.Serializable;

public class BuildingExtTableEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	private String bldGuid;
    private int bldCheckStatus;
    
	public String getBldGuid() {
		return bldGuid;
	}
	public void setBldGuid(String bldGuid) {
		this.bldGuid = bldGuid;
	}
	public int getBldCheckStatus() {
		return bldCheckStatus;
	}
	public void setBldCheckStatus(int bldCheckStatus) {
		this.bldCheckStatus = bldCheckStatus;
	}  
}
