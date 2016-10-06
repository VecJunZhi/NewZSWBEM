package com.zs.crm.service;

import java.util.List;

import com.zs.crm.entity.XsBusinessUrgedInfoVo;
import com.zs.crm.entity.tableStructure.XsFeeTableEntity;
import com.zs.crm.entity.tableStructure.XsTradeExtTableEntity;
import com.zs.crm.entity.tableStructure.XsTradeGjjlTableEntity;

public interface XsBusinessUrgedService {
	/**
	 * ���ݶ���������ѯδ����Ŀͻ�
	 * @param bean
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnPaymentCstList(XsBusinessUrgedInfoVo bean);
	/**
	 * ��ѯ����Ŀ����������δ����Ŀͻ�(���ڹ���Ա��ȡ�ͻ�����)
	 * @param projGuid
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnPaymentCstList(String projGuid);
	/**
	 * ��ѯ����Ŀ��ĳһ��ҵ������������δ����Ŀͻ���������ҵ���ʻ�ȡ�ͻ�������
	 * @param projGuid
	 * @param userGuid
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnPaymentCstList(String projGuid,String userGuid);
	/**
	 * ��ѯ����Ŀ����������δ����Ŀͻ��е�m��n�������ڹ���Ա��ҳ�鿴��
	 * @param projGuid
	 * @param startIndex
	 * @param length
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnPaymentCstList(String projGuid,int startIndex,int length);
	/**
	 * ��ѯ����Ŀ��ĳһ��ҵ������������δ����Ŀͻ��е�m��n����������ҵ���ʷ�ҳ�鿴��
	 * @param projGuid
	 * @param userGuid
	 * @param startIndex
	 * @param length
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnPaymentCstList(String projGuid,String userGuid,int startIndex,int length);
	/**
	 * ��ѯ����Ŀ����������δ����Ŀͻ��е�m��n�����Ұ�sortName��sortDir�������ڹ���Ա��ҳ�鿴��
	 * @param projGuid
	 * @param startIndex
	 * @param length
	 * @param sortName
	 * @param sortDir
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnPaymentCstList(String projGuid,int startIndex,int length,String sortName,String sortDir);
	/**
	 * ��ѯ����Ŀ��ĳһ��ҵ������������δ����Ŀͻ��е�m��n�����Ұ�sortName��sortDir����������ҵ���ʷ�ҳ�鿴��
	 * @param projGuid
	 * @param userGuid
	 * @param startIndex
	 * @param length
	 * @param sortName
	 * @param sortDir
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnPaymentCstList(String projGuid,String userGuid,int startIndex,int length,String sortName,String sortDir);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnSignUpCstList(XsBusinessUrgedInfoVo bean);
	/**
	 * ��ѯ����Ŀ������δǩԼ�Ŀͻ�(���ڹ���Ա��ȡ�ͻ�����)
	 * @param projGuid
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnSignUpCstList(String projGuid);
	/**
	 * ��ѯ����Ŀ��ĳһ��ҵ��������δǩԼ�Ŀͻ���������ҵ���ʻ�ȡ�ͻ�������
	 * @param projGuid
	 * @param userGuid
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnSignUpCstList(String projGuid,String userGuid);
	/**
	 * ��ѯ����Ŀ������δǩԼ�Ŀͻ��е�m��n�������ڹ���Ա��ҳ�鿴��
	 * @param projGuid
	 * @param startIndex
	 * @param length
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnSignUpCstList(String projGuid,int startIndex,int length);
	/**
	 * ��ѯ����Ŀ��ĳһ��ҵ��������δǩԼ�Ŀͻ��е�m��n����������ҵ���ʷ�ҳ�鿴��
	 * @param projGuid
	 * @param userGuid
	 * @param startIndex
	 * @param length
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnSignUpCstList(String projGuid,String userGuid,int startIndex,int length);
	/**
	 * ��ѯ����Ŀ��ĳһ��ҵ��������δǩԼ�Ŀͻ��е�m��n�����Ұ�sortName��sortDir����������ҵ���ʷ�ҳ�鿴��
	 * @param projGuid
	 * @param startIndex
	 * @param length
	 * @param sortName
	 * @param sortDir
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnSignUpCstList(String projGuid,int startIndex,int length,String sortName,String sortDir);
	/**
	 * ��ѯ����Ŀ��ĳһ��ҵ��������δǩԼ�Ŀͻ��е�m��n�����Ұ�sortName��sortDir����������ҵ���ʷ�ҳ�鿴��
	 * @param projGuid
	 * @param userGuid
	 * @param startIndex
	 * @param length
	 * @param sortName
	 * @param sortDir
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnSignUpCstList(String projGuid,String userGuid,int startIndex,int length,String sortName,String sortDir);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnLendingCstList(XsBusinessUrgedInfoVo bean);
	/**
	 * ��ѯ����Ŀ������δ�ſ�Ŀͻ�(���ڹ���Ա��ȡ�ͻ�����)
	 * @param projGuid
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnLendingCstList(String projGuid);
	/**
	 * ��ѯ����Ŀ��ĳһ��ҵ��������δ�ſ�Ŀͻ���������ҵ���ʻ�ȡ�ͻ�������
	 * @param projGuid
	 * @param userGuid
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnLendingCstList(String projGuid,String userGuid);
	/**
	 * ��ѯ����Ŀ��ĳһ��ҵ��������δ�ſ�Ŀͻ��е�m��n����������ҵ���ʷ�ҳ�鿴��
	 * @param projGuid
	 * @param startIndex
	 * @param length
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnLendingCstList(String projGuid,int startIndex,int length);
	/**
	 * ��ѯ����Ŀ��ĳһ��ҵ��������δ�ſ�Ŀͻ��е�m��n����������ҵ���ʷ�ҳ�鿴��
	 * @param projGuid
	 * @param userGuid
	 * @param startIndex
	 * @param length
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnLendingCstList(String projGuid,String userGuid,int startIndex,int length);
	/**
	 * ��ѯ����Ŀ��ĳһ��ҵ��������δ�ſ�Ŀͻ��е�m��n�����Ұ�sortName��sortDir����������ҵ���ʷ�ҳ�鿴��
	 * @param projGuid
	 * @param startIndex
	 * @param length
	 * @param sortName
	 * @param sortDir
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnLendingCstList(String projGuid,int startIndex,int length,String sortName,String sortDir);
	/**
	 * ��ѯ����Ŀ��ĳһ��ҵ��������δ�ſ�Ŀͻ��е�m��n�����Ұ�sortName��sortDir����������ҵ���ʷ�ҳ�鿴��
	 * @param projGuid
	 * @param userGuid
	 * @param startIndex
	 * @param length
	 * @param sortName
	 * @param sortDir
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnLendingCstList(String projGuid,String userGuid,int startIndex,int length,String sortName,String sortDir);
	/**
	 * ���ݶ���������ѯ���մ��߰�Ŀͻ�
	 * @param bean
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getToUrgedCstList(XsBusinessUrgedInfoVo bean);
	/**
	 * ��ѯ����Ŀ�����н��մ��߰�Ŀͻ�(���ڹ���Ա��ȡ�ͻ�����)
	 * @param projGuid
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getToUrgedCstList(String projGuid);
	/**
	 * ��ѯ����Ŀ��ĳһ��ҵ�������н��մ��߰�Ŀͻ���������ҵ���ʻ�ȡ�ͻ�������
	 * @param projGuid
	 * @param userGuid
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getToUrgedCstList(String projGuid,String userGuid);
	/**
	 * ��ѯ����Ŀ�����н��մ��߰�Ŀͻ��е�m��n�������ڹ���Ա��ҳ�鿴��
	 * @param projGuid
	 * @param startIndex
	 * @param length
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getToUrgedCstList(String projGuid,int startIndex,int length);
	/**
	 * ��ѯ����Ŀ��ĳһ��ҵ�������н��մ��߰�Ŀͻ��е�m��n����������ҵ���ʷ�ҳ�鿴��
	 * @param projGuid
	 * @param userGuid
	 * @param startIndex
	 * @param length
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getToUrgedCstList(String projGuid,String userGuid,int startIndex,int length);
	/**
	 * ��ѯ����Ŀ�����н��մ��߰�Ŀͻ��е�m��n�����Ұ�sortName��sortDir�������ڹ���Ա��ҳ�鿴��
	 * @param projGuid
	 * @param startIndex
	 * @param length
	 * @param sortName
	 * @param sortDir
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getToUrgedCstList(String projGuid,int startIndex,int length,String sortName,String sortDir);
	/**
	 * ��ѯ����Ŀ��ĳһ��ҵ�������н��մ��߰�Ŀͻ��е�m��n�����Ұ�sortName��sortDir����������ҵ���ʷ�ҳ�鿴��
	 * @param projGuid
	 * @param userGuid
	 * @param startIndex
	 * @param length
	 * @param sortName
	 * @param sortDir
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getToUrgedCstList(String projGuid,String userGuid,int startIndex,int length,String sortName,String sortDir);
	
	/**
	 * ��ѯδ����ͻ�������Ϣ
	 * @param projGuid
	 * @param userGuid
	 * @param cstGuid
	 * @return
	 */
	public XsBusinessUrgedInfoVo getUnPaymentTradeInfo(String projGuid,String tradeGuid);
	/**
	 * ��ѯδǩԼ�ͻ�������Ϣ
	 * @param projGuid
	 * @param userGuid
	 * @param cstGuid
	 * @return
	 */
	public XsBusinessUrgedInfoVo getUnSignUpTradeInfo(String projGuid,String tradeGuid);
	/**
	 * ��ѯδ�ſ�ͻ�������Ϣ
	 * @param projGuid
	 * @param userGuid
	 * @param cstGuid
	 * @return
	 */
	public XsBusinessUrgedInfoVo getUnLendingTradeInfo(String projGuid,String tradeGuid);
	/**
	 * ��ѯ�ͻ������д߰��¼
	 * @param projGuid
	 * @param tradeGuid
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getCstUrgedInfo(String projGuid,String tradeGuid,String urgedStage);
	/**
	 * 
	 * @param projGuid
	 * @param tradeGuid
	 * @param type
	 * @param sortName
	 * @param sortDir
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getCstUrgedInfo(String projGuid,String tradeGuid,String type,String sortName,String sortDir,String urgedStage);
	/**
	 * 
	 * @param projGuid
	 * @param tradeGuid
	 * @param sortName
	 * @param sortDir
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getCstUrgedInfo(String projGuid,String tradeGuid,String sortName,String sortDir,String urgedStage);
	/**
	 * 
	 * @param projGuid
	 * @param tradeGuid
	 * @param startIndex
	 * @param length
	 * @param sortName
	 * @param sortDir
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getCstUrgedInfo(String projGuid,String tradeGuid,int startIndex,int length,String sortName,String sortDir,String urgedStage);
	/**
	 * �����߰���Ϣ
	 * @param urgedInfo
	 * @return
	 */
	public int insertCstUrgedInfo(XsTradeGjjlTableEntity urgedInfo);
	/**
	 * ���������������Ϣ
	 * @param tradeGuid
	 * @param postponeDate
	 * @param postponeReason
	 * @return
	 */
	public int insertOrUpdateDelayInfo(String tradeGuid,String postponeDate,String postponeReason,String postponeStage);
	/**
	 * ��������´߰�״̬��Ϣ
	 * @param tradeGuid
	 * @param level
	 * @param type
	 * @return
	 */
	public int insertOrUpdateUrgedStatusInfo(String tradeGuid,String level,String type);
	/**
	 * ����tradeGuid��ȡ�ɷ�����
	 * @param tradeGuid
	 * @return
	 */
	public List<XsFeeTableEntity> getFeeInfoByTradeGuid(String tradeGuid);
	
}
