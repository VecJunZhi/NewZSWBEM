package com.zs.rbac.dao.factory;

import com.zs.rbac.dao.impl.RoleDaoImpl;
import com.zs.rbac.dao.impl.SysMenuDaoImpl;
import com.zs.rbac.dao.impl.UserDaoImpl;

public class DaoImplFactory {

	/*public static PermissionDaoImpl getPermissionDaoInstance() {
		return new PermissionDaoImpl();
	}*/

	public static RoleDaoImpl getRoleDaoInstance() {
		return new RoleDaoImpl();
	}

	public static UserDaoImpl getUserDaoInstance() {
		return new UserDaoImpl();
	}
	
	public static SysMenuDaoImpl getSysMenuInstance(){
		return new SysMenuDaoImpl();
	}
	
//	public static IOrganizationDao getOrganizationInstance(){
//		return new OrganizationDaoImpl();
//	}

}
