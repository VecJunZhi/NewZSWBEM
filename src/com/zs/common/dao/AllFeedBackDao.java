package com.zs.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.common.entity.ZsReplyFeedEntity;
import com.zs.common.entity.ZsUpdateLogEntity;

@Repository
public interface AllFeedBackDao {
	/**
	 * 反馈列表
	 */
	public List<ZsUpdateLogEntity> findAllFeedBack(ZsUpdateLogEntity zsUpdateLogEntity);
	/**
	 * 发布反馈
	 */
	public int postAllFeedBack(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * 删除反馈
	 */
	public int delAllFeedBack(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * 新增回复
	 */
	public int addReplyContent(ZsReplyFeedEntity zsReplyFeed);
	/**
	 * 查询回复
	 */
	public List<ZsReplyFeedEntity> queryReplyContents(ZsReplyFeedEntity zsReplyFeedEntity);
	/**
	 * 查询未读
	 */
	public List<ZsReplyFeedEntity> getUnReadReply(ZsReplyFeedEntity zsReplyFeedEntity);
	/**
	 * 更改阅读状态
	 */
	public int changeUnReadType(ZsReplyFeedEntity zsReplyFeed);
	/**
	 * 删除回复
	 */
	public int delReplyFeed(ZsReplyFeedEntity zsReplyFeedEntity);
	
	
}
