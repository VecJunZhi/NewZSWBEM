package com.zs.rbac.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zs.rbac.entity.UserExt;

public interface IUserExtService {
	
	/**
	 * 根据用户Id查询用户扩展信息
	 * @param userID
	 * @return
	 */
	public List<UserExt> getUsetExtById(int userId);
	/**
	 * 查询扩展信息
	 * @param userExt
	 * @return
	 */
	public List<UserExt> getUserExtInfo(UserExt userExt);
	/**
	 * 插入扩展信息
	 * @param userExt
	 * @return
	 */
	public int insertUserExtInfo(UserExt userExt);
	/**
	 * 更新扩展信息
	 * @param userExt
	 * @return
	 */
	public int updateUserExtInfo(UserExt userExt);
}
