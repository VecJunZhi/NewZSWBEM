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
	 * �����û�
	 * 
	 * @param user
	 * @return
	 */
	public User createUser(User user);

	/**
	 * �����û�,����Ϊuser
	 * 
	 * @param user
	 */
	public void updateUser(User user);

	/**
	 * �����û�������Ϊuserid,password
	 * 
	 * @param userid
	 * @param password
	 */
	public void updateUser(int userid, String password);

	/**
	 * ɾ���û�
	 * 
	 * @param userId
	 */
	public void deleteUser(int userId);

	/**
	 * ����û�--��ɫ��ϵ
	 * 
	 * @param userId
	 * @param roleIds
	 */
	public void correlationUser_Roles(int userId, int... roleIds);

	/**
	 * ����û�--��ɫ��ϵ
	 * 
	 * @param userId
	 * @param roleIds
	 */
	public void uncorrelationUser_Roles(int userId, int... roleIds);

	/**
	 * �����û�id�õ��û�
	 * 
	 * @param userId
	 * @return
	 */
	User findOne(int userId);

	/**
	 * �����û����õ��û�
	 * 
	 * @param username
	 * @return
	 */
	User findByUsername(String username);
	
    /**
     * �����û�����ѯ�û����û���ָuserName,mobile,email��
     * @param loginName
     * @return
     */
    public User findUserByLoginName(String loginName);

	/**
	 * �����û���Ӧ��role,����roleʵ��
	 * 
	 * @param username
	 * @return
	 */
	List<Role> getRolesByUser(User user);

	/**
	 * ��ø��û����ڵ��û��鼯��
	 * 
	 * @param username
	 * @return
	 */
//	List<UserGroup> getUserGroupByUser(User user);

	/**
	 * �����û�--�û����ӳ���ϵ
	 * 
	 * @param userid
	 * @param usergroupID
	 */
	public void correlationUser_UserGroup(int userid, int... usergroupID);

	/**
	 * ����û�--�û����ӳ���ϵ
	 * 
	 * @param userid
	 * @param usergroupID
	 */
	public void uncorrelationUser_UserGroup(int userid, int... usergroupID);

	/**
	 * �õ����û���Ӧ����֯����
	 * 
	 * @param username
	 * @return
	 */
//	List<Organization> getOrganizationByUser(User user);

	/**
	 * ����û�--��֯ӳ���ϵ
	 * 
	 * @param userid
	 * @param usergroupID
	 */
	public void correlationUser_Organization(int userid, int... orgID);

	/**
	 * ����û�--��֯ӳ���ϵ
	 * 
	 * @param userid
	 * @param orgID
	 */
	public void uncorrelationUser_Organization(int userid, int... orgID);

	/**
	 * �����û����õ��û���Ӧ�����еĽ�ɫ����
	 * 
	 * @param username
	 * @return
	 */
	Set<String> findRoles(String username);

	/**
	 * �����û����õ��û���Ȩ�޼���
	 * 
	 * @param username
	 * @return
	 */
	Set<String> findPermissions(String username);

	List<User> getUsers();
}
