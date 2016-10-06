package com.zs.busi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.busi.dao.ZsfollowInfoDao;
import com.zs.busi.entity.ZsCustomTableVo;
import com.zs.busi.entity.ZsFollowInfoVo;
import com.zs.busi.entity.ZsInfoVo;
import com.zs.busi.service.ZsFollowInfoService;
import com.zs.busi.utils.LogUtil;

@Service
public class ZsFollowInfoServiceImpl implements ZsFollowInfoService{
	@Autowired
	ZsfollowInfoDao zsInfoDao;
	
	Log log=LogUtil.getLog();
	
	@Override
	public List<ZsInfoVo> getZsCustInfo(ZsFollowInfoVo zsFollowInfo) {
		List<ZsInfoVo> custInfo = new ArrayList<ZsInfoVo>();
		custInfo = zsInfoDao.getZsCustInfoDao(zsFollowInfo);
		return custInfo;
	}
	
	public int getZsCustCount(ZsFollowInfoVo zsFollowInfo) {
		int custNum;
		custNum = zsInfoDao.getZsCustCountDao(zsFollowInfo);
		return custNum;
	}
	
	public List<ZsInfoVo> getZsFollowInfoByCusid(ZsFollowInfoVo zsFollowInfo) {
		List<ZsInfoVo> followList = new ArrayList<ZsInfoVo>();
		followList = zsInfoDao.getZsFollowInfoDaoByCusid(zsFollowInfo);
		return followList;
	}
	
	public int getZsFollowInfoCountByCusid(ZsFollowInfoVo zsFollowInfo) {
		int followRecordNum;
		followRecordNum = zsInfoDao.getZsFollowInfoCountDaoByCusid(zsFollowInfo);
		return followRecordNum;
	}
	
	public ZsCustomTableVo getcusInfo(String name) {
		ZsCustomTableVo cusInfo = new ZsCustomTableVo();
		cusInfo = zsInfoDao.getCusInfo(name);
		return cusInfo;
	}
}
