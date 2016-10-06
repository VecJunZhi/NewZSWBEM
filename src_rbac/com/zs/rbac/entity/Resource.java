package com.zs.rbac.entity;

import java.util.List;
import java.util.Map;


/**
 * 权限相关集合类
 * @author jiarui
 */
public class Resource {
	
	private Map<String,List<Menu>> menu;
	
	private List<SubSystem> subSystem;
	
	private List<Permission> permission;

	/**
	 * 取得用户目录信息
	 * @return List<Menu>
	 */
	public Map<String, List<Menu>> getMenu() {
		return menu;
	}

	/**
	 * 得到用户子系统信息
	 * @return
	 */
	public List<SubSystem> getSubSystem() {
		return subSystem;
	}


	public void setMenu(Map<String, List<Menu>> menu) {
		this.menu = menu;
	}

	/**
	 * 设备用户子系统信息
	 * @param subSystem
	 */
	public void setSubSystem(List<SubSystem> subSystem) {
		this.subSystem = subSystem;
	}

	/**
	 * 得到用户操作权限信息
	 * @return 
	 */
	public List<Permission> getPermission() {
		return permission;
	}

	/**
	 * 设置用户操作权限信息
	 * @param permission
	 */
	public void setPermission(List<Permission> permission) {
		this.permission = permission;
	}
}
