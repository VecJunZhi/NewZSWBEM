package com.zs.common.entity;

import java.io.Serializable;

public class IssuesTypeTableEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	private int issueCode;
    private String issueName;
    private String parentCode;
    
	public int getIssueCode() {
		return issueCode;
	}
	public void setIssueCode(int issueCode) {
		this.issueCode = issueCode;
	}
	public String getIssueName() {
		return issueName;
	}
	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	} 
}
