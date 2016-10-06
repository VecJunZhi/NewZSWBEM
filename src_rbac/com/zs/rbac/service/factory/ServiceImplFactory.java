package com.zs.rbac.service.factory;

import com.zs.rbac.service.impl.PermissionServiceImpl;
import com.zs.rbac.service.impl.RoleServiceImpl;
import com.zs.rbac.service.impl.SysMenuServiceImpl;
import com.zs.rbac.service.impl.UserServiceImpl;

/**
 * <p>User: zj
 * <p>Date: 15-9-5
 * <p>Version: 1.0
 */
public class ServiceImplFactory {
	
	public static PermissionServiceImpl  getPermissionServiceInstance(){
		
		return new PermissionServiceImpl();
		
	}
	public static RoleServiceImpl  getRoleServiceInstance(){
		
		return new RoleServiceImpl();
		
	}
	public static UserServiceImpl  getUserServiceInstance(){
	
		return new UserServiceImpl();
	
	}
	
	public static SysMenuServiceImpl getSysMenuServiceInstance(){
		return new SysMenuServiceImpl();
	}

}
