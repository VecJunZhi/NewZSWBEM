package com.zs.crm.entity.tableStructure;

import java.io.Serializable;

public class XsCstTableEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String cstGuid;//�ͻ�GUID
    private String cstName;//�ͻ�����
    private String cardId;//֤������
    private String officeTel;//�칫�绰
    private String homeTel;//סլ�绰
    private String mobileTel;//�ֻ�
    private String email;//�ʼ�
    private String postCode;//�ʱ�
    private String address;//ͨѶ��ַ
    private String cstType;//�ͻ����ͣ������У����ˡ���˾
    private String gender;//�Ա�
    private String cardType;//֤������
    private String fax;//����
    private String workAddr;//������λ
    private String country;//����/����
    private String province;//ʡ��/��
    private String city;//����
    private String regional;//��
    private String road;//·
    private String addrNo;//��ַ����
    private String description;//����
    private String createdOn;//����ʱ��
    private String createdBy;//������
    private String bizLicence;//Ӫҵִ��
    private String corporation;//���˴���
    private String modifyOn;//�޸�ʱ��--��ǰ��¼�����һ���޸�ʱ��
    private String modifyBy;//�޸���--��ǰ��¼�����һ���޸���
    private String birthDate;//��������
    private String cstCode;//�ͻ�����
    private String nationality;//����
    private String preferred;//�����ϵ��ʽ
    private String refuseInfo;//�����ܵ�����
    private String firstContact;//��һ��ϵ��
    private String companyPhone;//��˾�绰
    private String createdByGuid;//�����˵�guid
    private Byte isCreatorUse;//Ĭ��1
    private Byte isReceiveSms;//Ĭ��1
    private String nativeplace;//����
    private String cardId15;//15λ���֤��
    private String firstName;//��
    private String lastName;//��
    private Byte isYz;//Ĭ��0
    private String cardAddress;//���֤���ڵ�ַ
    private String scrmTimestampData;//ʱ���
    private String defaultTel;//2016.3.9��¼Ĭ�ϵ绰
    
    
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
