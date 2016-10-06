package com.zs.rbac.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.rbac.entity.Permission;
import com.zs.rbac.entity.Role;
import com.zs.rbac.entity.RolePermission;
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
public interface RoleDao {
	/**
	 * ������ɫ
	 * 
	 * @param role
	 * @return
	 */
	public int createRole(Role role);

	/**
	 * ���Ľ�ɫ
	 * 
	 * @param role
	 */
	public int updateRole(Role role);

	/**
	 * ɾ����ɫ
	 * 
	 * @param roleId
	 */
	public int deleteRole(int roleId);
	/**
	 * ɾ����ɫ--Ȩ�޹���
	 * @param rolePermission
	 * @return
	 */
	public int deleteRolePermission(RolePermission rolePermission);
	/**
	 * ������е�roles
	 * 
	 * @return
	 */
	public List<Role> getRoles(Role role);
	/**
	 * ����roleId ��ȡ��Ӧ��user
	 * @param role
	 * @return
	 */
	public List<User>getUsersByRoleID(Role role);

	/**
	 * ��ӽ�ɫ--�û���ϵ
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	public int correUser_Role(UserRole userRole);
	/**
	 * ��ӽ�ɫ--�û���ϵ
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	public int deleteUserRole(UserRole userRole);
	/**
	 * ��ӽ�ɫ--Ȩ�޹�ϵ
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	public int correRole_Permission(RolePermission rolePermission);
	/**
	 * �ж��Ƿ���ӽ�ɫ--Ȩ�޹�ϵ
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	public  List<RolePermission> ifCor_role_permission(RolePermission rolePermission);
	

	/**
	 * �����ɫ--Ȩ�޹�ϵ
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	//public void uncorrelationRole_Permissions(int roleId, int... permissionIds);

	/**
	 * ͨ��role����ô�role��Ӧ��Ȩ��
	 * 
	 * @param role
	 * @return
	 */
	//List<Permission> getPermissionByRole(Role role);

	

	/**
	 * ���ָ��role�¶�Ӧ���û�����
	 * 
	 * @param role
	 * @return
	 */
	//List<User> getUsersByRole(Role role);

	/**
	 * ���ָ��role�µ��û���
	 * 
	 * @param role
	 * @return
	 */
	//	List<UserGroup> getUserGroupByRole(Role role);

}
