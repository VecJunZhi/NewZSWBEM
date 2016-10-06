package com.zs.busi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.busi.dao.ZsCustomReportDao;
import com.zs.busi.entity.ZsCustomReportEntity;
import com.zs.busi.entity.ZsCustomReportSearchVo;
import com.zs.busi.service.ZsCustomReportService;
@Service
public class ZsCustomReportServiceImpl implements ZsCustomReportService{
	@Autowired
	ZsCustomReportDao zsCustomReportDao;
	
	public int getCustomCountByDateAndWay(ZsCustomReportSearchVo searchOption) {
		int count = zsCustomReportDao.getCustomCountByDateAndWayDao(searchOption);
		return count;
	}
	
	public List<ZsCustomReportEntity> getCustomInfoByDateAndWay(ZsCustomReportSearchVo searchOption) {
		List<ZsCustomReportEntity> customList = new ArrayList<ZsCustomReportEntity>();
		customList = zsCustomReportDao.getCustomInfoByDateAndWayDao(searchOption);
		return customList;
	}
}
