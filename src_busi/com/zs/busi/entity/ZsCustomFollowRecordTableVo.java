package com.zs.busi.entity;

import java.io.Serializable;

public class ZsCustomFollowRecordTableVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String cusId;//客户id
	private String followPersionId;//跟进人id
	private String followDate;//跟进日期
	private String followTime;//跟进时间
	private String followInfo;//跟进说明
	private String followWay;//跟进方式   defined by self
	private String nextFollowDay;//下次跟进日期 defined by self
	private String employeeName;//跟进人姓�?defined by self
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
	public String getFollowPersionId() {
		return followPersionId;
	}
	public void setFollowPersionId(String followPersionId) {
		this.followPersionId = followPersionId;
	}
	public String getFollowDate() {
		return followDate;
	}
	public void setFollowDate(String followDate) {
		this.followDate = followDate;
	}
	public String getFollowTime() {
		return followTime;
	}
	public void setFollowTime(String followTime) {
		this.followTime = followTime;
	}
	public String getFollowInfo() {
		return followInfo;
	}
	public void setFollowInfo(String followInfo) {
		this.followInfo = followInfo;
	}
	
	public String getFollowWay() {
		return followWay;
	}
	public void setFollowWay(String followWay) {
		this.followWay = followWay;
	}
	
	public String getNextFollowDay() {
		return nextFollowDay;
	}
	public void setNextFollowDay(String nextFollowDay) {
		this.nextFollowDay = nextFollowDay;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public ZsCustomFollowRecordTableVo() {
		super();
	}
	

}
