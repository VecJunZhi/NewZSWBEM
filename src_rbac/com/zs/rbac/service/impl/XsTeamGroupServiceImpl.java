package com.zs.rbac.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.crm.dao.XsInfoDao;
import com.zs.rbac.dao.XsTeamGroupDao;
import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.rbac.service.XsTeamGroupService;
import com.zs.crm.web.vo.XsTeamGroupVo;
import com.zs.rbac.dao.UserExtDao;
import com.zs.rbac.entity.UserExt;
@Service
public class XsTeamGroupServiceImpl implements XsTeamGroupService{
	
	@Autowired
	XsInfoDao xsInfoDao;
	@Autowired
	UserExtDao userExtDao;
	@Autowired
	XsTeamGroupDao xsTeamGroupDao;
	@Override
	public List<XsTeamGroupEntity> getUsersFromXsCRM(String userName) {
		XsTeamGroupVo vo=new XsTeamGroupVo();
		vo.setUserName(userName);
		return xsInfoDao.getUsersFromXsCRM(vo);
	}
	@Override
	public List<XsTeamGroupEntity> getPreConnectUsersByUserId(String userId,String property) {
		XsTeamGroupVo vo=new XsTeamGroupVo();
		vo.setUserId(userId);
		vo.setProperty(property);
		return userExtDao.getPreConnectUsers(vo);
	}
	@Override
	public List<XsTeamGroupEntity> getPreConnectUsers(String userName,
			String property) {
		XsTeamGroupVo vo=new XsTeamGroupVo();
		vo.setUserName(userName);
		vo.setProperty(property);
		return userExtDao.getPreConnectUsers(vo);
	}
	@Override
	public List<XsTeamGroupEntity> getPreConnectUsers(String userName,
			String property,int startLength,int rows,String sidx,String sord) {
		XsTeamGroupVo vo=new XsTeamGroupVo();
		vo.setUserName(userName);
		vo.setProperty(property);
		vo.setStartLength(startLength);
		vo.setRows(rows);
		vo.setSidx(sidx);
		vo.setSord(sord);
		return userExtDao.getPreConnectUsers(vo);
	}
	@Override
	public List<XsTeamGroupEntity> getConnectedUsers(String userName,
			String property) {
		XsTeamGroupVo vo=new XsTeamGroupVo();
		vo.setUserName(userName);
		vo.setProperty(property);
		return userExtDao.getConnectedUsers(vo);
	}
	@Override
	public List<XsTeamGroupEntity> getConnectedUsers(String userName,
			String property,int startLength,int rows,String sidx,String sord) {
		XsTeamGroupVo vo=new XsTeamGroupVo();
		vo.setUserName(userName);
		vo.setProperty(property);
		vo.setStartLength(startLength);
		vo.setRows(rows);
		vo.setSidx(sidx);
		vo.setSord(sord);
		return userExtDao.getConnectedUsers(vo);
	}

	@Override
	public int insertUserConnectExt(List<XsTeamGroupVo> list) {
		XsTeamGroupVo vo=new XsTeamGroupVo();
		vo.setList(list);
		return userExtDao.insertUserConnectExt(vo);
	}

	@Override
	public int deleteUserConnect(String id) {
		XsTeamGroupVo vo=new XsTeamGroupVo();
		vo.setId(id);
		return userExtDao.deleteUserConnect(vo);
	}

	@Override
	public List<XsTeamGroupEntity> getProjectItem() {
		XsTeamGroupVo vo=new XsTeamGroupVo();
		return xsTeamGroupDao.getProjectItem(vo);
	}

	@Override
	public List<XsTeamGroupEntity> getTeamGroupFromProject(String projectId,String isProjectAdmin,String groupType) {
		XsTeamGroupVo vo=new XsTeamGroupVo();
		vo.setProjectId(projectId);
		vo.setIsProjectAdmin(isProjectAdmin);
		vo.setGroupType(groupType);
		return xsTeamGroupDao.getTeamGroupFromProject(vo);
	}
	public List<XsTeamGroupEntity> getTeamGroupFromProject(String projectId) {
		XsTeamGroupVo vo=new XsTeamGroupVo();
		vo.setProjectId(projectId);
		return xsTeamGroupDao.getTeamGroupFromProject(vo);
	}
	public List<XsTeamGroupEntity> getAdminTeamGroupFromProject(String projectId,String isProjecAdmin,String groupType) {
		XsTeamGroupVo vo=new XsTeamGroupVo();
		vo.setProjectId(projectId);
		vo.setIsProjectAdmin(isProjecAdmin);
		vo.setGroupType(groupType);
		return xsTeamGroupDao.getTeamGroupFromProject(vo);
	}
	public List<XsTeamGroupEntity> getTeamGroupFromProjectById(String id) {
		XsTeamGroupVo vo=new XsTeamGroupVo();
		vo.setId(id);
		return xsTeamGroupDao.getTeamGroupFromProject(vo);
	}
	

