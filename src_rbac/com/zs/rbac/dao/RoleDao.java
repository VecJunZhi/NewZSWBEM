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
	 * 创建角色
	 * 
	 * @param role
	 * @return
	 */
	public int createRole(Role role);

	/**
	 * 更改角色
	 * 
	 * @param role
	 */
	public int updateRole(Role role);

	/**
	 * 删除角色
	 * 
	 * @param roleId
	 */
	public int deleteRole(int roleId);
	/**
	 * 删除角色--权限关联
	 * @param rolePermission
	 * @return
	 */
	public int deleteRolePermission(RolePermission rolePermission);
	/**
	 * 获得所有的roles
	 * 
	 * @return
	 */
	public List<Role> getRoles(Role role);
	/**
	 * 根据roleId 获取对应的user
	 * @param role
	 * @return
	 */
	public List<User>getUsersByRoleID(Role role);

	/**
	 * 添加角色--用户关系
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	public int correUser_Role(UserRole userRole);
	/**
	 * 添加角色--用户关系
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	public int deleteUserRole(UserRole userRole);
	/**
	 * 添加角色--权限关系
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	public int correRole_Permission(RolePermission rolePermission);
	/**
	 * 判断是否添加角色--权限关系
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	public  List<RolePermission> ifCor_role_permission(RolePermission rolePermission);
	

	/**
	 * 解除角色--权限关系
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	//public void uncorrelationRole_Permissions(int roleId, int... permissionIds);

	/**
	 * 通过role，获得此role对应的权限
	 * 
	 * @param role
	 * @return
	 */
	//List<Permission> getPermissionByRole(Role role);

	

	/**
	 * 获得指定role下对应的用户集合
	 * 
	 * @param role
	 * @return
	 */
	//List<User> getUsersByRole(Role role);

	/**
	 * 获得指定role下的用户组
	 * 
	 * @param role
	 * @return
	 */
	//	List<UserGroup> getUserGroupByRole(Role role);

}
