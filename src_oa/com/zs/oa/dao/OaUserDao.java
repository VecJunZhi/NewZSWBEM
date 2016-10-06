package com.zs.oa.dao;
import java.util.Map;
import com.zs.oa.entity.OaUserEntity;
public interface OaUserDao {
	/**
	 * 通过姓名获得uid
	 * @param  userName oa用户姓名
	 * @return oa用户uid
	 */
	public String getUidByUserName(String userName);
	/**
	 * 获得oa用户所有信息(oa数据库user表中信息)
	 */
	public Map<String ,OaUserEntity > getAllUser();
}
