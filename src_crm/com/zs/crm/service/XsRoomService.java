package com.zs.crm.service;

import java.util.List;

import com.zs.crm.entity.XsRoomCountInfoVo;
import com.zs.crm.entity.XsRoomInfoVo;
import com.zs.crm.entity.XsRoomResourceInfoVo;
import com.zs.crm.entity.XsRoomSearchVo;
import com.zs.crm.entity.XsUnitInfoVo;

public interface XsRoomService {
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<XsUnitInfoVo> getXsRoomInfoByUnitNo(XsRoomSearchVo bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public XsRoomCountInfoVo getXsCountInfoByBulidNo(XsRoomSearchVo bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<String> getXsUnitInfoByBulidNo(XsRoomSearchVo bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<XsRoomResourceInfoVo> getXsRoomInfoByRoomNo(XsRoomSearchVo bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<XsRoomInfoVo> getXsBldNameAndCountInfo(XsRoomSearchVo bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<XsRoomCountInfoVo> getXsBldInfoByProNo(XsRoomSearchVo bean);
}
