package com.zs.crm.entity;

import java.io.Serializable;
import java.util.List;

public class XsUnitInfoVo implements Serializable{
	private static final long serialVersionUID = 1L;
	//private String unitNo;
	private String floorNo;//���
	private List<XsRoomInfoVo> roomList;//���еķ����б���Ϣ
	/*public String getUnitNo() {
		return unitNo;
	}
	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}*/
	
	public String getFloorNo() {
		return floorNo;
	}
	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}
	public List<XsRoomInfoVo> getRoomList() {
		return roomList;
	}
	public void setRoomList(List<XsRoomInfoVo> roomList) {
		this.roomList = roomList;
	}
	
}
