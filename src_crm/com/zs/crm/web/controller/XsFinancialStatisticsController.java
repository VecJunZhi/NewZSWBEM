package com.zs.crm.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.busi.utils.LogUtil;
import com.zs.common.entity.DataTablesParameters;
import com.zs.common.util.CommonUtil;
import com.zs.common.util.RequestUtil;
import com.zs.crm.entity.XsFinancialStatisticsInfoVo;
import com.zs.crm.entity.XsFinancialStatisticsSearchInfoVo;
import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.crm.service.XsFinancialStatisticsService;
import com.zs.crm.web.vo.XsTeamGroupVo;
import com.zs.rbac.core.RBACSubject;
import com.zs.rbac.entity.User;
import com.zs.rbac.service.XsTeamGroupService;

@Controller
@RequestMapping(value="/wbem/houses/analysis")
public class XsFinancialStatisticsController {
	private Log log = LogUtil.getLog();
	@Autowired
	XsFinancialStatisticsService xsFinancialStatisticsService;
	@Autowired
	XsTeamGroupService xsTeamGroupService;
	
	@RequiresPermissions(value="page:financialManage:financialStatistics:index")
	@RequestMapping("/financialStatistics")
	public String financialStatistics(Model model) {
		log.info("financialStatistics");
		//List<XsTeamGroupEntity> projectList = xsTeamGroupService.getProjectItem();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		XsTeamGroupVo searchInfo = new XsTeamGroupVo();
		searchInfo.setGroupType("xs");
		searchInfo.setUserLevelId("2");
		searchInfo.setProperty("xs_userGuid");
		searchInfo.setProjectId(projGuid);
		List<XsTeamGroupEntity> zygwList = xsTeamGroupService.getUserFromTeamGroup(searchInfo);
		//model.addAttribute("projectList", projectList);
		model.addAttribute("zygwList", zygwList);
		return "/wbem/houses/analysis/financialStatistics";
	}

