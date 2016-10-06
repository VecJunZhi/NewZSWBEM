package com.zs.crm.web.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zs.common.util.CommonUtil;
import com.zs.crm.entity.XsSaleManagerEntity;
import com.zs.crm.service.XsSaleManagerService;
import com.zs.crm.web.vo.XsFollowWayVo;
import com.zs.crm.web.vo.XsSaleManagerVo;

@Controller
@RequestMapping(value="/mbem/mcrm/house/saleManager")
public class XsSaleManagerController {
	private Log log=LogFactory.getLog(XsSaleManagerController.class);
	
	@Autowired
	XsSaleManagerService xsSaleManagerService;
	@Autowired
	HttpSession httpSession;
	/**
	 * 案场管理首页
	 * @return  
	 */
	/*@RequestMapping("/index")   //实际访问路径    /mbem/mcrm/house/saleManager/index.action 头部文件value值 加 index   
	public String index(){
		
		return "/mbem/mcrm/house/saleManager/index";    //此路径实际指向到      /WebRoot/WEB-INF/jsp/mcrm/house/saleManager/index.jsp
	}*/
	public String getFollowInfo(String beginTime,String endTime) {
		XsSaleManagerEntity searchOption = new XsSaleManagerEntity();
		XsFollowWayVo followInfo = new XsFollowWayVo();
		searchOption.setBeginTime(beginTime);
		searchOption.setEndTime(endTime);
		searchOption.setGjfs("客户来电");
		List<XsSaleManagerEntity> callList = xsSaleManagerService.getCstByCycleAndFollowWay(searchOption);
		log.info(callList.size());
		searchOption.setGjfs("现场接待");
		List<XsSaleManagerEntity> visitList = xsSaleManagerService.getCstByCycleAndFollowWay(searchOption);
		log.info(visitList.size());
		searchOption.setGjfs("主动电访");
		List<XsSaleManagerEntity> toCallList = xsSaleManagerService.getCstByCycleAndFollowWay(searchOption);
		log.info(toCallList.size());
		searchOption.setGjfs("其他");
		List<XsSaleManagerEntity> otherList = xsSaleManagerService.getCstByCycleAndFollowWay(searchOption);
		log.info(otherList.size());
		Map<String,Integer> groupInfo = new HashMap<String,Integer>();
		if(callList.size() != 0) {
			groupInfo = followInfo.getCall().getGroupInfo();
			int callNum = 0;
			for(XsSaleManagerEntity call:callList) {
				if(call.getGroupName() == null || call.getGroupName().equals(""))
					call.setGroupName("C");
				groupInfo.put(call.getGroupName(),call.getGroupNum());
				followInfo.getCall().setFollowWayName(call.getGjfs());
				callNum += call.getGroupNum();
			}
			followInfo.getCall().setFollowTimes(callNum);
		}
		if(visitList.size() != 0) {
			groupInfo = followInfo.getVisit().getGroupInfo();
			int visitNum = 0;
			for(XsSaleManagerEntity call:visitList) {
				if(call.getGroupName() == null || call.getGroupName().equals(""))
					call.setGroupName("C");
				groupInfo.put(call.getGroupName(),call.getGroupNum());
				followInfo.getVisit().setFollowWayName(call.getGjfs());
				visitNum += call.getGroupNum();
				log.info("visit:"+visitNum);
			}
			followInfo.getVisit().setFollowTimes(visitNum);
		}
		if(toCallList.size() != 0) {
			groupInfo = followInfo.getToCall().getGroupInfo();
			int toCallNum = 0;
			for(XsSaleManagerEntity call:toCallList) {
				if(call.getGroupName() == null || call.getGroupName().equals(""))
					call.setGroupName("C");
				groupInfo.put(call.getGroupName(),call.getGroupNum());
				followInfo.getToCall().setFollowWayName(call.getGjfs());
				toCallNum += call.getGroupNum();
			}
			followInfo.getToCall().setFollowTimes(toCallNum);
		}
		if(otherList.size() != 0) {
			groupInfo = followInfo.getOther().getGroupInfo();
			int otherNum = 0;
			for(XsSaleManagerEntity call:otherList) {
				if(call.getGroupName() == null || call.getGroupName().equals(""))
					call.setGroupName("C");
				groupInfo.put(call.getGroupName(),call.getGroupNum());
				followInfo.getOther().setFollowWayName(call.getGjfs()+"跟进方式");
				otherNum += call.getGroupNum();
			}
			followInfo.getOther().setFollowTimes(otherNum);
		}
		httpSession.setAttribute("followInfo", followInfo);
		return "success";
	}
	
