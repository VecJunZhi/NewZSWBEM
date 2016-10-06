package com.zs.common.entity;

import java.io.Serializable;

public class IssueFeedbackTableEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
    private int issuesId;
    private String backContent;
    private String createDate;
    private String repairMan;
    private String operId;
    private int fbType;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIssuesId() {
		return issuesId;
	}
	public void setIssuesId(int issuesId) {
		this.issuesId = issuesId;
	}
	public String getBackContent() {
		return backContent;
	}
	public void setBackContent(String backContent) {
		this.backContent = backContent;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getRepairMan() {
		return repairMan;
	}
	public void setRepairMan(String repairMan) {
		this.repairMan = repairMan;
	}
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public int getFbType() {
		return fbType;
	}
	public void setFbType(int fbType) {
		this.fbType = fbType;
	}
}
