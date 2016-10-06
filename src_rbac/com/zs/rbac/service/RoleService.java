package com.zs.rbac.service;

import java.util.List;

import com.zs.rbac.entity.Role;
import com.zs.rbac.entity.RolePermission;
import com.zs.rbac.entity.User;
import com.zs.rbac.entity.UserRole;



/**
 * <p>User: zj
 * <p>Date: 15-9-5
 * <p>Version: 1.0
 */
public interface RoleService {

	/**
	 * 创建角色
	 * @param role
	 * @return
	 */
    public int createRole(String roleName,String description,Boolean available,String createTime,String lastTime,String priority,int orgid );
    /**
     * 删除角色
     * @param roleId
     */
    public int deleteRole(int roleID);
    
	/**
	 * 更改角色
	 * 
	 * @param role
	 */
	public int updateRole(Role role);
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
	public List<User>getUsersByRoleID(Role  role);
	/**
	 * 添加角色--用户关系
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	public int correUser_Role(int userID,int roleID);
	/**
	 * 添加角色--用户关系
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	public int deleteUserRole(int userID,int roleID);
	/**
	 * 添加角色--权限关系
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	public int correRole_Permission(int roleID, int permissionID);
	/**
	 * 判断是否添加角色--权限关系
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	public  List<RolePermission> ifCor_role_permission(int roleID, int permissionID);
    /**
     * 添加角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
   // public void correlationPermissions(int roleId, int... permissionIds);

    /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
  //  public void uncorrelationPermissions(int roleId, int... permissionIds);
	/**
	 * 删除角色--权限关联
	 * @param rolePermission
	 * @return
	 */
	public int deleteRolePermission(int roleID, int permissionID);

}
