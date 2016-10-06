package com.zs.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.common.dao.AllFeedBackDao;
import com.zs.common.entity.ZsReplyFeedEntity;
import com.zs.common.entity.ZsUpdateLogEntity;
import com.zs.common.service.AllFeedBackService;

@Service
public class AllFeedBackServiceImpl implements AllFeedBackService{
	@Autowired
	AllFeedBackDao allFeedBackDao;
	/**
	 * 反馈列表
	 */
	@Override
	public List<ZsUpdateLogEntity> findAllFeedBackDao(ZsUpdateLogEntity zsUpdateLogEntity){
		List<ZsUpdateLogEntity> zsUpdateLogList = new ArrayList<ZsUpdateLogEntity>();
		zsUpdateLogList=allFeedBackDao.findAllFeedBack(zsUpdateLogEntity);
		return zsUpdateLogList;
	}
	/**
	 * 发布反馈
	 */
	@Override
	public int postAllFeedBackDao(ZsUpdateLogEntity zsUpdateLog){
		return allFeedBackDao.postAllFeedBack(zsUpdateLog);
	}
	/**
	 * 删除反馈
	 */
	@Override
	public int delAllFeedBackDao(ZsUpdateLogEntity zsUpdateLog){
		return allFeedBackDao.delAllFeedBack(zsUpdateLog);
		
	}
	/**
	 * 新增回复
	 */
	@Override
	public int addReplyContentDao(ZsReplyFeedEntity zsReplyFeed){
		return allFeedBackDao.addReplyContent(zsReplyFeed);
		
	}
	/**
	 * 查询回复
	 */
	@Override
	public List<ZsReplyFeedEntity> queryReplyContentsDao(ZsReplyFeedEntity zsReplyFeedEntity){
		List<ZsReplyFeedEntity> zsReplyFeedEntityList = new ArrayList<ZsReplyFeedEntity>();
		zsReplyFeedEntityList = allFeedBackDao.queryReplyContents(zsReplyFeedEntity);
		return zsReplyFeedEntityList;
	}
	/**
	 * 查询未读
	 */
	@Override
	public List<ZsReplyFeedEntity> getUnReadReplyDao(ZsReplyFeedEntity zsReplyFeedEntity){
		List<ZsReplyFeedEntity> zsReplyFeedEntityList = new ArrayList<ZsReplyFeedEntity>();
		zsReplyFeedEntityList=allFeedBackDao.getUnReadReply(zsReplyFeedEntity);
		return zsReplyFeedEntityList;
	}
	/**
	 * 更改阅读状态
	 */
	@Override
	public int changeUnReadTypeDao(ZsReplyFeedEntity zsReplyFeed){
		return allFeedBackDao.changeUnReadType(zsReplyFeed);
	}
	/**
	 * 删除回复
	 */
	@Override
	public int delReplyFeedDao(ZsReplyFeedEntity zsReplyFeedEntity){
			return allFeedBackDao.delReplyFeed(zsReplyFeedEntity);
	}
}









