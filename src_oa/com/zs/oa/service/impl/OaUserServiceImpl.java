package com.zs.oa.service.impl;
import java.util.Map;

import com.zs.oa.dao.OaUserDao;
import com.zs.oa.dao.impl.OaUserDaoImpl;
import com.zs.oa.entity.OaUserEntity;
import com.zs.oa.service.OaUserService;
public class OaUserServiceImpl implements OaUserService{
	/**
	 * 通过姓名获得uid
	 * @param  userName oa用户姓名
	 * @return oa用户uid
	 */
	public String getUidByUserNameService(String userName) {
		OaUserDao oaUserDao = new OaUserDaoImpl();
		String uid = oaUserDao.getUidByUserName(userName);
		return uid;
	}
	/**
	 * 获得oa用户所有信息(oa数据库user表中信息)
	 */
	public Map<String ,OaUserEntity > getAllUserService(){
		OaUserDao oaUserDao = new OaUserDaoImpl();
		Map<String, OaUserEntity> map = oaUserDao.getAllUser();
		return map;
	}
/*	public static void main(String[] args){
		OaUserService oaUserService = new OaUserServiceImpl();
	  Map<String, OaUserEntity> map = oaUserService.getAllUserService();
	  System.out.println(map.get("姬小航").uid);
	}*/
}
