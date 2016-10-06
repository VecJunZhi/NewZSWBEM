package com.zs.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zs.common.entity.ZsReplyFeedEntity;
import com.zs.common.entity.ZsUpdateLogEntity;

public interface AllFeedBackService {
	/**
	 * 反馈列表
	 */
	public List<ZsUpdateLogEntity> findAllFeedBackDao(ZsUpdateLogEntity zsUpdateLogEntity);
	/**
	 * 发布反馈
	 */
	public int postAllFeedBackDao(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * 删除反馈
	 */
	public int delAllFeedBackDao(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * 新增回复
	 */
	public int addReplyContentDao(ZsReplyFeedEntity zsReplyFeed);
	/**
	 * 查询回复
	 */
	public List<ZsReplyFeedEntity> queryReplyContentsDao(ZsReplyFeedEntity zsReplyFeedEntity);
	/**
	 * 查询未读
	 */
	public List<ZsReplyFeedEntity> getUnReadReplyDao(ZsReplyFeedEntity zsReplyFeedEntity);
	/**
	 * 更改阅读状态
	 */
	public int changeUnReadTypeDao(ZsReplyFeedEntity zsReplyFeed);
	/**
	 * 删除回复
	 */
	public int delReplyFeedDao(ZsReplyFeedEntity zsReplyFeedEntity);
	
}
