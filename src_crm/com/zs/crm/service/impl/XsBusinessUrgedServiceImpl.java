package com.zs.crm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.crm.dao.XsBusinessUrgedDao;
import com.zs.crm.entity.XsBusinessUrgedInfoVo;
import com.zs.crm.entity.tableStructure.XsFeeTableEntity;
import com.zs.crm.entity.tableStructure.XsTradeExtTableEntity;
import com.zs.crm.entity.tableStructure.XsTradeGjjlTableEntity;
import com.zs.crm.service.XsBusinessUrgedService;

@Service
public class XsBusinessUrgedServiceImpl implements XsBusinessUrgedService{
	@Autowired
	XsBusinessUrgedDao xsBusinessUrgedDao;
	
	public List<XsBusinessUrgedInfoVo> getUnPaymentCstList(XsBusinessUrgedInfoVo bean) {
		List<XsBusinessUrgedInfoVo> urgedCstList = new ArrayList<XsBusinessUrgedInfoVo>();
		urgedCstList = xsBusinessUrgedDao.getUnPaymentCstListDao(bean);
		return urgedCstList;
	}
	
	public List<XsBusinessUrgedInfoVo> getUnPaymentCstList(String projGuid) {
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		List<XsBusinessUrgedInfoVo> urgedCstList = new ArrayList<XsBusinessUrgedInfoVo>();
		urgedCstList = xsBusinessUrgedDao.getUnPaymentCstListDao(bean);
		return urgedCstList;
	}
	
	public List<XsBusinessUrgedInfoVo> getUnPaymentCstList(String projGuid,String userGuid) {
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		bean.getXsOpp().setUserGuid(userGuid);
		List<XsBusinessUrgedInfoVo> urgedCstList = new ArrayList<XsBusinessUrgedInfoVo>();
		urgedCstList = xsBusinessUrgedDao.getUnPaymentCstListDao(bean);
		return urgedCstList;
	}
	
	public List<XsBusinessUrgedInfoVo> getUnPaymentCstList(String projGuid,int startIndex,int length) {
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		System.out.println("startIndex:"+startIndex+"length:"+length);
		bean.getXsOpp().setProjGuid(projGuid);
		bean.getXsSearch().setStartIndex(startIndex);
		bean.getXsSearch().setLength(length);
		bean.getXsSearch().setSortName("overDays");//默认按某个字段降序排列，到时候写上字段名就可以
		bean.getXsSearch().setSortDir("desc");//
		List<XsBusinessUrgedInfoVo> urgedCstList = new ArrayList<XsBusinessUrgedInfoVo>();
		urgedCstList = xsBusinessUrgedDao.getUnPaymentCstListDao(bean);
		return urgedCstList;
	}
	
	public List<XsBusinessUrgedInfoVo> getUnPaymentCstList(String projGuid,String userGuid,int startIndex,int length) {
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		bean.getXsOpp().setUserGuid(userGuid);
		bean.getXsSearch().setStartIndex(startIndex);
		bean.getXsSearch().setLength(length);
		bean.getXsSearch().setSortName("overDays");//默认按某个字段降序排列，到时候写上字段名就可以
		bean.getXsSearch().setSortDir("desc");//
		List<XsBusinessUrgedInfoVo> urgedCstList = new ArrayList<XsBusinessUrgedInfoVo>();
		urgedCstList = xsBusinessUrgedDao.getUnPaymentCstListDao(bean);
		return urgedCstList;
	}

	public List<XsBusinessUrgedInfoVo> getUnPaymentCstList(String projGuid,int startIndex,int length,String sortName,String SortDir) {
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		bean.getXsSearch().setStartIndex(startIndex);
		bean.getXsSearch().setLength(length);
		bean.getXsSearch().setSortName(sortName);//默认按某个字段降序排列，到时候写上字段名就可以
		bean.getXsSearch().setSortDir(SortDir);//
		List<XsBusinessUrgedInfoVo> urgedCstList = new ArrayList<XsBusinessUrgedInfoVo>();
		urgedCstList = xsBusinessUrgedDao.getUnPaymentCstListDao(bean);
		return urgedCstList;
	}

