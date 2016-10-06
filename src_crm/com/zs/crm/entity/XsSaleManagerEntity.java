package com.zs.crm.entity;

import java.io.Serializable;

public class XsSaleManagerEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String gjfs;//跟进方式
	private String gfyx;//购房意向
	private String status;//客户状态
	private String beginTime;
	private String endTime;
	private String groupBy;//分组类型：按哪个字段分组
	private String groupName;
	private int groupNum;
	private int sets;//套数
	private String money;//金额
	
	public String getGjfs() {
		return gjfs;
	}
	public void setGjfs(String gjfs) {
		this.gjfs = gjfs;
	}
	public String getGfyx() {
		return gfyx;
	}
	public void setGfyx(String gfyx) {
		this.gfyx = gfyx;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}
	public int getSets() {
		return sets;
	}
	public void setSets(int sets) {
		this.sets = sets;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGroupBy() {
		return groupBy;
	}
	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}
	
	
}
