package com.zs.common.entity;

import java.io.Serializable;

public class InspectRoomTableEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
    private String bldGuid;
    private String roomGuid;
    private String projGuid;
    private int checkStatus;
    private int roomStatus;
    private int repairTimes;
    private String createDate;
    private String endDate;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBldGuid() {
		return bldGuid;
	}
	public void setBldGuid(String bldGuid) {
		this.bldGuid = bldGuid;
	}
	public String getRoomGuid() {
		return roomGuid;
	}
	public void setRoomGuid(String roomGuid) {
		this.roomGuid = roomGuid;
	}
	public String getProjGuid() {
		return projGuid;
	}
	public void setProjGuid(String projGuid) {
		this.projGuid = projGuid;
	}
	public int getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(int checkStatus) {
		this.checkStatus = checkStatus;
	}
	public int getRoomStatus() {
		return roomStatus;
	}
	public void setRoomStatus(int roomStatus) {
		this.roomStatus = roomStatus;
	}
	public int getRepairTimes() {
		return repairTimes;
	}
	public void setRepairTimes(int repairTimes) {
		this.repairTimes = repairTimes;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
