package com.zs.common.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.common.entity.BldUnitInfoVo;
import com.zs.common.entity.BuildUnitTableEntity;
import com.zs.common.entity.DataTablesParameters;
import com.zs.common.entity.InspectRoomInfoVo;
import com.zs.common.entity.InspectRoomSearchInfoVo;
import com.zs.common.entity.InspectRoomTableEntity;
import com.zs.common.entity.IssueFeedbackTableEntity;
import com.zs.common.entity.IssuesDetailTableEntity;
import com.zs.common.entity.IssuesRoomTableEntity;
import com.zs.common.entity.IssuesTypeTableEntity;
import com.zs.common.entity.RoomTableEntity;
import com.zs.common.service.InspectRoomService;
import com.zs.common.util.RequestUtil;
import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.rbac.core.RBACSubject;
import com.zs.rbac.entity.User;
import com.zs.rbac.service.XsTeamGroupService;
import com.zs.rbac.utils.RbacUtils;

@Controller
@RequestMapping(value="/wbem/inspect")
public class InspectRoomController {
	private Log log=LogFactory.getLog(InspectRoomController.class);
	@Autowired
	XsTeamGroupService xsTeamGroupService;
	@Autowired
	InspectRoomService inspectRoomService;
	/**
	 * 进入验房首页
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:propertyManage:addIssues:index")
	@RequestMapping("/inspectIndex")
	public String inspectIndex(Model model) {
		//List<XsTeamGroupEntity> projectList = new ArrayList<XsTeamGroupEntity>();
		InspectRoomSearchInfoVo searchInfo = new InspectRoomSearchInfoVo();
		List<InspectRoomInfoVo> bldList = new ArrayList<InspectRoomInfoVo>();
		List<IssuesTypeTableEntity> typeList = new ArrayList<IssuesTypeTableEntity>();
		//projectList = xsTeamGroupService.getProjectItem();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		searchInfo = new InspectRoomSearchInfoVo();
		searchInfo.setProjGuid(projGuid);
		bldList = inspectRoomService.getBldInfoList(searchInfo);
		typeList = inspectRoomService.getIssuesTypeList("0");
		
		//model.addAttribute("projectList", projectList);
		model.addAttribute("bldList", bldList);
		model.addAttribute("typeList", typeList);
		return "/wbem/inspect/inspectIndex";
	}
	/**
	 * 加载问题列表
	 * @param request
	 * @param projGuid
	 * @return
	 */
	@RequiresPermissions(value={"page:propertyManage:addIssues:index","page:projectManage:issuesDeal:index"},logical=Logical.OR)
	@ResponseBody
	@RequestMapping("/loadIssuesList")
	public Map<String,Object> loadIssuesList(HttpServletRequest request,/*String projGuid,*/String bldGuid,String unitGuid,String status,String mainType) {
		InspectRoomSearchInfoVo searchInfo = new InspectRoomSearchInfoVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		searchInfo.setProjGuid(projGuid);
		searchInfo.setBldGuid(bldGuid);
		searchInfo.setUnitGuid(unitGuid);
		searchInfo.setStatus(status);
		searchInfo.setMainType(mainType);
		List<InspectRoomInfoVo> allIssueDetailList = new ArrayList<InspectRoomInfoVo>();
		List<InspectRoomInfoVo> issueDetailList = new ArrayList<InspectRoomInfoVo>();
		allIssueDetailList = inspectRoomService.getIssuesInfoList(searchInfo);
		int totalNum = allIssueDetailList.size();
		DataTablesParameters para= RequestUtil.getDTPara(request);
		int startIndex = para.getStart();
		int length = para.getLength();
		searchInfo.setStartIndex(startIndex);
		searchInfo.setLength(length);
		issueDetailList = inspectRoomService.getIssuesInfoList(searchInfo);
		List<Map<String,Object>> issueList = new ArrayList<Map<String,Object>>();
		int i = startIndex+1;
		for(InspectRoomInfoVo issue:issueDetailList) {
			Map<String,Object> issueMap = new HashMap<String,Object>();
			issueMap.put("index","<label class='checkbox inline index'> <input type='checkbox' name='radios' />"+i+"</label>");
			issueMap.put("bldName", issue.getBuild().getBldName());
         	issueMap.put("unit", issue.getRoom().getUnit());
         	issueMap.put("room", issue.getRoom().getRoom());
         	issueMap.put("issuePosition", issue.getIssuesDetail().getPosition());
         	issueMap.put("issueType", (issue.getIssuesType().getParentCode()==null?"":issue.getIssuesType().getParentCode()+"-")+(issue.getIssuesType().getIssueName()==null?"":issue.getIssuesType().getIssueName()));
			issueMap.put("issueDesc", issue.getIssuesDetail().getIssueDesc());
			String level = "";
			switch(issue.getIssuesDetail().getLevel()) {
			case 0:
				level = "一般";
				break;
			case 1:
				level = "紧急";
				break;
			}
			issueMap.put("level", level);
			String statusName = "";
			switch(issue.getIssuesDetail().getStatus()){
				case "0":
					statusName = "未修复";
	         		break;
	         	case "1":
	         		statusName = "工程修复完成";
	         		break;
	         	case "2":
	         		statusName = "物业确认完成";
	         		break;
	         	case "3":
	         		statusName = "物业退回返修";
	         		break;
	         	case "4":
	         		statusName = "工程复修完成";
	         		break;
			}
			issueMap.put("status", statusName);
			issueMap.put("createDate", issue.getIssuesDetail().getCreateDate().substring(0, 10));
			issueMap.put("operate", issue.getIssuesDetail().getId());
			i++;
			issueList.add(issueMap);
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("draw", para.getDraw());
		resultMap.put("recordsTotal", totalNum);
		resultMap.put("recordsFiltered", totalNum);
		resultMap.put("data", issueList);
		return resultMap;
	}
	/**
	 * 加载指定房间问题列表
	 * @param request
	 * @param projGuid
	 * @return
	 */
	@RequiresPermissions(value="page:propertyManage:addIssues:addIssues")
	@ResponseBody
	@RequestMapping("/loadRoomIssuesList")
	public Map<String,Object> loadRoomIssuesList(HttpServletRequest request,String roomGuid) {
		log.info("roomGuid:"+roomGuid);
		InspectRoomSearchInfoVo searchInfo = new InspectRoomSearchInfoVo();
		searchInfo.setRoomGuid(roomGuid);
		List<InspectRoomInfoVo> allIssueDetailList = new ArrayList<InspectRoomInfoVo>();
		List<InspectRoomInfoVo> issueDetailList = new ArrayList<InspectRoomInfoVo>();
		allIssueDetailList = inspectRoomService.getIssuesInfoList(searchInfo);
		int totalNum = allIssueDetailList.size();
		DataTablesParameters para= RequestUtil.getDTPara(request);
		int startIndex = para.getStart();
		int length = para.getLength();
		searchInfo.setStartIndex(startIndex);
		searchInfo.setLength(length);
		issueDetailList = inspectRoomService.getIssuesInfoList(searchInfo);
		List<Map<String,Object>> issueList = new ArrayList<Map<String,Object>>();
		int i = startIndex+1;
		for(InspectRoomInfoVo issue:issueDetailList) {
			Map<String,Object> issueMap = new HashMap<String,Object>();
			issueMap.put("index",i);
			issueMap.put("issuePosition",issue.getIssuesDetail().getPosition());
			issueMap.put("type", issue.getIssuesType().getIssueName());
         	issueMap.put("issueDesc", issue.getIssuesDetail().getIssueDesc());
         	String status = "";
         	switch(issue.getIssuesDetail().getStatus()) {
	         	case "0":
	         		status = "未修复";
	         		break;
	         	case "1":
	         		status = "工程修复完成";
	         		break;
	         	case "2":
	         		status = "物业确认完成";
	         		break;
	         	case "3":
	         		status = "物业退回返修";
	         		break;
	         	case "4":
	         		status = "工程复修完成";
	         		break;
         	}
         	issueMap.put("status",status );
         	issueMap.put("createDate", issue.getIssuesDetail().getCreateDate().substring(0, 10));
         	issueMap.put("operate", issue.getIssuesDetail().getId());
			i++;
			issueList.add(issueMap);
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("draw", para.getDraw());
		resultMap.put("recordsTotal", totalNum);
		resultMap.put("recordsFiltered", totalNum);
		resultMap.put("data", issueList);
		return resultMap;
	}
	/**
	 * 进入新增验房问题反馈页
	 * @param issuesId
	 * @param status
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:propertyManage:addIssues:inspectFeedback")
	@RequestMapping("/inspectFeedback")
	public String inspectFeedback(@RequestParam(value="issuesId",required=true)String issuesId,@RequestParam(value="status",required=true)String status,Model model) {
		List<String> employeeList = new ArrayList<String>();
		employeeList = inspectRoomService.getEmployeeList();
		model.addAttribute("employeeList", employeeList);
		model.addAttribute("issuesId", issuesId);
		model.addAttribute("status", status);
		log.info("issuesId:"+issuesId+"   status:"+status);
		return "/wbem/inspect/inspectFeedback";
	}
	/**
	 * 进入验房问题反馈详情页(查看)
	 * @param issuesId
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:propertyManage:addIssues:issuesFeedbackDetail")
	@RequestMapping("/issuesFeedbackDetail")
	public String issuesFeedbackDetail(@RequestParam(value="issuesId",required=true)String issuesId,Model model) {
		
		model.addAttribute("issuesId", issuesId);	
		return "/wbem/inspect/issuesFeedbackDetail";
	}
	/**
	 * 验房新增反馈确认
	 * @param inspectMan
	 * @param backContent
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequiresPermissions(value="page:propertyManage:addIssues:inspectFeedback")
	@RequestMapping("/addIssuesFeedback")
	@ResponseBody
	public int addIssuesFeedback(String inspectMan,String backContent,int issuesId,String status) throws UnsupportedEncodingException {
		log.info("inspectMan:"+inspectMan+"backContent:"+backContent+"issuesId"+issuesId+"status:"+status);
		IssueFeedbackTableEntity feedbackInfo = new IssueFeedbackTableEntity();
		IssuesDetailTableEntity issuesDetail = new IssuesDetailTableEntity();
		IssuesDetailTableEntity searchInfo = new IssuesDetailTableEntity();
		searchInfo.setId(issuesId);
		issuesDetail = inspectRoomService.getIssuesDetailById(searchInfo).get(0);
		User user = (User)RbacUtils.subject().getSession().getAttribute("user");
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String endDate = fm.format(cal.getTime());
		feedbackInfo.setRepairMan(URLDecoder.decode(inspectMan,"utf-8"));
		feedbackInfo.setBackContent(URLDecoder.decode(backContent,"utf-8"));
		feedbackInfo.setIssuesId(issuesId);
		feedbackInfo.setCreateDate(endDate);
		feedbackInfo.setOperId(String.valueOf(user.getUserID()));
		int newStatus = Integer.parseInt(issuesDetail.getStatus());
		int flag = 1;

		if(issuesDetail.getStatus().equals("1") || issuesDetail.getStatus().equals("4")) {
			if(status.equals("1")) {
				newStatus = 2;
				feedbackInfo.setFbType(newStatus);
				searchInfo = new IssuesDetailTableEntity();
				searchInfo.setIssueRoomId(issuesDetail.getIssueRoomId());
				int size = inspectRoomService.getIssuesDetailById(searchInfo).size();
				if(size == 1) {
					flag = inspectRoomService.insertIssueFeedback(feedbackInfo, issuesDetail.getIssueRoomId(), newStatus, endDate);
				}else {
					flag = inspectRoomService.insertIssueFeedback(feedbackInfo, newStatus, endDate);
				}	
			}
			if(status.equals("2")) {
				newStatus = 3;
				feedbackInfo.setFbType(newStatus);
				flag = inspectRoomService.insertIssueFeedback(feedbackInfo, newStatus);
			}
		}
		return flag;
	}
	/**
	 * 加载问题反馈列表
	 * @param request
	 * @param issuesId
	 * @return
	 */
	@RequiresPermissions(value="page:propertyManage:addIssues:issuesFeedbackDetail")
	@RequestMapping("loadIssuesFeedbackList")
	@ResponseBody
	public Map<String,Object> loadIssuesFeedbackList(HttpServletRequest request,int issuesId) {
		List<InspectRoomInfoVo> allIssuesFeedbackList = new ArrayList<InspectRoomInfoVo>();
		List<InspectRoomInfoVo> issuesFeedbackList = new ArrayList<InspectRoomInfoVo>();
		allIssuesFeedbackList = inspectRoomService.getIssueFeedbackList(issuesId);
		int totalNum = allIssuesFeedbackList.size();
		DataTablesParameters para= RequestUtil.getDTPara(request);
		int startIndex = para.getStart();
		int length = para.getLength();
		issuesFeedbackList = inspectRoomService.getIssueFeedbackList(issuesId, startIndex, length);
		int i = startIndex+1;
		List<Map<String,Object>> feedbackList = new ArrayList<Map<String,Object>>();
		for(InspectRoomInfoVo issuesFeedback:issuesFeedbackList) {
			Map<String,Object> issuesFeedbackMap = new HashMap<String,Object>();
			issuesFeedbackMap.put("index", "<label class='checkbox inline index'> <input type='checkbox' name='radios' />"+i+"</label>");
			issuesFeedbackMap.put("createDate", issuesFeedback.getIssueFeedback().getCreateDate());
			issuesFeedbackMap.put("backContent", issuesFeedback.getIssueFeedback().getBackContent());
			issuesFeedbackMap.put("repairMan", issuesFeedback.getIssueFeedback().getRepairMan());
			String issuesStatus = "";
			switch(issuesFeedback.getIssueFeedback().getFbType()) {
				case 1:
					issuesStatus = "工程修复完成";
					break;
				case 2:
					issuesStatus = "物业确认完成";
					break;
				case 3:
					issuesStatus = "物业退回返修";
					break;
				case 4:
					issuesStatus = "工程复修完成";
					break;
			}
			issuesFeedbackMap.put("fbType", issuesStatus);
			feedbackList.add(issuesFeedbackMap);
			i++;
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("draw", para.getDraw());
		resultMap.put("recordsTotal", totalNum);
		resultMap.put("recordsFiltered", totalNum);
		resultMap.put("data", feedbackList);
		return resultMap;
	}
	/**
	 * 进入登记问题页
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:propertyManage:addIssues:addIssues")
	@RequestMapping("/addIssues")
	public String addIssue(Model model,@RequestParam(value="status",required=false)String status) {
		List<InspectRoomInfoVo> bldInfoList = new ArrayList<InspectRoomInfoVo>();
		InspectRoomSearchInfoVo searchInfo = new InspectRoomSearchInfoVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		searchInfo.setProjGuid(projGuid);
		bldInfoList = inspectRoomService.getBldInfoList(searchInfo);
		List<IssuesTypeTableEntity> mainTypeList = new ArrayList<IssuesTypeTableEntity>();
		mainTypeList = inspectRoomService.getIssuesTypeList("0");
		
		model.addAttribute("bldInfoList", bldInfoList);
		model.addAttribute("mainTypeList", mainTypeList);
		model.addAttribute("status", status);
		return "/wbem/inspect/addIssues";
	}
	/**
	 * 加载指定楼栋下单元及房间信息
	 * @param bldGuid
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequiresPermissions(value="page:propertyManage:addIssues:addIssues")
	@RequestMapping("/bldUnitRoomInfo")
	public String bldUnitRoomInfo(String bldGuid,String bldName,Model model) throws UnsupportedEncodingException {
		List<BldUnitInfoVo> bldUnitInfoList = new ArrayList<BldUnitInfoVo>();
		int type = 0;
		if(URLDecoder.decode(bldName,"utf-8").indexOf("楼") != -1){
			type = 1;
		}
		else{
			type = 2;
		}
		bldUnitInfoList = inspectRoomService.getBldUnitInfoList(bldGuid,type);
		int maxFloor = 0;
		int minFloor = 100;
		for(BldUnitInfoVo bldUnitInfo:bldUnitInfoList) {
			if(type == 1) {
				log.info(bldUnitInfo.getUnit()+"  "+bldUnitInfo.getDoorNum());
				int maxf = Integer.parseInt(bldUnitInfo.getRoomList().get(0).getFloor());
				int minf = Integer.parseInt(bldUnitInfo.getRoomList().get(bldUnitInfo.getRoomList().size()-1).getFloor());
				if(maxf >= maxFloor) {
					maxFloor = maxf;
				}
				if(minf <= minFloor) {
					minFloor = minf;
				}
			}
			if(type == 2){
				for(RoomTableEntity roomInfo:bldUnitInfo.getRoomList()){
					if(Integer.parseInt(roomInfo.getFloor()) >= maxFloor) {
						maxFloor = Integer.parseInt(roomInfo.getFloor());
					}
					if(Integer.parseInt(roomInfo.getFloor()) <= minFloor) {
						minFloor = Integer.parseInt(roomInfo.getFloor());
					}
				}
			}
		}
		log.info("maxFloor:"+maxFloor+"   minFloor:"+minFloor);
		model.addAttribute("bldUnitInfoList", bldUnitInfoList);
		model.addAttribute("maxFloor", maxFloor);
		model.addAttribute("minFloor", minFloor);
		return "/wbem/inspect/bldUnitRoomInfo";
	}
	/**
	 * 新增问题确认
	 * @param request
	 * @param roomGuid
	 * @param issuesStr
	 * @return
	 */
	@RequiresPermissions(value="page:propertyManage:addIssues:addIssues")
	@RequestMapping("/insertRoomIssuesInfo")
	public String insertRoomIssuesInfo(HttpServletRequest request,RoomTableEntity roomInfo,String issuesStr,@RequestParam(value="type",required=true)int type,Model model) {
		log.info(roomInfo.getRoomGuid()+"roomGuid:"+"  issuesStr:"+issuesStr+"  type:"+type);
		String []roomArray = new String[roomInfo.getRoomGuid().split(",").length];
		String []issuesArray = new String[issuesStr.split(",").length];
		roomArray = roomInfo.getRoomGuid().split(",");
		issuesArray = issuesStr.split(",");
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String createDate = fm.format(cal.getTime());
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		for(int i=0; i<roomArray.length; i++) {
			List<IssuesDetailTableEntity> issuesList = new ArrayList<IssuesDetailTableEntity>();
			IssuesRoomTableEntity issuesRoom = new IssuesRoomTableEntity();
			InspectRoomTableEntity inspectRoom = new InspectRoomTableEntity();
			issuesRoom.setRoomGuid(roomArray[i]);
			issuesRoom.setStatus(1);//后续修改
			issuesRoom.setType(1);//后续修改
			issuesRoom.setCreateDate(createDate);
			
			inspectRoom.setProjGuid(projGuid);
			inspectRoom.setBldGuid(roomInfo.getBldGuid());
			inspectRoom.setRoomGuid(roomArray[i]);
			inspectRoom.setRepairTimes(0);
			inspectRoom.setCheckStatus(0);
			inspectRoom.setCreateDate(createDate);
			inspectRoom.setRoomStatus(0);
			for(int j=0; j<issuesArray.length; j++) {
				IssuesDetailTableEntity issuesDetail = new IssuesDetailTableEntity();
				if(issuesArray[j].equals("1")) {
					//issuesDetail.setIssueRoomId(issuesRoom.getIssueRoomId());
					issuesDetail.setIssueCode(request.getParameter("subType"+j));
					issuesDetail.setIssueDesc(request.getParameter("issueDesc"+j));
					issuesDetail.setPosition(request.getParameter("issuePosition"+j));
					issuesDetail.setImgPath(request.getParameter("imgPath"+j));
					issuesDetail.setLevel(Integer.parseInt(request.getParameter("level"+j)));
					issuesDetail.setStatus("0");//暂定
					issuesDetail.setCreateDate(createDate);
					issuesList.add(issuesDetail);
				}
			}
			inspectRoomService.insertIssueDetailList(issuesRoom, inspectRoom, issuesList);
			log.info("issussSize"+issuesList.size());
		}
		log.info("insertRoomIssuesInfo"+roomInfo.getRoomGuid());
		String status = "";
		if(type == 1) {
			status = "close";
		}
		return "redirect:/wbem/inspect/addIssues.action?status="+status;
	}
	/**
	 * 加载问题类型列表
	 * @param parentCode
	 * @return
	 */
	@RequiresPermissions(value="page:basicSetting:issueTypeSet:index")
	@RequestMapping("/loadIssuesTypeList")
	@ResponseBody
	public List<IssuesTypeTableEntity> loadIssuesTypeList(String parentCode) {
		List<IssuesTypeTableEntity> issuesTypeList = new ArrayList<IssuesTypeTableEntity>();
		issuesTypeList = inspectRoomService.getIssuesTypeList(parentCode);
		return issuesTypeList;
		
	}
	/**
	 * 维修首页
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:projectManage:issuesDeal:index")
	@RequestMapping("/repairIndex")
	public String repairIndex(Model model) {
		//List<XsTeamGroupEntity> projectList = new ArrayList<XsTeamGroupEntity>();
		InspectRoomSearchInfoVo searchInfo = new InspectRoomSearchInfoVo();
		List<InspectRoomInfoVo> bldList = new ArrayList<InspectRoomInfoVo>();
		List<IssuesTypeTableEntity> typeList = new ArrayList<IssuesTypeTableEntity>();
		//projectList = xsTeamGroupService.getProjectItem();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		searchInfo.setProjGuid(projGuid);
		bldList = inspectRoomService.getBldInfoList(searchInfo);
		typeList = inspectRoomService.getIssuesTypeList("0");
		
		//model.addAttribute("projectList", projectList);
		model.addAttribute("bldList", bldList);
		model.addAttribute("typeList", typeList);
		return "/wbem/inspect/repairIndex";
	}
	/**
	 * 
	 * @return
	 */
	@RequiresPermissions(value="page:projectManage:issuesDeal:repairFeedback")
	@RequestMapping("/repairFeedback")
	public String repairFeedback(@RequestParam(value="issuesId",required=true)String issuesId,Model model) {
		
		model.addAttribute("issuesId", issuesId);
		return "/wbem/inspect/repairFeedback";
	}
	/**
	 * 进入新增维修反馈页
	 * @param repairMan
	 * @param backContent
	 * @param issuesId
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequiresPermissions(value="page:projectManage:issuesDeal:repairFeedback")
	@RequestMapping("/addRepairFeedback")
	@ResponseBody
	public int addRepairFeedback(String repairMan,String backContent,int issuesId) throws UnsupportedEncodingException {
		IssuesDetailTableEntity issuesDetail = new IssuesDetailTableEntity();
		IssuesDetailTableEntity searchInfo = new IssuesDetailTableEntity();
		IssueFeedbackTableEntity feedbackInfo = new IssueFeedbackTableEntity();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		User user = (User)RbacUtils.subject().getSession().getAttribute("user");
		searchInfo.setId(issuesId);
		issuesDetail = inspectRoomService.getIssuesDetailById(searchInfo).get(0);
		int status = Integer.parseInt(issuesDetail.getStatus());
		if(issuesDetail.getStatus().equals("0")){
			status = 1;	
		}
		if(issuesDetail.getStatus().equals("3")) {
			status = 4;
		}
		feedbackInfo.setBackContent(URLDecoder.decode(backContent, "utf-8"));
		feedbackInfo.setIssuesId(issuesId);
		feedbackInfo.setRepairMan(URLDecoder.decode(repairMan,"utf-8"));
		feedbackInfo.setOperId(String.valueOf(user.getUserID()));
		feedbackInfo.setCreateDate(fm.format(cal.getTime()));
		feedbackInfo.setFbType(status);
		int flag = inspectRoomService.insertIssueFeedback(feedbackInfo, status);
		return flag;
	}
	/**
	 * 进入打印页
	 * @param projGuid
	 * @param bldGuid
	 * @param unitGuid
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value={"page:propertyManage:addIssues:print","page:projectManage:issuesDeal:print"},logical=Logical.OR)
	@RequestMapping("/inspectPrint")
	public String inspectPrint(/*@RequestParam(value="projGuid",required=true)String projGuid,*/@RequestParam(value="bldGuid",required=true)String bldGuid,@RequestParam(value="unitGuid",required=true)String unitGuid,@RequestParam(value="status",required=true)String status,@RequestParam(value="mainType",required=true)String mainType,Model model) {
		//model.addAttribute("projGuid", projGuid);
		model.addAttribute("bldGuid", bldGuid);
		model.addAttribute("unitGuid", unitGuid);
		model.addAttribute("status", status);
		model.addAttribute("mainType", mainType);
		return "/wbem/inspect/inspectPrint";
	}
	/**
	 * 加载打印问题详细列表
	 * @param request
	 * @param projGuid
	 * @return
	 */
	@RequiresPermissions(value={"page:propertyManage:addIssues:print","page:projectManage:issuesDeal:print"},logical=Logical.OR)
	@ResponseBody
	@RequestMapping("/loadPrintIssuesList")
	public Map<String,Object> loadPrintIssuesList(HttpServletRequest request,/*String projGuid,*/String bldGuid,String unitGuid,String mainType,String status) {
		
		InspectRoomSearchInfoVo searchInfo = new InspectRoomSearchInfoVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		searchInfo.setProjGuid(projGuid);
		searchInfo.setBldGuid(bldGuid);
		searchInfo.setUnitGuid(unitGuid);
		searchInfo.setStatus(status);
		searchInfo.setMainType(mainType);
		List<InspectRoomInfoVo> allIssueDetailList = new ArrayList<InspectRoomInfoVo>();
		List<InspectRoomInfoVo> issueDetailList = new ArrayList<InspectRoomInfoVo>();
		allIssueDetailList = inspectRoomService.getIssuesInfoList(searchInfo);
		int totalNum = allIssueDetailList.size();
		DataTablesParameters para= RequestUtil.getDTPara(request);
		
		issueDetailList = inspectRoomService.getIssuesInfoList(searchInfo);
		List<Map<String,Object>> issueList = new ArrayList<Map<String,Object>>();
		int i = 1;
		for(InspectRoomInfoVo issue:issueDetailList) {
			Map<String,Object> issueMap = new HashMap<String,Object>();
			issueMap.put("index",i);
			issueMap.put("bldName", issue.getBuild().getBldName());
         	issueMap.put("unit", issue.getRoom().getUnit());
         	issueMap.put("room", issue.getRoom().getRoom());
         	issueMap.put("issueType", issue.getIssuesType().getIssueName());
			issueMap.put("issueDesc", issue.getIssuesDetail().getIssueDesc());
			String level = "";
			switch(issue.getIssuesDetail().getLevel()) {
			case 0:
				level = "一般";
				break;
			case 1:
				level = "紧急";
				break;
			}
			issueMap.put("level", level);
			issueMap.put("remark", "");
			i++;
			issueList.add(issueMap);
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("draw", para.getDraw());
		resultMap.put("recordsTotal", totalNum);
		resultMap.put("recordsFiltered", totalNum);
		resultMap.put("data", issueList);
		return resultMap;
	}
	/**
	 * 加载指定楼栋下的单元列表
	 * @param bldGuid
	 * @return
	 */
	@RequiresPermissions(value="page:propertyManage:addIssues:addIssues")
	@RequestMapping("/loadUnitList")
	@ResponseBody
	public List<BuildUnitTableEntity> loadUnitList(String bldGuid){
		List<BuildUnitTableEntity> unitList = new ArrayList<BuildUnitTableEntity>();
		unitList = inspectRoomService.getUnitInfoByBld(bldGuid);
		return unitList;
	}
	/**
	 * 加载指定项目下可验的楼栋列表
	 * @param projGuid
	 * @return
	 */
	@RequiresPermissions(value="page:propertyManage:addIssues:addIssues")
	@RequestMapping("/loadBldList")
	@ResponseBody
	public List<InspectRoomInfoVo> loadBldList(/*String projGuid*/) {
		InspectRoomSearchInfoVo searchInfo = new InspectRoomSearchInfoVo();
		List<InspectRoomInfoVo> bldList = new ArrayList<InspectRoomInfoVo>();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		searchInfo.setProjGuid(projGuid);
		bldList = inspectRoomService.getBldInfoList(searchInfo);
		return bldList;
	}
	/**
	 * 根据id获取问题详情
	 * @param id
	 * @return
	 */
	@RequiresPermissions(value="page:propertyManage:addIssues:addIssues")
	@RequestMapping("/getIssueDetailById")
	@ResponseBody
	public InspectRoomInfoVo getIssueDetailById(int issuesId) {
		List<InspectRoomInfoVo> issuesList = new ArrayList<InspectRoomInfoVo>();
		InspectRoomInfoVo issueDetail = new InspectRoomInfoVo();
		InspectRoomSearchInfoVo searchInfo = new InspectRoomSearchInfoVo();
		searchInfo.setIssuesId(issuesId);
		issuesList = inspectRoomService.getIssuesInfoList(searchInfo);
		issueDetail = issuesList.get(0);
		return issueDetail; 
	}
	/**
	 * 更新问题明细
	 * @param issuesId
	 * @param position
	 * @param mainType
	 * @param subType
	 * @param level
	 * @param issueDesc
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequiresPermissions(value="page:propertyManage:addIssues:addIssues")
	@RequestMapping("/updateIssueDetailById")
	@ResponseBody
	public int updateIssueDetailById(int issuesId,String position,String subType,int level,String issueDesc) throws UnsupportedEncodingException {
		IssuesDetailTableEntity issueDetail = new IssuesDetailTableEntity();
		issueDetail.setId(issuesId);
		issueDetail.setIssueCode(subType);
		issueDetail.setIssueDesc(URLDecoder.decode(issueDesc,"utf-8"));
		issueDetail.setLevel(level);
		issueDetail.setPosition(URLDecoder.decode(position,"utf-8"));
		int flag = inspectRoomService.updateIssuesDetailById(issueDetail);
		return flag;
	}
	/**
	 * 删除问题明细
	 * @param issuesId
	 * @return
	 */
	@RequiresPermissions(value="page:propertyManage:addIssues:index")
	@RequestMapping("/deleteIssueDetailById")
	@ResponseBody
	public int deleteIssueDetailById(int issuesId) {
		int flag1 = 1;
		int flag2 = 1;
		//int flag3 = 1;
		IssuesDetailTableEntity issueDetail = new IssuesDetailTableEntity();
		IssuesDetailTableEntity searchInfo = new IssuesDetailTableEntity();
		searchInfo.setId(issuesId);
		issueDetail = inspectRoomService.getIssuesDetailById(searchInfo).get(0);
		flag1 = inspectRoomService.deleteIssuesDetailById(issuesId);
		searchInfo = new IssuesDetailTableEntity();
		searchInfo.setIssueRoomId(issueDetail.getIssueRoomId());
		if(inspectRoomService.getIssuesDetailById(searchInfo).size() == 0){
			//IssuesRoomTableEntity issueRoom = inspectRoomService.getIssuesRoomById(issueDetail.getIssueRoomId());
			flag2 = inspectRoomService.deleteIssuesRoomById(issueDetail.getIssueRoomId());
			//flag3 = inspectRoomService.deleteInspectRoomById(issueRoom.getRoomGuid());
		}
		return flag1&flag2;
	}
	/**
	 * 进入问题参数设置页面
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:basicSetting:issueTypeSet:index")
	@RequestMapping("/issueParametersSetting")
	public String issueParametersSetting(Model model) {
		List<IssuesTypeTableEntity> typeList = new ArrayList<IssuesTypeTableEntity>();
		typeList = inspectRoomService.getIssuesTypeList("0");
		model.addAttribute("typeList", typeList);
		return "/wbem/inspect/issueParametersSetting";
	}
	/**
	 * 根据主类型加载问题类型列表
	 * @param request
	 * @return
	 */
	@RequiresPermissions(value="page:basicSetting:issueTypeSet:index")
	@RequestMapping("/loadTypeList")
	@ResponseBody
	public Map<String,Object> loadTypeList(HttpServletRequest request,String mainType) {
		DataTablesParameters para= RequestUtil.getDTPara(request);
		InspectRoomSearchInfoVo searchInfo = new InspectRoomSearchInfoVo();
		List<IssuesTypeTableEntity> typeList = new ArrayList<IssuesTypeTableEntity>();
		List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		searchInfo.setMainType(mainType);
		int totalNum = inspectRoomService.getAllIssusTypeList(searchInfo).size();
		searchInfo.setStartIndex(para.getStart());
		searchInfo.setLength(para.getLength());
		typeList = inspectRoomService.getAllIssusTypeList(searchInfo);
		int i = para.getStart()+1;
		for(IssuesTypeTableEntity type:typeList) {
			Map<String,Object> typeMap =  new HashMap<String,Object>();
			typeMap.put("index", i);
			typeMap.put("issueType", type.getIssueName());
			typeMap.put("mainType", type.getParentCode()==null?"":type.getParentCode());
			typeMap.put("operate", type.getIssueCode());
			data.add(typeMap);
			i++;
		}
		resultMap.put("draw", para.getDraw());
		resultMap.put("recordsTotal", totalNum);
		resultMap.put("recordsFiltered", totalNum);
		resultMap.put("data", data);
		return resultMap;
	}
	/**
	 * 根据id删除问题类型
	 * @param issuesId
	 * @return
	 */
	@RequiresPermissions(value="page:basicSetting:issueTypeSet:index")
	@RequestMapping("/deleteIssuesTypeById")
	@ResponseBody
	public int deleteIssuesTypeById(int issuesId) {
		int flag = 0;
		List<IssuesDetailTableEntity> issuesList = new ArrayList<IssuesDetailTableEntity>();
		IssuesDetailTableEntity searchInfo = new IssuesDetailTableEntity();
		searchInfo.setIssueCode(String.valueOf(issuesId));
		issuesList = inspectRoomService.getIssuesDetailById(searchInfo);
		if(issuesList.size() == 0) {
			flag = inspectRoomService.deleteIssuesTypeById(issuesId);
		} else {
			flag = 2;
		}
		return flag;
	}
	/**
	 * 进入新增问题类型页
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:basicSetting:issueTypeSet:addIssuesType")
	@RequestMapping("/addIssuesType")
	public String addIssuesType(Model model) {
		List<IssuesTypeTableEntity> typeList = new ArrayList<IssuesTypeTableEntity>();
		typeList = inspectRoomService.getIssuesTypeList("0");
		model.addAttribute("typeList", typeList);
		return "/wbem/inspect/addIssuesType";
	} 
	/**
	 * 新增问题类型
	 * @param issueName
	 * @param mainType
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequiresPermissions(value="page:basicSetting:issueTypeSet:addIssuesType")
	@RequestMapping("/insertIssuesType")
	@ResponseBody
	public int insertIssuesType(String issueName,String mainType) throws UnsupportedEncodingException {
		int flag = 0;
		flag = inspectRoomService.insertIssuesType(URLDecoder.decode(issueName, "utf-8"), mainType);
		return flag;
	}
	/**
	 * 编辑问题类型（更改问题类型名称）
	 * @param issueName
	 * @param issuesId
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequiresPermissions(value="page:basicSetting:issueTypeSet:index")
	@RequestMapping("/updateIssuesTypeById")
	@ResponseBody
	public int updateIssuesTypeById(String issueName,int issuesId) throws UnsupportedEncodingException {
		int flag = 0;
		flag = inspectRoomService.updateIssuesTypeById(URLDecoder.decode(issueName,"utf-8"), issuesId);
		return flag;
	}
	/**
	 * 进入楼栋开放页
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:projectManage:bldOpen:index")
	@RequestMapping("/bldStatusSetting")
	public String bldStatusSetting(Model model) {
		//List<XsTeamGroupEntity> projectList = new ArrayList<XsTeamGroupEntity>();
		//projectList = xsTeamGroupService.getProjectItem();
		//model.addAttribute("projectList", projectList);

		return "/wbem/inspect/bldStatusSetting";
	}
	/**
	 * 根据项目id搜索当前项目下所有楼栋信息及状态
	 * @param projGuid
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:projectManage:bldOpen:index")
	@RequestMapping("/loadBldStatusInfoById")
	public String loadBldStatusInfoById(/*String projGuid,*/Model model){
		List<InspectRoomInfoVo> bldList = new ArrayList<InspectRoomInfoVo>();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		bldList = inspectRoomService.getAllBldInfoListById(projGuid);
		model.addAttribute("bldList", bldList);
		return "/wbem/inspect/bldStatusInfo";
	}
	/**
	 * 设置楼栋状态为开放验房
	 * @param bldGuid
	 * @return
	 */
	@RequiresPermissions(value="page:projectManage:bldOpen:index")
	@RequestMapping("/insertBldStatusInfo")
	@ResponseBody
	public int insertBLdStatusInfo(String bldGuid) {
		int flag = 0;
		flag = inspectRoomService.insertBuildingExt(bldGuid, 1);
		return flag;
	}	
}
