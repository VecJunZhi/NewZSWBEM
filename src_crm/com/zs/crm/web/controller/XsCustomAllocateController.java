package com.zs.crm.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.AudioFormat.Encoding;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zs.common.entity.DataTablesParameters;
import com.zs.common.util.DateUtil;
import com.zs.common.util.ExcelUploadImport;
import com.zs.common.util.RequestUtil;
import com.zs.common.util.ViewExcelExport;
import com.zs.busi.entity.ZsBusiCustomLogTable;
import com.zs.busi.service.ZsBusiCustomAllocateService;
import com.zs.busi.utils.LogUtil;
import com.zs.common.entity.SearchOptionEntity;
import com.zs.common.service.IOptionListService;
import com.zs.common.util.ding.DingDingVo;
import com.zs.common.util.ding.DingUtil;
import com.zs.common.util.search.TagOption;
import com.zs.crm.entity.XsCstInfo;
import com.zs.crm.entity.XsCstSearchOption;
import com.zs.crm.entity.XsCustomersManagerEntity;
import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.crm.entity.tableStructure.XsCstAttachTableEntity;
import com.zs.crm.entity.tableStructure.XsCstAttrTableEntity;
import com.zs.crm.entity.tableStructure.XsCstTableEntity;
import com.zs.crm.entity.tableStructure.XsEmployeeTableEntity;
import com.zs.crm.entity.tableStructure.XsOpp2CstTableEntity;
import com.zs.crm.entity.tableStructure.XsOpp2GjjlTableEntity;
import com.zs.crm.entity.tableStructure.XsOpp2RoomTableEntity;
import com.zs.crm.entity.tableStructure.XsOppTableEntity;
import com.zs.crm.service.XsCstInfoService;
import com.zs.crm.service.XsCustomAllocateService;
import com.zs.oa.entity.OaUserEntity;
import com.zs.oa.service.OaUserService;
import com.zs.oa.service.impl.OaUserServiceImpl;
import com.zs.rbac.core.RBACSubject;
import com.zs.rbac.entity.User;
import com.zs.rbac.service.XsTeamGroupService;
import com.zs.rbac.utils.RbacUtils;
import com.zs.test.common.Ajax;
import com.zs.crm.service.impl.XsFollowSearch;
import com.zs.crm.web.vo.XsCustomerFollowingExportVo;
import com.zs.crm.web.vo.XsCustomerImportVo;
import com.zs.crm.web.vo.XsCustomersManagerVo;
import com.zs.crm.web.vo.XsExportVo;
import com.zs.crm.web.vo.XsTeamGroupVo;


@Controller
@RequestMapping(value="/wbem/houses/customer")
public class XsCustomAllocateController {
	
	Log log=LogUtil.getLog();
	@Autowired 
	XsCustomAllocateService xsBusiCustomAllocateService;
	@Autowired
	HttpSession httpSession;
	@Autowired
	IOptionListService listservice;
	@Autowired
	XsTeamGroupService xsTeamGroupService;
	@Autowired
	ZsBusiCustomAllocateService logService;
	@Autowired
	XsCstInfoService xsCstInfo;
	
	//全局变量
	XsExportVo xsexportVo=new XsExportVo();//全局量
	JSONArray assignCusIdsArray=new JSONArray();
	JSONArray oppGuidArray=new JSONArray();
	JSONArray userGuidArray=new JSONArray();
	
	public static Map<String, OaUserEntity> oamap ;
	static{
		OaUserService oaUserService = new OaUserServiceImpl();
		oamap = oaUserService.getAllUserService();
	}
	
	public List<String> loadSearchOption(String module) {
		List<SearchOptionEntity> optionList = new ArrayList<SearchOptionEntity>();
		SearchOptionEntity option = new SearchOptionEntity();
		log.info("module="+module);
		option.setModule(module);
		optionList = listservice.getOptionListByModule(option);
		log.info("size"+optionList.size());
		XsFollowSearch hh = new XsFollowSearch();
		List<String> optionListShow = new ArrayList<String>();
		for(SearchOptionEntity option1:optionList) {
			log.info(option1.getModule());
			log.info(option1.getName());
			log.info("isEdit:"+option1.isEdit());
			TagOption to = hh.generateHtmlEntity(option1);
			String test = hh.jointHtmlStr(to);
			optionListShow.add(test);
		}
			return optionListShow;	
	}
	//进入逾期客户页面
	@RequiresPermissions(value="page:wbem:crm:xs:custAssignOverdue")
	@RequestMapping(value="/custAssignOverdue")
	public String page_CustAssignOverdue(Model model){
		List<String> optionlist = new ArrayList<String>();
		optionlist = loadSearchOption("xsovercussearch");
		httpSession.setAttribute("overListShow", optionlist);
		log.info("zsbusiCustOverdue");
		
		//List<XsTeamGroupEntity> projectList = xsTeamGroupService.getProjectItem();
		//model.addAttribute("projectList", projectList);
		XsTeamGroupVo searchInfo = new XsTeamGroupVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		searchInfo.setGroupType("xs");
		searchInfo.setUserLevelId("2");
		searchInfo.setProperty("xs_userGuid");
		searchInfo.setProjectId(projGuid);
		List<XsTeamGroupEntity> zygwList = xsTeamGroupService.getUserFromTeamGroup(searchInfo);
		model.addAttribute("zygwList", zygwList);
		return "/wbem/houses/customer/custAssignOverdue";
	}
	//进入无效客户页面
	@RequiresPermissions(value="page:wbem:crm:xs:custAssignInvalid")
	@RequestMapping(value="/custAssignInvalid")
	public String page_CustInvalid(Model model){
		List<String> optionlist = new ArrayList<String>();
		optionlist = loadSearchOption("xsinvalidcussearch");
		httpSession.setAttribute("overListShow", optionlist);
		log.info("CustInvalid");
		
		//List<XsTeamGroupEntity> projectList = xsTeamGroupService.getProjectItem();
		//model.addAttribute("projectList", projectList);
		XsTeamGroupVo searchInfo = new XsTeamGroupVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		searchInfo.setGroupType("xs");
		searchInfo.setUserLevelId("2");
		searchInfo.setProperty("xs_userGuid");
		searchInfo.setProjectId(projGuid);
		List<XsTeamGroupEntity> zygwList = xsTeamGroupService.getUserFromTeamGroup(searchInfo);
		model.addAttribute("zygwList", zygwList);
		return "/wbem/houses/customer/custAssignInvalid";
	}
	//进入跟进客户页面
	@RequiresPermissions(value="page:wbem:crm:xs:custAssignFollowing")
	@RequestMapping(value="/custAssignFollowing")
	public String page_CustAssignFollowing(Model model){
		log.info("CustAssignFollowing");
		List<String> optionlist = new ArrayList<String>();
		optionlist = loadSearchOption("xsfollowcussearch");
		httpSession.setAttribute("overListShow", optionlist);
		
		//List<XsTeamGroupEntity> projectList = xsTeamGroupService.getProjectItem();
		//model.addAttribute("projectList", projectList);
		XsTeamGroupVo searchInfo = new XsTeamGroupVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		searchInfo.setGroupType("xs");
		searchInfo.setUserLevelId("2");
		searchInfo.setProperty("xs_userGuid");
		searchInfo.setProjectId(projGuid);
		List<XsTeamGroupEntity> zygwList = xsTeamGroupService.getUserFromTeamGroup(searchInfo);
		model.addAttribute("zygwList", zygwList);
		log.info("pro:"+projGuid+"zygw:"+zygwList.size());
		return "/wbem/houses/customer/custAssignFollowing";
	}
	//进入公共客户页面
	@RequiresPermissions(value="page:wbem:crm:xs:custAssignPublic")
	@RequestMapping(value="/custAssignPublic")
	public String page_CustAssignPublic(Model model){
		log.info("CustAssignPublic");
		/*List<String> optionlist = new ArrayList<String>();
		optionlist = loadSearchOption("xspubliccussearch");
		httpSession.setAttribute("overListShow", optionlist);*/
		//List<XsTeamGroupEntity> projectList = xsTeamGroupService.getProjectItem();
		//model.addAttribute("projectList", projectList);
		XsTeamGroupVo searchInfo = new XsTeamGroupVo();
		//searchInfo.setProjectId("8FFF2136-61EA-E411-BAAF-FCAA145C42F2");
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		searchInfo.setGroupType("xs");
		searchInfo.setUserLevelId("2");
		searchInfo.setProperty("xs_userGuid");
		searchInfo.setProjectId(projGuid);
		List<XsTeamGroupEntity> zygwList = xsTeamGroupService.getUserFromTeamGroup(searchInfo);
		model.addAttribute("zygwList", zygwList);
		return "/wbem/houses/customer/custAssignPublic";
	}
	//进入垃圾箱客户页面
	@RequiresPermissions(value="page:wbem:crm:xs:custAssignDusBin")
	@RequestMapping(value="/custAssignDusBin")
	public String page_CustAssignDusBin(Model model){
		log.info("CustAssignDusBin");
		//List<XsTeamGroupEntity> projectList = xsTeamGroupService.getProjectItem();
		//model.addAttribute("projectList", projectList);
		XsTeamGroupVo searchInfo = new XsTeamGroupVo();
		//searchInfo.setProjectId("8FFF2136-61EA-E411-BAAF-FCAA145C42F2");
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		searchInfo.setGroupType("xs");
		searchInfo.setUserLevelId("2");
		searchInfo.setProperty("xs_userGuid");
		searchInfo.setProjectId(projGuid);
		List<XsTeamGroupEntity> zygwList = xsTeamGroupService.getUserFromTeamGroup(searchInfo);
		model.addAttribute("zygwList", zygwList);
		return "/wbem/houses/customer/custAssignDusBin";
	}
	//进入分配客户页面
	@RequiresPermissions(value="page:wbem:crm:xs:custAssignAllocate")
	@RequestMapping(value="/custAssignAllocate")
	public String page_allocateCustomer(@RequestParam String cusid){
		log.info("fepei"+cusid);
		httpSession.setAttribute("allocateCusId", cusid);
		return "/wbem/houses/customer/custAssignAllocate";
		
	};

