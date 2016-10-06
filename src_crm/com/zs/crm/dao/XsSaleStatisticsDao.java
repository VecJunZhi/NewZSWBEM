package com.zs.crm.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zs.crm.entity.XsBusinessUrgedInfoVo;
import com.zs.crm.entity.XsSaleStatisticsResultVo;
@Repository
public interface XsSaleStatisticsDao {
	/**
	 * 获取房间统计信息（总数、已售数、未售数）
	 * @param searchInfo
	 * @return
	 */
	public XsSaleStatisticsResultVo getRoomStatisticsInfoDao(Map<String,String> searchInfo);
	/**
	 * 获取未付款信息（未付款客户数和欠款金额）
	 * @param searchInfo
	 * @return
	 */
	public XsSaleStatisticsResultVo getUnPaymentInfoDao(XsBusinessUrgedInfoVo searchInfo);
	/**
	 * 获取未签约信息（未签约中欠款客户数和欠款金额）
	 * @param searchInfo
	 * @return
	 */
	public XsSaleStatisticsResultVo getUnSignUpInfoDao(XsBusinessUrgedInfoVo searchInfo);
	/**
	 * 获取未放款信息（未放款客户数和欠款金额）
	 * @param searchInfo
	 * @return
	 */
	public XsSaleStatisticsResultVo getUnLendingInfoDao(XsBusinessUrgedInfoVo searchInfo);
}
