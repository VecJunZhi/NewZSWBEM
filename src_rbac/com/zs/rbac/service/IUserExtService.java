package com.zs.rbac.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zs.rbac.entity.UserExt;

public interface IUserExtService {
	
	/**
	 * �����û�Id��ѯ�û���չ��Ϣ
	 * @param userID
	 * @return
	 */
	public List<UserExt> getUsetExtById(int userId);
	/**
	 * ��ѯ��չ��Ϣ
	 * @param userExt
	 * @return
	 */
	public List<UserExt> getUserExtInfo(UserExt userExt);
	/**
	 * ������չ��Ϣ
	 * @param userExt
	 * @return
	 */
	public int insertUserExtInfo(UserExt userExt);
	/**
	 * ������չ��Ϣ
	 * @param userExt
	 * @return
	 */
	public int updateUserExtInfo(UserExt userExt);
}
