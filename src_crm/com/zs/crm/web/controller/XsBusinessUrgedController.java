package com.zs.crm.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
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

import com.zs.common.entity.DataTablesParameters;
import com.zs.common.util.CommonUtil;
import com.zs.common.util.RequestUtil;
import com.zs.crm.entity.XsBusinessUrgedInfoVo;
import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.crm.entity.tableStructure.XsFeeTableEntity;
import com.zs.crm.entity.tableStructure.XsTradeGjjlTableEntity;
import com.zs.crm.service.XsBusinessUrgedService;
import com.zs.rbac.service.XsTeamGroupService;
import com.zs.crm.web.vo.XsTeamGroupVo;
import com.zs.rbac.core.RBACSubject;
import com.zs.rbac.entity.User;
import com.zs.rbac.utils.RbacUtils;


@Controller
public class XsBusinessUrgedController {
	private Log log=LogFactory.getLog(XsCustomerController.class);
	//private static final String PROJGUID = "8FFF2136-61EA-E411-BAAF-FCAA145C42F2";//�����԰��Ŀid
	@Autowired
	XsBusinessUrgedService xsBusinessUrgedService;
	@Autowired
	XsTeamGroupService xsTeamGroupService;
	/**
	 * �ֻ��˽���߰����ҳ
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:backlog:businessUrged")
	@RequestMapping(value="/mbem/mcrm/house/customer/businessUrged")
	public String businessUrged(Model model) {
		int unPaymentCount = 0;
		int unSignUpCount = 0;
		int unLendingCount = 0;
		int toUrgedCount = 0;
		User user = (User)RbacUtils.subject().getSession().getAttribute("user");
		String userGuid = user.getExtInfo("xs_userGuid");
		String projGuid = user.getExtInfo("projGuid");
		unPaymentCount = xsBusinessUrgedService.getUnPaymentCstList(projGuid,userGuid).size();
		unSignUpCount = xsBusinessUrgedService.getUnSignUpCstList(projGuid, userGuid).size();
		unLendingCount = xsBusinessUrgedService.getUnLendingCstList(projGuid, userGuid).size();
		toUrgedCount = xsBusinessUrgedService.getToUrgedCstList(projGuid, userGuid).size();
		
		model.addAttribute("unPaymentCount", unPaymentCount);
		model.addAttribute("unSignUpCount", unSignUpCount);
		model.addAttribute("unLendingCount", unLendingCount);
		model.addAttribute("toUrgedCount", toUrgedCount);
		return "/mbem/mcrm/house/customer/businessUrged";   
	}
	/**
	 * �ֻ��˷�ҳ���ش߰�ҳ��ͻ��б�
	 * @param type
	 * @param page
	 * @param pageLen
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:backlog:businessUrged")
	@ResponseBody
	@RequestMapping(value="/mbem/mcrm/house/customer/loadXsBusinessUrgedByType")
	public List<XsBusinessUrgedInfoVo> loadXsBusinessUrgedByType(String type,int page,int pageLen,Model model){
		List<XsBusinessUrgedInfoVo> cstList = new ArrayList<XsBusinessUrgedInfoVo>();
		User user = (User)RbacUtils.subject().getSession().getAttribute("user");
		String userGuid = user.getExtInfo("xs_userGuid");
		String projGuid = user.getExtInfo("projGuid");
		log.info("loadType:"+type+" page:"+page+" pageLen:"+pageLen);
		switch(type){
			case "unPayment":
				cstList = xsBusinessUrgedService.getUnPaymentCstList(projGuid, userGuid, (page-1)*pageLen, pageLen);
				break;
			case "unSignUp":
				cstList = xsBusinessUrgedService.getUnSignUpCstList(projGuid, userGuid, (page-1)*pageLen, pageLen);
				break;
			case "unLending":
				cstList = xsBusinessUrgedService.getUnLendingCstList(projGuid, userGuid, (page-1)*pageLen, pageLen);
				break;
			case "toUrged":
				cstList = xsBusinessUrgedService.getToUrgedCstList(projGuid, userGuid);//��ʱ����ҳ,�ֱ�����ֿͻ���ѡ���´δ߰������ǵ����
				break;
		}
		model.addAttribute("type", type);
		return cstList;
	}
	/**
	 * �ֻ��˲鿴�ͻ����飨����������Ϣ�ʹ߰���Ϣ��
	 * @param type
	 * @param cstGuid
	 * @param roomGuid
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:backlog:urgedDetail")
	@RequestMapping(value="/mbem/mcrm/house/customer/urgedDetail")
	public String urgedDetail(@RequestParam(value="type",required=true)String type,@RequestParam(value="tradeGuid",required=true)String tradeGuid, Model model) {
		XsBusinessUrgedInfoVo tradeInfo = new XsBusinessUrgedInfoVo();
		List<XsBusinessUrgedInfoVo> urgedInfoList = new ArrayList<XsBusinessUrgedInfoVo>();
		List<XsFeeTableEntity> feeInfoList = new ArrayList<XsFeeTableEntity>();
		List<XsFeeTableEntity> qkInfoList = new ArrayList<XsFeeTableEntity>();
		List<XsFeeTableEntity> skInfoList = new ArrayList<XsFeeTableEntity>();
		double qkTotal = 0;
		double skTotal = 0;
		String qkTotalFm = "";
		String skTotalFm = "";
		log.info("type:"+type+" tradeGuid:"+tradeGuid);
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		switch(type){
			case "unPayment":
				tradeInfo =  xsBusinessUrgedService.getUnPaymentTradeInfo(projGuid, tradeGuid);
				feeInfoList = xsBusinessUrgedService.getFeeInfoByTradeGuid(tradeGuid);
				log.info("feeInfoSize:"+feeInfoList.size());
				DecimalFormat df = new DecimalFormat("#,###.00"); 
				for(XsFeeTableEntity feeInfo:feeInfoList) {
					log.info(""+feeInfo.getRmbAmount()+"   "+feeInfo.getRmbYe());
					double rmbAmount = Double.parseDouble(feeInfo.getRmbAmount());
					if(feeInfo.getRmbYe().equals("0.0000")) {
						skTotal += rmbAmount;
						feeInfo.setRmbAmount(df.format(rmbAmount));
						skInfoList.add(feeInfo);
					}else {
						qkTotal += rmbAmount;
						feeInfo.setRmbAmount(df.format(rmbAmount));
						qkInfoList.add(feeInfo);
					}
				}
				skTotalFm = df.format(skTotal);
				qkTotalFm = df.format(qkTotal);
				log.info("qkTotal:"+df.format(qkTotal)+"  skTotal:"+df.format(skTotal));
				break;
			case "unSignUp":
				tradeInfo =  xsBusinessUrgedService.getUnSignUpTradeInfo(projGuid, tradeGuid);
				break;
			case "unLending":   
				tradeInfo =  xsBusinessUrgedService.getUnLendingTradeInfo(projGuid, tradeGuid);
				break;
			case "toUrged":
				//��ѯ������Ϣ(�÷�֧����Ҫ�ˣ����߰�������������������е�һ���ֿͻ�)
				break;
		}
		log.info("tradeInfo:"+tradeInfo.getXsCst().getCstName());
		urgedInfoList = xsBusinessUrgedService.getCstUrgedInfo(projGuid, tradeGuid,"createDate","desc","");//��ѯ�߰��¼
		log.info("urgetInfoSize:"+urgedInfoList.size());
		for(XsBusinessUrgedInfoVo urgedInfo:urgedInfoList) {
			if(urgedInfo.getXsUser().getUsername()==null || "".equals(urgedInfo.getXsUser().getUsername())){
				urgedInfo.getXsUser().setUsername("ϵͳ����Ա");
			}
		}
		model.addAttribute("tradeInfo", tradeInfo);//������Ϣ
		model.addAttribute("urgedInfoList", urgedInfoList);//�߰��¼�б�
		model.addAttribute("tradeGuid", tradeGuid);
		model.addAttribute("type", type);
		model.addAttribute("skTotal", skTotalFm);//ʵ�տ���
		model.addAttribute("qkTotal", qkTotalFm);//Ƿ������
		model.addAttribute("skInfoList", skInfoList);
		model.addAttribute("qkInfoList", qkInfoList);
		return "/mbem/mcrm/house/customer/urgedDetail";
	}
	/**
	 * �ֻ��˱�ǿͻ�Ϊ���ѿͻ�����ͨ�ͻ�
	 * @param tradeGuid
	 * @param level
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:backlog:businessUrged")
	@RequestMapping("/mbem/mcrm/house/customer/setCstUrgedStatusInfo")
	@ResponseBody
	public int setCstUrgedStatusInfo(String tradeGuid,String level,String type) {
		if(level.equals("0")) {
			log.info("����Ϊ��ͨ�ͻ���");
		}
		if(level.equals("1")) {
			log.info("����Ϊ���ѿͻ���");
		}
		int flag = xsBusinessUrgedService.insertOrUpdateUrgedStatusInfo(tradeGuid,level,type);
		return flag;
	}
	/**
	 * �ֻ��˽��������߰�ҳ
	 * @param tradeGuid
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:backlog:urgedFollow")
	@RequestMapping(value="/mbem/mcrm/house/customer/urgedFollow")
	public String urgedPage(@RequestParam(value="tradeGuid",required=true)String tradeGuid,@RequestParam(value="type",required=true)String type,Model model) {
		model.addAttribute("tradeGuid", tradeGuid);
		model.addAttribute("type", type);
		return "/mbem/mcrm/house/customer/urgedFollow";
	}
	/**
	 * �ֻ��������߰��¼
	 * @param urgedInfo
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:backlog:urgedFollow")
	@RequestMapping(value="/mbem/mcrm/house/customer/insertCstUrgedInfo")
	public String insertCstUrgedInfo(@RequestParam(value="type",required=true)String type,XsTradeGjjlTableEntity urgedInfo,Model model) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String urgedDate = df.format(cal.getTime());
		User user = (User)RbacUtils.subject().getSession().getAttribute("user");
		String userGuid = user.getExtInfo("xs_userGuid");
		urgedInfo.setUrgedDate(urgedDate);
		urgedInfo.setUrgedUserGuid(userGuid);
		urgedInfo.setUrgedType("1");//tradeGuid���߰����ݡ��´δ߰�ʱ��ͨ��form�ύ����,�ֻ��˴߰���������Ϊ1
		switch(type){//���Ӵ߰�׶α�ʶ����������δ���δǩԼ����δ�ſ�׶εĴ߰��¼ 
        case "unPayment": 
           urgedInfo.setUrgedStage("1"); 
           break; 
        case "unSignUp": 
           urgedInfo.setUrgedStage("2"); 
           break; 
        case "unLending": 
           urgedInfo.setUrgedStage("3"); 
           break; 
         
     }
		xsBusinessUrgedService.insertCstUrgedInfo(urgedInfo);
		model.addAttribute("tradeGuid", urgedInfo.getTradeGuid());
		model.addAttribute("type", type);
		return "redirect:/mbem/mcrm/house/customer/urgedDetail.action";
	}
	/**
	 * PC�˽���δ����ҳ��
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:urgedManage:unPayment:index")
	@RequestMapping(value="/wbem/houses/customer/unPayment")
	public String unPayment(Model model){
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
		return "/wbem/houses/customer/unPayment";
	}
	/**
	 * PC�˷�ҳ����δ����Ŀͻ��б�
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequiresPermissions(value="page:urgedManage:unPayment:index")
	@ResponseBody
	@RequestMapping(value="/wbem/houses/customer/loadUnPaymentCstList")
	public Map<String,Object> loadUnPaymentCstList(HttpServletRequest request,/*String projGuid,*/String userGuid,String telOrName,String urgedType) throws UnsupportedEncodingException {
		List<XsBusinessUrgedInfoVo> cstList = new ArrayList<XsBusinessUrgedInfoVo>();
		List<Map<String,String>> cstInfoList = new ArrayList<Map<String,String>>();
		XsBusinessUrgedInfoVo searchInfo = new XsBusinessUrgedInfoVo();
		DataTablesParameters para= RequestUtil.getDTPara(request);
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		int startIndex = para.getStart();
		int length = para.getLength();
		int totalNum = 0;
		if(telOrName !=null && !"".equals(telOrName)){//���ݿͻ����ƺ͵绰ģ����ѯ
			telOrName = URLDecoder.decode(telOrName, "utf-8");
			boolean isNum = telOrName.matches("[0-9]+");
			if(isNum){
				searchInfo.getXsCst().setMobileTel(telOrName);
			}else{
				searchInfo.getXsCst().setCstName(telOrName);
			}
		}
		para.setColumnArray(new String[]{"","","bldArea","","","","qsDate","endDate","postponeDate","overDays","qkTotal","","content",""});//����Ҫ����ĸ��ֶ��г���������Ӧ���ǵڼ���
		log.info(para.getOrderColumn()+":sortName");
		log.info(para.getOrderDir()+":sortDir");
		searchInfo.getXsOpp().setProjGuid(projGuid);
		searchInfo.getXsOpp().setUserGuid(userGuid);
		log.info("urgedType:"+urgedType);
		String level = "";
		switch(urgedType){
			case "1":
				level = "\'1\'";
				break;
			case "2":
				level = "\'0\'";
				break;
			case "1,2":
				level = "\'0\',\'1\'";
				break;
		}
		searchInfo.getXsExt().setLevel(level);
		totalNum = xsBusinessUrgedService.getUnPaymentCstList(searchInfo).size();
		
		searchInfo.getXsSearch().setStartIndex(startIndex);
		searchInfo.getXsSearch().setLength(length);
		searchInfo.getXsSearch().setSortName(para.getOrderColumn());
		searchInfo.getXsSearch().setSortDir(para.getOrderDir());
		cstList = xsBusinessUrgedService.getUnPaymentCstList(searchInfo);
	
		log.info("startIndex:"+startIndex+" length:"+length);
		log.info("totalNum:"+totalNum+" cstSize:"+cstList.size());
		int i=para.getStart()+1;
		for(XsBusinessUrgedInfoVo cst:cstList) {
			Map<String,String> cstInfo = new HashMap<String,String>();
			cstInfo.put("index","<label class='checkbox inline index'> <input type='checkbox' name='radios' />"+i+"</label>");
			cstInfo.put("roomInfo",cst.getXsRoom().getRoomInfo()==null?"":cst.getXsRoom().getRoomInfo().substring(16));
			cstInfo.put("bldArea", cst.getXsRoom().getBldArea());
			cstInfo.put("cstName",cst.getXsCst().getCstName());
			String tel="";
			if(cst.getXsCst().getMobileTel()!=null && !cst.getXsCst().getMobileTel().equals(""))
				tel += "(M)"+cst.getXsCst().getMobileTel();
			if(cst.getXsCst().getHomeTel()!=null && !cst.getXsCst().getHomeTel().equals(""))
				tel += " (H)"+cst.getXsCst().getHomeTel();
			if(cst.getXsCst().getOfficeTel()!=null && !cst.getXsCst().getOfficeTel().equals(""))
				tel += " (O)"+cst.getXsCst().getOfficeTel();
			if(cst.getXsCst().getFax()!=null && !cst.getXsCst().getFax().equals(""))
				tel += " (F)"+cst.getXsCst().getFax();
			//modify by jixiaohang
				String qkTotalString = cst.getXsOther().getQkTotal();
				Double qkTotalDouble = Double.parseDouble(qkTotalString);
				String qkTotals=CommonUtil.formatTosepara(qkTotalDouble);
			//modify by jixiaohang
			cstInfo.put("mobileTel",tel);
			cstInfo.put("userName", cst.getXsOrder().getYwy());
         	cstInfo.put("qsDate", cst.getXsOrder().getQsDate());
         	cstInfo.put("endDate", cst.getXsOther().getEndDate()==null?"":cst.getXsOther().getEndDate().substring(0, 11));
         	cstInfo.put("postponeDate", cst.getXsExt().getPostponeDate()==null?"":cst.getXsExt().getPostponeDate().substring(0, 11));
         	cstInfo.put("overDays", cst.getXsOther().getOverDays());
         	cstInfo.put("qkTotal", qkTotals);// modify by jixiaohang
         	cstInfo.put("isDifficult", cst.getXsExt().getLevel().equals("0")?"��":"��");
         	cstInfo.put("urgedContent", cst.getXsGjjl().getUrgedDate()==null?"":cst.getXsGjjl().getUrgedDate().substring(0, 11)+"��"+cst.getXsGjjl().getUrgedContent());
         	cstInfo.put("operate", cst.getXsTrade().getTradeGuid());
			i++;
			cstInfoList.add(cstInfo);
		}
		log.info(cstInfoList.size()+"listSize");
		Map<String,Object> jsonResult = new HashMap<String,Object>();
		jsonResult.put("draw", para.getDraw());
		jsonResult.put("recordsTotal", totalNum);
		jsonResult.put("recordsFiltered", totalNum);
		jsonResult.put("data",cstInfoList);
		return jsonResult;
	}
	/**
	 * PC�˽���δǩԼҳ��
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:urgedManage:unSignUp:index")
	@RequestMapping(value="/wbem/houses/customer/unSignUp")
	public String unSignUp(Model model){
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
		return "/wbem/houses/customer/unSignUp";
	}
	/**
	 * PC�˷�ҳ����δǩԼ�Ŀͻ��б�
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequiresPermissions(value="page:urgedManage:unSignUp:index")
	@ResponseBody
	@RequestMapping(value="/wbem/houses/customer/loadUnSignUpCstList")
	public Map<String,Object> loadUnSignUpCstList(HttpServletRequest request,/*String projGuid,*/String userGuid,String telOrName,String urgedType) throws UnsupportedEncodingException {
		List<XsBusinessUrgedInfoVo> cstList = new ArrayList<XsBusinessUrgedInfoVo>();
		List<Map<String,String>> cstInfoList = new ArrayList<Map<String,String>>();
		XsBusinessUrgedInfoVo searchInfo = new XsBusinessUrgedInfoVo();
		DataTablesParameters para= RequestUtil.getDTPara(request);
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		para.setColumnArray(new String[]{"","","bldArea","","","","QStime","endDate","postponeDate","overDays","","content",""});//����Ҫ����ĸ��ֶ��г���������Ӧ���ǵڼ���
		int startIndex = para.getStart();
		int length = para.getLength();
		if(telOrName !=null && !"".equals(telOrName)) {
			telOrName = URLDecoder.decode(telOrName, "utf-8");
			boolean isNum = telOrName.matches("[0-9]+");
			if(isNum) {
				searchInfo.getXsCst().setMobileTel(telOrName);
			}else {
				searchInfo.getXsCst().setCstName(telOrName);
			}
		}
		searchInfo.getXsOpp().setProjGuid(projGuid);
		searchInfo.getXsOpp().setUserGuid(userGuid);
		String level = "";
		switch(urgedType){
			case "1":
				level = "\'1\'";
				break;
			case "2":
				level = "\'0\'";
				break;
			case "1,2":
				level = "\'0\',\'1\'";
				break;
		}
		searchInfo.getXsExt().setLevel(level);
		int totalNum = xsBusinessUrgedService.getUnSignUpCstList(searchInfo).size();
		log.info("startIndex:"+startIndex+" length:"+length);
		log.info("totalNum:"+totalNum);
		searchInfo.getXsSearch().setSortName(para.getOrderColumn());
		searchInfo.getXsSearch().setSortDir(para.getOrderDir());
		searchInfo.getXsSearch().setStartIndex(startIndex);
		searchInfo.getXsSearch().setLength(length);
		cstList = xsBusinessUrgedService.getUnSignUpCstList(searchInfo);
		log.info("cstSize:"+cstList.size());
		int i=para.getStart()+1;
		for(XsBusinessUrgedInfoVo cst:cstList) {
			Map<String,String> cstInfo = new HashMap<String,String>();
			cstInfo.put("index","<label class='checkbox inline index'> <input type='checkbox' name='radios' />"+i+"</label>");
			cstInfo.put("roomInfo",cst.getXsRoom().getRoomInfo()==null?"":cst.getXsRoom().getRoomInfo().substring(16));
			cstInfo.put("bldArea", cst.getXsRoom().getBldArea());
			cstInfo.put("cstName",cst.getXsCst().getCstName());
			String tel="";
			if(cst.getXsCst().getMobileTel()!=null && !cst.getXsCst().getMobileTel().equals(""))
				tel += "(M)"+cst.getXsCst().getMobileTel();
			if(cst.getXsCst().getHomeTel()!=null && !cst.getXsCst().getHomeTel().equals(""))
				tel += " (H)"+cst.getXsCst().getHomeTel();
			if(cst.getXsCst().getOfficeTel()!=null && !cst.getXsCst().getOfficeTel().equals(""))
				tel += " (O)"+cst.getXsCst().getOfficeTel();
			if(cst.getXsCst().getFax()!=null && !cst.getXsCst().getFax().equals(""))
				tel += " (F)"+cst.getXsCst().getFax();

         	cstInfo.put("mobileTel",tel);
			cstInfo.put("userName", cst.getXsOrder().getYwy());
         	cstInfo.put("qsDate", cst.getXsOrder().getQsDate());
         	cstInfo.put("endDate", cst.getXsOther().getEndDate()==null?"":cst.getXsOther().getEndDate().substring(0, 11));
         	cstInfo.put("postponeDate", cst.getXsExt().getPostponeDate()==null?"":cst.getXsExt().getPostponeDate().substring(0, 11));
         	cstInfo.put("overDays", cst.getXsOther().getOverDays());
         	//cstInfo.put("qkTotal", cst.getXsOther().getQkTotal());
         	cstInfo.put("isDifficult", cst.getXsExt().getLevel1().equals("0")?"��":"��");
         	cstInfo.put("urgedContent", cst.getXsGjjl().getUrgedDate()==null?"":cst.getXsGjjl().getUrgedDate().substring(0, 11)+"��"+cst.getXsGjjl().getUrgedContent());
         	cstInfo.put("operate", cst.getXsTrade().getTradeGuid());
			i++;
			cstInfoList.add(cstInfo);
		}
		log.info(cstInfoList.size()+"listSize");
		Map<String,Object> jsonResult = new HashMap<String,Object>();
		jsonResult.put("draw", para.getDraw());
		jsonResult.put("recordsTotal", totalNum);
		jsonResult.put("recordsFiltered", totalNum);
		jsonResult.put("data",cstInfoList);
		return jsonResult;
	}
	/**
	 * PC�˽���δ�ſ�ҳ��
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:urgedManage:unLending:index")
	@RequestMapping(value="/wbem/houses/customer/unLending")
	public String unLending(Model model){
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
		return "/wbem/houses/customer/unLending";
	}
	/**
	 * PC�˷�ҳ����δ�ſ�Ŀͻ��б�
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequiresPermissions(value="page:urgedManage:unLending:index")
	@ResponseBody
	@RequestMapping(value="/wbem/houses/customer/loadUnLendingCstList")
	public Map<String,Object> loadUnLendingCstList(HttpServletRequest request,/*String projGuid,*/String userGuid,String telOrName,String urgedType,String lendingStatus,String bank) throws UnsupportedEncodingException {
		List<XsBusinessUrgedInfoVo> cstList = new ArrayList<XsBusinessUrgedInfoVo>();
		List<Map<String,String>> cstInfoList = new ArrayList<Map<String,String>>();
		XsBusinessUrgedInfoVo searchInfo = new XsBusinessUrgedInfoVo();
		DataTablesParameters para= RequestUtil.getDTPara(request);
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		para.setColumnArray(new String[]{"","","bldArea","","","","QStime","postponeDate","overDays","qkTotal","","","content",""});//����Ҫ����ĸ��ֶ��г���������Ӧ���ǵڼ���
		int startIndex = para.getStart();
		int length = para.getLength();
		if(telOrName!=null && !"".equals(telOrName)) {
			telOrName = URLDecoder.decode(telOrName, "utf-8");
			boolean isNum = telOrName.matches("[0-9]+");
			if(isNum) {
				searchInfo.getXsCst().setMobileTel(telOrName);
			}else {
				searchInfo.getXsCst().setCstName(telOrName);
			}
		}
		log.info("projGuid:"+projGuid+"   userGuid:"+userGuid+"   telOrName:"+telOrName+"   urgedType:"+urgedType);
		searchInfo.getXsOpp().setProjGuid(projGuid);
		searchInfo.getXsOpp().setUserGuid(userGuid);
		searchInfo.getXsSearch().setSortName(para.getOrderColumn());
		searchInfo.getXsSearch().setSortDir(para.getOrderDir());
		String level = "";
		switch(urgedType){
			case "1":
				level = "\'1\'";
				break;
			case "2":
				level = "\'0\'";
				break;
			case "1,2":
				level = "\'0\',\'1\'";
				break;
		}
		searchInfo.getXsExt().setLevel(level);
		searchInfo.getXsFee().setRmbYe(lendingStatus);//�ſ�״̬
		searchInfo.getXsOther().setBank(URLDecoder.decode(bank, "utf-8"));//Add 2016-7-14
		
		int totalNum = xsBusinessUrgedService.getUnLendingCstList(searchInfo).size();
		searchInfo.getXsSearch().setStartIndex(startIndex);
		searchInfo.getXsSearch().setLength(length);
		cstList = xsBusinessUrgedService.getUnLendingCstList(searchInfo);
		log.info("totalNum:"+totalNum+"  cstSize:"+cstList.size());
		int i=para.getStart()+1;
		for(XsBusinessUrgedInfoVo cst:cstList) {
			Map<String,String> cstInfo = new HashMap<String,String>();
			cstInfo.put("index","<label class='checkbox inline index'> <input type='checkbox' name='radios' />"+i+"</label>");
			cstInfo.put("roomInfo",cst.getXsRoom().getRoomInfo()==null?"":cst.getXsRoom().getRoomInfo().substring(16));
			cstInfo.put("cstName",cst.getXsCst().getCstName());
			String tel="";
			if(cst.getXsCst().getMobileTel()!=null && !cst.getXsCst().getMobileTel().equals(""))
				tel += "(M)"+cst.getXsCst().getMobileTel();
			if(cst.getXsCst().getHomeTel()!=null && !cst.getXsCst().getHomeTel().equals(""))
				tel += " (H)"+cst.getXsCst().getHomeTel();
			if(cst.getXsCst().getOfficeTel()!=null && !cst.getXsCst().getOfficeTel().equals(""))
				tel += " (O)"+cst.getXsCst().getOfficeTel();
			if(cst.getXsCst().getFax()!=null && !cst.getXsCst().getFax().equals(""))
				tel += " (F)"+cst.getXsCst().getFax();
			//modify by jixiaohang
			String qkTotalString = cst.getXsOther().getQkTotal();
			Double qkTotalDouble = Double.parseDouble(qkTotalString);
			String qkTotals=CommonUtil.formatTosepara(qkTotalDouble);
			//modify by jixiaohang
			cstInfo.put("mobileTel",tel);	
         	cstInfo.put("qsDate", cst.getXsOrder().getQsDate());
         	cstInfo.put("overDays", cst.getXsOther().getOverDays());
         	cstInfo.put("bldArea", cst.getXsRoom().getBldArea());
         	cstInfo.put("userName", cst.getXsOrder().getYwy());
         	cstInfo.put("postponeDate", cst.getXsExt().getPostponeDate()==null?"":cst.getXsExt().getPostponeDate().substring(0, 11));
         	cstInfo.put("qkTotal", qkTotals); //modify by jixiaohang
         	cstInfo.put("isDifficult", cst.getXsExt().getLevel2().equals("0")?"��":"��");
         	cstInfo.put("bank", cst.getXsOther().getBank());
         	cstInfo.put("urgedContent", cst.getXsGjjl().getUrgedDate()==null?"":cst.getXsGjjl().getUrgedDate().substring(0, 11)+"��"+cst.getXsGjjl().getUrgedContent());
         	cstInfo.put("operate", cst.getXsTrade().getTradeGuid());
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
	/**
	 * PC�˽�������ҳ��
	 * @param tradeGuid
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value={"page:urgedManage:unPayment:cstDelay","page:urgedManage:unLending:cstDelay"},logical=Logical.OR)
	@RequestMapping(value="/wbem/houses/customer/cstDelay")
	public String cstDelay(@RequestParam(value="tradeGuid",required=true)String tradeGuid,String postponeStage,Model model) {
		model.addAttribute("tradeGuid", tradeGuid);
		model.addAttribute("postponeStage", postponeStage);
		log.info(postponeStage);
		return "/wbem/houses/customer/cstDelay";
	}
	/**
	 * PC�����ÿͻ�������Ϣ
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequiresPermissions(value={"page:urgedManage:unPayment:cstDelay","page:urgedManage:unLending:cstDelay"},logical=Logical.OR)
	@RequestMapping("/wbem/houses/customer/setCstDelayInfo")
	@ResponseBody
	public int setCstDelayInfo(String tradeGuid,String postponeDate,String postponeReason,String postponeStage) throws UnsupportedEncodingException{
		String reason = URLDecoder.decode(postponeReason, "utf-8");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String date = df.format(cal.getTime());
		log.info(postponeStage);
		int flag = xsBusinessUrgedService.insertOrUpdateDelayInfo(tradeGuid,postponeDate,date+":"+reason+";",postponeStage);
		return flag;
	} 
	/**
	 * PC�˽��������߰�ҳ
	 * @param tradeGuid
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value={"page:urgedManage:unPayment:cstUrged","page:urgedManage:unSignUp:cstUrged","page:urgedManage:unLending:cstUrged"},logical=Logical.OR)
	@RequestMapping(value="/wbem/houses/customer/cstUrged")
	public String cstUrged(@RequestParam(value="tradeGuid",required=true)String tradeGuid,String urgedStage,Model model) {
		model.addAttribute("tradeGuid", tradeGuid);
		model.addAttribute("urgedStage", urgedStage);
		return "/wbem/houses/customer/cstUrged";
	}
	/**
	 * PC�˲���߰���Ϣ
	 * @param tradeGuid
	 * @param nextUrgedDate
	 * @param urgedContent
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequiresPermissions(value={"page:urgedManage:unPayment:cstUrged","page:urgedManage:unSignUp:cstUrged","page:urgedManage:unLending:cstUrged"},logical=Logical.OR)
	@RequestMapping(value="/wbem/houses/customer/insertCstUrgedInfo")
	@ResponseBody
	public int insertCstUrgedInfoPc(String tradeGuid,/*String nextUrgedDate,*/String urgedContent,String urgedStage) throws UnsupportedEncodingException {
		XsTradeGjjlTableEntity urgedInfo = new  XsTradeGjjlTableEntity();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String urgedDate = df.format(cal.getTime());
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		//User user = (User)RbacUtils.subject().getSession().getAttribute("user");
		String userGuid = user.getExtInfo("xs_userGuid");
		if(userGuid == null || "".equals(userGuid)){//ϵͳ����Ա����ʱuserGuidΪ��
			userGuid = (String)RbacUtils.subject().getSession().getAttribute("username");
		}
		List<XsBusinessUrgedInfoVo> urgedInfoList = new ArrayList<XsBusinessUrgedInfoVo>();
		urgedInfoList = xsBusinessUrgedService.getCstUrgedInfo(projGuid,tradeGuid,"createDate","desc","1");//��ѯ�߰��¼
		urgedInfo.setTradeGuid(tradeGuid);
		urgedInfo.setUrgedContent(URLDecoder.decode(urgedContent,"utf-8"));
		urgedInfo.setNextUrgedDate(urgedInfoList.size()==0?null:urgedInfoList.get(0).getXsGjjl().getNextUrgedDate());
		urgedInfo.setUrgedDate(urgedDate);
		urgedInfo.setUrgedUserGuid(userGuid);
		urgedInfo.setUrgedType("2");//PC�����߰������Ϊ2���ֻ������߰������Ϊ1��PC�����߰�ʱ������ѡ���´δ߰����ڣ������һ�δ߰�����ȡ�´δ߰�����
		urgedInfo.setUrgedStage(urgedStage);//����߰�Ľ׶Σ�1Ϊδ���2ΪδǩԼ �� 3Ϊδ�ſ�
		log.info(urgedStage);
		int flag = xsBusinessUrgedService.insertCstUrgedInfo(urgedInfo);
		return flag;
	}
	/**
	 * PC�˽���鿴�߰��¼ҳ
	 * @param tradeGuid
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value={"page:urgedManage:unPayment:cstView","page:urgedManage:unSignUp:cstView","page:urgedManage:unLending:cstView"},logical=Logical.OR)
	@RequestMapping(value="/wbem/houses/customer/cstView")
	public String cstView(@RequestParam(value="tradeGuid",required=true)String tradeGuid,Model model) {
		model.addAttribute("tradeGuid", tradeGuid);
		return "/wbem/houses/customer/cstView";
	} 
	/**
	 * PC�˷�ҳ���ش߰��¼
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequiresPermissions(value={"page:urgedManage:unPayment:cstView","page:urgedManage:unSignUp:cstView","page:urgedManage:unLending:cstView"},logical=Logical.OR)
	@ResponseBody
	@RequestMapping(value="/wbem/houses/customer/loadCstUrgedList")
	public Map<String,Object> loadUnLendingCstList(HttpServletRequest request,String tradeGuid,String urgedStage) throws UnsupportedEncodingException {
		List<XsBusinessUrgedInfoVo> cstList = new ArrayList<XsBusinessUrgedInfoVo>();
		List<Map<String,String>> cstInfoList = new ArrayList<Map<String,String>>();
		log.info("tradeGuid"+tradeGuid);
		DataTablesParameters para= RequestUtil.getDTPara(request);
		para.setColumnArray(new String[]{"","createDate","","nextDate",""});//����Ҫ����ĸ��ֶ��г���������Ӧ���ǵڼ���
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		int startIndex = para.getStart();
		int length = para.getLength();
		int totalNum = xsBusinessUrgedService.getCstUrgedInfo(projGuid, tradeGuid,urgedStage).size();
		
		cstList = xsBusinessUrgedService.getCstUrgedInfo(projGuid, tradeGuid,startIndex,length,para.getOrderColumn(),para.getOrderDir(),urgedStage);
		log.info("totalNum:"+totalNum+"  cstSize:"+cstList.size());
		int i=para.getStart()+1;
		for(XsBusinessUrgedInfoVo cst:cstList) {
			Map<String,String> cstInfo = new HashMap<String,String>();
			cstInfo.put("index","<label class='checkbox inline index'> <input type='checkbox' name='radios' />"+i+"</label>");
			cstInfo.put("urgedDate",cst.getXsGjjl().getUrgedDate()==null?"":cst.getXsGjjl().getUrgedDate().substring(0, 11));
			cstInfo.put("urgedContent",cst.getXsGjjl().getUrgedContent());	
         	cstInfo.put("nextUrgedDate", cst.getXsGjjl().getNextUrgedDate()==null?"":cst.getXsGjjl().getNextUrgedDate().substring(0, 11));
         	cstInfo.put("userName", cst.getXsUser().getUsername());
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
