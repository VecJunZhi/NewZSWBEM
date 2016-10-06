package com.zs.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.common.dao.InspectRoomDao;
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
import com.zs.common.service.InspectRoomService;

@Service
public class InspectRoomServiceImpl implements InspectRoomService{
	@Autowired
	InspectRoomDao inspectRoomDao;
	
	public List<InspectRoomInfoVo> getIssuesInfoList(InspectRoomSearchInfoVo bean) {
		List<InspectRoomInfoVo> issuesInfoList = new ArrayList<InspectRoomInfoVo>();
		issuesInfoList = inspectRoomDao.getIssuesInfoListDao(bean);
		return issuesInfoList;
	}
	public List<IssuesTypeTableEntity> getIssuesTypeList(String type) {
		List<IssuesTypeTableEntity> issuesTypeList = new ArrayList<IssuesTypeTableEntity>();
		issuesTypeList = inspectRoomDao.getIssuesTypeListDao(type);
		return issuesTypeList;
	}
	public List<InspectRoomInfoVo> getBldInfoList(InspectRoomSearchInfoVo bean) {
		List<InspectRoomInfoVo> bldInfoList = new ArrayList<InspectRoomInfoVo>();
		bldInfoList = inspectRoomDao.getBldInfoListDao(bean);
		return bldInfoList;
	}
	
