package com.zs.oa.service;
import java.util.Map;

import com.zs.oa.entity.OaUserEntity;
public interface OaUserService {
	/**
	 * ͨ���������uid
	 * @param  userName oa�û�����
	 * @return oa�û�uid
	 */
	public  String getUidByUserNameService(String userName);
	/**
	 * ���oa�û�������Ϣ(oa���ݿ�user������Ϣ)
	 */
	public Map<String ,OaUserEntity > getAllUserService();
}	
