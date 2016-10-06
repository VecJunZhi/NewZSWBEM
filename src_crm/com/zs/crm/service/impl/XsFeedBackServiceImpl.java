package com.zs.crm.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.common.entity.ZsReplyFeedEntity;
import com.zs.common.entity.ZsUpdateLogEntity;
import com.zs.crm.dao.XsFeedBackDao;
import com.zs.crm.service.XsFeedBackService;
@Service
public class XsFeedBackServiceImpl implements XsFeedBackService {
	@Autowired
	XsFeedBackDao zsFeedBackDao;
	/**
	 * �����б�
	 */
	public List<ZsUpdateLogEntity> findXsFeedBackDao(){
		List<ZsUpdateLogEntity> zsUpdateLogEntity = new ArrayList<ZsUpdateLogEntity>();
		zsUpdateLogEntity=zsFeedBackDao.findXsFeedBack();
		return  zsUpdateLogEntity;
	}
	/**
	 * ��������
	 */
	@Override
	public int postXsFeedBackDao(ZsUpdateLogEntity zsUpdateLog){
		return zsFeedBackDao.postXsFeedBack(zsUpdateLog);
	}
	/**
	 * �����ظ�
	 */
	@Override
	public int addXsReplyContentDao(ZsReplyFeedEntity zsReplyFeedEntity){
		return zsFeedBackDao.addXsReplyContent(zsReplyFeedEntity);
	}
	/**
	 * ��ѯ�ظ�
	 */
	@Override
	public List<ZsReplyFeedEntity> findXsReplyFeedDao(ZsReplyFeedEntity zsReplyFeedEntity){
		List<ZsReplyFeedEntity> zsReplyFeedEntityList = new ArrayList<ZsReplyFeedEntity>();
		zsReplyFeedEntityList=zsFeedBackDao.findXsReplyFeed(zsReplyFeedEntity);
		return zsReplyFeedEntityList;
		
	}
}
