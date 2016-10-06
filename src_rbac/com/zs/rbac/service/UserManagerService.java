package com.zs.rbac.service;

import java.util.List;
import java.util.Set;

import com.zs.rbac.entity.Role;
import com.zs.rbac.entity.User;
import com.zs.rbac.entity.UserExt;
import com.zs.rbac.entity.UserRole;


/**
 * <p>User: zj
 * <p>Date: 15-9-5
 * <p>Version: 1.0
 */
public interface UserManagerService {
	/**
	 * 获取用户
	 * @param user
	 * @return
	 */
	public List<User> getUser(User user);
	/**
	 * 新增用户
	 * 
	 * @param user
	 * @return
	 */
	public int createUser(User user);

	/**
	 * 更新用户,参数为user
	 * 
	 * @param user
	 */
	public int updateUser(User user);
	/**
	 * 删除用户
	 * 
	 * @param userId
	 */
	public int deleteUser(int userId);
	/**
	 * 判断用户名是否存在
	 * @param userName
	 * @return
	 */
	public int judgeIfExistUserName(String userName);
	/**
	 * 判断手机号是否存在
	 * @param userName
	 * @return
	 */
	public int judgeIfExistMobile(String mobile);
	/**
	 * 判断邮箱是否存在
	 * @param userName
	 * @return
	 */
	public int judgeIfExistEmail(String email);
	/**
	 * 
	 * @param userName
	 * @return
	 */
	public List<User> getUserByUserExt(int utid);
	/**
	 * 根据用户的id或角色id查找相应的项目
	 * @param userID:
	 * @param roleID
	 * @return
	 */
	public List<Role> getProjByUserIdOrRoleId(int userID,int  roleID);
}
