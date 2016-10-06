package com.zs.crm.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.common.entity.ZsReplyFeedEntity;
import com.zs.common.entity.ZsUpdateLogEntity;
@Repository
public interface XsFeedBackDao {
	/**
	 * 反馈列表
	 */
	public List<ZsUpdateLogEntity> findXsFeedBack();
	/**
	 * 发布反馈
	 */
	public int postXsFeedBack(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * 新增回复
	 */
	public int addXsReplyContent(ZsReplyFeedEntity zsReplyFeedEntity);
	/**
	 * 查询回复
	 */
	public List<ZsReplyFeedEntity> findXsReplyFeed(ZsReplyFeedEntity zsReplyFeedEntity);
}
