package com.zs.crm.entity;

import java.io.Serializable;


public class XsRoomInfoVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String bldName;
	private String roomCount;
	private String proNo;
	private String bldNo;
	private String unitNo;
	private String roomNo;
	private String roomStatus;
	private String statusClass;
	private String roomInfo;//房间名称,全称
	private String huXing;
	private String roomStru;//房间结构
	private String floor;//楼层
	private String west;//朝向
	private String bldArea;//建筑面积
	private String tnArea;//套内面积
	private String price;//建筑单价
	private String tnPrice;//套内单价
	private String total;//标准总价
	
	public String getProNo() {
		return proNo;
	}
	public void setProNo(String proNo) {
		this.proNo = proNo;
	}
	public String getBldNo() {
		return bldNo;
	}
	public void setBldNo(String bldNo) {
		this.bldNo = bldNo;
	}
	public String getUnitNo() {
		return unitNo;
	}
	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getRoomStatus() {
		return roomStatus;
	}
	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}
	public String getStatusClass() {
		return statusClass;
	}
	public void setStatusClass(String statusClass) {
		this.statusClass = statusClass;
	}
	public String getRoomInfo() {
		return roomInfo;
	}
	public void setRoomInfo(String roomInfo) {
		this.roomInfo = roomInfo;
	}
	public String getHuXing() {
		return huXing;
	}
	public void setHuXing(String huXing) {
		this.huXing = huXing;
	}
	public String getRoomStru() {
		return roomStru;
	}
	public void setRoomStru(String roomStru) {
		this.roomStru = roomStru;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getWest() {
		return west;
	}
	public void setWest(String west) {
		this.west = west;
	}
	public String getBldArea() {
		return bldArea;
	}
	public void setBldArea(String bldArea) {
		this.bldArea = bldArea;
	}
	public String getTnArea() {
		return tnArea;
	}
	public void setTnArea(String tnArea) {
		this.tnArea = tnArea;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTnPrice() {
		return tnPrice;
	}
	public void setTnPrice(String tnPrice) {
		this.tnPrice = tnPrice;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
	public String getBldName() {
		return bldName;
	}
	public void setBldName(String bldName) {
		this.bldName = bldName;
	}
	public String getRoomCount() {
		return roomCount;
	}
	public void setRoomCount(String roomCount) {
		this.roomCount = roomCount;
	}
	
}
