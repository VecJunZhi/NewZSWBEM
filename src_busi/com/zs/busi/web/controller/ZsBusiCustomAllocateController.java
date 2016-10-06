package com.zs.busi.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

import com.zs.busi.entity.ZsBusiCustomAllocateEntity;
import com.zs.busi.entity.ZsBusiCustomLogTable;
import com.zs.busi.entity.ZsCustomTableVo;
import com.zs.busi.entity.ZsFollowInfoVo;
import com.zs.busi.entity.ZsInfoVo;
import com.zs.busi.service.ZsBusiCustomAllocateService;
import com.zs.busi.service.ZsFollowInfoService;
import com.zs.busi.service.ZsInfoService;
import com.zs.busi.service.impl.ZsFollowSearch;
import com.zs.busi.utils.DateFormat;
import com.zs.busi.utils.LogUtil;
import com.zs.busi.web.vo.ZsBusiCustomAllocateVo;
import com.zs.busi.web.vo.ZsCustomerExportVo;
import com.zs.busi.web.vo.ZsCustomerImportVo;
import com.zs.busi.web.vo.ZsExcelVo;
import com.zs.common.entity.DataTablesParameters;
import com.zs.common.entity.SearchOptionEntity;
import com.zs.common.service.IOptionListService;
import com.zs.common.util.DateUtil;
import com.zs.common.util.ExcelUploadImport;
import com.zs.common.util.RequestUtil;
import com.zs.common.util.ViewExcelExport;
import com.zs.common.util.ding.DingDingVo;
import com.zs.common.util.ding.DingUtil;
import com.zs.common.util.search.TagOption;
import com.zs.crm.entity.XsCustomersManagerEntity;
import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.oa.entity.OaUserEntity;
import com.zs.oa.service.OaUserService;
import com.zs.oa.service.impl.OaUserServiceImpl;
import com.zs.rbac.core.RBACSubject;
import com.zs.rbac.entity.User;
import com.zs.rbac.service.XsTeamGroupService;
import com.zs.rbac.utils.RbacUtils;
import com.zs.test.common.Ajax;
import com.zs.crm.web.vo.XsCustomerImportVo;
import com.zs.crm.web.vo.XsTeamGroupVo;

@Controller
@RequestMapping(value="/wbem/business/customer")
public class ZsBusiCustomAllocateController {
	
