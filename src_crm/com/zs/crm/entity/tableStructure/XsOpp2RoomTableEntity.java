package com.zs.crm.entity.tableStructure;

import java.io.Serializable;

public class XsOpp2RoomTableEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String oppGuid;//销售机会guid
	private String roomGuid;//房间guid
	private String num;//序号
	private String rowGuid;//记录guid--主键
	private String yxFangXing;//意向房型
	private String yxArea;//意向面积
	private String yxPrice;//意向价格
	private String yxYeTai;//意向业态？
	public String getOppGuid() {
		return oppGuid;
	}
	public void setOppGuid(String oppGuid) {
		this.oppGuid = oppGuid;
	}
	public String getRoomGuid() {
		return roomGuid;
	}
	public void setRoomGuid(String roomGuid) {
		this.roomGuid = roomGuid;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getRowGuid() {
		return rowGuid;
	}
	public void setRowGuid(String rowGuid) {
		this.rowGuid = rowGuid;
	}
	public String getYxFangXing() {
		return yxFangXing;
	}
	public void setYxFangXing(String yxFangXing) {
		this.yxFangXing = yxFangXing;
	}
	public String getYxArea() {
		return yxArea;
	}
	public void setYxArea(String yxArea) {
		this.yxArea = yxArea;
	}
	public String getYxPrice() {
		return yxPrice;
	}
	public void setYxPrice(String yxPrice) {
		this.yxPrice = yxPrice;
	}
	public String getYxYeTai() {
		return yxYeTai;
	}
	public void setYxYeTai(String yxYeTai) {
		this.yxYeTai = yxYeTai;
	}
	
}
