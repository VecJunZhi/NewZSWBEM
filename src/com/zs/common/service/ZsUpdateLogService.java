package com.zs.common.service;
import java.util.List;
import java.util.Map;
import com.zs.common.entity.ZsUpdateLogEntity;
import com.zs.rbac.entity.User;
public interface ZsUpdateLogService {
	/**
	 * 更改阅读状态
	 */
	public int editLogTypeDao();
	/**
	 * 查找未读日志
	 */
	public List<ZsUpdateLogEntity> getReadTypeDao(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * 查找用户
	 */
	public List<User> getUserNameDao();
	/**
	 * 查找日志
	 */
	public List<ZsUpdateLogEntity> getZsUpdateLogDao(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * 增加日志
	 */
	public int insertUpdateLogDao(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * 修改日志
	 */
	public int editUpdateLogDao(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * 删除日志
	 */
	public int delLogDao(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * 用户查询日志列表
	 */
	public List<ZsUpdateLogEntity> showLogListDao();
}
