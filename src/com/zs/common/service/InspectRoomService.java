package com.zs.common.service;

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

public interface InspectRoomService {
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<InspectRoomInfoVo> getIssuesInfoList(InspectRoomSearchInfoVo bean);
	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<IssuesTypeTableEntity> getIssuesTypeList(String type);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<InspectRoomInfoVo> getBldInfoList(InspectRoomSearchInfoVo bean);
	/**
	 * 
	 * @param projGuid
	 * @param bldGuid
	 * @return
	 */
	public List<BldUnitInfoVo> getBldUnitInfoList(String bldGuid,int type);
	/**
	 * 
	 * @param issuesId
	 * @return
	 */
	public List<InspectRoomInfoVo> getIssueFeedbackList(int issuesId);
	/**
	 * 
	 * @param issuesId
	 * @param startIndex
	 * @param length
	 * @return
	 */
	public List<InspectRoomInfoVo> getIssueFeedbackList(int issuesId,int startIndex,int length);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public int insertIssueDetailList(IssuesRoomTableEntity issuesRoomInfo,InspectRoomTableEntity inspectRoomInfo,List<IssuesDetailTableEntity> bean);
	/**
	 * 
	 * @param bean
	 * @param status
	 * @return
	 */
	public int insertIssueFeedbackInfo(IssueFeedbackTableEntity bean,int status);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<IssuesDetailTableEntity> getIssuesDetailById(IssuesDetailTableEntity bean);
	/**
	 * 
	 * @param bldGuid
	 * @return
	 */
	public List<BuildUnitTableEntity> getUnitInfoByBld(String bldGuid);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public int updateIssuesDetailById(IssuesDetailTableEntity bean);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public int deleteIssuesDetailById(int id);
	 /**
     * 
     * @param id
     * @return
     */
    public int deleteIssuesRoomById(int id);
    /**
     * 
     * @param id
     * @return
     */
    public int deleteInspectRoomById(String roomGuid);
    /**
     * 
     * @param id
     * @return
     */
    public IssuesRoomTableEntity getIssuesRoomById(int id);
    /**
     * 
     * @param feedback
     * @param status
     * @return
     */
    public int insertIssueFeedback(IssueFeedbackTableEntity feedback,int status);
    /**
     * 
     * @param feedback
     * @param status
     * @param endDate
     * @return
     */
    public int insertIssueFeedback(IssueFeedbackTableEntity feedback,int status,String endDate);
    /**
     * 
     * @param feedback
     * @param issueRoomId
     * @param status
     * @param endDate
     * @return
     */
    public int insertIssueFeedback(IssueFeedbackTableEntity feedback,int issueRoomId,int status,String endDate);
    /**
     * 
     * @return
     */
    public List<String> getEmployeeList();
    /**
     * 
     * @param bean
     * @return
     */
    public List<IssuesTypeTableEntity> getAllIssusTypeList(InspectRoomSearchInfoVo bean);
    /**
     * 
     * @param issuesId
     * @return
     */
    public int deleteIssuesTypeById(int issuesId);
    /**
     * 
     * @param issueName
     * @param parentCodes
     * @return
     */
    public int insertIssuesType(String issueName,String parentCodes);
    /**
     * 
     * @param issuesType
     * @return
     */
    public int updateIssuesTypeById(String issueName,int issueCode);
    /**
     * 
     * @param projGuid
     * @return
     */
    public List<InspectRoomInfoVo> getAllBldInfoListById(String projGuid);
    /**
     * 
     * @param bldGuid
     * @param status
     * @return
     */
    public int insertBuildingExt(String bldGuid,int status);
}
