package com.zs.crm.service;

import com.zs.crm.entity.XsSaleStatisticsResultVo;

public interface XsSaleStatisticsService {
	/**
	 * 
	 * @param projGuid
	 * @param roomType
	 * @return
	 */
	public XsSaleStatisticsResultVo getRoomStatisticsInfo(String projGuid,String roomType);
	/**
	 * 
	 * @param projGuid
	 * @return
	 */
	public XsSaleStatisticsResultVo getUnPaymentInfo(String projGuid);
	/**
	 * 
	 * @param projGuid
	 * @return
	 */
	public XsSaleStatisticsResultVo getUnSignUpInfo(String projGuid);
	/**
	 * 
	 * @param projGuid
	 * @param feeType
	 * @return
	 */
	public XsSaleStatisticsResultVo getUnLendingInfo(String projGuid,String feeType);
}
