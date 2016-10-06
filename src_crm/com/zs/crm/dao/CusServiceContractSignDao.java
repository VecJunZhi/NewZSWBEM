package com.zs.crm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.crm.entity.CstServiceContractSignVo;
@Repository
public interface CusServiceContractSignDao {
	
	public List<CstServiceContractSignVo>getCusServiceContractSignList(CstServiceContractSignVo vo);

}
