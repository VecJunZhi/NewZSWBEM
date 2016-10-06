package com.zs.crm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.crm.dao.XsFinancialStatisticsDao;
import com.zs.crm.entity.XsFinancialStatisticsInfoVo;
import com.zs.crm.entity.XsFinancialStatisticsSearchInfoVo;
import com.zs.crm.service.XsFinancialStatisticsService;

@Service
public class XsFinancialStatisticsServiceImpl implements XsFinancialStatisticsService{
	@Autowired
	XsFinancialStatisticsDao xsFinancialStatisticsDao;
	
	public List<XsFinancialStatisticsInfoVo> getFinancialListInfo(XsFinancialStatisticsSearchInfoVo bean){
		List<XsFinancialStatisticsInfoVo> financialList = new ArrayList<XsFinancialStatisticsInfoVo>();
		financialList = xsFinancialStatisticsDao.getFinancialListInfoDao(bean);
		return financialList;
	}
}
