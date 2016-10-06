package com.zs.rbac.entity;

import java.util.List;
import java.util.Map;


/**
 * Ȩ����ؼ�����
 * @author jiarui
 */
public class Resource {
	
	private Map<String,List<Menu>> menu;
	
	private List<SubSystem> subSystem;
	
	private List<Permission> permission;

	/**
	 * ȡ���û�Ŀ¼��Ϣ
	 * @return List<Menu>
	 */
	public Map<String, List<Menu>> getMenu() {
		return menu;
	}

	/**
	 * �õ��û���ϵͳ��Ϣ
	 * @return
	 */
	public List<SubSystem> getSubSystem() {
		return subSystem;
	}


	public void setMenu(Map<String, List<Menu>> menu) {
		this.menu = menu;
	}

	/**
	 * �豸�û���ϵͳ��Ϣ
	 * @param subSystem
	 */
	public void setSubSystem(List<SubSystem> subSystem) {
		this.subSystem = subSystem;
	}

	/**
	 * �õ��û�����Ȩ����Ϣ
	 * @return 
	 */
	public List<Permission> getPermission() {
		return permission;
	}

	/**
	 * �����û�����Ȩ����Ϣ
	 * @param permission
	 */
	public void setPermission(List<Permission> permission) {
		this.permission = permission;
	}
}
