package com.zs.crm.web.vo;


public class XsCustomersManagerVo {
	
	private int startIndex;
	private int length;
	private String sortName;//���������������
	private String sortDir;//������---����/����
	private String proName;//��Ŀ����
	private String startTime;//��ʼ����
	private String endTime;//��������
	
    private String buGUID;//��˾id
	private String projGUID;//��Ŀid
    
	private String id;
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
    private String oppGUID;
    private String oldZygw;
    private String  oldUserGuid;
    
    private String fax;//2016-7-23 Lu
    
    
	public String getOldUserGuid() {
		return oldUserGuid;
	}
	public void setOldUserGuid(String oldUserGuid) {
		this.oldUserGuid = oldUserGuid;
	}
	public String getOldZygw() {
		return oldZygw;
	}
	public void setOldZygw(String oldZygw) {
		this.oldZygw = oldZygw;
	}
	public String getOppGUID() {
		return oppGUID;
	}
	public void setOppGUID(String oppGUID) {
		this.oppGUID = oppGUID;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public String getModifybyGuid() {
		return modifybyGuid;
	}
	public void setModifybyGuid(String modifybyGuid) {
		this.modifybyGuid = modifybyGuid;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getModifyby() {
		return modifyby;
	}
	public void setModifyby(String modifyby) {
		this.modifyby = modifyby;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getBuGUID() {
		return buGUID;
	}
	public void setBuGUID(String buGUID) {
		this.buGUID = buGUID;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserGUID2() {
		return userGUID2;
	}
	public void setUserGUID2(String userGUID2) {
		this.userGUID2 = userGUID2;
	}
	public String getProjGUID() {
		return projGUID;
	}
	public void setProjGUID(String projGUID) {
		this.projGUID = projGUID;
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
	public String getUserGuid() {
		return userGuid;
	}
	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}
	public String getCusNameOrTel() {
		return cusNameOrTel;
	}
	public void setCusNameOrTel(String cusNameOrTel) {
		this.cusNameOrTel = cusNameOrTel;
	}
	public String getOverDays() {
		return overDays;
	}
	public void setOverDays(String overDays) {
		this.overDays = overDays;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getUserName2() {
		return userName2;
	}
	public void setUserName2(String userName2) {
		this.userName2 = userName2;
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
	public String getInvalidReason() {
		return invalidReason;
	}
	public void setInvalidReason(String invalidReason) {
		this.invalidReason = invalidReason;
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
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
    
}
