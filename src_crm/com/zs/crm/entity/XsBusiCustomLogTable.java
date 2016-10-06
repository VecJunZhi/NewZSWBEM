package com.zs.crm.entity;

public class XsBusiCustomLogTable {
	private String id;
	private String cusId;
	private String logType;
	private String operDate;
	private String opercation;
	private String content;
	private String description;
	
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
	public String getOpercation() {
		return opercation;
	}
	public void setOpercation(String opercation) {
		this.opercation = opercation;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public XsBusiCustomLogTable() {
		super();
	}
	public XsBusiCustomLogTable(String cusId, String logType, String operDate,
			String opercation, String content, String description) {
		super();
		this.cusId = cusId;
		this.logType = logType;
		this.operDate = operDate;
		this.opercation = opercation;
		this.content = content;
		this.description = description;
	}
	
	

}
