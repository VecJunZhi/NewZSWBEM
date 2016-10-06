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
	 * ������ɫ
	 * @param role
	 * @return
	 */
    public int createRole(String roleName,String description,Boolean available,String createTime,String lastTime,String priority,int orgid );
    /**
     * ɾ����ɫ
     * @param roleId
     */
    public int deleteRole(int roleID);
    
	/**
	 * ���Ľ�ɫ
	 * 
	 * @param role
	 */
	public int updateRole(Role role);
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
	public List<User>getUsersByRoleID(Role  role);
	/**
	 * ��ӽ�ɫ--�û���ϵ
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	public int correUser_Role(int userID,int roleID);
	/**
	 * ��ӽ�ɫ--�û���ϵ
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	public int deleteUserRole(int userID,int roleID);
	/**
	 * ��ӽ�ɫ--Ȩ�޹�ϵ
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	public int correRole_Permission(int roleID, int permissionID);
	/**
	 * �ж��Ƿ���ӽ�ɫ--Ȩ�޹�ϵ
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	public  List<RolePermission> ifCor_role_permission(int roleID, int permissionID);
    /**
     * ��ӽ�ɫ-Ȩ��֮���ϵ
     * @param roleId
     * @param permissionIds
     */
   // public void correlationPermissions(int roleId, int... permissionIds);

    /**
     * �Ƴ���ɫ-Ȩ��֮���ϵ
     * @param roleId
     * @param permissionIds
     */
  //  public void uncorrelationPermissions(int roleId, int... permissionIds);
	/**
	 * ɾ����ɫ--Ȩ�޹���
	 * @param rolePermission
	 * @return
	 */
	public int deleteRolePermission(int roleID, int permissionID);

}
