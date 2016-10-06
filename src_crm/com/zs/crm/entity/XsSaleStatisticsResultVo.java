package com.zs.crm.entity;

import java.io.Serializable;

public class XsSaleStatisticsResultVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String totalNum;//总数
	public String soldNum;//已售数
	public String unSoldNum;//未售数
	public String totalAmount;//总钱数
	public String qkAmount;//欠款金额
	public String qkNum;//欠款户数
	public String unPaymentNum;
	public String unPaymentAmount;
	public String unSignUpNum;
	public String unSignUpAmount;
	public String unLendingNum;
	public String unLendingAmount;
	public String getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}
	public String getSoldNum() {
		return soldNum;
	}
	public void setSoldNum(String soldNum) {
		this.soldNum = soldNum;
	}
	public String getUnSoldNum() {
		return unSoldNum;
	}
	public void setUnSoldNum(String unSoldNum) {
		this.unSoldNum = unSoldNum;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getQkAmount() {
		return qkAmount;
	}
	public void setQkAmount(String qkAmount) {
		this.qkAmount = qkAmount;
	}
	public String getQkNum() {
		return qkNum;
	}
	public void setQkNum(String qkNum) {
		this.qkNum = qkNum;
	}
	public String getUnPaymentNum() {
		return unPaymentNum;
	}
	public void setUnPaymentNum(String unPaymentNum) {
		this.unPaymentNum = unPaymentNum;
	}
	public String getUnPaymentAmount() {
		return unPaymentAmount;
	}
	public void setUnPaymentAmount(String unPaymentAmount) {
		this.unPaymentAmount = unPaymentAmount;
	}
	public String getUnSignUpNum() {
		return unSignUpNum;
	}
	public void setUnSignUpNum(String unSignUpNum) {
		this.unSignUpNum = unSignUpNum;
	}
	public String getUnSignUpAmount() {
		return unSignUpAmount;
	}
	public void setUnSignUpAmount(String unSignUpAmount) {
		this.unSignUpAmount = unSignUpAmount;
	}
	public String getUnLendingNum() {
		return unLendingNum;
	}
	public void setUnLendingNum(String unLendingNum) {
		this.unLendingNum = unLendingNum;
	}
	public String getUnLendingAmount() {
		return unLendingAmount;
	}
	public void setUnLendingAmount(String unLendingAmount) {
		this.unLendingAmount = unLendingAmount;
	}
	public XsSaleStatisticsResultVo() {
		super();
		this.totalNum = "0";
		this.soldNum = "0";
		this.unSoldNum = "0";
		this.totalAmount = "0";
		this.qkAmount = "0";
		this.qkNum = "0";
		this.unPaymentNum = "0";
		this.unPaymentAmount = "0";
		this.unSignUpNum = "0";
		this.unSignUpAmount = "0";
		this.unLendingNum = "0";
		this.unLendingAmount = "0";
	}
	
}