	public List<XsBusinessUrgedInfoVo> getUnPaymentCstList(String projGuid,String userGuid,int startIndex,int length,String sortName,String SortDir) {
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		bean.getXsOpp().setUserGuid(userGuid);
		bean.getXsSearch().setStartIndex(startIndex);
		bean.getXsSearch().setLength(length);
		bean.getXsSearch().setSortName(sortName);//默认按某个字段降序排列，到时候写上字段名就可以
		bean.getXsSearch().setSortDir(SortDir);//
		List<XsBusinessUrgedInfoVo> urgedCstList = new ArrayList<XsBusinessUrgedInfoVo>();
		urgedCstList = xsBusinessUrgedDao.getUnPaymentCstListDao(bean);
		return urgedCstList;
	}
	public List<XsBusinessUrgedInfoVo> getUnSignUpCstList(XsBusinessUrgedInfoVo bean) {
		List<XsBusinessUrgedInfoVo> unSignUpCstList = new ArrayList<XsBusinessUrgedInfoVo>();
		unSignUpCstList = xsBusinessUrgedDao.getUnSignUpCstListDao(bean);
		return unSignUpCstList;
	}
	public List<XsBusinessUrgedInfoVo> getUnSignUpCstList(String projGuid) {
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		List<XsBusinessUrgedInfoVo> unSignUpCstList = new  ArrayList<XsBusinessUrgedInfoVo>();
		unSignUpCstList = xsBusinessUrgedDao.getUnSignUpCstListDao(bean);
		return unSignUpCstList; 
	}
	public List<XsBusinessUrgedInfoVo> getUnSignUpCstList(String projGuid,String userGuid) {
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		bean.getXsOpp().setUserGuid(userGuid);
		List<XsBusinessUrgedInfoVo> unSignUpCstList = new  ArrayList<XsBusinessUrgedInfoVo>();
		unSignUpCstList = xsBusinessUrgedDao.getUnSignUpCstListDao(bean);
		return unSignUpCstList; 
	}
	public List<XsBusinessUrgedInfoVo> getUnSignUpCstList(String projGuid,int startIndex,int length) {
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		bean.getXsSearch().setStartIndex(startIndex);
		bean.getXsSearch().setLength(length);
		bean.getXsSearch().setSortName("overDays");
		bean.getXsSearch().setSortDir("desc");
		List<XsBusinessUrgedInfoVo> unSignUpCstList = new  ArrayList<XsBusinessUrgedInfoVo>();
		unSignUpCstList = xsBusinessUrgedDao.getUnSignUpCstListDao(bean);
		return unSignUpCstList; 
	}
	public List<XsBusinessUrgedInfoVo> getUnSignUpCstList(String projGuid,String userGuid,int startIndex,int length) {
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		bean.getXsOpp().setUserGuid(userGuid);
		bean.getXsSearch().setStartIndex(startIndex);
		bean.getXsSearch().setLength(length);
		bean.getXsSearch().setSortName("overDays");
		bean.getXsSearch().setSortDir("desc");
		List<XsBusinessUrgedInfoVo> unSignUpCstList = new  ArrayList<XsBusinessUrgedInfoVo>();
		unSignUpCstList = xsBusinessUrgedDao.getUnSignUpCstListDao(bean);
		return unSignUpCstList; 
	}
	public List<XsBusinessUrgedInfoVo> getUnSignUpCstList(String projGuid,int startIndex,int length,String sortName,String SortDir) {
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		bean.getXsSearch().setStartIndex(startIndex);
		bean.getXsSearch().setLength(length);
		bean.getXsSearch().setSortName(sortName);
		bean.getXsSearch().setSortDir(SortDir);
		List<XsBusinessUrgedInfoVo> unSignUpCstList = new  ArrayList<XsBusinessUrgedInfoVo>();
		unSignUpCstList = xsBusinessUrgedDao.getUnSignUpCstListDao(bean);
		return unSignUpCstList; 
	}
	public List<XsBusinessUrgedInfoVo> getUnSignUpCstList(String projGuid,String userGuid,int startIndex,int length,String sortName,String SortDir) {
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		bean.getXsOpp().setUserGuid(userGuid);
		bean.getXsSearch().setStartIndex(startIndex);
		bean.getXsSearch().setLength(length);
		bean.getXsSearch().setSortName(sortName);
		bean.getXsSearch().setSortDir(SortDir);
		List<XsBusinessUrgedInfoVo> unSignUpCstList = new  ArrayList<XsBusinessUrgedInfoVo>();
		unSignUpCstList = xsBusinessUrgedDao.getUnSignUpCstListDao(bean);
		return unSignUpCstList; 
	}
	public List<XsBusinessUrgedInfoVo> getUnLendingCstList(XsBusinessUrgedInfoVo bean) {
		List<XsBusinessUrgedInfoVo> unLendingCstList = new ArrayList<XsBusinessUrgedInfoVo>();
		unLendingCstList = xsBusinessUrgedDao.getUnLendingCstListDao(bean);
		return unLendingCstList;
	}
	public List<XsBusinessUrgedInfoVo> getUnLendingCstList(String projGuid) {
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		List<XsBusinessUrgedInfoVo> unLendingCstList = new  ArrayList<XsBusinessUrgedInfoVo>();
		unLendingCstList = xsBusinessUrgedDao.getUnLendingCstListDao(bean);
		return unLendingCstList; 
	}
	public List<XsBusinessUrgedInfoVo> getUnLendingCstList(String projGuid,String userGuid) {
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		bean.getXsOpp().setUserGuid(userGuid);
		bean.getXsFee().setRmbYe("1");
		List<XsBusinessUrgedInfoVo> unLendingCstList = new  ArrayList<XsBusinessUrgedInfoVo>();
		unLendingCstList = xsBusinessUrgedDao.getUnLendingCstListDao(bean);
		return unLendingCstList; 
	}
	public List<XsBusinessUrgedInfoVo> getUnLendingCstList(String projGuid,int startIndex,int length) {
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		bean.getXsSearch().setStartIndex(startIndex);
		bean.getXsSearch().setLength(length);
		bean.getXsSearch().setSortName("overDays");
		bean.getXsSearch().setSortDir("desc");
		List<XsBusinessUrgedInfoVo> unLendingCstList = new  ArrayList<XsBusinessUrgedInfoVo>();
		unLendingCstList = xsBusinessUrgedDao.getUnLendingCstListDao(bean);
		return unLendingCstList; 
	}
	public List<XsBusinessUrgedInfoVo> getUnLendingCstList(String projGuid,String userGuid,int startIndex,int length) {
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		bean.getXsOpp().setUserGuid(userGuid);
		bean.getXsSearch().setStartIndex(startIndex);
		bean.getXsSearch().setLength(length);
		bean.getXsSearch().setSortName("overDays");
		bean.getXsSearch().setSortDir("desc");
		bean.getXsFee().setRmbYe("1");
		List<XsBusinessUrgedInfoVo> unLendingCstList = new  ArrayList<XsBusinessUrgedInfoVo>();
		unLendingCstList = xsBusinessUrgedDao.getUnLendingCstListDao(bean);
		return unLendingCstList; 
	}
	public List<XsBusinessUrgedInfoVo> getUnLendingCstList(String projGuid,int startIndex,int length,String sortName,String SortDir) {
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		bean.getXsSearch().setStartIndex(startIndex);
		bean.getXsSearch().setLength(length);
		bean.getXsSearch().setSortName(sortName);
		bean.getXsSearch().setSortDir(SortDir);
		List<XsBusinessUrgedInfoVo> unLendingCstList = new  ArrayList<XsBusinessUrgedInfoVo>();
		unLendingCstList = xsBusinessUrgedDao.getUnLendingCstListDao(bean);
		return unLendingCstList; 
	}
	public List<XsBusinessUrgedInfoVo> getUnLendingCstList(String projGuid,String userGuid,int startIndex,int length,String sortName,String SortDir) {
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		bean.getXsOpp().setUserGuid(userGuid);
		bean.getXsSearch().setStartIndex(startIndex);
		bean.getXsSearch().setLength(length);
		bean.getXsSearch().setSortName(sortName);
		bean.getXsSearch().setSortDir(SortDir);
		bean.getXsFee().setRmbYe("1");
		List<XsBusinessUrgedInfoVo> unLendingCstList = new  ArrayList<XsBusinessUrgedInfoVo>();
		unLendingCstList = xsBusinessUrgedDao.getUnLendingCstListDao(bean);
		return unLendingCstList; 
	}
	
