package com.zs.busi.web.vo;

public class ZsCustomerImportVo {
	private String cstName;//客户姓名
    private String tel;//手机
    private String homeTel;//家庭电话
    private String officeTel;//公司电话
    private String faxNo;//传真
    private String sex;//性别
    private String label;//标签
    private String idNo;//15位身份证号

    private String intentionArea;//意向面积
    private String intentionPrice;//意向价格
    private String intentionRoom;//意向房型

    private String houseUse;//购买用途
    private String age;//年龄段
    private String buyTimes;//置业次数
    private String addrArea;//居住区域
    private String workComp;//工作区域
    private String familyInfo;//家庭结构
    private String investmentInfo;//招商业态

    private String competitor;//竞争对手
    private String intentionType;//购房意向
    private String customStatus;//业务阶段   状态--内容有:赢得、丢失
    private String username;//置业顾问
    private String projGuid;//项目guid
    
	public String getCstName() {
		return cstName;
	}
	public void setCstName(String cstName) {
		this.cstName = cstName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getHomeTel() {
		return homeTel;
	}
	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}
	public String getOfficeTel() {
		return officeTel;
	}
	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}
	public String getFaxNo() {
		return faxNo;
	}
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getIntentionArea() {
		return intentionArea;
	}
	public void setIntentionArea(String intentionArea) {
		this.intentionArea = intentionArea;
	}
	public String getIntentionPrice() {
		return intentionPrice;
	}
	public void setIntentionPrice(String intentionPrice) {
		this.intentionPrice = intentionPrice;
	}
	public String getIntentionRoom() {
		return intentionRoom;
	}
	public void setIntentionRoom(String intentionRoom) {
		this.intentionRoom = intentionRoom;
	}
	public String getHouseUse() {
		return houseUse;
	}
	public void setHouseUse(String houseUse) {
		this.houseUse = houseUse;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getBuyTimes() {
		return buyTimes;
	}
	public void setBuyTimes(String buyTimes) {
		this.buyTimes = buyTimes;
	}
	public String getAddrArea() {
		return addrArea;
	}
	public void setAddrArea(String addrArea) {
		this.addrArea = addrArea;
	}
	public String getWorkComp() {
		return workComp;
	}
	public void setWorkComp(String workComp) {
		this.workComp = workComp;
	}
	public String getFamilyInfo() {
		return familyInfo;
	}
	public void setFamilyInfo(String familyInfo) {
		this.familyInfo = familyInfo;
	}
	public String getInvestmentInfo() {
		return investmentInfo;
	}
	public void setInvestmentInfo(String investmentInfo) {
		this.investmentInfo = investmentInfo;
	}
	public String getCompetitor() {
		return competitor;
	}
	public void setCompetitor(String competitor) {
		this.competitor = competitor;
	}
	public String getIntentionType() {
		return intentionType;
	}
	public void setIntentionType(String intentionType) {
		this.intentionType = intentionType;
	}
	public String getCustomStatus() {
		return customStatus;
	}
	public void setCustomStatus(String customStatus) {
		this.customStatus = customStatus;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProjGuid() {
		return projGuid;
	}
	public void setProjGuid(String projGuid) {
		this.projGuid = projGuid;
	}
	public ZsCustomerImportVo() {
		super();
	}
	public ZsCustomerImportVo(String cstName, String tel, String homeTel,
			String officeTel, String faxNo, String sex, String label,
			String idNo, String intentionArea, String intentionPrice,
			String intentionRoom, String houseUse, String age, String buyTimes,
			String addrArea, String workComp, String familyInfo,
			String investmentInfo, String competitor, String intentionType,
			String customStatus, String username, String projGuid) {
		super();
		this.cstName = cstName;
		this.tel = tel;
		this.homeTel = homeTel;
		this.officeTel = officeTel;
		this.faxNo = faxNo;
		this.sex = sex;
		this.label = label;
		this.idNo = idNo;
		this.intentionArea = intentionArea;
		this.intentionPrice = intentionPrice;
		this.intentionRoom = intentionRoom;
		this.houseUse = houseUse;
		this.age = age;
		this.buyTimes = buyTimes;
		this.addrArea = addrArea;
		this.workComp = workComp;
		this.familyInfo = familyInfo;
		this.investmentInfo = investmentInfo;
		this.competitor = competitor;
		this.intentionType = intentionType;
		this.customStatus = customStatus;
		this.username = username;
		this.projGuid = projGuid;
	}
	
    
    

}
