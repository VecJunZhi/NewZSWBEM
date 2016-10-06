package com.zs.crm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.crm.entity.XsCustAnalysisShowEntity;
import com.zs.crm.entity.XsSaleManagerEntity;
@Repository
public interface XsCustAnalysisDao {
	/**
	 * 根据跟进方式查询客户数量
	 * @return
	 */
	public XsCustAnalysisShowEntity getCstCountByFollowWayDao(XsSaleManagerEntity bean);
	/**
	 * 跟进客户意向查询客户数量
	 * @return
	 */
	public XsCustAnalysisShowEntity getCstCountByIntentionDao(XsSaleManagerEntity bean);
	/**
	 * 根据客户状态查询客户数量
	 * @return
	 */
	public XsCustAnalysisShowEntity getCstCountByStatusDao(XsSaleManagerEntity bean);
	/**
	 * 根据时间和类型查询客户的认知途径、年龄段、工作行业的分类信息
	 * @param bean
	 * @return
	 */
	public List<XsSaleManagerEntity> getCstClassInfoByTypeAndDateDao(XsSaleManagerEntity bean);
	/**
	 * 跟进时间获取来电、来访的时间区间统计信息
	 * @param bean
	 * @return
	 */
	public List<XsSaleManagerEntity> getCallAndVisitInfoByCycleDao(XsSaleManagerEntity bean);
}
