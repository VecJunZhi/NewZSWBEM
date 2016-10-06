package com.zs.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zs.common.entity.ZsReplyFeedEntity;
import com.zs.common.entity.ZsUpdateLogEntity;

public interface AllFeedBackService {
	/**
	 * �����б�
	 */
	public List<ZsUpdateLogEntity> findAllFeedBackDao(ZsUpdateLogEntity zsUpdateLogEntity);
	/**
	 * ��������
	 */
	public int postAllFeedBackDao(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * ɾ������
	 */
	public int delAllFeedBackDao(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * �����ظ�
	 */
	public int addReplyContentDao(ZsReplyFeedEntity zsReplyFeed);
	/**
	 * ��ѯ�ظ�
	 */
	public List<ZsReplyFeedEntity> queryReplyContentsDao(ZsReplyFeedEntity zsReplyFeedEntity);
	/**
	 * ��ѯδ��
	 */
	public List<ZsReplyFeedEntity> getUnReadReplyDao(ZsReplyFeedEntity zsReplyFeedEntity);
	/**
	 * �����Ķ�״̬
	 */
	public int changeUnReadTypeDao(ZsReplyFeedEntity zsReplyFeed);
	/**
	 * ɾ���ظ�
	 */
	public int delReplyFeedDao(ZsReplyFeedEntity zsReplyFeedEntity);
	
}
