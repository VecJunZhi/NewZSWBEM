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
     * 根据id删除问题类型
     * @param id
     * @return
     */
    int deleteIssuesTypeByIdDao(int id);
    /**
     * 插入问题类型
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
     * 查询问题明细列表
     * @param bean
     * @return
     */
    List<InspectRoomInfoVo> getIssuesInfoListDao(InspectRoomSearchInfoVo bean);
    /**
     * 根据父问题类型查询其下一级问题列表
     * @param bean
     * @return
     */
    List<IssuesTypeTableEntity> getIssuesTypeListDao(String type);
    /**
     * 查询可验楼栋信息
     * @param bean
     * @return
     */
    List<InspectRoomInfoVo> getBldInfoListDao(InspectRoomSearchInfoVo bean);
    /**
     * 查询楼栋下的单元及单元中房间信息
     * @param bean
     * @return
     */
    List<BldUnitInfoVo> getBldUnitInfoListDao(InspectRoomSearchInfoVo bean);
    /**
     * 查询问题反馈信息
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
     * 根据id查询问题详情
     * @param id
     * @return
     */
    List<IssuesDetailTableEntity> getIssuesDetailByIdDao(IssuesDetailTableEntity bean);
    /**
     * 根据楼栋id查询该楼栋下的单元信息
     * @param bldGuid
     * @return
     */
    List<BuildUnitTableEntity> getUnitInfoByBldDao(String bldGuid);
    /**
     * 根据id更新问题状态
     * @param bean
     * @return
     */
    int updateIssuesStatusByIdDao(IssuesDetailTableEntity bean);
    /**
     * 根据id删除问题详情
     * @param id
     * @return
     */
    int deleteIssuesDetailByIdDao(int id);
    /**
     * 根据id删除问题房间信息
     * @param id
     * @return
     */
    int deleteIssuesRoomByIdDao(int id);
    /**
     * 删除验房信息
     * @param id
     * @return
     */
    int deleteInspectRoomByIdDao(String roomGuid);
    /**
     * 根据id获取问题房间信息
     * @param id
     * @return
     */
    IssuesRoomTableEntity getIssuesRoomByIdDao(int id);
    /**
     * 获取物业专员列表
     * @return
     */
    List<String> getEmployeeListDao();
    /**
     * 查询当前项目下所有楼栋及楼栋状态等信息
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
