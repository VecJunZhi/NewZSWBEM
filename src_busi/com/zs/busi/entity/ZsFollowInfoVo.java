package com.zs.busi.entity;

public class ZsFollowInfoVo extends ZsInfoVo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int startIndex;
	private int length;
	private String sortName;//���������������
	private String sortDir;//������---����/����
	private String proName;//��Ŀ����
	private String startTime;//��ʼ����
	private String endTime;//��������
	private String employeeName;
	private String employeeId;//2016.3.22
	private String progGuid;
	private String cusName;
	private String tel;
	private String cusId;
	
	public String getCusId() {
		return cusId;
	}
	public void setCusId(String cusId) {
		this.cusId = cusId;
	}
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
	
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getProgGuid() {
		return progGuid;
	}
	public void setProgGuid(String progGuid) {
		this.progGuid = progGuid;
	}
	
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public ZsFollowInfoVo() {
		super();
	}
}
