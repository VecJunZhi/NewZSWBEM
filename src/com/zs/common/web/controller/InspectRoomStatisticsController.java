package com.zs.common.web.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.common.entity.BuildingTableEntity;
import com.zs.common.entity.InspectRoomStatisticsInfoVo;
import com.zs.common.service.InspectRoomStatisticsService;
import com.zs.rbac.core.RBACSubject;
import com.zs.rbac.entity.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/wbem/inspect")
public class InspectRoomStatisticsController {
	private Log log=LogFactory.getLog(InspectRoomStatisticsController.class);
	@Autowired
	InspectRoomStatisticsService statisticsService;
	
	@RequiresPermissions(value="page:inspectReport:inspectStatistics:index")
	@RequestMapping("/inspectStatistics")
	public String inspectStatistics(Model model){
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		List<InspectRoomStatisticsInfoVo> typeStatisticsListInfo = new ArrayList<InspectRoomStatisticsInfoVo>();
		List<InspectRoomStatisticsInfoVo> statusStatisticsListInfo = new ArrayList<InspectRoomStatisticsInfoVo>();
		List<InspectRoomStatisticsInfoVo> positionStatisticsListInfo = new ArrayList<InspectRoomStatisticsInfoVo>();
		List<BuildingTableEntity> bldList = new ArrayList<BuildingTableEntity>();
		bldList = statisticsService.getBldInfoListByProj(projGuid);
		//String bldGuid = bldList.get(0).getBldGuid();
		typeStatisticsListInfo = statisticsService.getIssuesTypeStatisticsByBld(projGuid);
		statusStatisticsListInfo = statisticsService.getIssuesStatusStatisticsByBld(projGuid);
		positionStatisticsListInfo = statisticsService.getIssuesPositionStatisticsByBld(projGuid);
		
		JSONArray typeStatisticsJsonArray = new JSONArray();
		if(typeStatisticsListInfo.size() == 0) {
			JSONObject issueStatisticsJson = new JSONObject();
			issueStatisticsJson.put("name", "暂无数据");
			issueStatisticsJson.put("value", "......");
			typeStatisticsJsonArray.add(issueStatisticsJson);
		} else {
			for(InspectRoomStatisticsInfoVo statisticsInfo:typeStatisticsListInfo) {
				JSONObject issueStatisticsJson = new JSONObject();
				issueStatisticsJson.put("name", statisticsInfo.getIssueType());
				issueStatisticsJson.put("value", statisticsInfo.getIssueNum());
				log.info(statisticsInfo.getIssueType()+"============="+statisticsInfo.getIssueNum());
				typeStatisticsJsonArray.add(issueStatisticsJson);
			}
		}
		//String [] statusName = {"\'未修复\'","\'修复完成\'","\'退回返修\'","\'复修完成\'","\'确认完成\'"};
		//String [] statusValue = {"0","0","0","0","0"};
		List<String> statusNameList = new ArrayList<String>();
		List<String> statusValueList = new ArrayList<String>();
		statusNameList.add("\'未修复\'");
		statusNameList.add("\'修复完成\'");
		statusNameList.add("\'退回返修\'");
		statusNameList.add("\'复修完成\'");
		statusNameList.add("\'确认完成\'");
		statusValueList.add("0");
		statusValueList.add("0");
		statusValueList.add("0");
		statusValueList.add("0");
		statusValueList.add("0");
		
		int issuesTotal = 0;
		for(InspectRoomStatisticsInfoVo statisticsInfo:statusStatisticsListInfo){
			issuesTotal += Integer.parseInt(statisticsInfo.getIssueNum());
		}
		log.info("总数："+issuesTotal);
		DecimalFormat df = new DecimalFormat("0.00");
		for(InspectRoomStatisticsInfoVo statisticsInfo:statusStatisticsListInfo){
			int i = 0;
			for(String statusName:statusNameList){
				if(statusName.equals("\'"+statisticsInfo.getIssueType()+"\'")){
					Double per = Double.parseDouble(statisticsInfo.getIssueNum())/issuesTotal*100;
					statusValueList.set(i, df.format(per).toString()); 	
				}
				i++;
			}
		}
		JSONArray positionStatisticsJsonArray = new JSONArray();
		if(positionStatisticsListInfo.size() == 0) {
			JSONObject issueStatisticsJson = new JSONObject();
			issueStatisticsJson.put("name", "暂无数据");
			issueStatisticsJson.put("value", "......");
			positionStatisticsJsonArray.add(issueStatisticsJson);
		} else {
			for(InspectRoomStatisticsInfoVo statisticsInfo:positionStatisticsListInfo) {
				JSONObject issueStatisticsJson = new JSONObject();
				issueStatisticsJson.put("name", statisticsInfo.getIssueType());
				issueStatisticsJson.put("value", statisticsInfo.getIssueNum());
				log.info(statisticsInfo.getIssueType()+"============="+statisticsInfo.getIssueNum());
				positionStatisticsJsonArray.add(issueStatisticsJson);
			}
		}
		model.addAttribute("typeStatisticsData", typeStatisticsJsonArray);
		model.addAttribute("positionStatisticsData", positionStatisticsJsonArray);
		model.addAttribute("bldList", bldList);
		model.addAttribute("statusName", statusNameList);
		model.addAttribute("statusValue", statusValueList);
		return "/wbem/inspect/inspectStatistics";
	}
	
