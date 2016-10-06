package com.zs.busi.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis2.transport.http.util.URIEncoderDecoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.busi.entity.ZsRentSearchInfoVo;
import com.zs.busi.entity.ZsRentTableEntity;
import com.zs.busi.service.ZsRentService;
import com.zs.common.entity.DataTablesParameters;
import com.zs.common.util.RequestUtil;
import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.rbac.core.RBACSubject;
import com.zs.rbac.entity.User;
import com.zs.rbac.service.XsTeamGroupService;
import com.zs.crm.web.controller.XsCustomerController;
import com.zs.crm.web.vo.XsTeamGroupVo;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/wbem/business/customer")
public class ZsRentController {
	@Autowired
	ZsRentService zsRentService;
	@Autowired
	XsTeamGroupService xsTeamGroupService;
	private Log log=LogFactory.getLog(XsCustomerController.class);
	
	@RequiresPermissions(value="page:rentManage:rentInfo:index")
	@RequestMapping("/zsRentPage")
	public String zsRentPage(Model model) {
		/*List<XsTeamGroupEntity> projectList = xsTeamGroupService.getProjectItem();
		model.addAttribute("projectList", projectList);*/
		
		return "/wbem/business/customer/zsRentPage";
	}
	
	@RequiresPermissions(value="page:rentManage:rentInfo:index")
	@RequestMapping("/loadRentList")
	@ResponseBody
	public Map<String,Object> loadRentList(HttpServletRequest request,/*String projGuid,*/String saleStatus,String shopStatus) throws UnsupportedEncodingException {
		ZsRentSearchInfoVo searchInfo = new ZsRentSearchInfoVo();
		List<ZsRentTableEntity> rentList = new ArrayList<ZsRentTableEntity>();
		List<Map<String,String>> dataList = new ArrayList<Map<String,String>>();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		int totalNum = 0;
		DataTablesParameters para= RequestUtil.getDTPara(request);
		int startIndex = para.getStart();
		int length = para.getLength();
		searchInfo.setProjGuid(projGuid);
		searchInfo.setSaleStatus(URLDecoder.decode(saleStatus, "utf-8"));
		searchInfo.setShopStatus(URLDecoder.decode(shopStatus, "utf-8"));
		totalNum = zsRentService.getZsRentInfoList(searchInfo).size();
		searchInfo.setStartIndex(startIndex);
		searchInfo.setLength(length);
		rentList = zsRentService.getZsRentInfoList(searchInfo);
		int i = startIndex+1;
		for(ZsRentTableEntity rent:rentList) {
			Map<String,String> rentInfo = new HashMap<String,String>();
			rentInfo.put("index","<label class='checkbox inline index'> <input type='checkbox' name='radios' />"+i+"</label>");
			rentInfo.put("unitNo", rent.getUnitNo());
         	rentInfo.put("addressNo", rent.getAddressNo());
         	rentInfo.put("firstArea", rent.getFirstArea());
         	rentInfo.put("secondArea", rent.getSecondArea());
         	rentInfo.put("totalArea", rent.getTotalArea());
         	rentInfo.put("saleStatus", rent.getSaleStatus());
         	rentInfo.put("shopStatus", rent.getShopStatus());
         	rentInfo.put("rentStatus", rent.getRentStatus());
         	rentInfo.put("totalRent", rent.getTotalRent());
         	rentInfo.put("dayRent", rent.getDayRent());
         	rentInfo.put("signUpDate", (rent.getSignUpDate()==null || "".equals(rent.getSignUpDate()))?"":rent.getSignUpDate().substring(0, 10));
         	rentInfo.put("intentionCst", rent.getIntentionCst());
         	rentInfo.put("investmentOfficer", rent.getInvestmentOfficer());
         	rentInfo.put("keyAccountManager", rent.getKeyAccountManager());
         	rentInfo.put("operate", rent.getUnitNo());
         	dataList.add(rentInfo);
         	i++;
		}
		Map<String,Object> queryResult = new HashMap<String,Object>();
		queryResult.put("draw", para.getDraw());
		queryResult.put("recordsTotal", totalNum);
		queryResult.put("recordsFiltered", totalNum);
		queryResult.put("data",dataList);
		return queryResult;
	}
	
