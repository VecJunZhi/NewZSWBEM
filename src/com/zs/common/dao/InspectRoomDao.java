package com.zs.common.dao;

import java.util.List;

import com.zs.common.entity.BldUnitInfoVo;
import com.zs.common.entity.BuildUnitTableEntity;
import com.zs.common.entity.BuildingExtTableEntity;
import com.zs.common.entity.InspectRoomInfoVo;
import com.zs.common.entity.InspectRoomSearchInfoVo;
import com.zs.common.entity.InspectRoomTableEntity;
import com.zs.common.entity.IssueFeedbackTableEntity;
import com.zs.common.entity.IssuesDetailTableEntity;
import com.zs.common.entity.IssuesRoomTableEntity;
import com.zs.common.entity.IssuesTypeTableEntity;

public interface InspectRoomDao {
	/**
	 * 
	 * @param id
	 * @return
	 */
	int deleteBuildingExtById(String id);
	/**
	 * 
	 * @param bean
	 * @return
	 */
    int insertBuildingExtDao(BuildingExtTableEntity bean);
    /**
     * 
     * @param bean
     * @return
     */
    int updateBuildingExtById(BuildingExtTableEntity bean);
    /**
     * 
     * @param id
     * @return
     */
    int deleteInspectRoomById(int id);
    /**
     * 
     * @param bean
     * @return
     */
    int updateInspectRoomById(InspectRoomTableEntity bean);
    /**
     * 
     * @param id
     * @return
     */
    int deleteIssueFeedbackById(int id);
    /**
     * 
     * @param bean
     * @return
     */
    int insertIssueFeedbackDao(IssueFeedbackTableEntity bean);
    /**
     * 
     * @param bean
     * @return
     */
    int updateIssueFeedbackById(IssueFeedbackTableEntity bean);
    /**
     * 
     * @param id
     * @return
     */
    int deleteIssuesDetailById(int id);
    /**
     * 
     * @param bean
     * @return
     */
    int insertIssuesDetailDao(IssuesDetailTableEntity bean);
    /**
     * 
     * @param bean
     * @return
     */
    int updateIssuesDetailByIdDao(IssuesDetailTableEntity bean);
    /**
     * 
     * @param id
     * @return
     */
    int deleteIssuesRoomById(int id);
    /**
     * 
     * @param bean
     * @return
     */
    int getIssuesRoomIdDao(IssuesRoomTableEntity bean);
    /**
     * 
     * @param bean
     * @return
     */
    int updateIssuesRoomByIdDao(IssuesRoomTableEntity bean);
    /**
     * ����idɾ����������
     * @param id
     * @return
     */
    int deleteIssuesTypeByIdDao(int id);
    /**
     * ������������
     * @param bean
     * @return
     */
    int insertIssuesTypeDao(IssuesTypeTableEntity bean);
    /**
     * 
     * @param bean
     * @return
     */
    int updateIssuesTypeByIdDao(IssuesTypeTableEntity bean);
    /**
     * ��ѯ������ϸ�б�
     * @param bean
     * @return
     */
    List<InspectRoomInfoVo> getIssuesInfoListDao(InspectRoomSearchInfoVo bean);
    /**
     * ���ݸ��������Ͳ�ѯ����һ�������б�
     * @param bean
     * @return
     */
    List<IssuesTypeTableEntity> getIssuesTypeListDao(String type);
    /**
     * ��ѯ����¥����Ϣ
     * @param bean
     * @return
     */
    List<InspectRoomInfoVo> getBldInfoListDao(InspectRoomSearchInfoVo bean);
    /**
     * ��ѯ¥���µĵ�Ԫ����Ԫ�з�����Ϣ
     * @param bean
     * @return
     */
    List<BldUnitInfoVo> getBldUnitInfoListDao(InspectRoomSearchInfoVo bean);
    /**
     * ��ѯ���ⷴ����Ϣ
     * @param bean
     * @return
     */
    List<InspectRoomInfoVo> getIssueFeedbackListDao(InspectRoomSearchInfoVo bean);
    /**
     * 
     * @param bean
     * @return
     */
    int insertIssueDetailListDao(List<IssuesDetailTableEntity> bean);
    /**
     * 
     * @param bean
     * @return
     */
    int insertInspectRoomDao(InspectRoomTableEntity bean);
    /**
     * ����id��ѯ��������
     * @param id
     * @return
     */
    List<IssuesDetailTableEntity> getIssuesDetailByIdDao(IssuesDetailTableEntity bean);
    /**
     * ����¥��id��ѯ��¥���µĵ�Ԫ��Ϣ
     * @param bldGuid
     * @return
     */
    List<BuildUnitTableEntity> getUnitInfoByBldDao(String bldGuid);
    /**
     * ����id��������״̬
     * @param bean
     * @return
     */
    int updateIssuesStatusByIdDao(IssuesDetailTableEntity bean);
    /**
     * ����idɾ����������
     * @param id
     * @return
     */
    int deleteIssuesDetailByIdDao(int id);
    /**
     * ����idɾ�����ⷿ����Ϣ
     * @param id
     * @return
     */
    int deleteIssuesRoomByIdDao(int id);
    /**
     * ɾ���鷿��Ϣ
     * @param id
     * @return
     */
    int deleteInspectRoomByIdDao(String roomGuid);
    /**
     * ����id��ȡ���ⷿ����Ϣ
     * @param id
     * @return
     */
    IssuesRoomTableEntity getIssuesRoomByIdDao(int id);
    /**
     * ��ȡ��ҵרԱ�б�
     * @return
     */
    List<String> getEmployeeListDao();
    /**
     * ��ѯ��ǰ��Ŀ������¥����¥��״̬����Ϣ
     * @return
     */
    List<InspectRoomInfoVo> getAllBldInfoListByIdDao(String projGuid);
    /**
     * 
     * @param bean
     * @return
     */
    List<IssuesTypeTableEntity> getAllIssusTypeListDao(InspectRoomSearchInfoVo bean);
}