	@Override
	public List<XsTeamGroupEntity> getUserFromTeamGroup(String teamGroupId,
			String projectId,String userName,String sidx,String sord,String property) {
		XsTeamGroupVo vo=new XsTeamGroupVo();
		vo.setTeamGroupId(teamGroupId);
		vo.setProjectId(projectId);
		vo.setUserName(userName);
		vo.setSidx(sidx);
		vo.setSord(sord);
		vo.setProperty(property);
		return xsTeamGroupDao.getUserFromTeamGroup(vo);
	}
	@Override
	public List<XsTeamGroupEntity> getUserFromTeamGroup(int startLength,int rows,String teamGroupId,
			String projectId,String userName,String sidx,String sord,String property) {
		XsTeamGroupVo vo=new XsTeamGroupVo();
		vo.setTeamGroupId(teamGroupId);
		vo.setProjectId(projectId);
		vo.setStartLength(startLength);
		vo.setUserName(userName);
		vo.setLength(rows);
		vo.setSidx(sidx);
		vo.setSord(sord);
		vo.setProperty(property);
		return xsTeamGroupDao.getUserFromTeamGroup(vo);
	}

	@Override
	public List<XsTeamGroupEntity> getUserFromTeamGroup(XsTeamGroupVo vo) {
		return xsTeamGroupDao.getUserFromTeamGroup(vo);
	}
	@Override
	public int judgeTeamGroupIfExist(String groupName, String projectId) {
		XsTeamGroupVo vo=new XsTeamGroupVo();
		vo.setGroupName(groupName);
		vo.setProjectId(projectId);
		return xsTeamGroupDao.judgeTeamGroupIfExist(vo);
	}

	@Override
	public int insertTeamGroup(String groupName, String projectId,
			String isProjectAdmin, String description,String groupType,String projectName) {
		XsTeamGroupVo vo=new XsTeamGroupVo();
		vo.setGroupName(groupName);
		vo.setProjectId(projectId);
		vo.setIsProjectAdmin(isProjectAdmin);
		vo.setDescription(description);
		vo.setGroupType(groupType);
		vo.setProjectName(projectName);
		vo.setAvailable(1);
		return xsTeamGroupDao.insertTeamGroup(vo);
	}
	@Override
	public int updateTeamGoup(String teamGrouId,String groupName){
		XsTeamGroupVo vo=new XsTeamGroupVo();
		vo.setGroupName(groupName);
		vo.setId(teamGrouId);
		return xsTeamGroupDao.updateTeamGoup(vo);
	}
	@Override
	public int deleteTeamGoup(String teamGroupId,String projectId,String groupType){
		if("xs".equals(groupType)){
			groupType="xs_userGuid";
		}else if("zs".equals(groupType)){
			groupType="bs_userGuid";
		}
		List<XsTeamGroupEntity> list=getUserFromTeamGroup(teamGroupId, projectId,"", "userId", "desc",groupType);
		if(list.size()>0){
			return -1;//不能删除，里面有成员。
		}else{
			List<XsTeamGroupEntity> tList=getTeamGroupFromProjectById(teamGroupId);
			//List<XsTeamGroupEntity> list=xsTeamGroupService.getTeamGroupFromProject(parentID,"0",groupType);
			if("1".equals(tList.get(0).getIsProjectAdmin())){
				List<XsTeamGroupEntity> teamlist=getTeamGroupFromProject(projectId,"0",groupType);
				if(teamlist.size()>0){
					return -2;//分组中有子团队，不能删除
				}else if(list.size()==0){
					XsTeamGroupVo vo=new XsTeamGroupVo();
					vo.setId(teamGroupId);
					int _int=xsTeamGroupDao.deleteTeamGoup(vo);
					return _int;
				}
			}else if(list.size()==0){
				XsTeamGroupVo vo=new XsTeamGroupVo();
				vo.setId(teamGroupId);
				int _int=xsTeamGroupDao.deleteTeamGoup(vo);
				return _int;
			}
		}
		return 0;
		
	}
	@Override
	public List<XsTeamGroupEntity> judgeUserIfExistInTeamGroup(String[] userId, String teamGroupId) {
		XsTeamGroupVo vo=new XsTeamGroupVo();
		vo.setArray(userId);
		vo.setTeamGroupId(teamGroupId);
		return xsTeamGroupDao.judgeUserIfExistInTeamGroup(vo);
	}

	@Override
	public int insertUserToTeamGroup(List<XsTeamGroupVo> list) {
		XsTeamGroupVo vo=new XsTeamGroupVo();
		vo.setList(list);
		return xsTeamGroupDao.insertUserToTeamGroup(vo);
	}

	@Override
	public int updateUserInTeamGroup(String id, String teamGroupId,
			String userLevelId, String description) {
		XsTeamGroupVo vo=new XsTeamGroupVo();
		vo.setId(id);
		vo.setTeamGroupId(teamGroupId);
		vo.setUserLevelId(userLevelId);
		vo.setDescription(description);
		return xsTeamGroupDao.updateUserInTeamGroup(vo);
	}

	@Override
	public int deleteUserFromTeamGroup(String id) {
		XsTeamGroupVo vo=new XsTeamGroupVo();
		vo.setId(id);
		return xsTeamGroupDao.deleteUserFromTeamGroup(vo);
	}
	@Override
	public List<UserExt> judgeIfCorSystem(String utid,String property) {
		UserExt ext =new UserExt();
		ext.setId(utid);
		ext.setProperty(property);
		List<UserExt> list=userExtDao.judgeIfCorSystem(ext);
		return list;
	}

}
