package com.zs.crm.entity;

import java.io.Serializable;
/**
 * �Ϲ���������ݽṹ
 * @author Administrator
 *
 */
public class XsSaleDetailVo implements Serializable{
	private static final long serialVersionUID = 1L;
	int newSale;
	int salePriceChange;//�Ϲ��۸���
	int saleChangeHouseDiff;//�Ϲ��������
	int saleCheckOut;//�Ϲ��˷�
	int saleInvalid;//�Ϲ�����
	int signUpDiff;//תǩԼ���
	int reSale;//�����Ϲ�
	int netSale;//���Ϲ�
	public int getNewSale() {
		return newSale;
	}
	public void setNewSale(int newSale) {
		this.newSale = newSale;
	}
	public int getSalePriceChange() {
		return salePriceChange;
	}
	public void setSalePriceChange(int salePriceChange) {
		this.salePriceChange = salePriceChange;
	}
	public int getSaleChangeHouseDiff() {
		return saleChangeHouseDiff;
	}
	public void setSaleChangeHouseDiff(int saleChangeHouseDiff) {
		this.saleChangeHouseDiff = saleChangeHouseDiff;
	}
	public int getSaleCheckOut() {
		return saleCheckOut;
	}
	public void setSaleCheckOut(int saleCheckOut) {
		this.saleCheckOut = saleCheckOut;
	}
	public int getSaleInvalid() {
		return saleInvalid;
	}
	public void setSaleInvalid(int saleInvalid) {
		this.saleInvalid = saleInvalid;
	}
	public int getSignUpDiff() {
		return signUpDiff;
	}
	public void setSignUpDiff(int signUpDiff) {
		this.signUpDiff = signUpDiff;
	}
	public int getReSale() {
		return reSale;
	}
	public void setReSale(int reSale) {
		this.reSale = reSale;
	}
	public int getNetSale() {
		return netSale;
	}
	public void setNetSale(int netSale) {
		this.netSale = netSale;
	}
	
}
