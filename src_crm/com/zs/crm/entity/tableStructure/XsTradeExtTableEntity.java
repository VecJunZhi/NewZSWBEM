package com.zs.crm.entity.tableStructure;

import java.io.Serializable;

public class XsTradeExtTableEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String tradeGuid;//交易guid
	private String level;//交易级别：普通、疑难
	private String level1;//未签约状态下的交易级别
	private String level2;//未放款状态下的交易级别
	private String postponeDate;//延期日期
	private String postponeTimes;//延期次数
	private String postponeReason;//延期原因
	private String postponeStage;//延期阶段
	
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
