package com.zs.crm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.crm.entity.XsRoomCountInfoVo;
import com.zs.crm.entity.XsRoomInfoVo;
import com.zs.crm.entity.XsRoomResourceInfoVo;
import com.zs.crm.entity.XsRoomSearchVo;
import com.zs.crm.entity.XsUnitInfoVo;
@Repository
public interface XsRoomInfoDao {
	/**
	 * 获取某单元下所有房源信息
	 * @param bean
	 * @return
	 */
	public List<XsUnitInfoVo> getXsRoomInfoByUnitNoDao(XsRoomSearchVo bean);
	/**
	 * 根据楼栋获取该楼栋下的统计信息（已售、未售、销控等）
	 * @param bean
	 * @return
	 */
	public XsRoomCountInfoVo getXsCountInfoByBulidNoDao(XsRoomSearchVo bean);
	/**
	 * 根据楼栋获取单元信息（各单元名称）
	 * @param bean
	 * @return
	 */
	public List<String> getXsUnitInfoByBulidNoDao(XsRoomSearchVo bean);
	/**
	 * 根据房号查询房间的所有基本信息
	 * @param bean
	 * @return
	 */
	public List<XsRoomResourceInfoVo> getXsRoomInfoByRoomNoDao(XsRoomSearchVo bean);
	/**
	 * 当前项目搜索楼栋及剩余套数
	 * @param bean
	 * @return
	 */
	public List<XsRoomInfoVo> getXsBldNameAndCountInfoDao(XsRoomSearchVo bean);
	/**
	 * 查询该项目下的楼栋统计信息
	 * @param bean
	 * @return
	 */
	public List<XsRoomCountInfoVo> getXsBldInfoByProNoDao(XsRoomSearchVo bean);
}
