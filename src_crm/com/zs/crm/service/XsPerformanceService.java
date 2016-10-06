package com.zs.crm.service;

import java.util.List;

import com.zs.crm.entity.XsPerformanceEntity;
import com.zs.crm.web.vo.XsPerformanceVo;

public interface XsPerformanceService {
	/**
	 * Ԥ��������--����ְҵ����
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public XsPerformanceEntity getBudgetedPerformanceByDateTime(String startTime,String endTime);
	/**
	 * Ԥ��������---����
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public XsPerformanceEntity getMy_BudgetedPerformanceByDateTime(String startTime,String endTime,String ywy);
	/**
	 * �Ŷ���������
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> getTeamMonthPerformanceRanking(String startTime,String endTime ,String orderWord);
	/**
	 * ҵ��Ա��������
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> getYWYMonthPerformanceRanking(String startTime,String endTime,String orderWord);
	/**
	 * ����������--ͨ����ҵ������ϸ����
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> followAnalysisByYwy(String startTime,String endTime);
	/**
	 * ��������--ͨ����������
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> followAnalysisByGroup(String startTime,String endTime);
	/**
	 * ��������--��ȡ�����ͳ���������� ���ã�
	 * @param vo
	 * @return
	 */
	public XsPerformanceEntity followAnalysisByTotalCount(String startTime,String endTime);
	/**
	 * �ɽ�������--ͨ����ҵ������ϸ����
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> dealAnalysisByYwy(String startTime,String endTime);
	/**
	 * �ɽ�����--ͨ����������
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> dealAnalysisByGroup(String startTime,String endTime);
	/**
	 * �ɽ�����--��ȡ�����ͳ������ǩԼ���Ϲ�ͳ������
	 * @param vo
	 * @return
	 */
	public XsPerformanceEntity dealAnalysisByTotalCount(String startTime,String endTime);
	/**
	 * �ɽ�����--��ȡ�����ͳ���������ڽ�������
	 * @param vo
	 * @return
	 */
	public XsPerformanceEntity dealAnalysis_yqts(String startTime,String endTime);
	/**
	 * ҵ��Ա�����������
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
