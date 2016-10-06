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
	 * 获取跟进信息
	 * @param bean
	 * @return
	 */
	public List<XsSaleManagerEntity> getCstByCycleAndFollowWay(XsSaleManagerEntity bean);
	/**
	 * 获取认购信息
	 * @param bean
	 * @return
	 */
	public List<XsSaleManagerEntity> getCstSubscribeInfoByCycle(XsSaleManagerEntity bean);
	/**
	 * 获取转签约信息
	 * @param bean
	 * @return
	 */
	public List<XsSaleManagerEntity> getCstSignUpInfoByCycle(XsSaleManagerEntity bean);
	/**
	 * 获取新来访客户数
	 * @param bean
	 * @return
	 */
	public String getFirstVistCountByCycle(XsSaleManagerEntity bean);
	/**
	 * 获取认购详情
	 * @param bean
	 * @return
	 */
	public XsSaleDetailVo getSaleDetailByCycle(XsSaleManagerEntity bean);
	/**
	 * 获取转签约详情
	 * @param bean
	 * @return
	 */
	public XsSignUpDetailVo getSignUpDetailByCycle(XsSaleManagerEntity bean);
}
