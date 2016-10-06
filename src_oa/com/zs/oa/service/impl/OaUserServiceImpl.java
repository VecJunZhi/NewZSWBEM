package com.zs.oa.service.impl;
import java.util.Map;

import com.zs.oa.dao.OaUserDao;
import com.zs.oa.dao.impl.OaUserDaoImpl;
import com.zs.oa.entity.OaUserEntity;
import com.zs.oa.service.OaUserService;
public class OaUserServiceImpl implements OaUserService{
	/**
	 * ͨ���������uid
	 * @param  userName oa�û�����
	 * @return oa�û�uid
	 */
	public String getUidByUserNameService(String userName) {
		OaUserDao oaUserDao = new OaUserDaoImpl();
		String uid = oaUserDao.getUidByUserName(userName);
		return uid;
	}
	/**
	 * ���oa�û�������Ϣ(oa���ݿ�user������Ϣ)
	 */
	public Map<String ,OaUserEntity > getAllUserService(){
		OaUserDao oaUserDao = new OaUserDaoImpl();
		Map<String, OaUserEntity> map = oaUserDao.getAllUser();
		return map;
	}
/*	public static void main(String[] args){
		OaUserService oaUserService = new OaUserServiceImpl();
	  Map<String, OaUserEntity> map = oaUserService.getAllUserService();
	  System.out.println(map.get("��С��").uid);
	}*/
}
