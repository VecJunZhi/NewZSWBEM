package com.zs.rbac.dao;

import java.util.List;

import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.crm.web.vo.XsTeamGroupVo;

public interface XsTeamGroupDao {
	/**
	 * 获得销售项目列表 
	 * @param vo
	 * @return
	 */
	public List<XsTeamGroupEntity> getProjectItem(XsTeamGroupVo vo);
	/**
	 * 获得销售项目对应的团队分组信息
	 * @param vo
	 * @return
	 */
	public List<XsTeamGroupEntity> getTeamGroupFromProject(XsTeamGroupVo vo);
	/**
	 * 获得对应组的组员信息
	 * @param vo
	 * @return
	 */
	public List<XsTeamGroupEntity> getUserFromTeamGroup(XsTeamGroupVo vo);
	/**
	 * 当新增分组时，判断该分组是否已经存在  
	 * @param vo
	 * @return
	 */
	public int judgeTeamGroupIfExist(XsTeamGroupVo vo);
	/**
	 * 对相应项目增加分组
	 * @param vo
	 * @return
	 */
	public int insertTeamGroup(XsTeamGroupVo vo);
	/**
	 * 修改对应项目的分组
	 * @param vo
	 * @return
	 */
	public int updateTeamGoup(XsTeamGroupVo vo);
	/**
	 * 删除对应项目的分组
	 * @param vo
	 * @return
	 */
	public int deleteTeamGoup(XsTeamGroupVo vo);
	/**
	 * 当新增成员时，判断该成员是否已经存在于该项目组中
	 * @param vo
	 * @return
	 */
	public List<XsTeamGroupEntity> judgeUserIfExistInTeamGroup(XsTeamGroupVo vo);
	/**
	 * 新增组里成员 
	 * @param vo
	 * @return
	 */
	public int insertUserToTeamGroup(XsTeamGroupVo vo);
	/**
	 * 编辑组里成员  将该成员移到其他组   将该成员定义为组长或组员 -
	 * @param vo
	 * @return
	 */
	public int updateUserInTeamGroup(XsTeamGroupVo vo);
	/**
	 *  移除组里成员
	 * @param vo
	 * @return
	 */
	public int deleteUserFromTeamGroup(XsTeamGroupVo vo);
}
