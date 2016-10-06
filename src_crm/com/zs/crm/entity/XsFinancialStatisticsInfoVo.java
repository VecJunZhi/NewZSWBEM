package com.zs.crm.entity;

import java.io.Serializable;

import com.zs.crm.entity.tableStructure.XsCstTableEntity;
import com.zs.crm.entity.tableStructure.XsRoomTableEntity;
import com.zs.crm.entity.tableStructure.XsTradeTableEntity;

public class XsFinancialStatisticsInfoVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private XsCstTableEntity xsCst;
	private XsRoomTableEntity xsRoom;
	private XsTradeTableEntity xsTrade;
	private XsFinancialStatisticsOtherInfoVo xsOther;
	
	public XsCstTableEntity getXsCst() {
		return xsCst;
	}
	public void setXsCst(XsCstTableEntity xsCst) {
		this.xsCst = xsCst;
	}
	public XsRoomTableEntity getXsRoom() {
		return xsRoom;
	}
	public void setXsRoom(XsRoomTableEntity xsRoom) {
		this.xsRoom = xsRoom;
	}
	public XsFinancialStatisticsOtherInfoVo getXsOther() {
		return xsOther;
	}
	public void setXsOther(XsFinancialStatisticsOtherInfoVo xsOther) {
		this.xsOther = xsOther;
	}
	public XsTradeTableEntity getXsTrade() {
		return xsTrade;
	}
	public void setXsTrade(XsTradeTableEntity xsTrade) {
		this.xsTrade = xsTrade;
	}
	
	
}