	//点击批量分配客户-进入置业顾问列表页
	@RequiresPermissions(value="page:wbem:crm:xs:custAssignAllocate")
	@RequestMapping(value="/dupl_custAssignAllocate")
	public String allocateCustomers(@RequestParam String cusidlist){
		httpSession.setAttribute("allocateCusId", cusidlist);
		return "/wbem/houses/customer/custAssignAllocate";
		
	};

	//进入客户分配--所有日志页面
	@RequiresPermissions(value="page:wbem:crm:xs:custAssignLog")
	@RequestMapping(value="/custAssignLog")
	public String page_CustAssignLog(@RequestParam String cusid) {
		log.info("huishou"+cusid);
		httpSession.setAttribute("custid_log", cusid);
		return "/wbem/houses/customer/custAssignLog";
		//return new ModelAndView("/wbem/houses/customer/custAssignLog","cusid",cusid);
		
	}
	/**
	 * 
	 * 客户基本信息(dailog page)
	 * @return
	 */
	@RequiresPermissions(value="page:wbem:crm:xs:custAssignLogInfo")
	@RequestMapping(value="/dailog/custDetail")
	public String custDetail(@RequestParam String cusid,String cstType,/*String proId,*/Model model) {
		log.info(cusid);
		log.info(cstType);
		XsCustomersManagerEntity list=xsBusiCustomAllocateService.getCustomInfoByCusId(cusid);
		log.info(list.getMobileTel());
		model.addAttribute("custDetail",list);
		httpSession.setAttribute("custLogID", cusid);
		httpSession.setAttribute("custType", cstType);
		//httpSession.setAttribute("proId", proId);
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		XsCustomersManagerVo xsinfo=new XsCustomersManagerVo();
		xsinfo.setCstGuid(cusid);
		xsinfo.setProjGUID(projGuid);
		List<XsCustomersManagerEntity> custList = new ArrayList<XsCustomersManagerEntity>();
		if("public".equals(cstType)){
			custList =  xsBusiCustomAllocateService.getZsCustPublicDao(xsinfo);
			//model.addAttribute("custDetail2",custList.get(0));
		}else if("dusbin".equals(cstType)){
			custList =  xsBusiCustomAllocateService.getZsCustDusBinDao(xsinfo);
			
		}else if("cusFollow".equals(cstType)){
			custList =  xsBusiCustomAllocateService.getZsCustFollowingDao(xsinfo);
		}else if("cusOverDue".equals(cstType)){
			custList =  xsBusiCustomAllocateService.getZsOverdueCustInfoDao(xsinfo);
			
		}else if("cusInvalid".equals(cstType)){
			custList =  xsBusiCustomAllocateService.getZsCusInvalidDao(xsinfo);
			
		}
		model.addAttribute("custDetail2",custList.get(0));
		return "/wbem/houses/customer/dailog/custDetail";
	}
	
	/**
	 * 客户变更记录(dailog page)
	 * @return
	 */
	@RequiresPermissions(value="page:sale:saleManage:custAllocation:custAssignLogChange")
	@RequestMapping(value="/dailog/custChangeLog")
	public String custChangeLog(Model model) {
		return "/wbem/houses/customer/dailog/custChangeLog";
	}
	
	/**
	 * 客户分配日志(dailog page)
	 * @return
	 */
	@RequiresPermissions(value="page:wbem:crm:xs:custAssignLogAllocate")
	@RequestMapping(value="/dailog/custAssignLog")
	public String custAssignLog() {
		log.info("ffeage");
		return "/wbem/houses/customer/dailog/custAssignLog";
	}
	