	Log log=LogUtil.getLog();
	@Autowired 
	ZsBusiCustomAllocateService zsBusiCustomAllocateService;
	@Autowired
	HttpSession httpSession;
	@Autowired
	IOptionListService listservice;
	@Autowired
	XsTeamGroupService xsTeamGroupService;
	@Autowired
	ZsInfoService zsInfoService;
	@Autowired
	ZsBusiCustomAllocateService logService;
	@Autowired
	ZsFollowInfoService zsFollowInfoService;
	public static Map<String, OaUserEntity> oamap ;
	static{
		OaUserService oaUserService = new OaUserServiceImpl();
		oamap = oaUserService.getAllUserService();
	}
	ZsExcelVo zsExcelVo =new ZsExcelVo();
	public List<String> loadSearchOption(String module) {
		List<SearchOptionEntity> optionList = new ArrayList<SearchOptionEntity>();
		SearchOptionEntity option = new SearchOptionEntity();
		log.info("module="+module);
		option.setModule(module);
		optionList = listservice.getOptionListByModule(option);
		log.info("size"+optionList.size());
		ZsFollowSearch hh = new ZsFollowSearch();
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
	//�������ڿͻ�ҳ��
	@RequiresPermissions(value="page:wbem:crm:zs:custAssignOverdue")
	@RequestMapping(value="/custAssignOverdue")
	public String page_CustAssignOverdue(Model model){
		List<String> optionlist = new ArrayList<String>();
		List<String> queryList = new ArrayList<String>();
		List<String> searchList = new ArrayList<String>();
		optionlist = loadSearchOption("overcussearch");
		for(String option:optionlist) {
			if(option.indexOf("button") != -1)
				queryList.add(option);
			else
				searchList.add(option);
		}
		httpSession.setAttribute("overListShow", searchList);
		httpSession.setAttribute("overQueryList", queryList);
		//List<XsTeamGroupEntity> projectList = xsTeamGroupService.getProjectItem();
		//model.addAttribute("projectList", projectList);
		XsTeamGroupVo searchInfo = new XsTeamGroupVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		searchInfo.setGroupType("zs");
		searchInfo.setUserLevelId("2");
		searchInfo.setProperty("bs_userGuid");
		searchInfo.setProjectId(projGuid);
		List<XsTeamGroupEntity> zygwList = xsTeamGroupService.getUserFromTeamGroup(searchInfo);
		model.addAttribute("zygwList", zygwList);
		return "/wbem/business/customer/custAssignOverdue";
	}
	//������Ч�ͻ�ҳ��
	@RequiresPermissions(value="page:wbem:crm:zs:custAssignInvalid")
	@RequestMapping(value="/custAssignInvalid")
	public String page_CustInvalid(Model model){
		List<String> optionlist = new ArrayList<String>();
		optionlist = loadSearchOption("invalidcussearch");
		List<String> queryList = new ArrayList<String>();
		List<String> searchList = new ArrayList<String>();
		for(String option:optionlist) {
			if(option.indexOf("button") != -1)
				queryList.add(option);
			else
				searchList.add(option);
		}
		httpSession.setAttribute("overListShow", searchList);
		httpSession.setAttribute("overQueryList", queryList);
		//List<XsTeamGroupEntity> projectList = xsTeamGroupService.getProjectItem();
		//model.addAttribute("projectList", projectList);
		XsTeamGroupVo searchInfo = new XsTeamGroupVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		searchInfo.setGroupType("zs");
		searchInfo.setUserLevelId("2");
		searchInfo.setProperty("bs_userGuid");
		searchInfo.setProjectId(projGuid);
		List<XsTeamGroupEntity> zygwList = xsTeamGroupService.getUserFromTeamGroup(searchInfo);
		model.addAttribute("zygwList", zygwList);
		return "/wbem/business/customer/custAssignInvalid";
	}
	@RequiresPermissions(value="page:wbem:crm:zs:custAssignFollowing")
	@RequestMapping(value="/custAssignNav")
	public String custAssign(Model model) {
		//List<XsTeamGroupEntity> projectList = xsTeamGroupService.getProjectItem();
		//model.addAttribute("projectList", projectList);
		XsTeamGroupVo searchInfo = new XsTeamGroupVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		searchInfo.setGroupType("zs");
		searchInfo.setUserLevelId("2");
		searchInfo.setProperty("bs_userGuid");
		searchInfo.setProjectId(projGuid);
		List<XsTeamGroupEntity> zygwList = xsTeamGroupService.getUserFromTeamGroup(searchInfo);
		model.addAttribute("zygwList", zygwList);
		return "/wbem/business/customer/custAssignNav";
	}
	
	
	
	//��������ͻ�ҳ��
	@RequiresPermissions(value="page:wbem:crm:zs:custAssignFollowing")
	@RequestMapping(value="/custAssignFollowing")
	public String page_CustAssignFollowing(Model model){
		log.info("CustAssignFollowing");
		List<String> optionlist = new ArrayList<String>();
		optionlist = loadSearchOption("followcussearch");
		List<String> queryList = new ArrayList<String>();
		List<String> searchList = new ArrayList<String>();
		for(String option:optionlist) {
			if(option.indexOf("button") != -1)
				queryList.add(option);
			else
				searchList.add(option);
		}
		httpSession.setAttribute("overListShow", searchList);
		httpSession.setAttribute("overQueryList", queryList);
		return "/wbem/business/customer/custAssignFollowing";
	}
	//���빫���ͻ�ҳ��
	@RequiresPermissions(value="page:wbem:crm:zs:custAssignPublic")
	@RequestMapping(value="/custAssignPublic")
	public String page_CustAssignPublic(Model model){
		log.info("CustAssignPublic");
		//List<XsTeamGroupEntity> projectList = xsTeamGroupService.getProjectItem();
		//model.addAttribute("projectList", projectList);
		XsTeamGroupVo searchInfo = new XsTeamGroupVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		searchInfo.setGroupType("zs");
		searchInfo.setUserLevelId("2");
		searchInfo.setProperty("bs_userGuid");
		searchInfo.setProjectId(projGuid);
		List<XsTeamGroupEntity> zygwList = xsTeamGroupService.getUserFromTeamGroup(searchInfo);
		model.addAttribute("zygwList", zygwList);
		return "/wbem/business/customer/custAssignPublic";
	}
	//����������ͻ�ҳ��
	@RequiresPermissions(value="page:wbem:crm:zs:custAssignDusBin")
	@RequestMapping(value="/custAssignDusBin")
	public String page_CustAssignDusBin(Model model){
		log.info("CustAssignDusBin");
		//List<XsTeamGroupEntity> projectList = xsTeamGroupService.getProjectItem();
		//model.addAttribute("projectList", projectList);
		XsTeamGroupVo searchInfo = new XsTeamGroupVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		searchInfo.setGroupType("zs");
		searchInfo.setUserLevelId("2");
		searchInfo.setProperty("bs_userGuid");
		searchInfo.setProjectId(projGuid);
		List<XsTeamGroupEntity> zygwList = xsTeamGroupService.getUserFromTeamGroup(searchInfo);
		model.addAttribute("zygwList", zygwList);
		return "/wbem/business/customer/custAssignDusBin";
	}
	//�������ͻ�ҳ��
	@RequiresPermissions(value="page:wbem:crm:zs:custAssignAllocate")
	@RequestMapping(value="/custAssignAllocate")
	public String page_allocateCustomer(@RequestParam String cusid){
		log.info("fepei"+cusid);
		httpSession.setAttribute("allocateCusId", cusid);
		return "/wbem/business/customer/custAssignAllocate";
		
	};
/*	//������տͻ�ҳ��
	@RequestMapping(value="/page_reBackCustomer")
	public String page_reBackCustomer(@RequestParam String cusid){
		httpSession.setAttribute("rebackCusId", cusid);
		return "/wbem/business/customer/custAssignReback";
		
	};*/
	//�����������ͻ�-������ҵ�����б�ҳ
	@RequiresPermissions(value="page:wbem:crm:zs:custAssignAllocate")
	@RequestMapping(value="/dulp_custAssignAllocate")
	public String allocateCustomers(@RequestParam String cusidlist){
		
		httpSession.setAttribute("allocateCusId", cusidlist);
		//return new ModelAndView("/wbem/business/customer/custAssignAllocate","cusid",list[0]+","+list[1]);
		return "/wbem/business/customer/custAssignAllocate";
		
	};
/*	//����������տͻ�
	@RequestMapping(value="/reBackCustomers")
	@ResponseBody
	public ModelAndView reBackCustomers(@RequestParam String cusid){
		log.info("huishou"+cusid);
		return new ModelAndView("/wbem/business/customer/custAssignReback","cusid",cusid);
		
	};*/
	//����ͻ�����--������־ҳ��
	@RequiresPermissions(value="page:wbem:crm:zs:custAssignLog")
	@RequestMapping(value="/custAssignLog")
	public ModelAndView page_CustAssignLog(@RequestParam String cusid) {
		log.info("huishou"+cusid);
		//return "/wbem/business/customer/custAssignLog";
		return new ModelAndView("/wbem/business/customer/custAssignLog","cusid",cusid);
		
	}
	//����ͻ�����--�ͻ�������Ϣҳ��
	@RequiresPermissions(value="page:wbem:crm:zs:custAssignLogInfo")
	@RequestMapping(value="/custAssignLogInfo")
	public ModelAndView page_CustAssignLogInfo(@RequestParam String cusid) {
		log.info("loginfo"+cusid);
		//return "/wbem/business/customer/custAssignLogInfo";
		return new ModelAndView("/wbem/business/customer/custAssignLogInfo","cusid",cusid);
		
	}
	//����ͻ�����--������־ҳ��
	@RequiresPermissions(value="page:wbem:crm:zs:custAssignLogAllocate")
	@RequestMapping(value="/custAssignLogAllocate")
	public ModelAndView page_zsCustLogAllocate(@RequestParam String cusid) {
		log.info("loginfo"+cusid);
		//return "/wbem/business/customer/custAssignLogInfo";
		return new ModelAndView("/wbem/business/customer/custAssignLogAllocate","cusid",cusid);
		//return "/wbem/business/customer/custAssignLogAllocate";
		
	}
	//����ͻ�����--������־ҳ��
	@RequiresPermissions(value="page:wbem:crm:zs:custAssignLogReback")
	@RequestMapping(value="/custAssignLogReback")
	public ModelAndView page_zsCustLogReback(@RequestParam String cusid) {
		log.info("loginfo"+cusid);
		//return "/wbem/business/customer/custAssignLogInfo";
		return new ModelAndView("/wbem/business/customer/custAssignLogReback","cusid",cusid);
		//return "/wbem/business/customer/custAssignLogReback";
		
	}
	/**
	 * 
	 * �ͻ�������Ϣ(dailog page)
	 * @return
	 */
	@RequiresPermissions(value="page:wbem:crm:zs:custAssignLogInfo")
	@RequestMapping(value="/dailog/custDetail")
	public String custDetail(@RequestParam String cusid,String userName,/*String proId,*/Model model) {
		ZsCustomTableVo list=zsBusiCustomAllocateService.getCustomInfoByCusId(cusid);
		model.addAttribute("custDetail",list);
		try {
			userName=URLDecoder.decode(userName,"utf-8");
		} catch (Exception e) {
			log.info("code_error:"+e);
		}
		/*if(httpSession.getAttribute("userName")!=null && !"".equals(httpSession.getAttribute("userName"))){
			userName=(String) httpSession.getAttribute("userName");
		}*/
		httpSession.setAttribute("custLogID", cusid);
		httpSession.setAttribute("userName", userName);
		//httpSession.setAttribute("proId", proId);
		return "/wbem/business/customer/dailog/custDetail";
	}
	
	/**
	 * �ͻ������¼(dailog page)
	 * @return
	 */
	@RequiresPermissions(value="page:busi:busiManage:custAllocation:custAssignLogChange")
	@RequestMapping(value="/dailog/custChangeLog")
	public String custChangeLog(Model model) {
		
		return "/wbem/business/customer/dailog/custChangeLog";
	}
	
	/**
	 * �ͻ�������־(dailog page)
	 * @return
	 */
	@RequiresPermissions(value="page:wbem:crm:zs:custAssignLogAllocate")
	@RequestMapping(value="/dailog/custAssignLog")
	public String custAssignLog(String cusid,Model model) {
		log.info("efefe "+cusid);
		//model.addAttribute("custAssignLog_Id",cusid);
		return "/wbem/business/customer/dailog/custAssignLog";
	}
	
	/**
	 * �ͻ�������־(dailog page)
	 * @return
	 */
	@RequiresPermissions(value="page:wbem:crm:zs:custAssignLogReback")
	@RequestMapping(value="/dailog/custRecycleLog")
	public String custRecycleLog() {
		log.info("fefefe");
		return "/wbem/business/customer/dailog/custRecycleLog";
	}
//----------------------------------����page-------------------------------------------------//	
//������ڿͻ�
	@RequestMapping(value="/getZsbusiCustomOverDue")
	@ResponseBody
	public Map<String,Object> getZsbusiCustomOverDue(HttpServletRequest request,/*String proId,*/String userId,String telOrName,String status,String gfyx,String startDate,String endDate,String overDays,String startDays,String endDays) throws UnsupportedEncodingException{
		int dataNum=0;
		
		ZsBusiCustomAllocateVo zsInfo= new ZsBusiCustomAllocateVo();
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		List<ZsBusiCustomAllocateEntity> custList = new ArrayList<ZsBusiCustomAllocateEntity>();
		DataTablesParameters para= RequestUtil.getDTPara(request);
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		zsInfo.setStartIndex(para.getStart());
		zsInfo.setLength(para.getLength());
		zsInfo.setProjGUID(projGuid);
		zsInfo.setUserGuid(userId);
		para.setColumnArray(new String[]{"","","","","","a.�������","a.�����������","","",""});
		log.info(para.getOrderColumn());
		log.info(para.getOrderDir());
		zsInfo.setSortName(para.getOrderColumn());
		zsInfo.setSortDir(para.getOrderDir());
		if(status != null && !"".equals(status)) {
			zsInfo.setCstStatus(URLDecoder.decode(status, "utf-8"));
			log.info("status:"+URLDecoder.decode(status, "utf-8"));
		}
		if(gfyx !=null && !"".equals(gfyx)) {
			zsInfo.setGfyx(URLDecoder.decode(gfyx, "utf-8"));
			log.info("gfyx:"+URLDecoder.decode(gfyx, "utf-8"));
		}
		if(telOrName !=null && !"".equals(telOrName)){//���ݿͻ����ƺ͵绰ģ����ѯ
			telOrName = URLDecoder.decode(telOrName, "utf-8");
			boolean isNum = telOrName.matches("[0-9]+");
			if(isNum){
				zsInfo.setMobileTel(telOrName);
			}else{
				zsInfo.setCstName(telOrName);;
			}
		}
		switch(overDays){
			case "0":
				zsInfo.setStartDays("1");
				zsInfo.setEndDays("10");//��������1-10��
				break;
			case "1":
				zsInfo.setStartDays("11");
				zsInfo.setEndDays("20");//��������11-20��
				break;
			case "2":
				zsInfo.setStartDays("21");//��������20������
				break;
			case "3":
				zsInfo.setStartDays(startDays);
				zsInfo.setEndDays(endDays);//���������Զ���
				break;
		}
		zsInfo.setStartTime(startDate);
		zsInfo.setEndTime(endDate);
		//zsInfo.setSortName("lastDate");
		//zsInfo.setSortDir("desc");
		dataNum = zsBusiCustomAllocateService.getZsCustCountDao("���ڿͻ�",zsInfo);
		log.info(dataNum);
		custList =  zsBusiCustomAllocateService.getZsOverdueCustInfoDao(zsInfo);
		log.info(custList.size());
		zsInfo.setLength(0);
		List<ZsBusiCustomAllocateEntity>  exportList =  zsBusiCustomAllocateService.getZsOverdueCustInfoDao(zsInfo);
		zsExcelVo.setFollow_oveDue_invalid_VoList(exportList);
		int i = para.getStart()+1;
		for(ZsBusiCustomAllocateEntity cust:custList) {
			//modify by jixiaohang
			String lastDateOld = cust.getLastDate();
			String lastDateNew = DateUtil.dateToStringSecond(lastDateOld);
			//modify by jixiaohang
			Map<String,String> custInfo = new HashMap<String,String>();
			custInfo.put("index","<label class='checkbox inline index'> <input type='checkbox' name='radios' />"+i+"</label>");
			custInfo.put("cstName",cust.getCusName());
			custInfo.put("mobileTel",cust.getTel());
			custInfo.put("userName",cust.getEmployeeName());
			custInfo.put("status", cust.getCustomStatus());
			custInfo.put("gfyx",cust.getIntentionType());
			custInfo.put("lastDate",lastDateNew);//modify by jixiaohang
			custInfo.put("gjfs",cust.getFollowWay());
			custInfo.put("overDays",cust.getOverDays());
			custInfo.put("operate",cust.getCusId());
			i++;
			lst.add(custInfo);
		}
		Map<String,Object> jsonResult = new HashMap<String,Object>();
		jsonResult.put("draw", para.getDraw());
		jsonResult.put("recordsTotal", dataNum);
		jsonResult.put("recordsFiltered", dataNum);
		jsonResult.put("data",lst);
		
		//����Ĵ���Ϊȫ������׼��
		jsonResult.put("assignNum",dataNum);
		//httpSession.setAttribute("dataNum", dataNum);
		List<ZsBusiCustomAllocateEntity> custList2 = new ArrayList<ZsBusiCustomAllocateEntity>();
		zsInfo.setFlg_all("1");
		custList2 =  zsBusiCustomAllocateService.getZsOverdueCustInfoDao(zsInfo);
		List<String> cusId_all=new ArrayList<String>();
		for (ZsBusiCustomAllocateEntity cust : custList2) {
			cusId_all.add(cust.getCusId());//Ϊȫ������׼��
		}
		jsonResult.put("ids", cusId_all.toString());
		log.info(cusId_all.toString());
				
		return jsonResult;
	}

//�����Ч�ͻ�
	@RequestMapping(value="/getZsbusiCustomInvalid")
	@ResponseBody
	public Map<String,Object> getZsbusiCustomInvalid(HttpServletRequest request,/*String proId,*/String telOrName,Model model) throws UnsupportedEncodingException{
		int dataNum=0;
		
		ZsBusiCustomAllocateVo zsInfo= new ZsBusiCustomAllocateVo();
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		List<ZsBusiCustomAllocateEntity> custList = new ArrayList<ZsBusiCustomAllocateEntity>();
		DataTablesParameters para= RequestUtil.getDTPara(request);
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		zsInfo.setStartIndex(para.getStart());
		zsInfo.setLength(para.getLength());
		zsInfo.setProjGUID(projGuid);
		para.setColumnArray(new String[]{"","","","","","","a.�������","a.�����������","",""});
		log.info(para.getOrderColumn());
		log.info(para.getOrderDir());
		zsInfo.setSortName(para.getOrderColumn());
		zsInfo.setSortDir(para.getOrderDir());
		if(telOrName !=null && !"".equals(telOrName)){//���ݿͻ����ƺ͵绰ģ����ѯ
			telOrName = URLDecoder.decode(telOrName, "utf-8");
			boolean isNum = telOrName.matches("[0-9]+");
			if(isNum){
				zsInfo.setMobileTel(telOrName);
			}else{
				zsInfo.setCstName(telOrName);;
			}
		}
		//zsInfo.setSortName("lastDate");
		//zsInfo.setSortDir("desc");
		dataNum = zsBusiCustomAllocateService.getZsCustCountDao("��Ч�ͻ�",zsInfo);
		custList =  zsBusiCustomAllocateService.getZsCusInvalidDao(zsInfo);
		log.info(custList.size());
		zsInfo.setLength(0);
		List<ZsBusiCustomAllocateEntity>  exportList =  zsBusiCustomAllocateService.getZsCusInvalidDao(zsInfo);
		zsExcelVo.setFollow_oveDue_invalid_VoList(exportList);
		int i=para.getStart()+1;
		for(ZsBusiCustomAllocateEntity cust:custList) {
			//modify by jixiaohang
			String lastDateOld = cust.getLastFollowDate();
			String lastDateNew = DateUtil.dateToStringSecond(lastDateOld);
			//modify by jixiaohang
			Map<String,String> custInfo = new HashMap<String,String>();
			custInfo.put("index","<label class='checkbox inline index'> <input type='checkbox' name='radios' />"+i+"</label>");
			custInfo.put("cstName",cust.getCusName());
			custInfo.put("mobileTel",cust.getTel());
			custInfo.put("userName",cust.getEmployeeName());
			custInfo.put("status", cust.getCustomStatus());
			custInfo.put("invalidReason",cust.getInvalidReason() );
			custInfo.put("gfyx",cust.getIntentionType());
			custInfo.put("lastDate", lastDateNew); //modify by jixiaohang
			custInfo.put("gjfs", cust.getFollowWay());
			custInfo.put("operate", cust.getCusId());

			i++;
			lst.add(custInfo);
		}
		Map<String,Object> jsonResult = new HashMap<String,Object>();
		jsonResult.put("draw", para.getDraw());
		jsonResult.put("recordsTotal", dataNum);
		jsonResult.put("recordsFiltered", dataNum);
		jsonResult.put("data",lst);
		//����Ĵ���Ϊȫ������׼��
		jsonResult.put("assignNum", dataNum);
		List<ZsBusiCustomAllocateEntity> custList2 = new ArrayList<ZsBusiCustomAllocateEntity>();
		zsInfo.setFlg_all("1");
		custList2 =  zsBusiCustomAllocateService.getZsCusInvalidDao(zsInfo);
		List<String> cusId_all=new ArrayList<String>();
		for (ZsBusiCustomAllocateEntity cust : custList2) {
			cusId_all.add(cust.getCusId());//Ϊȫ������׼��
		}
		jsonResult.put("ids", cusId_all.toString());
		log.info(cusId_all.toString());
		return jsonResult;
	}
	
	//��ø����пͻ�
	@RequestMapping(value="/getZsbusiCustomFollowing")
	@ResponseBody
	public Map<String,Object> getZsbusiCustomFollowing(HttpServletRequest request,/*String proId,*/String userId,String telOrName,String status,String gfyx,String createdOn,String startCreate,String endCreate,Model model) throws UnsupportedEncodingException{
		int dataNum=0;
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		List<ZsBusiCustomAllocateEntity> custList = new ArrayList<ZsBusiCustomAllocateEntity>();
		DataTablesParameters para= RequestUtil.getDTPara(request);
		ZsBusiCustomAllocateVo zsInfo = new ZsBusiCustomAllocateVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		zsInfo.setStartIndex(para.getStart());
		zsInfo.setLength(para.getLength());
		zsInfo.setProjGUID(projGuid);
		zsInfo.setUserGuid(userId);
		para.setColumnArray(new String[]{"","","","","","a.�������","a.�����������","","a.��������","",""});
		log.info(para.getOrderColumn());
		log.info(para.getOrderDir());
		zsInfo.setSortName(para.getOrderColumn());
		zsInfo.setSortDir(para.getOrderDir());
		if(status!=null && !status.equals("")){
			zsInfo.setCstStatus(URLDecoder.decode(status,"utf-8"));
		}
		if(gfyx!=null && !gfyx.equals("")) {
			zsInfo.setGfyx(URLDecoder.decode(gfyx,"utf-8"));
		}
		zsInfo.setCreatedOn(createdOn);//����ʱ����Ҫ����
		//xsInfo.setCstName(URLDecoder.decode(cstName,"utf-8"));
		if(telOrName !=null && !"".equals(telOrName)){//���ݿͻ����ƺ͵绰ģ����ѯ
			telOrName = URLDecoder.decode(telOrName, "utf-8");
			boolean isNum = telOrName.matches("[0-9]+");
			if(isNum){
				zsInfo.setMobileTel(telOrName);
			}else{
				zsInfo.setCstName(telOrName);;
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
			zsInfo.setStartCreate(startCreate);
			zsInfo.setEndCreate(endCreate);
			
			log.info("start:"+startCreate+"end:"+endCreate);
		}
		log.info("projGuid"+projGuid+"userId:"+userId+"telOrName:"+telOrName+"createdOn:"+createdOn);
		dataNum = zsBusiCustomAllocateService.getZsCustCountDao("�����пͻ�",zsInfo);
		custList =  zsBusiCustomAllocateService.getZsCustFollowingDao(zsInfo);
		log.info(custList.size());
		zsInfo.setLength(0);
		List<ZsBusiCustomAllocateEntity>  exportList =  zsBusiCustomAllocateService.getZsCustFollowingDao(zsInfo);
		zsExcelVo.setFollow_oveDue_invalid_VoList(exportList);
		int i=para.getStart()+1;
		for(ZsBusiCustomAllocateEntity cust:custList) {
			//modify by jixiaohang
				String lastDateOld = cust.getLastDate();
				String lastDateNew = DateUtil.dateToStringSecond(lastDateOld);
				String createdOnOld = cust.getCreateDate();
				String createdOnNew = DateUtil.dateToStringSecond(createdOnOld);
				String nextDateOld= cust.getNextFollowDate();
				String nextDateNew = DateUtil.dateToStringSecond(nextDateOld);
			//modify by jixiaohang
			Map<String,String> custInfo = new HashMap<String,String>();
			custInfo.put("index","<label class='checkbox inline index'> <input type='checkbox' name='radios' />"+i+"</label>");
			custInfo.put("cstName",cust.getCusName());
			custInfo.put("mobileTel",cust.getTel());
			custInfo.put("userName",cust.getEmployeeName());
			custInfo.put("status", cust.getCustomStatus());
			custInfo.put("gfyx",cust.getIntentionType());
			custInfo.put("lastDate", lastDateNew);//modify by jixiaohang
			custInfo.put("gjfs",cust.getFollowWay());
			custInfo.put("createdOn",createdOnNew);//modify by jixiaohang
			custInfo.put("nextDate", nextDateNew);//modify by jixiaohang
			custInfo.put("operate", cust.getCusId());
			i++;
			lst.add(custInfo);
			

		}
		Map<String,Object> jsonResult = new HashMap<String,Object>();
		jsonResult.put("draw", para.getDraw());
		jsonResult.put("recordsTotal", dataNum);
		jsonResult.put("recordsFiltered", dataNum);
		jsonResult.put("data",lst);
		//����Ĵ���Ϊȫ������׼��
		jsonResult.put("assignNum",dataNum);
		
		List<ZsBusiCustomAllocateEntity> custList2 = new ArrayList<ZsBusiCustomAllocateEntity>();
		custList2 =  zsBusiCustomAllocateService.getZsCustFollowingDao_withoutPage(zsInfo);
		List<String> cusId_all=new ArrayList<String>();
		for (ZsBusiCustomAllocateEntity cust : custList2) {
			cusId_all.add(cust.getCusId());//Ϊȫ������׼��
		}
		jsonResult.put("ids",cusId_all.toString());
		return jsonResult;
	}
		
//��ù����ͻ�
	@RequestMapping(value="/getZsbusiCustomPublic")
	@ResponseBody
	public Map<String,Object>  getZsbusiCustomPublic(HttpServletRequest request,String cstStatus,String gfyx,String rebackReason,String startDate,String endDate,String userGuid,String telOrName,/*String proId,*/String userName2){
		DataTablesParameters para= RequestUtil.getDTPara(request);
		ZsBusiCustomAllocateVo zsinfo=new ZsBusiCustomAllocateVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		try {
			cstStatus=URLDecoder.decode(cstStatus,"UTF-8");
			userName2=URLDecoder.decode(userName2,"UTF-8");
			gfyx=URLDecoder.decode(gfyx,"UTF-8");
			rebackReason=URLDecoder.decode(rebackReason,"UTF-8");
			zsinfo.setStartIndex(para.getStart());
			zsinfo.setLength(para.getLength());
			zsinfo.setCstStatus(cstStatus);
			zsinfo.setGfyx(gfyx);
			zsinfo.setRebackReason(rebackReason);
			zsinfo.setUserGuid(userGuid);
			zsinfo.setProjGUID(projGuid);
			zsinfo.setStartTime(startDate);
			zsinfo.setEndTime(endDate);
			zsinfo.setUserName2(userName2);
			para.setColumnArray(new String[]{"","�ͻ�����","��ϵ�绰","","","�������","�����������","","","","",""});
			log.info(para.getOrderColumn());
			log.info(para.getOrderDir());
			zsinfo.setSortName(para.getOrderColumn());
			zsinfo.setSortDir(para.getOrderDir());
			if(telOrName !=null && !"".equals(telOrName)){//���ݿͻ����ƺ͵绰ģ����ѯ 
		         boolean isNum = telOrName.matches("[0-9]+"); 
		         if(isNum){ 
		        	 zsinfo.setMobileTel(telOrName); 
		         }else{ 
		        	String cstName=URLDecoder.decode(telOrName,"UTF-8");
		        	zsinfo.setCstName(cstName);
		         } 
		      }
			//zsinfo.setSortName("��ϵ�绰");
			//zsinfo.setSortDir("desc");
		} catch (UnsupportedEncodingException e) {
			log.info(e);
		}
		log.info(cstStatus);
		log.info(gfyx);
		int recordsTotal =zsBusiCustomAllocateService.getZsCustPubDupCountDao("�����ͻ�",zsinfo);
		int recordsFiltered=recordsTotal;
		log.info(recordsTotal);
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		List<ZsBusiCustomAllocateVo> custList = new ArrayList<ZsBusiCustomAllocateVo>();
		custList =  zsBusiCustomAllocateService.getZsCustPublicDao(zsinfo);
		log.info(custList.size());
		
		zsinfo.setLength(0);
		List<ZsBusiCustomAllocateVo>  exportList =  zsBusiCustomAllocateService.getZsCustPublicDao(zsinfo);
		List<ZsBusiCustomAllocateEntity> manu_custList = new ArrayList<ZsBusiCustomAllocateEntity>();
		for (ZsBusiCustomAllocateVo vo : exportList) {
			ZsBusiCustomAllocateEntity entity =new ZsBusiCustomAllocateEntity();
			entity.setCusName(vo.getCstName());
			entity.setTel(vo.getMobileTel());
			entity.setEmployeeName(vo.getUserName());
			entity.setFollowWay(vo.getGjfs());
			entity.setCustomStatus(vo.getStatus()); 
			entity.setLastDate(vo.getLastDate());
			entity.setNextFollowDate("");
			entity.setIntentionType(vo.getGfyx());
			entity.setCreateDate(vo.getFirstDate());
			entity.setCusId(vo.getCstGuid());
			manu_custList.add(entity);
		}
		zsExcelVo.setFollow_oveDue_invalid_VoList(manu_custList);
		int i=para.getStart()+1;
		ZsBusiCustomLogTable rebackInfo;
		int rebackCount=0;
		for(ZsBusiCustomAllocateVo cust:custList) {
			//modify by jixiaohang
			String lastDateOld = cust.getLastDate();
			String lastDateNew = DateUtil.dateToStringSecond(lastDateOld);
			//modify by jixiaohang
			Map<String,String> custInfo = new HashMap<String,String>();
			custInfo.put("index","<label class='checkbox inline index'> <input type='checkbox' name='radios' />"+i+"</label>");
			custInfo.put("cstName",cust.getCstName());
			custInfo.put("mobileTel",cust.getMobileTel());
			custInfo.put("userName",cust.getUserName());
			custInfo.put("status",cust.getStatus());
			custInfo.put("gfyx",cust.getGfyx());
			custInfo.put("lastDate",lastDateNew);//modify by jixiaohang
			custInfo.put("gjfs",cust.getGjfs());
			custInfo.put("id",cust.getCstGuid());
			rebackCount=zsBusiCustomAllocateService.getCusReBackCountByCusId(cust.getCstGuid(), "������־");
			if(rebackCount==0){
				custInfo.put("rebackReason","");
				custInfo.put("rebackDate","");
				custInfo.put("rebackNums","0");
			}else{
				rebackInfo=zsBusiCustomAllocateService.getCusReBackInfoByCusId(cust.getCstGuid(), "������־");
				//modify by jixiaohang
				String rebackDateOld = rebackInfo.getOperDate();
				String rebackDateNew = DateUtil.dateToStringSecond(rebackDateOld);
				//modify by jixiaohang
				custInfo.put("rebackReason",rebackInfo.getContent());
				custInfo.put("rebackDate",rebackDateNew);
				custInfo.put("rebackNums",rebackCount+"");
			}
			i++;
			lst.add(custInfo);
		}
		Map<String,Object> jsonResult = new HashMap<String,Object>();
		jsonResult.put("draw", para.getDraw());
		jsonResult.put("recordsTotal", recordsTotal);
		jsonResult.put("recordsFiltered", recordsFiltered);
		jsonResult.put("data",lst);
		//����Ĵ���Ϊȫ������׼��
		jsonResult.put("assignNum", recordsTotal);
		List<ZsBusiCustomAllocateVo> custList2 = new ArrayList<ZsBusiCustomAllocateVo>();
		zsinfo.setCstGuid("");
		zsinfo.setSortName("");
		zsinfo.setFlg_all("1");
		custList2 =  zsBusiCustomAllocateService.getZsCustPublicDao(zsinfo);
		List<String> cusId_all=new ArrayList<String>();
		for (ZsBusiCustomAllocateVo cust : custList2) {
			cusId_all.add(cust.getCstGuid());//Ϊȫ������׼��
		}
		jsonResult.put("ids", cusId_all.toString());
		log.info(cusId_all.toString());
		return jsonResult;
	}
	
//���������ͻ�
	@RequestMapping(value="/getZsbusiCustomDusBin")
	@ResponseBody
	public Map<String,Object> getZsbusiCustomDusBin(HttpServletRequest request,String cstStatus,String gfyx,String startDate,String endDate,String userGuid,String telOrName/*,String proId*/){
		DataTablesParameters para= RequestUtil.getDTPara(request);
		ZsBusiCustomAllocateVo zsinfo=new ZsBusiCustomAllocateVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		try {
			cstStatus=URLDecoder.decode(cstStatus,"UTF-8");
			gfyx=URLDecoder.decode(gfyx,"UTF-8");
			zsinfo.setStartIndex(para.getStart());
			zsinfo.setLength(para.getLength());
			zsinfo.setCstStatus(cstStatus);
			zsinfo.setGfyx(gfyx);
			zsinfo.setStartTime(startDate);
			zsinfo.setEndTime(endDate);
			zsinfo.setUserGuid(userGuid);
			zsinfo.setProjGUID(projGuid);
			para.setColumnArray(new String[]{"","�ͻ�����","��ϵ�绰","","","�������","�����������","","","","",""});
			log.info(para.getOrderColumn());
			log.info(para.getOrderDir());
			zsinfo.setSortName(para.getOrderColumn());
			zsinfo.setSortDir(para.getOrderDir());
			if(telOrName !=null && !"".equals(telOrName)){//���ݿͻ����ƺ͵绰ģ����ѯ 
		         boolean isNum = telOrName.matches("[0-9]+"); 
		         if(isNum){ 
		        	 zsinfo.setMobileTel(telOrName); 
		         }else{ 
		        	String cstName=URLDecoder.decode(telOrName,"UTF-8");
		        	zsinfo.setCstName(cstName);
		         } 
		      }
		} catch (UnsupportedEncodingException e) {
			log.info(e);
		}
		log.info(cstStatus);
		log.info(gfyx);
		int recordsTotal =zsBusiCustomAllocateService.getZsCustPubDupCountDao("������ͻ�",zsinfo);
		int recordsFiltered=recordsTotal;
		log.info(recordsTotal);
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		List<ZsBusiCustomAllocateVo> custList = new ArrayList<ZsBusiCustomAllocateVo>();
		custList =  zsBusiCustomAllocateService.getZsCustDusBinDao(zsinfo);
		log.info(custList.size());
		int i=para.getStart()+1;
		ZsBusiCustomLogTable rebackInfo;
		int rebackCount=0;
		for(ZsBusiCustomAllocateVo cust:custList) {
			//modify by jixiaohang
			String lastDateOld = cust.getLastDate();
			String lastDateNew = DateUtil.dateToStringSecond(lastDateOld);
			//modify by jixiaohang
			Map<String,String> custInfo = new HashMap<String,String>();
			custInfo.put("index","<label class='checkbox inline index'> <input type='checkbox' name='radios' />"+i+"</label>");
			custInfo.put("cstName",cust.getCstName());
			custInfo.put("mobileTel",cust.getMobileTel());
			custInfo.put("userName",cust.getUserName());
			custInfo.put("status",cust.getStatus());
			custInfo.put("gfyx",cust.getGfyx());
			custInfo.put("lastDate",lastDateNew);//modify by jixiaohang
			custInfo.put("gjfs",cust.getGjfs());
			custInfo.put("id",cust.getCstGuid());
			rebackCount=zsBusiCustomAllocateService.getCusReBackCountByCusId(cust.getCstGuid(), "������־");
			if(rebackCount==0){
				custInfo.put("rebackReason","");
				custInfo.put("rebackDate","");
				custInfo.put("rebackNums","0");
			}else{
				rebackInfo=zsBusiCustomAllocateService.getCusReBackInfoByCusId(cust.getCstGuid(), "������־");
				custInfo.put("rebackReason",rebackInfo.getContent());
				custInfo.put("rebackDate",rebackInfo.getOperDate());
				custInfo.put("rebackNums",rebackCount+"");
			}
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
//-----------------------------------ҳ���ʼ��------------------------------------------//	
	
	@RequestMapping(value="/custAssignInfo")
	@ResponseBody
	public ModelAndView custAssignInfo(@RequestParam String cusid) {
		log.info("dfeee");
		log.info("cusid="+cusid);
		return new ModelAndView("/wbem/business/customer/custAssignInfo","cusid",cusid);
		
	}
	//����ͻ�
	@RequestMapping(value="/allocateCustomer")
	@ResponseBody
	public String allocateCustomer(@RequestParam String data){
		log.info("fepei"+data);
		JSONObject object =JSONObject.fromObject(data);
		ZsInfoVo vo=(ZsInfoVo)JSONObject.toBean(object, ZsInfoVo.class);
		log.info(vo.getEmployeeId());
		log.info(vo.getCusId());
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		//log.info(httpSession.getAttribute("allocateCusId"));
		String cusIdList=vo.getCusId();//(String)httpSession.getAttribute("allocateCusId");
		boolean isDul=cusIdList.contains(",");
		int k=0;
		if(isDul){
			String[] culist=cusIdList.split(",");
			log.info(culist.length);
			//����ǰ���ֻ���
			String beforeName="";
			List<String> userList=new ArrayList<String>();
			for (String cusid : culist) {
				ZsInfoVo beforvo=new ZsInfoVo();
				beforvo.setCusId(cusid);
				List<User> beflist=zsBusiCustomAllocateService.getEmployeeList(beforvo);//���������
				if(beflist.size()>0){
					beforeName=beflist.get(0).getUsername();
				}else if(beflist.size()==0){
					beforeName="�����ͻ�";
				}
				log.info(beforeName);
				userList.add(beforeName);
			}
			for (String cusid : culist) {
				 k=zsBusiCustomAllocateService.allocateCustomers(vo.getEmployeeId(),cusid,projGuid);
			}
			//���������
			ZsInfoVo aftervo=new ZsInfoVo();
			aftervo.setEmployeeId(vo.getEmployeeId());
			List<User> afterlist=zsBusiCustomAllocateService.getEmployeeList(aftervo);
			String afterName="";
			if(afterlist.size()>0){
				 afterName=afterlist.get(0).getUsername();
			}
			log.info(afterName);
			//dd����
			sendDDAllocateMessage(userList,afterName);
		}else{
				//
				ZsInfoVo beforvo=new ZsInfoVo();
				beforvo.setCusId(cusIdList);
				List<User> beflist=zsBusiCustomAllocateService.getEmployeeList(beforvo);//���������
				String bname="";
				if(beflist.size()>0){
					bname=beflist.get(0).getUsername();
				}else if(beflist.size()==0){
					bname="�����ͻ�";
				}
				List<String> userList=new ArrayList<String>();
				userList.add(bname);
				 k=zsBusiCustomAllocateService.allocateCustomers(vo.getEmployeeId(),cusIdList,projGuid);
				 log.info(cusIdList);
				 //
				//���������
				ZsInfoVo aftervo=new ZsInfoVo();
				aftervo.setEmployeeId(vo.getEmployeeId());
				List<User> afterlist=zsBusiCustomAllocateService.getEmployeeList(aftervo);
				String afterName="";
				if(afterlist.size()>0){
					 afterName=afterlist.get(0).getUsername();
				}
				sendDDAllocateMessage(userList,afterName);
				 
		}
		
		log.info(k);
		return k+"";
		
	};
	//���տͻ�
	@RequestMapping(value="/reBackCustomer")
	@ResponseBody
	public String reBackCustomer(@RequestParam String data){
		int k=0;
		try {
			log.info(data);
			JSONObject object =JSONObject.fromObject(data);
			String cusId=object.get("cusId").toString();
			String reason =(String)object.get("reason");
			reason=URLDecoder.decode(reason,"utf-8");
			Session session = RBACSubject.getSecurityUtils().getSession();
			User user = (User)session.getAttribute("user");
			String projGuid = user.getExtInfo("projGuid");
			String fromwhere="�ֶ�����";
			log.info(cusId);
			log.info(reason);
			boolean isDul=cusId.contains(",");
			if(isDul){
				JSONArray array =JSONArray.fromObject(cusId);
				log.info(array.size());
				//����ǰ����ҵ���ʵ����ֻ�������
				String beforeName="";
				List<String> userList=new ArrayList<String>();
				for (Object cstid : array) {
					ZsInfoVo beforvo=new ZsInfoVo();
					beforvo.setCusId((String)cstid);
					List<User> beflist=zsBusiCustomAllocateService.getEmployeeList(beforvo);//���������
					if(beflist.size()>0){//�����еĻ���
						log.info("a ���");
						beforeName=beflist.get(0).getUsername();
						
					}else if(beflist.size()==0){//������Ļ���
						log.info("b ���");
						beforeName="�����ͻ�";
					}
					log.info(beforeName);
					userList.add(beforeName);
				}
				for (Object object2 : array) {
					ZsFollowInfoVo zsFollowInfoVo=new ZsFollowInfoVo();
					zsFollowInfoVo.setProgGuid(projGuid);
					zsFollowInfoVo.setCusId((String)object2);
					zsFollowInfoVo.setLength(0);
					List<ZsInfoVo> list=zsFollowInfoService.getZsCustInfo(zsFollowInfoVo);
					ZsInfoVo vo=list.get(0);
					k=zsBusiCustomAllocateService.reBackCustomers((String)object2,vo.getCusName(),vo.getTel(),reason,fromwhere,projGuid,oamap);
				}
				//dd����
				sendDDMessage(userList);
			}else {
					ZsFollowInfoVo zsFollowInfoVo=new ZsFollowInfoVo();
					zsFollowInfoVo.setProgGuid(projGuid);
					zsFollowInfoVo.setCusId(cusId);
					zsFollowInfoVo.setLength(0);
					List<ZsInfoVo> list=zsFollowInfoService.getZsCustInfo(zsFollowInfoVo);
					ZsInfoVo vo=list.get(0);
					//
					ZsInfoVo beforvo=new ZsInfoVo();
					beforvo.setCusId(cusId);
					List<User> beflist=zsBusiCustomAllocateService.getEmployeeList(beforvo);//���������
					String bname="";
					if(beflist.size()>0){//�����еĻ���
						log.info("a ���");
						bname=beflist.get(0).getUsername();
						
					}else if(beflist.size()==0){//������Ļ���
						log.info("b ���");
						bname="�����ͻ�";
					}
					k=zsBusiCustomAllocateService.reBackCustomers(cusId,vo.getCusName(),vo.getTel(),reason,fromwhere,projGuid,oamap);
					log.info(cusId);
					//
					User user2 = (User)RbacUtils.subject().getSession().getAttribute("user");
					String operator = user2.getRealName();
					String str0 = operator+"�ֶ���";
					String str1=bname+"��";
					String str2="1���ͻ����ճɹ����ͻ�";
					if(operator.length()%2==1){//����
						str0 = operator+"�ֶ���";
					}else{//ż��
						str0 = operator+"���ֶ���";
					}
					if(bname.length()%2==1){//����
						str1=bname+"��";
					}else{//ż��
						str1=bname+"�Ĺ�";
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
			            DingUtil.sendTextMessage(str0+str1+str2, duid, "", "37907377");//��ʽ
					} catch (Exception e) {
						// TODO: handle exception
					}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k+"";
		
	};
	public void sendDDMessage(List<String> userList){
		List<String> oppList=new ArrayList<String>();
		JSONArray opparray =JSONArray.fromObject(oppList);
		JSONArray userguidArray =JSONArray.fromObject(userList);
		User user2 = (User)RbacUtils.subject().getSession().getAttribute("user");
		String operator = user2.getRealName();
		//���պ󶤶�����
		Map<String, Object>ddMap=DingUtil.getDDmap(userguidArray, opparray);
		Set<Entry<String, Object>>sets=ddMap.entrySet();
		log.info(sets.size());
		for (Entry<String, Object> entry : sets) {
			int count=(int)entry.getValue();
			String bname=entry.getKey();
			String str0 = operator+"�ֶ���";
			String str1=bname+"��";
			String str1_1=Integer.toString(count);
			String str2="���ͻ����ճɹ����ͻ�";
			if(operator.length()%2==1){//����
				str0 = operator+"�ֶ���";
			}else{//ż��
				str0 = operator+"���ֶ���";
			}
			if(bname.length()%2==1){//����
				str1=bname+"��";
			}else{//ż��
				str1=bname+"�Ĺ�";
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
	            DingUtil.sendTextMessage(str0+str1+str1_1+str2, duid, "", "37907377");//��ʽ
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	public void sendDDAllocateMessage(List<String> userList,String aname){
		List<String> oppList=new ArrayList<String>();
		JSONArray opparray =JSONArray.fromObject(oppList);
		JSONArray userguidArray =JSONArray.fromObject(userList);
		User user2 = (User)RbacUtils.subject().getSession().getAttribute("user");
		String operator = user2.getRealName();
		//���պ󶤶�����
		Map<String, Object>ddMap=DingUtil.getDDmap(userguidArray, opparray);
		Set<Entry<String, Object>>sets=ddMap.entrySet();
		log.info(sets.size());
		for (Entry<String, Object> entry : sets) {
			int count=(int)entry.getValue();
			String bname=entry.getKey();
			log.info(entry.getKey()+" ");
			String str0 = operator+"�ֶ���";
			String str1=bname+"��";
			String str1_1=Integer.toString(count);
			String str2="���ͻ������"+aname;
			if(operator.length()%2==1){//����
				str0 = operator+"�ֶ���";
			}else{//ż��
				str0 = operator+"���ֶ���";
			}
			if(bname.length()%2==1){//����
				str1=bname+"��";
			}else{//ż��
				str1=bname+"�Ĺ�";
			}
			if(aname.length()%2==1){//����
				str2="���ͻ��������"+aname;
			}else{//ż��
				str2="���ͻ������"+aname;
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
	            DingUtil.sendTextMessage(str0+str1+str1_1+str2, duid, "", "37907377");//��ʽ
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	}
	@RequestMapping(value="/dusbinCustomers")
	@ResponseBody
	public String dusbinCustomers(@RequestParam String data) {
		log.info(data);
		JSONObject object =JSONObject.fromObject(data);
		String cusId=object.get("cusId").toString();
		//String proId = (String)object.get("proId");
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		log.info(cusId);
		boolean isDul=cusId.contains(",");
		int k=0;
		if(isDul){
			JSONArray array =JSONArray.fromObject(cusId);
			log.info(array.size());
			for (Object object2 : array) {
				k=zsBusiCustomAllocateService.dusbinCustomers("",(String)object2,"�ֶ�����",projGuid);
				log.info(object2);
			}
		}else {
				k=zsBusiCustomAllocateService.dusbinCustomers("",cusId,"�ֶ�����",projGuid);
			log.info(cusId);
		}
		return k+"";
		
		
	}

	//�����ҵ���ʵ���Ϣ
	@RequestMapping(value="/getEmployeeList")
	@ResponseBody
	public Map<String,Object> getEmployeeList(HttpServletRequest request,String telOrName) throws UnsupportedEncodingException{
		
		int dataNum;
		
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		List<XsCustomersManagerEntity> custList = new ArrayList<XsCustomersManagerEntity>();
		
		DataTablesParameters para= RequestUtil.getDTPara(request);
		//XsCustomersManagerVo xsInfo = new XsCustomersManagerVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		XsTeamGroupVo xsInfo = new XsTeamGroupVo();
		xsInfo.setGroupType("zs");
		xsInfo.setProperty("bs_userGuid");
		xsInfo.setProjectId(projGuid);
		if(telOrName !=null && !"".equals(telOrName)){//���ݿͻ����ƺ͵绰ģ����ѯ 
	         boolean isNum = telOrName.matches("[0-9]+"); 
	         if(isNum){ 
	        	xsInfo.setMobileTel(telOrName); 
	         }else{ 
	        	String cstName=URLDecoder.decode(telOrName,"UTF-8");
	        	xsInfo.setUserName(cstName);
	         } 
	     }
		List<XsTeamGroupEntity> allZygwList = xsTeamGroupService.getUserFromTeamGroup(xsInfo);
		dataNum = allZygwList.size();
		xsInfo.setStartIndex(para.getStart());
		xsInfo.setLength(para.getLength());
		
		List<XsTeamGroupEntity> zygwList = xsTeamGroupService.getUserFromTeamGroup(xsInfo);
		
		int i=para.getStart()+1;
		for(XsTeamGroupEntity zygw:zygwList) {
			//log.info(cust.getUserName());
			Map<String,String> zygwInfo = new HashMap<String,String>();
			zygwInfo.put("index",i+""+"<input type='hidden' value="+zygw.getUserId()+">");
			zygwInfo.put("userName",zygw.getUserName());
			zygwInfo.put("mobile",zygw.getMobile());
			zygwInfo.put("duty",zygw.getDescription());
			zygwInfo.put("userId",zygw.getUserId());
			zygwInfo.put("operate", "");
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
	
	//��ÿͻ��������id
	@RequestMapping(value="/getCustomInfoByCusId")
	@ResponseBody
	public ZsCustomTableVo getCustomInfoByCusId(@RequestParam String cusid,Model model){

		log.info(cusid);
		ZsCustomTableVo list=zsBusiCustomAllocateService.getCustomInfoByCusId(cusid);
		log.info(list.getTel());
		JSONObject object =new JSONObject();
		object.put("data", list);
		model.addAttribute("cus",list);
		return list;
	}
	//
	@RequestMapping(value="/getCusRecordLogByCusId")
	@ResponseBody
	public Map<String,Object> getCusRecordLogByCusId(HttpServletRequest request,String cusid, String logType/*,String proId*/){
		
		DataTablesParameters para= RequestUtil.getDTPara(request);
		ZsBusiCustomLogTable option = new ZsBusiCustomLogTable();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		option.setProjectId(projGuid);
		option.setLogType(logType);
		option.setCusId(cusid);
		option.setBelongSys("zs");
		log.info("logty  "+logType);
		List<ZsBusiCustomLogTable> list=zsBusiCustomAllocateService.getCusRecordLogByCusId(option);
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
		/*============================================
		log.info("hello"+logType);
		log.info(cusid);
		if("allocate".equals(logType)){
			logType="������־";
		}else if("reback".equals(logType)){
			logType="������־";
		}
		List<ZsBusiCustomLogTable> list=zsBusiCustomAllocateService.getCusRecordLogByCusId(cusid,logType);
		log.info(list.size());
		JSONObject object =new JSONObject();
		object.put("recordsTotal",list.size());
		object.put("recordsFiltered",list.size());
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		for(ZsBusiCustomLogTable cust:list) {
			Map<String,String> custInfo = new HashMap<String,String>();
			custInfo.put("id",cust.getId());
			custInfo.put("operDate",cust.getOperDate());
			custInfo.put("opercation",cust.getOpercation());
			custInfo.put("content",cust.getContent());
			lst.add(custInfo);
		}
		object.put("data", list);
		return object.toString();*/
		
	}
/*	@RequestMapping(value="/autoAllocateToPublicCustomer")
	public String autoAllocateToPublicCustomer(){
		// ���ڳ��������Ļ���
		
		List<ZsBusiCustomAllocateEntity> list=zsBusiCustomAllocateService.autoRebackOverCustomer();
		log.info(list.size());
		String cusId;
		if(list.size()>0){
			for (ZsBusiCustomAllocateEntity zsBusiCustomAllocateEntity : list) {
				log.info(zsBusiCustomAllocateEntity.getCusId());
				cusId=zsBusiCustomAllocateEntity.getCusId();
				zsBusiCustomAllocateService.reBackCustomers(cusId, "���ڿͻ��Զ�����","���ڿͻ��Զ�����","");
			}
		}
		return null;
		
	}
	@RequestMapping(value="/autoRebackInvalidCustomer")
	public String autoRebackInvalidCustomer(){
		//��Ч�ͻ����������Ϊ�����ͻ�
		List<ZsBusiCustomAllocateEntity> list=zsBusiCustomAllocateService.autoRebackInvalidCustomer();
		log.info(list.size());
		String cusId;
		if(list.size()>0){
			for (ZsBusiCustomAllocateEntity zsBusiCustomAllocateEntity : list) {
				log.info(zsBusiCustomAllocateEntity.getCusId());
				cusId=zsBusiCustomAllocateEntity.getCusId();
				zsBusiCustomAllocateService.reBackCustomers(cusId, "��Ч�ͻ��Զ�����","��Ч�ͻ��Զ�����","");
			}
		}
		return null;
		
	}*/
	
	
	@RequestMapping(value="/dailog/custAssignEmployeeList")
	public String custAssignEmployeeList(@RequestParam(value="cusId",required=false)String cusId,/*@RequestParam(value="proId",required=false)String proId ,*/Model model){
		
		/*String cusId_all=	(String) httpSession.getAttribute("cusId_all");
		log.info("cusli"+cusId_all.split(",").length);
		log.info("cusli"+cusId_all);*/
		
		log.info("����Ŀͻ�idΪ��"+cusId);
		String []cus = cusId.split(",");
		log.info("length:"+cus.length);
		model.addAttribute("cusNum", cus.length);
		//model.addAttribute("proId", "\""+proId+"\"");
		model.addAttribute("cusId", "\""+cusId+"\"");
		
		
		return "/wbem/business/customer/dailog/custAssignEmployeeList";
	}
	
	
	/**
	 * ���Ŀͻ�
	 * @return
	 */
	
	@RequestMapping(value="/updateZsBasicInfo")
	@ResponseBody
	public String updateZsBasicInfoDao(String data) {
		
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		log.info(data);
		String k="00";
			try {
				
				JSONObject object =JSONObject.fromObject(data);
				log.info(data);
				String cusId=object.get("cusId").toString();
				log.info(data);
				//String proId=object.get("proId").toString();
				log.info(data);
				String oldTel=(String)object.get("oldTel");
				log.info(data);
				String newTel = (String)object.get("newTel");
				log.info(data);
				String oldName=(String)object.get("oldName");
				String newName = (String)object.get("newName");
				String oldOperator=(String)object.get("oldOperator");
				oldName=URLDecoder.decode(oldName,"utf-8");
				newName=URLDecoder.decode(newName,"utf-8");
				oldOperator=URLDecoder.decode(oldOperator,"utf-8");
				log.info("not equal "+oldName+" "+newName+" "+oldOperator);
				SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String modifyDate = fm.format(new Date());
				String followPerson = httpSession.getAttribute("username").toString();
				ZsBusiCustomLogTable logInfo = new ZsBusiCustomLogTable();
				logInfo.setCusId(cusId);
				logInfo.setLogType("1");
				logInfo.setOperator(followPerson);
				logInfo.setOperDate(modifyDate);
				ZsInfoVo bean= new ZsInfoVo();
				bean.setCusId(cusId);
				if(!oldTel.equals(newTel) && !oldName.equals(newName)){
					logInfo.setContent("�ͻ��ֻ�����"+oldTel+"���Ϊ"+newTel+",�ͻ�������"+oldName+"��Ϊ"+newName);
					logInfo.setReason("�������ֻ���");
					bean.setCusName(newName);
					bean.setTel(newTel);
					k=zsInfoService.updateZsBasicInfo(bean);
				}else if(!oldTel.equals(newTel) && oldName.equals(newName)){
					logInfo.setContent("�ͻ��ֻ�����"+oldTel+"���Ϊ"+newTel);
					bean.setTel(newTel);
					//vo.setCstName(newName);
					logInfo.setReason("�ֻ���");
					zsInfoService.updateZsBasicInfo(bean);
				}else if(oldTel.equals(newTel) && !oldName.equals(newName)){
					logInfo.setContent("�ͻ�������"+oldName+"���Ϊ"+newName);
					//vo.setMobileTel(newTel);
					logInfo.setReason("����");
					bean.setCusName(newName);
					k=zsInfoService.updateZsBasicInfo(bean);
				}
				
				logInfo.setProjectId(projGuid);
				logInfo.setBelongSys("zs");
				logInfo.setOldOperator(oldOperator);
				//ZsBusiCustomLogTable table=new ZsBusiCustomLogTable(cusId, "3", DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"), operator, content, "�ֶ�����",proId,"xs",beforeName);
				
					k=logService.insertZsCusChangeLog(logInfo)+"";//�˴�����һ���޸��ֻ��ŵ��޸���־
				
				log.info("kisthe "+k);
			} catch (Exception e) {
				// TODO: handle exception
				log.info(e);
				k="00";
			}
		return k;
	}
	//����ͻ��е�_ģ������
	@RequestMapping(value="/import/import_exportExcelDemo")
	@ResponseBody
	public String  import_exportExcelDemo(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		//��ҵ�����б�
		XsTeamGroupVo xsInfo2 = new XsTeamGroupVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		xsInfo2.setGroupType("zs");
		xsInfo2.setUserLevelId("2");
		xsInfo2.setProperty("bs_userGuid");
		xsInfo2.setProjectId(projGuid);
		List<XsTeamGroupEntity> allZygwList = xsTeamGroupService.getUserFromTeamGroup(xsInfo2);
		List<String> list2=new ArrayList<String>();
		for (XsTeamGroupEntity vo : allZygwList) {
			list2.add(vo.getUserName());
		}
		//����
		List<XsCustomerImportVo> exportList = new ArrayList<XsCustomerImportVo>();
		JSONArray ja = JSONArray.fromObject(exportList);
		String[] titles={"�ͻ�����","�ֻ�����","��ͥ�绰","��˾�绰","����","�Ա�","��ǩ","���֤����","�������","����۸�","������","������;","�����","��ҵ����","��ס����","��������","��ͥ�ṹ","����ҵ̬","��������","��������","ҵ��׶�","��ҵ����","������Ŀ"};
		Map<String, Object> model=new HashMap<String,Object>();
		model.put("list", ja);
		model.put("zygwList", list2);
		model.put("titles", titles);
		model.put("class", ZsCustomerImportVo.class);
		List<XsTeamGroupEntity> projectList = xsTeamGroupService.getProjectItem();
		List<String> list3=new ArrayList<String>();
		for (XsTeamGroupEntity vo : projectList) {
			if(projGuid.equals(vo.getId())){//2016.8.23 Add
				list3.add(vo.getName());
			}
		}
		model.put("projectList", list3);
		zsExcelVo.setModel(model);
		log.info("hello");
		return "success";
		//return new ModelAndView(new ViewExcelExport(),model);
	}
	//����ͻ��е�_�ظ��ͻ�
	@RequestMapping(value="/import/import_exportDupCustomer")
	@ResponseBody
	public String  import_exportDupCustomer(HttpServletRequest request,HttpServletResponse response ,String data) throws Exception{
		
		//��ҵ�����б�
		XsTeamGroupVo xsInfo2 = new XsTeamGroupVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		xsInfo2.setGroupType("zs");
		xsInfo2.setUserLevelId("2");
		xsInfo2.setProperty("bs_userGuid");
		xsInfo2.setProjectId(projGuid);
		List<XsTeamGroupEntity> allZygwList = xsTeamGroupService.getUserFromTeamGroup(xsInfo2);
		List<String> list2=new ArrayList<String>();
		for (XsTeamGroupEntity vo : allZygwList) {
			list2.add(vo.getUserName());
		}
		//JSONObject jsonObject=JSONObject.fromObject(data);
		//List<ZsCustomerImportVo>dupCusList=(List<ZsCustomerImportVo>) jsonObject.get("dupList");
		//����
		//List<ZsCustomerImportVo> dupCusList=(List<ZsCustomerImportVo>) obj;
		//List<XsCustomerImportVo> exportList = new ArrayList<XsCustomerImportVo>();
		List<ZsCustomerImportVo> dupCusList=zsExcelVo.getDupCusList();
		JSONArray ja = JSONArray.fromObject(dupCusList);
		String[] titles={"�ͻ�����","�ֻ�����","��ͥ�绰","��˾�绰","����","�Ա�","��ǩ","���֤����","�������","����۸�","������","������;","�����","��ҵ����","��ס����","��������","��ͥ�ṹ","����ҵ̬","��������","��������","ҵ��׶�","��ҵ����","������Ŀ"};
		Map<String, Object> model=new HashMap<String,Object>();
		model.put("list", ja);
		model.put("zygwList", list2);
		model.put("titles", titles);
		model.put("class", ZsCustomerImportVo.class);
		List<XsTeamGroupEntity> projectList = xsTeamGroupService.getProjectItem();
		List<String> list3=new ArrayList<String>();
		for (XsTeamGroupEntity vo : projectList) {
			if(projGuid.equals(vo.getId())) {//2016.8.23 Add
				list3.add(vo.getName());
			}
		}
		model.put("projectList", list3);
		zsExcelVo.setModel(model);
		log.info("hello");
		return "success";
		//return new ModelAndView(new ViewExcelExport(),model);
	}
	@RequestMapping(value="/import/import_exportExcelDemo2")
	public ModelAndView  import_exportExcelDemo2(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		/*//��ҵ�����б�
		XsTeamGroupVo xsInfo2 = new XsTeamGroupVo();
		xsInfo2.setGroupType("zs");
		xsInfo2.setUserLevelId("3");
		List<XsTeamGroupEntity> allZygwList = xsTeamGroupService.getUserFromTeamGroup(xsInfo2);
		List<String> list2=new ArrayList<String>();
		for (XsTeamGroupEntity vo : allZygwList) {
			list2.add(vo.getUserName());
		}
		//����
		List<XsCustomerImportVo> exportList = new ArrayList<XsCustomerImportVo>();
		JSONArray ja = JSONArray.fromObject(exportList);
		String[] titles={"�ͻ�����","�ֻ�����","��ͥ�绰","��˾�绰","����","�Ա�","��ǩ","���֤����","�������","����۸�","������","������;","�����","��ҵ����","��ס����","��������","��ͥ�ṹ","����ҵ̬","��������","��������","ҵ��׶�","��ҵ����","������Ŀ"};
		Map<String, Object> model=new HashMap<String,Object>();
		model.put("list", ja);
		model.put("zygwList", list2);
		model.put("titles", titles);
		model.put("class", ZsCustomerImportVo.class);*/
		Map<String, Object> model=zsExcelVo.getModel();
		return new ModelAndView(new ViewExcelExport(),model);
	}
	//����ͻ�_���µ���ͻ�
	@RequestMapping(value = "/import/import_importCustomers", method = RequestMethod.POST)
	@ResponseBody
	  public  String import_importCustomers(@RequestParam("filename") MultipartFile file,
	      HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			List<ZsCustomerImportVo> list=new ExcelUploadImport().uploadFile_And_readExcelToList(file, request, response, ZsCustomerImportVo.class);
			if(list.size()==0){
				log.info("û����Ч����");
				return "1";
			}
			for (ZsCustomerImportVo vo : list) {
				//XsEmployeeTableEntity employee = new XsEmployeeTableEntity();
				if(vo.getUsername()==null || "".equals(vo.getUsername()) || "null".equalsIgnoreCase(vo.getUsername())){
					log.info("������ҵ����Ϊ�յ����������˲�");
					return "2";
				}
				XsTeamGroupVo xsInfo2 = new XsTeamGroupVo();
				Session session = RBACSubject.getSecurityUtils().getSession();
				User user = (User)session.getAttribute("user");
				String projGuid = user.getExtInfo("projGuid");
				xsInfo2.setGroupType("zs");
				xsInfo2.setUserLevelId("2");
				xsInfo2.setProperty("bs_userGuid");
				xsInfo2.setProjectId(projGuid);//
				xsInfo2.setUserName(vo.getUsername());
				List<XsTeamGroupEntity> allZygwList = xsTeamGroupService.getUserFromTeamGroup(xsInfo2);
				if(allZygwList==null || allZygwList.size()!=1){
					log.info("��ѡ��ҵ����û����ϵͳ��ά������˲�");
					return "3";
				}
			}
			List<XsTeamGroupEntity> projectList = xsTeamGroupService.getProjectItem();
			String projectGuid="";
			List<Map<String, Object>> telList=zsInfoService.getTel_allCustomer();
			List<ZsCustomerImportVo> dupCusList=new ArrayList<ZsCustomerImportVo>();
			int importCount=0;
			for (ZsCustomerImportVo o : list) {
				for (XsTeamGroupEntity entity : projectList) {
					if(o.getProjGuid().equals(entity.getName())){
						projectGuid=entity.getId();
						break;
					}
				}
				ZsInfoVo vo =new ZsInfoVo();
				vo.setCusName(o.getCstName());
				vo.setTel(o.getTel());
				vo.setHomeTel(o.getHomeTel());
				vo.setOfficeTel(o.getOfficeTel());
				vo.setFaxNo(o.getFaxNo());
				vo.setSex(o.getSex());
				vo.setLabel(o.getLabel());
				vo.setIdNo(o.getIdNo());
				vo.setIntentionArea(o.getIntentionArea());
				vo.setIntentionPrice(o.getIntentionPrice());
				vo.setIntentionRoom(o.getIntentionRoom());
				vo.setHouseUse(o.getHouseUse());
				vo.setAge(o.getAge());
				vo.setBuyTimes(o.getBuyTimes());
				vo.setAddrArea(o.getAddrArea());
				vo.setWorkComp(o.getWorkComp());
				vo.setFamilyInfo(o.getFamilyInfo());
				vo.setInvestmentInfo(o.getInvestmentInfo());
				vo.setCompetitor(o.getCompetitor());
				vo.setIntentionType(o.getIntentionType());
				vo.setCustomStatus(o.getCustomStatus());
				vo.setProgGuid(projectGuid);
				vo.setFirstVisWay("İ��");
				
				String currentTime = DateFormat.getCurrentDate(); 
				vo.setLastFollowDate(currentTime);
				vo.setCreateDate(currentTime);
				vo.setIntentionCreateDate(currentTime);
				vo.setIntentionModifyDate(currentTime);
				vo.setFollowDate(currentTime);
				vo.setClasses("0");//���ֵ��ʱû��ʵ�����壬���ݿ���Ʊ�������ֵ������ģ��ֵΪ0��
				vo.setValided("1");//�����ͻ�Ĭ�϶�����Ч�ͻ���ͨ���ͻ��޸����ı�ͻ�����Ч/��Ч
				//
				vo.setFollowInfo("ϵͳ����");
				vo.setFollowWay("ϵͳ����");
				//��ҵ�����б�
				XsTeamGroupVo xsInfo2 = new XsTeamGroupVo();
				xsInfo2.setGroupType("zs");
				xsInfo2.setUserLevelId("2");
				xsInfo2.setProperty("bs_userGuid");
				xsInfo2.setUserName(o.getUsername());
				xsInfo2.setProjectId(projectGuid);//
				List<XsTeamGroupEntity> allZygwList = xsTeamGroupService.getUserFromTeamGroup(xsInfo2);
				
				if(allZygwList.size()==1){
					boolean _insertFlg=true;
					 out: for (Map<String, Object> telMap : telList) {
						  Iterator<Entry<String, Object>> iterator=telMap.entrySet().iterator();
							while (iterator.hasNext()) {
								Entry<String, Object> entry=iterator.next();
								//log.info(entry.getKey()+" "+entry.getValue());
								String _tel=(String)entry.getValue();
								//log.info(o.getTel());
								if(!"".equals(o.getTel()) && _tel.contains(o.getTel()) ){
									log.info("tel: "+_tel);
									dupCusList.add(o);
									_insertFlg=false;
									break out;
								}
								if(!"".equals(o.getHomeTel()) && _tel.contains(o.getHomeTel()) ){
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
								}
							}
					  }
					if(_insertFlg){
						importCount++;
						String homeTel = vo.getHomeTel()==null?"null":(vo.getHomeTel().equals("")?"null":vo.getHomeTel());
						String officeTel = vo.getOfficeTel()==null?"null":(vo.getOfficeTel().equals("")?"null":vo.getOfficeTel().toString());
						vo.setPhoneNo(vo.getTel()+","+homeTel+","+officeTel);
						zsInfoService.insertZsBasicInfoDao(vo,allZygwList.get(0).getUserId());
						log.info(dupCusList.size());
					}
				}else {
					log.info("�������ҵ������������ҵ����û��ά��");
				}
			}
			
			
			//int count = list.size();
		    String strAlertMsg ="";
		    if(importCount!=0){
		      strAlertMsg= "�ɹ�����" + importCount + "����";
		    }else {
		      strAlertMsg = "����ʧ�ܣ�";
		    }
		    log.info(strAlertMsg);
		    model.addAttribute("result", strAlertMsg);
		    if(dupCusList.size()==0){
		    	 return "success";
		    }else {
		    	zsExcelVo.setDupCusList(dupCusList);
		    	 return "success_dusp";
		    }
		   
		} catch (Exception e) {
			log.info("error : "+e);
			return "0";
		}
	   // return "redirect:/wbem/business/customer/custAssignNav.action?menuId=15";
	  }
	/*
	 * �����ͻ�����
	 */
	@RequestMapping(value="/export/export_followingCustomer")
	@ResponseBody
	public String  export_followingCst(HttpServletRequest request,HttpServletResponse response) throws Exception{

		ZsCustomerExportVo exportVo;
		List<ZsBusiCustomAllocateEntity> custList = new ArrayList<ZsBusiCustomAllocateEntity>();
		custList=zsExcelVo.getFollow_oveDue_invalid_VoList();
		log.info(custList.size());
		List<ZsCustomerExportVo> exportList = new ArrayList<ZsCustomerExportVo>();
		for (ZsBusiCustomAllocateEntity entity : custList) {
			exportVo=new ZsCustomerExportVo(entity.getCusName(), entity.getTel(), entity.getEmployeeName(), entity.getFollowWay(), entity.getCustomStatus(), entity.getLastDate(), entity.getNextFollowDate(), entity.getIntentionType(),
					entity.getCreateDate(), entity.getCusId());
			
					
					exportList.add(exportVo);
		}
		log.info(custList.size()+" exelojos" +" exporlist "+exportList.size());
		//JSONObject   result=JSONObject.fromObject(custList);
		JSONArray ja = JSONArray.fromObject(exportList);
		//��ҵ�����б�
		XsTeamGroupVo xsInfo2 = new XsTeamGroupVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		xsInfo2.setGroupType("zs");
		xsInfo2.setUserLevelId("2");
		xsInfo2.setProperty("bs_userGuid");
		xsInfo2.setProjectId(projGuid);
		List<XsTeamGroupEntity> allZygwList = xsTeamGroupService.getUserFromTeamGroup(xsInfo2);
		List<String> list2=new ArrayList<String>();
		for (XsTeamGroupEntity vo : allZygwList) {
			list2.add(vo.getUserName());
		}
		String[] titles={"��ҵ��Ա","�ͻ�����","�ֻ�����","��ҵ����","������ʽ","ҵ��״̬","��������","�´θ�������","�ͻ�����","��������","�ͻ�GUID"};//{ "�������", "��������"}
		Map<String, Object> model=new HashMap<String,Object>();
		model.put("list", ja);
		model.put("zygwList", list2);
		model.put("titles", titles);
		model.put("class", ZsCustomerExportVo.class);
		zsExcelVo.setModel(model);
		return "success";
		//return new ModelAndView(new ViewExcelExport(),model);
	}
	@RequestMapping(value="/export/export_followingCustomer2")
	public ModelAndView  export_followingCst2(HttpServletRequest request,HttpServletResponse response) throws Exception{

		/*ZsCustomerExportVo exportVo;
		List<ZsBusiCustomAllocateEntity> custList = new ArrayList<ZsBusiCustomAllocateEntity>();
		custList=zsExcelVo.getFollow_oveDue_invalid_VoList();
		log.info(custList.size());
		List<ZsCustomerExportVo> exportList = new ArrayList<ZsCustomerExportVo>();
		for (ZsBusiCustomAllocateEntity entity : custList) {
			exportVo=new ZsCustomerExportVo(entity.getCusName(), entity.getTel(), entity.getEmployeeName(), entity.getFollowWay(), entity.getCustomStatus(), entity.getLastDate(), entity.getNextFollowDate(), entity.getIntentionType(),
					entity.getCreateDate(), entity.getCusId());
			
					
					exportList.add(exportVo);
		}
		log.info(custList.size()+" exelojos" +" exporlist "+exportList.size());
		//JSONObject   result=JSONObject.fromObject(custList);
		JSONArray ja = JSONArray.fromObject(exportList);
		//��ҵ�����б�
		XsTeamGroupVo xsInfo2 = new XsTeamGroupVo();
		xsInfo2.setGroupType("zs");
		xsInfo2.setUserLevelId("3");
		List<XsTeamGroupEntity> allZygwList = xsTeamGroupService.getUserFromTeamGroup(xsInfo2);
		List<String> list2=new ArrayList<String>();
		for (XsTeamGroupEntity vo : allZygwList) {
			list2.add(vo.getUserName());
		}
		String[] titles={"��ҵ��Ա","�ͻ�����","�ֻ�����","��ҵ����","������ʽ","ҵ��״̬","��������","�´θ�������","�ͻ�����","��������","�ͻ�GUID"};//{ "�������", "��������"}
		Map<String, Object> model=new HashMap<String,Object>();
		model.put("list", ja);
		model.put("zygwList", list2);
		model.put("titles", titles);
		model.put("class", ZsCustomerExportVo.class);*/
		Map<String, Object> model=zsExcelVo.getModel();
		return new ModelAndView(new ViewExcelExport(),model);
	}
	//�����ͻ�����_���·���ú���ͻ�����
	@RequestMapping(value = "/export/importAllocateResult", method = RequestMethod.POST)
	@ResponseBody
	  public  String export_importAllocateResult(@RequestParam("filename") MultipartFile file,
	      HttpServletRequest request,HttpServletResponse response,Model model/*,String proId*/){
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		log.info(projGuid);
		try {
			List<ZsCustomerExportVo> list=new ExcelUploadImport().uploadFile_And_readExcelToList(file, request, response, ZsCustomerExportVo.class);
			if(list.size()==0){
				log.info("û����Ч����");
				return "1";
			}
			for (ZsCustomerExportVo vo : list){
				//XsEmployeeTableEntity employee = new XsEmployeeTableEntity();
				log.info(vo.getNewZygw());
				if(vo.getNewZygw()==null || "".equals(vo.getNewZygw()) || "null".equalsIgnoreCase(vo.getNewZygw())){
					log.info("������ҵ����Ϊ�յ����������˲�");
					return "2";
				}
				XsTeamGroupVo xsInfo2 = new XsTeamGroupVo();
				xsInfo2.setGroupType("zs");
				xsInfo2.setUserLevelId("2");
				xsInfo2.setProperty("bs_userGuid");
				xsInfo2.setProjectId(projGuid);
				xsInfo2.setUserName(vo.getNewZygw());
				List<XsTeamGroupEntity> allZygwList = xsTeamGroupService.getUserFromTeamGroup(xsInfo2);
				if(allZygwList==null || allZygwList.size()!=1){
					log.info("��ѡ��ҵ����û����ϵͳ��ά������˲�");
					return "3";
				}
			}
			for (ZsCustomerExportVo vo : list) {
				XsTeamGroupVo xsInfo2 = new XsTeamGroupVo();
				xsInfo2.setGroupType("zs");
				xsInfo2.setUserLevelId("2");
				xsInfo2.setProperty("bs_userGuid");
				xsInfo2.setProjectId(projGuid);
				xsInfo2.setUserName(vo.getNewZygw());
				List<XsTeamGroupEntity> list2 = xsTeamGroupService.getUserFromTeamGroup(xsInfo2);
				 log.info("list2 "+list2.size()+" "+list2.get(0).getUserId());
				 String employeeId="";
				 employeeId=list2.get(0).getUserId();
				 zsBusiCustomAllocateService.allocateCustomers(employeeId,vo.getCusId(),projGuid);
			}
			return "success";
		} catch (Exception e) {
			log.info("error : "+e);
			return "0";
		}
	}
	@RequestMapping(value = "/getTels" )//method = RequestMethod.POST
	@ResponseBody
	public  List<Map<String, Object>> getTels(){
		//����
		String tel="15383456718";
		String _a="15383456718,null,null";
		  List<Map<String, Object>> list=zsInfoService.getTel_allCustomer();
		  log.info(list.size());
		  for (Map<String, Object> telMap : list) {
			  Iterator<Entry<String, Object>> iterator=telMap.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, Object> entry=iterator.next();
					//log.info(entry.getKey()+" "+entry.getValue());
					String _tel=(String)entry.getValue();
					if(_tel.contains("15383456718")){
						log.info("abcddd");
						continue;
					}
				}
		  }
		  return list;
	}
	public static void main(String[] args) {
		String tel="28a";
		String _a="15383456718a";
		System.out.println(tel.contains(_a));
		System.out.println(_a.contains(tel));
	}
}
