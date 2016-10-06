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
	 * ����Ȩ��
	 * 
	 * @param permission
	 * @return
	 */
	public int createPermission(String permissionName,String permissionMark,String permissionType,String parentID,
    		String url,int priority,Boolean available,String createTime,String lastTime,String description);

	/**
	 * ����Ȩ��
	 * 
	 * @param permission
	 * @return
	 */
	public int updateSubSystemModel(int permissionID,String permissionName,String permissionMark,String permissionType,String parentID,
    		String url,int priority,Boolean available,String description);

	/**
	 * ��ѯȨ��
	 * 
	 * @param permission
	 * @return
	 */
	public List<Permission> queryPermission(Permission permission);


	/**
	 * ɾ��Ȩ��
	 * 
	 * @param permissionId
	 */
	public int deletePermission(int permissionId);
	/**
	 * ����һ��ģ�������е�����
	 * @param modelID
	 * @return
	 */
	public List<String> getMenuSubMenuPageIdByModelID(String modelID);
}
