package com.zs.rbac.dao;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.zs.rbac.entity.Role;
import com.zs.rbac.entity.User;

/**
 * <p>
 * User: zj
 * <p>
 * Date: 15-9-5
 * <p>
 * Version: 1.0
 */
@Repository
public interface UserDao {
	/**
	 * 新增用户
	 * 
	 * @param user
	 * @return
	 */
	public User createUser(User user);

	/**
	 * 更新用户,参数为user
	 * 
	 * @param user
	 */
	public void updateUser(User user);

	/**
	 * 更新用户，参数为userid,password
	 * 
	 * @param userid
	 * @param password
	 */
	public void updateUser(int userid, String password);

	/**
	 * 删除用户
	 * 
	 * @param userId
	 */
	public void deleteUser(int userId);

	/**
	 * 添加用户--角色关系
	 * 
	 * @param userId
	 * @param roleIds
	 */
	public void correlationUser_Roles(int userId, int... roleIds);

	/**
	 * 解除用户--角色关系
	 * 
	 * @param userId
	 * @param roleIds
	 */
	public void uncorrelationUser_Roles(int userId, int... roleIds);

	/**
	 * 根据用户id得到用户
	 * 
	 * @param userId
	 * @return
	 */
	User findOne(int userId);

	/**
	 * 根据用户名得到用户
	 * 
	 * @param username
	 * @return
	 */
	User findByUsername(String username);
	
    /**
     * 跟据用户名查询用户（用户名指userName,mobile,email）
     * @param loginName
     * @return
     */
    public User findUserByLoginName(String loginName);

	/**
	 * 查找用户对应的role,返回role实体
	 * 
	 * @param username
	 * @return
	 */
	List<Role> getRolesByUser(User user);

	/**
	 * 获得该用户所在的用户组集合
	 * 
	 * @param username
	 * @return
	 */
//	List<UserGroup> getUserGroupByUser(User user);

	/**
	 * 增加用户--用户组的映射关系
	 * 
	 * @param userid
	 * @param usergroupID
	 */
	public void correlationUser_UserGroup(int userid, int... usergroupID);

	/**
	 * 解除用户--用户组的映射关系
	 * 
	 * @param userid
	 * @param usergroupID
	 */
	public void uncorrelationUser_UserGroup(int userid, int... usergroupID);

	/**
	 * 得到该用户对应的组织集合
	 * 
	 * @param username
	 * @return
	 */
//	List<Organization> getOrganizationByUser(User user);

	/**
	 * 添加用户--组织映射关系
	 * 
	 * @param userid
	 * @param usergroupID
	 */
	public void correlationUser_Organization(int userid, int... orgID);

	/**
	 * 解除用户--组织映射关系
	 * 
	 * @param userid
	 * @param orgID
	 */
	public void uncorrelationUser_Organization(int userid, int... orgID);

	/**
	 * 根据用户名得到用户对应的所有的角色集合
	 * 
	 * @param username
	 * @return
	 */
	Set<String> findRoles(String username);

	/**
	 * 根据用户名得到用户的权限集合
	 * 
	 * @param username
	 * @return
	 */
	Set<String> findPermissions(String username);

	List<User> getUsers();
}