	@RequiresPermissions(value="page:rentManage:rentInfo:editRentInfo")
	@RequestMapping("/editRentPage")
	public String addRentInfo(/*@RequestParam(value="projGuid",required=true)String projGuid,*/@RequestParam(value="unitNo",required=true)String unitNo,Model model) {
		log.info("unitNo:"+unitNo);
		ZsRentSearchInfoVo searchInfo = new ZsRentSearchInfoVo();
		List<ZsRentTableEntity> rentList = new ArrayList<ZsRentTableEntity>();
		ZsRentTableEntity rentInfo = new ZsRentTableEntity();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		searchInfo.setProjGuid(projGuid);
		searchInfo.setUnitNo(unitNo);
		rentList = zsRentService.getZsRentInfoList(searchInfo);
		if(rentList != null && rentList.size() != 0) {
			rentInfo = rentList.get(0);
			String signUpDate = (rentInfo.getSignUpDate()==null || "".equals(rentInfo.getSignUpDate()))?"":rentInfo.getSignUpDate().substring(0, 10);
			rentInfo.setSignUpDate(signUpDate);
		}
		XsTeamGroupVo teamGroup = new XsTeamGroupVo();
		teamGroup.setGroupType("zs");
		teamGroup.setUserLevelId("2");
		teamGroup.setProperty("bs_userGuid");
		teamGroup.setProjectId(projGuid);
		List<XsTeamGroupEntity> zygwList = xsTeamGroupService.getUserFromTeamGroup(teamGroup);//置业顾问列表
		model.addAttribute("zygwList", zygwList);
		model.addAttribute("rentInfo", rentInfo);
		model.addAttribute("unitNo", "\""+unitNo+"\"");
		//model.addAttribute("projGuid", "\""+projGuid+"\"");
		return "/wbem/business/customer/editRentInfo";
	}
	
	@RequiresPermissions(value="page:rentManage:rentInfo:editRentInfo")
	@RequestMapping("/updateRentInfo")
	@ResponseBody
	public int updateZsBasicInfoDao(String data) throws UnsupportedEncodingException {
		ZsRentTableEntity rentInfo = new ZsRentTableEntity();
		JSONObject object =JSONObject.fromObject(data);
		//String projGuid = object.get("projGuid").toString();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		String unitNo = object.get("unitNo").toString();
		String saleStatus = URLDecoder.decode(object.get("saleStatus").toString(),"utf-8");
		String shopStatus = URLDecoder.decode(object.get("shopStatus").toString(),"utf-8");
		String rentStatus = URLDecoder.decode(object.get("rentStatus").toString(),"utf-8");
		String totalRent = URLDecoder.decode(object.get("totalRent").toString(),"utf-8");
		String dayRent = URLDecoder.decode(object.get("dayRent").toString(),"utf-8");
		String signUpDate = object.get("signUpDate").toString();
		String intentionCst = URLDecoder.decode(object.get("intentionCst").toString(),"utf-8");
		String investmentOfficer = URLDecoder.decode(object.get("investmentOfficer").toString(),"utf-8");
		String keyAccountManager = URLDecoder.decode(object.get("keyAccountManager").toString(),"utf-8");
		rentInfo.setProjGuid(projGuid);
		rentInfo.setUnitNo(unitNo);
		rentInfo.setSaleStatus(saleStatus);
		rentInfo.setShopStatus(shopStatus);
		rentInfo.setRentStatus(rentStatus);
		rentInfo.setTotalRent(totalRent);
		rentInfo.setDayRent(dayRent);
		rentInfo.setSignUpDate(signUpDate);
		rentInfo.setIntentionCst(intentionCst);
		rentInfo.setInvestmentOfficer(investmentOfficer);
		rentInfo.setKeyAccountManager(keyAccountManager);
		int flag = zsRentService.updateZsRentInfo(rentInfo);
		return flag;
	} 
}
