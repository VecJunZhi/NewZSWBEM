package com.zs.crm.service;

import java.util.List;

import com.zs.crm.entity.XsFinancialStatisticsInfoVo;
import com.zs.crm.entity.XsFinancialStatisticsSearchInfoVo;

public interface XsFinancialStatisticsService {
	/**
	 * ��ȡ������Ϣ�б�
	 * @param bean
	 * @return
	 */
	public List<XsFinancialStatisticsInfoVo> getFinancialListInfo(XsFinancialStatisticsSearchInfoVo bean);
}
