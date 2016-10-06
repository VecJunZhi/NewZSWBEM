package com.zs.crm.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zs.busi.utils.LogUtil;
import com.zs.common.util.DateUtil;
import com.zs.crm.entity.XsCustomersManagerEntity;
import com.zs.crm.entity.XsPerformanceEntity;
import com.zs.crm.service.XsCustomAllocateService;
import com.zs.crm.service.XsPerformanceService;
import com.zs.crm.web.vo.XsCustomersManagerVo;
import com.zs.crm.web.vo.XsPerformanceVo;

@Controller
@RequestMapping(value="/mbem/mcrm/house")
public class XsPerformanceController {
	
	static Log log=LogUtil.getLog();
	@Autowired 
	XsPerformanceService xsPerformanceService;
	@Autowired 
	XsCustomAllocateService xsBusiCustomAllocateService;
	@Autowired
	HttpSession httpSession;
	@Autowired
	XsInfoAction xsInfoAction;
	
//Ԥ���¶�
//�ͻ������� ���ڿͻ�/�����ͻ� ��ͳ����
	@RequiresPermissions(value="page:mbem:mcrm:xs:sale:index")
	@RequestMapping(value="/saleManager/index")
	public String getBudgetedPerformanceByDateTime(){
		String endTime=DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss");//��ǰʱ��
		String startTime=DateUtil.getCurrentTime("yyyy-MM")+"-01 00:00:00";//��ǰ����ʼ����
		//Ԥ���¶�ͳ��
		XsPerformanceEntity budgetMonth=xsPerformanceService.getBudgetedPerformanceByDateTime(startTime, endTime);
		log.info(budgetMonth==null);
		if(budgetMonth==null){
			budgetMonth=new XsPerformanceEntity(0, "0", "0", "0");
		}
		httpSession.setAttribute("budgetMonth", budgetMonth);
		//�ͻ������ͳ����
		XsCustomersManagerVo zsinfo=new XsCustomersManagerVo();
		int overdueCsNum = xsBusiCustomAllocateService.getZsCustCountDao("���ڿͻ�",zsinfo);
		int publicCsNum = xsBusiCustomAllocateService.getZsCustPubDupCountDao("�����ͻ�",zsinfo);
		httpSession.setAttribute("overdueCsNum", overdueCsNum);
		httpSession.setAttribute("publicCsNum", publicCsNum);
		//�����ձ�ͳ����
		xsInfoAction.getCountRoom(httpSession);
		xsInfoAction.getCountTelAccess(httpSession);
		return "/mbem/mcrm/house/saleManager/index";
		
		
	}
	
//Ԥ�㼾�Ⱥ����
//�Ŷ�����
//�����������
	@RequiresPermissions(value="page:mbem:mcrm:xs:sale:budgetEdit")
	@RequestMapping(value="/salePerformance/budgetEdit")
	public String getBudgetedPerformanceByDateTimes(){
		String startTime=DateUtil.getCurrentTime("yyyy-MM")+"-01 00:00:00";//��ǰ����ʼ����
		String endTime=DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss");//��ǰʱ��
		int season=getSeasonByMonth(Integer.parseInt(DateUtil.getCurrentTime("MM")));
		int year=Integer.parseInt(DateUtil.getCurrentTime("yyyy"));
		XsPerformanceVo seasonStartEnd=getSeasonStartEnd(year, season);
		//���Ԥ�����������������ͳ����Ϣ��
		XsPerformanceEntity budgetSeason=xsPerformanceService.getBudgetedPerformanceByDateTime(seasonStartEnd.getStartTime(), seasonStartEnd.getEndTime());
		XsPerformanceEntity budgetYear=xsPerformanceService.getBudgetedPerformanceByDateTime(year+"-01-01 00:00:00",endTime);
		log.info(budgetSeason==null);
		if(budgetSeason==null){
			budgetSeason=new XsPerformanceEntity(0, "0", "0", "0");
		}
		log.info(budgetYear==null);
		if(budgetYear==null){
			budgetYear=new XsPerformanceEntity(0, "0", "0", "0");
		}
		httpSession.setAttribute("budgetSeason", budgetSeason);
		httpSession.setAttribute("budgetYear", budgetYear);
		//�Ŷ��¶�ҵ�����У��������۶�����
		String orderWord="salesAmount";//�������۶�����
		List<XsPerformanceEntity> list_bymoney=xsPerformanceService.getTeamMonthPerformanceRanking(startTime, endTime,orderWord);
		httpSession.setAttribute("TeamMonthPerformanceRankingByMoney", list_bymoney);
		//�����¶�ҵ�����У��������۶�����
		List<XsPerformanceEntity> list_ywy_bymoney=xsPerformanceService.getYWYMonthPerformanceRanking(startTime, endTime,orderWord);
		httpSession.setAttribute("YWYMonthPerformanceRankingByMoney", list_ywy_bymoney);
		//�Ŷ��¶�ҵ�����У�������������
		orderWord="ts";//������������
		List<XsPerformanceEntity> list_byts=xsPerformanceService.getTeamMonthPerformanceRanking(startTime, endTime,orderWord);
		httpSession.setAttribute("TeamMonthPerformanceRankingByTs", list_byts);
		//�����¶�ҵ�����У��������۶�����
		List<XsPerformanceEntity> list_ywy_byts=xsPerformanceService.getYWYMonthPerformanceRanking(startTime, endTime,orderWord);
		httpSession.setAttribute("YWYMonthPerformanceRankingByTs", list_ywy_byts);
		
		return "/mbem/mcrm/house/salePerformance/budgetEdit";
	}
	
//�ͻ���������������Ч�ͻ���ͳ����
	@RequiresPermissions(value="page:mbem:mcrm:xs:customer:index")
	@RequestMapping(value="/customer/index")
	public String getCustomerAllocateData(){
		//��Ч�ͻ�ͳ����
		XsCustomersManagerVo zsinfo=new XsCustomersManagerVo();
		int disbinCsNum = xsBusiCustomAllocateService.getZsCustCountDao("��Ч�ͻ�",zsinfo);
		httpSession.setAttribute("disbinCsNum", disbinCsNum);
		//�����пͻ�ͳ����
		int followCs = xsBusiCustomAllocateService.getZsCustCountDao("�����пͻ�",zsinfo);
		httpSession.setAttribute("followCs", followCs);
		return "/mbem/mcrm/house/customer/index";
	}
//ÿ��ҵ��Ա�����ڿͻ�ͳ���� �����ڿͻ���ϸ
	@RequiresPermissions(value="page:mbem:mcrm:xs:customer:index")
	@RequestMapping(value="/saleManager/listtype")
	public String getOverdueCustomerDetailData(){
		List<XsCustomersManagerEntity> overduelistByYWY=xsBusiCustomAllocateService.getZsOverdueCustCountByYwy();
		httpSession.setAttribute("overduelistByYWY", overduelistByYWY);
		Map<String, Object> map =new HashMap<String,Object>();
		for (XsCustomersManagerEntity xsCustomersManagerEntity : overduelistByYWY) {
			String userGuid=xsCustomersManagerEntity.getUserGuid();
			log.info(userGuid);
			XsCustomersManagerVo vo=new XsCustomersManagerVo();
			vo.setUserGuid(userGuid);
			List<XsCustomersManagerEntity> overdueCustByYWY=xsBusiCustomAllocateService.getZsOverdueCustInfoDao(vo);
			//httpSession.setAttribute("overdueCustByYWY", overdueCustByYWY);
			map.put(userGuid, overdueCustByYWY);
		}
		httpSession.setAttribute("overdueCustByYWY", map);
		return "/mbem/mcrm/house/saleManager/listtype";
	}
//ÿ��ҵ��Ա����Ч�ͻ�ͳ���� ����Ч�ͻ���ϸ
	@RequiresPermissions(value="page:mbem:mcrm:xs:customer:index")
	@RequestMapping(value="/saleManager/invalidCustomType")
	public String getInvalidCustomerDetailData(){
		List<XsCustomersManagerEntity> overduelistByYWY=xsBusiCustomAllocateService.getZsCusInvalidCustCountByYwy();
		httpSession.setAttribute("invalidlistByYWY", overduelistByYWY);
		Map<String, Object> map =new HashMap<String,Object>();
		for (XsCustomersManagerEntity xsCustomersManagerEntity : overduelistByYWY) {
			String userGuid=xsCustomersManagerEntity.getUserGuid();
			log.info(userGuid);
			XsCustomersManagerVo vo=new XsCustomersManagerVo();
			vo.setUserGuid(userGuid);
			List<XsCustomersManagerEntity> overdueCustByYWY=xsBusiCustomAllocateService.getZsCusInvalidDao(vo);
			//httpSession.setAttribute("overdueCustByYWY", overdueCustByYWY);
			map.put(userGuid, overdueCustByYWY);
		}
		httpSession.setAttribute("invalidCustByYWY", map);
		return "/mbem/mcrm/house/saleManager/invalidCustomType";
	}
//ÿ��ҵ��Ա�ĸ����пͻ�ͳ���� �����пͻ���ϸ
	@RequiresPermissions(value="page:mbem:mcrm:xs:customer:index")
		@RequestMapping(value="/saleManager/followingCustomerType")
		public String getFollowingDetailData(){
			List<XsCustomersManagerEntity> overduelistByYWY=xsBusiCustomAllocateService.getZsCustFollowingCustCountByYwy();
			httpSession.setAttribute("followinglistByYWY", overduelistByYWY);
			Map<String, Object> map =new HashMap<String,Object>();
			for (XsCustomersManagerEntity xsCustomersManagerEntity : overduelistByYWY) {
				String userGuid=xsCustomersManagerEntity.getUserGuid();
				log.info(userGuid);
				XsCustomersManagerVo vo=new XsCustomersManagerVo();
				vo.setUserGuid(userGuid);
				List<XsCustomersManagerEntity> overdueCustByYWY=xsBusiCustomAllocateService.getZsCustFollowingDao(vo);
				//httpSession.setAttribute("overdueCustByYWY", overdueCustByYWY);
				map.put(userGuid, overdueCustByYWY);
			}
			httpSession.setAttribute("followingCustByYWY", map);
			return "/mbem/mcrm/house/saleManager/followingCustomerType";
		}
//�����ͻ���ϸ
	@RequestMapping(value="/saleManager/publicCustomType")
	@RequiresPermissions(value="page:mbem:mcrm:xs:customer:index")
	public String getPublicDetailData(){
		XsCustomersManagerVo vo=new XsCustomersManagerVo();
		List<XsCustomersManagerEntity> overduelistByYWY=xsBusiCustomAllocateService.getZsCustPublicDao(vo);
		httpSession.setAttribute("publiclistByYWY", overduelistByYWY);
		return "/mbem/mcrm/house/saleManager/publicCustomType";
	}
//�����ͻ���ϸ
	@RequiresPermissions(value="page:wbem:crm:xs:custAssignOverdue")
	@RequestMapping(value="/saleManager/duspinCustomType")
	public String getDuspinDetailData(){
		log.info("hello");
		XsCustomersManagerVo vo=new XsCustomersManagerVo();
		List<XsCustomersManagerEntity> overduelistByYWY=xsBusiCustomAllocateService.getZsCustDusBinDao(vo);
		httpSession.setAttribute("duspinlistByYWY", overduelistByYWY);
		return "/mbem/mcrm/house/saleManager/duspinCustomType";
	}
		
//*****************************��������***************************************************//
	//�����������ҳ��
	@RequiresPermissions(value="page:mbem:mcrm:xs:custAnalysis:followReport")
	@RequestMapping(value="/custAnalysis/followReport")
	public String followReport(@RequestParam(value="flg", required=true) String flg,String startTime,String endTime){
		log.info(flg+" "+startTime+" "+endTime);
		XsPerformanceEntity totalcount=null;
		List<XsPerformanceEntity> grouplist=null;
		List<XsPerformanceEntity> ywy_grouplist=null;
		
		if("alltime".equals(flg)){
			XsPerformanceVo todayvo=getTodayStartEnd();
			totalcount=xsPerformanceService.followAnalysisByTotalCount("1900-01-01",todayvo.getEndTime());
			grouplist=xsPerformanceService.followAnalysisByGroup("1900-01-01",todayvo.getEndTime());
			ywy_grouplist=xsPerformanceService.followAnalysisByYwy("1900-01-01",todayvo.getEndTime());
		}else if("yesterday".equals(flg)){
			XsPerformanceVo yesterdayvo=getYesterdayStartEnd();
			totalcount=xsPerformanceService.followAnalysisByTotalCount(yesterdayvo.getStartTime(),yesterdayvo.getEndTime());
			grouplist=xsPerformanceService.followAnalysisByGroup(yesterdayvo.getStartTime(),yesterdayvo.getEndTime());
			ywy_grouplist=xsPerformanceService.followAnalysisByYwy(yesterdayvo.getStartTime(),yesterdayvo.getEndTime());
		}else if("today".equals(flg)){
			XsPerformanceVo todayvo=getTodayStartEnd();
			totalcount=xsPerformanceService.followAnalysisByTotalCount(todayvo.getStartTime(),todayvo.getEndTime());
			grouplist=xsPerformanceService.followAnalysisByGroup(todayvo.getStartTime(),todayvo.getEndTime());
			ywy_grouplist=xsPerformanceService.followAnalysisByYwy(todayvo.getStartTime(),todayvo.getEndTime());
		}else if("curweek".equals(flg)){
			XsPerformanceVo curWeekvo=getCurWeekStartEnd();
			totalcount=xsPerformanceService.followAnalysisByTotalCount(curWeekvo.getStartTime(),curWeekvo.getEndTime());
			grouplist=xsPerformanceService.followAnalysisByGroup(curWeekvo.getStartTime(),curWeekvo.getEndTime());
			ywy_grouplist=xsPerformanceService.followAnalysisByYwy(curWeekvo.getStartTime(),curWeekvo.getEndTime());
		}else if("curmonth".equals(flg)){
			XsPerformanceVo curMonthvo=getCurMonthStartEnd();
			totalcount=xsPerformanceService.followAnalysisByTotalCount(curMonthvo.getStartTime(),curMonthvo.getEndTime());
			grouplist=xsPerformanceService.followAnalysisByGroup(curMonthvo.getStartTime(),curMonthvo.getEndTime());
			ywy_grouplist=xsPerformanceService.followAnalysisByYwy(curMonthvo.getStartTime(),curMonthvo.getEndTime());
		}else if("zdy".equals(flg)){
			totalcount=xsPerformanceService.followAnalysisByTotalCount(startTime,endTime);
			grouplist=xsPerformanceService.followAnalysisByGroup(startTime,endTime);
			ywy_grouplist=xsPerformanceService.followAnalysisByYwy(startTime,endTime);
		}
		//log.info(totalcount==null);
		if(totalcount==null){
			totalcount=new XsPerformanceEntity();
			totalcount.setFromTel("0");
			totalcount.setFromInterview("0");
		}
		httpSession.setAttribute("flg", flg);
		httpSession.setAttribute("totalcount", totalcount);
		httpSession.setAttribute("grouplist", grouplist);
		httpSession.setAttribute("ywy_grouplist", ywy_grouplist);
		return "/mbem/mcrm/house/custAnalysis/followReport";
	}
	@RequiresPermissions(value="page:mbem:mcrm:xs:custAnalysis:followReport")
	@RequestMapping(value="/custAnalysis/followReportzdy")
	public String followReportzdy(){
		return "/mbem/mcrm/house/custAnalysis/followReportzdy";
	}
	//�����������ҳ��--�ɽ�����
	@RequiresPermissions(value="page:mbem:mcrm:xs:custAnalysis:dealReport")
	@RequestMapping(value="/custAnalysis/dealReport")
	public String dealReport(@RequestParam(value="flg", required=true) String flg,String startTime,String endTime){
		log.info(flg);
		XsPerformanceEntity totalcount=null;
		XsPerformanceEntity yqts=null;
		List<XsPerformanceEntity> grouplist=null;
		List<XsPerformanceEntity> ywy_grouplist=null;
		if("alltime".equals(flg)){
			XsPerformanceVo todayvo=getTodayStartEnd();
			yqts=xsPerformanceService.dealAnalysis_yqts("1900-01-01",todayvo.getEndTime());
			totalcount=xsPerformanceService.dealAnalysisByTotalCount("1900-01-01",todayvo.getEndTime());
			grouplist=xsPerformanceService.dealAnalysisByGroup("1900-01-01",todayvo.getEndTime());
			ywy_grouplist=xsPerformanceService.dealAnalysisByYwy("1900-01-01",todayvo.getEndTime());
		}else if("yesterday".equals(flg)){
			XsPerformanceVo yesterdayvo=getYesterdayStartEnd();
			yqts=xsPerformanceService.dealAnalysis_yqts(yesterdayvo.getStartTime(),yesterdayvo.getEndTime());
			totalcount=xsPerformanceService.dealAnalysisByTotalCount(yesterdayvo.getStartTime(),yesterdayvo.getEndTime());
			grouplist=xsPerformanceService.dealAnalysisByGroup(yesterdayvo.getStartTime(),yesterdayvo.getEndTime());
			ywy_grouplist=xsPerformanceService.dealAnalysisByYwy(yesterdayvo.getStartTime(),yesterdayvo.getEndTime());
		}else if("today".equals(flg)){
			XsPerformanceVo todayvo=getTodayStartEnd();
			yqts=xsPerformanceService.dealAnalysis_yqts(todayvo.getStartTime(),todayvo.getEndTime());
			totalcount=xsPerformanceService.dealAnalysisByTotalCount(todayvo.getStartTime(),todayvo.getEndTime());
			grouplist=xsPerformanceService.dealAnalysisByGroup(todayvo.getStartTime(),todayvo.getEndTime());
			ywy_grouplist=xsPerformanceService.dealAnalysisByYwy(todayvo.getStartTime(),todayvo.getEndTime());
		}else if("curweek".equals(flg)){
			XsPerformanceVo curWeekvo=getCurWeekStartEnd();
			yqts=xsPerformanceService.dealAnalysis_yqts(curWeekvo.getStartTime(),curWeekvo.getEndTime());
			totalcount=xsPerformanceService.dealAnalysisByTotalCount(curWeekvo.getStartTime(),curWeekvo.getEndTime());
			grouplist=xsPerformanceService.dealAnalysisByGroup(curWeekvo.getStartTime(),curWeekvo.getEndTime());
			ywy_grouplist=xsPerformanceService.dealAnalysisByYwy(curWeekvo.getStartTime(),curWeekvo.getEndTime());
		}else if("curmonth".equals(flg)){
			XsPerformanceVo curMonthvo=getCurMonthStartEnd();
			yqts=xsPerformanceService.dealAnalysis_yqts(curMonthvo.getStartTime(),curMonthvo.getEndTime());
			totalcount=xsPerformanceService.dealAnalysisByTotalCount(curMonthvo.getStartTime(),curMonthvo.getEndTime());
			grouplist=xsPerformanceService.dealAnalysisByGroup(curMonthvo.getStartTime(),curMonthvo.getEndTime());
			ywy_grouplist=xsPerformanceService.dealAnalysisByYwy(curMonthvo.getStartTime(),curMonthvo.getEndTime());
		}else{
			yqts=xsPerformanceService.dealAnalysis_yqts(startTime,endTime);
			totalcount=xsPerformanceService.dealAnalysisByTotalCount(startTime,endTime);
			grouplist=xsPerformanceService.dealAnalysisByGroup(startTime,endTime);
			ywy_grouplist=xsPerformanceService.dealAnalysisByYwy(startTime,startTime);
		}
		//log.info(totalcount==null);
		if(totalcount==null){
			totalcount=new XsPerformanceEntity();
			totalcount.setRgts("0");
			totalcount.setQyts("0");
		}
		if(yqts ==null){
			totalcount.setYqts("0");
		}else{
			totalcount.setYqts(yqts.getYqts());
		}
		log.info(yqts.getYqts());
		httpSession.setAttribute("del_flg", flg);
		httpSession.setAttribute("totalcount", totalcount);
		httpSession.setAttribute("grouplist", grouplist);
		httpSession.setAttribute("ywy_grouplist", ywy_grouplist);
		return "/mbem/mcrm/house/custAnalysis/dealReport";
	}
	/**
	 * ����������--ͨ����ҵ������ϸ����
	 * @param vo
	 * @return
	 */
	/*public String followAnalysisByYwy(){
		
	};*/
	/**
	 * ��������--ͨ����������
	 * @param vo
	 * @return
	 */
	//public String followAnalysisByGroup(){
		
