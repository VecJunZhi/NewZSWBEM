package com.zs.crm.service;

import java.util.List;

import com.zs.crm.entity.XsSaleDetailVo;
import com.zs.crm.entity.XsSaleManagerEntity;
import com.zs.crm.entity.XsSaleManagerShowEntity;
import com.zs.crm.entity.XsSignUpDetailVo;

public interface XsSaleManagerService {
	/**
	 * 
	 * @return
	 */
	public XsSaleManagerShowEntity getDayReportInfoDao();
	/**
	 * ��ȡ������Ϣ
	 * @param bean
	 * @return
	 */
	public List<XsSaleManagerEntity> getCstByCycleAndFollowWay(XsSaleManagerEntity bean);
	/**
	 * ��ȡ�Ϲ���Ϣ
	 * @param bean
	 * @return
	 */
	public List<XsSaleManagerEntity> getCstSubscribeInfoByCycle(XsSaleManagerEntity bean);
	/**
	 * ��ȡתǩԼ��Ϣ
	 * @param bean
	 * @return
	 */
	public List<XsSaleManagerEntity> getCstSignUpInfoByCycle(XsSaleManagerEntity bean);
	/**
	 * ��ȡ�����ÿͻ���
	 * @param bean
	 * @return
	 */
	public String getFirstVistCountByCycle(XsSaleManagerEntity bean);
	/**
	 * ��ȡ�Ϲ�����
	 * @param bean
	 * @return
	 */
	public XsSaleDetailVo getSaleDetailByCycle(XsSaleManagerEntity bean);
	/**
	 * ��ȡתǩԼ����
	 * @param bean
	 * @return
	 */
	public XsSignUpDetailVo getSignUpDetailByCycle(XsSaleManagerEntity bean);
}
