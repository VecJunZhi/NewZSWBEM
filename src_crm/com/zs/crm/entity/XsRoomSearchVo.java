package com.zs.crm.entity;

public class XsRoomSearchVo {
	
	private String bldName;
	private String bldFullName;
	private String unit;
	private String roomNo;
	
	private String bldGuid;//当前楼栋guid
	private String projGuid;//当前工程guid
	private String minTotalPrice;
	private String maxTotalPrice;//总价范围
	private String minArea;
	private String maxArea;//面积范围
	private String roomStru;//房间结构
	private String status;//房间状态
	private String huXing;//户型
	public String getBldName() {
		return bldName;
	}
	public void setBldName(String bldName) {
		this.bldName = bldName;
	}
	
	public String getBldFullName() {
		return bldFullName;
	}
	public void setBldFullName(String bldFullName) {
		this.bldFullName = bldFullName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	
	public String getBldGuid() {
		return bldGuid;
	}
	public void setBldGuid(String bldGuid) {
		this.bldGuid = bldGuid;
	}
	public String getProjGuid() {
		return projGuid;
	}
	public void setProjGuid(String projGuid) {
		this.projGuid = projGuid;
	}
	public String getMinTotalPrice() {
		return minTotalPrice;
	}
	public void setMinTotalPrice(String minTotalPrice) {
		this.minTotalPrice = minTotalPrice;
	}
	public String getMaxTotalPrice() {
		return maxTotalPrice;
	}
	public void setMaxTotalPrice(String maxTotalPrice) {
		this.maxTotalPrice = maxTotalPrice;
	}
	public String getMinArea() {
		return minArea;
	}
	public void setMinArea(String minArea) {
		this.minArea = minArea;
	}
	public String getMaxArea() {
		return maxArea;
	}
	public void setMaxArea(String maxArea) {
		this.maxArea = maxArea;
	}
	public String getRoomStru() {
		return roomStru;
	}
	public void setRoomStru(String roomStru) {
		this.roomStru = roomStru;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHuXing() {
		return huXing;
	}
	public void setHuXing(String huXing) {
		this.huXing = huXing;
	}
	
	
	
}
