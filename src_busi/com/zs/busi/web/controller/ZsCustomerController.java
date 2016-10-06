package com.zs.busi.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.busi.dao.ZsInfoDao;
import com.zs.busi.entity.ZsBusiCustomLogTable;
import com.zs.busi.entity.ZsCustomFollowRecordTableVo;
import com.zs.busi.entity.ZsCustomTableVo;
import com.zs.busi.entity.ZsEmployeeTableVo;
import com.zs.busi.entity.ZsInfoVo;
import com.zs.busi.service.ZsBusiCustomAllocateService;
import com.zs.busi.service.ZsInfoService;
import com.zs.busi.service.impl.ZsAddAndUpdateCusOption;
import com.zs.busi.utils.DateFormat;
import com.zs.common.entity.SearchOptionEntity;
import com.zs.common.service.IOptionListService;
import com.zs.common.util.search.TagOption;
import com.zs.rbac.core.RBACSubject;
import com.zs.rbac.entity.User;
import com.zs.rbac.utils.RbacUtils;

@Controller 
@RequestMapping(value="/mbem/mcrm/business")
public class ZsCustomerController {

	Log log=LogFactory.getLog(ZsCustomerController.class);
	
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpSession httpSession;
	@Autowired
	ZsInfoService zsInfoService ;
	@Autowired
	IOptionListService listservice;
	@Autowired
	ZsBusiCustomAllocateService logService;
	@Autowired
	ZsInfoDao zsInfoDao;
	@Autowired 
	ZsBusiCustomAllocateService zsBusiCustomAllocateService;
	
