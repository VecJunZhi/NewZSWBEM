package com.zs.crm.entity;

public class XsRoomResourceInfoVo {
	private String resourceId;
	private XsRoomInfoVo roomInfo;//房间信息
	private XsRoomBargainInfoVo bargainInfo;//成交信息
	public XsRoomInfoVo getRoomInfo() {
		return roomInfo;
	}
	public void setRoomInfo(XsRoomInfoVo roomInfo) {
		this.roomInfo = roomInfo;
	}
	public XsRoomBargainInfoVo getBargainInfo() {
		return bargainInfo;
	}
	public void setBargainInfo(XsRoomBargainInfoVo bargainInfo) {
		this.bargainInfo = bargainInfo;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	
}
