package com.zs.crm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.crm.dao.XsSaleManagerDao;
import com.zs.crm.entity.XsSaleDetailVo;
import com.zs.crm.entity.XsSaleManagerEntity;
import com.zs.crm.entity.XsSaleManagerShowEntity;
import com.zs.crm.entity.XsSignUpDetailVo;
import com.zs.crm.service.XsSaleManagerService;

@Service
public class XsSaleManagerServiceImpl implements XsSaleManagerService{
	@Autowired
	XsSaleManagerDao xsSaleManagerDao;
	
	public XsSaleManagerShowEntity getDayReportInfoDao() {
		XsSaleManagerShowEntity showInfo = xsSaleManagerDao.getDayReportInfoDao();
		return showInfo;
	}
	public List<XsSaleManagerEntity> getCstByCycleAndFollowWay(XsSaleManagerEntity bean) {
		List<XsSaleManagerEntity> cstList = new ArrayList<XsSaleManagerEntity>();
		cstList = xsSaleManagerDao.getCstByCycleAndFollowWayDao(bean);
		return cstList;
	}
	public List<XsSaleManagerEntity> getCstSubscribeInfoByCycle(XsSaleManagerEntity bean) {
		List<XsSaleManagerEntity> subscribeList = new ArrayList<XsSaleManagerEntity>();
		subscribeList = xsSaleManagerDao.getCstSubscribeInfoByCycleDao(bean);
		return subscribeList;
	}
	public List<XsSaleManagerEntity> getCstSignUpInfoByCycle(XsSaleManagerEntity bean) {
		List<XsSaleManagerEntity> signUpList = new ArrayList<XsSaleManagerEntity>();
		signUpList = xsSaleManagerDao.getCstSignUpInfoByCycleDao(bean);
		return signUpList;
	}
	public String getFirstVistCountByCycle(XsSaleManagerEntity bean) {
		String count = xsSaleManagerDao.getFirstVisitCountByCycleDao(bean);
		return count;
	}
	public XsSaleDetailVo getSaleDetailByCycle(XsSaleManagerEntity bean) {
		XsSaleDetailVo saleInfo = xsSaleManagerDao.getSaleDetailByCycleDao(bean);
		return saleInfo;
	}

	public XsSignUpDetailVo getSignUpDetailByCycle(XsSaleManagerEntity bean) {
		XsSignUpDetailVo signUpInfo = xsSaleManagerDao.getSignUpDetailByCycleDao(bean);
		return signUpInfo;
	}
}
