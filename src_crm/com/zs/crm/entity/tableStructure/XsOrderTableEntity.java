package com.zs.crm.entity.tableStructure;

import java.io.Serializable;

public class XsOrderTableEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String orderGuid;//订单Guid
	private String buGuid;//单位Guid
	private String projGuid;//项目Guid
	private String tradeGuid;//交易Guid
	private String roomGuid;//房间Guid
	private String lastSaleGuid;//前次销售单Guid
	private String lastSaleType;//前次销售单类型
	private String potocolNo;//协议编号
	private String qsDate;//签署日期
	private String endDate;//失效日期
	private String bldArea;//建筑面积
	private String tnArea;//套内面积
	private String price;//建筑单价
	private String tnPrice;//套内单价
	private String total;//标准总价
	private String calMode;//计价方式
	private String payFormName;//付款方式
	private String discntValue;//折扣
	private String discntRemark;//折扣说明
	private String bldCjPrice;//建筑成交单价
	private String tnCjPrice;//套内成交单价
	private String roomTotal;//房间总价
	private String zxBz;//装修标准
	private String zxPrice;//装修价格
	private String zsTotal;//装修总价
	private String isZskbrht;//装修款是否并入合同
	private String fsTotal;//附属款
	private String rmbCjTotal;//协议总价（人民币）
	private String bz;//协议币种
	private String exRate;//协议汇率
	private String cjTotal;//协议总价
	private String earnest;//应收定金
	private String ajBank;//按揭银行
	private String ajTotal;//按揭贷款额
	private String ajYear;//按揭年限
	private String gjjBank;//公积金银行
	private String gjjTotal;//公积金贷款额
	private String gjjYear;//公积金年限
	private String orderType;//定单类型
	private String status;//状态
	private String ywy;//业务员
	private String cstGuid;//推荐人
	private String orderRemark;//附加条款
	private String createdOn;//创建时间
	private String createdBy;//创建人
	private String lastMender;//最后修改人
	private String modiDate;//最后修改时间
	private String auditBy;//审核人
	private String auditingDate;//审核时间
	private String isValid;//
	private String closeDate;//关闭日期
	private String closeReason;//关闭原因
	private String ywblDate;//业务办理日期
	private String remark;//备注
	private String areaStatus;//面积状态
	private String createdByGuid;//
	private String userGuidList;
	private String isCreatorUse;
	private String importDateSp5;
	private String djBldCjPrice;//底价建筑成交单价
	private String djTnCjPrice;//底价套内成交单价
	private String djRoomTotal;//底价房间总价
	private String cstRemark;//
	private String printDate;//
	private String isSigned;//
	private String isSt;
	private String scrmTimestampData;
	public String getOrderGuid() {
		return orderGuid;
	}
	public void setOrderGuid(String orderGuid) {
		this.orderGuid = orderGuid;
	}
	public String getBuGuid() {
		return buGuid;
	}
	public void setBuGuid(String buGuid) {
		this.buGuid = buGuid;
	}
	public String getProjGuid() {
		return projGuid;
	}
	public void setProjGuid(String projGuid) {
		this.projGuid = projGuid;
	}
	public String getTradeGuid() {
		return tradeGuid;
	}
	public void setTradeGuid(String tradeGuid) {
		this.tradeGuid = tradeGuid;
	}
	public String getRoomGuid() {
		return roomGuid;
	}
	public void setRoomGuid(String roomGuid) {
		this.roomGuid = roomGuid;
	}
	public String getLastSaleGuid() {
		return lastSaleGuid;
	}
	public void setLastSaleGuid(String lastSaleGuid) {
		this.lastSaleGuid = lastSaleGuid;
	}
	public String getLastSaleType() {
		return lastSaleType;
	}
	public void setLastSaleType(String lastSaleType) {
		this.lastSaleType = lastSaleType;
	}
	public String getPotocolNo() {
		return potocolNo;
	}
	public void setPotocolNo(String potocolNo) {
		this.potocolNo = potocolNo;
	}
	public String getQsDate() {
		return qsDate;
	}
	public void setQsDate(String qsDate) {
		this.qsDate = qsDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getBldArea() {
		return bldArea;
	}
	public void setBldArea(String bldArea) {
		this.bldArea = bldArea;
	}
	public String getTnArea() {
		return tnArea;
	}
	public void setTnArea(String tnArea) {
		this.tnArea = tnArea;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTnPrice() {
		return tnPrice;
	}
	public void setTnPrice(String tnPrice) {
		this.tnPrice = tnPrice;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getCalMode() {
		return calMode;
	}
	public void setCalMode(String calMode) {
		this.calMode = calMode;
	}
	public String getPayFormName() {
		return payFormName;
	}
	public void setPayFormName(String payFormName) {
		this.payFormName = payFormName;
	}
	public String getDiscntValue() {
		return discntValue;
	}
	public void setDiscntValue(String discntValue) {
		this.discntValue = discntValue;
	}
	public String getDiscntRemark() {
		return discntRemark;
	}
	public void setDiscntRemark(String discntRemark) {
		this.discntRemark = discntRemark;
	}
	public String getBldCjPrice() {
		return bldCjPrice;
	}
	public void setBldCjPrice(String bldCjPrice) {
		this.bldCjPrice = bldCjPrice;
	}
	public String getTnCjPrice() {
		return tnCjPrice;
	}
	public void setTnCjPrice(String tnCjPrice) {
		this.tnCjPrice = tnCjPrice;
	}
	public String getRoomTotal() {
		return roomTotal;
	}
	public void setRoomTotal(String roomTotal) {
		this.roomTotal = roomTotal;
	}
	public String getZxBz() {
		return zxBz;
	}
	public void setZxBz(String zxBz) {
		this.zxBz = zxBz;
	}
	public String getZxPrice() {
		return zxPrice;
	}
	public void setZxPrice(String zxPrice) {
		this.zxPrice = zxPrice;
	}
	public String getZsTotal() {
		return zsTotal;
	}
	public void setZsTotal(String zsTotal) {
		this.zsTotal = zsTotal;
	}
	public String getIsZskbrht() {
		return isZskbrht;
	}
	public void setIsZskbrht(String isZskbrht) {
		this.isZskbrht = isZskbrht;
	}
	public String getFsTotal() {
		return fsTotal;
	}
	public void setFsTotal(String fsTotal) {
		this.fsTotal = fsTotal;
	}
	public String getRmbCjTotal() {
		return rmbCjTotal;
	}
	public void setRmbCjTotal(String rmbCjTotal) {
		this.rmbCjTotal = rmbCjTotal;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getExRate() {
		return exRate;
	}
	public void setExRate(String exRate) {
		this.exRate = exRate;
	}
	public String getCjTotal() {
		return cjTotal;
	}
	public void setCjTotal(String cjTotal) {
		this.cjTotal = cjTotal;
	}
	public String getEarnest() {
		return earnest;
	}
	public void setEarnest(String earnest) {
		this.earnest = earnest;
	}
	public String getAjBank() {
		return ajBank;
	}
	public void setAjBank(String ajBank) {
		this.ajBank = ajBank;
	}
	public String getAjTotal() {
		return ajTotal;
	}
	public void setAjTotal(String ajTotal) {
		this.ajTotal = ajTotal;
	}
	public String getAjYear() {
		return ajYear;
	}
	public void setAjYear(String ajYear) {
		this.ajYear = ajYear;
	}
	public String getGjjBank() {
		return gjjBank;
	}
	public void setGjjBank(String gjjBank) {
		this.gjjBank = gjjBank;
	}
	public String getGjjTotal() {
		return gjjTotal;
	}
	public void setGjjTotal(String gjjTotal) {
		this.gjjTotal = gjjTotal;
	}
	public String getGjjYear() {
		return gjjYear;
	}
	public void setGjjYear(String gjjYear) {
		this.gjjYear = gjjYear;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getYwy() {
		return ywy;
	}
	public void setYwy(String ywy) {
		this.ywy = ywy;
	}
	public String getCstGuid() {
		return cstGuid;
	}
	public void setCstGuid(String cstGuid) {
		this.cstGuid = cstGuid;
	}
	public String getOrderRemark() {
		return orderRemark;
	}
	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
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
	public String getLastMender() {
		return lastMender;
	}
	public void setLastMender(String lastMender) {
		this.lastMender = lastMender;
	}
	public String getModiDate() {
		return modiDate;
	}
	public void setModiDate(String modiDate) {
		this.modiDate = modiDate;
	}
	public String getAuditBy() {
		return auditBy;
	}
	public void setAuditBy(String auditBy) {
		this.auditBy = auditBy;
	}
	public String getAuditingDate() {
		return auditingDate;
	}
	public void setAuditingDate(String auditingDate) {
		this.auditingDate = auditingDate;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
	public String getCloseReason() {
		return closeReason;
	}
	public void setCloseReason(String closeReason) {
		this.closeReason = closeReason;
	}
	public String getYwblDate() {
		return ywblDate;
	}
	public void setYwblDate(String ywblDate) {
		this.ywblDate = ywblDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAreaStatus() {
		return areaStatus;
	}
	public void setAreaStatus(String areaStatus) {
		this.areaStatus = areaStatus;
	}
	public String getCreatedByGuid() {
		return createdByGuid;
	}
	public void setCreatedByGuid(String createdByGuid) {
		this.createdByGuid = createdByGuid;
	}
	public String getUserGuidList() {
		return userGuidList;
	}
	public void setUserGuidList(String userGuidList) {
		this.userGuidList = userGuidList;
	}
	public String getIsCreatorUse() {
		return isCreatorUse;
	}
	public void setIsCreatorUse(String isCreatorUse) {
		this.isCreatorUse = isCreatorUse;
	}
	public String getImportDateSp5() {
		return importDateSp5;
	}
	public void setImportDateSp5(String importDateSp5) {
		this.importDateSp5 = importDateSp5;
	}
	public String getDjBldCjPrice() {
		return djBldCjPrice;
	}
	public void setDjBldCjPrice(String djBldCjPrice) {
		this.djBldCjPrice = djBldCjPrice;
	}
	public String getDjTnCjPrice() {
		return djTnCjPrice;
	}
	public void setDjTnCjPrice(String djTnCjPrice) {
		this.djTnCjPrice = djTnCjPrice;
	}
	public String getDjRoomTotal() {
		return djRoomTotal;
	}
	public void setDjRoomTotal(String djRoomTotal) {
		this.djRoomTotal = djRoomTotal;
	}
	public String getCstRemark() {
		return cstRemark;
	}
	public void setCstRemark(String cstRemark) {
		this.cstRemark = cstRemark;
	}
	public String getPrintDate() {
		return printDate;
	}
	public void setPrintDate(String printDate) {
		this.printDate = printDate;
	}
	public String getIsSigned() {
		return isSigned;
	}
	public void setIsSigned(String isSigned) {
		this.isSigned = isSigned;
	}
	public String getIsSt() {
		return isSt;
	}
	public void setIsSt(String isSt) {
		this.isSt = isSt;
	}
	public String getScrmTimestampData() {
		return scrmTimestampData;
	}
	public void setScrmTimestampData(String scrmTimestampData) {
		this.scrmTimestampData = scrmTimestampData;
	}
	
	
}
