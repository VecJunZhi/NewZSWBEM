package com.zs.busi.web.vo;

public class ZsCustomerExportVo {
	
	private String newZygw;
    private String cusName;//�ͻ�����
    private String tel;//�ֻ�
    private String employeeName;//��ҵ��������
    private String followWay;//������ʽ
    private String customStatus;//ҵ��׶�
    private String lastDate;//������ʱ��
    private String nextFollowDate;//�´θ�������
    private String intentionType;//�ͻ�����
    private String createDate;//����ʱ��
    private String cusId;//�ͻ�GUID
	public String getNewZygw() {
		return newZygw;
	}
	public void setNewZygw(String newZygw) {
		this.newZygw = newZygw;
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
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getFollowWay() {
		return followWay;
	}
	public void setFollowWay(String followWay) {
		this.followWay = followWay;
	}
	public String getCustomStatus() {
		return customStatus;
	}
	public void setCustomStatus(String customStatus) {
		this.customStatus = customStatus;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public String getNextFollowDate() {
		return nextFollowDate;
	}
	public void setNextFollowDate(String nextFollowDate) {
		this.nextFollowDate = nextFollowDate;
	}
	public String getIntentionType() {
		return intentionType;
	}
	public void setIntentionType(String intentionType) {
		this.intentionType = intentionType;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCusId() {
		return cusId;
	}
	public void setCusId(String cusId) {
		this.cusId = cusId;
	}
	public ZsCustomerExportVo() {
		super();
	}
	public ZsCustomerExportVo( String cusName, String tel,
			String employeeName, String followWay, String customStatus,
			String lastDate, String nextFollowDate, String intentionType,
			String createDate, String cusId) {
		super();
		this.cusName = cusName;
		this.tel = tel;
		this.employeeName = employeeName;
		this.followWay = followWay;
		this.customStatus = customStatus;
		this.lastDate = lastDate;
		this.nextFollowDate = nextFollowDate;
		this.intentionType = intentionType;
		this.createDate = createDate;
		this.cusId = cusId;
	}
    
    

}
