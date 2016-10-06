package com.zs.crm.entity.tableStructure;

import java.io.Serializable;

public class XsFeeTableEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String feeGuid;//公款Guid
	private String tradeGuid;//交易Guid
	private Integer sequence;//序号
	private String flag;//
    private String lastDate;//付款期限
    private String itemType;//款项类型
    private String itemName;//款项名称
    private String amount;//金额
    private String bz;//币种
    private String exrate;//汇率
    private String rmbAmount;//人民币金额
    private String rmbYe;//人民币余额
    private String jmLateFee;//
    private String remark;
    private String ye;//余额
    private String isChg;
    private String outAmount;
    private String outRmbAmount;
    private String payEvent;
    private String payLagQty;
    private String payLagUnit;
    private String dsAmount;//多收金额
    private String rmbDsAmount;//人民币多收金额
    private String buGuid;
    private String projGuid;
    private String scrmTimestampData;
	public String getFeeGuid() {
		return feeGuid;
	}
	public void setFeeGuid(String feeGuid) {
		this.feeGuid = feeGuid;
	}
	public String getTradeGuid() {
		return tradeGuid;
	}
	public void setTradeGuid(String tradeGuid) {
		this.tradeGuid = tradeGuid;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getExrate() {
		return exrate;
	}
	public void setExrate(String exrate) {
		this.exrate = exrate;
	}
	public String getRmbAmount() {
		return rmbAmount;
	}
	public void setRmbAmount(String rmbAmount) {
		this.rmbAmount = rmbAmount;
	}
	public String getRmbYe() {
		return rmbYe;
	}
	public void setRmbYe(String rmbYe) {
		this.rmbYe = rmbYe;
	}
	public String getJmLateFee() {
		return jmLateFee;
	}
	public void setJmLateFee(String jmLateFee) {
		this.jmLateFee = jmLateFee;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getYe() {
		return ye;
	}
	public void setYe(String ye) {
		this.ye = ye;
	}
	public String getIsChg() {
		return isChg;
	}
	public void setIsChg(String isChg) {
		this.isChg = isChg;
	}
	public String getOutAmount() {
		return outAmount;
	}
	public void setOutAmount(String outAmount) {
		this.outAmount = outAmount;
	}
	public String getOutRmbAmount() {
		return outRmbAmount;
	}
	public void setOutRmbAmount(String outRmbAmount) {
		this.outRmbAmount = outRmbAmount;
	}
	public String getPayEvent() {
		return payEvent;
	}
	public void setPayEvent(String payEvent) {
		this.payEvent = payEvent;
	}
	public String getPayLagQty() {
		return payLagQty;
	}
	public void setPayLagQty(String payLagQty) {
		this.payLagQty = payLagQty;
	}
	public String getPayLagUnit() {
		return payLagUnit;
	}
	public void setPayLagUnit(String payLagUnit) {
		this.payLagUnit = payLagUnit;
	}
	public String getDsAmount() {
		return dsAmount;
	}
	public void setDsAmount(String dsAmount) {
		this.dsAmount = dsAmount;
	}
	public String getRmbDsAmount() {
		return rmbDsAmount;
	}
	public void setRmbDsAmount(String rmbDsAmount) {
		this.rmbDsAmount = rmbDsAmount;
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
	public String getScrmTimestampData() {
		return scrmTimestampData;
	}
	public void setScrmTimestampData(String scrmTimestampData) {
		this.scrmTimestampData = scrmTimestampData;
	}
    
}
