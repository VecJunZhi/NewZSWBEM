package com.zs.crm.entity;

import java.io.Serializable;

import com.zs.crm.entity.tableStructure.XsCstTableEntity;
import com.zs.crm.entity.tableStructure.XsFeeTableEntity;
import com.zs.crm.entity.tableStructure.XsOppTableEntity;
import com.zs.crm.entity.tableStructure.XsOrderTableEntity;
import com.zs.crm.entity.tableStructure.XsRoomTableEntity;
import com.zs.crm.entity.tableStructure.XsTrade2CstTableEntity;
import com.zs.crm.entity.tableStructure.XsTradeExtTableEntity;
import com.zs.crm.entity.tableStructure.XsTradeGjjlTableEntity;
import com.zs.crm.entity.tableStructure.XsTradeTableEntity;
import com.zs.rbac.entity.User;

public class XsBusinessUrgedInfoVo implements Serializable{
	private static final long serialVersionUID = 1L;

	private XsCstTableEntity xsCst;
	private XsOrderTableEntity xsOrder;
	private XsFeeTableEntity xsFee;
	private XsTradeTableEntity xsTrade;
	private XsRoomTableEntity xsRoom;
	private XsTrade2CstTableEntity xsTrade2Cst;
	private XsBusinessUrgedPagingAndSort xsSearch;
	private XsBusinessUrgedOtherInfoVo xsOther;
	private XsOppTableEntity xsOpp;
	private XsTradeGjjlTableEntity xsGjjl;
	private XsTradeExtTableEntity xsExt;
	private User xsUser;
	
	public XsCstTableEntity getXsCst() {
		return xsCst;
	}
	public void setXsCst(XsCstTableEntity xsCst) {
		this.xsCst = xsCst;
	}
	public XsOrderTableEntity getXsOrder() {
		return xsOrder;
	}
	public void setXsOrder(XsOrderTableEntity xsOrder) {
		this.xsOrder = xsOrder;
	}
	public XsFeeTableEntity getXsFee() {
		return xsFee;
	}
	public void setXsFee(XsFeeTableEntity xsFee) {
		this.xsFee = xsFee;
	}
	public XsTradeTableEntity getXsTrade() {
		return xsTrade;
	}
	public void setXsTrade(XsTradeTableEntity xsTrade) {
		this.xsTrade = xsTrade;
	}
	public XsRoomTableEntity getXsRoom() {
		return xsRoom;
	}
	public void setXsRoom(XsRoomTableEntity xsRoom) {
		this.xsRoom = xsRoom;
	}
	public XsTrade2CstTableEntity getXsTrade2Cst() {
		return xsTrade2Cst;
	}
	public void setXsTrade2Cst(XsTrade2CstTableEntity xsTrade2Cst) {
		this.xsTrade2Cst = xsTrade2Cst;
	}
	public XsOppTableEntity getXsOpp() {
		return xsOpp;
	}
	public void setXsOpp(XsOppTableEntity xsOpp) {
		this.xsOpp = xsOpp;
	}
	public XsBusinessUrgedPagingAndSort getXsSearch() {
		return xsSearch;
	}
	public void setXsSearch(XsBusinessUrgedPagingAndSort xsSearch) {
		this.xsSearch = xsSearch;
	}
	public XsBusinessUrgedOtherInfoVo getXsOther() {
		return xsOther;
	}
	public void setXsOther(XsBusinessUrgedOtherInfoVo xsOther) {
		this.xsOther = xsOther;
	}
	public XsTradeGjjlTableEntity getXsGjjl() {
		return xsGjjl;
	}
	public void setXsGjjl(XsTradeGjjlTableEntity xsGjjl) {
		this.xsGjjl = xsGjjl;
	}
	public XsTradeExtTableEntity getXsExt() {
		return xsExt;
	}
	public void setXsExt(XsTradeExtTableEntity xsExt) {
		this.xsExt = xsExt;
	}
	
	public User getXsUser() {
		return xsUser;
	}
	public void setXsUser(User xsUser) {
		this.xsUser = xsUser;
	}
	public XsBusinessUrgedInfoVo() {
		super();
		this.xsCst = new XsCstTableEntity();
		this.xsOrder = new XsOrderTableEntity();
		this.xsFee = new XsFeeTableEntity();
		this.xsTrade = new XsTradeTableEntity();
		this.xsRoom = new XsRoomTableEntity();
		this.xsTrade2Cst = new XsTrade2CstTableEntity();
		this.xsOpp = new XsOppTableEntity();
		this.xsSearch = new XsBusinessUrgedPagingAndSort();
		this.xsOther = new XsBusinessUrgedOtherInfoVo();
		this.xsGjjl = new XsTradeGjjlTableEntity();
		this.xsExt = new XsTradeExtTableEntity();
		this.xsUser = new User();
	}
	
	
}
