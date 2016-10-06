package com.zs.common.service;
import java.util.List;
import java.util.Map;
import com.zs.common.entity.ZsUpdateLogEntity;
import com.zs.rbac.entity.User;
public interface ZsUpdateLogService {
	/**
	 * �����Ķ�״̬
	 */
	public int editLogTypeDao();
	/**
	 * ����δ����־
	 */
	public List<ZsUpdateLogEntity> getReadTypeDao(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * �����û�
	 */
	public List<User> getUserNameDao();
	/**
	 * ������־
	 */
	public List<ZsUpdateLogEntity> getZsUpdateLogDao(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * ������־
	 */
	public int insertUpdateLogDao(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * �޸���־
	 */
	public int editUpdateLogDao(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * ɾ����־
	 */
	public int delLogDao(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * �û���ѯ��־�б�
	 */
	public List<ZsUpdateLogEntity> showLogListDao();
}
