package com.zs.crm.entity;


public class XsBusiCustomAllocateEntity extends  XsInfoVo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String employName;
	private String followDate;
	private String firstFollowDate;//新增日期
	public String getFollowDate() {
		return followDate;
	}

	public void setFollowDate(String followDate) {
		this.followDate = followDate;
	}
	public String getEmployName() {
		return employName;
	}

	public void setEmployName(String employName) {
		this.employName = employName;
	}

	public String getFirstFollowDate() {
		return firstFollowDate;
	}

	public void setFirstFollowDate(String firstFollowDate) {
		this.firstFollowDate = firstFollowDate;
	}
	

}
