package com.zs.crm.entity;

import java.io.Serializable;

public class XsBusinessUrgedPagingAndSort implements Serializable{
	private static final long serialVersionUID = 1L;
	private int startIndex;//从第几条开始
	private int length;//每页长度
	private String sortName;//按哪个字段排序
	private String sortDir;//排序方式：升序/降序
	
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