	public List<XsBusinessUrgedInfoVo> getToUrgedCstList(XsBusinessUrgedInfoVo bean) {
		List<XsBusinessUrgedInfoVo> urgedCstList = new ArrayList<XsBusinessUrgedInfoVo>();
		urgedCstList = xsBusinessUrgedDao.getToUrgedCstListDao(bean);
		return urgedCstList;
	}
	
	public List<XsBusinessUrgedInfoVo> getToUrgedCstList(String projGuid) {
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		List<XsBusinessUrgedInfoVo> urgedCstList = new ArrayList<XsBusinessUrgedInfoVo>();
		urgedCstList = xsBusinessUrgedDao.getToUrgedCstListDao(bean);
		return urgedCstList;
	}
	
	public List<XsBusinessUrgedInfoVo> getToUrgedCstList(String projGuid,String userGuid) {
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		bean.getXsOpp().setUserGuid(userGuid);
		bean.getXsOther().setToUrged("1");
		bean.getXsFee().setRmbYe("1");
		List<XsBusinessUrgedInfoVo> urgedCstList = new ArrayList<XsBusinessUrgedInfoVo>();
		List<XsBusinessUrgedInfoVo> unPaymentCstList = new ArrayList<XsBusinessUrgedInfoVo>();
		List<XsBusinessUrgedInfoVo> unSignUpCstList = new ArrayList<XsBusinessUrgedInfoVo>();
		List<XsBusinessUrgedInfoVo> unLendingCstList = new ArrayList<XsBusinessUrgedInfoVo>();
		unPaymentCstList = xsBusinessUrgedDao.getUnPaymentCstListDao(bean);
		unSignUpCstList = xsBusinessUrgedDao.getUnSignUpCstListDao(bean);
		unLendingCstList = xsBusinessUrgedDao.getUnLendingCstListDao(bean);
		urgedCstList = xsBusinessUrgedDao.getToUrgedCstListDao(bean);
		for(XsBusinessUrgedInfoVo cst:unPaymentCstList) {
			cst.getXsOther().setToUrged("unPayment");
			urgedCstList.add(cst);
		}
		for(XsBusinessUrgedInfoVo cst:unSignUpCstList) {
			cst.getXsOther().setToUrged("unSignUp");
			urgedCstList.add(cst);
		}
		for(XsBusinessUrgedInfoVo cst:unLendingCstList) {
			cst.getXsOther().setToUrged("unLending");
			urgedCstList.add(cst);
		}
		return urgedCstList;
	}
	