	@RequiresPermissions(value="page:inspectReport:inspectStatistics:index")
	@RequestMapping("/loadTypeStatisticsByBld")
	@ResponseBody
	public JSONArray loadTypeStatisticsByBld(String bldGuid){
		List<InspectRoomStatisticsInfoVo> statisticsListInfo = new ArrayList<InspectRoomStatisticsInfoVo>();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		if("".equals(bldGuid)){
			statisticsListInfo = statisticsService.getIssuesTypeStatisticsByBld(projGuid);
		}else {
			String []bldArray = bldGuid.split(",");
			String bldString = "";
			for(int i=0; i<bldArray.length; i++) {
				if(i != 0) {
					bldString += ",";
				}
				bldString += "\'"+bldArray[i]+"\'";
			}
			statisticsListInfo = statisticsService.getIssuesTypeStatisticsByBld(projGuid, bldString);
		}
		
		JSONArray issueStatisticsJsonArray = new JSONArray();
		if(statisticsListInfo.size() == 0){
			JSONObject issueStatisticsJson = new JSONObject();
			issueStatisticsJson.put("name", "暂无数据");
			issueStatisticsJson.put("value", "......");
			issueStatisticsJsonArray.add(issueStatisticsJson);
		} else {
			for(InspectRoomStatisticsInfoVo statisticsInfo:statisticsListInfo) {
				JSONObject issueStatisticsJson = new JSONObject();
				issueStatisticsJson.put("name", statisticsInfo.getIssueType());
				issueStatisticsJson.put("value", statisticsInfo.getIssueNum());
				log.info("issueType:"+statisticsInfo.getIssueType()+"  issueNum:"+statisticsInfo.getIssueNum());
				issueStatisticsJsonArray.add(issueStatisticsJson);
			}
		}
		return issueStatisticsJsonArray;
	}
	
	@RequiresPermissions(value="page:inspectReport:inspectStatistics:index")
	@RequestMapping("/loadStatusStatisticsByBld")
	@ResponseBody
	public Map<String,Object> loadStatusStatisticsByBld(String bldGuid){
		List<InspectRoomStatisticsInfoVo> statisticsListInfo = new ArrayList<InspectRoomStatisticsInfoVo>();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		if("".equals(bldGuid)){
			statisticsListInfo = statisticsService.getIssuesStatusStatisticsByBld(projGuid);
		}else {
			String []bldArray = bldGuid.split(",");
			String bldString = "";
			for(int i=0; i<bldArray.length; i++) {
				if(i != 0) {
					bldString += ",";
				}
				bldString += "\'"+bldArray[i]+"\'";
			}
			statisticsListInfo = statisticsService.getIssuesStatusStatisticsByBld(projGuid, bldString);
		}
		
		Map<String,Object> statusInfo = new HashMap<String,Object>();
		List<String> statusNameList = new ArrayList<String>();
		List<String> statusValueList = new ArrayList<String>();
		statusNameList.add("未修复");
		statusNameList.add("修复完成");
		statusNameList.add("退回返修");
		statusNameList.add("复修完成");
		statusNameList.add("确认完成");
		statusValueList.add("0");
		statusValueList.add("0");
		statusValueList.add("0");
		statusValueList.add("0");
		statusValueList.add("0");
		
		int issuesTotal = 0;
		for(InspectRoomStatisticsInfoVo statisticsInfo:statisticsListInfo){
			issuesTotal += Integer.parseInt(statisticsInfo.getIssueNum());
		}
		log.info("总数："+issuesTotal);
		DecimalFormat df = new DecimalFormat("0.00");
		for(InspectRoomStatisticsInfoVo statisticsInfo:statisticsListInfo){
			int i = 0;
			for(String statusName:statusNameList){
				if(statusName.equals(statisticsInfo.getIssueType())){
					Double per = Double.parseDouble(statisticsInfo.getIssueNum())/issuesTotal*100;
					statusValueList.set(i, df.format(per).toString()); 	
				}
				i++;
			}
		}
		statusInfo.put("name", statusNameList);
		statusInfo.put("value", statusValueList);
		return statusInfo;
	}
	
	@RequiresPermissions(value="page:inspectReport:inspectStatistics:index")
	@RequestMapping("/loadPositionStatisticsByBld")
	@ResponseBody
	public List<Map<String,String>> loadPositionStatisticsByBld(String bldGuid) {
		List<InspectRoomStatisticsInfoVo> statisticsListInfo = new ArrayList<InspectRoomStatisticsInfoVo>();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		if("".equals(bldGuid)){
			statisticsListInfo = statisticsService.getIssuesPositionStatisticsByBld(projGuid);
		}else {
			String []bldArray = bldGuid.split(",");
			String bldString = "";
			for(int i=0; i<bldArray.length; i++) {
				if(i != 0) {
					bldString += ",";
				}
				bldString += "\'"+bldArray[i]+"\'";
			}
			statisticsListInfo = statisticsService.getIssuesPositionStatisticsByBld(projGuid, bldString);
		}
		List<Map<String,String>> issueStatisticsList = new ArrayList<Map<String,String>>();
		if(statisticsListInfo.size() == 0){
			Map<String,String> issueInfo = new HashMap<String,String>();
			issueInfo.put("name", "暂无数据");
			issueInfo.put("value", "......");
			issueStatisticsList.add(issueInfo);
		}
		for(InspectRoomStatisticsInfoVo statisticsInfo:statisticsListInfo) {
			Map<String,String> issueInfo = new HashMap<String,String>();
			issueInfo.put("name", statisticsInfo.getIssueType());
			issueInfo.put("value", statisticsInfo.getIssueNum());
			log.info("issueType:"+statisticsInfo.getIssueType()+"  issueNum:"+statisticsInfo.getIssueNum());
			issueStatisticsList.add(issueInfo);
		}
		return issueStatisticsList;
	}
}
