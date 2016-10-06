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
	 * ����Ȩ��
	 * 
	 * @param permission
	 * @return
	 */
	public int createPermission(Permission permission);

	/**
	 * ����Ȩ��
	 * 
	 * @param permission
	 * @return
	 */
	public int updatePermission(Permission permission);

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
	 * ���ָ��Ȩ���ȶ�Ӧ�Ľ�ɫ
	 * 
	 * @param permission
	 * @return
	 */
	public List<Role> getRoleByPermission(Permission permission);
	
	

	
	
	

}
