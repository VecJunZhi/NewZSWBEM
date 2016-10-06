package com.zs.crm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.crm.dao.XsfollowInfoDao;
import com.zs.crm.entity.XsCustomTableVo;
import com.zs.crm.entity.XsCustomersManagerEntity;
import com.zs.crm.entity.XsFollowInfoVo;
import com.zs.crm.entity.XsInfoVo;
import com.zs.crm.service.XsFollowInfoService;
import com.zs.crm.web.vo.XsCustomersManagerVo;
import com.zs.busi.utils.LogUtil;

@Service
public class XsFollowInfoServiceImpl implements XsFollowInfoService{
	@Autowired
	XsfollowInfoDao xsInfoDao;
	
	Log log=LogUtil.getLog();
	
	@Override
	public List<XsCustomersManagerEntity> getXsCustInfo(XsCustomersManagerVo xsCustomersManagerVo) {
		List<XsCustomersManagerEntity> custInfo = new ArrayList<XsCustomersManagerEntity>();
		custInfo = xsInfoDao.getXsCustInfoDao(xsCustomersManagerVo);
		return custInfo;
	}
	@Override
	public int getXsCustCount(XsCustomersManagerVo xsCustomersManagerVo) {
		int custNum;
		custNum = xsInfoDao.getXsCustCountDao(xsCustomersManagerVo);
		return custNum;
	}
	@Override
	public List<XsCustomersManagerEntity> getXsFollowInfoByCusid(XsCustomersManagerVo xsCustomersManagerVo) {
		List<XsCustomersManagerEntity> followList = new ArrayList<XsCustomersManagerEntity>();
		followList = xsInfoDao.getXsFollowInfoDaoByCusid(xsCustomersManagerVo);
		return followList;
	}
	@Override
	public int getXsFollowInfoCountByCusid(XsCustomersManagerVo xsCustomersManagerVo) {
		int followRecordNum;
		followRecordNum = xsInfoDao.getXsFollowInfoCountDaoByCusid(xsCustomersManagerVo);
		return followRecordNum;
	}

}
