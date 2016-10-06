package com.zs.common.entity;

import java.io.Serializable;

public class BuildUnitTableEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String unitGuid;
	private String bldGuid;
	private String unit;
	private int doorNum;
	private String roomNoList;
	private int unitNo;
	public String getUnitGuid() {
		return unitGuid;
	}
	public void setUnitGuid(String unitGuid) {
		this.unitGuid = unitGuid;
	}
	public String getBldGuid() {
		return bldGuid;
	}
	public void setBldGuid(String bldGuid) {
		this.bldGuid = bldGuid;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getDoorNum() {
		return doorNum;
	}
	public void setDoorNum(int doorNum) {
		this.doorNum = doorNum;
	}
	public String getRoomNoList() {
		return roomNoList;
	}
	public void setRoomNoList(String roomNoList) {
		this.roomNoList = roomNoList;
	}
	public int getUnitNo() {
		return unitNo;
	}
	public void setUnitNo(int unitNo) {
		this.unitNo = unitNo;
	}
	
	
}
