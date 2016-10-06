package com.zs.busi.service;

import java.util.List;

import com.zs.busi.entity.ZsCustomReportEntity;
import com.zs.busi.entity.ZsCustomReportSearchVo;

public interface ZsCustomReportService {
	/**
	 * 
	 * @param searchOption
	 * @return
	 */
	public int getCustomCountByDateAndWay(ZsCustomReportSearchVo searchOption);
	/**
	 * 
	 * @param searchOption
	 * @return
	 */
	public List<ZsCustomReportEntity> getCustomInfoByDateAndWay(ZsCustomReportSearchVo searchOption);
}
