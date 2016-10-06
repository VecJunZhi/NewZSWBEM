package com.zs.crm.web.vo;

public class XsPerformanceVo {
	private String startTime;
	private String endTime;
	private String  orderWord;
	private String ywy;
	private String projGuid;
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getOrderWord() {
		return orderWord;
	}
	public void setOrderWord(String orderWord) {
		this.orderWord = orderWord;
	}
	
	
	
	
	public String getProjGuid() {
		return projGuid;
	}
	public void setProjGuid(String projGuid) {
		this.projGuid = projGuid;
	}
	public String getYwy() {
		return ywy;
	}
	public void setYwy(String ywy) {
		this.ywy = ywy;
	}
	public XsPerformanceVo(String startTime, String endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public XsPerformanceVo() {
		super();
	}
	

}
