package com.zs.busi.entity;

import java.io.Serializable;

public class ZsRentTableEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	private String projGuid;
	private String unitNo;//铺位号
	private String addressNo;//门牌号
	private String firstArea;//一面积
	private String secondArea;//二面积
	private String totalArea;//总面积
	private String saleStatus;//销售情况
	private String shopStatus;//商铺状态
	private String rentStatus;//租赁情况
	private String totalRent;//租金总额
	private String dayRent;//日平米租金
	private String signUpDate;//签约日期
	private String intentionCst;//意向客户
	private String investmentOfficer;//招商专员
	private String keyAccountManager;//大客户经理
	public String getProjGuid() {
		return projGuid;
	}
	public void setProjGuid(String projGuid) {
		this.projGuid = projGuid;
	}
	public String getUnitNo() {
		return unitNo;
	}
	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}
	public String getAddressNo() {
		return addressNo;
	}
	public void setAddressNo(String addressNo) {
		this.addressNo = addressNo;
	}
	public String getFirstArea() {
		return firstArea;
	}
	public void setFirstArea(String firstArea) {
		this.firstArea = firstArea;
	}
	public String getSecondArea() {
		return secondArea;
	}
	public void setSecondArea(String secondArea) {
		this.secondArea = secondArea;
	}
	public String getTotalArea() {
		return totalArea;
	}
	public void setTotalArea(String totalArea) {
		this.totalArea = totalArea;
	}
	public String getSaleStatus() {
		return saleStatus;
	}
	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}
	public String getShopStatus() {
		return shopStatus;
	}
	public void setShopStatus(String shopStatus) {
		this.shopStatus = shopStatus;
	}
	public String getRentStatus() {
		return rentStatus;
	}
	public void setRentStatus(String rentStatus) {
		this.rentStatus = rentStatus;
	}
	public String getTotalRent() {
		return totalRent;
	}
	public void setTotalRent(String totalRent) {
		this.totalRent = totalRent;
	}
	public String getDayRent() {
		return dayRent;
	}
	public void setDayRent(String dayRent) {
		this.dayRent = dayRent;
	}
	public String getSignUpDate() {
		return signUpDate;
	}
	public void setSignUpDate(String signUpDate) {
		this.signUpDate = signUpDate;
	}
	public String getIntentionCst() {
		return intentionCst;
	}
	public void setIntentionCst(String intentionCst) {
		this.intentionCst = intentionCst;
	}
	public String getInvestmentOfficer() {
		return investmentOfficer;
	}
	public void setInvestmentOfficer(String investmentOfficer) {
		this.investmentOfficer = investmentOfficer;
	}
	public String getKeyAccountManager() {
		return keyAccountManager;
	}
	public void setKeyAccountManager(String keyAccountManager) {
		this.keyAccountManager = keyAccountManager;
	}
	
	
}
