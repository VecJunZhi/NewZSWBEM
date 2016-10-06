package com.zs.oa.entity;

public class AssetInStorageDto {
	
	public String cptl_id;
	public String cptl_name;
	public String type_id;
	public String dept_id;
	public String money;
	public String create_date;
	public String from_yymm;
	public String keeper;
	public String remark;
	public String getCptl_id() {
		return cptl_id;
	}
	public void setCptl_id(String cptl_id) {
		this.cptl_id = cptl_id;
	}
	public String getCptl_name() {
		return cptl_name;
	}
	public void setCptl_name(String cptl_name) {
		this.cptl_name = cptl_name;
	}
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public String getDept_id() {
		return dept_id;
	}
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getFrom_yymm() {
		return from_yymm;
	}
	public void setFrom_yymm(String from_yymm) {
		this.from_yymm = from_yymm;
	}
	public String getKeeper() {
		return keeper;
	}
	public void setKeeper(String keeper) {
		this.keeper = keeper;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public AssetInStorageDto() {
		super();
	}
	public AssetInStorageDto(String cptl_id, String cptl_name, String type_id,
			String dept_id, String money, String create_date, String from_yymm,
			String keeper, String remark) {
		super();
		this.cptl_id = cptl_id;
		this.cptl_name = cptl_name;
		this.type_id = type_id;
		this.dept_id = dept_id;
		this.money = money;
		this.create_date = create_date;
		this.from_yymm = from_yymm;
		this.keeper = keeper;
		this.remark = remark;
	}
	
	

}
