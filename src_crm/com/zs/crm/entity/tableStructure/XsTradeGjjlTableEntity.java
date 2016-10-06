package com.zs.crm.entity.tableStructure;

import java.io.Serializable;

public class XsTradeGjjlTableEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String tradeGuid;//交易guid
	private String urgedDate;//催办日期
	private String nextUrgedDate;//下次催办日期
	private String urgedUserGuid;//催办人guid
	private String urgedContent;//催办内容
	private String urgedType;//催办类型 1、手机端 2、pc端
	private String urgedStage;//催办阶段 1、未交款 2、未签约 3、未放款
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTradeGuid() {
		return tradeGuid;
	}
	public void setTradeGuid(String tradeGuid) {
		this.tradeGuid = tradeGuid;
	}
	public String getUrgedDate() {
		return urgedDate;
	}
	public void setUrgedDate(String urgedDate) {
		this.urgedDate = urgedDate;
	}
	public String getNextUrgedDate() {
		return nextUrgedDate;
	}
	public void setNextUrgedDate(String nextUrgedDate) {
		this.nextUrgedDate = nextUrgedDate;
	}
	public String getUrgedUserGuid() {
		return urgedUserGuid;
	}
	public void setUrgedUserGuid(String urgedUserGuid) {
		this.urgedUserGuid = urgedUserGuid;
	}
	public String getUrgedContent() {
		return urgedContent;
	}
	public void setUrgedContent(String urgedContent) {
		this.urgedContent = urgedContent;
	}
	public String getUrgedType() {
		return urgedType;
	}
	public void setUrgedType(String urgedType) {
		this.urgedType = urgedType;
	}
	public String getUrgedStage() {
		return urgedStage;
	}
	public void setUrgedStage(String urgedStage) {
		this.urgedStage = urgedStage;
	}
	
	
}
