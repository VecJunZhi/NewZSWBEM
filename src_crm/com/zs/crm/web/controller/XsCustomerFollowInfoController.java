package com.zs.crm.web.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.busi.utils.LogUtil;
import com.zs.common.entity.DataTablesParameters;
import com.zs.common.entity.SearchOptionEntity;
import com.zs.common.service.IOptionListService;
import com.zs.common.util.DateUtil;
import com.zs.common.util.RequestUtil;
import com.zs.common.util.search.TagOption;
import com.zs.crm.entity.XsCustomersManagerEntity;
import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.crm.service.XsFollowInfoService;
import com.zs.rbac.core.RBACSubject;
import com.zs.rbac.entity.User;
import com.zs.rbac.service.XsTeamGroupService;
import com.zs.crm.service.impl.XsFollowSearch;
import com.zs.crm.web.vo.XsCustomersManagerVo;
import com.zs.crm.web.vo.XsTeamGroupVo;

@Controller
@RequestMapping(value="/wbem/houses/customer")
public class XsCustomerFollowInfoController{
	
	Log log=LogUtil.getLog();
	@Autowired 
	XsFollowInfoService xsFollowInfoService;
	@Autowired
	IOptionListService listservice;
	@Autowired
	HttpSession httpSession;
	@Autowired
	XsTeamGroupService xsTeamGroupService;
	
	/**
	 * 加载客户详情页面的搜索选项
	 * @param session
	 * @return
	 */
	@RequiresPermissions(value="page:wbem:crm:xs:custFollow")
	@RequestMapping(value="/custFollow")
	public String loadSearchOption(Model model) {
		List<SearchOptionEntity> optionList = new ArrayList<SearchOptionEntity>();
		SearchOptionEntity option = new SearchOptionEntity();
		option.setModule("xscustsearch");
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
		log.info(optionListShow.size());
		int dataNum = xsFollowInfoService.getXsCustCount(new XsCustomersManagerVo());
		log.info(dataNum);
		model.addAttribute("optionList", optionList);
		
		//List<XsTeamGroupEntity> projectList = xsTeamGroupService.getProjectItem();
		//model.addAttribute("projectList", projectList);
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		XsTeamGroupVo searchInfo = new XsTeamGroupVo();
		//searchInfo.setProjectId("8FFF2136-61EA-E411-BAAF-FCAA145C42F2");
		searchInfo.setGroupType("xs");
		searchInfo.setUserLevelId("2");
		searchInfo.setProperty("xs_userGuid");
		searchInfo.setProjectId(projGuid);
		List<XsTeamGroupEntity> zygwList = xsTeamGroupService.getUserFromTeamGroup(searchInfo);
		model.addAttribute("zygwList", zygwList);
		//log.info("project.size:"+projectList.size());
		log.info("zygw.size:"+zygwList.size());
		return "/wbem/houses/customer/custFollow";
	}
	
	
	@RequestMapping(value="/getXsCustInfo")
	@ResponseBody
	public Map<String,Object> getXsCustInfo1(HttpServletRequest request,/*String proId,*/String userId,String cstName,String cstTel,String startDate,String endDate) throws Exception {
		//获取DataTables公共参数
		DataTablesParameters para= RequestUtil.getDTPara(request);
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		XsCustomersManagerVo xsinfo = new XsCustomersManagerVo();
		xsinfo.setStartIndex(para.getStart());
		xsinfo.setLength(para.getLength());
		xsinfo.setProjGUID(projGuid);
		xsinfo.setUserGuid(userId);
		xsinfo.setCstName(URLDecoder.decode(cstName,"utf-8"));
		xsinfo.setMobileTel(cstTel);
		//测试
		/*默认排序方式""*/
		xsinfo.setSortDir("desc");
		xsinfo.setSortName("lastDate");
		
		xsinfo.setStartTime(startDate+" 00:00:00");
		xsinfo.setEndTime(endDate+" 23:59:59");//2016-7-9 L
		
		int recordsTotal = xsFollowInfoService.getXsCustCount(xsinfo);    //总数，不对
		int recordsFiltered = recordsTotal;   							  //搜索数
		List<XsCustomersManagerEntity> custList =  xsFollowInfoService.getXsCustInfo(xsinfo);
		
		List<Map<String,String>> childList = new ArrayList<Map<String,String>>();
		Map<String,String> dataMap = new HashMap<String,String>();
		for(int i=0;i<custList.size();i++) {
			
			//modify by jixiaohang
				String gjDateOld=custList.get(i).getGjDate();
				String gjDateNew = DateUtil.dateToStringSecond(gjDateOld);
			//modify by jixiaohang
			dataMap = new HashMap<String,String>();
			dataMap.put("index",para.getStart()+i+1+"");
			dataMap.put("id", custList.get(i).getCstGuid());
			dataMap.put("cstName",custList.get(i).getCstName());
			dataMap.put("mobileTel",custList.get(i).getMobileTel());
			dataMap.put("gjDate",gjDateNew);
			dataMap.put("userName",custList.get(i).getUserName());
			dataMap.put("gjfs",custList.get(i).getGjfs());
			dataMap.put("gjContent", custList.get(i).getGjContent());
			childList.add(dataMap);
		}
		
		Map<String,Object> jsonResult = new HashMap<String,Object>();
		jsonResult.put("draw", para.getDraw());
		jsonResult.put("recordsTotal", recordsTotal);
		jsonResult.put("recordsFiltered", recordsFiltered);
		jsonResult.put("data",childList);
		return jsonResult;
	}

