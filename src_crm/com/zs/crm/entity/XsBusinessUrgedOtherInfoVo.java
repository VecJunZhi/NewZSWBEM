package com.zs.crm.entity;

import java.io.Serializable;

public class XsBusinessUrgedOtherInfoVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String overDays;//��������
	private String qkTotal;//Ƿ������
	private String skTotal;//ʵ�տ�
	private String qsDate;//ǩ������
	private String minOverDueDays;//��С��������
	private String maxOverDueDays;//�����������
	private String minQsDate;
	private String maxQsDate;
	private String endDate;//��������
	private String toUrged;//�Ƿ��Ǵ��߰�
	private String bank;//��������
	
	public String getOverDays() {
		return overDays;
	}
	public void setOverDays(String overDueDays) {
		this.overDays = overDueDays;
	}
	public String getQkTotal() {
		return qkTotal;
	}
	public void setQkTotal(String qkTotal) {
		this.qkTotal = qkTotal;
	}
	public String getQsDate() {
		return qsDate;
	}
	public void setQsDate(String qsDate) {
		this.qsDate = qsDate;
	}
	public String getMinOverDueDays() {
		return minOverDueDays;
	}
	public void setMinOverDueDays(String minOverDueDays) {
		this.minOverDueDays = minOverDueDays;
	}
	public String getMaxOverDueDays() {
		return maxOverDueDays;
	}
	public void setMaxOverDueDays(String maxOverDueDays) {
		this.maxOverDueDays = maxOverDueDays;
	}
	public String getMinQsDate() {
		return minQsDate;
	}
	public void setMinQsDate(String minQsDate) {
		this.minQsDate = minQsDate;
	}
	public String getMaxQsDate() {
		return maxQsDate;
	}
	public void setMaxQsDate(String maxQsDate) {
		this.maxQsDate = maxQsDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getSkTotal() {
		return skTotal;
	}
	public void setSkTotal(String skTotal) {
		this.skTotal = skTotal;
	}
	public String getToUrged() {
		return toUrged;
	}
	public void setToUrged(String toUrged) {
		this.toUrged = toUrged;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	
	
}
