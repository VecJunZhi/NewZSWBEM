package com.zs.busi.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.axis2.dataretrieval.Data;
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
import org.springframework.web.servlet.ModelAndView;

import com.zs.busi.entity.ZsFollowInfoVo;
import com.zs.busi.entity.ZsInfoVo;
import com.zs.busi.service.ZsFollowInfoService;
import com.zs.busi.service.impl.ZsFollowSearch;
import com.zs.busi.utils.LogUtil;
import com.zs.common.entity.DataTablesParameters;
import com.zs.common.entity.SearchOptionEntity;
import com.zs.common.service.IOptionListService;
import com.zs.common.util.DateUtil;
import com.zs.common.util.RequestUtil;
import com.zs.common.util.search.TagOption;
import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.rbac.core.RBACSubject;
import com.zs.rbac.entity.User;
import com.zs.rbac.service.XsTeamGroupService;
import com.zs.crm.web.vo.XsTeamGroupVo;

@Controller
@RequestMapping(value="/wbem/business/customer")
public class ZsFollowInfoController{
	
	Log log=LogUtil.getLog();
	@Autowired 
	ZsFollowInfoService zsFollowInfoService;
	@Autowired
	IOptionListService listservice;
	@Autowired
	HttpSession httpSession;
	@Autowired
	XsTeamGroupService xsTeamGroupService;
	/**
	 * 
	 * @param aoData
	 * @return
	 */
	@RequiresPermissions(value="page:wbem:crm:zs:custFollow")
	@RequestMapping(value="/getZsCustInfo",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getZsCustInfo(HttpServletRequest request,/*String proId,*/String userId,String cstName,String cstTel,String startDate,String endDate) {
		//获取DataTables公共参数
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		DataTablesParameters para= RequestUtil.getDTPara(request);
		ZsFollowInfoVo zsinfo = new ZsFollowInfoVo();
		zsinfo.setProgGuid(projGuid);
		zsinfo.setStartIndex(para.getStart());
		zsinfo.setLength(para.getLength());
		zsinfo.setSortDir("desc");
		zsinfo.setSortName("跟进日期");
		zsinfo.setStartTime(startDate);
		zsinfo.setEndTime(endDate);
		try {
			cstName = URLDecoder.decode(cstName,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("URLDecoderError:", e);
		}
		zsinfo.setCusName(cstName);
		zsinfo.setTel(cstTel);
		zsinfo.setEmployeeId(userId);//置业顾问id
		int recordsTotal = zsFollowInfoService.getZsCustCount(zsinfo);
		int recordsFiltered = recordsTotal;
		List<ZsInfoVo> custList =  zsFollowInfoService.getZsCustInfo(zsinfo);
		List<Map<String,String>> childList = new ArrayList<Map<String,String>>();
		for(int i=0;i<custList.size();i++) {
			ZsInfoVo cust = custList.get(i);
			//modify by jixiaohang
			String followDateOld = cust.getFollowDate();
			String followDateNew=DateUtil.dateToStringSecond(followDateOld);
			//modify by jixiaohang
			Map<String,String> custInfo = new HashMap<String,String>();
			custInfo.put("index",para.getStart()+i+1+"");
			custInfo.put("name",cust.getCusName());
			custInfo.put("tel", cust.getTel());
			custInfo.put("followDate", followDateNew);//modify by jixiaohang
			custInfo.put("followInfo", cust.getFollowInfo());
			custInfo.put("userName", cust.getEmployeeName());
			custInfo.put("followWay",cust.getFollowWay());
			childList.add(custInfo);
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
	 * @return
	 */
	@RequestMapping("/wbem/test")
	public String test() {
		log.info("test");
		return "/wbem/business/customer/custAssign";
	}
	/**
	 * 
	 * @param cusid
	 * @return
	 */
	@RequiresPermissions(value="page:wbem:crm:zs:custFollowInfo")
	@RequestMapping(value="/custFollowInfo")
	@ResponseBody
	public ModelAndView followInfo(@RequestParam String cusid) {
		List<SearchOptionEntity> optionList = new ArrayList<SearchOptionEntity>();
		SearchOptionEntity option = new SearchOptionEntity();
		option.setModule("followsearch");
		optionList = listservice.getOptionListByModule(option);
		log.info("size"+optionList.size());
		ZsFollowSearch hh = new ZsFollowSearch();
		List<String> optionListShow = new ArrayList<String>();
		List<String> queryList = new ArrayList<String>();
		for(SearchOptionEntity option1:optionList) {
			log.info(option1.getModule());
			log.info(option1.getName());
			log.info("isEdit:"+option1.isEdit());
			TagOption to = hh.generateHtmlEntity(option1);
			String test = hh.jointHtmlStr(to);
			if(test.indexOf("button") != -1)
				queryList.add(test);
			else
				optionListShow.add(test);
		}
		log.info(optionListShow.size());
		httpSession.setAttribute("followListShow", optionListShow);
		httpSession.setAttribute("followQueryList", queryList);
		log.info("cusid="+cusid);
		return new ModelAndView("/wbem/business/customer/custFollowInfo","cusid",cusid);
	}
	/**
	 * 
	 * @param aoData
	 * @param cusid
	 * @return
	 */
	@RequiresPermissions(value="page:wbem:crm:zs:custFollowInfo")
	@RequestMapping("/getZsFollowInfo")
	@ResponseBody
	public String getZsFollowInfo(@RequestParam String aoData,@RequestParam String cusid) {
		JSONArray jsonarray = JSONArray.fromObject(aoData);
		int dataNum;
		int iDisplayStart = 0; // 起始索引
		int iDisplayLength = 0; // 每页显示的行数
		String str;//search中输入的查询字符串
		String search="";
		ZsFollowInfoVo zsFollowInfo = new ZsFollowInfoVo();
		for (int i = 0; i < jsonarray.size(); i++) {
        JSONObject obj = (JSONObject) jsonarray.get(i);
        	if (obj.get("name").equals("iDisplayStart"))
        		iDisplayStart = obj.getInt("value");
        	if (obj.get("name").equals("iDisplayLength"))
        		iDisplayLength = obj.getInt("value");
        	if(obj.get("name").equals("sSearch")) {
        		str = obj.getString("value");
        	}   //获取搜索框中的内容,根据该内容进行查找
        	if(obj.get("name").equals("aaSorting")) {
        		str = obj.getString("value");
        	}
        	if(obj.get("name").equals("sSearch")) {
        		search = obj.getString("value");
        		System.out.println("search="+search);
        	}
        	if(obj.get("name").equals("sSearch_1")) {//搜索的实现
        		search = obj.getString("value");
        		log.info("content="+search+"len="+search.length());
        		if(search.length() != 0) {
        			try{
        				search = URLDecoder.decode(search,"utf-8");
        				log.info(search);
        			}catch(Exception e) {
        				e.printStackTrace();
        			};
        			JSONObject option = JSONObject.fromObject(search);
        			zsFollowInfo = (ZsFollowInfoVo)JSONObject.toBean(option,ZsFollowInfoVo.class);
        		}
        	}
		}
		log.info("start="+iDisplayStart+",len="+iDisplayLength);
		JSONObject getObj = new JSONObject();
		List<ZsInfoVo> followInfoList = new ArrayList<ZsInfoVo>();
		zsFollowInfo.setCusId(cusid);
		zsFollowInfo.setStartIndex(iDisplayStart);
		zsFollowInfo.setLength(iDisplayLength);
		dataNum = zsFollowInfoService.getZsFollowInfoCountByCusid(zsFollowInfo);
		log.info("cusid="+cusid);
		log.info("dataNum="+dataNum);
		followInfoList = zsFollowInfoService.getZsFollowInfoByCusid(zsFollowInfo);
	
		getObj.put("recordsTotal",dataNum);
		getObj.put("recordsFiltered",dataNum);
		
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();

		log.info(followInfoList.size());
		int i=iDisplayStart+1;
		for(ZsInfoVo cust:followInfoList) {
			Map<String,String> custInfo1 = new HashMap<String,String>();
			custInfo1.put("index",i+"");
			custInfo1.put("followDate",cust.getFollowDate());
			custInfo1.put("followWay", cust.getFollowWayEach());
			custInfo1.put("followInfo",cust.getFollowInfo());
			custInfo1.put("followPerson", cust.getEmployeeName());
			i++;
			lst.add(custInfo1);
		}
		getObj.put("data",lst);
		return getObj.toString();
	}
	/**
	 * 加载客户详情页面的搜索选项
	 * @param session
	 * @return
	 */
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
	@RequiresPermissions(value="page:wbem:crm:zs:custFollow")
	@RequestMapping(value="/custFollow")
	public String loadCusSearchOption(Model model) {
		List<String> optionListShow = new ArrayList<String>();
		optionListShow = loadSearchOption("custsearch");
		List<String> queryList = new ArrayList<String>();
		List<String> optionList = new ArrayList<String>();
		log.info(optionListShow.size());
		for(String option:optionListShow) {
			if(option.indexOf("button") != -1) {
				queryList.add(option);
				log.info("555");
			}
			else {
				optionList.add(option);
			}
		}
		log.info(queryList.size()+"size");
		log.info(optionList.size()+"size");
		httpSession.setAttribute("optionListShow", optionList);
		httpSession.setAttribute("queryList", queryList);
		
		
		/*List<XsTeamGroupEntity> projectList = xsTeamGroupService.getProjectItem();
		model.addAttribute("projectList", projectList);*/
		XsTeamGroupVo searchInfo = new XsTeamGroupVo();
		//searchInfo.setProjectId("8FFF2136-61EA-E411-BAAF-FCAA145C42F2");
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		searchInfo.setGroupType("zs");
		searchInfo.setUserLevelId("2");
		searchInfo.setProperty("bs_userGuid");
		searchInfo.setProjectId(projGuid);
		List<XsTeamGroupEntity> zygwList = xsTeamGroupService.getUserFromTeamGroup(searchInfo);
		model.addAttribute("zygwList", zygwList);
		//log.info("project.size:"+projectList.size());
		log.info("zygw.size:"+zygwList.size());
		return "/wbem/business/customer/custFollow";
	}
}

