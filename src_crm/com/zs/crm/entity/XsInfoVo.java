package com.zs.crm.entity;

import java.io.Serializable;

import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class XsInfoVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//dbo.
	public String id;
	//public String deptId;
	public String cusId;//
	public String cusName;//
	public String cusNo;//
	public String compSymbol;//
	public String legalPerson;//
	public String addr;//
	public String addrArea;//
	public String addrRoad;//
	public String person;//
	public String tel;//
	public String faxNo;//
	public String zipCode;//
	public String accountBank;//
	public String accountNo;//
	public String sex;//
	public String birthday;//
	public String email;//Email
	public String homePage;//
	public String residenceAddr;//
	public String idName;//
	public String idNo;//
	public String workComp;//
	public String appendInfo;//
	public String identitys;//
	public String nationality;//
	public String classes;//
	public String levelId;//
	public String cusDesp;//
	public String age;//
	public String cusAbb;//
	public String phoneNo;//
	public String monthIncome;//
	public String yearIncome;//
	public String firstVisWay;//
	public String accountCode;//
	public String accountName;//
	public String vipFee;//
	public String billNo;//
	public String cardNo;//
	public String followWay;//
	public String nextFollowDate;//
	public String intentionType;//
	public String houseUse;//
	public String factor;//
	public String workType;//
	public String visitTimes;//
	public String familyInfo;//
	public String investmentInfo;//
	public String label; // 
	public String intentionArea; //
	public String buyTimes; //
	public String competitor; //
	public String intentionPrice;// 
	public String intentionProduct;// 
	public String intentionRoom;//
	public String valided;// 是否有效  define by  yzj
	public String invalidDate;//无效日期点 define by yzj
	
	public String customStatus;//
	public String purposeItem;//
	//
	public String followPersionId;//
	public String followDate;//
	public String followTime;//
	public String followInfo;//
	public String followWayEach;//
	
	//dbo.
	public String detpId;//
	public String detpNo;//
	public String deptName;//
	public String note;//
	public String manager;//
	
	//dbo
	public String employeeId;//
	public String employeeNo;//
	public String employeeName;//
	public String duty;//
	public String homeAddress;//
	public String bpNo;//
	public String education;//
	public String school;//
	public String major;//
	public String nativePlace;//
	public String entryDate;//
	public String leaveDate;//
	public String position;//
	
	//log
	public String logType;

	//defined by ours
	public String cusNameOrTel;

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getCusId() {
		return cusId;
	}



	public void setCusId(String cusId) {
		this.cusId = cusId;
	}



	public String getCusName() {
		return cusName;
	}



	public void setCusName(String cusName) {
		this.cusName = cusName;
	}



	public String getCusNo() {
		return cusNo;
	}



	public void setCusNo(String cusNo) {
		this.cusNo = cusNo;
	}



	public String getCompSymbol() {
		return compSymbol;
	}



	public void setCompSymbol(String compSymbol) {
		this.compSymbol = compSymbol;
	}



	public String getFollowWayEach() {
		return followWayEach;
	}



	public void setFollowWayEach(String followWayEach) {
		this.followWayEach = followWayEach;
	}



	public String getLegalPerson() {
		return legalPerson;
	}



	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}



	public String getAddr() {
		return addr;
	}



	public void setAddr(String addr) {
		this.addr = addr;
	}



	public String getAddrArea() {
		return addrArea;
	}



	public void setAddrArea(String addrArea) {
		this.addrArea = addrArea;
	}



	public String getAddrRoad() {
		return addrRoad;
	}



	public void setAddrRoad(String addrRoad) {
		this.addrRoad = addrRoad;
	}



	public String getPerson() {
		return person;
	}



	public void setPerson(String person) {
		this.person = person;
	}



	public String getTel() {
		return tel;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}



	public String getFaxNo() {
		return faxNo;
	}



	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}



	public String getZipCode() {
		return zipCode;
	}



	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}



	public String getAccountBank() {
		return accountBank;
	}



	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}



	public String getAccountNo() {
		return accountNo;
	}



	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	public String getBirthday() {
		return birthday;
	}



	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getHomePage() {
		return homePage;
	}



	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}



	public String getResidenceAddr() {
		return residenceAddr;
	}



	public void setResidenceAddr(String residenceAddr) {
		this.residenceAddr = residenceAddr;
	}



	public String getIdName() {
		return idName;
	}



	public void setIdName(String idName) {
		this.idName = idName;
	}



	public String getIdNo() {
		return idNo;
	}



	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}



	public String getWorkComp() {
		return workComp;
	}



	public void setWorkComp(String workComp) {
		this.workComp = workComp;
	}



	public String getAppendInfo() {
		return appendInfo;
	}



	public void setAppendInfo(String appendInfo) {
		this.appendInfo = appendInfo;
	}



	public String getIdentitys() {
		return identitys;
	}



	public void setIdentitys(String identitys) {
		this.identitys = identitys;
	}



	public String getNationality() {
		return nationality;
	}



	public void setNationality(String nationality) {
		this.nationality = nationality;
	}



	public String getClasses() {
		return classes;
	}



	public void setClasses(String classes) {
		this.classes = classes;
	}



	public String getLevelId() {
		return levelId;
	}



	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}



	public String getCusDesp() {
		return cusDesp;
	}



	public void setCusDesp(String cusDesp) {
		this.cusDesp = cusDesp;
	}



	public String getAge() {
		return age;
	}



	public void setAge(String age) {
		this.age = age;
	}



	public String getCusAbb() {
		return cusAbb;
	}



	public void setCusAbb(String cusAbb) {
		this.cusAbb = cusAbb;
	}



	public String getPhoneNo() {
		return phoneNo;
	}



	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}



	public String getMonthIncome() {
		return monthIncome;
	}



	public void setMonthIncome(String monthIncome) {
		this.monthIncome = monthIncome;
	}



	public String getYearIncome() {
		return yearIncome;
	}



	public void setYearIncome(String yearIncome) {
		this.yearIncome = yearIncome;
	}



	public String getFirstVisWay() {
		return firstVisWay;
	}



	public void setFirstVisWay(String firstVisWay) {
		this.firstVisWay = firstVisWay;
	}



	public String getAccountCode() {
		return accountCode;
	}



	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}



	public String getAccountName() {
		return accountName;
	}



	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}



	public String getVipFee() {
		return vipFee;
	}



	public void setVipFee(String vipFee) {
		this.vipFee = vipFee;
	}



	public String getBillNo() {
		return billNo;
	}



	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}



	public String getCardNo() {
		return cardNo;
	}



	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}



	public String getFollowWay() {
		return followWay;
	}



	public void setFollowWay(String followWay) {
		this.followWay = followWay;
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



	public String getHouseUse() {
		return houseUse;
	}



	public void setHouseUse(String houseUse) {
		this.houseUse = houseUse;
	}



	public String getFactor() {
		return factor;
	}



	public void setFactor(String factor) {
		this.factor = factor;
	}



	public String getWorkType() {
		return workType;
	}



	public void setWorkType(String workType) {
		this.workType = workType;
	}



	public String getVisitTimes() {
		return visitTimes;
	}



	public void setVisitTimes(String visitTimes) {
		this.visitTimes = visitTimes;
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



	public String getLabel() {
		return label;
	}



	public void setLabel(String label) {
		this.label = label;
	}



	public String getIntentionArea() {
		return intentionArea;
	}



	public String getValided() {
		return valided;
	}



	public void setValided(String valided) {
		this.valided = valided;
	}



	public void setIntentionArea(String intentionArea) {
		this.intentionArea = intentionArea;
	}



	public String getBuyTimes() {
		return buyTimes;
	}



	public void setBuyTimes(String buyTimes) {
		this.buyTimes = buyTimes;
	}



	public String getCompetitor() {
		return competitor;
	}



	public void setCompetitor(String competitor) {
		this.competitor = competitor;
	}



	public String getIntentionPrice() {
		return intentionPrice;
	}



	public void setIntentionPrice(String intentionPrice) {
		this.intentionPrice = intentionPrice;
	}



	public String getIntentionProduct() {
		return intentionProduct;
	}



	public void setIntentionProduct(String intentionProduct) {
		this.intentionProduct = intentionProduct;
	}



	public String getIntentionRoom() {
		return intentionRoom;
	}



	public void setIntentionRoom(String intentionRoom) {
		this.intentionRoom = intentionRoom;
	}



	public String getFollowPersionId() {
		return followPersionId;
	}



	public void setFollowPersionId(String followPersionId) {
		this.followPersionId = followPersionId;
	}



	public String getFollowDate() {
		return followDate;
	}



	public void setFollowDate(String followDate) {
		this.followDate = followDate;
	}



	public String getFollowTime() {
		return followTime;
	}



	public void setFollowTime(String followTime) {
		this.followTime = followTime;
	}



	public String getFollowInfo() {
		return followInfo;
	}



	public void setFollowInfo(String followInfo) {
		this.followInfo = followInfo;
	}
	public String getPurposeItem() {
		return purposeItem;
	}

	public void setPurposeItem(String purposeItem) {
		this.purposeItem = purposeItem;
	}



	public String getDetpId() {
		return detpId;
	}



	public void setDetpId(String detpId) {
		this.detpId = detpId;
	}



	public String getDetpNo() {
		return detpNo;
	}



	public void setDetpNo(String detpNo) {
		this.detpNo = detpNo;
	}



	public String getDeptName() {
		return deptName;
	}



	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}



	public String getNote() {
		return note;
	}



	public String getCustomStatus() {
		return customStatus;
	}



	public void setCustomStatus(String customStatus) {
		this.customStatus = customStatus;
	}



	public void setNote(String note) {
		this.note = note;
	}



	public String getManager() {
		return manager;
	}



	public void setManager(String manager) {
		this.manager = manager;
	}



	public String getEmployeeId() {
		return employeeId;
	}



	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}



	public String getEmployeeNo() {
		return employeeNo;
	}



	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}



	public String getEmployeeName() {
		return employeeName;
	}



	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}



	public String getDuty() {
		return duty;
	}



	public void setDuty(String duty) {
		this.duty = duty;
	}



	public String getHomeAddress() {
		return homeAddress;
	}



	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}



	public String getBpNo() {
		return bpNo;
	}



	public void setBpNo(String bpNo) {
		this.bpNo = bpNo;
	}



	public String getEducation() {
		return education;
	}



	public void setEducation(String education) {
		this.education = education;
	}



	public String getSchool() {
		return school;
	}



	public void setSchool(String school) {
		this.school = school;
	}



	public String getMajor() {
		return major;
	}



	public void setMajor(String major) {
		this.major = major;
	}



	public String getNativePlace() {
		return nativePlace;
	}



	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}



	public String getEntryDate() {
		return entryDate;
	}



	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}



	public String getLeaveDate() {
		return leaveDate;
	}



	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}



	public String getPosition() {
		return position;
	}



	public void setPosition(String position) {
		this.position = position;
	}



	public String getCusNameOrTel() {
		return cusNameOrTel;
	}



	public void setCusNameOrTel(String cusNameOrTel) {
		this.cusNameOrTel = cusNameOrTel;
	}



	public String getLogType() {
		return logType;
	}



	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getInvalidDate() {
		return invalidDate;
	}



	public void setInvalidDate(String invalidDate) {
		this.invalidDate = invalidDate;
	}



	public XsInfoVo() {
		
		super();
	}



	public XsInfoVo(String cusId) {
		super();
		this.cusId = cusId;
	}



	public XsInfoVo(String cusName, String cusNo, String compSymbol,
			String legalPerson, String addr, String addrArea, String addrRoad,
			String person, String tel, String faxNo, String zipCode,
			String accountBank, String accountNo, String sex, String birthday,
			String email, String homePage, String residenceAddr, String idName,
			String idNo, String workComp, String appendInfo, String identitys,
			String nationality, String classes, String levelId, String cusDesp,
			String age, String cusAbb, String phoneNo, String monthIncome,
			String yearIncome, String firstVisWay, String accountCode,
			String accountName, String vipFee, String billNo, String cardNo,
			String followWay, String nextFollowDate, String intentionType,
			String houseUse, String factor, String workType, String visitTimes,
			String familyInfo, String investmentInfo) {
		super();
		this.cusName = cusName;
		this.cusNo = cusNo;
		this.compSymbol = compSymbol;
		this.legalPerson = legalPerson;
		this.addr = addr;
		this.addrArea = addrArea;
		this.addrRoad = addrRoad;
		this.person = person;
		this.tel = tel;
		this.faxNo = faxNo;
		this.zipCode = zipCode;
		this.accountBank = accountBank;
		this.accountNo = accountNo;
		this.sex = sex;
		this.birthday = birthday;
		this.email = email;
		this.homePage = homePage;
		this.residenceAddr = residenceAddr;
		this.idName = idName;
		this.idNo = idNo;
		this.workComp = workComp;
		this.appendInfo = appendInfo;
		this.identitys = identitys;
		this.nationality = nationality;
		this.classes = classes;
		this.levelId = levelId;
		this.cusDesp = cusDesp;
		this.age = age;
		this.cusAbb = cusAbb;
		this.phoneNo = phoneNo;
		this.monthIncome = monthIncome;
		this.yearIncome = yearIncome;
		this.firstVisWay = firstVisWay;
		this.accountCode = accountCode;
		this.accountName = accountName;
		this.vipFee = vipFee;
		this.billNo = billNo;
		this.cardNo = cardNo;
		this.followWay = followWay;
		this.nextFollowDate = nextFollowDate;
		this.intentionType = intentionType;
		this.houseUse = houseUse;
		this.factor = factor;
		this.workType = workType;
		this.visitTimes = visitTimes;
		this.familyInfo = familyInfo;
		this.investmentInfo = investmentInfo;
	}



	public XsInfoVo(String cusName, String cusNo, String compSymbol,
			String legalPerson, String addr, String addrArea, String addrRoad,
			String person, String tel, String faxNo, String zipCode,
			String accountBank, String accountNo, String sex, String birthday,
			String email, String homePage, String residenceAddr, String idName,
			String idNo, String workComp, String appendInfo, String identitys,
			String nationality, String classes, String levelId, String cusDesp,
			String age, String cusAbb, String phoneNo, String monthIncome,
			String yearIncome, String firstVisWay, String accountCode,
			String accountName, String vipFee, String billNo, String cardNo,
			String followWay, String nextFollowDate, String intentionType,
			String houseUse, String factor, String workType, String visitTimes,
			String familyInfo, String investmentInfo, String label,
			String intentionArea, String buyTimes, String competitor,
			String intentionPrice, String intentionProduct,
			String intentionRoom, String customStatus, String purposeItem,
			String followPersionId, String followDate, String followTime,
			String followInfo, String followWayEach, String detpId,
			String detpNo, String deptName, String note, String manager,
			String employeeId, String employeeNo, String employeeName,
			String duty, String homeAddress, String bpNo, String education,
			String school, String major, String nativePlace, String entryDate,
			String leaveDate, String position, String cusNameOrTel) {
		super();
		this.cusName = cusName;
		this.cusNo = cusNo;
		this.compSymbol = compSymbol;
		this.legalPerson = legalPerson;
		this.addr = addr;
		this.addrArea = addrArea;
		this.addrRoad = addrRoad;
		this.person = person;
		this.tel = tel;
		this.faxNo = faxNo;
		this.zipCode = zipCode;
		this.accountBank = accountBank;
		this.accountNo = accountNo;
		this.sex = sex;
		this.birthday = birthday;
		this.email = email;
		this.homePage = homePage;
		this.residenceAddr = residenceAddr;
		this.idName = idName;
		this.idNo = idNo;
		this.workComp = workComp;
		this.appendInfo = appendInfo;
		this.identitys = identitys;
		this.nationality = nationality;
		this.classes = classes;
		this.levelId = levelId;
		this.cusDesp = cusDesp;
		this.age = age;
		this.cusAbb = cusAbb;
		this.phoneNo = phoneNo;
		this.monthIncome = monthIncome;
		this.yearIncome = yearIncome;
		this.firstVisWay = firstVisWay;
		this.accountCode = accountCode;
		this.accountName = accountName;
		this.vipFee = vipFee;
		this.billNo = billNo;
		this.cardNo = cardNo;
		this.followWay = followWay;
		this.nextFollowDate = nextFollowDate;
		this.intentionType = intentionType;
		this.houseUse = houseUse;
		this.factor = factor;
		this.workType = workType;
		this.visitTimes = visitTimes;
		this.familyInfo = familyInfo;
		this.investmentInfo = investmentInfo;
		this.label = label;
		this.intentionArea = intentionArea;
		this.buyTimes = buyTimes;
		this.competitor = competitor;
		this.intentionPrice = intentionPrice;
		this.intentionProduct = intentionProduct;
		this.intentionRoom = intentionRoom;
		this.customStatus = customStatus;
		this.purposeItem = purposeItem;
		this.followPersionId = followPersionId;
		this.followDate = followDate;
		this.followTime = followTime;
		this.followInfo = followInfo;
		this.followWayEach = followWayEach;
		this.detpId = detpId;
		this.detpNo = detpNo;
		this.deptName = deptName;
		this.note = note;
		this.manager = manager;
		this.employeeId = employeeId;
		this.employeeNo = employeeNo;
		this.employeeName = employeeName;
		this.duty = duty;
		this.homeAddress = homeAddress;
		this.bpNo = bpNo;
		this.education = education;
		this.school = school;
		this.major = major;
		this.nativePlace = nativePlace;
		this.entryDate = entryDate;
		this.leaveDate = leaveDate;
		this.position = position;
		this.cusNameOrTel = cusNameOrTel;
	}



	public XsInfoVo(String age, String followWay, String intentionType,
			String investmentInfo, String customStatus, String firstVisWay,
			String purposeItem) {
		super();
		this.age = age;
		this.followWay = followWay;
		this.intentionType = intentionType;
		this.investmentInfo = investmentInfo;
		this.customStatus = customStatus;
		this.firstVisWay = firstVisWay;
		this.purposeItem = purposeItem;
	}
	
	
	
	

}
