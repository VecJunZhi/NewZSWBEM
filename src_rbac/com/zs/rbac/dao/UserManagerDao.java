package com.zs.rbac.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.rbac.entity.Role;
import com.zs.rbac.entity.User;
import com.zs.rbac.entity.UserRole;

/**
 * <p>
 * User: zj
 * <p>
 * Date: 15-9-5
 * <p>
 * Version: 1.0
 */
@Repository
public interface UserManagerDao {
	/**
	 * ��ȡ�û�
	 * @param user
	 * @return
	 */
	public List<User> getUser(User user);
	/**
	 * �����û�
	 * 
	 * @param user
	 * @return
	 */
	public int createUser(User user);

	/**
	 * �����û�,����Ϊuser
	 * 
	 * @param user
	 */
	public int updateUser(User user);
	/**
	 * ɾ���û�
	 * 
	 * @param userId
	 */
	public int deleteUser(int userId);
	/**
	 * �ж��û����Ƿ����
	 * @param userName
	 * @return
	 */
	public int judgeIfExistUserName(String userName);
	/**
	 * �ж��ֻ����Ƿ����
	 * @param userName
	 * @return
	 */
	public int judgeIfExistMobile(String mobile);
	/**
	 * �ж������Ƿ����
	 * @param userName
	 * @return
	 */
	public int judgeIfExistEmail(String email);
	/**
	 * 
	 * @param userName
	 * @return
	 */
	public List<User> getUserByUserExt(User user);
	/**
	 * �����û���id���ɫid������Ӧ����Ŀ
	 * @param userRole
	 * @return
	 */
	public List<Role> getProjByUserIdOrRoleId(UserRole userRole);
	/**
	 * 
	 * @param role
	 * @return
	 */
	public List<Role> getProjByRoleName(Role role);
}