	/**
	 * �����ͻ� ���ͻ��Ļ�����Ϣ������󣬻�õ�ǰ�ͻ���id,
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:zs:addCustom")
	@RequestMapping(value="/insertZsBasicInfoDao")
	public String insertZsBasicInfoDao(ZsInfoVo vo) {
		String tel = vo.getTel();
		log.info("tel"+tel);
		String exist = zsInfoService.checkExistCusByTel(tel);
		if(exist.equals("no")) {
			log.info("���ֻ����ѵǼǹ�");
			return "redirect:/mbem/mcrm/business/customPage.action";
		}
		String homeTel = vo.getHomeTel()==null?"null":(vo.getHomeTel().equals("")?"null":vo.getHomeTel());
		String officeTel = vo.getOfficeTel()==null?"null":(vo.getOfficeTel().equals("")?"null":vo.getOfficeTel().toString());
		vo.setPhoneNo(vo.getTelPhone()+","+homeTel+","+officeTel);//2016-1-12
		
		String currentTime = DateFormat.getCurrentDate(); 
		vo.setLastFollowDate(currentTime);
		vo.setCreateDate(currentTime);
		vo.setIntentionCreateDate(currentTime);
		vo.setIntentionModifyDate(currentTime);
		vo.setFollowDate(currentTime);
		vo.setClasses("0");//���ֵ��ʱû��ʵ�����壬���ݿ���Ʊ�������ֵ������ģ��ֵΪ0��
		vo.setValided("1");//�����ͻ�Ĭ�϶�����Ч�ͻ���ͨ���ͻ��޸����ı�ͻ�����Ч/��Ч
		String followWay = vo.getFollowWay();
		if(followWay.equals("����") || followWay.equals("ȥ��"))
			vo.setCustomStatus("��ѯ");
		else
			vo.setCustomStatus(followWay);
		
		String succ=zsInfoService.insertZsBasicInfoDao(vo);
		return "redirect:/mbem/mcrm/business/customPage.action";
	}
	@RequestMapping(value="/checkTelValidity",method = RequestMethod.POST)
	@ResponseBody
	public String checkTelValidity(@RequestParam(value="tel",required=true) String tel) {
		log.info(tel);
		if(tel==null || tel.equals(""))
			return "YES";
		String exist = zsInfoService.checkExistCusByTel(tel);
		if(exist.equals("no")){
			log.info("�ú����ѵǼǹ�");
			return "NO";
		}
		return "YES";
	}
	/**
	 * ���Ŀͻ�
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:zs:updateCustom")
	@RequestMapping(value="/updateZsBasicInfoDao")
	public String updateZsBasicInfoDao(String oldTel,String oldName,ZsInfoVo vo) {
		log.info(vo.getIntentionArea()+" valid "+vo.getValided());
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		String valid=vo.getValided();
		if(valid !=null && "��Ч".equals(valid)){
			valid="1";
		}else if(valid !=null && "��Ч".equals(valid)){
			valid="0";
		}
		log.info("oldTel:"+oldTel+" oldName:"+oldName);
		log.info(vo.getTel());
		String homeTel = vo.getHomeTel()==null?"null":(vo.getHomeTel().equals("")?"null":vo.getHomeTel());
		String officeTel = vo.getOfficeTel()==null?"null":(vo.getOfficeTel().equals("")?"null":vo.getOfficeTel().toString());
		vo.setPhoneNo(vo.getTelPhone()+","+homeTel+","+officeTel);//2016-1-12
		ZsInfoVo bean= new ZsInfoVo(vo.getCusName(), vo.getCusNo(), vo.getCompSymbol(), vo.getLegalPerson(), vo.getAddr(), vo.getAddrArea(), vo.getAddrRoad(), vo.getLegalPerson(), vo.getTel(), 
				vo.getFaxNo(), vo.getZipCode(), vo.getAccountBank(), vo.getAccountNo(), vo.getSex(), vo.getBirthday(), vo.getEmail(), vo.getHomePage(), vo.getResidenceAddr(), vo.getIdName(), vo.getIdNo(),
				vo.getWorkComp(), vo.getAppendInfo(), vo.getIdentitys(), vo.getNationality(), vo.getClasses(), vo.getLevelId(), vo.getCusDesp(), vo.getAge(), vo.getCusAbb(), vo.getPhoneNo(),
				vo.getMonthIncome(), vo.getYearIncome(), vo.getFirstVisWay(), vo.getAccountCode(), vo.getAccountName(), vo.getVipFee(), vo.getBillNo(), vo.getCardNo(), vo.getFollowWay(), 
				vo.getNextFollowDate(), vo.getIntentionType(), vo.getHouseUse(), vo.getFactor(), vo.getWorkType(), vo.getVisitTimes(), vo.getFamilyInfo(), vo.getInvestmentInfo(), vo.getLabel(),
				vo.getIntentionArea(), vo.getBuyTimes(), vo.getCompetitor(), vo.getIntentionPrice(), vo.getIntentionProduct(), vo.getIntentionRoom(), vo.getCustomStatus(), vo.getPurposeItem(),
				vo.getFollowPersionId(), vo.getFollowDate(), vo.getFollowTime(), vo.getFollowInfo(), vo.getFollowWayEach(), vo.getDetpId(), vo.getDetpNo(), vo.getDeptName(), vo.getNote(),
				vo.getManager(), vo.getEmployeeId(), vo.getEmployeeNo(), vo.getEmployeeName(),vo.getDuty(), vo.getHomeAddress(), vo.getBpNo(), vo.getEducation(), vo.getSchool(), vo.getMajor(),
				vo.getNativePlace(), vo.getEntryDate(), vo.getLeaveDate(), vo.getPosition(), vo.getCusNameOrTel());
		bean.setClasses("0");//���ֵ��ʱû��ʵ�����壬���ݿ���Ʊ�������ֵ������ģ��ֵΪ0��
		bean.setValided(valid);//�������ֵ���жϿͻ��Ƿ���Ч�������Ч��ֵΪ0����ЧֵΪ1
		zsInfoService.updateZsBasicInfoDao(bean);
		
		
		if(!oldTel.equals(vo.getTel()) || !oldName.equals(vo.getCusName())) {
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String modifyDate = fm.format(new Date());
			String followPerson = httpSession.getAttribute("username").toString();
			String cusId=(String) httpSession.getAttribute("currentCustomID");
			ZsBusiCustomLogTable logInfo = new ZsBusiCustomLogTable();
			if(!oldTel.equals(vo.getTel()) && !oldName.equals(vo.getCusName())) {
				logInfo.setContent("��������"+oldName+"�޸�Ϊ"+vo.getCusName()+","+"���ֻ�����"+oldTel+"�޸�Ϊ"+vo.getTel());
				logInfo.setReason("�����ֻ���");
			}else if(!oldTel.equals(vo.getTel())) {
				logInfo.setContent("���ֻ�����"+oldTel+"�޸�Ϊ"+vo.getTel());
				logInfo.setReason("�ֻ���");
			}else if(!oldName.equals(vo.getCusName())) {
				logInfo.setContent("��������"+oldName+"�޸�Ϊ"+vo.getCusName());
				logInfo.setReason("����");
			}
			logInfo.setBelongSys("zs");
			logInfo.setCusId(cusId);
			logInfo.setOperator(followPerson);
			logInfo.setLogType("1");
			logInfo.setOperDate(modifyDate);
			logInfo.setProjectId(projGuid);
			logService.insertZsCusChangeLog(logInfo);//�˴�����һ���޸��ֻ��ŵ��޸���־
		}	
		return "/mbem/mcrm/business/personal";
	}

	/**
	 * ������е��������ѡ��ֵ�������ͻ�ʱ�������޸Ŀͻ�ʱ����
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:zs:addCustom")
	@RequestMapping(value="/addCustom")
	public String getAllDropList(){
		Map<String,Object> optionlistlist = new HashMap<String,Object>();
		List<SearchOptionEntity> optionList = new ArrayList<SearchOptionEntity>();
		List<List<Map<String,String>>> listlist = new ArrayList<List<Map<String,String>>>();
		List<Object> droplist = new ArrayList<Object>();
		SearchOptionEntity option = new SearchOptionEntity();
		List<Map<String,String>> list0 = new ArrayList<Map<String,String>>();
		List<Map<String,String>> list1 = new ArrayList<Map<String,String>>();
		List<Map<String,String>> list2 = new ArrayList<Map<String,String>>();
		option.setModule("addcust");
		optionList = listservice.getOptionListByModule(option);
		log.info("size"+optionList.size());
		ZsAddAndUpdateCusOption hh = new ZsAddAndUpdateCusOption();
		for(SearchOptionEntity option1:optionList) {
			log.info(option1.getModule());
			log.info(option1.getName());
			log.info("isEdit:"+option1.isEdit());
			if(option1.getComment().equals("valided")||option1.getComment().equals("homeTel") || option1.getComment().equals("officeTel"))
				continue;//�����ͻ�ʱ��Ч��Ч����ͥ�绰���칫�绰����ʾ������ͨ��������������ӣ�
			TagOption to = hh.generateHtmlEntity(option1);
			Map<String,String> test = hh.jointHtmlStr(to);
			int index = Integer.parseInt(test.get("group").toString());
			log.info("index"+index);
			if(index == 0)
				list0.add(test);
			else if(index == 1)
				list1.add(test);
			else if(index == 2)
				list2.add(test);
		}
		listlist.add(list0);
		listlist.add(list1);
		listlist.add(list2);
		droplist = zsInfoService.getAllDroplist("add");//������е������ֶ�
		optionlistlist.put("field", listlist);
		optionlistlist.put("option", droplist);
		httpSession.setAttribute("addCustMap", optionlistlist);
		String tel = (String)httpSession.getAttribute("tel");
		log.info("tel=="+tel);
		if(tel == null || tel.equals("")) {//2016.3.1
			httpSession.setAttribute("newAddTel","null");
			httpSession.setAttribute("tel", "");
		}
		else {
			httpSession.setAttribute("newAddTel", tel);
			httpSession.setAttribute("tel", "");
		}
		return "/mbem/mcrm/business/addCustom";//����¿ͻ�ʱ�򣬴�����������
	}
	
	/**
	 * �����ҵ�������ڸ����Ŀͻ��б�����
	 * @return
	 */
	/*@RequiresPermissions(value="page:mbem:mcrm:zs:customPage")
	@RequestMapping(value="/customPage")
	public String getZsBasicInfoByEmploy() {
		ZsInfoVo vo=new ZsInfoVo();
		zsInfoService.getZsBasicInfoByEmployee(vo);
		return "/mbem/mcrm/business/customPage";
	}*/
	
