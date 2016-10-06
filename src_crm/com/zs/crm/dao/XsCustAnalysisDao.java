package com.zs.crm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.crm.entity.XsCustAnalysisShowEntity;
import com.zs.crm.entity.XsSaleManagerEntity;
@Repository
public interface XsCustAnalysisDao {
	/**
	 * ���ݸ�����ʽ��ѯ�ͻ�����
	 * @return
	 */
	public XsCustAnalysisShowEntity getCstCountByFollowWayDao(XsSaleManagerEntity bean);
	/**
	 * �����ͻ������ѯ�ͻ�����
	 * @return
	 */
	public XsCustAnalysisShowEntity getCstCountByIntentionDao(XsSaleManagerEntity bean);
	/**
	 * ���ݿͻ�״̬��ѯ�ͻ�����
	 * @return
	 */
	public XsCustAnalysisShowEntity getCstCountByStatusDao(XsSaleManagerEntity bean);
	/**
	 * ����ʱ������Ͳ�ѯ�ͻ�����֪;��������Ρ�������ҵ�ķ�����Ϣ
	 * @param bean
	 * @return
	 */
	public List<XsSaleManagerEntity> getCstClassInfoByTypeAndDateDao(XsSaleManagerEntity bean);
	/**
	 * ����ʱ���ȡ���硢���õ�ʱ������ͳ����Ϣ
	 * @param bean
	 * @return
	 */
	public List<XsSaleManagerEntity> getCallAndVisitInfoByCycleDao(XsSaleManagerEntity bean);
}
