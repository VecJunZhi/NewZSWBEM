package com.zs.crm.entity;

import java.io.Serializable;
/**
 * תǩԼ��������ݽṹ
 * @author Administrator
 *
 */
public class XsSignUpDetailVo implements Serializable{
	private static final long serialVersionUID = 1L;
	int newSignUp;//����תǩԼ
	int signUpReSale;//תǩԼ�����Ϲ�
	int netSignUp;//��תǩԼ
	public int getNewSignUp() {
		return newSignUp;
	}
	public void setNewSignUp(int newSignUp) {
		this.newSignUp = newSignUp;
	}
	public int getSignUpReSale() {
		return signUpReSale;
	}
	public void setSignUpReSale(int signUpReSale) {
		this.signUpReSale = signUpReSale;
	}
	public int getNetSignUp() {
		return netSignUp;
	}
	public void setNetSignUp(int netSignUp) {
		this.netSignUp = netSignUp;
	}
	
	
}
