package com.zs.crm.service;

import java.util.List;

import com.zs.crm.entity.XsFinancialStatisticsInfoVo;
import com.zs.crm.entity.XsFinancialStatisticsSearchInfoVo;

public interface XsFinancialStatisticsService {
	/**
	 * 获取财务信息列表
	 * @param bean
	 * @return
	 */
	public List<XsFinancialStatisticsInfoVo> getFinancialListInfo(XsFinancialStatisticsSearchInfoVo bean);
}
