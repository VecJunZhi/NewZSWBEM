package com.zs.rbac.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zs.rbac.entity.Permission;

/**
 * <p>User: zj
 * <p>Date: 15-9-5
 * <p>Version: 1.0
 */

public interface PermissionService {
	/**
	 * 创建权限
	 * 
	 * @param permission
	 * @return
	 */
	public int createPermission(String permissionName,String permissionMark,String permissionType,String parentID,
    		String url,int priority,Boolean available,String createTime,String lastTime,String description);

	/**
	 * 更改权限
	 * 
	 * @param permission
	 * @return
	 */
	public int updateSubSystemModel(int permissionID,String permissionName,String permissionMark,String permissionType,String parentID,
    		String url,int priority,Boolean available,String description);

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
	 * 查找一个模块下所有的子类
	 * @param modelID
	 * @return
	 */
	public List<String> getMenuSubMenuPageIdByModelID(String modelID);
}
