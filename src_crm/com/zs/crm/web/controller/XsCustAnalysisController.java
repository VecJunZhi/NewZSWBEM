package com.zs.crm.web.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zs.common.util.DateUtil;
import com.zs.crm.entity.XsCustAnalysisShowEntity;
import com.zs.crm.entity.XsSaleManagerEntity;
import com.zs.crm.service.XsCustAnalysisService;

@Controller
@RequestMapping(value="/mbem/mcrm/house/custAnalysis")
public class XsCustAnalysisController {
	private Log log=LogFactory.getLog(XsCustAnalysisController.class);
	@Autowired
	XsCustAnalysisService xsCustAnalysisService;
	@Autowired
	HttpSession httpSession;
	
	/**
	 * ���ͷ�������ҳ
	 * @param reportType ��������
	 * @param time ���䱨���ʱ��
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:xs:custAnalysis:index")
	@RequestMapping("/index")
	public String index(@RequestParam(value="reportType",required=true) String reportType,XsSaleManagerEntity time) {
		log.info("reportType==="+reportType);
		SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat fm2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat fm3 = new SimpleDateFormat("MM-dd");
		SimpleDateFormat fm4 = new SimpleDateFormat("yyyy");
		Calendar cal = Calendar.getInstance();
		String beginTime="";
		String endTime=fm2.format(new Date());
		String []callNum = new String[2];
		String []visitNum = new String[2];
		String labelStr = "[";
		String []day = new String[2];
		int num;
		String callStr="";
		String visitStr="";
		String type="";
		switch(reportType) {
			case "week":
				type="����";
				num = 7;
				day = new String[num];
				callNum = new String[num];
				visitNum = new String[num];
				cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				beginTime = fm1.format(cal.getTime())+" 00:00:00";
				time.setBeginTime(beginTime);
				time.setEndTime(endTime);
				time.setGroupBy("day");//�ܱ��������
				for(int i=0;i<num; i++) {
					callNum[i] = "0";
					visitNum[i] = "0";
				}
				day[0] = fm3.format(cal.getTime());
				cal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
				day[1] =  fm3.format(cal.getTime());
				cal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
				day[2] =  fm3.format(cal.getTime());
				cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
				day[3] =  fm3.format(cal.getTime());
				cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
				day[4] =  fm3.format(cal.getTime());
				cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
				day[5] =  fm3.format(cal.getTime());
				cal.add(Calendar.DAY_OF_MONTH, +1);
				day[6] =  fm3.format(cal.getTime());
				for(int i=0; i<day.length; i++)
					labelStr += "\""+day[i]+"\""+",";
				break;
			case "month":
				type="����";
				num = 5;
				day = new String[num];
				callNum = new String[num];
				visitNum = new String[num];
				for(int i=0; i<num; i++) {
					day[i] = i+1+"";
					callNum[i] = "0";
					visitNum[i] = "0";
					labelStr += "\"��"+day[i]+"��\""+",";
				}
				cal.set(Calendar.DAY_OF_MONTH, 1);
				beginTime = fm1.format(cal.getTime())+" 00:00:00";
				time.setBeginTime(beginTime);
				time.setEndTime(endTime);
				time.setGroupBy("week");//�±����ܷ���
				break;
			case "year":
				type="����";
				num = 12;
				day = new String[num];
				callNum = new String[num];
				visitNum = new String[num];
				for(int i=0; i<num; i++) {
					day[i] = i+1+"";
					callNum[i] = "0";
					visitNum[i] = "0";
					labelStr += "\""+day[i]+"\""+",";
				}
				cal.set(Calendar.DAY_OF_YEAR, 1);
				beginTime = fm1.format(cal.getTime())+" 00:00:00";
				time.setBeginTime(beginTime);
				time.setEndTime(endTime);
				time.setGroupBy("month");//�걨���·���
				break;
			case "total":
				type="�ۼ�";
				beginTime = "1970-01-01 00:00:00";
				time.setBeginTime(beginTime);
				time.setEndTime(endTime);
				time.setGroupBy("year");//�ۼư������
				num = 3;
				day = new String[num];
				callNum = new String[num];
				visitNum = new String[num];
				for(int i=0; i<num; i++) {
					callNum[i] = "0";
					visitNum[i] = "0";
				}
				day = new String[num];
				day[2] = fm4.format(new Date());
				cal.add(Calendar.YEAR, -1);
				day[1] = fm4.format(cal.getTime());
				cal.add(Calendar.YEAR, -1);
				day[0] = fm4.format(cal.getTime());
				for(int i=0; i<day.length; i++) {
					labelStr += "\""+day[i]+"��\""+",";
				}
				break;
			case "range":
				try {
					int count;
					int days = DateUtil.dateDiff(time.getBeginTime(), time.getEndTime());
					Date bDate = fm1.parse(time.getBeginTime());
					Date eDate = fm1.parse(time.getEndTime());
					SimpleDateFormat yearFm = new SimpleDateFormat("yyyy");
					SimpleDateFormat monthFm = new SimpleDateFormat("MM");
					String byear = yearFm.format(bDate);
					String eyear = yearFm.format(eDate);
					String bmonth = monthFm.format(bDate);
					String emonth = monthFm.format(eDate);
					if(days >= 366) {
						time.setGroupBy("year");
						count = Integer.parseInt(eyear)-Integer.parseInt(byear)+1;
						day = new String[count];
						for(int j=0; j<count; j++) {
							day[j] = Integer.parseInt(byear)+j+"";
							labelStr += "\""+day[j]+"��\""+",";
						}
					}
					else if(days >= 31) {
						time.setGroupBy("month");
						count = Integer.parseInt(bmonth)<=Integer.parseInt(emonth)?Integer.parseInt(emonth)-Integer.parseInt(bmonth)+1:Integer.parseInt(emonth)-Integer.parseInt(bmonth)+12+1;
						log.info("����"+count+"����");
						day = new String[count];
						for(int j=0; j<count; j++) {
							day[j] = Integer.parseInt(bmonth)+j<=12?Integer.parseInt(bmonth)+j+"":Integer.parseInt(bmonth)+j-12+"";
							labelStr += "\""+day[j]+"\""+",";
						}
						
					}
					else if(days >= 7) {
						time.setGroupBy("week");
						count = days/7+1;
						day = new String[count];
						for(int j=0; j<count; j++) {
							day[j]=j+1+"";
							labelStr += "\"��"+day[j]+"��\""+",";
						}
					}
					else {
						time.setGroupBy("day");
						count = days;
						day = new String[count];
						cal.setTime(bDate);
						for(int j=0; j<count; j++) {
							day[j] = fm3.format(cal.getTime());
							cal.add(Calendar.DAY_OF_YEAR, 1);
							labelStr += "\""+day[j]+"\""+",";
						}//��ʱ����Կ��Բ���
					}
					callNum = new String[count];
					visitNum = new String[count];
					for(int i=0;i<count;i++) {
						callNum[i] = "0";
						visitNum[i] = "0";
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			default:
				break;
		}
		labelStr += "]";
		XsCustAnalysisShowEntity followInfo = xsCustAnalysisService.getCstCountByFollowWay(time);
		XsCustAnalysisShowEntity intentionInfo = xsCustAnalysisService.getCstCountByIntention(time);
		XsCustAnalysisShowEntity statusInfo = xsCustAnalysisService.getCstCountByStatus(time);
		XsCustAnalysisShowEntity allInfo = new XsCustAnalysisShowEntity();
		if(followInfo != null && !followInfo.equals("")) {
			allInfo.setCall(followInfo.getCall());
			allInfo.setVisit(followInfo.getVisit());
		}
		if(intentionInfo != null && !intentionInfo.equals("")) {
			allInfo.setNoIntention(intentionInfo.getNoIntention());
			allInfo.setGeneralIntention(intentionInfo.getGeneralIntention());
			allInfo.setHighIntention(intentionInfo.getHighIntention());
			allInfo.setMustBuy(intentionInfo.getMustBuy());
			allInfo.setIntention(intentionInfo.getNoIntention()+intentionInfo.getGeneralIntention()+intentionInfo.getHighIntention()+intentionInfo.getMustBuy());
		}
		if(statusInfo != null && !statusInfo.equals("")) {
			allInfo.setInvalidCst(statusInfo.getInvalidCst());
			allInfo.setBuyCst(statusInfo.getBuyCst());
			allInfo.setSignUpCst(statusInfo.getSignUpCst());
			allInfo.setConfessCst(statusInfo.getConfessCst());
		}
		
		JSONObject callJs = new JSONObject();
		JSONObject visitJs = new JSONObject();
		JSONArray callJsa = new JSONArray();
		JSONArray visitJsa = new JSONArray();
		time.setGjfs("�ͻ�����");
		List<XsSaleManagerEntity> callGraphList = xsCustAnalysisService.getCallAndVisitInfoByCycle(time);
		time.setGjfs("�ֳ��Ӵ�");
		List<XsSaleManagerEntity> visitGraphList = xsCustAnalysisService.getCallAndVisitInfoByCycle(time);
		callJs.put("name", "����");
		visitJs.put("name", "����");
		if(callGraphList.size() != 0) {
			switch(reportType) {
				case "week":
					for(XsSaleManagerEntity callInfo:callGraphList) {
						log.info(callInfo.getGroupName().substring(5, 10));
						for(int i=0; i< day.length; i++) {
							if(callInfo.getGroupName().substring(6, 10).equals(day[i])) {
								callNum[i] = callInfo.getGroupNum()+""; 
							}
						}
					}
					for(XsSaleManagerEntity visitInfo:visitGraphList) {
						for(int i=0; i< day.length; i++) {
							if(visitInfo.getGroupName().substring(6, 10).equals(day[i])) {
								visitNum[i] = visitInfo.getGroupNum()+"";
							}
						}
					}
					break;
				case "month":
				case "year":
				case "total":
				case "range":
					for(XsSaleManagerEntity callInfo:callGraphList) {
						for(int i=0; i<day.length; i++) {
							if(callInfo.getGroupName().equals(day[i])) {
								callNum[i] = callInfo.getGroupNum()+"";
							}
						}
					}
					for(XsSaleManagerEntity visitInfo:visitGraphList) {
						for(int i=0; i<day.length; i++) {
							if(visitInfo.getGroupName().equals(day[i])) {
								visitNum[i] = visitInfo.getGroupNum()+"";
							}
						}
					}
					break;
			}
		}
		
		callStr = StringUtils.join(callNum,",");
		visitStr = StringUtils.join(visitNum,",");
		callJs.put("value", "["+callStr+"]");
		callJs.put("color", "#3c6");
		callJs.put("line_width", "3");
		callJsa.add(callJs);
		visitJs.put("value", "["+visitStr+"]");
		visitJs.put("color", "#3c6");
		visitJs.put("line_width", "3");
		visitJsa.add(visitJs);
		
		log.info(callJsa.toString());
		log.info(visitJsa.toString());
		httpSession.setAttribute("labelVal", labelStr);
		httpSession.setAttribute("analysisInfo", allInfo);
		httpSession.setAttribute("cstReportType", reportType);
		httpSession.setAttribute("callGraphInfo", callJsa);
		httpSession.setAttribute("visitGraphInfo", visitJsa);
		httpSession.setAttribute("type", type);
		return "/mbem/mcrm/house/custAnalysis/index";
	}
	
	@RequiresPermissions(value="page:mbem:mcrm:xs:custAnalysis:custAnalysisSearch")
	@RequestMapping("/custAnalysisSearch")
	public String custAnalysisSearch() {
		return "/mbem/mcrm/house/custAnalysis/custAnalysisSearch";
	}
	
	public String convertToPercent(double db) {
		DecimalFormat df = new DecimalFormat("0.0");
		String doub=df.format(db*100)+"%";
		return doub;
	}
	/**
	 * 
	 * @param type �ͻ����ͣ����硢���á�����ͻ�����Ч�ͻ����Ϲ��ͻ���
	 * @param reportType �������ͣ��ձ����ܱ����±���
	 * @param time ���䱨���ʱ��
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:xs:custAnalysis:feature")
	@RequestMapping("/feature")
	public String feature(@RequestParam(value="cstType",required=true) String type,@RequestParam(value="reportType",required=true) String reportType,XsSaleManagerEntity time) {
		log.info("type=="+type);
		log.info("reportType=="+reportType);
		List<XsSaleManagerEntity> mediaList = new ArrayList<XsSaleManagerEntity>();
		List<XsSaleManagerEntity> ageList = new ArrayList<XsSaleManagerEntity>();
		List<XsSaleManagerEntity> workList = new ArrayList<XsSaleManagerEntity>();
		SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat fm2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String beginTime="";
		String endTime=fm2.format(new Date());
		String color[] = {"#FCDD72","#48B6CD","#369898","#E66F4F","#CCCCCC","#ED8C61","#FF7713","#FFD47F","#FFFFD4","#2AFF7F","#00FFFF","#5D96C3","#7FFFAA","#7F2AFF","#FCDD72","#48B6CD","#369898","#E66F4F","#CCCCCC","#ED8C61","#FF7713","#FFD47F","#FFFFD4","#2AFF7F","#00FFFF","#5D96C3","#7FFFAA","#7F2AFF"};//��ɫ����
		String cstTypeName="";
		switch(reportType) {
			case "week":
				cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				beginTime = fm1.format(cal.getTime())+" 00:00:00";
				time.setBeginTime(beginTime);
				time.setEndTime(endTime);
				break;
			case "month":
				cal.set(Calendar.DAY_OF_MONTH, 1);
				beginTime = fm1.format(cal.getTime())+" 00:00:00";
				time.setBeginTime(beginTime);
				time.setEndTime(endTime);
				break;
			case "year":
				cal.set(Calendar.DAY_OF_YEAR, 1);
				beginTime = fm1.format(cal.getTime())+" 00:00:00";
				time.setBeginTime(beginTime);
				time.setEndTime(endTime);
				break;
			case "total":
				beginTime = "1970-01-01 00:00:00";
				time.setBeginTime(beginTime);
				time.setEndTime(endTime);
				break;
			case "range":
				beginTime = time.getBeginTime()+" 00:00:00";
				endTime = time.getEndTime()+" 23:59:59";
				break;
			default:
				break;
		}
		switch(type) {
			case "call":
				time.setGjfs("�ͻ�����");//֮ǰ¼�Ķ��ǿͻ����磬�Ժ�¼�Ķ������硢���ã���������������Ƹ���
				cstTypeName = "����ͻ�";
				break;
			case "visit":
				time.setGjfs("�ֳ��Ӵ�");//ͬ��
				cstTypeName =  "���ÿͻ�";
				break;
			case "intention":
				time.setGfyx("intention");
				cstTypeName =  "����ͻ�";
				break;
			case "noIntention":
				time.setGfyx("������");
				cstTypeName =  "������";
				break;
			case "generalIntention":
				time.setGfyx("һ������");
				cstTypeName =  "һ������";
				break;
			case "highIntention":
				time.setGfyx("������");
				cstTypeName =  "������";
				break;
			case "mustBuy":
				time.setGfyx("����");
				cstTypeName =  "����";
				break;
			case "invalidCst":
				time.setStatus("��ʧ");
				cstTypeName =  "��Ч�ͻ�";
				break;
			case "confessCst":
				time.setStatus("�ϳ�");
				cstTypeName =  "�ϳ�ͻ�";
				break;
			case "buyCst":
				time.setStatus("�Ϲ�");
				cstTypeName =  "�Ϲ��ͻ�";
				break;
			case "signUpCst":
				time.setStatus("ǩԼ");
				cstTypeName =  "ǩԼ�ͻ�";
				break;
		}
		JSONObject js = new JSONObject();
		JSONArray mediaPieJsa = new JSONArray();
		JSONArray agePieJsa = new JSONArray();
		JSONArray workPieJsa = new JSONArray();
		
		time.setGroupBy("MainMediaName");//���ȡ��ý�壬�����ΪSubMediaName
		mediaList = xsCustAnalysisService.getCstClassInfoByTypeAndDate(time);//����֪;�������ѯ
		double cstTotal = 0;
		int i=0;
		double totalWidth;
		List<Map<String,String>> mediaBarList = new ArrayList<Map<String,String>>();
		for(XsSaleManagerEntity media:mediaList) {
			cstTotal += media.getGroupNum();
		}
		log.info("total:"+cstTotal);
		if(mediaList.size() == 0) {
			mediaPieJsa = null;
			mediaBarList = null;
		}
		else {
			totalWidth = mediaList.get(0).getGroupNum();
			for(XsSaleManagerEntity media:mediaList) {
				if(media.getGroupName() == null || media.getGroupName().equals("")){
					if(media.getGroupNum() != 0) {
						media.setGroupName("δ��ȷ");
					}else
						continue;
				}
				js.put("name", media.getGroupName());
				js.put("color", color[i]);
				js.put("value", convertToPercent(media.getGroupNum()/cstTotal));
				log.info(js.get("name")+":"+js.get("value"));
				mediaPieJsa.add(js);
				
				Map<String,String> mediaMap = new HashMap<String,String>();
				mediaMap.put("name", media.getGroupName());
				mediaMap.put("value", media.getGroupNum()+"");
				mediaMap.put("width", convertToPercent(media.getGroupNum()/totalWidth));
				mediaMap.put("color", color[i]);
				mediaBarList.add(mediaMap);
				i++;
			}
		}
		//log.info(mediaPieJsa.toString());
		time.setGroupBy("AgeGroup");
		ageList = xsCustAnalysisService.getCstClassInfoByTypeAndDate(time);//������η����ѯ
		cstTotal = 0;
		i = 0;
		List<Map<String,String>> ageBarList = new ArrayList<Map<String,String>>();
		for(XsSaleManagerEntity age:ageList) {
			cstTotal += age.getGroupNum();
		}
		if(ageList.size() == 0) {
			agePieJsa = null;
			ageBarList = null;
		}
		else {
			totalWidth = ageList.get(0).getGroupNum();
			for(XsSaleManagerEntity age:ageList) {
				if(age.getGroupName() == null || age.getGroupName().equals("")){	
					if(age.getGroupNum() != 0) {
						age.setGroupName("δ��ȷ");
					}else
						continue;
				}
				js.put("name", age.getGroupName());
				js.put("color", color[i]);
				js.put("value", convertToPercent(age.getGroupNum()/cstTotal));
				log.info(js.get("name")+":"+js.get("value"));
				agePieJsa.add(js);
				
				Map<String,String> ageMap = new HashMap<String,String>();
				ageMap.put("name", age.getGroupName());
				ageMap.put("value", age.getGroupNum()+"");
				ageMap.put("width", convertToPercent(age.getGroupNum()/totalWidth));
				ageMap.put("color", color[i]);
				ageBarList.add(ageMap);
				i++;
			}
		}
		//log.info(agePieJsa.toString());
		time.setGroupBy("Work");
		workList = xsCustAnalysisService.getCstClassInfoByTypeAndDate(time);//���������ͷ����ѯ
		cstTotal = 0;
		i = 0;
		List<Map<String,String>> workBarList = new ArrayList<Map<String,String>>();
		for(XsSaleManagerEntity work:workList) {
			cstTotal += work.getGroupNum();
		}
		if(workList.size() == 0) {
			workPieJsa = null;
			workBarList = null;
		}
		else {
			totalWidth = workList.get(0).getGroupNum();
			for(XsSaleManagerEntity work:workList) {
				if(work.getGroupName() == null ||work.getGroupName().equals("")) {		
					if(work.getGroupNum() != 0) {
						work.setGroupName("δ��ȷ");
					}else
						continue;
				}
				js.put("name", work.getGroupName());
				js.put("color", color[i]);
				js.put("value", convertToPercent(work.getGroupNum()/cstTotal));
				log.info(js.get("name")+":"+js.get("value"));
				workPieJsa.add(js);
				
				Map<String,String> workMap = new HashMap<String,String>();
				workMap.put("name", work.getGroupName());
				workMap.put("value", work.getGroupNum()+"");
				workMap.put("width", convertToPercent(work.getGroupNum()/totalWidth));
				workMap.put("color", color[i]);
				workBarList.add(workMap);
				i++;
			}
		}
		//log.info(workPieJsa.toString());
		httpSession.setAttribute("mediaPieInfo", mediaPieJsa);
		httpSession.setAttribute("mediaBarList", mediaBarList);
		httpSession.setAttribute("agePieInfo", agePieJsa);
		httpSession.setAttribute("ageBarList", ageBarList);
		httpSession.setAttribute("workPieInfo", workPieJsa);
		httpSession.setAttribute("workBarList", workBarList);
		
		httpSession.setAttribute("cstType", type);
		httpSession.setAttribute("reportType", reportType);
		httpSession.setAttribute("cstCount", (int)cstTotal);
		httpSession.setAttribute("cstTypeName", cstTypeName);

		return "/mbem/mcrm/house/custAnalysis/feature";
	}
	
	@RequestMapping("/featureSearch")
	public String featureSearch() {
		return "/mbem/mcrm/house/custAnalysis/featureSearch";
	}
	
	@RequestMapping("/test")
	public String test() {
		//String str = "["+"{'name':'A','value':'25','color':'#ccff66'},"+"{'name':'B','value':'25','color':'#77ff66'},"+"]";
		String str = "["+"{name:'A',value:'25%',color:'#ccff66'},"+"{name:'B',value:'75%',color:'#77ff66'},"+"]";
		JSONArray js = JSONArray.fromObject(str);
		log.info("js"+js);
		httpSession.setAttribute("data", js);
		return "/mbem/mcrm/house/custAnalysis/test";
	}
}