	/**
	 * 
	 * @param cusid
	 * @return page:wbem:crm:xs:custFollow
	 */
	@RequiresPermissions(value="page:wbem:crm:xs:custFollowInfo")
	@RequestMapping("custFollowInfo")
	public String followInfo(String cusid,String pid,Model model) {
		List<SearchOptionEntity> optionList = new ArrayList<SearchOptionEntity>();
		SearchOptionEntity option = new SearchOptionEntity();
		option.setModule("xsfollowsearch");
		optionList = listservice.getOptionListByModule(option);
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
		log.info(optionListShow.size());
		model.addAttribute("followListShow", optionListShow);
		model.addAttribute("cusid", cusid);
		model.addAttribute("pid", pid);
		model.addAttribute("startDate", DateUtil.getCurrentTime("yyyy-MM")+"-01");
		model.addAttribute("endDate", DateUtil.getCurrentTime("yyyy-MM-dd"));
		log.info("cusid="+cusid);
		//return new ModelAndView("/wbem/houses/customer/custFollowInfo","cusid","123");
		return "/wbem/houses/customer/custFollowInfo";
	}
	/**
	 * 
	 * @param aoData
	 * @param cusid
	 * @return getXsFollowInfo
	 */
	@RequestMapping("/getXsFollowInfo")
	@ResponseBody
	public Map<String,Object> getXsFollowInfo(HttpServletRequest request,String pid,String cusid,String startDate,String endDate) {
		//获取DataTables公共参数
		DataTablesParameters para= RequestUtil.getDTPara(request);

		XsCustomersManagerVo xsFollowInfo = new XsCustomersManagerVo();
		xsFollowInfo.setCstGuid(cusid);
		xsFollowInfo.setStartIndex(para.getStart());
		xsFollowInfo.setLength(para.getLength());
		xsFollowInfo.setStartFollowTime(startDate);
		xsFollowInfo.setEndFollowTime(endDate);
		
		int recordsTotal = xsFollowInfoService.getXsFollowInfoCountByCusid(xsFollowInfo);
		log.info("cusid="+cusid);
		log.info("recordsTotal="+recordsTotal);
		List<XsCustomersManagerEntity> followInfoList = xsFollowInfoService.getXsFollowInfoByCusid(xsFollowInfo);
		
		List<Map<String,String>> childList = new ArrayList<Map<String,String>>();
		Map<String,Object> jsonResult = new HashMap<String,Object>();
		Map<String,String> dataMap = new HashMap<String,String>();
		for(int i=0;i<followInfoList.size();i++) {
			dataMap = new HashMap<String,String>();
			log.info(followInfoList.get(i).getGjDate()+" "+followInfoList.get(i).getUserName());
			dataMap.put("index",para.getStart()+i+1+"");
			dataMap.put("followDate",followInfoList.get(i).getGjDate());
			dataMap.put("followWay", followInfoList.get(i).getGjfs());
			dataMap.put("followInfo",followInfoList.get(i).getGjContent());
			dataMap.put("followPerson", followInfoList.get(i).getUserName());
			childList.add(dataMap);
		}
		jsonResult.put("draw", para.getDraw());
		jsonResult.put("recordsTotal", recordsTotal);
		jsonResult.put("recordsFiltered", recordsTotal);
		jsonResult.put("data",childList);
		return jsonResult;
	}

}