	public List<XsBusinessUrgedInfoVo> getToUrgedCstList(String projGuid,int startIndex,int length) {
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		System.out.println("startIndex:"+startIndex+"length:"+length);
		bean.getXsOpp().setProjGuid(projGuid);
		bean.getXsSearch().setStartIndex(startIndex);
		bean.getXsSearch().setLength(length);
		bean.getXsSearch().setSortName("overDays");//默认按某个字段降序排列，到时候写上字段名就可以
		bean.getXsSearch().setSortDir("desc");//
		List<XsBusinessUrgedInfoVo> urgedCstList = new ArrayList<XsBusinessUrgedInfoVo>();
		urgedCstList = xsBusinessUrgedDao.getToUrgedCstListDao(bean);
		return urgedCstList;
	}
	
	public List<XsBusinessUrgedInfoVo> getToUrgedCstList(String projGuid,String userGuid,int startIndex,int length) {
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		bean.getXsOpp().setUserGuid(userGuid);
		bean.getXsSearch().setStartIndex(startIndex);
		bean.getXsSearch().setLength(length);
		bean.getXsSearch().setSortName("overDays");//默认按某个字段降序排列，到时候写上字段名就可以
		bean.getXsSearch().setSortDir("desc");//
		List<XsBusinessUrgedInfoVo> urgedCstList = new ArrayList<XsBusinessUrgedInfoVo>();
		urgedCstList = xsBusinessUrgedDao.getToUrgedCstListDao(bean);
		return urgedCstList;
	}

