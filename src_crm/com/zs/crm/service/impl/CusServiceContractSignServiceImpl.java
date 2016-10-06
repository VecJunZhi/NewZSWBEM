package com.zs.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.crm.dao.CusServiceContractSignDao;
import com.zs.crm.entity.CstServiceContractSignVo;
import com.zs.crm.service.CusServiceContractSignService;
@Service
public class CusServiceContractSignServiceImpl implements CusServiceContractSignService{

	@Autowired
	CusServiceContractSignDao contractSignDao;
	@Override
	public List<CstServiceContractSignVo> getCusServiceContractSignList(
			String project, String zygw, String cstName, String cstTel,
			String payWay,String startIndex,String length) {
		CstServiceContractSignVo vo = new CstServiceContractSignVo();
		vo.setProjGuid(project);
		vo.setZygw(zygw);
		vo.setCstName(cstName);
		vo.setMobileTel(cstTel);
		vo.setPaymentWay(payWay);
		vo.setStartIndex(startIndex);
		vo.setLength(length);
		System.out.println("nimamidnid "+length);
		List<CstServiceContractSignVo>  list=contractSignDao.getCusServiceContractSignList(vo);
		return list;
	}

}
