package com.zs.rbac.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.common.util.DateUtil;
import com.zs.rbac.dao.PermissionDao;
import com.zs.rbac.entity.Permission;
import com.zs.rbac.entity.RolePermission;
import com.zs.rbac.service.PermissionService;



/**
 * <p>User: zj
 * <p>Date: 15-9-5
 * <p>Version: 1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {

	
    //private PermissionDao permissionDao = new PermissionDaoImpl();
	@Autowired
	private PermissionDao permissionDao;

    public int createPermission(String permissionName,String permissionMark,String permissionType,String parentID,
    		String url,int priority,Boolean available,String createTime,String lastTime,String description) {
    	Permission permission=new Permission(permissionMark, description, available, permissionName, permissionType, parentID, url, priority, createTime, lastTime);
        return permissionDao.createPermission(permission);
    }

    public int deletePermission(int permissionId) {
        int _int=permissionDao.deletePermission(permissionId);
        return _int;
    }

	@Override
	public int updateSubSystemModel(int permissionID,String permissionName,String permissionMark,String permissionType,String parentID,
    		String url,int priority,Boolean available,String description) {
		String createTime=DateUtil.getNowDateSS();
		String lastTime=DateUtil.getNowDateSS();
		Permission permission =new Permission(permissionID, permissionMark, description, available, permissionName, permissionType, parentID, url, priority, createTime, lastTime);
		int _int=permissionDao.updatePermission(permission);
        return _int;
	}

	@Override
	public List<Permission> queryPermission(Permission permission) {
		List<Permission> list=permissionDao.queryPermission(permission);
		return list;
	}
	@Override
	public List<String> getMenuSubMenuPageIdByModelID(String modelID){
		List<String> list = new ArrayList<String>();
		Permission permission=new Permission();
		permission.setPermissionType("menu");
		permission.setParentID(modelID);
		List<Permission> menulist=queryPermission(permission);
		for(Permission permission2 : menulist){
			int menuID=permission2.getPermissionID();
			list.add(Integer.toString(menuID));//menu
			Permission permission3=new Permission();
			permission3.setPermissionType("menu");
			permission3.setParentID(Integer.toString(menuID));
			List<Permission> subMenulist=queryPermission(permission3);
			for (Permission permission4 : subMenulist) {
				int subMenuId=permission4.getPermissionID();
				list.add(Integer.toString(subMenuId));//submenu
				Permission permission5=new Permission();
				permission5.setParentID(Integer.toString(subMenuId));
				List<Permission> pagelist=queryPermission(permission5);
				for (Permission permission6 : pagelist) {
					int pageId=permission6.getPermissionID();
					list.add(Integer.toString(pageId));//page
				}
			}
		}
		return list;
	}
}
