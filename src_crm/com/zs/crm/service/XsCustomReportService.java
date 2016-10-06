package com.zs.crm.service;

import java.util.List;

import com.zs.crm.entity.XsCustomReportEntity;
import com.zs.crm.entity.XsCustomReportSearchVo;


public interface XsCustomReportService {
	/**
	 * 
	 * @param searchOption
	 * @return
	 */
	public int getCustomCountByDateAndWay(XsCustomReportSearchVo searchOption);
	/**
	 * 
	 * @param searchOption
	 * @return
	 */
	public List<XsCustomReportEntity> getCustomInfoByDateAndWay(XsCustomReportSearchVo searchOption);
}
