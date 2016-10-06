package com.zs.common.dao;
import java.util.List;

import com.zs.common.entity.ZsUpdateLogEntity;


import com.zs.rbac.entity.User;

import org.springframework.stereotype.Repository;
@Repository
public interface ZsUpdateLogDao {
	/**
	 * 更改阅读状态
	 */
	public int editLogType();
	/**
	 * 查找未读日志
	 */
	public List<ZsUpdateLogEntity> getReadType(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * 查找用户
	 */
	public List<User> getUserName();
	/**
	 * 查找日志
	 */
	public List<ZsUpdateLogEntity> getZsUpdateLog(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * 增加日志
	 */
	public int insertUpdateLog(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * 修改日志
	 */
	public int editUpdateLog(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * 删除日志
	 */
	public int delLog(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * 用户查询日志列表
	 */
	public List<ZsUpdateLogEntity> showLogList();
}
