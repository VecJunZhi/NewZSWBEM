package com.zs.busi.entity;

public class ZsBusiCustomLogTable {
	private String id;
	private String cusId;
	private String logType;
	private String operDate;
	private String operator;
	private String content;
	private String reason;
	private String projectId;
	private String belongSys;
	private String oldOperator;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCusId() {
		return cusId;
	}
	public void setCusId(String cusId) {
		this.cusId = cusId;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getOperDate() {
		return operDate;
	}
	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getBelongSys() {
		return belongSys;
	}
	public void setBelongSys(String belongSys) {
		this.belongSys = belongSys;
	}
	public String getOldOperator() {
		return oldOperator;
	}
	public void setOldOperator(String oldOperator) {
		this.oldOperator = oldOperator;
	}
	public ZsBusiCustomLogTable() {
		super();
	}
	public ZsBusiCustomLogTable(String cusId, String logType,
			String operDate, String operator, String content, String reason,
			String projectId, String belongSys, String oldOperator) {
		super();
		this.cusId = cusId;
		this.logType = logType;
		this.operDate = operDate;
		this.operator = operator;
		this.content = content;
		this.reason = reason;
		this.projectId = projectId;
		this.belongSys = belongSys;
		this.oldOperator = oldOperator;
	}
}
