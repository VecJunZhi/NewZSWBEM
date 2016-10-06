package com.zs.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.common.entity.ZsReplyFeedEntity;
import com.zs.common.entity.ZsUpdateLogEntity;

@Repository
public interface AllFeedBackDao {
	/**
	 * �����б�
	 */
	public List<ZsUpdateLogEntity> findAllFeedBack(ZsUpdateLogEntity zsUpdateLogEntity);
	/**
	 * ��������
	 */
	public int postAllFeedBack(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * ɾ������
	 */
	public int delAllFeedBack(ZsUpdateLogEntity zsUpdateLog);
	/**
	 * �����ظ�
	 */
	public int addReplyContent(ZsReplyFeedEntity zsReplyFeed);
	/**
	 * ��ѯ�ظ�
	 */
	public List<ZsReplyFeedEntity> queryReplyContents(ZsReplyFeedEntity zsReplyFeedEntity);
	/**
	 * ��ѯδ��
	 */
	public List<ZsReplyFeedEntity> getUnReadReply(ZsReplyFeedEntity zsReplyFeedEntity);
	/**
	 * �����Ķ�״̬
	 */
	public int changeUnReadType(ZsReplyFeedEntity zsReplyFeed);
	/**
	 * ɾ���ظ�
	 */
	public int delReplyFeed(ZsReplyFeedEntity zsReplyFeedEntity);
	
	
}