	/**
	 * �����ҵ�����������ڿͻ��б� �󣬸��ݿͻ������ƻ�绰������н�һ����ѯ
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:zs:customPage")
	@RequestMapping(value="/getZsBasicInfoByEmployeeAndCusNameOrTel",method=RequestMethod.POST)
	public String getZsBasicInfoByEmployeeAndCusNameOrTel(ZsInfoVo voo) {
		String cusNameOrTel=voo.getCusNameOrTel();
		log.info("cusname oR tel "+voo.getCusNameOrTel());
		if(cusNameOrTel !=null && !"".equals(cusNameOrTel)){
			ZsInfoVo vo=new ZsInfoVo();
			vo.setCusNameOrTel(cusNameOrTel);
			zsInfoService.getZsBasicInfoByEmployee(vo);
		}else{
			ZsInfoVo vo=new ZsInfoVo();
			zsInfoService.getZsBasicInfoByEmployee(vo);
		}
		
		return "/mbem/mcrm/business/customPage";
	}
	
	/**
	 *  �߼���ѯ
	 *  --�����ҵ�����������ڿͻ��б� �󣬸��ݿͻ��ĸ������Ͻ��и߼���ѯ
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:zs:customPage")
	@RequestMapping(value="/getZsBasicInfoByEmployeeAndCusMoreInfo")
	public String getZsBasicInfoByEmployeeAndCusMoreInfo(ZsInfoVo vo) {
		log.info(" "+vo.getIntentionType());
		ZsInfoVo voo= new ZsInfoVo(vo.getAge(), vo.getFollowWay(), vo.getIntentionType(), vo.getInvestmentInfo(), vo.getCustomStatus(), vo.getFirstVisWay(), vo.getPurposeItem());
		zsInfoService.getZsBasicInfoByEmployee(voo);
		return "/mbem/mcrm/business/customPage";
	}
	
	/**
	 * ���ݿͻ�id��øÿͻ��ĸ�����¼�͸ÿͻ�����ϸ���ϡ�
	 * @RequestParam ������·���б�����Ĳ���
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:zs:personal")
	@RequestMapping(value="/personal")
	public String getZsFollowInfoByCustomID(@RequestParam(value="cusId",required=false) String cusId) {
		log.info(cusId);
		if("".equals(cusId)){
			log.info("id ����Ϊ��");
		}
		List<ZsCustomFollowRecordTableVo> list=zsInfoService.getZsFollowInfoByCustomID();
		return "/mbem/mcrm/business/personal";
	}
	
	/**
	 * �����Ͽͻ��ĸ�����¼��ͬʱ�޸Ŀͻ����пͻ����´θ���ʱ����������
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:zs:follow")
	@RequestMapping(value="/insertZsOldCusFollowInfoDao")
	public String insertZsOldCusFollowInfoDao(ZsInfoVo vo) {
		//employeeName//zsInfoService ������intentionType IntentionType
		log.info(vo.getNextFollowDate()+" type "+vo.getIntentionType());
		String nextFollowDate=vo.getNextFollowDate();
		String cusId="";
			cusId=(String) httpSession.getAttribute("currentCustomID");
			log.info("ssssss "+cusId);
			log.info(cusId==null);
		if(cusId ==null || "".equals(cusId) || "null".equals(cusId)){
			log.info("dddd");
			cusId =(String) httpSession.getAttribute("cst_id");
		}
		log.info("cusid "+cusId);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String followDate=df.format(new Date());//�������ʱ���� ������ 
		log.info(followDate);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String hms=sdf.format(new Date());//�������ʱ���� ������ 
		log.info("hms "+hms);
		nextFollowDate=nextFollowDate+" "+hms;
		log.info(nextFollowDate);
		log.info(vo.getFollowWay());
		/*try{
				String gbk = new String(vo.getFollowInfo().getBytes("GBK"),"ISO-8859-1");//ת����gbk����
				String unicodeChinese = new String(gbk.getBytes("ISO-8859-1"),"gbk");
				String c = new String(vo.getFollowInfo().getBytes("ISO-8859-1"),"utf-8");
				String a=URLDecoder.decode(vo.getFollowInfo(),"UTF-8");
				String b=URLDecoder.decode(vo.getFollowInfo(),"gb2312");
				String d=new String(vo.getFollowInfo().getBytes("GBK"), "UTF-8");
				String dd=new String(vo.getFollowInfo().getBytes("GBK"), "gbk");
				String f=new String(vo.getFollowInfo().getBytes(), "UTF-8");
				log.info("����   "+unicodeChinese+" a "+a+" b "+b+" c"+c+" d "+d+" f "+f+" dd "+dd);
			
		}catch (UnsupportedEncodingException e) {
				e.printStackTrace();
		}*/
		zsInfoService.insertZsOldCusFollowInfoDao(vo.getEmployeeName(),followDate,vo.getFollowWay(),vo.getFollowInfo(),vo.getIntentionType(),nextFollowDate);
		//return getZsFollowInfoByCustomID(cusId);
		//���ͨ������ֱ�Ӳ�ѯΪ�����ͻ�����ֱ�ӶԹ����ͻ����и�����������Ҫ���ÿͻ��ֵ������˵����¡�
		ZsInfoVo bean=new ZsInfoVo();
		bean.setCusId(cusId);
		ZsEmployeeTableVo voo=zsInfoDao.checkCusPublicOrInvalid(bean);
		if(voo.getEmployeeId().equals("-10")){//�ӹ����ͻ�ֱ�Ӹ�����
			User user = (User)RbacUtils.subject().getSession().getAttribute("user");
			int userId=user.getUserID();
			zsBusiCustomAllocateService.allocateCustomers(Integer.toString(userId),cusId,voo.getProjectID());
			log.info("hi a am the public to private ");
		}
		return "redirect:/mbem/mcrm/business/personal.action?cusId="+cusId;
	}
	
	/**
	 * ��ÿͻ��������� 
	 * @return
	 */
	/*@RequiresPermissions(value="page:mbem:mcrm:zs:backlogPage")
	@RequestMapping(value="/backlogPage")
	public String getFollowRemind() {
		log.info("acremaind");
		log.info("testtesttest");
		zsInfoService.getFollowRemind();
		return "/mbem/mcrm/business/backlogPage";
	}*/
	
	/**
	 * ��ÿͻ��Ĺ�����Ϣ
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:zs:customPage")
	@RequestMapping(value="/getZsCustomBelonging")
	public String getZsCustomBelonging(String tel){
		log.info(tel);
		zsInfoService.getZsCustomBelonging(tel);
		return null;
		
	}
	
	/**
	 * �����ͻ�ʱ���жϿͻ��绰�����Ƿ���Ч
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:zs:addCustom")
	@RequestMapping(value="/checkExistCusByTel")
	public String checkExistCusByTel(String tel) {
		log.info(tel);
		zsInfoService.checkExistCusByTel(tel);
		return null;
	}
	
	/**
	 * ����´θ���ʱ��
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:zs:addCustom")
	@RequestMapping(value="/getAutoGenNextFollowDate")
	public String getAutoGenNextFollowDate(Model model,ZsInfoVo vo) {
		String nextFollowDate=zsInfoService.getAutoGenNextFollowDate();
		model.addAttribute("cusName", "di");//��model ������session
		log.info(vo);
		return "/mbem/mcrm/business/addCustom";
	}
	
	
	/**
	 * �޸Ŀͻ�
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:zs:updateCustom")
	@RequestMapping("/updateCustom")
	public String updateCustom(Model model){
		List<ZsCustomTableVo> cusInfoList = (List<ZsCustomTableVo>)httpSession.getAttribute("cusBasicByCusIdlist");
		log.info(cusInfoList.get(0).getCusName());
		Map<String,List<Object>> optionlistlist = new HashMap<String,List<Object>>();
		List<SearchOptionEntity> optionList = new ArrayList<SearchOptionEntity>();
		List<Object> listlist = new ArrayList<Object>();
		List<Object> droplist = new ArrayList<Object>();
		SearchOptionEntity option = new SearchOptionEntity();
		List<Map<String,String>> list0 = new ArrayList<Map<String,String>>();
		List<Map<String,String>> list1 = new ArrayList<Map<String,String>>();
		List<Map<String,String>> list2 = new ArrayList<Map<String,String>>();
		ZsAddAndUpdateCusOption hh = new ZsAddAndUpdateCusOption();
		String defaultTel;
		ZsCustomTableVo cus = cusInfoList.get(0);
		option.setModule("addcust");
		optionList = listservice.getOptionListByModule(option);
		String defaultTelId = "telPhone";
		for(SearchOptionEntity options:optionList) {
			String comment = options.getComment();
			if(comment == null || comment.equals(""))
				continue;
			log.info("comment"+comment);
			switch(comment) {
				case "cusName":
					options.setContent(cus.getCusName());
					break;
				case "sex":
					options.setContent(cus.getSex());
					break;
				case "label":
					options.setContent(cus.getLabel());
					break;
				case "valided":
					options.setContent(cus.getValided());
					log.info(cus.getValided()+"��Ч��Ч");
					break;
				case "age":
					options.setContent(cus.getAge());
					break;
				case "firstVisWay":
					options.setContent(cus.getFirstVisWay());
					break;
				case "houseUse":
					options.setContent(cus.getHouseUse());
					break;
				case "idNo":
					options.setContent(cus.getIdNo());
					break;
				case "intentionArea":
					options.setContent(cus.getIntentionArea());
					break;
				case "factor":
					options.setContent(cus.getFactor());
					break;
				case "workComp":
					options.setContent(cus.getWorkComp());
					break;
				case "residenceAddr":
					options.setContent(cus.getResidenceAddr());
					break;
				case "buyTimes":
					options.setContent(cus.getBuyTimes());
					break;
				case "workType":
					options.setContent(cus.getWorkType());
					break;
				case "competitor":
					options.setContent(cus.getCompetitor());
					break;
				case "intentionPrice":
					options.setContent(cus.getIntentionPrice());
					break;
				case "intentionProduct":
					options.setContent(cus.getIntentionProduct());
					break;
				case "intentionRoom":
					options.setContent(cus.getIntentionRoom());
					break;
				case "visitTimes":
					options.setContent(cus.getVisitTimes());
					break;
				case "familyInfo":
					options.setContent(cus.getFamilyInfo());
					break;
				case "investmentInfo":
					options.setContent(cus.getInvestmentInfo());
					break;
				case "telPhone":
					defaultTel = cus.getTel();
					if(cus.getPhoneNo()==null || cus.getPhoneNo().equals(""))
						options.setContent(cus.getTel());
					else{
						options.setContent(cus.getPhoneNo().split(",")[0]);
						if(cus.getPhoneNo().split(",")[0].equals(defaultTel)) {
							defaultTelId = "telPhone";
							//httpSession.setAttribute("defaultTelId", "telPhone");
						}
					}
					model.addAttribute("defaultTelId", defaultTelId);
					break;
				case "homeTel":
					defaultTel = cus.getTel();
					if(cus.getPhoneNo()==null || cus.getPhoneNo().equals(""))
						options.setContent("");
					else{
						String homeTel = cus.getPhoneNo().split(",")[1];
						if(homeTel.equals("null") || homeTel.equals(""))
							options.setContent("invisiable");//2016.4.5
						else
							options.setContent(homeTel);
						if(homeTel.equals(defaultTel)) {
							defaultTelId = "telhome";
							//httpSession.setAttribute("defaultTelId", "telhome");
						}
					}
					break;
				case "officeTel":
					defaultTel = cus.getTel();
					log.info(cus.getPhoneNo());
					if(cus.getPhoneNo()==null || cus.getPhoneNo().equals(""))
						options.setContent("");
					else{
						String officeTel = cus.getPhoneNo().split(",")[2];
						if(officeTel.equals("null") || officeTel.equals(""))
							options.setContent("invisiable");
						else
							options.setContent(officeTel);//phoneNo�����δ�����ֻ��š���ͥ�绰���칫�绰���Զ��ŷָ�
						if(officeTel.equals(defaultTel)) {
							defaultTelId = "teloffice";
							//httpSession.setAttribute("defaultTelId", "teloffice");
						}
					}
					break;
			}
		}
		for(SearchOptionEntity option1:optionList) {
			log.info(option1.getModule());
			log.info(option1.getName());
			log.info("isEdit:"+option1.isEdit());
			if(option1.getSort() == 2 ||(option1.getContent()!=null &&option1.getContent().equals("invisiable"))/*|| option1.getContent() == null || option1.getContent().equals("")|| option1.getContent().equals("null")*/)
				continue;
			TagOption to = hh.generateHtmlEntity(option1);
			Map<String,String> test = hh.jointHtmlStr(to);
			int index = Integer.parseInt(test.get("group").toString());
			log.info("index"+index);
			//arrayoptionlist.add(index, test);
			if(index == 0)
				list0.add(test);
			else if(index == 1)
				list1.add(test);
			else if(index == 2)
				list2.add(test);
		}
		listlist.add(list0);
		listlist.add(list1);
		listlist.add(list2);
		droplist = zsInfoService.getAllDroplist("edit");//������е������ֶ�
		
		optionlistlist.put("field", listlist);
		optionlistlist.put("option", droplist);
		httpSession.setAttribute("updatecustList", optionlistlist);
		
		model.addAttribute("defaultTelId", defaultTelId);
		return "/mbem/mcrm/business/updateCustom";
	}
	/**
	 * �������������ť
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:zs:follow")
	@RequestMapping("/follow")
	public String newFollow(@RequestParam(value="type",required=false)String type,Model model){
		
		zsInfoService.getAllDroplist("edit");//��ȡ�����ֶ�
		model.addAttribute("closeType", type);
		log.info("closeType:"+type);
		
		return "/mbem/mcrm/business/follow";
		
	}
	/**
	 * �������������ȡ����ť
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:zs:follow")
	@RequestMapping("/cancleNewFollow")
	public String cancleNewFollow(){
		
		
		return "/mbem/mcrm/business/personal";
		
	}
	/**
	 * ����޸Ŀͻ���ȡ����ť
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:zs:updateCustom")
	@RequestMapping("/cancleUpdateCus")
	public String cancleUpdateCus(){
		
		
		return "/mbem/mcrm/business/personal";
		
	}
	
	

	@RequestMapping(value="/getZsFollowInfoDao")
	public String getZsFollowInfoDao() {
		return null;
		
	}

	
	public String getCusInfoCompleteDegree() {
		return null;
	}

	
	public String getCusFollowStatus() {
		return null;
	}

	
	public List<ZsInfoVo> getFollowRecall() {
		return null;
	}
	
	/**
	 * �Զ���½ʱ���������󣬻�ȡ����
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:zs:backlogPage")
	@RequestMapping(value="/getFollowRemindWithoutLogin")
	public String getFollowRemindWithoutLogin() {
		String username=getZsEmployeeListWithoutLogin();
		request.getSession().setAttribute("username", username);
		zsInfoService.getFollowRemind();
		return "/mbem/mcrm/business/backlogPage";
	}
	
	
	@RequestMapping(value="/getZsEmployeeListWithoutLogin")
	public String getZsEmployeeListWithoutLogin() {
		ZsInfoVo bean = new ZsInfoVo();
		String tel=(String) request.getSession().getAttribute("telphone");
		log.info("zs tel "+tel);
		bean.setTel(tel);
		List<ZsEmployeeTableVo> list=zsInfoService.getZsEmployeeList(bean);
		for (ZsEmployeeTableVo zsInfoVo : list) {
			System.out.println(zsInfoVo.getId()+" ");
		}
		return list.get(0).getEmployeeName();
		
	}
	
	@RequestMapping(value="/getZsEmployeeList")
	public String getZsEmployeeList(String tel) {
		ZsInfoVo bean = new ZsInfoVo();
		log.info(tel);
		bean.setTel(tel);
		List<ZsEmployeeTableVo> list=zsInfoService.getZsEmployeeList(bean);
		for (ZsEmployeeTableVo zsInfoVo : list) {
			System.out.println(zsInfoVo.getId()+" ");
		}
		return list.get(0).getEmployeeName();
		
	}

	@RequestMapping(value="/insertZsEmployee")
	public String insertZsEmployee() {
		ZsInfoVo bean = new ZsInfoVo();
		bean.setEmployeeName("yanzhiu");
		bean.setEmployeeNo("A055");
		bean.setDetpId("2");
		bean.setBirthday("2015-01-01");
		String id=zsInfoService.insertZsEmployee(bean);
		id=bean.getId();
		return "success";
	}

	@RequestMapping(value="/updateZsEmployee")
	public String updateZsEmployee() {
		ZsInfoVo bean = new ZsInfoVo();
		bean.setEmployeeName("yaya");
		bean.setEmployeeNo("A055");
		bean.setDetpId("2");
		bean.setBirthday("2015-01-01");
		bean.setId("64");
		String id=zsInfoService.updateZsEmployee(bean);
		id=bean.getId();
		return "success";
	}
	
	
	@RequestMapping("/testtest")
	public String test(Model model){
		log.info("test ");
		model.addAttribute("cusName", "yanzhijun");
		return "addCustom";
		
	}
	
	@RequestMapping("/find/feedback")
	public String feedback() {
		return "/mbem/mcrm/business/find/feedback";
	}
	@RequiresPermissions(value="page:mbem:mcrm:zs:customPage")
	@ResponseBody
	@RequestMapping(value="/loadZsCustomList",method = RequestMethod.POST)
	public List<ZsInfoVo> loadZsCustomList(int page,int pageLen) {
		List<ZsInfoVo> customList = new ArrayList<ZsInfoVo>();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		String employeeId = user.getUserID()+"";
		ZsInfoVo searchInfo = new ZsInfoVo();
		searchInfo.setStartIndex((page-1)*pageLen);
		searchInfo.setLength(pageLen);
		searchInfo.setEmployeeId(employeeId);
		searchInfo.setProgGuid(projGuid);
		customList = zsInfoService.getZsCustomListInfo(searchInfo);
		log.info(customList.size()+"��");
		return customList;
	}
	@RequiresPermissions(value="page:mbem:mcrm:zs:customPage")
	@ResponseBody
	@RequestMapping(value="/loadZsCustomListByNameOrTel",method = RequestMethod.POST)
	public List<ZsInfoVo> loadZsCustomListByNameOrTel(int page,int pageLen){
		List<ZsInfoVo> customList = new ArrayList<ZsInfoVo>();
		ZsInfoVo searchInfo = new ZsInfoVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		String employeeId = user.getUserID()+"";
		String nameOrTel = (String) httpSession.getAttribute("nameOrTel");
		searchInfo.setStartIndex((page-1)*pageLen);
		searchInfo.setLength(pageLen);
		searchInfo.setEmployeeId(employeeId);
		searchInfo.setProgGuid(projGuid);
		if(nameOrTel !=null && !"".equals(nameOrTel)){//���ݿͻ����ƺ͵绰ģ����ѯ
			boolean isNum = nameOrTel.matches("[0-9]+");
			if(isNum){
				searchInfo.setTel(nameOrTel);
			}else{
				searchInfo.setCusName(nameOrTel);
			}
		}
		customList = zsInfoService.getZsCustomListInfo(searchInfo);
		log.info(customList.size()+"��");
		return customList;
	}
	@RequiresPermissions(value="page:mbem:mcrm:zs:customPage")
	@RequestMapping(value="/customPage")
	public String customPage(Model model) {
		ZsInfoVo searchInfo = new ZsInfoVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		String employeeId = user.getUserID()+"";//"20";//�����ã�������session��ȡ
		searchInfo.setEmployeeId(employeeId);
		searchInfo.setProgGuid(projGuid);
		int cusCount = zsInfoService.getZsCustomCount(searchInfo);
		model.addAttribute("customListCount", cusCount);
		model.addAttribute("ajaxUrl", "/mbem/mcrm/business/loadZsCustomList.action");
		return "/mbem/mcrm/business/customPage";
	}
	@RequiresPermissions(value="page:mbem:mcrm:zs:customPage")
	@RequestMapping(value="/getZsCustomListByNameOrTel")
	public String getZsCustomListByNameOrTel(Model model,String cusNameOrTel) {
		ZsInfoVo searchInfo = new ZsInfoVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		if(cusNameOrTel !=null && !"".equals(cusNameOrTel)){//���ݿͻ����ƺ͵绰ģ����ѯ
			boolean isNum = cusNameOrTel.matches("[0-9]+");
			if(isNum){
				searchInfo.setTel(cusNameOrTel);
			}else{
				searchInfo.setCusName(cusNameOrTel);
			}
		}
		String employeeId = user.getUserID()+"";
		searchInfo.setEmployeeId(employeeId);
		searchInfo.setProgGuid(projGuid);
		int cusCount = zsInfoService.getZsCustomCount(searchInfo);
		model.addAttribute("customListCount", cusCount);
		model.addAttribute("ajaxUrl", "/mbem/mcrm/business/loadZsCustomListByNameOrTel.action");
		httpSession.setAttribute("nameOrTel", cusNameOrTel);
		return "/mbem/mcrm/business/customPage";
	}
	@RequiresPermissions(value="page:mbem:mcrm:zs:backlogPage")
	@RequestMapping(value="/backlogPage")
	public String backlogPage(Model model) {
		ZsInfoVo searchInfo = new ZsInfoVo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		String employeeId = user.getUserID()+"";
		searchInfo.setEmployeeId(employeeId);
		searchInfo.setProgGuid(projGuid);
		searchInfo.setCusType("toVisit");
		int toVisit = zsInfoService.getZsCustomCountByType(searchInfo);
		searchInfo.setCusType("overDue3Days");
		int overDue3Days = zsInfoService.getZsCustomCountByType(searchInfo);
		searchInfo.setCusType("overDue");
		int overDue = zsInfoService.getZsCustomCountByType(searchInfo);
		searchInfo.setCusType("newAssign");
		int newAssign = zsInfoService.getZsCustomCountByType(searchInfo);
		log.info(toVisit+" "+overDue3Days+" "+overDue+"  "+newAssign);
		model.addAttribute("toVisit", toVisit);
		model.addAttribute("overDue3Days", overDue3Days);
		model.addAttribute("overDue", overDue);
		model.addAttribute("newAssign", newAssign);
		return "/mbem/mcrm/business/backlogPage";
	}
	@RequiresPermissions(value="page:mbem:mcrm:zs:backlogPage")
	@ResponseBody
	@RequestMapping(value="/getZsCustomListByType",method=RequestMethod.POST)
	public List<ZsInfoVo> getZsCustomListByType(String type,int page,int pageLen) {
		ZsInfoVo searchInfo = new ZsInfoVo();
		List<ZsInfoVo> customList = new ArrayList<ZsInfoVo>();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		String employeeId = user.getUserID()+"";
		searchInfo.setEmployeeId(employeeId);
		searchInfo.setProgGuid(projGuid);
		searchInfo.setCusType(type);
		searchInfo.setStartIndex(pageLen*(page-1));
		searchInfo.setLength(pageLen);
		customList = zsInfoService.getZsCustomListInfoByType(searchInfo);
		log.info(type+"  "+customList.size()+"��");
		return customList;
	}
	
	@RequestMapping(value="/backlogCustomPage")
	public String backlogCustomPage(@RequestParam(value="type",required=true) String type) {
		log.info("cusType="+type);
		httpSession.setAttribute("cstType", type);
		return "/mbem/mcrm/business/backlogCustomPage";
	}
	/*
	 * 
	 */
	/*@RequiresPermissions(value="page:mbem:mcrm:zs:customPage")*/
	@RequiresPermissions(value="page:businessMobile:customer:advancedSearch")
	@RequestMapping(value="/advancedSearch")
	public String advancedSearch(){
		return "/mbem/mcrm/business/advancedSearch";
	}
	
	/*@RequiresPermissions(value="page:mbem:mcrm:zs:customPage")*/
	@RequiresPermissions(value="page:businessMobile:customer:advancedSearch")
	@RequestMapping(value="/getZsCstInfoByAdvancedSearch")
	public String getXsCstInfoByAdvancedSearch(Model model,ZsInfoVo searchOption){
		String telOrName = searchOption.getCusNameOrTel();
		log.info("telOrName=="+telOrName);
		if(telOrName !=null && !"".equals(telOrName)){//���ݿͻ����ƺ͵绰ģ����ѯ
			boolean isNum = telOrName.matches("[0-9]+");
			if(isNum){
				searchOption.setTel(telOrName);
			}else{
				searchOption.setCusName(telOrName);
			}
		}
		String []options = {};
		String optionStr = "";
		log.info("������"+searchOption.getCusName());
		log.info("�ֻ��ţ�"+searchOption.getTel());
		log.info("���򼶱�"+searchOption.getIntentionType());
		log.info("�ͻ�״̬��"+searchOption.getCustomStatus());
		log.info("����Σ�"+searchOption.getAge());
		log.info("������ʽ��"+searchOption.getFollowWay());
		log.info("��֪;����"+searchOption.getFirstVisWay());
		log.info("���������"+searchOption.getIntentionArea());
		if(searchOption.getIntentionType() != null && !searchOption.getIntentionType().equals("")) {
			options = searchOption.getIntentionType().split(",");
			optionStr = "";
			for(int i=0; i<options.length; i++) {
				if(i == 0) {
					optionStr += "\'"+options[i]+"\'";
				}else{
					optionStr += ",\'"+options[i]+"\'";
				}	
			}
			log.info("gfyxOptionStr:"+optionStr);
			searchOption.setIntentionType(optionStr);;
		}
		if(searchOption.getCustomStatus() != null && !searchOption.getCustomStatus().equals("")) {
			options = searchOption.getCustomStatus().split(",");
			optionStr = "";
			for(int i=0; i<options.length; i++) {
				if(i == 0) {
					optionStr += "\'"+options[i]+"\'";
				}else{
					optionStr += ",\'"+options[i]+"\'";
				}	
			}
			log.info("statusOptionStr:"+optionStr);
			searchOption.setCustomStatus(optionStr);
		}
		if(searchOption.getAge() != null && !searchOption.getAge().equals("")) {
			options = searchOption.getAge().split(",");
			optionStr = "";
			for(int i=0; i<options.length; i++) {
				if(i == 0) {
					optionStr += "\'"+options[i]+"\'";
				}else{
					optionStr += ",\'"+options[i]+"\'";
				}	
			}
			log.info("ageGroupOptionStr:"+optionStr);
			searchOption.setAge(optionStr);
		}
		if(searchOption.getFollowWay() != null && !searchOption.getFollowWay().equals("")) {
			options = searchOption.getFollowWay().split(",");
			optionStr = "";
			for(int i=0; i<options.length; i++) {
				if(i == 0) {
					optionStr += "\'"+options[i]+"\'";
				}else{
					optionStr += ",\'"+options[i]+"\'";
				}	
			}
			log.info("followWayStr:"+optionStr);
			searchOption.setFollowWay(optionStr);
		}
		if(searchOption.getIntentionArea() != null && !searchOption.getIntentionArea().equals("")) {
			options = searchOption.getIntentionArea().split(",");
			optionStr = "";
			for(int i=0; i<options.length; i++) {
				if(i == 0) {
					optionStr += "a.������� like \'%"+options[i]+"%\'";
				}else{
					optionStr += "or a.������� like \'%"+options[i]+"%\'";
					//optionStr += ",\'"+options[i]+"\'";
				}	
			}
			log.info("intentionAreaStr:"+optionStr);
			searchOption.setIntentionArea(optionStr);
		}
		if(searchOption.getFirstVisWay() != null && !searchOption.getFirstVisWay().equals("")) {
			options = searchOption.getFirstVisWay().split(",");
			optionStr = "";
			for(int i=0; i<options.length; i++) {
				if(i == 0) {
					optionStr += "\'"+options[i]+"\'";
				}else{
					optionStr += ",\'"+options[i]+"\'";
				}	
			}
			log.info("ageGroupOptionStr:"+optionStr);
			searchOption.setFirstVisWay(optionStr);
		}
		if(searchOption.getInvestmentInfo() != null && !searchOption.getInvestmentInfo().equals("")) {
			options = searchOption.getInvestmentInfo().split(",");
			optionStr = "";
			for(int i=0; i<options.length; i++) {
				if(i == 0) {
					optionStr += "a.����ҵ̬ like \'%"+options[i]+"%\'";
				}else{
					//optionStr += ",\'"+options[i]+"\'";
					optionStr += "or a.����ҵ̬ like \'%"+options[i]+"%\'";
				}	
			}
			log.info("investmentInfoOptionStr:"+optionStr);
			searchOption.setInvestmentInfo(optionStr);;
		}
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String employeeId = user.getUserID()+"";
		String projGuid = user.getExtInfo("projGuid");
		searchOption.setEmployeeId(employeeId);
		searchOption.setProgGuid(projGuid);
		int customListCount = zsInfoService.getZsCustomCount(searchOption);
		log.info("count"+customListCount);

		model.addAttribute("customListCount", customListCount);
		model.addAttribute("ajaxUrl", "/mbem/mcrm/business/loadZsCustomListByAdvancedSearch.action");
		httpSession.setAttribute("zsAdvancedSearchOption", searchOption);
		
		return "/mbem/mcrm/business/customPage";
	}
	/**
	 * 
	 * @param page
	 * @param pageLen
	 * @return
	 */
	@RequiresPermissions(value="page:businessMobile:customer:advancedSearch")
	@ResponseBody
	@RequestMapping(value="loadZsCustomListByAdvancedSearch")
	public List<ZsInfoVo> getZsCustomListByAdvancedSearch(int page,int pageLen){
		ZsInfoVo searchOption = new ZsInfoVo();
		List<ZsInfoVo> customList = new ArrayList<ZsInfoVo>();
		Session session = RBACSubject.getSecurityUtils().getSession();
		searchOption = (ZsInfoVo)httpSession.getAttribute("zsAdvancedSearchOption");
		User user = (User)session.getAttribute("user");
		String employeeId = user.getUserID()+"";
		String projGuid = user.getExtInfo("projGuid");
		searchOption.setEmployeeId(employeeId);
		searchOption.setProgGuid(projGuid);
		searchOption.setStartIndex((page-1)*pageLen);
		searchOption.setLength(pageLen);
		customList = zsInfoService.getZsCustomListInfo(searchOption);
		return customList;	
	}
	@RequestMapping(value="/test")
	public String backlogCustomPage() {
		return "/mbem/mcrm/business/MyJsp";
	}
	
public static void main(String[] args) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		Calendar rightNow=Calendar.getInstance();
		rightNow.add(java.util.Calendar.DAY_OF_MONTH, 3);          
		String date = df.format(rightNow.getTime()); 
		System.out.println(date);
		System.out.println(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
		//AnnotationMethodHandlerAdapter
		//AutowiredAnnotationBeanPostProcessor
		//CommonAnnotationBeanPostProcessor
		//PersistenceAnnotationBeanPostProcessor 
		//RequiredAnnotationBeanPostProcessor 
		//DispatcherServlet
		//HandlerExecutionChain
	}

}