	//};	
	
	
	
	
	
	
	
	
//�����·��ж������ĸ�����
	public static int getSeasonByMonth(int month){
		int quarter=0;
		if(month>=1&&month<=3){     
	        quarter=1;     
	    }     
	    if(month>=4&&month<=6){     
	        quarter=2;       
	    }     
	    if(month>=7&&month<=9){     
	        quarter =3;     
	    }     
	    if(month>=10&&month<=12){     
	        quarter = 4;     
	    }
	    return quarter;
		
	}
	public static XsPerformanceVo getYesterdayStartEnd(){
		String currday=DateUtil.getCurrentTime("yyyy-MM-dd");
		String yestday=DateUtil.addDate(currday, -1);
		String start=yestday+" 00:00:00";
		String end=yestday+" 23:59:59";
		log.info(start+" "+end);
		XsPerformanceVo vo = new XsPerformanceVo(start, end);
		return vo;
	}
	public static XsPerformanceVo getTodayStartEnd(){
		String currday=DateUtil.getCurrentTime("yyyy-MM-dd");
		String start=currday+" 00:00:00";
		String end=currday+" 23:59:59";
		log.info(start+" "+end);
		//XsPerformanceVo vo = new XsPerformanceVo("2015-01-01", "2015-12-12");//����
		XsPerformanceVo vo = new XsPerformanceVo(start, start);
		return vo;
	}
	public static XsPerformanceVo getCurWeekStartEnd(){
		String currday=DateUtil.getFirstDayOfWeek("yyyy-MM-dd");
		String start=currday+" 00:00:00";
		String end=DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss");
		log.info(start+" "+end);
		XsPerformanceVo vo = new XsPerformanceVo(start, end);
		return vo;
	}
	public static XsPerformanceVo getCurMonthStartEnd(){
		String ym=DateUtil.getCurrentTime("yyyy-MM");
		String start=ym+"-01 00:00:00";
		String end=DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss");
		log.info(start+" "+end);
		XsPerformanceVo vo = new XsPerformanceVo(start, end);
		return vo;
	}
	
//���ݼ��Ȼ�ȡ�ü��ȵ���ʼ���ںͽ�������
	public static XsPerformanceVo getSeasonStartEnd(int year,int season){
		XsPerformanceVo vo = null;
		switch (season){
			case 1:
				vo=new XsPerformanceVo(year+"-01-01 00:00:00", year+"-03-31 23:59:59");
				break;
			case 2:
				vo=new XsPerformanceVo(year+"-04-01 00:00:00", year+"-06-30 23:59:59");
				break;
			case 3:
				vo=new XsPerformanceVo(year+"-07-01 00:00:00", year+"-09-30 23:59:59");
				break;
			case 4:
				vo=new XsPerformanceVo(year+"-10-01 00:00:00", year+"-12-31 23:59:59");
				break;
		}
		return vo;
	}
public static void main(String[] args) {
	//log.info(getTodayStartEnd());
	//log.info(getYesterdayStartEnd());
	log.info(getCurWeekStartEnd());
	log.info(getCurMonthStartEnd());
	/*String start=DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss");
	String e=DateUtil.getCurrentTime("yyyy-MM")+"-01 00:00:00";
	log.info(start);
	log.info(e);
	DateUtil.getCurrentTime("MM");
	DateUtil.getCurrentTime("yyyy");
	log.info(Integer.parseInt(DateUtil.getCurrentTime("MM")));
	log.info(Integer.parseInt(DateUtil.getCurrentTime("yyyy")));
	int season=getSeasonByMonth(Integer.parseInt(DateUtil.getCurrentTime("MM")));
	XsPerformanceVo seasonStartEnd=getSeasonStartEnd(Integer.parseInt(DateUtil.getCurrentTime("yyyy")), season);
	log.info(seasonStartEnd.getStartTime());
	log.info(seasonStartEnd.getEndTime());*/
}
}
