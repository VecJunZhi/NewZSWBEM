package com.zs.common.dao;

import java.util.List;

import com.zs.common.entity.BuildingTableEntity;
import com.zs.common.entity.InspectRoomSearchInfoVo;
import com.zs.common.entity.InspectRoomStatisticsInfoVo;

public interface InspectRoomStatisticsDao {
	/**
	 * ��ѯ��Ŀ�µ�¥����Ϣ
	 * @param bean
	 * @return
	 */
	public List<BuildingTableEntity> getBldInfoListDao(InspectRoomSearchInfoVo bean);
	/**
	 * ��ѯ�������ͳ����Ϣ
	 * @param bean
	 * @return
	 */
	public List<InspectRoomStatisticsInfoVo> getIssuesTypeStatisticsInfoDao(InspectRoomSearchInfoVo bean);
	/**
	 * ��ѯ����״̬ͳ����Ϣ
	 * @param bean
	 * @return
	 */
	public List<InspectRoomStatisticsInfoVo> getIssuesStatusStatisticsInfoDao(InspectRoomSearchInfoVo bean);
	/**
	 * ��ѯ���ⲿλͳ����Ϣ
	 * @param bean
	 * @return
	 */
	public List<InspectRoomStatisticsInfoVo> getIssuesPositionStatisticsInfoDao(InspectRoomSearchInfoVo bean);
}
