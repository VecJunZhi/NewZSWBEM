package com.zs.crm.entity.tableStructure;

import java.io.Serializable;

public class XsOpp2CstTableEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String oppGuid;//销售机会guid
	private String cstGuid;//客户guid
	private String cstNum;//客户顺序号
	private String opp2CstGuid;//机会客户对照guid
	private String projGuid;//项目guid
	public String getOppGuid() {
		return oppGuid;
	}
	public void setOppGuid(String oppGuid) {
		this.oppGuid = oppGuid;
	}
	public String getCstGuid() {
		return cstGuid;
	}
	public void setCstGuid(String cstGuid) {
		this.cstGuid = cstGuid;
	}
	public String getCstNum() {
		return cstNum;
	}
	public void setCstNum(String cstNum) {
		this.cstNum = cstNum;
	}
	public String getOpp2CstGuid() {
		return opp2CstGuid;
	}
	public void setOpp2CstGuid(String opp2CstGuid) {
		this.opp2CstGuid = opp2CstGuid;
	}
	public String getProjGuid() {
		return projGuid;
	}
	public void setProjGuid(String projGuid) {
		this.projGuid = projGuid;
	}
	
}