	public List<XsBusinessUrgedInfoVo> getToUrgedCstList(String projGuid,int startIndex,int length,String sortName,String SortDir) {
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		bean.getXsSearch().setStartIndex(startIndex);
		bean.getXsSearch().setLength(length);
		bean.getXsSearch().setSortName(sortName);//默认按某个字段降序排列，到时候写上字段名就可以
		bean.getXsSearch().setSortDir(SortDir);//
		List<XsBusinessUrgedInfoVo> urgedCstList = new ArrayList<XsBusinessUrgedInfoVo>();
		urgedCstList = xsBusinessUrgedDao.getToUrgedCstListDao(bean);
		return urgedCstList;
	}

	public List<XsBusinessUrgedInfoVo> getToUrgedCstList(String projGuid,String userGuid,int startIndex,int length,String sortName,String SortDir) {
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		bean.getXsOpp().setUserGuid(userGuid);
		bean.getXsSearch().setStartIndex(startIndex);
		bean.getXsSearch().setLength(length);
		bean.getXsSearch().setSortName(sortName);//默认按某个字段降序排列，到时候写上字段名就可以
		bean.getXsSearch().setSortDir(SortDir);//
		List<XsBusinessUrgedInfoVo> urgedCstList = new ArrayList<XsBusinessUrgedInfoVo>();
		urgedCstList = xsBusinessUrgedDao.getToUrgedCstListDao(bean);
		return urgedCstList;
	}
	
	
	public XsBusinessUrgedInfoVo getUnPaymentTradeInfo(String projGuid,String tradeGuid) {
		XsBusinessUrgedInfoVo tradeInfo = new XsBusinessUrgedInfoVo();
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		bean.getXsTrade().setTradeGuid(tradeGuid);
		tradeInfo = xsBusinessUrgedDao.getUnPaymentCstListDao(bean).get(0);
		return tradeInfo;
	}
	public XsBusinessUrgedInfoVo getUnSignUpTradeInfo(String projGuid,String tradeGuid) {
		XsBusinessUrgedInfoVo tradeInfo = new XsBusinessUrgedInfoVo();
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		bean.getXsTrade().setTradeGuid(tradeGuid);
		tradeInfo = xsBusinessUrgedDao.getUnSignUpCstListDao(bean).get(0);
		return tradeInfo;
	}
	public XsBusinessUrgedInfoVo getUnLendingTradeInfo(String projGuid,String tradeGuid) {
		XsBusinessUrgedInfoVo tradeInfo = new XsBusinessUrgedInfoVo();
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsOpp().setProjGuid(projGuid);
		bean.getXsTrade().setTradeGuid(tradeGuid);
		bean.getXsFee().setRmbYe("1");
		tradeInfo = xsBusinessUrgedDao.getUnLendingCstListDao(bean).get(0);
		return tradeInfo;
	}
	public List<XsBusinessUrgedInfoVo> getCstUrgedInfo(String projGuid,String tradeGuid,String urgedStage) {
		List<XsBusinessUrgedInfoVo> urgedInfoList = new ArrayList<XsBusinessUrgedInfoVo>();
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsGjjl().setTradeGuid(tradeGuid);
		bean.getXsGjjl().setUrgedStage(urgedStage);
		urgedInfoList = xsBusinessUrgedDao.getCstUrgedInfoDao(bean);
		return urgedInfoList;
	}
	
