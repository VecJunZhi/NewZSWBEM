package com.zs.crm.service;

import java.util.List;

import com.zs.crm.entity.XsPerformanceEntity;
import com.zs.crm.web.vo.XsPerformanceVo;

public interface XsPerformanceService {
	/**
	 * 预算完成情况--所有职业顾问
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public XsPerformanceEntity getBudgetedPerformanceByDateTime(String startTime,String endTime);
	/**
	 * 预算完成情况---个人
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public XsPerformanceEntity getMy_BudgetedPerformanceByDateTime(String startTime,String endTime,String ywy);
	/**
	 * 团队销售排行
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> getTeamMonthPerformanceRanking(String startTime,String endTime ,String orderWord);
	/**
	 * 业务员销售排行
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> getYWYMonthPerformanceRanking(String startTime,String endTime,String orderWord);
	/**
	 * 跟进分析中--通过置业顾问详细分析
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> followAnalysisByYwy(String startTime,String endTime);
	/**
	 * 跟进分析--通过组来分析
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> followAnalysisByGroup(String startTime,String endTime);
	/**
	 * 跟进分析--获取总体的统计数（来电 来访）
	 * @param vo
	 * @return
	 */
	public XsPerformanceEntity followAnalysisByTotalCount(String startTime,String endTime);
	/**
	 * 成交分析中--通过置业顾问详细分析
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> dealAnalysisByYwy(String startTime,String endTime);
	/**
	 * 成交分析--通过组来分析
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> dealAnalysisByGroup(String startTime,String endTime);
	/**
	 * 成交分析--获取总体的统计数（签约，认购统计数）
	 * @param vo
	 * @return
	 */
	public XsPerformanceEntity dealAnalysisByTotalCount(String startTime,String endTime);
	/**
	 * 成交分析--获取总体的统计数（逾期交易数）
	 * @param vo
	 * @return
	 */
	public XsPerformanceEntity dealAnalysis_yqts(String startTime,String endTime);
	/**
	 * 业务员个人销售情况
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> getMy_YWYMonthPerformanceRanking(String startTime,String endTime,String orderWord,String ywy);
	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @param ywy
	 * @param projGuid
	 * @return
	 */
	public XsPerformanceEntity getMy_BudgetedPerformanceByDateTime(String startTime,String endTime,String ywy,String projGuid);
	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @param orderWord
	 * @param ywy
	 * @param projGuid
	 * @return
	 */
	public List<XsPerformanceEntity> getMy_YWYMonthPerformanceRanking(String startTime,String endTime,String orderWord,String ywy,String projGuid);
	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @param orderWord
	 * @param projGuid
	 * @return
	 */
	public List<XsPerformanceEntity> getTeamMonthPerformanceRanking(String startTime,String endTime ,String orderWord,String projGuid);
}
