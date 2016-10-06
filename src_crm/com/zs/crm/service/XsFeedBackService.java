package com.zs.crm.service;

import java.util.List;

import com.zs.common.entity.ZsReplyFeedEntity;
import com.zs.common.entity.ZsUpdateLogEntity;

public interface XsFeedBackService {
	/**
	 * �����б�
	 */
	public List<ZsUpdateLogEntity> findXsFeedBackDao();
	/**
	 * ��������
	 */
	public int postXsFeedBackDao(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * �����ظ�
	 */
	public int addXsReplyContentDao(ZsReplyFeedEntity zsReplyFeedEntity);
	/**
	 * ��ѯ�ظ�
	 */
	public List<ZsReplyFeedEntity> findXsReplyFeedDao(ZsReplyFeedEntity zsReplyFeedEntity);
}
