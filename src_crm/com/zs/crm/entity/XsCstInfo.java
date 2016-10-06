package com.zs.crm.entity;

import java.io.Serializable;
import java.util.List;

import com.zs.crm.entity.tableStructure.XsCstAttachTableEntity;
import com.zs.crm.entity.tableStructure.XsCstAttrTableEntity;
import com.zs.crm.entity.tableStructure.XsCstTableEntity;
import com.zs.crm.entity.tableStructure.XsEmployeeTableEntity;
import com.zs.crm.entity.tableStructure.XsOpp2CstTableEntity;
import com.zs.crm.entity.tableStructure.XsOpp2GjjlTableEntity;
import com.zs.crm.entity.tableStructure.XsOpp2RoomTableEntity;
import com.zs.crm.entity.tableStructure.XsOppTableEntity;

public class XsCstInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String cstId;//无用，但得有
	private XsCstTableEntity xsCst;
	private XsCstAttrTableEntity xsCstAttr;
	private XsCstAttachTableEntity xsCstAttach;
	private XsOpp2CstTableEntity xsOpp2Cst;
	private XsOpp2RoomTableEntity xsOpp2Room;
	private XsOppTableEntity xsOpp;
	private XsOpp2GjjlTableEntity xsOpp2Gjjl;
	private XsEmployeeTableEntity employee;
	
	public String getCstId() {
		return cstId;
	}
	public void setCstId(String cstId) {
		this.cstId = cstId;
	}
	
	public XsCstTableEntity getXsCst() {
		return xsCst;
	}
	public void setXsCst(XsCstTableEntity xsCst) {
		this.xsCst = xsCst;
	}
	public XsCstAttrTableEntity getXsCstAttr() {
		return xsCstAttr;
	}
	public void setXsCstAttr(XsCstAttrTableEntity xsCstAttr) {
		this.xsCstAttr = xsCstAttr;
	}
	public XsCstAttachTableEntity getXsCstAttach() {
		return xsCstAttach;
	}
	public void setXsCstAttach(XsCstAttachTableEntity xsCstAttach) {
		this.xsCstAttach = xsCstAttach;
	}
	public XsOpp2CstTableEntity getXsOpp2Cst() {
		return xsOpp2Cst;
	}
	public void setXsOpp2Cst(XsOpp2CstTableEntity xsOpp2Cst) {
		this.xsOpp2Cst = xsOpp2Cst;
	}
	public XsOpp2RoomTableEntity getXsOpp2Room() {
		return xsOpp2Room;
	}
	public void setXsOpp2Room(XsOpp2RoomTableEntity xsOpp2Room) {
		this.xsOpp2Room = xsOpp2Room;
	}
	public XsOppTableEntity getXsOpp() {
		return xsOpp;
	}
	public void setXsOpp(XsOppTableEntity xsOpp) {
		this.xsOpp = xsOpp;
	}
	public XsOpp2GjjlTableEntity getXsOpp2Gjjl() {
		return xsOpp2Gjjl;
	}
	public void setXsOpp2Gjjl(XsOpp2GjjlTableEntity xsOpp2Gjjl) {
		this.xsOpp2Gjjl = xsOpp2Gjjl;
	}
	public XsEmployeeTableEntity getEmployee() {
		return employee;
	}
	public void setEmployee(XsEmployeeTableEntity employee) {
		this.employee = employee;
	}
	public XsCstInfo() {
		xsCst = new XsCstTableEntity();
		xsCstAttr = new XsCstAttrTableEntity();
		xsCstAttach = new XsCstAttachTableEntity();
		xsOpp2Cst =  new XsOpp2CstTableEntity();
		xsOpp2Room = new XsOpp2RoomTableEntity();
		xsOpp = new XsOppTableEntity();
		xsOpp2Gjjl = new XsOpp2GjjlTableEntity();
		employee = new XsEmployeeTableEntity();
	}
}
