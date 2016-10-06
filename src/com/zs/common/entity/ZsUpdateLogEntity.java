package com.zs.common.entity;

import java.util.List;

public class ZsUpdateLogEntity {
	private int logId;
	private String logSubject;
	private String logContent;
	private String logTime;
	private String logClasses;
	private String logType;
	private String userId;
	private String userName;
	private String remark;
	private int startIndex;
	private int length;
	private String distinState;
	List<ZsReplyFeedEntity> zsReplyFeedEntityList;
	public List<ZsReplyFeedEntity> getZsReplyFeedEntityList() {
		return zsReplyFeedEntityList;
	}
	public void setZsReplyFeedEntityList(
			List<ZsReplyFeedEntity> zsReplyFeedEntityList) {
		this.zsReplyFeedEntityList = zsReplyFeedEntityList;
	}
	public String getDistinState() {
		return distinState;
	}
	public void setDistinState(String distinState) {
		this.distinState = distinState;
	}
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
	public String getLogContent() {
		return logContent;
	}
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public String getLogSubject() {
		return logSubject;
	}
	public void setLogSubject(String logSubject) {
		this.logSubject = logSubject;
	}
	public String getLogTime() {
		return logTime;
	}
	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}
	public String getLogClasses() {
		return logClasses;
	}
	public void setLogClasses(String logClasses) {
		this.logClasses = logClasses;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}	
}
