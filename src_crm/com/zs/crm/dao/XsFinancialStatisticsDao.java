package com.zs.crm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.crm.entity.XsFinancialStatisticsInfoVo;
import com.zs.crm.entity.XsFinancialStatisticsSearchInfoVo;
@Repository
public interface XsFinancialStatisticsDao {
	/**
	 * 获取财务信息列表
	 * @param bean
	 * @return
	 */
	public List<XsFinancialStatisticsInfoVo> getFinancialListInfoDao(XsFinancialStatisticsSearchInfoVo bean);
}
