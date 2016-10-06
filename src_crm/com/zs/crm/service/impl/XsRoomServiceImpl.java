package com.zs.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.crm.dao.XsRoomInfoDao;
import com.zs.crm.entity.XsRoomCountInfoVo;
import com.zs.crm.entity.XsRoomInfoVo;
import com.zs.crm.entity.XsRoomResourceInfoVo;
import com.zs.crm.entity.XsRoomSearchVo;
import com.zs.crm.entity.XsUnitInfoVo;
import com.zs.crm.service.XsRoomService;

@Service
public class XsRoomServiceImpl implements XsRoomService{
	@Autowired
	XsRoomInfoDao xsRoomInfoDao;

	public List<XsUnitInfoVo> getXsRoomInfoByUnitNo(XsRoomSearchVo bean) {
		List<XsUnitInfoVo> floorList = xsRoomInfoDao.getXsRoomInfoByUnitNoDao(bean);
		return floorList;
	}

	public XsRoomCountInfoVo getXsCountInfoByBulidNo(XsRoomSearchVo bean) {
		XsRoomCountInfoVo roomCountInfo = xsRoomInfoDao.getXsCountInfoByBulidNoDao(bean);
		return roomCountInfo;
	}

	public List<String> getXsUnitInfoByBulidNo(XsRoomSearchVo bean) {
		List<String> unitInfo = xsRoomInfoDao.getXsUnitInfoByBulidNoDao(bean);
		return unitInfo;
	}
	public List<XsRoomResourceInfoVo> getXsRoomInfoByRoomNo(XsRoomSearchVo bean) {
		List<XsRoomResourceInfoVo> roomInfo = xsRoomInfoDao.getXsRoomInfoByRoomNoDao(bean);
		return roomInfo;
	}
	public List<XsRoomInfoVo> getXsBldNameAndCountInfo(XsRoomSearchVo bean) {
		List<XsRoomInfoVo> bldInfoList = xsRoomInfoDao.getXsBldNameAndCountInfoDao(bean);
		return bldInfoList;
	}
	public List<XsRoomCountInfoVo> getXsBldInfoByProNo(XsRoomSearchVo bean) {
		List<XsRoomCountInfoVo> bldInfoList = xsRoomInfoDao.getXsBldInfoByProNoDao(bean);
		return bldInfoList;
	}
}
