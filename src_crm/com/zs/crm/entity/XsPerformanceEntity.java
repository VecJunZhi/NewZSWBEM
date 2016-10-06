package com.zs.crm.entity;

import java.io.Serializable;

import com.zs.common.util.CommonUtil;

public class XsPerformanceEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ts;//����
	private String bldArea ;//���
	private String salesAmount; //���۽��
	private String backAmount ;//�ؿ���
	
	private String groups;//��
	private String ywy;//ҵ��Ա
	private String orderWord;//�����ֶ�
	
	private String fromTel;//����
	private String fromInterview;//����
	
	private String rgts;//�Ϲ�����
	private String qyts;//ǩԼ����
	private String yqts;//��������
	
	public int getTs() {
		return ts;
	}
	public void setTs(int ts) {
		this.ts = ts;
	}
	public String getBldArea() {
		if(bldArea == null){
			setBldArea("0");
			return bldArea;
		}//2016-8-5 lu
		if(bldArea.split(",").length<=1){
			bldArea=CommonUtil.formate(bldArea, 1);
		}
		if(".00".equals(bldArea)){
			setBldArea("0");
		}
		return bldArea;
	}
	public void setBldArea(String bldArea) {
		this.bldArea = bldArea;
	}
	public String getRgts() {
		return rgts;
	}
	public void setRgts(String rgts) {
		this.rgts = rgts;
	}
	public String getQyts() {
		return qyts;
	}
	public void setQyts(String qyts) {
		this.qyts = qyts;
	}
	public String getYqts() {
		return yqts;
	}
	public void setYqts(String yqts) {
		this.yqts = yqts;
	}
	public String getSalesAmount() {
		if(salesAmount == null){
			setSalesAmount("0");
			return salesAmount;
		}//2016-8-5 lu
		if(salesAmount.split(",").length<=1){
			salesAmount=CommonUtil.formate(salesAmount,10000);
		}
		if(".00".equals(salesAmount)){
			setSalesAmount("0");
		}
		return salesAmount;
	}
	public void setSalesAmount(String salesAmount) {
		this.salesAmount = salesAmount;
	}
	public String getBackAmount() {
		if(backAmount == null){
			setBackAmount("0");
			return backAmount;
		}//2016-8-5 lu
		if(backAmount.split(",").length<=1){
			backAmount=CommonUtil.formate(backAmount,10000);
		}
		if(".00".equals(backAmount)){
			setBackAmount("0");
		}
		return backAmount;
	}
	public void setBackAmount(String backAmount) {
		this.backAmount = backAmount;
	}
	public String getGroups() {
		return groups;
	}
	public void setGroups(String groups) {
		this.groups = groups;
	}
	public String getFromTel() {
		return fromTel;
	}
	public void setFromTel(String fromTel) {
		this.fromTel = fromTel;
	}
	public String getFromInterview() {
		return fromInterview;
	}
	public void setFromInterview(String fromInterview) {
		this.fromInterview = fromInterview;
	}
	public String getYwy() {
		return ywy;
	}
	public void setYwy(String ywy) {
		this.ywy = ywy;
	}
	public String getOrderWord() {
		return orderWord;
	}
	public void setOrderWord(String orderWord) {
		this.orderWord = orderWord;
	}
	
	
	public XsPerformanceEntity(int ts, String bldArea, String salesAmount,
			String backAmount) {
		super();
		this.ts = ts;
		this.bldArea = bldArea;
		this.salesAmount = salesAmount;
		this.backAmount = backAmount;
	}
	public XsPerformanceEntity() {
		super();
	}
	
}
