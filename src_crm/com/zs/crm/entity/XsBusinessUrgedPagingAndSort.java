package com.zs.crm.entity;

import java.io.Serializable;

public class XsBusinessUrgedPagingAndSort implements Serializable{
	private static final long serialVersionUID = 1L;
	private int startIndex;//�ӵڼ�����ʼ
	private int length;//ÿҳ����
	private String sortName;//���ĸ��ֶ�����
	private String sortDir;//����ʽ������/����
	
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getSortDir() {
		return sortDir;
	}
	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}
}
