package com.zs.busi.entity;

import java.io.Serializable;

public class ZsRentTableEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	private String projGuid;
	private String unitNo;//��λ��
	private String addressNo;//���ƺ�
	private String firstArea;//һ���
	private String secondArea;//�����
	private String totalArea;//�����
	private String saleStatus;//�������
	private String shopStatus;//����״̬
	private String rentStatus;//�������
	private String totalRent;//����ܶ�
	private String dayRent;//��ƽ�����
	private String signUpDate;//ǩԼ����
	private String intentionCst;//����ͻ�
	private String investmentOfficer;//����רԱ
	private String keyAccountManager;//��ͻ�����
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
