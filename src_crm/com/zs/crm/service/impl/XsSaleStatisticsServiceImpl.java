package com.zs.crm.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.busi.utils.LogUtil;
import com.zs.crm.dao.XsSaleStatisticsDao;
import com.zs.crm.entity.XsBusinessUrgedInfoVo;
import com.zs.crm.entity.XsSaleStatisticsResultVo;
import com.zs.crm.service.XsSaleStatisticsService;

@Service
public class XsSaleStatisticsServiceImpl implements XsSaleStatisticsService{
	private Log log=LogUtil.getLog();
	@Autowired
	XsSaleStatisticsDao xsSaleStatisticsDao;
	
	public XsSaleStatisticsResultVo getRoomStatisticsInfo(String projGuid,String roomType) {
		XsSaleStatisticsResultVo roomStatistics = new XsSaleStatisticsResultVo();
		Map<String,String> searchInfo = new HashMap<String,String>();
		searchInfo.put("projGuid", projGuid);
		searchInfo.put("roomType", roomType);
		roomStatistics = xsSaleStatisticsDao.getRoomStatisticsInfoDao(searchInfo);
		return roomStatistics;
	}

	public XsSaleStatisticsResultVo getUnPaymentInfo(String projGuid) {
		XsBusinessUrgedInfoVo searchInfo = new XsBusinessUrgedInfoVo();
		XsSaleStatisticsResultVo unPaymentInfo = new XsSaleStatisticsResultVo();
		searchInfo.getXsOpp().setProjGuid(projGuid);
		unPaymentInfo = xsSaleStatisticsDao.getUnPaymentInfoDao(searchInfo);
		return unPaymentInfo;
	}
	
	public XsSaleStatisticsResultVo getUnSignUpInfo(String projGuid) {
		XsBusinessUrgedInfoVo searchInfo = new XsBusinessUrgedInfoVo();
		XsSaleStatisticsResultVo unSignUpInfo = new XsSaleStatisticsResultVo();
		searchInfo.getXsOpp().setProjGuid(projGuid);
		unSignUpInfo = xsSaleStatisticsDao.getUnSignUpInfoDao(searchInfo);
		return unSignUpInfo;
	}
	
	public XsSaleStatisticsResultVo getUnLendingInfo(String projGuid,String feeType) {
		XsBusinessUrgedInfoVo searchInfo = new XsBusinessUrgedInfoVo();
		XsSaleStatisticsResultVo unLendingInfo = new XsSaleStatisticsResultVo();
		searchInfo.getXsOpp().setProjGuid(projGuid);
		searchInfo.getXsFee().setRmbYe(feeType);
		unLendingInfo = xsSaleStatisticsDao.getUnLendingInfoDao(searchInfo);
		return unLendingInfo;
	}
}
