package com.zs.crm.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.busi.entity.CountRoomVo;
import com.zs.busi.entity.CountTelAccessVo;
import com.zs.busi.entity.XsMonthCountInfoVo;
import com.zs.crm.dao.XsInfoDao;
import com.zs.crm.service.XsInfoService;
@Service
public class XsInfoServiceImpl implements XsInfoService {
	
	Log log=LogFactory.getLog(XsInfoServiceImpl.class);
	@Autowired
	XsInfoDao dao;

	@Override
	public CountTelAccessVo getCountTelAccess(String startDate,
			String endDate) throws Exception {
		
		List<CountTelAccessVo> list= dao.getCountTelAccess(startDate, endDate);
		CountTelAccessVo dto = null;
		if(list.size()>0){
			 dto=list.get(0);
		}
		return dto;
	}

	@Override
	public CountRoomVo getCountRoom(String startDate, String endDate,
			String proJid) throws Exception {
		// TODO Auto-generated method stub
		List<CountRoomVo> list=dao.getCountRoom(startDate, endDate, proJid);
		CountRoomVo dto=null;
		if(list.size()>0){
			dto=list.get(0);
		}
		return dto;
	}

	@Override
	public List<XsMonthCountInfoVo> getMonthCountMoneyTnumber()
			throws Exception {
		List<XsMonthCountInfoVo> list=dao.getMonthCountMoneyTnumber();
		return list;
	}

	

}
