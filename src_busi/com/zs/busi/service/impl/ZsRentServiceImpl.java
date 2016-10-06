package com.zs.busi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.busi.dao.ZsRentDao;
import com.zs.busi.entity.ZsRentSearchInfoVo;
import com.zs.busi.entity.ZsRentTableEntity;
import com.zs.busi.service.ZsRentService;

@Service
public class ZsRentServiceImpl implements ZsRentService{

	@Autowired
	ZsRentDao zsRentDao;
	
	@Override
	public List<ZsRentTableEntity> getZsRentInfoList(ZsRentSearchInfoVo searchInfo) {
		
		List<ZsRentTableEntity> rentInfoList = new ArrayList<ZsRentTableEntity>();
		rentInfoList = zsRentDao.getZsRentInfoListDao(searchInfo);
		return rentInfoList;
	}

	@Override
	public int updateZsRentInfo(ZsRentTableEntity bean) {
		// TODO Auto-generated method stub
		int flag;
		flag = zsRentDao.updateZsRentInfoDao(bean);
		return flag;
	}

}
