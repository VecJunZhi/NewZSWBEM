package com.zs.crm.entity.tableStructure;

import java.io.Serializable;

public class XsOpp2GjjlTableEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String gjjlGuid;//������¼guid
    private String oppGuid;//����guid
    private String status;//״̬
    private String gfyx;//��������
    private String gjDate;//��������
    private String gjrGuid;//������guid
    private String gjfs;//������ʽ
    private String gjxw;//�ؼ���Ϊ
    private String gjContent;//��������
    private String nextDate;//�´θ�������
    private String nextContent;//�´θ�������
    private String remark;//��ע
    private String sequence;
    private String projGuid;//��Ŀguid
    private String scrmTimestampData;
    private String gjStatus;
    
	public String getGjStatus() {
		return gjStatus;
	}
	public void setGjStatus(String gjStatus) {
		this.gjStatus = gjStatus;
	}
	public String getGjjlGuid() {
		return gjjlGuid;
	}
	public void setGjjlGuid(String gjjlGuid) {
		this.gjjlGuid = gjjlGuid;
	}
	public String getOppGuid() {
		return oppGuid;
	}
	public void setOppGuid(String oppGuid) {
		this.oppGuid = oppGuid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGfyx() {
		return gfyx;
	}
	public void setGfyx(String gfyx) {
		this.gfyx = gfyx;
	}
	public String getGjDate() {
		return gjDate;
	}
	public void setGjDate(String gjDate) {
		this.gjDate = gjDate;
	}
	public String getGjrGuid() {
		return gjrGuid;
	}
	public void setGjrGuid(String gjrGuid) {
		this.gjrGuid = gjrGuid;
	}
	public String getGjfs() {
		return gjfs;
	}
	public void setGjfs(String gjfs) {
		this.gjfs = gjfs;
	}
	public String getGjxw() {
		return gjxw;
	}
	public void setGjxw(String gjxw) {
		this.gjxw = gjxw;
	}
	public String getGjContent() {
		return gjContent;
	}
	public void setGjContent(String gjContent) {
		this.gjContent = gjContent;
	}
	public String getNextDate() {
		return nextDate;
	}
	public void setNextDate(String nextDate) {
		this.nextDate = nextDate;
	}
	public String getNextContent() {
		return nextContent;
	}
	public void setNextContent(String nextContent) {
		this.nextContent = nextContent;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getProjGuid() {
		return projGuid;
	}
	public void setProjGuid(String projGuid) {
		this.projGuid = projGuid;
	}
	public String getScrmTimestampData() {
		return scrmTimestampData;
	}
	public void setScrmTimestampData(String scrmTimestampData) {
		this.scrmTimestampData = scrmTimestampData;
	}
    
}
