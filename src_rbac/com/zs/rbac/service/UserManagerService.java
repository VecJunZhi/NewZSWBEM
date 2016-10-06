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
	public List<User> getUserByUserExt(int utid);
	/**
	 * �����û���id���ɫid������Ӧ����Ŀ
	 * @param userID:
	 * @param roleID
	 * @return
	 */
	public List<Role> getProjByUserIdOrRoleId(int userID,int  roleID);
}
