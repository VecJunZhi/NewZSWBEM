package com.zs.crm.entity;

import java.io.Serializable;

import com.zs.crm.entity.tableStructure.XsCstTableEntity;

public class XsCustomersManagerEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String cstGuid;//客户GUID
    private String cstName;//客户姓名
    private String mobileTel;//手机
    private String projGUID;//项目id
    private String lastDate;//最后跟进时间
    private String gfyx;//客户意向
    private String createdOn;//客户创建日期
    private String status;//客户状态
    private String zygw;
    private String gjDate;//跟进日期
    private String nextDate;//下次跟进日期
    private String gjContent;//跟进内容
    private String gjfs;//跟进方式
    
    private String ageGroup;
    private String homeArea;
    private String workArea;
    private String industry;
    private String marriage;
    private String nativePlace;
    private String family;
    private String email;
    private String gfyt;
    private String gzfm1;
    private String mainMediaName;
    private String modifyOn;
    private String modifyBy;
    private String homeTel;
    private String gfys;
    private String gender;
    
    private String userName;//置业顾问姓名
    private String duty;//置业顾问身份
    private String userGuid;//置业顾问guid
    private String mobilePhone;
    
    private String rebackReason;//回收原因
    private String rebackDate;//回收时间
    private String rebackNums;//回收次数
    private String invalidReason;//无效原因
    private String overDays;//逾期天数
    
    private String countNum;//统计数
    private String oppGUID;
    private XsCstTableEntity xsCstTableEntity;
    private String oldZygw;
    private String oldUserGuid;
    
    private String officeTel;
    private String fax;
    private String address;
    private String cardType;
    private String workAddr;
    private String birthDate;
    private String cardID15;
    private String competitor;
    private String subMediaName;
    private String dfNum;
    private String roomStatus;
    
	public String getRoomStatus() {
		return roomStatus;
	}
	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}
	public String getGfys() {
		return gfys;
	}
	public void setGfys(String gfys) {
		this.gfys = gfys;
	}
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getId() {	
		return id;
	}
	public String getAgeGroup() {
		return ageGroup;
	}
	public String getHomeTel() {
		return homeTel;
	}
	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getWorkAddr() {
		return workAddr;
	}
	public void setWorkAddr(String workAddr) {
		this.workAddr = workAddr;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getCardID15() {
		return cardID15;
	}
	public void setCardID15(String cardID15) {
		this.cardID15 = cardID15;
	}
	public String getCompetitor() {
		return competitor;
	}
	public void setCompetitor(String competitor) {
		this.competitor = competitor;
	}
	public String getSubMediaName() {
		return subMediaName;
	}
	public void setSubMediaName(String subMediaName) {
		this.subMediaName = subMediaName;
	}
	public String getDfNum() {
		return dfNum;
	}
	public void setDfNum(String dfNum) {
		this.dfNum = dfNum;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	public String getHomeArea() {
		return homeArea;
	}
	public void setHomeArea(String homeArea) {
		this.homeArea = homeArea;
	}
	public String getWorkArea() {
		return workArea;
	}
	public void setWorkArea(String workArea) {
		this.workArea = workArea;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGfyt() {
		return gfyt;
	}
	public void setGfyt(String gfyt) {
		this.gfyt = gfyt;
	}
	public String getGzfm1() {
		return gzfm1;
	}
	public void setGzfm1(String gzfm1) {
		this.gzfm1 = gzfm1;
	}
	public String getMainMediaName() {
		return mainMediaName;
	}
	public void setMainMediaName(String mainMediaName) {
		this.mainMediaName = mainMediaName;
	}
	public String getModifyOn() {
		return modifyOn;
	}
	public void setModifyOn(String modifyOn) {
		this.modifyOn = modifyOn;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
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
	public String getMobileTel() {
		return mobileTel;
	}
	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}
	public String getUserName() {
		return userName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProjGUID() {
		return projGUID;
	}
	public void setProjGUID(String projGUID) {
		this.projGUID = projGUID;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
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
	public String getZygw() {
		return zygw;
	}
	public void setZygw(String zygw) {
		this.zygw = zygw;
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
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getUserGuid() {
		return userGuid;
	}
	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}
	public XsCstTableEntity getXsCstTableEntity() {
		return xsCstTableEntity;
	}
	public void setXsCstTableEntity(XsCstTableEntity xsCstTableEntity) {
		this.xsCstTableEntity = xsCstTableEntity;
	}
	public String getCountNum() {
		return countNum;
	}
	public void setCountNum(String countNum) {
		this.countNum = countNum;
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
	public String getOverDays() {
		return overDays;
	}
	public void setOverDays(String overDays) {
		this.overDays = overDays;
	}
	public String getOfficeTel() {
		return officeTel;
	}
	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
    
    

}
