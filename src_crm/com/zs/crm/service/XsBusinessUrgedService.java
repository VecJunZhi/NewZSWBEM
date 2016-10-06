package com.zs.crm.service;

import java.util.List;

import com.zs.crm.entity.XsBusinessUrgedInfoVo;
import com.zs.crm.entity.tableStructure.XsFeeTableEntity;
import com.zs.crm.entity.tableStructure.XsTradeExtTableEntity;
import com.zs.crm.entity.tableStructure.XsTradeGjjlTableEntity;

public interface XsBusinessUrgedService {
	/**
	 * 根据多种条件查询未付款的客户
	 * @param bean
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnPaymentCstList(XsBusinessUrgedInfoVo bean);
	/**
	 * 查询该项目下所有逾期未付款的客户(用于管理员获取客户数量)
	 * @param projGuid
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnPaymentCstList(String projGuid);
	/**
	 * 查询该项目下某一置业顾问所有逾期未付款的客户（用于置业顾问获取客户数量）
	 * @param projGuid
	 * @param userGuid
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnPaymentCstList(String projGuid,String userGuid);
	/**
	 * 查询该项目下所有逾期未付款的客户中第m到n条（用于管理员分页查看）
	 * @param projGuid
	 * @param startIndex
	 * @param length
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnPaymentCstList(String projGuid,int startIndex,int length);
	/**
	 * 查询该项目下某一置业顾问所有逾期未付款的客户中第m到n条（用于置业顾问分页查看）
	 * @param projGuid
	 * @param userGuid
	 * @param startIndex
	 * @param length
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnPaymentCstList(String projGuid,String userGuid,int startIndex,int length);
	/**
	 * 查询该项目下所有逾期未付款的客户中第m到n条，且按sortName和sortDir排序（用于管理员分页查看）
	 * @param projGuid
	 * @param startIndex
	 * @param length
	 * @param sortName
	 * @param sortDir
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnPaymentCstList(String projGuid,int startIndex,int length,String sortName,String sortDir);
	/**
	 * 查询该项目下某一置业顾问所有逾期未付款的客户中第m到n条，且按sortName和sortDir排序（用于置业顾问分页查看）
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
	 * 查询该项目下所有未签约的客户(用于管理员获取客户数量)
	 * @param projGuid
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnSignUpCstList(String projGuid);
	/**
	 * 查询该项目下某一置业顾问所有未签约的客户（用于置业顾问获取客户数量）
	 * @param projGuid
	 * @param userGuid
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnSignUpCstList(String projGuid,String userGuid);
	/**
	 * 查询该项目下所有未签约的客户中第m到n条（用于管理员分页查看）
	 * @param projGuid
	 * @param startIndex
	 * @param length
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnSignUpCstList(String projGuid,int startIndex,int length);
	/**
	 * 查询该项目下某一置业顾问所有未签约的客户中第m到n条（用于置业顾问分页查看）
	 * @param projGuid
	 * @param userGuid
	 * @param startIndex
	 * @param length
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnSignUpCstList(String projGuid,String userGuid,int startIndex,int length);
	/**
	 * 查询该项目下某一置业顾问所有未签约的客户中第m到n条，且按sortName和sortDir排序（用于置业顾问分页查看）
	 * @param projGuid
	 * @param startIndex
	 * @param length
	 * @param sortName
	 * @param sortDir
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnSignUpCstList(String projGuid,int startIndex,int length,String sortName,String sortDir);
	/**
	 * 查询该项目下某一置业顾问所有未签约的客户中第m到n条，且按sortName和sortDir排序（用于置业顾问分页查看）
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
	 * 查询该项目下所有未放款的客户(用于管理员获取客户数量)
	 * @param projGuid
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnLendingCstList(String projGuid);
	/**
	 * 查询该项目下某一置业顾问所有未放款的客户（用于置业顾问获取客户数量）
	 * @param projGuid
	 * @param userGuid
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnLendingCstList(String projGuid,String userGuid);
	/**
	 * 查询该项目下某一置业顾问所有未放款的客户中第m到n条（用于置业顾问分页查看）
	 * @param projGuid
	 * @param startIndex
	 * @param length
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnLendingCstList(String projGuid,int startIndex,int length);
	/**
	 * 查询该项目下某一置业顾问所有未放款的客户中第m到n条（用于置业顾问分页查看）
	 * @param projGuid
	 * @param userGuid
	 * @param startIndex
	 * @param length
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnLendingCstList(String projGuid,String userGuid,int startIndex,int length);
	/**
	 * 查询该项目下某一置业顾问所有未放款的客户中第m到n条，且按sortName和sortDir排序（用于置业顾问分页查看）
	 * @param projGuid
	 * @param startIndex
	 * @param length
	 * @param sortName
	 * @param sortDir
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getUnLendingCstList(String projGuid,int startIndex,int length,String sortName,String sortDir);
	/**
	 * 查询该项目下某一置业顾问所有未放款的客户中第m到n条，且按sortName和sortDir排序（用于置业顾问分页查看）
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
	 * 根据多种条件查询今日待催办的客户
	 * @param bean
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getToUrgedCstList(XsBusinessUrgedInfoVo bean);
	/**
	 * 查询该项目下所有今日待催办的客户(用于管理员获取客户数量)
	 * @param projGuid
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getToUrgedCstList(String projGuid);
	/**
	 * 查询该项目下某一置业顾问所有今日待催办的客户（用于置业顾问获取客户数量）
	 * @param projGuid
	 * @param userGuid
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getToUrgedCstList(String projGuid,String userGuid);
	/**
	 * 查询该项目下所有今日待催办的客户中第m到n条（用于管理员分页查看）
	 * @param projGuid
	 * @param startIndex
	 * @param length
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getToUrgedCstList(String projGuid,int startIndex,int length);
	/**
	 * 查询该项目下某一置业顾问所有今日待催办的客户中第m到n条（用于置业顾问分页查看）
	 * @param projGuid
	 * @param userGuid
	 * @param startIndex
	 * @param length
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getToUrgedCstList(String projGuid,String userGuid,int startIndex,int length);
	/**
	 * 查询该项目下所有今日待催办的客户中第m到n条，且按sortName和sortDir排序（用于管理员分页查看）
	 * @param projGuid
	 * @param startIndex
	 * @param length
	 * @param sortName
	 * @param sortDir
	 * @return
	 */
	public List<XsBusinessUrgedInfoVo> getToUrgedCstList(String projGuid,int startIndex,int length,String sortName,String sortDir);
	/**
	 * 查询该项目下某一置业顾问所有今日待催办的客户中第m到n条，且按sortName和sortDir排序（用于置业顾问分页查看）
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
	 * 查询未付款客户交易信息
	 * @param projGuid
	 * @param userGuid
	 * @param cstGuid
	 * @return
	 */
	public XsBusinessUrgedInfoVo getUnPaymentTradeInfo(String projGuid,String tradeGuid);
	/**
	 * 查询未签约客户交易信息
	 * @param projGuid
	 * @param userGuid
	 * @param cstGuid
	 * @return
	 */
	public XsBusinessUrgedInfoVo getUnSignUpTradeInfo(String projGuid,String tradeGuid);
	/**
	 * 查询未放款客户交易信息
	 * @param projGuid
	 * @param userGuid
	 * @param cstGuid
	 * @return
	 */
	public XsBusinessUrgedInfoVo getUnLendingTradeInfo(String projGuid,String tradeGuid);
	/**
	 * 查询客户的所有催办记录
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
	 * 新增催办信息
	 * @param urgedInfo
	 * @return
	 */
	public int insertCstUrgedInfo(XsTradeGjjlTableEntity urgedInfo);
	/**
	 * 新增或更新延期信息
	 * @param tradeGuid
	 * @param postponeDate
	 * @param postponeReason
	 * @return
	 */
	public int insertOrUpdateDelayInfo(String tradeGuid,String postponeDate,String postponeReason,String postponeStage);
	/**
	 * 新增或更新催办状态信息
	 * @param tradeGuid
	 * @param level
	 * @param type
	 * @return
	 */
	public int insertOrUpdateUrgedStatusInfo(String tradeGuid,String level,String type);
	/**
	 * 根据tradeGuid获取缴费详情
	 * @param tradeGuid
	 * @return
	 */
	public List<XsFeeTableEntity> getFeeInfoByTradeGuid(String tradeGuid);
	
}
