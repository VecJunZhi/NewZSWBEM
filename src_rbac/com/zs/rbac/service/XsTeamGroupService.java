package com.zs.rbac.service;

import java.util.List;

import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.crm.web.vo.XsTeamGroupVo;
import com.zs.rbac.entity.UserExt;

public interface XsTeamGroupService {
	/**
	 * 获得crm上所有用户的列表信息 
	 * @param userName :可以根据username进行模糊检索
	 * @return
	 */
	public List<XsTeamGroupEntity> getUsersFromXsCRM(String userName);
	/**
	 * 获得需要绑定的人员集合
	 * @param username 
	 * @param propery : 指定用户扩展表中的属性名称
	 * @return
	 */
	public List<XsTeamGroupEntity> getPreConnectUsers(String userName ,String property);
	public List<XsTeamGroupEntity> getPreConnectUsers(String userName,
			String property,int startLength,int rows,String sidx,String sord) ;
	/**
	 * 获得已经绑定的人员名单
	 * @param vo
	 * @return
	 */
	public List<XsTeamGroupEntity> getPreConnectUsersByUserId(String userId,String property);
	public List<XsTeamGroupEntity> getConnectedUsers(String userName ,String property);
	public List<XsTeamGroupEntity> getConnectedUsers(String userName,
			String property,int startLength,int rows,String sidx,String sord);
	/**
	 * 将rbac 用户与 crm 销售用户进行绑定 
	 * @param userId 用户id
	 * @param userName：用户名称
	 * @param property：绑定属性
	 * @param value：绑定属性值
	 * @param description：绑定属性描述
	 * @return
	 */
	public int insertUserConnectExt(List<XsTeamGroupVo> list);
	/**
	 *  解除绑定
	 * @param id:用户扩展表中的自增长id
	 * @return
	 */
	public int deleteUserConnect(String id);
	/**
	 * 获得销售项目列表 
	 * @param 无 ：进行遍历获取所有的项目
	 * @return
	 */
	public List<XsTeamGroupEntity> getProjectItem();
	/**
	 * 获得销售项目对应的团队分组信息
	 * @param 无：进行遍历获取每个项目对应的项目分组
	 * @return
	 */
	public List<XsTeamGroupEntity> getTeamGroupFromProject(String projectId,String isProjectAdmin,String groupType);
	public List<XsTeamGroupEntity> getTeamGroupFromProject(String projectId);
	public List<XsTeamGroupEntity> getAdminTeamGroupFromProject(String projectId,String isProjecAdmin,String groupType);
	public List<XsTeamGroupEntity> getTeamGroupFromProjectById(String id);
	/**
	 * 获得对应组的组员信息
	 * @param teamGroupId : 项目组的id号
	 * @param projectId ： 项目的id号
	 * @return
	 */
	public List<XsTeamGroupEntity> getUserFromTeamGroup(String teamGroupId,String projectId,String userName,String sidx,String sord,String property);
	public List<XsTeamGroupEntity> getUserFromTeamGroup(int page,int rows,String teamGroupId,String projectId,String userName,String sidx,String sord,String property);
	public List<XsTeamGroupEntity> getUserFromTeamGroup(XsTeamGroupVo vo);
	/**
	 * 当新增分组时，判断该分组是否已经存在  
	 * @param groupName : 输入的分组名称
	 * @param projectId ： 项目的id号
	 * @return：0 说明不存在，非0 说明该组已经存在
	 */
	public int judgeTeamGroupIfExist(String groupName,String projectId);
	/**
	 *  对相应项目增加分组
	 * @param groupName : 新增的项目分组名称
	 * @param projectId ：项目id
	 * @param isProjectAdmin：是否为项目管理员
	 * @param description：描述
	 * @return
	 */
	public int insertTeamGroup(String groupName,String projectId,String isProjectAdmin,String description,String groupType,String projectName);
	/**
	 * 修改对应项目的分组
	 * @param vo
	 * @return
	 */
	public int updateTeamGoup(String teamGrouId,String groupName);
	/**
	 * 删除对应项目的分组
	 * @param vo
	 * @return
	 */
	public int deleteTeamGoup(String teamGrouId,String projectId,String groupType);
	/**
	 * 当新增成员时，判断该成员是否已经存在于该项目组中
	 * @param userId ：选中的用户的id号
	 * @param teamGroupId ：对应的项目分组的id号
	 * @return 0 说明不存在，非0 说明该组已经存在
	 */
	public List<XsTeamGroupEntity> judgeUserIfExistInTeamGroup(String[] userId,String teamGroupId);
	/**
	 * 新增组里成员 
	 * @param userId :用户id
	 * @param teamGroupId：分组id
	 * @param userLevelId：用户等级 组长/组员
	 * @param description ：描述
	 * @return
	 */
	public int insertUserToTeamGroup(List<XsTeamGroupVo> vo);
	/**
	 * 编辑组里成员  将该成员移到其他组   将该成员定义为组长或组员 
	 * @param id:要修改的用户在用户_团队分组表中的自增长id
	 * @param teamGroupId ： 团队分组的id号，修改此值可以将成员移到其他组里
	 * @param userLevelId： 用户的级别，组长/组员
	 * @param description：描述
	 * @return
	 */
	public int updateUserInTeamGroup(String id,String teamGroupId,String userLevelId,String description);
	/**
	 *  移除组里成员
	 * @param id 用户在用户_团队分组表中的自增长id
	 * @return
	 */
	public int deleteUserFromTeamGroup(String id);
	/**
	 * 判断系统是否绑定了销售或招商系统
	 * @param userExt
	 * @return
	 */
	public List<UserExt> judgeIfCorSystem(String utid,String property);
	
}
