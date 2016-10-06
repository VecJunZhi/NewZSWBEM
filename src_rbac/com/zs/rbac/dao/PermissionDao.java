package com.zs.rbac.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.rbac.entity.Menu;
import com.zs.rbac.entity.Permission;
import com.zs.rbac.entity.Role;

/**
 * 
 * @author zj
 * @version 1.0 @
 */
@Repository
public interface PermissionDao {

	/**
	 * 创建权限
	 * 
	 * @param permission
	 * @return
	 */
	public int createPermission(Permission permission);

	/**
	 * 更改权限
	 * 
	 * @param permission
	 * @return
	 */
	public int updatePermission(Permission permission);

	/**
	 * 查询权限
	 * 
	 * @param permission
	 * @return
	 */
	public List<Permission> queryPermission(Permission permission);


	/**
	 * 删除权限
	 * 
	 * @param permissionId
	 */
	public int deletePermission(int permissionId);

	/**
	 * 获得指定权限先对应的角色
	 * 
	 * @param permission
	 * @return
	 */
	public List<Role> getRoleByPermission(Permission permission);
	
	

	
	
	

}
