package com.zs.rbac.dao;

import java.util.List;

import com.zs.rbac.entity.Menu;
import com.zs.rbac.entity.SubSystem;


public interface ISysMenuDao {
	/**
	 * 根据UserId获取菜单信息
	 * @param userID
	 * @return
	 */
	public List<Menu> getMenuByUserID(int userID);
	
	/**
	 * 根据UserId获取子系统信息
	 * @param userID
	 * @return
	 */
	public List<SubSystem> getSubSystemByUserID(int userID);
}
