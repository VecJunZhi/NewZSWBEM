package com.zs.oa.dao;
import java.util.Map;
import com.zs.oa.entity.OaUserEntity;
public interface OaUserDao {
	/**
	 * ͨ���������uid
	 * @param  userName oa�û�����
	 * @return oa�û�uid
	 */
	public String getUidByUserName(String userName);
	/**
	 * ���oa�û�������Ϣ(oa���ݿ�user������Ϣ)
	 */
	public Map<String ,OaUserEntity > getAllUser();
}
