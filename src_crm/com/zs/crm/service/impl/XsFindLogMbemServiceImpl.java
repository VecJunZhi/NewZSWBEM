package com.zs.crm.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.common.entity.ZsUpdateLogEntity;
import com.zs.crm.dao.XsFindLogMbemDao;
import com.zs.crm.entity.XsFindLogMbemEntity;
import com.zs.crm.service.XsFindLogMbemService;
@Service
public class XsFindLogMbemServiceImpl implements XsFindLogMbemService{
	@Autowired
	XsFindLogMbemDao xsFindLogMbemDao;
	@Override
	public List<XsFindLogMbemEntity> getXsLogDao(){
		List<XsFindLogMbemEntity> zsUpdateLogList = new ArrayList<XsFindLogMbemEntity>();
		zsUpdateLogList=xsFindLogMbemDao.getXsLog();
		return zsUpdateLogList;
	}
	@Override
	public int insertUpdateLogMbemDao(XsFindLogMbemEntity xsFindLogMbem){
		return xsFindLogMbemDao.insertUpdateLogMbem(xsFindLogMbem);
	}
}
