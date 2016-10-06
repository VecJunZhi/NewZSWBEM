package com.zs.common.entity;

import java.io.Serializable;

public class InspectRoomSearchInfoVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int startIndex;
	private int length;
	private String sortName;
	private String sortDir;
	private String projGuid;
	private String bldGuid;
	private String unitGuid;
	private String roomGuid;
	private int issuesId;
	private int type;
	private int bldCheckStatus;
	private String status;
	private String mainType;
	
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getSortDir() {
		return sortDir;
	}
	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}
	public String getProjGuid() {
		return projGuid;
	}
	public void setProjGuid(String projGuid) {
		this.projGuid = projGuid;
	}
	public String getBldGuid() {
		return bldGuid;
	}
	public void setBldGuid(String bldGuid) {
		this.bldGuid = bldGuid;
	}
	public String getUnitGuid() {
		return unitGuid;
	}
	public void setUnitGuid(String unitGuid) {
		this.unitGuid = unitGuid;
	}
	public String getRoomGuid() {
		return roomGuid;
	}
	public void setRoomGuid(String roomGuid) {
		this.roomGuid = roomGuid;
	}
	public int getIssuesId() {
		return issuesId;
	}
	public void setIssuesId(int issuesId) {
		this.issuesId = issuesId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getBldCheckStatus() {
		return bldCheckStatus;
	}
	public void setBldCheckStatus(int bldCheckStatus) {
		this.bldCheckStatus = bldCheckStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMainType() {
		return mainType;
	}
	public void setMainType(String mainType) {
		this.mainType = mainType;
	}
	
	
}
