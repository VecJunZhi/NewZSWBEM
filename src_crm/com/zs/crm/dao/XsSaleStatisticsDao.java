package com.zs.crm.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zs.crm.entity.XsBusinessUrgedInfoVo;
import com.zs.crm.entity.XsSaleStatisticsResultVo;
@Repository
public interface XsSaleStatisticsDao {
	/**
	 * ��ȡ����ͳ����Ϣ����������������δ������
	 * @param searchInfo
	 * @return
	 */
	public XsSaleStatisticsResultVo getRoomStatisticsInfoDao(Map<String,String> searchInfo);
	/**
	 * ��ȡδ������Ϣ��δ����ͻ�����Ƿ���
	 * @param searchInfo
	 * @return
	 */
	public XsSaleStatisticsResultVo getUnPaymentInfoDao(XsBusinessUrgedInfoVo searchInfo);
	/**
	 * ��ȡδǩԼ��Ϣ��δǩԼ��Ƿ��ͻ�����Ƿ���
	 * @param searchInfo
	 * @return
	 */
	public XsSaleStatisticsResultVo getUnSignUpInfoDao(XsBusinessUrgedInfoVo searchInfo);
	/**
	 * ��ȡδ�ſ���Ϣ��δ�ſ�ͻ�����Ƿ���
	 * @param searchInfo
	 * @return
	 */
	public XsSaleStatisticsResultVo getUnLendingInfoDao(XsBusinessUrgedInfoVo searchInfo);
}
