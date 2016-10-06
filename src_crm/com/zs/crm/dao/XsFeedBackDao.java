package com.zs.crm.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.common.entity.ZsReplyFeedEntity;
import com.zs.common.entity.ZsUpdateLogEntity;
@Repository
public interface XsFeedBackDao {
	/**
	 * �����б�
	 */
	public List<ZsUpdateLogEntity> findXsFeedBack();
	/**
	 * ��������
	 */
	public int postXsFeedBack(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * �����ظ�
	 */
	public int addXsReplyContent(ZsReplyFeedEntity zsReplyFeedEntity);
	/**
	 * ��ѯ�ظ�
	 */
	public List<ZsReplyFeedEntity> findXsReplyFeed(ZsReplyFeedEntity zsReplyFeedEntity);
}
