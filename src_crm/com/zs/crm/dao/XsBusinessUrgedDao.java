package com.zs.crm.dao;

import java.util.List;

import com.zs.crm.entity.XsBusinessUrgedInfoVo;
import com.zs.crm.entity.tableStructure.XsFeeTableEntity;
import com.zs.crm.entity.tableStructure.XsTradeExtTableEntity;
import com.zs.crm.entity.tableStructure.XsTradeGjjlTableEntity;

public interface XsBusinessUrgedDao {
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnPaymentCstListDao(XsBusinessUrgedInfoVo bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnSignUpCstListDao(XsBusinessUrgedInfoVo bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnLendingCstListDao(XsBusinessUrgedInfoVo bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getToUrgedCstListDao(XsBusinessUrgedInfoVo bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getCstUrgedInfoDao(XsBusinessUrgedInfoVo bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public int insertCstUrgedInfoDao(XsTradeGjjlTableEntity bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public int insertOrUpdateDelayInfoDao(XsTradeExtTableEntity bean);
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public int insertOrUpdateUrgedStatusInfoDao(XsTradeExtTableEntity bean);
	/**
	 * 
	 * @param tradeGuid
	 * @return
	 */
	public List<XsFeeTableEntity> getFeeInfoByTradeGuidDao(String tradeGuid);
}
