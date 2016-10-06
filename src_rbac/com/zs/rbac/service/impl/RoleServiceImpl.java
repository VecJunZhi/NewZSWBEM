package com.zs.rbac.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.rbac.dao.RoleDao;
import com.zs.rbac.dao.impl.RoleDaoImpl;
import com.zs.rbac.entity.Role;
import com.zs.rbac.entity.RolePermission;
import com.zs.rbac.entity.User;
import com.zs.rbac.entity.UserRole;
import com.zs.rbac.service.RoleService;



/**
 * <p>User: zj
 * <p>Date: 15-9-5
 * <p>Version: 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

	
	@Autowired
	private RoleDao roleDao;

	@Override
	public int createRole(String roleName, String description,
			Boolean available, String createTime, String lastTime,
			String priority, int orgid) {
		Role role=new Role(roleName, description, available, createTime, lastTime, priority, orgid, 0);
		int _int=roleDao.createRole(role);
		return _int;
	}
	@Override
    public int deleteRole(int roleId) {
        int _int=roleDao.deleteRole(roleId);
		return _int;
    }
	@Override
	public int updateRole(Role role) {
		int _int=roleDao.updateRole(role);
		return _int;
		
	}

	@Override
	public List<Role> getRoles(Role role) {
		List<Role> list=roleDao.getRoles(role);
		return list;
	}

    /**
     * 添加角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void correlationPermissions(int roleId, int... permissionIds) {
        //roleDao.correlationRole_Permissions(roleId, permissionIds);
    }

    /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void uncorrelationPermissions(int roleId, int... permissionIds) {
       // roleDao.uncorrelationRole_Permissions(roleId, permissionIds);
    }
	@Override
	public List<User> getUsersByRoleID(Role role) {
		
		List<User> list=roleDao.getUsersByRoleID(role);
		return list;
	}
	@Override
	public int correUser_Role(int userID,int roleID) {
		UserRole userRole =new UserRole(userID, roleID);
		int _int=roleDao.correUser_Role(userRole);
		return _int;
	}
	@Override
	public int deleteUserRole(int userID, int roleID) {
		UserRole userRole =new UserRole(userID, roleID);
		int _int=roleDao.deleteUserRole(userRole);
		return _int;
	}
	@Override
	public int correRole_Permission(int roleID, int permissionID) {
		RolePermission rolePermission=new RolePermission(roleID, permissionID);
		int _int =roleDao.correRole_Permission(rolePermission);
		return _int;
	}
	@Override
	public List<RolePermission> ifCor_role_permission(int roleID, int permissionID) {
		RolePermission rolePermission=new RolePermission(roleID, permissionID);
		List<RolePermission> list=roleDao.ifCor_role_permission(rolePermission);
		
		return list;
	}
	@Override
	public int deleteRolePermission(int roleID, int permissionID) {
		RolePermission rolePermission=new RolePermission(roleID, permissionID);
		int _int=roleDao.deleteRolePermission(rolePermission);
		return _int;
	}






}
