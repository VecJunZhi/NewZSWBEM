package com.zs.crm.entity;

import java.io.Serializable;
/**
 * 转签约详情的数据结构
 * @author Administrator
 *
 */
public class XsSignUpDetailVo implements Serializable{
	private static final long serialVersionUID = 1L;
	int newSignUp;//新增转签约
	int signUpReSale;//转签约重置认购
	int netSignUp;//净转签约
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
