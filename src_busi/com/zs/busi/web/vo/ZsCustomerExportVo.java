package com.zs.busi.web.vo;

public class ZsCustomerExportVo {
	
	private String newZygw;
    private String cusName;//客户姓名
    private String tel;//手机
    private String employeeName;//置业顾问姓名
    private String followWay;//跟进方式
    private String customStatus;//业务阶段
    private String lastDate;//最后跟进时间
    private String nextFollowDate;//下次跟进日期
    private String intentionType;//客户意向
    private String createDate;//创建时间
    private String cusId;//客户GUID
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
