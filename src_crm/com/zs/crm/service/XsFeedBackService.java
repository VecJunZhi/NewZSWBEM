package com.zs.crm.service;

import java.util.List;

import com.zs.common.entity.ZsReplyFeedEntity;
import com.zs.common.entity.ZsUpdateLogEntity;

public interface XsFeedBackService {
	/**
	 * 反馈列表
	 */
	public List<ZsUpdateLogEntity> findXsFeedBackDao();
	/**
	 * 发布反馈
	 */
	public int postXsFeedBackDao(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * 新增回复
	 */
	public int addXsReplyContentDao(ZsReplyFeedEntity zsReplyFeedEntity);
	/**
	 * 查询回复
	 */
	public List<ZsReplyFeedEntity> findXsReplyFeedDao(ZsReplyFeedEntity zsReplyFeedEntity);
}
