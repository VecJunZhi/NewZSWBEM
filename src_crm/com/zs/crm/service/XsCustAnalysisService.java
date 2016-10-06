package com.zs.crm.service;

import java.util.List;

import com.zs.crm.entity.XsCustAnalysisShowEntity;
import com.zs.crm.entity.XsSaleManagerEntity;

public interface XsCustAnalysisService {
	/**
	 * ���ݸ�����ʽ��ѯ�ͻ�����
	 * @return
	 */
	public XsCustAnalysisShowEntity getCstCountByFollowWay(XsSaleManagerEntity bean);
	/**
	 * �����ͻ������ѯ�ͻ�����
	 * @return
	 */
	public XsCustAnalysisShowEntity getCstCountByIntention(XsSaleManagerEntity bean);
	/**
	 * ���ݿͻ�״̬��ѯ�ͻ�����
	 * @return
	 */
	public XsCustAnalysisShowEntity getCstCountByStatus(XsSaleManagerEntity bean);
	/**
	 * ����ʱ������Ͳ�ѯ�ͻ�����֪;��������Ρ�������ҵ�ķ�����Ϣ
	 * @param bean
	 * @return
	 */
	public List<XsSaleManagerEntity> getCstClassInfoByTypeAndDate(XsSaleManagerEntity bean);
	/**
	 * ����ʱ��λ�ȡʱ����������硢����ͳ����Ϣ
	 * @param bean
	 * @return
	 */
	public List<XsSaleManagerEntity> getCallAndVisitInfoByCycle(XsSaleManagerEntity bean);
}
