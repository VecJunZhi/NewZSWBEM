package com.zs.common.entity;

import java.io.Serializable;

public class InspectRoomStatisticsInfoVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String issueType;
	private String issueNum;
	
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	public String getIssueNum() {
		return issueNum;
	}
	public void setIssueNum(String issueNum) {
		this.issueNum = issueNum;
	}
	
}