	public XsSaleManagerVo getCstStatusInfo(String beginTime,String endTime,String saleOrSign) {
		XsSaleManagerEntity searchOption = new XsSaleManagerEntity();
		SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		XsSaleManagerVo subscribeInfo = new XsSaleManagerVo("");

		endTime = fm1.format(new Date());
		log.info("beginTime:"+beginTime+"   endTime:"+endTime);
		searchOption.setBeginTime(beginTime);
		searchOption.setEndTime(endTime);
		List<XsSaleManagerEntity> subscribeList = new ArrayList<XsSaleManagerEntity>();
		if(saleOrSign.equals("Sale"))
			subscribeList = xsSaleManagerService.getCstSubscribeInfoByCycle(searchOption);
		if(saleOrSign.equals("Sign"))
			subscribeList = xsSaleManagerService.getCstSignUpInfoByCycle(searchOption);
		int sets = 0;
		double money= 0;
		log.info(subscribeList.size());
		Map<String,Integer> groupInfo = subscribeInfo .getGroupInfo();
		for(XsSaleManagerEntity subscribe:subscribeList) {
			sets += subscribe.getSets();
			log.info(subscribe.getMoney());
			money += Double.parseDouble(subscribe.getMoney());//CommonUtil.formate(subscribe.getMoney(),10000);
			log.info(money);
		}
		for(XsSaleManagerEntity subscribe:subscribeList) {
			if(subscribe.getGroupName() == null || subscribe.getGroupName().equals(""))
				subscribe.setGroupName("C");
			double groupMoney = Double.parseDouble(subscribe.getMoney());
			log.info(groupMoney);
			log.info(money);
			int ratio;
			if(groupMoney == 0)
				ratio = 0;
			else
				ratio = Integer.parseInt(new java.text.DecimalFormat("0").format((groupMoney/money)*100));
			groupInfo.put(subscribe.getGroupName(),ratio);
		}
		subscribeInfo.setMoney(CommonUtil.formate(money,10000).equals(".00")?"0.00":CommonUtil.formate(money, 10000));
		subscribeInfo.setSets(sets);
		return subscribeInfo;
	}
	
	public String getSignUpInfo(String signUpInfoType) {
		return "success";
	}
	
