package com.zs.busi.entity;

public class CountTelAccessVo {
	
	public String cusTel;
	public String cusAccess;
	public String toTel;
	public String toInterView;
	public CountTelAccessVo() {
		super();
	}
	public CountTelAccessVo(String cusTel, String cusAccess, String toTel,
			String toInterView) {
		super();
		this.cusTel = cusTel;
		this.cusAccess = cusAccess;
		this.toTel = toTel;
		this.toInterView = toInterView;
	}
	public String getCusTel() {
		return cusTel;
	}
	public void setCusTel(String cusTel) {
		this.cusTel = cusTel;
	}
	public String getCusAccess() {
		return cusAccess;
	}
	public void setCusAccess(String cusAccess) {
		this.cusAccess = cusAccess;
	}
	public String getToTel() {
		return toTel;
	}
	public void setToTel(String toTel) {
		this.toTel = toTel;
	}
	public String getToInterView() {
		return toInterView;
	}
	public void setToInterView(String toInterView) {
		this.toInterView = toInterView;
	}
	
	

}
