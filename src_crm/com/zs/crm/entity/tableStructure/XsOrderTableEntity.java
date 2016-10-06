package com.zs.crm.entity.tableStructure;

import java.io.Serializable;

public class XsOrderTableEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String orderGuid;//����Guid
	private String buGuid;//��λGuid
	private String projGuid;//��ĿGuid
	private String tradeGuid;//����Guid
	private String roomGuid;//����Guid
	private String lastSaleGuid;//ǰ�����۵�Guid
	private String lastSaleType;//ǰ�����۵�����
	private String potocolNo;//Э����
	private String qsDate;//ǩ������
	private String endDate;//ʧЧ����
	private String bldArea;//�������
	private String tnArea;//�������
	private String price;//��������
	private String tnPrice;//���ڵ���
	private String total;//��׼�ܼ�
	private String calMode;//�Ƽ۷�ʽ
	private String payFormName;//���ʽ
	private String discntValue;//�ۿ�
	private String discntRemark;//�ۿ�˵��
	private String bldCjPrice;//�����ɽ�����
	private String tnCjPrice;//���ڳɽ�����
	private String roomTotal;//�����ܼ�
	private String zxBz;//װ�ޱ�׼
	private String zxPrice;//װ�޼۸�
	private String zsTotal;//װ���ܼ�
	private String isZskbrht;//װ�޿��Ƿ����ͬ
	private String fsTotal;//������
	private String rmbCjTotal;//Э���ܼۣ�����ң�
	private String bz;//Э�����
	private String exRate;//Э�����
	private String cjTotal;//Э���ܼ�
	private String earnest;//Ӧ�ն���
	private String ajBank;//��������
	private String ajTotal;//���Ҵ����
	private String ajYear;//��������
	private String gjjBank;//����������
	private String gjjTotal;//����������
	private String gjjYear;//����������
	private String orderType;//��������
	private String status;//״̬
	private String ywy;//ҵ��Ա
	private String cstGuid;//�Ƽ���
	private String orderRemark;//��������
	private String createdOn;//����ʱ��
	private String createdBy;//������
	private String lastMender;//����޸���
	private String modiDate;//����޸�ʱ��
	private String auditBy;//�����
	private String auditingDate;//���ʱ��
	private String isValid;//
	private String closeDate;//�ر�����
	private String closeReason;//�ر�ԭ��
	private String ywblDate;//ҵ���������
	private String remark;//��ע
	private String areaStatus;//���״̬
	private String createdByGuid;//
	private String userGuidList;
	private String isCreatorUse;
	private String importDateSp5;
	private String djBldCjPrice;//�׼۽����ɽ�����
	private String djTnCjPrice;//�׼����ڳɽ�����
	private String djRoomTotal;//�׼۷����ܼ�
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
