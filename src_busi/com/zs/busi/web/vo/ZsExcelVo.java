package com.zs.busi.web.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zs.busi.entity.ZsBusiCustomAllocateEntity;

public class ZsExcelVo {
	List<ZsBusiCustomAllocateVo> public_DusLin_VoList;
	List<ZsBusiCustomAllocateEntity> follow_oveDue_invalid_VoList;
	public Map<String, Object> model;
	List<ZsCustomerImportVo> dupCusList;
	public List<ZsBusiCustomAllocateVo> getPublic_DusLin_VoList() {
		return public_DusLin_VoList;
	}
	public void setPublic_DusLin_VoList(
			List<ZsBusiCustomAllocateVo> public_DusLin_VoList) {
		this.public_DusLin_VoList = public_DusLin_VoList;
	}
	public List<ZsBusiCustomAllocateEntity> getFollow_oveDue_invalid_VoList() {
		return follow_oveDue_invalid_VoList;
	}
	public void setFollow_oveDue_invalid_VoList(
			List<ZsBusiCustomAllocateEntity> follow_oveDue_invalid_VoList) {
		this.follow_oveDue_invalid_VoList = follow_oveDue_invalid_VoList;
	}
	public Map<String, Object> getModel() {
		return model;
	}
	public void setModel(Map<String, Object> model) {
		this.model = model;
	}
	public List<ZsCustomerImportVo> getDupCusList() {
		return dupCusList;
	}
	public void setDupCusList(List<ZsCustomerImportVo> dupCusList) {
		this.dupCusList = dupCusList;
	}
	
	

}
