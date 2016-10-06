package com.zs.busi.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.busi.entity.ZsBusiCustomAllocateEntity;
import com.zs.busi.entity.ZsCustomReportEntity;
import com.zs.busi.entity.ZsCustomReportSearchVo;
import com.zs.busi.service.ZsCustomReportService;
import com.zs.common.entity.DataTablesParameters;
import com.zs.common.util.RequestUtil;
import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.rbac.core.RBACSubject;
import com.zs.rbac.entity.User;
import com.zs.rbac.service.XsTeamGroupService;
import com.zs.crm.web.vo.XsTeamGroupVo;

@Controller
@RequestMapping(value="/wbem/business/analysis")
public class ZsCustomReportController {
	@Autowired
	ZsCustomReportService zsCustomReportService;
	@Autowired
	XsTeamGroupService xsTeamGroupService;
	
	@RequiresPermissions(value="page:countZS:report:index")
	@RequestMapping("indexReport")
	public String indexReport(@RequestParam(value="reportType",required=false)String reportType,/*@RequestParam(value="proId",required=false)String proId,*/ZsCustomReportSearchVo time,Model model) {
		if(reportType == null || reportType.equals("")) {
			reportType = "dayReport";
		}
		/*if(proId == null || proId.equals("")) {
			proId = "8FFF2136-61EA-E411-BAAF-FCAA145C42F2";
		}*/
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		ZsCustomReportSearchVo searchOption = new ZsCustomReportSearchVo();
		searchOption.setProjGuid(projGuid);
		String startDate="";
		String endDate="";
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		switch(reportType) {
			case "dayReport":
				startDate = fm.format(cal.getTime());
				endDate = fm.format(cal.getTime());
				break;
			case "weekReport":
				int day = cal.get(Calendar.DAY_OF_WEEK);
				System.out.println(day+"day");
				if(day == 1) {
					endDate = fm.format(cal.getTime());
					cal.add(Calendar.DAY_OF_MONTH, -6);
					startDate = fm.format(cal.getTime());
				}else {
					cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
					startDate = fm.format(cal.getTime());
					cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
					cal.add(Calendar.WEEK_OF_MONTH, 1);
					endDate = fm.format(cal.getTime());
				}
				break;
			case "monthReport":
				cal.set(Calendar.DAY_OF_MONTH, 1);
				startDate = fm.format(cal.getTime());
				cal.set(Calendar.DATE,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
				endDate = fm.format(cal.getTime());
				break;
			case "rangeReport":
				startDate = time.getStartTime();
				endDate = time.getEndTime();
				if(startDate == null || startDate.equals("")){
					startDate = fm.format(cal.getTime());
				}
				if(endDate == null || endDate.equals("")){
					endDate = fm.format(cal.getTime());
				}
				break;
		}
		searchOption.setStartTime(startDate+" 00:00:00");
		searchOption.setEndTime(endDate+" 23:59:59");
		
		searchOption.setGjfs("来电");
		int callNum = zsCustomReportService.getCustomCountByDateAndWay(searchOption);
		searchOption.setGjfs("去电");
		int toCallNum = zsCustomReportService.getCustomCountByDateAndWay(searchOption);
		searchOption.setGjfs("来访");
		int visitNum = zsCustomReportService.getCustomCountByDateAndWay(searchOption);
		searchOption.setFirst("first");
		int firstVisitNum = zsCustomReportService.getCustomCountByDateAndWay(searchOption);
		searchOption.setGjfs("来电");
		int firsCallNum = zsCustomReportService.getCustomCountByDateAndWay(searchOption);

		List<XsTeamGroupEntity> projectList = xsTeamGroupService.getProjectItem();
		model.addAttribute("projectList", projectList);
		XsTeamGroupVo searchInfo = new XsTeamGroupVo();
		searchInfo.setGroupType("zs");
		searchInfo.setUserLevelId("2");
		searchInfo.setProperty("bs_userGuid");
		searchInfo.setProjectId(projGuid);
		List<XsTeamGroupEntity> zygwList = xsTeamGroupService.getUserFromTeamGroup(searchInfo);
		model.addAttribute("zygwList", zygwList);
		model.addAttribute("call",callNum);
		model.addAttribute("firstCall",firsCallNum);
		model.addAttribute("toCall", toCallNum);
		model.addAttribute("visit", visitNum);
		model.addAttribute("firstVisit",firstVisitNum);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		System.out.println("reportType:"+reportType);
		model.addAttribute("reportType", reportType);
		return "/wbem/business/analysis/indexReport";
	}
	
	@RequiresPermissions(value="page:countZS:report:index")
	@RequestMapping("loadAnalysisData")
	@ResponseBody
	public Map<String,Object> loadAnalysisDate(HttpServletRequest request,/*String proId,*/String gjfs,String startTime,String endTime,String zygw,String telOrName) throws UnsupportedEncodingException {
		DataTablesParameters para= RequestUtil.getDTPara(request);
		ZsCustomReportSearchVo searchOption = new ZsCustomReportSearchVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		searchOption.setStartIndex(para.getStart());
		searchOption.setLength(para.getLength());
		searchOption.setProjGuid(projGuid);
		searchOption.setUserId(zygw);
		if(gjfs!=null && !"".equals(gjfs)) {
			gjfs = URLDecoder.decode(gjfs, "utf-8");
			if(gjfs.indexOf("首次") != -1) {
				searchOption.setFirst("first");
				gjfs = gjfs.substring(2);
			}
		}
		searchOption.setGjfs(gjfs);
		searchOption.setStartTime(startTime+" 00:00:00");
		searchOption.setEndTime(endTime+ " 23:59:59");
		if(telOrName !=null && !"".equals(telOrName)){//根据客户名称和电话模糊查询
			telOrName = URLDecoder.decode(telOrName, "utf-8");
			boolean isNum = telOrName.matches("[0-9]+");
			if(isNum){
				searchOption.setMobileTel(telOrName);
			}else{
				searchOption.setCstName(telOrName);;
			}
		}
		int customNum = zsCustomReportService.getCustomCountByDateAndWay(searchOption);
		
		List<ZsCustomReportEntity> customList = new ArrayList<ZsCustomReportEntity>();
		customList = zsCustomReportService.getCustomInfoByDateAndWay(searchOption);
		System.out.println("size:"+customList.size());
		List<Map<String,String>> dataList = new ArrayList<Map<String,String>>();		
		int i = para.getStart()+1;
		for(ZsCustomReportEntity custom:customList) {
			Map<String,String> custInfo = new HashMap<String,String>();
			custInfo.put("index",i+"");
			custInfo.put("cstName",custom.getCstName());
			custInfo.put("mobileTel",custom.getMobileTel());
			custInfo.put("oppSource",custom.getOppSource());
			custInfo.put("homeArea", custom.getHomeArea());
			custInfo.put("zygw", custom.getZygw());
			custInfo.put("team",custom.getTeam());
			custInfo.put("status", custom.getStatus());
			custInfo.put("lastDate",custom.getLastDate());
			custInfo.put("gjfs",custom.getGjfs());
			i++;
			dataList.add(custInfo);
		}
		Map<String,Object> jsonResult = new HashMap<String,Object>();
		jsonResult.put("draw", para.getDraw());
		jsonResult.put("recordsTotal", customNum);
		jsonResult.put("recordsFiltered", customNum);
		jsonResult.put("data",dataList);
		return jsonResult;		
	}
}
