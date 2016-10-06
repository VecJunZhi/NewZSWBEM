package com.zs.common.service;

import java.util.List;

import com.zs.common.entity.BuildingTableEntity;
import com.zs.common.entity.InspectRoomStatisticsInfoVo;

public interface InspectRoomStatisticsService {
	/**
	 * 
	 * @param projGuid
	 * @return
	 */
	public List<BuildingTableEntity> getBldInfoListByProj(String projGuid);
	/**
	 * 
	 * @param projGuid
	 * @return
	 */
	public List<InspectRoomStatisticsInfoVo> getIssuesTypeStatisticsByBld(String projGuid);
	/**
	 * 
	 * @param projGuid
	 * @param bldGuid
	 * @return
	 */
	public List<InspectRoomStatisticsInfoVo> getIssuesTypeStatisticsByBld(String projGuid,String bldGuid);
	/**
	 * 
	 * @param projGuid
	 * @return
	 */
	public List<InspectRoomStatisticsInfoVo> getIssuesStatusStatisticsByBld(String projGuid);
	/**
	 * 
	 * @param projGuid
	 * @param bldGuid
	 * @return
	 */
	public List<InspectRoomStatisticsInfoVo> getIssuesStatusStatisticsByBld(String projGuid,String bldGuid);
	/**
	 * 
	 * @param projGuid
	 * @return
	 */
	public List<InspectRoomStatisticsInfoVo> getIssuesPositionStatisticsByBld(String projGuid);
	/**
	 * 
	 * @param projGuid
	 * @param bldGuid
	 * @return
	 */
	public List<InspectRoomStatisticsInfoVo> getIssuesPositionStatisticsByBld(String projGuid,String bldGuid);
}
