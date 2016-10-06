package com.zs.crm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.crm.entity.XsPerformanceEntity;
import com.zs.crm.web.vo.XsPerformanceVo;

/**
 * ����ҵ��
 * 
 * �ɽ�����
 * �ɽ����
      �ɽ����
      �ؿ���

 */
@Repository
public interface XsPerformanceDao {
	/**
	 * Ԥ��������---������ҵ����
	 * @param vo
	 * @return
	 */
	public XsPerformanceEntity getBudgetedPerformanceByDateTime(XsPerformanceVo vo);
	/**
	 * Ԥ�������� ---����
	 * @param vo
	 * @return
	 */
	public XsPerformanceEntity getMy_BudgetedPerformanceByDateTime(XsPerformanceVo vo);
	/**
	 * �Ŷ���������
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> getTeamMonthPerformanceRanking(XsPerformanceVo vo);
	/**
	 * ҵ��Ա��������
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> getYWYMonthPerformanceRanking(XsPerformanceVo vo);
	/**
	 * ����������--ͨ����ҵ������ϸ����
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> followAnalysisByYwy(XsPerformanceVo vo);
	/**
	 * ��������--ͨ����������
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> followAnalysisByGroup(XsPerformanceVo vo);
	/**
	 * ��������--��ȡ�����ͳ���������� ���ã�
	 * @param vo
	 * @return
	 */
	public XsPerformanceEntity followAnalysisByTotalCount(XsPerformanceVo vo);
	/**
	 * �ɽ�������--ͨ����ҵ������ϸ����
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> dealAnalysisByYwy(XsPerformanceVo vo);
	/**
	 * �ɽ�����--ͨ����������
	 * @param vo
	 * @return
	 */
	public List<XsPerformanceEntity> dealAnalysisByGroup(XsPerformanceVo vo);
	/**
	 * �ɽ�����--��ȡ�����ͳ������ǩԼ���Ϲ�ͳ������
	 * @param vo
	 * @return
	 */
	public XsPerformanceEntity dealAnalysisByTotalCount(XsPerformanceVo vo);
	/**
	 * �ɽ�����--��ȡ�����ͳ���������ڽ�������
	 * @param vo
	 * @return
	 */
	public XsPerformanceEntity dealAnalysis_yqts(XsPerformanceVo vo);
}
