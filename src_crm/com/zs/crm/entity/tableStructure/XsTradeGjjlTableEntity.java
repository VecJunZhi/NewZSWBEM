package com.zs.crm.entity.tableStructure;

import java.io.Serializable;

public class XsTradeGjjlTableEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String tradeGuid;//����guid
	private String urgedDate;//�߰�����
	private String nextUrgedDate;//�´δ߰�����
	private String urgedUserGuid;//�߰���guid
	private String urgedContent;//�߰�����
	private String urgedType;//�߰����� 1���ֻ��� 2��pc��
	private String urgedStage;//�߰�׶� 1��δ���� 2��δǩԼ 3��δ�ſ�
	
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
