package com.zs.crm.entity.tableStructure;

import java.io.Serializable;

public class XsTradeExtTableEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String tradeGuid;//����guid
	private String level;//���׼�����ͨ������
	private String level1;//δǩԼ״̬�µĽ��׼���
	private String level2;//δ�ſ�״̬�µĽ��׼���
	private String postponeDate;//��������
	private String postponeTimes;//���ڴ���
	private String postponeReason;//����ԭ��
	private String postponeStage;//���ڽ׶�
	
	public String getTradeGuid() {
		return tradeGuid;
	}
	public void setTradeGuid(String tradeGuid) {
		this.tradeGuid = tradeGuid;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getPostponeDate() {
		return postponeDate;
	}
	public void setPostponeDate(String postponeDate) {
		this.postponeDate = postponeDate;
	}
	public String getPostponeTimes() {
		return postponeTimes;
	}
	public void setPostponeTimes(String postponeTimes) {
		this.postponeTimes = postponeTimes;
	}
	public String getPostponeReason() {
		return postponeReason;
	}
	public void setPostponeReason(String postponeReason) {
		this.postponeReason = postponeReason;
	}
	public String getLevel1() {
		return level1;
	}
	public void setLevel1(String level1) {
		this.level1 = level1;
	}
	public String getLevel2() {
		return level2;
	}
	public void setLevel2(String level2) {
		this.level2 = level2;
	}
	public String getPostponeStage() {
		return postponeStage;
	}
	public void setPostponeStage(String postponeStage) {
		this.postponeStage = postponeStage;
	}
	
}
