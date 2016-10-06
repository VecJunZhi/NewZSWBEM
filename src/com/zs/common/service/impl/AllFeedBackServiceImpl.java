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
	 * �����б�
	 */
	@Override
	public List<ZsUpdateLogEntity> findAllFeedBackDao(ZsUpdateLogEntity zsUpdateLogEntity){
		List<ZsUpdateLogEntity> zsUpdateLogList = new ArrayList<ZsUpdateLogEntity>();
		zsUpdateLogList=allFeedBackDao.findAllFeedBack(zsUpdateLogEntity);
		return zsUpdateLogList;
	}
	/**
	 * ��������
	 */
	@Override
	public int postAllFeedBackDao(ZsUpdateLogEntity zsUpdateLog){
		return allFeedBackDao.postAllFeedBack(zsUpdateLog);
	}
	/**
	 * ɾ������
	 */
	@Override
	public int delAllFeedBackDao(ZsUpdateLogEntity zsUpdateLog){
		return allFeedBackDao.delAllFeedBack(zsUpdateLog);
		
	}
	/**
	 * �����ظ�
	 */
	@Override
	public int addReplyContentDao(ZsReplyFeedEntity zsReplyFeed){
		return allFeedBackDao.addReplyContent(zsReplyFeed);
		
	}
	/**
	 * ��ѯ�ظ�
	 */
	@Override
	public List<ZsReplyFeedEntity> queryReplyContentsDao(ZsReplyFeedEntity zsReplyFeedEntity){
		List<ZsReplyFeedEntity> zsReplyFeedEntityList = new ArrayList<ZsReplyFeedEntity>();
		zsReplyFeedEntityList = allFeedBackDao.queryReplyContents(zsReplyFeedEntity);
		return zsReplyFeedEntityList;
	}
	/**
	 * ��ѯδ��
	 */
	@Override
	public List<ZsReplyFeedEntity> getUnReadReplyDao(ZsReplyFeedEntity zsReplyFeedEntity){
		List<ZsReplyFeedEntity> zsReplyFeedEntityList = new ArrayList<ZsReplyFeedEntity>();
		zsReplyFeedEntityList=allFeedBackDao.getUnReadReply(zsReplyFeedEntity);
		return zsReplyFeedEntityList;
	}
	/**
	 * �����Ķ�״̬
	 */
	@Override
	public int changeUnReadTypeDao(ZsReplyFeedEntity zsReplyFeed){
		return allFeedBackDao.changeUnReadType(zsReplyFeed);
	}
	/**
	 * ɾ���ظ�
	 */
	@Override
	public int delReplyFeedDao(ZsReplyFeedEntity zsReplyFeedEntity){
			return allFeedBackDao.delReplyFeed(zsReplyFeedEntity);
	}
}