	/**
	 * 客户回收日志(dailog page)
	 * @return
	 */
	@RequiresPermissions(value="page:wbem:crm:xs:custAssignLogReback")
	@RequestMapping(value="/dailog/custRecycleLog")
	public String custRecycleLog() {
		log.info("gyhhegds");
		return "/wbem/houses/customer/dailog/custRecycleLog";
	}
	
	
	//进入客户分配--客户基本信息页面
	@RequiresPermissions(value="page:wbem:crm:xs:custAssignLogInfo")
	@RequestMapping(value="/custAssignLogInfo")
	public String page_CustAssignLogInfo() {
		return "/wbem/houses/customer/custAssignLogInfo";
		//return new ModelAndView("/wbem/houses/customer/custAssignLogInfo","cusid",cusid);
		
	}
	//进入客户分配--分配日志页面
	@RequiresPermissions(value="page:wbem:crm:xs:custAssignLogAllocate")
	@RequestMapping(value="/custAssignLogAllocate")
	public String page_zsCustLogAllocate() {
		return "/wbem/houses/customer/custAssignLogAllocate";
		
	}
	//进入客户分配--回收日志页面
	@RequiresPermissions(value="page:wbem:crm:xs:custAssignLogReback")
	@RequestMapping(value="/custAssignLogReback")
	public String page_zsCustLogReback() {
		return "/wbem/houses/customer/custAssignLogReback";
		
	}
	
//----------------------------------进入page-------------------------------------------------//	
//获得逾期客户
	@RequestMapping(value="/getZsbusiCustomOverDue")
	@ResponseBody
	public Map<String,Object> getZsbusiCustomOverDue(HttpServletRequest request,/*String proId,*/String userId,String telOrName,String overDays,String startDays,String endDays,String status,String gfyx,String startDate,String endDate) throws UnsupportedEncodingException{
		 
		int dataNum=0;
		XsCustomersManagerVo xsInfo= new XsCustomersManagerVo();
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		List<XsCustomersManagerEntity> custList = new ArrayList<XsCustomersManagerEntity>();
		DataTablesParameters para= RequestUtil.getDTPara(request);
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		xsInfo.setStartIndex(para.getStart());
		xsInfo.setLength(para.getLength());
		xsInfo.setProjGUID(projGuid);
		xsInfo.setUserGuid(userId);
		para.setColumnArray(new String[]{"","","","","","gfyx","lastDate","","overDays","",""});
		xsInfo.setSortName(para.getOrderColumn());
		xsInfo.setSortDir(para.getOrderDir());
		if(status != null && !"".equals(status)) {
			xsInfo.setCstStatus(URLDecoder.decode(status, "utf-8"));
		}
		if(gfyx !=null && !"".equals(gfyx)) {
			xsInfo.setGfyx(URLDecoder.decode(gfyx, "utf-8"));
			log.info("gfyx:"+URLDecoder.decode(gfyx, "utf-8"));
		}
		if(telOrName !=null && !"".equals(telOrName)){//根据客户名称和电话模糊查询
			telOrName = URLDecoder.decode(telOrName, "utf-8");
			boolean isNum = telOrName.matches("[0-9]+");
			if(isNum){
				xsInfo.setMobileTel(telOrName);
			}else{
				xsInfo.setCstName(telOrName);;
			}
		}
		switch(overDays){
			case "0":
				xsInfo.setStartDays("1");
				xsInfo.setEndDays("10");//逾期天数1-10天
				break;
			case "1":
				xsInfo.setStartDays("11");
				xsInfo.setEndDays("20");//逾期天数11-20天
				break;
			case "2":
				xsInfo.setStartDays("21");//逾期天数20天以上
				break;
			case "3":
				xsInfo.setStartDays(startDays);
				xsInfo.setEndDays(endDays);//逾期天数自定义
				break;
		}
		xsInfo.setStartTime(startDate);
		xsInfo.setEndTime(endDate);
		dataNum = xsBusiCustomAllocateService.getZsCustCountDao("逾期客户",xsInfo);
		custList =  xsBusiCustomAllocateService.getZsOverdueCustInfoDao(xsInfo);
		xsInfo.setLength(0);
		List<XsCustomersManagerEntity> exportList =  xsBusiCustomAllocateService.getZsOverdueCustInfoDao(xsInfo);
		xsexportVo.setList(exportList);
		int i=para.getStart()+1;
		for(XsCustomersManagerEntity cust:custList) {
			String lastDateOld = cust.getLastDate();
			String lastDateNew = DateUtil.dateToStringSecond(lastDateOld);
			Map<String,String> custInfo = new HashMap<String,String>();
			custInfo.put("index","<label class='checkbox inline index'> <input type='checkbox' name='radios' />"+i+"</label>");
			custInfo.put("cstName",cust.getCstName());
			String tel="";
			if(cust.getMobileTel()!=null && !cust.getMobileTel().equals(""))
				tel += "(M)"+cust.getMobileTel();
			if(cust.getHomeTel()!=null && !cust.getHomeTel().equals(""))
				tel += "(H)"+cust.getHomeTel();
			if(cust.getOfficeTel()!=null && !cust.getOfficeTel().equals(""))
				tel += "(O)"+cust.getOfficeTel();
			if(cust.getFax()!=null && !cust.getFax().equals(""))
				tel += "(F)"+cust.getFax();
			custInfo.put("mobileTel",tel);
			custInfo.put("userName",cust.getUserName());
			custInfo.put("status",cust.getStatus());
			custInfo.put("gfyx", cust.getGfyx());
			custInfo.put("lastDate",lastDateNew);//modify by jixiaohang
			custInfo.put("gjfs",cust.getGjfs());
			custInfo.put("overDays", cust.getOverDays());
			custInfo.put("operate", cust.getCstGuid());
			custInfo.put("id",cust.getCstGuid());
			custInfo.put("oppGUID",cust.getOppGUID());
			custInfo.put("userGuid",cust.getUserGuid());
			i++;
			lst.add(custInfo);
		}
		Map<String,Object> jsonResult = new HashMap<String,Object>();
		jsonResult.put("draw", para.getDraw());
		jsonResult.put("recordsTotal", dataNum);
		jsonResult.put("recordsFiltered", dataNum);
		jsonResult.put("data",lst);
		return jsonResult;
	}

//获得无效客户
	@RequestMapping(value="/getZsbusiCustomInvalid")
	@ResponseBody
	public Map<String,Object> getZsbusiCustomInvalid(HttpServletRequest request,/*String proId,*/String telOrName) throws UnsupportedEncodingException{
		int dataNum=0;
		XsCustomersManagerVo xsInfo= new XsCustomersManagerVo();
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		List<XsCustomersManagerEntity> custList = new ArrayList<XsCustomersManagerEntity>();
		DataTablesParameters para= RequestUtil.getDTPara(request);
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		xsInfo.setStartIndex(para.getStart());
		xsInfo.setLength(para.getLength());
		xsInfo.setProjGUID(projGuid);
		para.setColumnArray(new String[]{"","","","","","","gfyx","lastDate","","",""});
		xsInfo.setSortName(para.getOrderColumn());
		xsInfo.setSortDir(para.getOrderDir());
		if(telOrName !=null && !"".equals(telOrName)){//根据客户名称和电话模糊查询
			telOrName = URLDecoder.decode(telOrName, "utf-8");
			boolean isNum = telOrName.matches("[0-9]+");
			if(isNum){
				xsInfo.setMobileTel(telOrName);
			}else{
				xsInfo.setCstName(telOrName);;
			}
		}
		dataNum = xsBusiCustomAllocateService.getZsCustCountDao("无效客户",xsInfo);
		custList =  xsBusiCustomAllocateService.getZsCusInvalidDao(xsInfo);
		xsInfo.setLength(0);
		List<XsCustomersManagerEntity>exportList =  xsBusiCustomAllocateService.getZsCusInvalidDao(xsInfo);
		xsexportVo.setList(exportList);
		int i=para.getStart()+1;
		for(XsCustomersManagerEntity cust:custList) {
			//modify by jixiaohang
			String lastDateOld = cust.getLastDate();
			String lastDateNew = DateUtil.dateToStringSecond(lastDateOld);
			//modify by jixiaohang
			Map<String,String> custInfo = new HashMap<String,String>();
			custInfo.put("index","<label class='checkbox inline index'> <input type='checkbox' name='radios' />"+i+"</label>");
			custInfo.put("cstName",cust.getCstName());
			String tel="";
			if(cust.getMobileTel()!=null && !cust.getMobileTel().equals(""))
				tel += "(M)"+cust.getMobileTel();
			if(cust.getHomeTel()!=null && !cust.getHomeTel().equals(""))
				tel += "(H)"+cust.getHomeTel();
			if(cust.getOfficeTel()!=null && !cust.getOfficeTel().equals(""))
				tel += "(O)"+cust.getOfficeTel();
			if(cust.getFax()!=null && !cust.getFax().equals(""))
				tel += "(F)"+cust.getFax();
			custInfo.put("mobileTel",tel);
			custInfo.put("userName",cust.getUserName());
			custInfo.put("status", cust.getStatus());
			custInfo.put("invalidReason", cust.getInvalidReason());
			custInfo.put("gfyx", cust.getGfyx());
			custInfo.put("lastDate", lastDateNew);
			custInfo.put("gjfs", cust.getGjfs());
			custInfo.put("operate", cust.getCstGuid());
			custInfo.put("id",cust.getCstGuid());
			custInfo.put("oppGUID",cust.getOppGUID());
			custInfo.put("userGuid",cust.getUserGuid());
			i++;
			lst.add(custInfo);
		}
		Map<String,Object> jsonResult = new HashMap<String,Object>();
		jsonResult.put("draw", para.getDraw());
		jsonResult.put("recordsTotal", dataNum);
		jsonResult.put("recordsFiltered", dataNum);
		jsonResult.put("data",lst);
		return jsonResult;
	}
	
//获得跟进中客户
	@RequestMapping(value="/getZsbusiCustomFollowing")
	@ResponseBody
	public Map<String,Object> getZsbusiCustomFollowing(HttpServletRequest request,/*String proId,*/String userId,String telOrName,String status,String gfyx,String createdOn,String startCreate,String endCreate,String userName) throws UnsupportedEncodingException{
		try {
			userName=URLDecoder.decode(userName,"utf-8");
		} catch (Exception e) {
			log.info(e);
		}
		int dataNum=0;
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		List<XsCustomersManagerEntity> custList = new ArrayList<XsCustomersManagerEntity>();
		
		DataTablesParameters para= RequestUtil.getDTPara(request);
		XsCustomersManagerVo xsInfo = new XsCustomersManagerVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		xsInfo.setStartIndex(para.getStart());
		xsInfo.setLength(para.getLength());
		xsInfo.setProjGUID(projGuid);
		xsInfo.setUserGuid(userId);
		xsInfo.setUserName(userName);
		para.setColumnArray(new String[]{"","","","","","gfyx","lastDate","","","","",""});
		xsInfo.setSortName(para.getOrderColumn());
		xsInfo.setSortDir(para.getOrderDir());
		if(status!=null && !status.equals("")){
			xsInfo.setCstStatus(URLDecoder.decode(status,"utf-8"));
		}
		if(gfyx!=null && !gfyx.equals("")) {
			xsInfo.setGfyx(URLDecoder.decode(gfyx,"utf-8"));
		}
		xsInfo.setCreatedOn(createdOn);//创建时间需要处理
		//xsInfo.setCstName(URLDecoder.decode(cstName,"utf-8"));
		if(telOrName !=null && !"".equals(telOrName)){//根据客户名称和电话模糊查询
			telOrName = URLDecoder.decode(telOrName, "utf-8");
			boolean isNum = telOrName.matches("[0-9]+");
			if(isNum){
				xsInfo.setMobileTel(telOrName);
			}else{
				xsInfo.setCstName(telOrName);;
			}
		}
		if(createdOn != null && !"".equals(createdOn)) {
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
			switch(createdOn) {
				case "0":
					cal.add(Calendar.DAY_OF_MONTH,-1);
					startCreate = fm.format(cal.getTime())+" 00:00:00";
					endCreate = fm.format(cal.getTime())+" 23:59:59";
					break;
				case "1":
					startCreate = fm.format(cal.getTime())+" 00:00:00";
					endCreate = fm.format(cal.getTime())+" 23:59:59";
					break;
				case "2":
					int day = cal.get(Calendar.DAY_OF_WEEK);
					if(day == 1) {
						endCreate = fm.format(cal.getTime())+" 23:59:59";
						cal.add(Calendar.DAY_OF_MONTH, -6);
						startCreate = fm.format(cal.getTime())+" 00:00:00";
					}else {
						cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
						startCreate = fm.format(cal.getTime())+" 00:00:00";
						cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
						cal.add(Calendar.WEEK_OF_MONTH, 1);
						endCreate = fm.format(cal.getTime())+" 23:59:59";
					}
					break;
				case "3":
					cal.set(Calendar.DAY_OF_MONTH, 1);
					startCreate = fm.format(cal.getTime())+" 00:00:00";
					cal.set(Calendar.DATE,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
					endCreate = fm.format(cal.getTime())+" 23:59:59";
					break;
				case "4":
					break;
			}
			xsInfo.setStartCreate(startCreate);
			xsInfo.setEndCreate(endCreate);
		}
		//xsInfo.setSortName("lastDate");
		//xsInfo.setSortDir("desc");
		//log.info("projGuid"+projGuid+"userId:"+userId+"telOrName:"+telOrName+"createdOn:"+createdOn);
		dataNum = xsBusiCustomAllocateService.getZsCustCountDao("跟进中客户",xsInfo);
		custList =  xsBusiCustomAllocateService.getZsCustFollowingDao(xsInfo);
		xsInfo.setLength(0);
		List<XsCustomersManagerEntity> exportList =  xsBusiCustomAllocateService.getZsCustFollowingDao(xsInfo);
		//log.info(custList.size());
		xsexportVo.setList(exportList);
		int i=para.getStart()+1;
		for(XsCustomersManagerEntity cust:custList) {
			String lastDateOld = cust.getLastDate();
			String lastDateNew = DateUtil.dateToStringSecond(lastDateOld);
			String createdOnOld = cust.getCreatedOn();
			String createdOnNew = DateUtil.dateToStringSecond(createdOnOld);
			String nextDateOld = cust.getNextDate();
			String nextDateNew="";
			if(nextDateOld !=null ){
				 nextDateNew = DateUtil.dateToStringSecond(nextDateOld);
			}
			Map<String,String> custInfo = new HashMap<String,String>();
			custInfo.put("index","<label class='checkbox inline index'> <input type='checkbox' name='radios' />"+i+"</label>");
			custInfo.put("cstName",cust.getCstName());
			String tel="";
			if(cust.getMobileTel()!=null && !cust.getMobileTel().equals(""))
				tel += "(M)"+cust.getMobileTel();
			if(cust.getHomeTel()!=null && !cust.getHomeTel().equals(""))
				tel += "(H)"+cust.getHomeTel();
			if(cust.getOfficeTel()!=null && !cust.getOfficeTel().equals(""))
				tel += "(O)"+cust.getOfficeTel();
			if(cust.getFax()!=null && !cust.getFax().equals(""))//2016-7-23 Add
				tel += "(F)"+cust.getFax();
			custInfo.put("mobileTel",tel);
			custInfo.put("userName",cust.getUserName());
			custInfo.put("status", cust.getStatus());
			custInfo.put("gfyx",cust.getGfyx());
			custInfo.put("lastDate",lastDateNew); 
			custInfo.put("gjfs",cust.getGjfs());
			custInfo.put("createdOn",createdOnNew);
			custInfo.put("nextDate", nextDateNew);
			custInfo.put("operate", cust.getCstGuid());
			custInfo.put("id",cust.getCstGuid());
			custInfo.put("oppGUID",cust.getOppGUID());
			custInfo.put("userGuid", cust.getUserGuid());
			i++;
			lst.add(custInfo);
		}
		Map<String,Object> jsonResult = new HashMap<String,Object>();
		jsonResult.put("draw", para.getDraw());
		jsonResult.put("recordsTotal", dataNum);
		jsonResult.put("recordsFiltered", dataNum);
		jsonResult.put("data",lst);
		return jsonResult;
	}
	//获得公共客户
		@RequestMapping(value="/getZsbusiCustomPublic")
		@ResponseBody
		public Map<String,Object> getZsbusiCustomPublic(HttpServletRequest request,String cstStatus,String gfyx,String rebackReason,String userGuid,String telOrName,/*String proId,*/String invalidReason){
			DataTablesParameters para= RequestUtil.getDTPara(request);
			XsCustomersManagerVo xsinfo=new XsCustomersManagerVo();
			Session session = RBACSubject.getSecurityUtils().getSession();
			User user = (User)session.getAttribute("user");
			String projGuid = user.getExtInfo("projGuid");
			try {
				cstStatus=URLDecoder.decode(cstStatus,"UTF-8");
				gfyx=URLDecoder.decode(gfyx,"UTF-8");
				rebackReason=URLDecoder.decode(rebackReason,"UTF-8");
				/*xsinfo.setStartIndex(para.getStart());
				xsinfo.setLength(para.getLength());*/
				xsinfo.setCstStatus(cstStatus);
				xsinfo.setGfyx(gfyx);
				String allRebackReason = "";
				if(rebackReason!=null && !rebackReason.equals("")) {
					String[] rebackArray = rebackReason.split(",");
					for(int i=0; i<rebackArray.length; i++){
						allRebackReason += "'"+rebackArray[i]+"'";
						if(i != rebackArray.length-1) {
							allRebackReason += ",";
						}
					}
				}
				
				xsinfo.setRebackReason(allRebackReason);
				//xsinfo.setUserGuid(userGuid);
				xsinfo.setOldUserGuid(userGuid);
				xsinfo.setProjGUID(projGuid);
				para.setColumnArray(new String[]{"","","","","","gfyx","lastDate","","","","",""});
				xsinfo.setSortName(para.getOrderColumn());
				xsinfo.setSortDir(para.getOrderDir());
				if(telOrName !=null && !"".equals(telOrName)){//根据客户名称和电话模糊查询 
			         boolean isNum = telOrName.matches("[0-9]+"); 
			         if(isNum){ 
			        	 xsinfo.setMobileTel(telOrName); 
			         }else{ 
			        	String cstName=URLDecoder.decode(telOrName,"UTF-8");
			        	xsinfo.setCstName(cstName);
			         } 
			      }
				xsinfo.setInvalidReason(invalidReason);//2016-7-6
			} catch (UnsupportedEncodingException e) {
				log.info(e);
			}
			//int recordsTotal = xsBusiCustomAllocateService.getZsCustPubDupCountDao("公共客户",xsinfo);2016-7-17
			int recordsTotal = xsBusiCustomAllocateService.getZsCustPublicDao(xsinfo).size();
			int recordsFiltered=recordsTotal;
			log.info(recordsTotal);
			List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
			List<XsCustomersManagerEntity> custList = new ArrayList<XsCustomersManagerEntity>();
			xsinfo.setStartIndex(para.getStart());
			xsinfo.setLength(para.getLength());//2016-7-17
			custList =  xsBusiCustomAllocateService.getZsCustPublicDao(xsinfo);
			xsinfo.setLength(0);
			List<XsCustomersManagerEntity> exportList=xsBusiCustomAllocateService.getZsCustPublicDao(xsinfo);
			xsexportVo.setList(exportList);
			log.info(custList.size());
			int i=para.getStart()+1;
			int rebackCount=0;
			//ZsBusiCustomLogTable rebackInfo;
			for(XsCustomersManagerEntity cust:custList) {
				String lastDateOld = cust.getLastDate();
				String lastDateNew = DateUtil.dateToStringSecond(lastDateOld);
				Map<String,String> custInfo = new HashMap<String,String>();
				custInfo.put("index","<label class='checkbox inline index'> <input type='checkbox' name='radios' />"+i+"</label>");
				custInfo.put("cstName",cust.getCstName());
				String tel="";
				if(cust.getMobileTel()!=null && !cust.getMobileTel().equals(""))
					tel += "(M)"+cust.getMobileTel();
				if(cust.getHomeTel()!=null && !cust.getHomeTel().equals(""))
					tel += "(H)"+cust.getHomeTel();
				if(cust.getOfficeTel()!=null && !cust.getOfficeTel().equals(""))
					tel += "(O)"+cust.getOfficeTel();
				if(cust.getFax()!=null && !cust.getFax().equals(""))//2016-7-23 Add
					tel += "(F)"+cust.getFax();
				custInfo.put("mobileTel",tel);
				custInfo.put("oldZygw",cust.getOldZygw());
				custInfo.put("status",cust.getStatus());
				custInfo.put("gfyx",cust.getGfyx());
				custInfo.put("lastDate",lastDateNew);
				custInfo.put("gjfs",cust.getGjfs());
				custInfo.put("id",cust.getCstGuid());
				custInfo.put("oppGUID",cust.getOppGUID());
				custInfo.put("userGuid",cust.getUserGuid());
				rebackCount=xsBusiCustomAllocateService.getCusReBackCountByCusId(cust.getCstGuid(), "回收日志");
				/*if(rebackCount==0){
					custInfo.put("rebackReason","");
					custInfo.put("rebackDate","");
					custInfo.put("rebackNums","0");
				}else{
					rebackInfo=xsBusiCustomAllocateService.getCusReBackInfoByCusId(cust.getCstGuid(), "回收日志");
					String rebackDateOld = rebackInfo.getOperDate();
					String rebackDateNew =DateUtil.dateToStringSecond(rebackDateOld);
					custInfo.put("rebackReason",rebackInfo.getReason());
					custInfo.put("rebackDate",rebackDateNew);
					custInfo.put("rebackNums",rebackCount+"");
				}*/
				custInfo.put("rebackReason",cust.getRebackReason());
				custInfo.put("rebackDate",cust.getRebackDate());
				//custInfo.put("rebackNums",cust.getRebackNums());
				custInfo.put("rebackNums",rebackCount+"");
				i++;
				lst.add(custInfo);
			}
			Map<String,Object> jsonResult = new HashMap<String,Object>();
			jsonResult.put("draw", para.getDraw());
			jsonResult.put("recordsTotal", recordsTotal);
			jsonResult.put("recordsFiltered", recordsFiltered);
			jsonResult.put("data",lst);
			return jsonResult;
		}
	
//获得垃圾箱客户

	@RequestMapping(value="/getCustomDusBin")
	@ResponseBody
	public Map<String,Object>  getZsbusiCustomDusBin(HttpServletRequest request,String cstStatus,String gfyx,String rebackReason,String userGuid,String telOrName/*,String proId*/){
		//log.info("proid "+proId);
		DataTablesParameters para= RequestUtil.getDTPara(request);
		XsCustomersManagerVo xsinfo=new XsCustomersManagerVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		try {
			cstStatus=URLDecoder.decode(cstStatus,"UTF-8");
			gfyx=URLDecoder.decode(gfyx,"UTF-8");
			rebackReason=URLDecoder.decode(rebackReason,"UTF-8");
			xsinfo.setStartIndex(para.getStart());
			xsinfo.setLength(para.getLength());
			xsinfo.setCstStatus(cstStatus);
			xsinfo.setGfyx(gfyx);
			xsinfo.setRebackReason(rebackReason);
			//xsinfo.setUserGuid(userGuid);
			xsinfo.setOldUserGuid(userGuid);//检索原置业顾问
			xsinfo.setProjGUID(projGuid);
			para.setColumnArray(new String[]{"","","","","","gfyx","lastDate","","","","",""});
			log.info(para.getOrderColumn());
			log.info(para.getOrderDir());
			xsinfo.setSortName(para.getOrderColumn());
			xsinfo.setSortDir(para.getOrderDir());
			if(telOrName !=null && !"".equals(telOrName)){//根据客户名称和电话模糊查询 
		         boolean isNum = telOrName.matches("[0-9]+"); 
		         if(isNum){ 
		        	 xsinfo.setMobileTel(telOrName); 
		         }else{ 
		        	String cstName=URLDecoder.decode(telOrName,"UTF-8");
		        	xsinfo.setCstName(cstName);
		         } 
		      }
			//xsinfo.setSortName("lastDate");
			//xsinfo.setSortDir("desc");
		} catch (UnsupportedEncodingException e) {
			log.info(e);
		}
		int recordsTotal = xsBusiCustomAllocateService.getZsCustPubDupCountDao("垃圾箱",xsinfo);
		int recordsFiltered=recordsTotal;
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		List<XsCustomersManagerEntity> custList = new ArrayList<XsCustomersManagerEntity>();
		custList =  xsBusiCustomAllocateService.getZsCustDusBinDao(xsinfo);
		int i=para.getStart()+1;
		for(XsCustomersManagerEntity cust:custList) {
			String lastDateOld = cust.getLastDate();
			String lastDateNew = DateUtil.dateToStringSecond(lastDateOld);
			Map<String,String> custInfo = new HashMap<String,String>();
			custInfo.put("index","<label class='checkbox inline index'> <input type='checkbox' name='radios' />"+i+"</label>");
			custInfo.put("cstName",cust.getCstName());
			String tel="";
			if(cust.getMobileTel()!=null && !cust.getMobileTel().equals(""))
				tel += "(M)"+cust.getMobileTel();
			if(cust.getHomeTel()!=null && !cust.getHomeTel().equals(""))
				tel += "(H)"+cust.getHomeTel();
			if(cust.getOfficeTel()!=null && !cust.getOfficeTel().equals(""))
				tel += "(O)"+cust.getOfficeTel();
			if(cust.getFax()!=null && !cust.getFax().equals(""))
				tel += "(F)"+cust.getFax();
			custInfo.put("mobileTel",tel);
			custInfo.put("oldZygw",cust.getOldZygw());
			custInfo.put("status",cust.getStatus());
			custInfo.put("gfyx",cust.getGfyx());
			custInfo.put("lastDate",lastDateNew);
			custInfo.put("gjfs",cust.getGjfs());
			custInfo.put("rebackReason","");
			custInfo.put("rebackDate","");
			custInfo.put("rebackNums","");
			custInfo.put("id",cust.getCstGuid());
			custInfo.put("oppGUID",cust.getOppGUID());
			custInfo.put("userGuid",cust.getUserGuid());
			i++;
			lst.add(custInfo);
		}
		Map<String,Object> jsonResult = new HashMap<String,Object>();
		jsonResult.put("draw", para.getDraw());
		jsonResult.put("recordsTotal", recordsTotal);
		jsonResult.put("recordsFiltered", recordsFiltered);
		jsonResult.put("data",lst);
		return jsonResult;
	}	
//-----------------------------------页面初始化------------------------------------------//	
	
	@RequestMapping(value="/custAssignInfo")
	@ResponseBody
	public ModelAndView custAssignInfo(@RequestParam String cusid) {
		return new ModelAndView("/wbem/houses/customer/custAssignInfo","cusid",cusid);
	}


	//分配客户
	@RequestMapping(value="/allocateCustomer")
	@ResponseBody
	public String allocateCustomer(@RequestParam String data){
		JSONObject object =JSONObject.fromObject(data);
		XsCustomersManagerVo vo=(XsCustomersManagerVo)JSONObject.toBean(object, XsCustomersManagerVo.class);
		JSONArray cusIdArray=getAssignCusIdsArray();
		JSONArray oppGuidArray=getOppGuidArray();
		JSONArray userGuidArray=getUserGuidArray();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		log.info(cusIdArray.size());
		
		for (int i = 0; i < cusIdArray.size(); i++) {
			log.info(cusIdArray.get(i));
		}
		int k=-1;
		if(cusIdArray.size()==oppGuidArray.size() && cusIdArray.size()>0){
			//分配前把置业顾问的名字缓冲起来
			Map<String, String> map=new HashMap<String,String>();
			String beforeName="";
			for (int i = 0; i < oppGuidArray.size(); i++) {
				XsCustomersManagerEntity beflist=xsBusiCustomAllocateService.getZygwByoppGUID(oppGuidArray.getString(i));
				beforeName=beflist.getZygw();
				map.put(oppGuidArray.getString(i), beforeName);
			}
			//分配过程...
			for (int i = 0; i < cusIdArray.size(); i++) {
				k=xsBusiCustomAllocateService.allocateCustomers(userGuidArray.getString(i),vo.getUserGuid(),cusIdArray.getString(i),projGuid,oppGuidArray.getString(i));
			}
			//分配后把职业顾问的名字缓冲起来。
			Map<String, String> _map=new HashMap<String,String>();
			String afterName="";
			for (int i = 0; i < oppGuidArray.size(); i++) {
				XsCustomersManagerEntity beflist=xsBusiCustomAllocateService.getZygwByoppGUID(oppGuidArray.getString(i));
				afterName=beflist.getZygw();
				_map.put(oppGuidArray.getString(i), afterName);
			}
			//钉钉提醒
			User user2 = (User)RbacUtils.subject().getSession().getAttribute("user");
			String operator = user2.getRealName();
			Map<String, Object>ddMap=DingUtil.getDDmap(userGuidArray, oppGuidArray);
			Set<Entry<String, Object>>sets=ddMap.entrySet();
			log.info(sets.size());
			for (Entry<String, Object> entry : sets) {
				DingDingVo d=(DingDingVo)entry.getValue();
				String bname=map.get(d.getOppguid());
				String aname=_map.get(d.getOppguid());
				log.info(entry.getKey()+" "+d.getCount()+" "+d.getOppguid()+" "+map.get(d.getOppguid())+" "+_map.get(d.getOppguid()));
				String str0 = operator+"手动将";
				String str1=bname+"的";
				String str1_1=Integer.toString(d.getCount());
				String str2="个客户分配给"+aname;
				if(operator.length()%2==1){//奇数
					str0 = operator+"手动将";
				}else{//偶数
					str0 = operator+"：手动将";
				}
				if(bname.length()%2==1){//奇数
					str1=bname+"的";
				}else{//偶数
					str1=bname+"的共";
				}
				if(aname.length()%2==1){//奇数
					str2="个客户分配给："+aname;
				}else{//偶数
					str2="个客户分配给"+aname;
				}
				try {
					int uid=oamap.get(bname).uid;
					String duid=Integer.toString(uid);
					byte[] bytes0 = str0.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
		            str0 = new String(bytes0, "GBK");//to GBK string  
		            byte[] bytes1 = str1.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
		            str1 = new String(bytes1, "GBK");//to GBK string 
		            byte[] bytes2 = str2.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
		            str2 = new String(bytes2, "GBK");//to GBK string 
		            DingUtil.sendTextMessage(str0+str1+str1_1+str2, duid, "", "37907377");//正式
				} catch (Exception e) {
					return k+"";
				}
				
			}
			
		}else{
			log.info("出错了");	
		}
		log.info(k);
		return k+"";
		
	};
	//回收客户
	@RequestMapping(value="/reBackCustomer")
	@ResponseBody
	public String reBackCustomer(@RequestParam String data){
		
		int k=0;
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		try {
			JSONObject object =JSONObject.fromObject(data);
			String cusId=object.get("cusId").toString();
			String reason =(String)object.get("reason");
			reason=URLDecoder.decode(reason,"utf-8");
			String userguid = object.get("userguid").toString();
			String oppguid =object.get("oppguid").toString();
			String fromwhere="手动回收";
			boolean isDul=cusId.contains(",");
			XsCstSearchOption bean = new XsCstSearchOption();
			User user2 = (User)RbacUtils.subject().getSession().getAttribute("user");
			String operator = user2.getRealName();
			log.info(isDul);
			if(isDul){
				JSONArray array =JSONArray.fromObject(cusId);
				JSONArray opparray =JSONArray.fromObject(oppguid);
				JSONArray userguidArray =JSONArray.fromObject(userguid);
				log.info(" cusid "+array.size()+" opparray "+opparray.size()+" "+userguidArray.size());
				//回收前把置业顾问的名字缓冲起来
				Map<String, String> bmap=new HashMap<String,String>();
				String beforeName="";
				for (int i = 0; i < opparray.size(); i++) {
					XsCustomersManagerEntity beflist=xsBusiCustomAllocateService.getZygwByoppGUID(opparray.getString(i));
					beforeName=beflist.getZygw();
					log.info(beforeName);
					bmap.put(opparray.getString(i), beforeName);
				}
				for (int i = 0; i < array.size(); i++) {
					bean.setCstGuid(array.getString(i));
					bean.setOppGUID(opparray.getString(i));
					List<XsCstInfo> cstList=xsCstInfo.getXsCstInfoByCstGuid(bean);
					XsCstInfo cst=cstList.get(0);
					k=xsBusiCustomAllocateService.reBackCustomers(array.getString(i),cst.getXsCst().getCstName(),cst.getXsCst().getMobileTel(),reason,fromwhere,projGuid,opparray.getString(i),oamap);
				}
				//回收后钉钉提醒
				Map<String, Object>ddMap=DingUtil.getDDmap(userguidArray, opparray);
				
				Set<Entry<String, Object>>sets=ddMap.entrySet();
				log.info(sets.size());
				for (Entry<String, Object> entry : sets) {
					DingDingVo d=(DingDingVo)entry.getValue();
					String bname=bmap.get(d.getOppguid());
					log.info(entry.getKey()+" "+d.getCount()+" "+d.getOppguid()+" "+bmap.get(d.getOppguid())+" ");
					String str0 = operator+"手动将";
					String str1=bname+"的";
					String str1_1=Integer.toString(d.getCount());
					String str2="个客户回收成公共客户";
					if(operator.length()%2==1){//奇数
						str0 = operator+"手动将";
					}else{//偶数
						str0 = operator+"：手动将";
					}
					if(bname.length()%2==1){//奇数
						str1=bname+"的";
					}else{//偶数
						str1=bname+"的共";
					}
					try {
						int uid=oamap.get(bname).uid;
						String duid=Integer.toString(uid);
						byte[] bytes0 = str0.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
			            str0 = new String(bytes0, "GBK");//to GBK string  
			            byte[] bytes1 = str1.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
			            str1 = new String(bytes1, "GBK");//to GBK string 
			            byte[] bytes2 = str2.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
			            str2 = new String(bytes2, "GBK");//to GBK string 
			            DingUtil.sendTextMessage(str0+str1+str1_1+str2, duid, "", "37907377");//正式
					} catch (Exception e) {
						return k+"";
					}
				}
			}else{
					bean.setCstGuid(cusId);
					bean.setOppGUID(oppguid);
					log.info(cusId);
					List<XsCstInfo> cstList=xsCstInfo.getXsCstInfoByCstGuid(bean);
					XsCstInfo cst=cstList.get(0);
					//回收前先缓冲名字
					XsCustomersManagerEntity beflist=xsBusiCustomAllocateService.getZygwByoppGUID(oppguid);
					k=xsBusiCustomAllocateService.reBackCustomers(cusId,cst.getXsCst().getCstName(),cst.getXsCst().getMobileTel(),reason,fromwhere,projGuid,oppguid,oamap);
					
					
					String bname=beflist.getZygw();
					String str0 = operator+"手动将";
					String str1=bname+"的";
					String str2="1个客户回收成公共客户";
					if(operator.length()%2==1){//奇数
						str0 = operator+"手动将";
					}else{//偶数
						str0 = operator+"：手动将";
					}
					if(bname.length()%2==1){//奇数
						str1=bname+"的";
					}else{//偶数
						str1=bname+"的共";
					}
					try {
						int uid=oamap.get(bname).uid;
						String duid=Integer.toString(uid);
						byte[] bytes0 = str0.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
			            str0 = new String(bytes0, "GBK");//to GBK string  
			            byte[] bytes1 = str1.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
			            str1 = new String(bytes1, "GBK");//to GBK string 
			            byte[] bytes2 = str2.getBytes(Charset.forName("UTF-8"));//convert to byte array with UTF-8 encode  
			            str2 = new String(bytes2, "GBK");//to GBK string 
			            DingUtil.sendTextMessage(str0+str1+str2, duid, "", "37907377");//正式
					} catch (Exception e) {
						return k+"";
					}
			}
		} catch (UnsupportedEncodingException e) {
			log.info(e);
			k=0;
		}
		return k+"";
	};
	@RequestMapping(value="/dusbinCustomers")
	@ResponseBody
	public String dusbinCustomers(@RequestParam String data) {
		JSONObject object =JSONObject.fromObject(data);
		String cusId=object.get("cusId").toString();
		//String proId = (String)object.get("proId");
		String oppguid = object.get("oppguid").toString();
		boolean isDul=cusId.contains(",");
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		int k=0;
		if(isDul){
			JSONArray array =JSONArray.fromObject(cusId);
			JSONArray opparray =JSONArray.fromObject(oppguid);
			for (int i = 0; i < array.size(); i++) {
				k=xsBusiCustomAllocateService.dusbinCustomers(array.getString(i),projGuid,"",opparray.getString(i));
			}
		}else {
				k=xsBusiCustomAllocateService.dusbinCustomers(cusId,projGuid,"",oppguid);
			log.info(cusId);
		}
		return k+"";
		
		
	}
	@RequestMapping(value="/updateZsBasicInfoDao")
	@ResponseBody
	public String updateZsBasicInfoDao(String data) {
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		int k=0;
		try {
			JSONObject object =JSONObject.fromObject(data);
			String cusId=object.get("cusId").toString();
			//String proId=object.get("proId").toString();
			String newMobileTel = (String)object.get("newMobileTel");
			String newHomeTel = (String)object.get("newHomeTel");
			String newOfficeTel = (String)object.get("newOfficeTel");
			String newFax = (String)object.get("newFax");
			String oldMobileTel = (String)object.get("oldMobileTel");
			String oldHomeTel = (String)object.get("oldHomeTel");
			String oldOfficeTel = (String)object.get("oldOfficeTel");
			String oldFax = (String)object.get("oldFax");
			String oldName=(String)object.get("oldName");
			String newName = (String)object.get("newName");
			String oldOperator=(String)object.get("oldOperator");
			oldName=URLDecoder.decode(oldName,"utf-8");
			newName=URLDecoder.decode(newName,"utf-8");
			oldOperator=URLDecoder.decode(oldOperator,"utf-8");
			log.info(oldOperator);
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String modifyDate = fm.format(new Date());
			String followPerson = httpSession.getAttribute("username").toString();
			ZsBusiCustomLogTable logInfo = new ZsBusiCustomLogTable();
			logInfo.setCusId(cusId);
			logInfo.setLogType("1");
			logInfo.setOperator(followPerson);
			logInfo.setOperDate(modifyDate);
			XsCustomersManagerVo vo=new XsCustomersManagerVo();
			vo.setCstGuid(cusId);
			vo.setCstName(newName);
			vo.setMobileTel(newMobileTel);
			vo.setHomeTel(newHomeTel);
			vo.setOfficeTel(newOfficeTel);
			vo.setFax(newFax);
			k=xsBusiCustomAllocateService.updateXsBasicInfo(vo);
			logInfo.setContent("");
			logInfo.setReason("");
			//logInfo.setContent("姓名由"+oldName+"变为"+newName+",手机号由"+oldMobileTel+"变为"+newMobileTel+",家庭号由"+oldHomeTel+"变更为"+newHomeTel+",办公号由"+oldOfficeTel+"变更为"+newOfficeTel);
			//logInfo.setReason("姓名，手机号，家庭号，办公号");
			if(!oldName.equals(newName)){
				logInfo.setContent(logInfo.getContent()+"姓名由"+oldName+"变为"+newName);
				logInfo.setReason(logInfo.getReason()+"姓名,");
			}
			if(!newMobileTel.equals(oldMobileTel)){
				logInfo.setContent(logInfo.getContent()+"手机号由"+oldMobileTel+"变为"+newMobileTel);
				logInfo.setReason(logInfo.getReason()+"手机号,");
			}
			if(!newOfficeTel.equals(oldOfficeTel)){
				logInfo.setContent(logInfo.getContent()+"办公号由"+oldOfficeTel+"变更为"+newOfficeTel);
				logInfo.setReason(logInfo.getReason()+"办公号,");
			}
			if(!newHomeTel.equals(oldHomeTel)){
				logInfo.setContent(logInfo.getContent()+"家庭号由"+oldHomeTel+"变更为"+newHomeTel);
				logInfo.setReason(logInfo.getReason()+"家庭号,");
			}
			if(!newFax.equals(oldFax)){
				logInfo.setContent(logInfo.getContent()+"传真号由"+oldFax+"变更为"+newFax);
				logInfo.setReason(logInfo.getReason()+"传真号");
			}
			logInfo.setProjectId(projGuid);
			logInfo.setBelongSys("xs");
			logInfo.setOldOperator(oldOperator);
			k=logService.insertZsCusChangeLog(logInfo);//此处插入一条修改手机号的修改日志
		} catch (Exception e) {
			log.info(e);
			k=0;
		}
		return k+"";
	}
	//获得置业顾问的信息
	@RequestMapping(value="/getEmployeeList")
	@ResponseBody
	public Map<String,Object> getEmployeeList(HttpServletRequest request,String telOrName) throws UnsupportedEncodingException{
		int dataNum;
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		
		DataTablesParameters para= RequestUtil.getDTPara(request);
		XsTeamGroupVo xsInfo = new XsTeamGroupVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		if(telOrName !=null && !"".equals(telOrName)){//根据客户名称和电话模糊查询 
	         boolean isNum = telOrName.matches("[0-9]+"); 
	         if(isNum){ 
	        	xsInfo.setMobileTel(telOrName); 
	         }else{ 
	        	String cstName=URLDecoder.decode(telOrName,"UTF-8");
	        	xsInfo.setUserName(cstName);
	         } 
	     }
		xsInfo.setGroupType("xs");
		xsInfo.setUserLevelId("2");
		xsInfo.setProperty("xs_userGuid");
		xsInfo.setProjectId(projGuid);
		List<XsTeamGroupEntity> allZygwList = xsTeamGroupService.getUserFromTeamGroup(xsInfo);
		dataNum = allZygwList.size();
		xsInfo.setStartIndex(para.getStart());
		xsInfo.setLength(para.getLength());
		List<XsTeamGroupEntity> zygwList = xsTeamGroupService.getUserFromTeamGroup(xsInfo);
		int i=para.getStart()+1;
		for(XsTeamGroupEntity zygw:zygwList) {
			//log.info(cust.getUserName());
			Map<String,String> zygwInfo = new HashMap<String,String>();
			zygwInfo.put("index",i+""+"<input type='hidden' value="+zygw.getValue()+">");
			zygwInfo.put("userName",zygw.getUserName());
			zygwInfo.put("mobile",zygw.getMobile());
			zygwInfo.put("duty",zygw.getDescription());
			zygwInfo.put("operate","");
			lst.add(zygwInfo);
			i++;
		}
		Map<String,Object> jsonResult = new HashMap<String,Object>();
		jsonResult.put("draw", para.getDraw());
		jsonResult.put("recordsTotal", dataNum);
		jsonResult.put("recordsFiltered", dataNum);
		jsonResult.put("data",lst);
		return jsonResult;
	}
	
	//获得客户详情根据id
	@RequestMapping(value="/getCustomInfoByCusId")
	@ResponseBody
	public XsCustomersManagerEntity getCustomInfoByCusId(Model model){
		String cusid=(String)httpSession.getAttribute("custid_log");
		log.info(cusid);
		XsCustomersManagerEntity list=xsBusiCustomAllocateService.getCustomInfoByCusId(cusid);
		log.info(list.getMobileTel());
		JSONObject object =new JSONObject();
		object.put("data", list);
		model.addAttribute("cus",list);
		return list;
	}
	//
	@RequestMapping(value="/getCusRecordLogByCusId")
	@ResponseBody
	public Map<String,Object> getCusRecordLogByCusId(HttpServletRequest request,/*String proId,*/String cusid, String logType){
		log.info("hgeee");
		DataTablesParameters para= RequestUtil.getDTPara(request);
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		log.info("fee "+cusid+" "+logType);
		ZsBusiCustomLogTable option = new ZsBusiCustomLogTable();
		option.setProjectId(projGuid);
		option.setBelongSys("xs");
		option.setCusId(cusid);
		option.setLogType(logType);
		
		List<ZsBusiCustomLogTable> list=xsBusiCustomAllocateService.getCusRecordLogByCusId(option);
		int recordsTotal=list.size();
		int recordsFiltered=recordsTotal;
		log.info(recordsTotal);
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		int i=0;
		for(ZsBusiCustomLogTable cust:list) {
			Map<String,String> custInfo = new HashMap<String,String>();
			switch(logType) {
				case "1":
					custInfo.put("content", cust.getReason());
					custInfo.put("description", cust.getContent());
					break;
				case "2":
					custInfo.put("description", cust.getContent());
					break;
				case "3":
					custInfo.put("oldEmployee", cust.getOldOperator());
					custInfo.put("reason", cust.getReason());
					custInfo.put("content", cust.getContent());
					break;
			}
			custInfo.put("index",para.getStart()+i+1+"");
			custInfo.put("operDate",cust.getOperDate());
			custInfo.put("operator",cust.getOperator());
			i++;
			lst.add(custInfo);
		}
		Map<String,Object> jsonResult = new HashMap<String,Object>();
		jsonResult.put("draw", para.getDraw());
		jsonResult.put("recordsTotal", recordsTotal);
		jsonResult.put("recordsFiltered", recordsFiltered);
		jsonResult.put("data",lst);
		return jsonResult;
	}
	@RequestMapping(value="/custAssignNav")
	public String custAssign(Model model) {
		log.info("feeeee");
		//List<XsTeamGroupEntity> projectList = xsTeamGroupService.getProjectItem();
		//model.addAttribute("projectList", projectList);
		XsTeamGroupVo searchInfo = new XsTeamGroupVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		searchInfo.setGroupType("xs");
		searchInfo.setUserLevelId("2");
		searchInfo.setProperty("xs_userGuid");
		searchInfo.setProjectId(projGuid);
		List<XsTeamGroupEntity> zygwList = xsTeamGroupService.getUserFromTeamGroup(searchInfo);
		model.addAttribute("zygwList", zygwList);
		log.info("pro:"+projGuid+"zygw:"+zygwList.size());
		return "/wbem/houses/customer/custAssignNav";
	}
	@ResponseBody
	@RequestMapping(value="/receiveCustAssignEmployeeList")
	public String receiveCustAssignEmployeeList(String data) {
		log.info(data);
		JSONObject obj=JSONObject.fromObject(data);
		JSONArray cusIdArray=(JSONArray)obj.get("cusId");
		JSONArray oppArray=(JSONArray)obj.get("oppList");
		JSONArray userGuid=(JSONArray)obj.get("userGuid");
		log.info(cusIdArray.size()+" "+oppArray.size()+" "+userGuid.size());
		
		setAssignCusIdsArray(cusIdArray);
		setOppGuidArray(oppArray);
		setUserGuidArray(userGuid);
		return "1";
		//new ModelAndView("/wbem/houses/customer/dailog/custAssignEmployeeList","cusId","\""+cusId+"\"");
	}
	@RequestMapping(value="/custAssignEmployeeList")
	public String custAssignEmployeeList(/*@RequestParam(value="proId",required=false)String proId,*/Model model) {
		
		//String []cus = cusId.split(",");
		//log.info("length:"+cus.length);
		//model.addAttribute("cusNum", 5);
		//model.addAttribute("proId", "\""+proId+"\"");
		//model.addAttribute("cusId", "\""+proId+"\"");
		//model.addAttribute("oppGUID", "\""+oppGUID+"\"");
		return "/wbem/houses/customer/dailog/custAssignEmployeeList";
	}

	@RequestMapping(value="/custDetail")
	public String custDetail(){
		return "/wbem/houses/customer/dailog/custDetail";
	}
	//导入客户中的_模板下载
	
	@RequestMapping(value="/import/import_exportExcelDemo")
	@ResponseBody
	public String  import_exportExcelDemo(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		//置业顾问列表
		XsTeamGroupVo xsInfo2 = new XsTeamGroupVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		xsInfo2.setGroupType("xs");
		xsInfo2.setUserLevelId("2");
		xsInfo2.setProperty("xs_userGuid");
		xsInfo2.setProjectId(projGuid);
		List<XsTeamGroupEntity> allZygwList = xsTeamGroupService.getUserFromTeamGroup(xsInfo2);
		List<String> list2=new ArrayList<String>();
		for (XsTeamGroupEntity vo : allZygwList) {
			list2.add(vo.getUserName());
		}
		//数据
		List<XsCustomerImportVo> exportList = new ArrayList<XsCustomerImportVo>();
		JSONArray ja = JSONArray.fromObject(exportList);
		String[] titles={"客户姓名","手机号码","家庭电话","公司电话","传真","性别","标签","身份证号码","意向面积","意向价格","意向房型","购买用途","年龄段","置业次数","居住区域","工作区域","婚姻状况","家庭结构","居住业态","教育程度","竞争对手","客户意向","业务阶段","置业顾问","意向项目"};
		Map<String, Object> model=new HashMap<String,Object>();
		model.put("list", ja);
		model.put("zygwList", list2);
		model.put("titles", titles);
		model.put("class", XsCustomerImportVo.class);
		List<XsTeamGroupEntity> projectList = xsTeamGroupService.getProjectItem();
		List<String> list3=new ArrayList<String>();
		for (XsTeamGroupEntity vo : projectList) {
			if(projGuid.equals(vo.getId())){//2016.8.23 Add
				list3.add(vo.getName());
			}
		}
		model.put("projectList", list3);
		xsexportVo.setModel(model);
		return "success";
	}
	@RequestMapping(value="/import/import_exportDupCustomer")
	@ResponseBody
	public String  import_exportDupCustomer(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		//置业顾问列表
		XsTeamGroupVo xsInfo2 = new XsTeamGroupVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		xsInfo2.setGroupType("xs");
		xsInfo2.setUserLevelId("2");
		xsInfo2.setProperty("xs_userGuid");
		xsInfo2.setProjectId(projGuid);
		List<XsTeamGroupEntity> allZygwList = xsTeamGroupService.getUserFromTeamGroup(xsInfo2);
		List<String> list2=new ArrayList<String>();
		for (XsTeamGroupEntity vo : allZygwList) {
			list2.add(vo.getUserName());
		}
		//数据
		List<XsCustomerImportVo> exportList = xsexportVo.getDupCusList();
		JSONArray ja = JSONArray.fromObject(exportList);
		String[] titles={"客户姓名","手机号码","家庭电话","公司电话","传真","性别","标签","身份证号码","意向面积","意向价格","意向房型","购买用途","年龄段","置业次数","居住区域","工作区域","婚姻状况","家庭结构","居住业态","教育程度","竞争对手","客户意向","业务阶段","置业顾问","意向项目"};
		Map<String, Object> model=new HashMap<String,Object>();
		model.put("list", ja);
		model.put("zygwList", list2);
		model.put("titles", titles);
		model.put("class", XsCustomerImportVo.class);
		List<XsTeamGroupEntity> projectList = xsTeamGroupService.getProjectItem();
		List<String> list3=new ArrayList<String>();
		for (XsTeamGroupEntity vo : projectList) {
			if(projGuid.equals(vo.getId())){//2016.8.23 Add
				list3.add(vo.getName());
			}
		}
		model.put("projectList", list3);
		xsexportVo.setModel(model);
		return "success";
	}
	@RequestMapping(value="/import/import_exportExcelDemo2")
	public ModelAndView  import_exportExcelDemo2(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> model=xsexportVo.getModel();
		return new ModelAndView(new ViewExcelExport(),model);
	}
	//导入客户_线下导入客户
	@RequestMapping(value = "/import/import_importCustomers", method = RequestMethod.POST)
	@ResponseBody
	  public  String  import_importCustomers(@RequestParam("filename") MultipartFile file,
	      HttpServletRequest request,HttpServletResponse response,Model model)  {
		try {
			SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<XsCustomerImportVo> list;
			list = new ExcelUploadImport().uploadFile_And_readExcelToList(file, request, response, XsCustomerImportVo.class);
			log.info(" "+list +"  "+ list.size());
			if(list.size()==0){
				log.info("没有有效数据");
				return "1";
			}
			for (XsCustomerImportVo vo : list) {
				XsEmployeeTableEntity employee = new XsEmployeeTableEntity();
				log.info(vo.getZygw());
				if(vo.getZygw()==null || "".equals(vo.getZygw()) || "null".equalsIgnoreCase(vo.getZygw())){
					log.info("存在置业顾问为空的情况，认真核查");
					return "2";
				}
				employee.setUserName(vo.getZygw());
				XsEmployeeTableEntity employeeInfo = xsCstInfo.getXsEmployeeInfo(employee);
				log.info("em "+employeeInfo);
				if(employeeInfo==null){
					log.info("所选置业顾问没有在系统中维护，请核查");
					return "3";
				}
			}
			List<XsTeamGroupEntity> projectList = xsTeamGroupService.getProjectItem();
			List<Map<String, Object>> telList=xsCstInfo.getTel_allCustomer();
			List<XsCustomerImportVo> dupCusList=new ArrayList<XsCustomerImportVo>();
			int importCount=0;
			String projectGuid="";
			for (XsCustomerImportVo vo : list) {
				XsEmployeeTableEntity employee = new XsEmployeeTableEntity();
				employee.setUserName(vo.getZygw());
				XsEmployeeTableEntity employeeInfo = xsCstInfo.getXsEmployeeInfo(employee);
				
				for (XsTeamGroupEntity entity : projectList) {
					if(vo.getProjGuid().equals(entity.getName())){
						projectGuid=entity.getId();
						break;
					}
				}
				log.info(projectGuid);
				
				XsCstTableEntity bean=new XsCstTableEntity();
				bean.setCstName(vo.getCstName());
				bean.setMobileTel(vo.getMobileTel());  
				bean.setHomeTel(vo.getHomeTel());
				bean.setOfficeTel(vo.getOfficeTel());
				bean.setFax(vo.getFax());
				bean.setGender(vo.getGender());
				bean.setDescription(vo.getDescription());
				bean.setCardId15(vo.getCardId15());
				Byte by = 1;
				bean.setCstType("个人");//暂定为这个，后续看根据什么来识别应该填啥
				bean.setIsReceiveSms(by);
				bean.setIsCreatorUse(by);
				
				XsCstAttrTableEntity bean1=new XsCstAttrTableEntity();
				bean1.setBuyersUse(vo.getBuyersUse());
				bean1.setAgeGroup(vo.getAgeGroup());
				bean1.setZyNum(vo.getZyNum());
				bean1.setHomeArea(vo.getHomeArea());
				bean1.setWorkArea(vo.getWorkArea());
				bean1.setMarriage(vo.getMarriage());
				bean1.setFamily(vo.getFamily());
				bean1.setJzyt(vo.getJzyt());
				bean1.setEduLevel(vo.getEduLevel());
				
				XsCstAttachTableEntity bean2=new XsCstAttachTableEntity();
				bean2.setProjGuid(projectGuid);
				bean2.setBuGuid("42A0C9C9-51EA-E411-BAAF-FCAA145C42F2");
				bean2.setUserGuid(employeeInfo.getUserGuid());
				
				XsOppTableEntity bean3=new XsOppTableEntity();
				bean3.setProjGuid(projectGuid);
				bean3.setBuGuid("42A0C9C9-51EA-E411-BAAF-FCAA145C42F2");
				bean3.setCreatedOn(fm1.format(new Date()));
				bean3.setIsCreatorUse(by);//不知做啥用的
				bean3.setUserGuid(employeeInfo.getUserGuid());//与创建者的区别
				bean3.setZygw(vo.getZygw());
				bean3.setZygwGuid(employeeInfo.getUserGuid());
				bean3.setDescription("");//与客户表中的description字段名相同，防止将客户标签填进去
				bean3.setStatus(vo.getStatus());
				bean3.setGfyx(vo.getGfyx());
				bean3.setCompetitor(vo.getCompetitor());
				
				XsOpp2CstTableEntity bean4=new XsOpp2CstTableEntity();
				bean4.setCstNum("1");//不知根据什么判断来填值
				bean4.setProjGuid(projectGuid);
				
				XsOpp2RoomTableEntity bean5=new XsOpp2RoomTableEntity();
				bean5.setNum("1");//序号,作何用不知
				bean5.setYxArea(vo.getYxArea());
				bean5.setYxPrice(vo.getYxPrice());
				bean5.setYxFangXing(vo.getYxFangXing());
				
				XsOpp2GjjlTableEntity bean6=new XsOpp2GjjlTableEntity();
				bean6.setProjGuid(projectGuid);
				bean6.setGjDate(fm1.format(new Date()));
				bean6.setGjrGuid(employeeInfo.getUserGuid());
				bean6.setGjfs("");
				bean6.setStatus("问询");
				
				XsCstInfo cstInfo = new XsCstInfo();
				cstInfo.setXsCst(bean);
				cstInfo.setXsCstAttr(bean1);
				cstInfo.setXsCstAttach(bean2);
				cstInfo.setXsOpp(bean3);
				cstInfo.setXsOpp2Cst(bean4);
				cstInfo.setXsOpp2Room(bean5);
				cstInfo.setXsOpp2Gjjl(bean6);

				boolean _insertFlg=true;
				 out: for (Map<String, Object> telMap : telList) {
					  Iterator<Entry<String, Object>> iterator=telMap.entrySet().iterator();
						while (iterator.hasNext()) {
							Entry<String, Object> entry=iterator.next();
							//log.info(entry.getKey()+" "+entry.getValue());
							String _tel=(String)entry.getValue();
							//log.info(o.getTel());
							if(!"".equals(vo.getMobileTel()) && _tel.contains(vo.getMobileTel()) ){
								log.info("tel: "+_tel);
								dupCusList.add(vo);
								_insertFlg=false;
								break out;
							}
							/*if(!"".equals(o.getHomeTel()) && _tel.contains(o.getHomeTel()) ){
								log.info("homeTel "+_tel);
								dupCusList.add(o);
								_insertFlg=false;
								break out;
							}
							if(!"".equals(o.getOfficeTel()) && _tel.contains(o.getOfficeTel()) ){
								log.info("off "+_tel);
								dupCusList.add(o);
								_insertFlg=false;
								break out;
							}*/
						}
				  }
				if(_insertFlg){
					importCount++;
					xsCstInfo.insertXsCstAllInfo(cstInfo);	
					log.info(dupCusList.size());
				}
			
				
			}
			
			
		    String strAlertMsg ="";
		    if(importCount!=0){
		      strAlertMsg= "成功导入" + importCount + "条！";
		    }else {
		      strAlertMsg = "导入失败！";
		    }
		    log.info(strAlertMsg);
		    model.addAttribute("result", strAlertMsg);
		    if(dupCusList.size()==0){
		    	 return "success";
		    }else {
		    	xsexportVo.setDupCusList(dupCusList);
		    	 return "success_dusp";
		    }
		} catch (Exception e) {
			log.info("error "+e);
			return "0";
		}
	  }
	/*
	 * 导出客户资料
	 */
	@RequestMapping(value="/export/export_followingCustomer")
	@ResponseBody
	public String  export_followingCst(HttpServletRequest request,HttpServletResponse response) throws Exception{

		XsCustomerFollowingExportVo exportVo;
		List<XsCustomersManagerEntity> custList = new ArrayList<XsCustomersManagerEntity>();
		custList=xsexportVo.getList();
		List<XsCustomerFollowingExportVo> exportList = new ArrayList<XsCustomerFollowingExportVo>();
		for (XsCustomersManagerEntity entity : custList) {
			exportVo=new XsCustomerFollowingExportVo(entity.getCstName(), entity.getMobileTel(), entity.getUserName(), entity.getGjfs(), entity.getStatus(), entity.getGfyx(), 
					entity.getGfys(), entity.getGfyt(), entity.getGzfm1(), entity.getGender(), entity.getEmail(), entity.getAddress(), entity.getCardType(), entity.getCardID15(), entity.getWorkAddr(),
					entity.getBirthDate(), entity.getCompetitor(), entity.getSubMediaName(), entity.getDfNum(), entity.getLastDate(), 
					entity.getNextDate(),  entity.getCreatedOn(), entity.getUserGuid(), entity.getProjGUID(), entity.getCstGuid(), entity.getOppGUID());
			exportList.add(exportVo);
		}
		JSONArray ja = JSONArray.fromObject(exportList);
		//置业顾问列表
		XsTeamGroupVo xsInfo2 = new XsTeamGroupVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		xsInfo2.setGroupType("xs");
		xsInfo2.setUserLevelId("2");
		xsInfo2.setProperty("xs_userGuid");
		xsInfo2.setProjectId(projGuid);
		List<XsTeamGroupEntity> allZygwList = xsTeamGroupService.getUserFromTeamGroup(xsInfo2);
		List<String> list2=new ArrayList<String>();
		for (XsTeamGroupEntity vo : allZygwList) {
			list2.add(vo.getUserName());
		}
		List<String> list3=new ArrayList<String>();
		String[] titles={"新业务员","客户姓名","手机号码","置业顾问","跟进方式","客户状态","客户意向","意向价格","购房用途","关注因素","性别","邮箱","家庭住址","证件类型","证件号码","工作区域","生日","竞争对手","认知途径","置业次数","到期日期","下次跟进日期","创建日期","置业顾问GUID","项目GUID","客户GUID","OPPguid"};//{ "辅料序号", "辅料名称"}
		Map<String, Object> model=new HashMap<String,Object>();
		model.put("list", ja);
		model.put("zygwList", list2);
		model.put("titles", titles);
		model.put("class", XsCustomerFollowingExportVo.class);
		model.put("projectList", list3);
		xsexportVo.setModel(model);
		return "success";
	}
	@RequestMapping(value="/export/export_followingCustomer2")
	public ModelAndView  export_followingCst2(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> model=xsexportVo.getModel();
		return new ModelAndView(new ViewExcelExport(),model);
	}
	//导出客户资料_线下分配好后导入客户资料
	@RequestMapping(value = "/export/importAllocateResult", method = RequestMethod.POST)
	@ResponseBody
	  public  String export_importAllocateResult(@RequestParam("filename") MultipartFile file,
	      HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			List<XsCustomerFollowingExportVo> list=new ExcelUploadImport().uploadFile_And_readExcelToList(file, request, response, XsCustomerFollowingExportVo.class);
			if(list.size()==0){
				log.info("没有有效数据");
				return "1";
			}
			for (XsCustomerFollowingExportVo vo : list) {
				XsEmployeeTableEntity employee = new XsEmployeeTableEntity();
				if(vo.getNewZygw()==null || "".equals(vo.getNewZygw()) || "null".equalsIgnoreCase(vo.getNewZygw())){
					log.info("存在置业顾问为空的情况，认真核查");
					return "2";
				}
				employee.setUserName(vo.getNewZygw());
				XsEmployeeTableEntity employeeInfo = xsCstInfo.getXsEmployeeInfo(employee);
				log.info("em "+employeeInfo);
				if(employeeInfo==null){
					log.info("所选置业顾问没有在系统中维护，请核查");
					return "3";
				}
			}
			for (XsCustomerFollowingExportVo vo : list) {
				XsEmployeeTableEntity employee = new XsEmployeeTableEntity();
				employee.setUserName(vo.getNewZygw());
				XsEmployeeTableEntity employeeInfo = xsCstInfo.getXsEmployeeInfo(employee);
				xsBusiCustomAllocateService.allocateCustomers(vo.getUserGUID(),employeeInfo.getUserGuid(),vo.getCstGUID(),vo.getProjGUID(),vo.getOppGUID());
			}
			log.info(list.size());
			log.info(list.get(0));
			return "success";
		} catch (Exception e) {
			log.info("error "+e);
			return "0";
		}
		
	}
	public JSONArray getAssignCusIdsArray() {
		return assignCusIdsArray;
	}
	public void setAssignCusIdsArray(JSONArray assignCusIdsArray) {
		this.assignCusIdsArray = assignCusIdsArray;
	}
	public JSONArray getOppGuidArray() {
		return oppGuidArray;
	}
	public void setOppGuidArray(JSONArray oppGuidArray) {
		this.oppGuidArray = oppGuidArray;
	}
	public JSONArray getUserGuidArray() {
		return userGuidArray;
	}
	public void setUserGuidArray(JSONArray userGuidArray) {
		this.userGuidArray = userGuidArray;
	}
	

	
	
}