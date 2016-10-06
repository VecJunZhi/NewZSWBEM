package com.zs.common.dao;
import java.util.List;

import com.zs.common.entity.ZsUpdateLogEntity;


import com.zs.rbac.entity.User;

import org.springframework.stereotype.Repository;
@Repository
public interface ZsUpdateLogDao {
	/**
	 * �����Ķ�״̬
	 */
	public int editLogType();
	/**
	 * ����δ����־
	 */
	public List<ZsUpdateLogEntity> getReadType(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * �����û�
	 */
	public List<User> getUserName();
	/**
	 * ������־
	 */
	public List<ZsUpdateLogEntity> getZsUpdateLog(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * ������־
	 */
	public int insertUpdateLog(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * �޸���־
	 */
	public int editUpdateLog(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * ɾ����־
	 */
	public int delLog(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * �û���ѯ��־�б�
	 */
	public List<ZsUpdateLogEntity> showLogList();
}
