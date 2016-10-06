package com.zs.rbac.dao;

import java.util.List;

import com.zs.rbac.entity.Menu;
import com.zs.rbac.entity.SubSystem;


public interface ISysMenuDao {
	/**
	 * ����UserId��ȡ�˵���Ϣ
	 * @param userID
	 * @return
	 */
	public List<Menu> getMenuByUserID(int userID);
	
	/**
	 * ����UserId��ȡ��ϵͳ��Ϣ
	 * @param userID
	 * @return
	 */
	public List<SubSystem> getSubSystemByUserID(int userID);
}
