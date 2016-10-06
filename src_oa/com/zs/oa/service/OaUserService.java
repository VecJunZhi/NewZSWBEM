package com.zs.oa.service;
import java.util.Map;

import com.zs.oa.entity.OaUserEntity;
public interface OaUserService {
	/**
	 * 通过姓名获得uid
	 * @param  userName oa用户姓名
	 * @return oa用户uid
	 */
	public  String getUidByUserNameService(String userName);
	/**
	 * 获得oa用户所有信息(oa数据库user表中信息)
	 */
	public Map<String ,OaUserEntity > getAllUserService();
}	
