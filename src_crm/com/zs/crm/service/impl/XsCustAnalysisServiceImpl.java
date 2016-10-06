package com.zs.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.crm.dao.XsCustAnalysisDao;
import com.zs.crm.entity.XsCustAnalysisShowEntity;
import com.zs.crm.entity.XsSaleManagerEntity;
import com.zs.crm.service.XsCustAnalysisService;
@Service
public class XsCustAnalysisServiceImpl implements XsCustAnalysisService{
	@Autowired
	XsCustAnalysisDao xsCustAnalysisDao;
	/**
	 * 根据跟进方式查询客户数量
	 * @return
	 */
	public XsCustAnalysisShowEntity getCstCountByFollowWay(XsSaleManagerEntity bean) {
		XsCustAnalysisShowEntity resultInfo = xsCustAnalysisDao.getCstCountByFollowWayDao(bean);
		return resultInfo;
	}
	/**
	 * 跟进客户意向查询客户数量
	 * @return
	 */
	public XsCustAnalysisShowEntity getCstCountByIntention(XsSaleManagerEntity bean) {
		XsCustAnalysisShowEntity resultInfo = xsCustAnalysisDao.getCstCountByIntentionDao(bean);
		return resultInfo;
	}
	/**
	 * 根据客户状态查询客户数量
	 * @return
	 */
	public XsCustAnalysisShowEntity getCstCountByStatus(XsSaleManagerEntity bean) {
		XsCustAnalysisShowEntity resultInfo = xsCustAnalysisDao.getCstCountByStatusDao(bean);
		return resultInfo;
	}
	/**
	 * 
	 */
	public List<XsSaleManagerEntity> getCstClassInfoByTypeAndDate(XsSaleManagerEntity bean) {
		List<XsSaleManagerEntity> resultInfo = xsCustAnalysisDao.getCstClassInfoByTypeAndDateDao(bean);
		return resultInfo;
	}
	public List<XsSaleManagerEntity> getCallAndVisitInfoByCycle(XsSaleManagerEntity bean) {
		List<XsSaleManagerEntity> resultInfo = xsCustAnalysisDao.getCallAndVisitInfoByCycleDao(bean);
		return resultInfo;
	}
}