	public List<XsBusinessUrgedInfoVo> getCstUrgedInfo(String projGuid,String tradeGuid,String type,String sortName,String sortDir,String urgedStage) {
		List<XsBusinessUrgedInfoVo> urgedInfoList = new ArrayList<XsBusinessUrgedInfoVo>();
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsGjjl().setTradeGuid(tradeGuid);
		bean.getXsGjjl().setUrgedType(type);
		bean.getXsSearch().setSortName(sortName);
		bean.getXsSearch().setSortDir(sortDir);
		bean.getXsGjjl().setUrgedStage(urgedStage);
		urgedInfoList = xsBusinessUrgedDao.getCstUrgedInfoDao(bean);
		return urgedInfoList;
	}
	public List<XsBusinessUrgedInfoVo> getCstUrgedInfo(String projGuid,String tradeGuid,String sortName,String sortDir,String urgedStage) {
		List<XsBusinessUrgedInfoVo> urgedInfoList = new ArrayList<XsBusinessUrgedInfoVo>();
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsGjjl().setTradeGuid(tradeGuid);
		bean.getXsGjjl().setUrgedStage(urgedStage);
		bean.getXsSearch().setSortName(sortName);
		bean.getXsSearch().setSortDir(sortDir);
		urgedInfoList = xsBusinessUrgedDao.getCstUrgedInfoDao(bean);
		return urgedInfoList;
	}
	public List<XsBusinessUrgedInfoVo> getCstUrgedInfo(String projGuid,String tradeGuid,int startIndex,int length,String sortName,String sortDir,String urgedStage) {
		List<XsBusinessUrgedInfoVo> urgedInfoList = new ArrayList<XsBusinessUrgedInfoVo>();
		XsBusinessUrgedInfoVo bean = new XsBusinessUrgedInfoVo();
		bean.getXsGjjl().setTradeGuid(tradeGuid);
		bean.getXsSearch().setStartIndex(startIndex);
		bean.getXsSearch().setLength(length);
		bean.getXsSearch().setSortName(sortName);
		bean.getXsSearch().setSortDir(sortDir);
		bean.getXsGjjl().setUrgedStage(urgedStage);
		urgedInfoList = xsBusinessUrgedDao.getCstUrgedInfoDao(bean);
		return urgedInfoList;
	}
	public int insertCstUrgedInfo(XsTradeGjjlTableEntity urgedInfo) {
		int flag = 0;
		flag = xsBusinessUrgedDao.insertCstUrgedInfoDao(urgedInfo);
		return flag;
	}
	public int insertOrUpdateDelayInfo(String tradeGuid,String postponeDate,String postponeReason,String postponeStage) {
		int flag = 0;
		XsTradeExtTableEntity delayInfo = new XsTradeExtTableEntity();
		delayInfo.setTradeGuid(tradeGuid);
		delayInfo.setPostponeDate(postponeDate);
		delayInfo.setPostponeReason(postponeReason);
		delayInfo.setPostponeStage(postponeStage);
		flag = xsBusinessUrgedDao.insertOrUpdateDelayInfoDao(delayInfo);
		return flag;
	}
	public int insertOrUpdateUrgedStatusInfo(String tradeGuid,String level,String type) {
		int flag = 0;
		XsTradeExtTableEntity urgedStatus = new XsTradeExtTableEntity();
		urgedStatus.setTradeGuid(tradeGuid);
		switch(type){
			case "unPayment":
				urgedStatus.setLevel(level);
				break;
			case "unSignUp":
				urgedStatus.setLevel1(level);
				break;
			case "unLending":
				urgedStatus.setLevel2(level);
				break;
		}
		
		flag = xsBusinessUrgedDao.insertOrUpdateUrgedStatusInfoDao(urgedStatus);
		return flag;
	}
	public List<XsFeeTableEntity> getFeeInfoByTradeGuid(String tradeGuid) {
		List<XsFeeTableEntity> feeInfoList = new ArrayList<XsFeeTableEntity>();
		feeInfoList	= xsBusinessUrgedDao.getFeeInfoByTradeGuidDao(tradeGuid);
		return feeInfoList;
	}
}
