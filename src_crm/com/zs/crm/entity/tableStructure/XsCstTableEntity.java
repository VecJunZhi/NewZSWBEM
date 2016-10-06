package com.zs.crm.entity.tableStructure;

import java.io.Serializable;

public class XsCstTableEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String cstGuid;//客户GUID
    private String cstName;//客户姓名
    private String cardId;//证件号码
    private String officeTel;//办公电话
    private String homeTel;//住宅电话
    private String mobileTel;//手机
    private String email;//邮件
    private String postCode;//邮编
    private String address;//通讯地址
    private String cstType;//客户类型，内容有：个人、公司
    private String gender;//性别
    private String cardType;//证件类型
    private String fax;//传真
    private String workAddr;//工作单位
    private String country;//国家/地区
    private String province;//省份/州
    private String city;//城市
    private String regional;//区
    private String road;//路
    private String addrNo;//地址号码
    private String description;//描述
    private String createdOn;//创建时间
    private String createdBy;//创建人
    private String bizLicence;//营业执照
    private String corporation;//法人代表
    private String modifyOn;//修改时间--当前记录的最后一次修改时间
    private String modifyBy;//修改人--当前记录的最后一次修改人
    private String birthDate;//出生日期
    private String cstCode;//客户编码
    private String nationality;//国籍
    private String preferred;//最佳联系方式
    private String refuseInfo;//不接受的资料
    private String firstContact;//第一联系人
    private String companyPhone;//公司电话
    private String createdByGuid;//创建人的guid
    private Byte isCreatorUse;//默认1
    private Byte isReceiveSms;//默认1
    private String nativeplace;//籍贯
    private String cardId15;//15位身份证号
    private String firstName;//名
    private String lastName;//姓
    private Byte isYz;//默认0
    private String cardAddress;//身份证所在地址
    private String scrmTimestampData;//时间戳
    private String defaultTel;//2016.3.9记录默认电话
    
    
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCstType() {
		return cstType;
	}
	public void setCstType(String cstType) {
		this.cstType = cstType;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getWorkAddr() {
		return workAddr;
	}
	public void setWorkAddr(String workAddr) {
		this.workAddr = workAddr;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRegional() {
		return regional;
	}
	public void setRegional(String regional) {
		this.regional = regional;
	}
	public String getRoad() {
		return road;
	}
	public void setRoad(String road) {
		this.road = road;
	}
	public String getAddrNo() {
		return addrNo;
	}
	public void setAddrNo(String addrNo) {
		this.addrNo = addrNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getBizLicence() {
		return bizLicence;
	}
	public void setBizLicence(String bizLicence) {
		this.bizLicence = bizLicence;
	}
	public String getCorporation() {
		return corporation;
	}
	public void setCorporation(String corporation) {
		this.corporation = corporation;
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
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getCstCode() {
		return cstCode;
	}
	public void setCstCode(String cstCode) {
		this.cstCode = cstCode;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getPreferred() {
		return preferred;
	}
	public void setPreferred(String preferred) {
		this.preferred = preferred;
	}
	public String getRefuseInfo() {
		return refuseInfo;
	}
	public void setRefuseInfo(String refuseInfo) {
		this.refuseInfo = refuseInfo;
	}
	public String getFirstContact() {
		return firstContact;
	}
	public void setFirstContact(String firstContact) {
		this.firstContact = firstContact;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getCreatedByGuid() {
		return createdByGuid;
	}
	public void setCreatedByGuid(String createdByGuid) {
		this.createdByGuid = createdByGuid;
	}
	public Byte getIsCreatorUse() {
		return isCreatorUse;
	}
	public void setIsCreatorUse(Byte isCreatorUse) {
		this.isCreatorUse = isCreatorUse;
	}
	
	public Byte getIsReceiveSms() {
		return isReceiveSms;
	}
	public void setIsReceiveSms(Byte isReceiveSms) {
		this.isReceiveSms = isReceiveSms;
	}
	public String getNativeplace() {
		return nativeplace;
	}
	public void setNativeplace(String nativeplace) {
		this.nativeplace = nativeplace;
	}
	public String getCardId15() {
		return cardId15;
	}
	public void setCardId15(String cardId15) {
		this.cardId15 = cardId15;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Byte getIsYz() {
		return isYz;
	}
	public void setIsYz(Byte isYz) {
		this.isYz = isYz;
	}
	public String getCardAddress() {
		return cardAddress;
	}
	public void setCardAddress(String cardAddress) {
		this.cardAddress = cardAddress;
	}
	public String getScrmTimestampData() {
		return scrmTimestampData;
	}
	public void setScrmTimestampData(String scrmTimestampData) {
		this.scrmTimestampData = scrmTimestampData;
	}
	public String getDefaultTel() {
		return defaultTel;
	}
	public void setDefaultTel(String defaultTel) {
		this.defaultTel = defaultTel;
	}
	
    

}