	@RequiresPermissions(value="page:mbem:mcrm:saleManager:saleReport")
	@RequestMapping("/saleReport")//月报周报区间报
	public String saleWeekReport(@RequestParam(value="reportType",required=true)String reportType,XsSaleManagerEntity xs) {
		log.info(reportType);
		SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat fm2 = new SimpleDateFormat("yyyy-MM-dd");
		String beginTime="";
		String endTime = fm1.format(new Date());
		String beginDate = "";
		String endDate = "";
		Calendar cal = Calendar.getInstance();
		XsSaleManagerVo saleInfo = new XsSaleManagerVo("本周");
		XsSaleManagerVo signInfo = new XsSaleManagerVo("本周");
		XsSaleManagerEntity bean = new XsSaleManagerEntity();
		String firstVisitCount;
		switch(reportType) {
			case "day":
				beginTime = fm2.format(new Date())+" 00:00:00";
				endTime = fm1.format(new Date());
				saleInfo = getCstStatusInfo(beginTime,endTime,"Sale");
				signInfo = getCstStatusInfo(beginTime,endTime,"Sign");
				saleInfo.setFollowWayName("本日");
				signInfo.setFollowWayName("本日");
				beginTime = fm2.format(cal.getTime())+" 00:00:00";
				XsSaleManagerVo monthSaleInfo = getCstStatusInfo(beginTime,endTime,"Sale");
				XsSaleManagerVo monthSignInfo = getCstStatusInfo(beginTime,endTime,"Sign");
				monthSaleInfo.setFollowWayName("本月");
				monthSignInfo.setFollowWayName("本月");
				cal.set(Calendar.DAY_OF_YEAR, 1);
				beginTime = fm2.format(cal.getTime())+" 00:00:00";
				XsSaleManagerVo yearSaleInfo = getCstStatusInfo(beginTime,endTime,"Sale");
				XsSaleManagerVo yearSignInfo = getCstStatusInfo(beginTime,endTime,"Sign");
				yearSaleInfo.setFollowWayName("本年");
				yearSignInfo.setFollowWayName("本年");
				beginTime = "1970-01-01 00:00:00";
				XsSaleManagerVo totalSaleInfo = getCstStatusInfo(beginTime,endTime,"Sale");
				XsSaleManagerVo totalSignInfo = getCstStatusInfo(beginTime,endTime,"Sign");
				totalSaleInfo.setFollowWayName("累计");
				totalSignInfo.setFollowWayName("累计");
				httpSession.setAttribute("monthSaleInfo", monthSaleInfo);
				httpSession.setAttribute("yearSaleInfo", yearSaleInfo);
				httpSession.setAttribute("totalSaleInfo", totalSaleInfo);
				httpSession.setAttribute("monthSignInfo", monthSignInfo);
				httpSession.setAttribute("yearSignInfo", yearSignInfo);
				httpSession.setAttribute("totalSignInfo", totalSignInfo);
				beginTime = fm2.format(new Date())+" 00:00:00";
				endTime = fm1.format(new Date());
				beginDate = fm2.format(new Date());
				break;
			case "week":
				cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				beginDate = fm2.format(cal.getTime());
				beginTime = fm2.format(cal.getTime())+" 00:00:00";
				endTime = fm1.format(new Date());
				cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				cal.add(Calendar.WEEK_OF_MONTH, 1);
				endDate = fm2.format(cal.getTime());
				saleInfo = getCstStatusInfo(beginTime,endTime,"Sale");
				signInfo = getCstStatusInfo(beginTime,endTime,"Sign");
				saleInfo.setFollowWayName("本周");
				signInfo.setFollowWayName("本周");
				break;
			case "month":
				cal.set(Calendar.DAY_OF_MONTH, 1);
				beginDate = fm2.format(cal.getTime());
				beginTime = fm2.format(cal.getTime())+" 00:00:00";
				endTime = fm1.format(new Date());
				cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
				endDate = fm2.format(cal.getTime());
				saleInfo = getCstStatusInfo(beginTime,endTime,"Sale");
				signInfo = getCstStatusInfo(beginTime,endTime,"Sign");
				saleInfo.setFollowWayName("本月");
				signInfo.setFollowWayName("本月");
				break;
			case "range":
				beginTime = xs.getBeginTime()+" 00:00:00";
				endTime = xs.getEndTime()+" 23:59:59";//
				beginDate = xs.getBeginTime();
				endDate = xs.getEndTime();
				saleInfo = getCstStatusInfo(beginTime,endTime,"Sale");
				signInfo = getCstStatusInfo(beginTime,endTime,"Sign");
				break;
			default:
				break;
		}
		bean.setBeginTime(beginTime);
		bean.setEndTime(endTime);
		firstVisitCount = xsSaleManagerService.getFirstVistCountByCycle(bean);
		if(firstVisitCount == null || firstVisitCount.equals(""))
			firstVisitCount = "0";
		getFollowInfo(beginTime,endTime);//获取跟进的统计
		
		httpSession.setAttribute("reportType",reportType);
		httpSession.setAttribute("firstVisitCount", firstVisitCount);
		httpSession.setAttribute("saleInfo",saleInfo);	
		httpSession.setAttribute("signInfo",signInfo);
		httpSession.setAttribute("beginDate",beginDate);	
		httpSession.setAttribute("endDate",endDate);
		httpSession.setAttribute("curTime", endTime);
		return "/mbem/mcrm/house/saleManager/saleReport";
	}

