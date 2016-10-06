package com.zs.common.dao;

import java.util.List;

import com.zs.common.entity.BuildingTableEntity;
import com.zs.common.entity.InspectRoomSearchInfoVo;
import com.zs.common.entity.InspectRoomStatisticsInfoVo;

public interface InspectRoomStatisticsDao {
	/**
	 * 查询项目下的楼栋信息
	 * @param bean
	 * @return
	 */
	public List<BuildingTableEntity> getBldInfoListDao(InspectRoomSearchInfoVo bean);
	/**
	 * 查询问题分类统计信息
	 * @param bean
	 * @return
	 */
	public List<InspectRoomStatisticsInfoVo> getIssuesTypeStatisticsInfoDao(InspectRoomSearchInfoVo bean);
	/**
	 * 查询问题状态统计信息
	 * @param bean
	 * @return
	 */
	public List<InspectRoomStatisticsInfoVo> getIssuesStatusStatisticsInfoDao(InspectRoomSearchInfoVo bean);
	/**
	 * 查询问题部位统计信息
	 * @param bean
	 * @return
	 */
	public List<InspectRoomStatisticsInfoVo> getIssuesPositionStatisticsInfoDao(InspectRoomSearchInfoVo bean);
}
