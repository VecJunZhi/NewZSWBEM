package com.zs.rbac.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.crm.web.vo.XsTeamGroupVo;
import com.zs.rbac.entity.UserExt;
@Repository
public interface UserExtDao {
	/**
	 * 获得需要绑定的人员集合
	 * @param vo
	 * @return
	 */
	public List<XsTeamGroupEntity> getPreConnectUsers(XsTeamGroupVo vo);
	/**
	 * 获得已经绑定的人员名单
	 * @param vo
	 * @return
	 */
	public List<XsTeamGroupEntity> getConnectedUsers(XsTeamGroupVo vo);
	/**
	 * 将rbac 用户与 crm 销售用户进行绑定 
	 * @param vo
	 * @return
	 */
	public int insertUserConnectExt(XsTeamGroupVo vo);
	/**
	 *  解除绑定
	 * @param vo
	 * @return
	 */
	public int deleteUserConnect(XsTeamGroupVo vo);
	
	
	/**
	 * 根据用户Id查询用户扩展信息
	 * @param userID
	 * @return
	 */
	public List<UserExt> getUsetExtById(int userId);
	/**
	 * 判断系统是否绑定了销售或招商系统
	 * @param userExt
	 * @return
	 */
	public List<UserExt> judgeIfCorSystem(UserExt userExt);
	/**
	 * 查询扩展信息
	 * @param userExt
	 * @return
	 */
	public List<UserExt> getUserExtInfoDao(UserExt userExt);
	/**
	 * 插入扩展信息
	 * @param userExt
	 * @return
	 */
	public int insertUserExtInfoDao(UserExt userExt);
	/**
	 * 更新扩展信息
	 * @param userExt
	 * @return
	 */
	public int updateUserExtInfoDao(UserExt userExt);

}
