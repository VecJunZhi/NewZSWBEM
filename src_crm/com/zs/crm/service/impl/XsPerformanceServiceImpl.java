package com.zs.crm.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.busi.utils.LogUtil;
import com.zs.crm.dao.XsPerformanceDao;
import com.zs.crm.entity.XsPerformanceEntity;
import com.zs.crm.service.XsPerformanceService;
import com.zs.crm.web.vo.XsPerformanceVo;

@Service
public class XsPerformanceServiceImpl implements XsPerformanceService{
	
	Log log=LogUtil.getLog();
	@Autowired
	XsPerformanceDao xsPerformanceDao;
	
	@Override
	public XsPerformanceEntity getBudgetedPerformanceByDateTime(String startTime, String endTime) {
		XsPerformanceVo vo=new XsPerformanceVo();
		vo.setStartTime(startTime);
		vo.setEndTime(endTime);
		XsPerformanceEntity entity=xsPerformanceDao.getBudgetedPerformanceByDateTime(vo);
		return entity;
	}

	@Override
	public List<XsPerformanceEntity> getTeamMonthPerformanceRanking(
			String startTime, String endTime,String orderWord) {
		XsPerformanceVo vo=new XsPerformanceVo();
		vo.setStartTime(startTime);
		vo.setEndTime(endTime);
		vo.setOrderWord(orderWord);
		List<XsPerformanceEntity> list=xsPerformanceDao.getTeamMonthPerformanceRanking(vo);
		return list;
	}

	@Override
	public List<XsPerformanceEntity> getYWYMonthPerformanceRanking(
			String startTime, String endTime,String orderWord) {
		XsPerformanceVo vo=new XsPerformanceVo();
		vo.setStartTime(startTime);
		vo.setEndTime(endTime);
		vo.setOrderWord(orderWord);
		List<XsPerformanceEntity> list=xsPerformanceDao.getYWYMonthPerformanceRanking(vo);
		return list;
	}
	@Override
	public List<XsPerformanceEntity> getMy_YWYMonthPerformanceRanking(
			String startTime, String endTime,String orderWord,String ywy) {
		XsPerformanceVo vo=new XsPerformanceVo();
		vo.setStartTime(startTime);
		vo.setEndTime(endTime);
		vo.setYwy(ywy);
		vo.setOrderWord(orderWord);
		List<XsPerformanceEntity> list=xsPerformanceDao.getYWYMonthPerformanceRanking(vo);
		return list;
	}

	@Override
	public List<XsPerformanceEntity> followAnalysisByYwy(String startTime,String endTime) {
		XsPerformanceVo vo=new XsPerformanceVo();
		vo.setStartTime(startTime);
		vo.setEndTime(endTime);
		return xsPerformanceDao.followAnalysisByYwy(vo);
	}

	@Override
	public List<XsPerformanceEntity> followAnalysisByGroup(String startTime,String endTime) {
		XsPerformanceVo vo=new XsPerformanceVo();
		vo.setStartTime(startTime);
		vo.setEndTime(endTime);
		return xsPerformanceDao.followAnalysisByGroup(vo);
	}

	@Override
	public XsPerformanceEntity followAnalysisByTotalCount(
			String startTime, String endTime) {
		XsPerformanceVo vo=new XsPerformanceVo();
		vo.setStartTime(startTime);
		vo.setEndTime(endTime);
		return xsPerformanceDao.followAnalysisByTotalCount(vo);
	}

	@Override
	public List<XsPerformanceEntity> dealAnalysisByYwy(String startTime,
			String endTime) {
		XsPerformanceVo vo=new XsPerformanceVo();
		vo.setStartTime(startTime);
		vo.setEndTime(endTime);
		return xsPerformanceDao.dealAnalysisByYwy(vo);
	}

	@Override
	public List<XsPerformanceEntity> dealAnalysisByGroup(String startTime,
			String endTime) {
		XsPerformanceVo vo=new XsPerformanceVo();
		vo.setStartTime(startTime);
		vo.setEndTime(endTime);
		return xsPerformanceDao.dealAnalysisByGroup(vo);
	}

	@Override
	public XsPerformanceEntity dealAnalysisByTotalCount(String startTime,
			String endTime) {
		XsPerformanceVo vo=new XsPerformanceVo();
		vo.setStartTime(startTime);
		vo.setEndTime(endTime);
		return xsPerformanceDao.dealAnalysisByTotalCount(vo);
	}

	@Override
	public XsPerformanceEntity dealAnalysis_yqts(String startTime,
			String endTime) {
		XsPerformanceVo vo=new XsPerformanceVo();
		vo.setStartTime(startTime);
		vo.setEndTime(endTime);
		return xsPerformanceDao.dealAnalysis_yqts(vo);
	}

	@Override
	public XsPerformanceEntity getMy_BudgetedPerformanceByDateTime(
			String startTime, String endTime,String ywy) {
		XsPerformanceVo vo=new XsPerformanceVo();
		vo.setStartTime(startTime);
		vo.setEndTime(endTime);
		vo.setYwy(ywy);
		XsPerformanceEntity entity=xsPerformanceDao.getMy_BudgetedPerformanceByDateTime(vo);
		return entity;
	}
	
	public XsPerformanceEntity getMy_BudgetedPerformanceByDateTime(
			String startTime,String endTime,String ywy,String projGuid) {
		XsPerformanceVo vo=new XsPerformanceVo();
		vo.setStartTime(startTime);
		vo.setEndTime(endTime);
		vo.setYwy(ywy);
		vo.setProjGuid(projGuid);
		XsPerformanceEntity entity=xsPerformanceDao.getMy_BudgetedPerformanceByDateTime(vo);
		return entity;
	}

	@Override
	public List<XsPerformanceEntity> getMy_YWYMonthPerformanceRanking(String startTime, String endTime,
			String orderWord, String ywy, String projGuid) {
		XsPerformanceVo vo=new XsPerformanceVo();
		vo.setStartTime(startTime);
		vo.setEndTime(endTime);
		vo.setYwy(ywy);
		vo.setOrderWord(orderWord);
		vo.setProjGuid(projGuid);
		List<XsPerformanceEntity> list=xsPerformanceDao.getYWYMonthPerformanceRanking(vo);
		return list;
	}

	@Override
	public List<XsPerformanceEntity> getTeamMonthPerformanceRanking(String startTime, String endTime, String orderWord,
			String projGuid) {
		XsPerformanceVo vo=new XsPerformanceVo();
		vo.setStartTime(startTime);
		vo.setEndTime(endTime);
		vo.setOrderWord(orderWord);
		vo.setProjGuid(projGuid);
		List<XsPerformanceEntity> list=xsPerformanceDao.getTeamMonthPerformanceRanking(vo);
		return list;
	}
	
}
