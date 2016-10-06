package com.zs.crm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.crm.dao.XsCustomReportDao;
import com.zs.crm.entity.XsCustomReportEntity;
import com.zs.crm.entity.XsCustomReportSearchVo;
import com.zs.crm.service.XsCustomReportService;

@Service
public class XsCustomReportServiceImpl implements XsCustomReportService{
	@Autowired
	XsCustomReportDao xsCustomReportDao;
	public int getCustomCountByDateAndWay(XsCustomReportSearchVo searchOption) {
		int count = xsCustomReportDao.getCustomCountByDateAndWayDao(searchOption);
		return count;
	}
	
	public List<XsCustomReportEntity> getCustomInfoByDateAndWay(XsCustomReportSearchVo searchOption) {
		List<XsCustomReportEntity> customList = new ArrayList<XsCustomReportEntity>();
		customList = xsCustomReportDao.getCustomInfoByDateAndWayDao(searchOption);
		return customList;
	}
}
