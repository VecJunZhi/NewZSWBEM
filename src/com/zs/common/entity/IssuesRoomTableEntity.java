package com.zs.common.entity;

import java.io.Serializable;

public class IssuesRoomTableEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	private int issueRoomId;
    private String roomGuid;
    private int status;
    private int type;
    private String createDate;
    private String endDate;
    
	public int getIssueRoomId() {
		return issueRoomId;
	}
	public void setIssueRoomId(int issueRoomId) {
		this.issueRoomId = issueRoomId;
	}
	public String getRoomGuid() {
		return roomGuid;
	}
	public void setRoomGuid(String roomGuid) {
		this.roomGuid = roomGuid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
