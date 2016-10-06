package com.zs.busi.dao;

import java.util.List;

import com.zs.busi.entity.ZsCustomReportEntity;
import com.zs.busi.entity.ZsCustomReportSearchVo;

public interface ZsCustomReportDao {
	/**
	 * 
	 * @param searchOption
	 * @return
	 */
	public int getCustomCountByDateAndWayDao(ZsCustomReportSearchVo searchOption);
	/**
	 * 
	 * @param searchOption
	 * @return
	 */
	public List<ZsCustomReportEntity> getCustomInfoByDateAndWayDao(ZsCustomReportSearchVo searchOption);
}
