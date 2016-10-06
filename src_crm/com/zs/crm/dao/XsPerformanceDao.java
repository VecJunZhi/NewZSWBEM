package com.zs.crm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.crm.entity.XsPerformanceEntity;
import com.zs.crm.web.vo.XsPerformanceVo;

/**
 * 销售业绩
 * 
 * 成交套数
 * 成交金额
      成交面积
      回款额度

 */
@Repository
public interface XsPerformanceDao {
	/**
	 * 预算完成情况---所有置业顾问
	 * @param vo
	 * @return
	 */
	public XsPerformanceEntity getBudgetedPerformanceByDateTime(XsPerformanceVo vo);
	/**
	 * 预算完成情况 ---个人
	 * @param vo
	 * @return
	 */
	public XsPerformanceEntity getMy_BudgetedPerformanceByDateTime(XsPerformanceVo vo);
	/**
	 * 团队销售排行
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> getTeamMonthPerformanceRanking(XsPerformanceVo vo);
	/**
	 * 业务员销售排行
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> getYWYMonthPerformanceRanking(XsPerformanceVo vo);
	/**
	 * 跟进分析中--通过置业顾问详细分析
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> followAnalysisByYwy(XsPerformanceVo vo);
	/**
	 * 跟进分析--通过组来分析
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> followAnalysisByGroup(XsPerformanceVo vo);
	/**
	 * 跟进分析--获取总体的统计数（来电 来访）
	 * @param vo
	 * @return
	 */
	public XsPerformanceEntity followAnalysisByTotalCount(XsPerformanceVo vo);
	/**
	 * 成交分析中--通过置业顾问详细分析
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> dealAnalysisByYwy(XsPerformanceVo vo);
	/**
	 * 成交分析--通过组来分析
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> dealAnalysisByGroup(XsPerformanceVo vo);
	/**
	 * 成交分析--获取总体的统计数（签约，认购统计数）
	 * @param vo
	 * @return
	 */
	public XsPerformanceEntity dealAnalysisByTotalCount(XsPerformanceVo vo);
	/**
	 * 成交分析--获取总体的统计数（逾期交易数）
	 * @param vo
	 * @return
	 */
	public XsPerformanceEntity dealAnalysis_yqts(XsPerformanceVo vo);
}
