package com.zs.crm.service;

import java.util.List;

import com.zs.crm.entity.CstServiceContractSignVo;

public interface CusServiceContractSignService {
	
	public List<CstServiceContractSignVo>getCusServiceContractSignList(String project,String zygw,String cstName,String cstTel,String payWay,String startIndex,String length);

}