	public List<BldUnitInfoVo> getBldUnitInfoList(String bldGuid,int type) {
		InspectRoomSearchInfoVo bean = new InspectRoomSearchInfoVo();
		List<BldUnitInfoVo> bldUnitInfoList = new ArrayList<BldUnitInfoVo>();
		bean.setBldGuid(bldGuid);
		bean.setType(type);
		System.out.println("bldGuid:"+bldGuid+"type:"+type);
		bldUnitInfoList = inspectRoomDao.getBldUnitInfoListDao(bean);
		return bldUnitInfoList;
	}
	public List<InspectRoomInfoVo> getIssueFeedbackList(int issuesId) {
		InspectRoomSearchInfoVo bean = new InspectRoomSearchInfoVo();
		List<InspectRoomInfoVo> feedbackList = new ArrayList<InspectRoomInfoVo>();
		bean.setIssuesId(issuesId);
		bean.setSortName("createDate");
		bean.setSortDir("desc");
		feedbackList = inspectRoomDao.getIssueFeedbackListDao(bean);
		return feedbackList;
	}
	public List<InspectRoomInfoVo> getIssueFeedbackList(int issuesId,int startIndex,int length) {
		InspectRoomSearchInfoVo bean = new InspectRoomSearchInfoVo();
		List<InspectRoomInfoVo> feedbackList = new ArrayList<InspectRoomInfoVo>();
		bean.setIssuesId(issuesId);
		bean.setStartIndex(startIndex);
		bean.setLength(length);
		bean.setSortName("createDate");
		bean.setSortDir("desc");
		feedbackList = inspectRoomDao.getIssueFeedbackListDao(bean);
		return feedbackList;
	}
	public int insertIssueDetailList(IssuesRoomTableEntity issuesRoom,InspectRoomTableEntity inspectRoom,List<IssuesDetailTableEntity> bean) {
		int flag = 0;
		inspectRoomDao.insertInspectRoomDao(inspectRoom);
		
		int issueRoomId = inspectRoomDao.getIssuesRoomIdDao(issuesRoom);
		for(IssuesDetailTableEntity issueDetail:bean) {
			issueDetail.setIssueRoomId(issueRoomId);
		}
		flag = inspectRoomDao.insertIssueDetailListDao(bean);
		return flag;
	}
	public int insertIssueFeedbackInfo(IssueFeedbackTableEntity bean,int status) {
		int insertFlag = 0;
		int updateFlag = 0;
		IssuesDetailTableEntity issuesDetail = new IssuesDetailTableEntity();
		issuesDetail.setId(bean.getIssuesId());
		issuesDetail.setStatus(String.valueOf(status));
		insertFlag = inspectRoomDao.insertIssueFeedbackDao(bean);
		updateFlag = inspectRoomDao.updateIssuesStatusByIdDao(issuesDetail);
		return insertFlag&updateFlag;
	}
	public List<IssuesDetailTableEntity> getIssuesDetailById(IssuesDetailTableEntity bean) {
		List<IssuesDetailTableEntity> issuesDetail = new ArrayList<IssuesDetailTableEntity>();
		issuesDetail = inspectRoomDao.getIssuesDetailByIdDao(bean);
		return issuesDetail;
	}
	public List<BuildUnitTableEntity> getUnitInfoByBld(String bldGuid) {
		List<BuildUnitTableEntity> unitList = new ArrayList<BuildUnitTableEntity>();
		unitList = inspectRoomDao.getUnitInfoByBldDao(bldGuid);
		return unitList;
	}
	public  int updateIssuesDetailById(IssuesDetailTableEntity bean){
		int flag = 0;
		flag = inspectRoomDao.updateIssuesDetailByIdDao(bean);
		return flag;
	}
	public int deleteIssuesDetailById(int id) {
		int flag = 0;
		flag = inspectRoomDao.deleteIssuesDetailByIdDao(id);
		return flag;
	}
    public int deleteIssuesRoomById(int id) {
    	int flag = 0;
    	flag = inspectRoomDao.deleteIssuesRoomByIdDao(id);
    	return flag;
    }
    public int deleteInspectRoomById(String roomGuid) {
    	int flag = 0;
    	flag = inspectRoomDao.deleteInspectRoomByIdDao(roomGuid);
    	return flag;
    }
    public IssuesRoomTableEntity getIssuesRoomById(int id) {
    	IssuesRoomTableEntity issuesRoom = inspectRoomDao.getIssuesRoomByIdDao(id);
    	return issuesRoom;
    }
    public int insertIssueFeedback(IssueFeedbackTableEntity feedback,int status) {
    	int flag1 = inspectRoomDao.insertIssueFeedbackDao(feedback);
    	IssuesDetailTableEntity bean = new IssuesDetailTableEntity();
    	bean.setId(feedback.getIssuesId());
    	bean.setStatus(String.valueOf(status));
    	int flag2 = inspectRoomDao.updateIssuesStatusByIdDao(bean);
    	return flag1&flag2;
    }
    public int insertIssueFeedback(IssueFeedbackTableEntity feedback,int status,String endDate) {
    	int flag1 = inspectRoomDao.insertIssueFeedbackDao(feedback);
    	IssuesDetailTableEntity bean = new IssuesDetailTableEntity();
    	bean.setId(feedback.getIssuesId());
    	bean.setStatus(String.valueOf(status));
    	bean.setEndDate(endDate);
    	int flag2 = inspectRoomDao.updateIssuesStatusByIdDao(bean);
    	return flag1&flag2;
    }
    public int insertIssueFeedback(IssueFeedbackTableEntity feedback,int issueRoomId,int status,String endDate) {
    	int flag1 = inspectRoomDao.insertIssueFeedbackDao(feedback);
    	IssuesDetailTableEntity issuesDetail = new IssuesDetailTableEntity();
    	issuesDetail.setId(feedback.getIssuesId());
    	issuesDetail.setStatus(String.valueOf(status));
    	issuesDetail.setEndDate(endDate);
    	int flag2 = inspectRoomDao.updateIssuesStatusByIdDao(issuesDetail);
    	IssuesRoomTableEntity issuesRoom = new IssuesRoomTableEntity();
    	issuesRoom.setIssueRoomId(issueRoomId);
    	issuesRoom.setStatus(2);
    	issuesRoom.setEndDate(endDate);
    	int flag3 = inspectRoomDao.updateIssuesRoomByIdDao(issuesRoom);
    	return flag1&flag2&flag3;
    }
    public List<String> getEmployeeList() {
    	List<String> employeeList = new ArrayList<String>();
    	employeeList = inspectRoomDao.getEmployeeListDao();
    	return employeeList;
    }
    public List<IssuesTypeTableEntity> getAllIssusTypeList(InspectRoomSearchInfoVo bean) {
    	List<IssuesTypeTableEntity> typeList = new ArrayList<IssuesTypeTableEntity>();
    	typeList = inspectRoomDao.getAllIssusTypeListDao(bean);
    	return typeList;
    }
    public int deleteIssuesTypeById(int issuesId) {
    	int flag = 0;
    	flag = inspectRoomDao.deleteIssuesTypeByIdDao(issuesId);
    	return flag;
    }
    public int insertIssuesType(String issueName,String parentCode) {
    	IssuesTypeTableEntity issueType = new IssuesTypeTableEntity();
    	int flag = 0;
    	issueType.setIssueName(issueName);
    	issueType.setParentCode(parentCode);
    	flag = inspectRoomDao.insertIssuesTypeDao(issueType);
    	return flag;
    }
    public int updateIssuesTypeById(String issueName,int issueCode) {
    	IssuesTypeTableEntity issueType = new IssuesTypeTableEntity();
    	int flag = 0;
    	issueType.setIssueName(issueName);
    	issueType.setIssueCode(issueCode);
    	flag = inspectRoomDao.updateIssuesTypeByIdDao(issueType);
    	return flag;
    }
    public List<InspectRoomInfoVo> getAllBldInfoListById(String projGuid) {
    	List<InspectRoomInfoVo> bldList = new ArrayList<InspectRoomInfoVo>();
    	bldList = inspectRoomDao.getAllBldInfoListByIdDao(projGuid);
    	return bldList;
    }
    public int insertBuildingExt(String bldGuid,int status) {
    	BuildingExtTableEntity buildExt = new BuildingExtTableEntity();
    	buildExt.setBldGuid(bldGuid);
    	buildExt.setBldCheckStatus(status);
    	int flag = 0;
    	flag = inspectRoomDao.insertBuildingExtDao(buildExt);
    	return flag;
    }
}
