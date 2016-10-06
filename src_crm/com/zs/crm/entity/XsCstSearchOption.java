package com.zs.crm.entity;

public class XsCstSearchOption {
	public String cstGuid;
	public String cstName;
	public String mobileTel;
	public String zygw;
	public int startIndex;
	public int length;//2016/3/6 起始项，每页长度
	public String cstType;
	/********2016.3.13**********/
	public String gfyx;
	public String status;
	public String ageGroup;
	public String gjfs;
	public String subMediaName;
	public String yxYeTai;
	public String yxArea;
	/***************************/
	public String mainMediaName;
	public String projGUID;
	public String userGUID;
	
	public String oppGUID;
	
	public String sortWay;//排序方式：跟进日期降序，跟进日期升序，创建日期降序，创建日期升序
	public String isAll;//是否显示全部：全部，我的关注，一般意向等
	
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
	public String getMainMediaName() {
		return mainMediaName;
	}
	public void setMainMediaName(String mainMediaName) {
		this.mainMediaName = mainMediaName;
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
	public String getZygw() {
		return zygw;
	}
	public void setZygw(String zygw) {
		this.zygw = zygw;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getCstType() {
		return cstType;
	}
	public void setCstType(String cstType) {
		this.cstType = cstType;
	}
	public String getGfyx() {
		return gfyx;
	}
	public void setGfyx(String gfyx) {
		this.gfyx = gfyx;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	public String getGjfs() {
		return gjfs;
	}
	public void setGjfs(String gjfs) {
		this.gjfs = gjfs;
	}
	public String getSubMediaName() {
		return subMediaName;
	}
	public void setSubMediaName(String subMediaName) {
		this.subMediaName = subMediaName;
	}
	public String getYxYeTai() {
		return yxYeTai;
	}
	public void setYxYeTai(String yxYeTai) {
		this.yxYeTai = yxYeTai;
	}
	public String getYxArea() {
		return yxArea;
	}
	public void setYxArea(String yxArea) {
		this.yxArea = yxArea;
	}
	public String getOppGUID() {
		return oppGUID;
	}
	public void setOppGUID(String oppGUID) {
		this.oppGUID = oppGUID;
	}
	public String getSortWay() {
		return sortWay;
	}
	public void setSortWay(String sortWay) {
		this.sortWay = sortWay;
	}
	public String getIsAll() {
		return isAll;
	}
	public void setIsAll(String isAll) {
		this.isAll = isAll;
	}
	
}
