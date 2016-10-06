package com.zs.crm.dao;

import java.util.List;

import com.zs.crm.entity.XsCustomReportEntity;
import com.zs.crm.entity.XsCustomReportSearchVo;

public interface XsCustomReportDao {
	/**
	 * 
	 * @param searchOption
	 * @return
	 */
	public int getCustomCountByDateAndWayDao(XsCustomReportSearchVo searchOption);
	/**
	 * 
	 * @param searchOption
	 * @return
	 */
	public List<XsCustomReportEntity> getCustomInfoByDateAndWayDao(XsCustomReportSearchVo searchOption);
}
