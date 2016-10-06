package com.zs.rbac.service;

import java.util.List;
import java.util.Map;

import com.zs.rbac.entity.Menu;
import com.zs.rbac.entity.SubSystem;

/**
 * 
 * @author JiaRui
 *
 */
public interface ISysMenuService {

	public Map<String, Map<String,List<Menu>>> getMenuByUserID(int userId);
	
	public List<SubSystem> getSubSystemID(int userID);

	public List<Menu> getAllMenuByUserID(int userID);
	
	
}
