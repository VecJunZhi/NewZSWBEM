package com.zs.common.entity;

import java.io.Serializable;
import java.util.List;

public class BldUnitInfoVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String unit;
	private String doorNum;
	private List<RoomTableEntity> roomList;
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDoorNum() {
		return doorNum;
	}
	public void setDoorNum(String doorNum) {
		this.doorNum = doorNum;
	}
	public List<RoomTableEntity> getRoomList() {
		return roomList;
	}
	public void setRoomList(List<RoomTableEntity> roomList) {
		this.roomList = roomList;
	}
	
}
