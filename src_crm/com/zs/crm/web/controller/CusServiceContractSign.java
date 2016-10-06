package com.zs.crm.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.busi.utils.LogUtil;
import com.zs.common.entity.DataTablesParameters;
import com.zs.common.util.CommonUtil;
import com.zs.common.util.DateUtil;
import com.zs.common.util.RequestUtil;
import com.zs.crm.entity.CstServiceContractSignVo;
import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.crm.service.CusServiceContractSignService;
import com.zs.crm.web.vo.XsTeamGroupVo;
import com.zs.rbac.service.XsTeamGroupService;

@Controller
public class CusServiceContractSign {
	private Log log=LogUtil.getLog();
	@Autowired
	CusServiceContractSignService contractSignService;
	@Autowired
	XsTeamGroupService xsTeamGroupService;
	/**
	 * PC端进入签订合同页面
	 * @param model
	 * @return
	 */
	//@RequiresPermissions(value="page:urgedManage:unLending:index")
	@RequestMapping(value="/wbem/houses/customer/cstServiceContractSign")
	public String cstServiceContractSign(Model model){
		List<XsTeamGroupEntity> projectList = xsTeamGroupService.getProjectItem();
		model.addAttribute("projectList", projectList);
		XsTeamGroupVo searchInfo = new XsTeamGroupVo();
		searchInfo.setGroupType("xs");
		searchInfo.setUserLevelId("2");
		searchInfo.setProperty("xs_userGuid");
		List<XsTeamGroupEntity> zygwList = xsTeamGroupService.getUserFromTeamGroup(searchInfo);
		model.addAttribute("zygwList", zygwList);
		log.info("dddd");
		return "/wbem/houses/customer/cstServiceContractSign";
	}
	/**
	 * PC端分页加载未放款的客户列表
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	//@RequiresPermissions(value="page:urgedManage:unLending:index")
	@ResponseBody
	@RequestMapping(value="/wbem/houses/customer/loadCstServiceContractSign")
	public Map<String,Object> loadCstServiceContractSign(HttpServletRequest request,String projGuid,String zygw,String telOrName,String payWay) throws UnsupportedEncodingException {
		List<CstServiceContractSignVo> cstList = new ArrayList<CstServiceContractSignVo>();
		List<Map<String,String>> cstInfoList = new ArrayList<Map<String,String>>();
		DataTablesParameters para= RequestUtil.getDTPara(request);
		//para.setColumnArray(new String[]{"","","bldArea","","","","QStime","postponeDate","overDays","qkTotal","","content",""});//将需要排序的各字段列出来，并对应好是第几列
		int startIndex = para.getStart();
		int length = para.getLength();
		log.info(startIndex +" "+length);
		
		String cstName="";
		String cstTel="";
		if(telOrName!=null && !"".equals(telOrName)) {
			telOrName = URLDecoder.decode(telOrName, "utf-8");
			boolean isNum = telOrName.matches("[0-9]+");
			if(isNum) {
				cstTel=telOrName;
			}else {
				cstName=telOrName;
			}
		}
		zygw=URLDecoder.decode(zygw, "utf-8");
		log.info("projGuid:"+projGuid+"   user:"+zygw+"   telOrName:"+telOrName+"   payWay:"+payWay);
		switch(payWay){
			case "1":
				payWay = "公积金";
				break;
			case "2":
				payWay = "贷|银行";
				break;
			case "3":
				payWay = "全";
				break;
			case "1,2":
				payWay = "公积金|贷|银行";
				break;
			case "1,3":
				payWay = "公积金|全";
				break;
			case "2,3":
				payWay = "贷|银行|全";
				break;
			case "1,2,3":
				payWay = "公积金|贷|全|银行";
				break;
		}
		List<CstServiceContractSignVo> list = contractSignService.getCusServiceContractSignList(projGuid, zygw, cstName, cstTel, payWay, "", "");
		int totalNum=list.size();
		cstList = contractSignService.getCusServiceContractSignList(projGuid, zygw, cstName, cstTel, payWay, Integer.toString(startIndex), Integer.toString(length));
		log.info("totalNum:"+totalNum+"  cstSize:"+cstList.size());
		int i=para.getStart()+1;
		for(CstServiceContractSignVo cst:cstList) {
			Map<String,String> cstInfo = new HashMap<String,String>();
			//cstInfo.put("index","<label class='checkbox inline index'> <input type='checkbox' name='radios' />"+i+"</label>");
			cstInfo.put("index",i+"");
			cstInfo.put("cstName",cst.getCstName());
			cstInfo.put("mobileTel",cst.getMobileTel());
			//modify by jixiaohang
				String contractSignDates = cst.getContractSignDate();
				String contractSignDate = DateUtil.dateToStringSecond(contractSignDates);//签订日期
				String unitPrices = cst.getUnitPrice();
				Double unitPricesDouble = Double.parseDouble(unitPrices);
				String unitPrice = CommonUtil.formatTosepara(unitPricesDouble);//单价
				String tradeAmounts = cst.getTradeAmount();
				Double tradeAmountsDouble = Double.parseDouble(tradeAmounts);
				String tradeAmount = CommonUtil.formatTosepara(tradeAmountsDouble);//成交金额
				String initPayments = cst.getInitPayment();
				Double initPaymentsDouble = Double.parseDouble(initPayments);
				String initPayment = CommonUtil.formatTosepara(initPaymentsDouble);//首付款
				String loanAmounts = cst.getLoanAmount();
				Double loanAmountsDouble = Double.parseDouble(loanAmounts);
				String loanAmount = CommonUtil.formatTosepara(loanAmountsDouble);//贷款额
			//modify by jixiaohang
			cstInfo.put("zygw",cst.getZygw());	
         	cstInfo.put("roomInfo", cst.getRoomInfo());
         	cstInfo.put("area", cst.getArea());
         	cstInfo.put("unitPrice", unitPrice);
         	cstInfo.put("tradeAmount", tradeAmount);
         	cstInfo.put("initPayment", initPayment);
         	cstInfo.put("loanAmount", loanAmount); 
         	cstInfo.put("loanYear", cst.getLoanYear());
         	cstInfo.put("contractNum", cst.getContractNum());
         	cstInfo.put("contractSignDate",  contractSignDate);
         	cstInfo.put("paymentWay", cst.getPaymentWay());
			i++;
			cstInfoList.add(cstInfo);
		}
		Map<String,Object> jsonResult = new HashMap<String,Object>();
		jsonResult.put("draw", para.getDraw());
		jsonResult.put("recordsTotal", totalNum);
		jsonResult.put("recordsFiltered", totalNum);
		jsonResult.put("data",cstInfoList);
		return jsonResult;
	}

}