	/**
	 * 进入区间报告搜索页
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:saleManager:saleReportSearch")
	@RequestMapping("/saleReportSearch")
	public String saleReportSearch() {
		return "/mbem/mcrm/house/saleManager/saleReportSearch";
	}
	@RequiresPermissions(value="page:mbem:mcrm:saleManager:saleReportSearch")
	@RequestMapping("/cancleSaleReportSearch")
	public String cancleSaleReportSearch() {
		return "/mbem/mcrm/house/saleManager/saleDetail";
	}
	@RequiresPermissions(value="page:mbem:mcrm:saleManager:buyDetail")
	@RequestMapping("/buyDetail")
	public String buyDetail(@RequestParam(value="type",required=true) String type) {
		log.info(type);
		SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
		String beginDate="";
		String endDate="";
		String dateRange="";
		Calendar cal = Calendar.getInstance();
		switch(type) {
			case "day":
				beginDate = fm1.format(new Date());
				dateRange = beginDate;
				break;
			case "week":
				cal.set(Calendar.DAY_OF_WEEK, Calendar.MONTH);
				beginDate = fm1.format(cal.getTime());
				cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				cal.add(Calendar.WEEK_OF_MONTH, 1);
				endDate = fm1.format(cal.getTime());
				dateRange = beginDate+"至"+endDate;
				break;
			case "month":
				 cal.set(Calendar.DAY_OF_MONTH, 1);
				 beginDate = fm1.format(cal.getTime());
				 cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
				 endDate = fm1.format(cal.getTime());
				 dateRange = beginDate+"至"+endDate;
				break;
			case "year":
				cal.set(Calendar.DAY_OF_YEAR, 1);
				beginDate = fm1.format(cal.getTime());
				cal.set(Calendar.DAY_OF_YEAR, cal.getActualMaximum(Calendar.DAY_OF_YEAR));
				endDate = fm1.format(cal.getTime());
				dateRange = beginDate+"至"+endDate;
				break;
			case "total":
				break;
		}
		log.info("beginDate:"+beginDate+"   endDate:"+endDate);
		httpSession.setAttribute("buyDateRange", dateRange);
		return "/mbem/mcrm/house/saleManager/buyDetail";
	}
	@RequiresPermissions(value="page:mbem:mcrm:saleManager:signUpDetail")
	@RequestMapping("/signUpDetail")
	public String signUpDetail(@RequestParam(value="type",required=true) String type) {
		log.info(type);
		SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
		String beginDate="";
		String endDate="";
		String dateRange="";
		Calendar cal = Calendar.getInstance();
		switch(type) {
			case "day":
				beginDate = fm1.format(new Date());
				dateRange = beginDate;
				break;
			case "week":
				cal.set(Calendar.DAY_OF_WEEK, Calendar.MONTH);
				beginDate = fm1.format(cal.getTime());
				cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				cal.add(Calendar.WEEK_OF_MONTH, 1);
				endDate = fm1.format(cal.getTime());
				dateRange = beginDate+"至"+endDate;
				break;
			case "month":
				 cal.set(Calendar.DAY_OF_MONTH, 1);
				 beginDate = fm1.format(cal.getTime());
				 cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
				 endDate = fm1.format(cal.getTime());
				 dateRange = beginDate+"至"+endDate;
				break;
			case "year":
				cal.set(Calendar.DAY_OF_YEAR, 1);
				beginDate = fm1.format(cal.getTime());
				cal.set(Calendar.DAY_OF_YEAR, cal.getActualMaximum(Calendar.DAY_OF_YEAR));
				endDate = fm1.format(cal.getTime());
				dateRange = beginDate+"至"+endDate;
				break;
			case "total":
				break;
		}
		log.info("beginDate:"+beginDate+"   endDate:"+endDate);
		httpSession.setAttribute("signUpDateRange", dateRange);
		return "/mbem/mcrm/house/saleManager/signUpDetail";
	}
}
