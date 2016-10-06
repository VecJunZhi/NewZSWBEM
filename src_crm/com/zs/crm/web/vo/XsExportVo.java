package com.zs.crm.web.vo;

import java.util.List;
import java.util.Map;

import com.zs.crm.entity.XsCustomersManagerEntity;

public class XsExportVo {
	
	public List<XsCustomersManagerEntity>  list;
	public Map<String, Object> model;
	public List<XsCustomerImportVo> dupCusList ;
	public List<XsCustomersManagerEntity> getList() {
		return list;
	}

	public void setList(List<XsCustomersManagerEntity> list) {
		this.list = list;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

	public List<XsCustomerImportVo> getDupCusList() {
		return dupCusList;
	}

	public void setDupCusList(List<XsCustomerImportVo> dupCusList) {
		this.dupCusList = dupCusList;
	}
	

}
