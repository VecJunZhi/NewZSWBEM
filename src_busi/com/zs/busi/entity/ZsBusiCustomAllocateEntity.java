package com.zs.busi.entity;


public class ZsBusiCustomAllocateEntity extends  ZsInfoVo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String employName;
	private String followDate;
	private String firstFollowDate;//��������

	private String cstGuid;//�ͻ�GUID
    private String cstName;//�ͻ�����
    private String cardId;//֤������
    private String officeTel;//�칫�绰
    private String homeTel;//סլ�绰
    private String mobileTel;//�ֻ�
    private String gfyx;//�ͻ�����
    private String createdOn;//�ͻ���������
    
    private String userName;//��ҵ��������
    private String userGuid;//��ҵ����guid
    private String userGUID2;//ԭ��ҵ����guid
    private String userName2;//ԭ��ҵ��������
    private String modifyby;//������
    private String modifybyGuid;//������guid
    
    private String gjDate;//��������
    private String nextDate;//�´θ�������
    private String gjContent;//��������
    private String gjfs;//������ʽ
    private String startFollowTime;//������ʼʱ��
    private String endFollowTime;//��������ʱ��
    
    private String logType;
    private String cusNameOrTel;//�������ߵ绰
    private String overDays;//��������
    private String desc;//��־���͵�����
    
    private String cstStatus;//�ͻ�״̬
    private String lastDate;//������ʱ��
    private String rebackReason;//����ԭ��
    private String rebackDate;//����ʱ��
    private String rebackNums;//���մ���
    private String invalidReason;//��Чԭ��
    private String startDays;//���������Ŀ�ʼ
    private String endDays;//���������Ľ���
    private String startCreate;//�������ڵĿ�ʼ
    private String endCreate;//�������ڵĽ���
    private String status;
    private String firstDate;
 
	public String getUserName() {
		return userName;
	}

	public String getCstGuid() {
		return cstGuid;
	}

	public void setCstGuid(String cstGuid) {
		this.cstGuid = cstGuid;
	}

	public String getCstName() {
		return cstName;
	}

	public void setCstName(String cstName) {
		this.cstName = cstName;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getHomeTel() {
		return homeTel;
	}

	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

	public String getMobileTel() {
		return mobileTel;
	}

	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}

	public String getGfyx() {
		return gfyx;
	}

	public void setGfyx(String gfyx) {
		this.gfyx = gfyx;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getUserGuid() {
		return userGuid;
	}

	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}

	public String getUserGUID2() {
		return userGUID2;
	}

	public void setUserGUID2(String userGUID2) {
		this.userGUID2 = userGUID2;
	}

	public String getUserName2() {
		return userName2;
	}

	public void setUserName2(String userName2) {
		this.userName2 = userName2;
	}

	public String getModifyby() {
		return modifyby;
	}

	public void setModifyby(String modifyby) {
		this.modifyby = modifyby;
	}

	public String getModifybyGuid() {
		return modifybyGuid;
	}

	public void setModifybyGuid(String modifybyGuid) {
		this.modifybyGuid = modifybyGuid;
	}

	public String getGjDate() {
		return gjDate;
	}

	public void setGjDate(String gjDate) {
		this.gjDate = gjDate;
	}

	public String getNextDate() {
		return nextDate;
	}

	public void setNextDate(String nextDate) {
		this.nextDate = nextDate;
	}

	public String getGjContent() {
		return gjContent;
	}

	public void setGjContent(String gjContent) {
		this.gjContent = gjContent;
	}

	public String getGjfs() {
		return gjfs;
	}

	public void setGjfs(String gjfs) {
		this.gjfs = gjfs;
	}

	public String getStartFollowTime() {
		return startFollowTime;
	}

	public void setStartFollowTime(String startFollowTime) {
		this.startFollowTime = startFollowTime;
	}

	public String getEndFollowTime() {
		return endFollowTime;
	}

	public void setEndFollowTime(String endFollowTime) {
		this.endFollowTime = endFollowTime;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getCusNameOrTel() {
		return cusNameOrTel;
	}

	public void setCusNameOrTel(String cusNameOrTel) {
		this.cusNameOrTel = cusNameOrTel;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCstStatus() {
		return cstStatus;
	}

	public void setCstStatus(String cstStatus) {
		this.cstStatus = cstStatus;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public String getRebackReason() {
		return rebackReason;
	}

	public void setRebackReason(String rebackReason) {
		this.rebackReason = rebackReason;
	}

	public String getRebackDate() {
		return rebackDate;
	}

	public void setRebackDate(String rebackDate) {
		this.rebackDate = rebackDate;
	}

	public String getRebackNums() {
		return rebackNums;
	}

	public void setRebackNums(String rebackNums) {
		this.rebackNums = rebackNums;
	}

	public String getStartDays() {
		return startDays;
	}

	public void setStartDays(String startDays) {
		this.startDays = startDays;
	}

	public String getEndDays() {
		return endDays;
	}

	public void setEndDays(String endDays) {
		this.endDays = endDays;
	}

	public String getStartCreate() {
		return startCreate;
	}

	public void setStartCreate(String startCreate) {
		this.startCreate = startCreate;
	}

	public String getEndCreate() {
		return endCreate;
	}

	public void setEndCreate(String endCreate) {
		this.endCreate = endCreate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(String firstDate) {
		this.firstDate = firstDate;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFollowDate() {
		return followDate;
	}

	public void setFollowDate(String followDate) {
		this.followDate = followDate;
	}
	public String getEmployName() {
		return employName;
	}

	public void setEmployName(String employName) {
		this.employName = employName;
	}

	public String getFirstFollowDate() {
		return firstFollowDate;
	}

	public void setFirstFollowDate(String firstFollowDate) {
		this.firstFollowDate = firstFollowDate;
	}

	public String getOverDays() {
		return overDays;
	}

	public void setOverDays(String overDays) {
		this.overDays = overDays;
	}

	public String getInvalidReason() {
		return invalidReason;
	}

	public void setInvalidReason(String invalidReason) {
		this.invalidReason = invalidReason;
	}
	

}
