package com.zs.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.common.dao.InspectRoomStatisticsDao;
import com.zs.common.entity.BuildingTableEntity;
import com.zs.common.entity.InspectRoomSearchInfoVo;
import com.zs.common.entity.InspectRoomStatisticsInfoVo;
import com.zs.common.service.InspectRoomStatisticsService;

@Service
public class InspectRoomStatisticsServiceImpl implements InspectRoomStatisticsService{
	@Autowired
	InspectRoomStatisticsDao statisticsDao;
	
	public List<BuildingTableEntity> getBldInfoListByProj(String projGuid) {
		List<BuildingTableEntity> bldList = new ArrayList<BuildingTableEntity>();
		InspectRoomSearchInfoVo searchInfo = new InspectRoomSearchInfoVo();
		searchInfo.setProjGuid(projGuid);
		bldList = statisticsDao.getBldInfoListDao(searchInfo);
		return bldList;
	}
	
	public List<InspectRoomStatisticsInfoVo> getIssuesTypeStatisticsByBld(String projGuid) {
		List<InspectRoomStatisticsInfoVo> statisticsListInfo = new ArrayList<InspectRoomStatisticsInfoVo>();
		InspectRoomSearchInfoVo searchInfo = new InspectRoomSearchInfoVo();
		searchInfo.setProjGuid(projGuid);
		statisticsListInfo = statisticsDao.getIssuesTypeStatisticsInfoDao(searchInfo);
		return statisticsListInfo;
	}
	
	public List<InspectRoomStatisticsInfoVo> getIssuesTypeStatisticsByBld(String projGuid,String bldGuid) {
		List<InspectRoomStatisticsInfoVo> statisticsListInfo = new ArrayList<InspectRoomStatisticsInfoVo>();
		InspectRoomSearchInfoVo searchInfo = new InspectRoomSearchInfoVo();
		searchInfo.setProjGuid(projGuid);
		searchInfo.setBldGuid(bldGuid);
		statisticsListInfo = statisticsDao.getIssuesTypeStatisticsInfoDao(searchInfo);
		return statisticsListInfo;
	}
	
	public List<InspectRoomStatisticsInfoVo> getIssuesStatusStatisticsByBld(String projGuid) {
		List<InspectRoomStatisticsInfoVo> statisticsListInfo = new ArrayList<InspectRoomStatisticsInfoVo>();
		InspectRoomSearchInfoVo searchInfo = new InspectRoomSearchInfoVo();
		searchInfo.setProjGuid(projGuid);
		statisticsListInfo = statisticsDao.getIssuesStatusStatisticsInfoDao(searchInfo);
		return statisticsListInfo;
	}
	
	public List<InspectRoomStatisticsInfoVo> getIssuesStatusStatisticsByBld(String projGuid,String bldGuid) {
		List<InspectRoomStatisticsInfoVo> statisticsListInfo = new ArrayList<InspectRoomStatisticsInfoVo>();
		InspectRoomSearchInfoVo searchInfo = new InspectRoomSearchInfoVo();
		searchInfo.setProjGuid(projGuid);
		searchInfo.setBldGuid(bldGuid);
		statisticsListInfo = statisticsDao.getIssuesStatusStatisticsInfoDao(searchInfo);
		return statisticsListInfo;
	}
	
	public List<InspectRoomStatisticsInfoVo> getIssuesPositionStatisticsByBld(String projGuid) {
		List<InspectRoomStatisticsInfoVo> statisticsListInfo = new ArrayList<InspectRoomStatisticsInfoVo>();
		InspectRoomSearchInfoVo searchInfo = new InspectRoomSearchInfoVo();
		searchInfo.setProjGuid(projGuid);
		statisticsListInfo = statisticsDao.getIssuesPositionStatisticsInfoDao(searchInfo);
		return statisticsListInfo;
	}
	
	public List<InspectRoomStatisticsInfoVo> getIssuesPositionStatisticsByBld(String projGuid,String bldGuid) {
		List<InspectRoomStatisticsInfoVo> statisticsListInfo = new ArrayList<InspectRoomStatisticsInfoVo>();
		InspectRoomSearchInfoVo searchInfo = new InspectRoomSearchInfoVo();
		searchInfo.setProjGuid(projGuid);
		searchInfo.setBldGuid(bldGuid);
		statisticsListInfo = statisticsDao.getIssuesPositionStatisticsInfoDao(searchInfo);
		return statisticsListInfo;
	}
}