	@RequiresPermissions(value="page:financialManage:financialStatistics:index")
	@RequestMapping("/loadStatisticsList")
	@ResponseBody
	public Map<String,Object> loadStatisticsList(HttpServletRequest request,/*String projGuid,*/String userGuid,String telOrName,String type) throws UnsupportedEncodingException {
		DataTablesParameters para= RequestUtil.getDTPara(request);
		List<Map<String,String>> financialList = new ArrayList<Map<String,String>>();
		List<XsFinancialStatisticsInfoVo> financialInfoList = new ArrayList<XsFinancialStatisticsInfoVo>();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		int totalNum = 0;
		XsFinancialStatisticsSearchInfoVo searchInfo = new XsFinancialStatisticsSearchInfoVo();
		searchInfo.setProjGuid(projGuid);
		searchInfo.setUserGuid(userGuid);
		if(telOrName != null && !telOrName.equals("")) {
			telOrName = URLDecoder.decode(telOrName, "utf-8"); 
			if(telOrName.matches("[0-9]+")){
				searchInfo.setMobileTel(telOrName);
			}else{
				searchInfo.setCstName(telOrName);
			}
		}
		if(type.equals("1,2")){
			type = "";
		}
		searchInfo.setType(type);
		totalNum = xsFinancialStatisticsService.getFinancialListInfo(searchInfo).size();
		searchInfo.setStartIndex(para.getStart());
		searchInfo.setLength(para.getLength());
		financialInfoList = xsFinancialStatisticsService.getFinancialListInfo(searchInfo);
		int i = para.getStart()+1;
		DecimalFormat df = new DecimalFormat("###.00"); 
		for(XsFinancialStatisticsInfoVo financial:financialInfoList){
			Map<String,String> financialInfo = new HashMap<String,String>();
			financialInfo.put("index", "<label class='checkbox inline index'> <input type='checkbox' name='radios' />"+i+"</label>");
			financialInfo.put("type", financial.getXsRoom().getHuXing().equals("车位")?"车位":"住宅");
			financialInfo.put("cstName", financial.getXsTrade().getCstAllName());
			financialInfo.put("cardID", financial.getXsCst().getCardId());
			financialInfo.put("mobileTel", financial.getXsCst().getMobileTel());
			//financialInfo.put("roomInfo", financial.getXsRoom().getRoomInfo()==null||financial.getXsRoom().getRoomInfo().equals("")?"":financial.getXsRoom().getRoomInfo().substring(16));2016.9.17
			financialInfo.put("roomInfo", financial.getXsRoom().getRoomCode()+"-"+financial.getXsRoom().getUnitNo()+"-"+financial.getXsRoom().getRoom());
			financialInfo.put("bldArea", financial.getXsRoom().getBldArea()==null||financial.getXsRoom().getBldArea().equals("")?"":df.format(Double.parseDouble(financial.getXsRoom().getBldArea())));
			financialInfo.put("totalPrice", financial.getXsRoom().getTotal()==null||financial.getXsRoom().getTotal().equals("")?"":CommonUtil.formatTosepara(Double.parseDouble(financial.getXsRoom().getTotal())));
			financialInfo.put("depositAmount", financial.getXsOther().getDepositAmount()==null||financial.getXsOther().getDepositAmount().equals("")?"":CommonUtil.formatTosepara(Double.parseDouble(financial.getXsOther().getDepositAmount())));
			financialInfo.put("subscribeDate", financial.getXsOther().getSubscribeDate()==null||financial.getXsOther().getSubscribeDate().equals("")?"":financial.getXsOther().getSubscribeDate().substring(0, 10));
			//financialInfo.put("paymentType", financial.getXsOther().getPaymentType()==null||financial.getXsOther().getPaymentType().equals("")?"":financial.getXsOther().getPaymentType().substring(0, 2));
			financialInfo.put("paymentType", financial.getXsOther().getPaymentType());
			financialInfo.put("paymentAmount", financial.getXsOther().getPaymentAmount()==null||financial.getXsOther().getPaymentAmount().equals("")?"":CommonUtil.formatTosepara(Double.parseDouble(financial.getXsOther().getPaymentAmount())));
         	financialInfo.put("paymentDate", financial.getXsOther().getPaymentDate()==null||financial.getXsOther().getPaymentDate().equals("")?"":financial.getXsOther().getPaymentDate().substring(0, 10));
         	financialInfo.put("signUpDate", financial.getXsOther().getSignUpDate()==null||financial.getXsOther().getSignUpDate().equals("")?"":financial.getXsOther().getSignUpDate().substring(0, 10));
         	financialInfo.put("recordDate", financial.getXsOther().getRecordDate()==null||financial.getXsOther().getRecordDate().equals("")?"":financial.getXsOther().getRecordDate().substring(0, 10));
         	financialInfo.put("marginDate", financial.getXsOther().getMarginDate()==null||financial.getXsOther().getMarginDate().equals("")?"":financial.getXsOther().getMarginDate().substring(0, 10));
         	financialInfo.put("lendingAmount", financial.getXsOther().getLendingAmount()==null||financial.getXsOther().getLendingAmount().equals("")?"":CommonUtil.formatTosepara(Double.parseDouble(financial.getXsOther().getLendingAmount())));
         	financialInfo.put("lendingDate", financial.getXsOther().getLendingDate()==null||financial.getXsOther().getLendingDate().equals("")?"":financial.getXsOther().getLendingDate().substring(0, 10));
         	financialInfo.put("rhDate", financial.getXsOther().getRhDate()==null||financial.getXsOther().getRhDate().equals("")?"":financial.getXsOther().getRhDate().substring(0, 10));
         	financialInfo.put("unRecordDate", financial.getXsOther().getUnRecordDate()==null||financial.getXsOther().getUnRecordDate().equals("")?"":financial.getXsOther().getUnRecordDate().substring(0, 10));
         	financialInfo.put("userName", financial.getXsOther().getUserName());
         	financialInfo.put("operate", financial.getXsTrade().getTradeGuid());
         	
			financialList.add(financialInfo);
			i++;
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("draw", para.getDraw());
		resultMap.put("recordsTotal", totalNum);
		resultMap.put("recordsFiltered", totalNum);
		resultMap.put("data", financialList);
		return resultMap;
	}
}
