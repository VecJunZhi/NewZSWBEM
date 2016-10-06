package com.zs.crm.service;

import java.util.List;

import com.zs.crm.entity.XsCustAnalysisShowEntity;
import com.zs.crm.entity.XsSaleManagerEntity;

public interface XsCustAnalysisService {
	/**
	 * 根据跟进方式查询客户数量
	 * @return
	 */
	public XsCustAnalysisShowEntity getCstCountByFollowWay(XsSaleManagerEntity bean);
	/**
	 * 跟进客户意向查询客户数量
	 * @return
	 */
	public XsCustAnalysisShowEntity getCstCountByIntention(XsSaleManagerEntity bean);
	/**
	 * 根据客户状态查询客户数量
	 * @return
	 */
	public XsCustAnalysisShowEntity getCstCountByStatus(XsSaleManagerEntity bean);
	/**
	 * 根据时间和类型查询客户的认知途径、年龄段、工作行业的分类信息
	 * @param bean
	 * @return
	 */
	public List<XsSaleManagerEntity> getCstClassInfoByTypeAndDate(XsSaleManagerEntity bean);
	/**
	 * 根据时间段获取时间区间的来电、来访统计信息
	 * @param bean
	 * @return
	 */
	public List<XsSaleManagerEntity> getCallAndVisitInfoByCycle(XsSaleManagerEntity bean);
}
