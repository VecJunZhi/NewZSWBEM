package com.zs.crm.web.vo;

public class XsCustomerFollowingExportVo {
	
	private String newZygw;
    private String cstName;//客户姓名
    private String mobileTel;//手机
    private String userName;//置业顾问姓名
    private String gjfs;//跟进方式
    private String status;//状态
    private String gfyx;//意向
    private String gfys;
    private String gfyt;
    private String gzfm1;
    private String gender;
    private String email;
    private String address;
    private String cardType;
    private String cardID15;
    private String workAddr;
    private String birthDate;
    private String competitor;
    private String subMediaName;
    private String dfNum;
    private String lastDate;//最后跟进时间
    private String nextDate;//下次跟进日期

    private String createdOn;//最后跟进时间
   // private String zygw;//最后跟进时间    
    private String userGUID;//原置业顾问guid
    private String projGUID;
    private String cstGUID;//客户GUID
    private String oppGUID;
    
  
	
    public String getNewZygw() {
		return newZygw;
	}
	public void setNewZygw(String newZygw) {
		this.newZygw = newZygw;
	}
	public String getCstGUID() {
		return cstGUID;
	}
	public void setCstGUID(String cstGUID) {
		this.cstGUID = cstGUID;
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
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserGUID() {
		return userGUID;
	}
	public void setUserGUID(String userGUID) {
		this.userGUID = userGUID;
	}
	public String getProjGUID() {
		return projGUID;
	}
	public void setProjGUID(String projGUID) {
		this.projGUID = projGUID;
	}
	public String getGjfs() {
		return gjfs;
	}
	public void setGjfs(String gjfs) {
		this.gjfs = gjfs;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public String getNextDate() {
		return nextDate;
	}
	public void setNextDate(String nextDate) {
		this.nextDate = nextDate;
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
	/*public String getZygw() {
		return zygw;
	}
	public void setZygw(String zygw) {
		this.zygw = zygw;
	}*/
	public String getOppGUID() {
		return oppGUID;
	}
	public void setOppGUID(String oppGUID) {
		this.oppGUID = oppGUID;
	}
	
	public XsCustomerFollowingExportVo() {
		super();
	}
	
	public String getGfys() {
		return gfys;
	}
	public void setGfys(String gfys) {
		this.gfys = gfys;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getCardID15() {
		return cardID15;
	}
	public void setCardID15(String cardID15) {
		this.cardID15 = cardID15;
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
	public XsCustomerFollowingExportVo(String cstName,
			String mobileTel, String userName, String gjfs, String status,
			String gfyx, String gfys, String gfyt, String gzfm1, String gender,
			String email, String address, String cardType, String cardID15,
			String workAddr, String birthDate, String competitor,
			String subMediaName, String dfNum, String lastDate,
			String nextDate, String createdOn, String userGUID,
			String projGUID, String cstGUID, String oppGUID) {
		super();
		this.cstName = cstName;
		this.mobileTel = mobileTel;
		this.userName = userName;
		this.gjfs = gjfs;
		this.status = status;
		this.gfyx = gfyx;
		this.gfys = gfys;
		this.gfyt = gfyt;
		this.gzfm1 = gzfm1;
		this.gender = gender;
		this.email = email;
		this.address = address;
		this.cardType = cardType;
		this.cardID15 = cardID15;
		this.workAddr = workAddr;
		this.birthDate = birthDate;
		this.competitor = competitor;
		this.subMediaName = subMediaName;
		this.dfNum = dfNum;
		this.lastDate = lastDate;
		this.nextDate = nextDate;
		this.createdOn = createdOn;
		this.userGUID = userGUID;
		this.projGUID = projGUID;
		this.cstGUID = cstGUID;
		this.oppGUID = oppGUID;
	}

   
    

}
