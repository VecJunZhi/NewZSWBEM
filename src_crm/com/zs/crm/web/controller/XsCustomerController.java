package com.zs.crm.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
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

import com.zs.busi.entity.ZsBusiCustomLogTable;
import com.zs.common.entity.SearchOptionEntity;
import com.zs.common.service.IOptionListService;
import com.zs.common.util.search.TagOption;
import com.zs.crm.entity.XsCstBelongToAjax;
import com.zs.crm.entity.XsCstInfo;
import com.zs.crm.entity.XsCstSearchOption;
import com.zs.crm.entity.tableStructure.XsCstAttachTableEntity;
import com.zs.crm.entity.tableStructure.XsCstAttrTableEntity;
import com.zs.crm.entity.tableStructure.XsCstTableEntity;
import com.zs.crm.entity.tableStructure.XsEmployeeTableEntity;
import com.zs.crm.entity.tableStructure.XsOpp2CstTableEntity;
import com.zs.crm.entity.tableStructure.XsOpp2GjjlTableEntity;
import com.zs.crm.entity.tableStructure.XsOpp2RoomTableEntity;
import com.zs.crm.entity.tableStructure.XsOppTableEntity;
import com.zs.crm.service.XsBusinessUrgedService;
import com.zs.crm.service.XsCstInfoService;
import com.zs.crm.service.impl.XsAddAndUpdateCstOption;
import com.zs.rbac.core.RBACSubject;
import com.zs.rbac.entity.User;
import com.zs.rbac.service.XsTeamGroupService;
import com.zs.rbac.utils.RbacUtils;

@Controller
@RequestMapping(value="/mbem/mcrm/house/customer")
public class XsCustomerController {
	private Log log=LogFactory.getLog(XsCustomerController.class);
	@Autowired
	XsCstInfoService xsCstInfo;
	@Autowired
	HttpSession httpSession;
	@Autowired
	IOptionListService optionListService;
	@Autowired
	HttpServletRequest request;
	@Autowired
	XsBusinessUrgedService xsBusinessUrgedService;
	@Autowired
	XsTeamGroupService xsTeamGroupService;
	
	
	//private static int NEXTFOLLOWINTERVALDAYS = 3;//Ĭ���´θ������
	//private static final String PROJGUID = "8FFF2136-61EA-E411-BAAF-FCAA145C42F2";//�����԰��Ŀid
	/**
	 * �������ҳ��(��ȡ���طá����ڡ����������ڿͻ�����)
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:backlog:remind")
	@RequestMapping(value="/backlogPage")
	public String getFollowRemind(Model model) {
		XsCstSearchOption searchInfo = new XsCstSearchOption();
		int toVisitCount;//���մ��طÿͻ���
		int overDueCount;//���ڿͻ���
		int overDue3DaysCount;//���������ڿͻ���
		int newAnalysisCount;
		User user = (User)RbacUtils.subject().getSession().getAttribute("user");
		String userGuid=user.getExtInfo("xs_userGuid");
		String projGuid = user.getExtInfo("projGuid");
		searchInfo.setProjGUID(projGuid);
		searchInfo.setUserGUID(userGuid);
		searchInfo.setCstType("toVisit");
		toVisitCount = xsCstInfo.getXsCstCountByEmployee(searchInfo);
		searchInfo.setCstType("overDue");
		overDueCount = xsCstInfo.getXsCstCountByEmployee(searchInfo);
		searchInfo.setCstType("overDue3Days");
		overDue3DaysCount = xsCstInfo.getXsCstCountByEmployee(searchInfo);
		searchInfo.setCstType("newAnalysisCount");
		newAnalysisCount = xsCstInfo.getXsCstCountByEmployee(searchInfo);
		model.addAttribute("toVisitCount", toVisitCount);
		model.addAttribute("overDue3DaysCount", overDue3DaysCount);
		model.addAttribute("overDueCount", overDueCount);
		model.addAttribute("newAnalysisCount",newAnalysisCount);
		return "/mbem/mcrm/house/customer/backlogPage";
	}
	/**
	 * ����ҳ���ݿͻ����ͷ�ҳ��ȡ�ͻ��б�
	 * @param type
	 * @param page
	 * @param pageLen
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:backlog:remind")
	@ResponseBody
	@RequestMapping(value="/getXsCustomListByType",method=RequestMethod.POST)
	public List<XsCstInfo> getXsCustomListByType(String type,int page,int pageLen) {
		XsCstSearchOption searchInfo = new XsCstSearchOption();
		List<XsCstInfo> customList = new ArrayList<XsCstInfo>();
		User user = (User)RbacUtils.subject().getSession().getAttribute("user");
		String userGUID = user.getExtInfo("xs_userGuid");
		String projGuid = user.getExtInfo("projGuid");
		searchInfo.setUserGUID(userGUID);
		searchInfo.setCstType(type);
		searchInfo.setStartIndex(pageLen*(page-1));
		searchInfo.setLength(pageLen);
		searchInfo.setSortWay("2");//Ĭ�ϰ��������ڽ��� 2016-5-27�޸�Ϊ�����������������������ڶ����ǰ��
		searchInfo.setProjGUID(projGuid);
		customList = xsCstInfo.getXsCstListByEmployee(searchInfo);
		log.info("type="+type+"  "+customList.size()+"��");
		return customList;
	}
	/**
	 * ���������ͻ�ҳ��(��ȡ�����ͻ���Ϣ��ѡ��)
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:customerManage:add")
	@RequestMapping(value="/addCustom")
	public String addCstInfo(@RequestParam(value="newTel",required=false) String tel,Model model) {
		SearchOptionEntity option = new SearchOptionEntity();
		List<Map<String,String>> optionList = new ArrayList<Map<String,String>>();
		XsAddAndUpdateCstOption cstOption = new XsAddAndUpdateCstOption();
		List<SearchOptionEntity> showOptionList = new ArrayList<SearchOptionEntity>();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		option.setModule("xsaddcst");
		showOptionList = optionListService.getOptionListByModule(option);
		for(SearchOptionEntity showOption:showOptionList) {
			if(showOption.getName().equals("�Ƿ���Ч")||showOption.getName().equals("�Ƿ���Ч")||showOption.getName().equals("��ͥ�绰")||showOption.getName().equals("�칫�绰")||showOption.getName().equals("����")){
				continue;
			}
			if(showOption.getName().equals("�ֻ�")){
				showOption.setContent(tel);
			}
			TagOption to = cstOption.generateHtmlEntity(showOption);
			Map<String,String> optionMap = cstOption.jointHtmlStr(to);
			optionList.add(optionMap);
		}
		if(tel == null || tel.equals("")) {
			request.setAttribute("xsNewTel","null");
		}else {
			request.setAttribute("xsNewTel", tel);//2016.3.12
		}
		String nextFollowDate = xsCstInfo.getNextFollowDate();
		model.addAttribute("autoGenNextFollowDate", nextFollowDate.substring(0,10));
		httpSession.setAttribute("addCstInfoList", optionList);
		model.addAttribute("projGuid", projGuid);
		model.addAttribute("actionUrl", "/mbem/mcrm/house/customer/insertXsCstAllInfo.action");
		return "/mbem/mcrm/house/customer/addCustom";
	}
	/**
	 * �����ͻ���Ϣ���������������Ϣ
	 * @param bean
	 * @param bean1
	 * @param bean2
	 * @param bean3
	 * @param bean4
	 * @param bean5
	 * @param bean6
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:customerManage:add")
	@RequestMapping(value="/insertXsCstAllInfo")
	public String insertCstAllInfo(XsCstTableEntity bean,XsCstAttrTableEntity bean1,XsCstAttachTableEntity bean2,XsOppTableEntity bean3,XsOpp2CstTableEntity bean4,XsOpp2RoomTableEntity bean5,XsOpp2GjjlTableEntity bean6) {	
		String zygw;
		String defaultTel,phoneNo;
		String insertOrUpdate = "insert";
		XsCstTableEntity searchInfo = new XsCstTableEntity();
		XsCstTableEntity cstInfo = new XsCstTableEntity();
		/*��֤���ֻ����Ƿ��пͻ�����     2016-7-15 lu*/
		List<XsCstInfo> cstList = new ArrayList<XsCstInfo>();
		XsCstSearchOption option = new XsCstSearchOption();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		option.setStartIndex(0);
		option.setLength(1);
		option.setSortWay("1");
		option.setIsAll("1");
		option.setProjGUID(projGuid);
		if(bean.getMobileTel()!=null && !bean.getMobileTel().equals("")){
			option.setMobileTel(bean.getMobileTel());
			cstList=xsCstInfo.getXsCstListByEmployee(option);
		    if(cstList.size() != 0) {
		    	log.info("�ֻ����Ѿ�¼���");
		    	return "redirect:/mbem/mcrm/house/customer/customPage.action";
		    }
		}
		if(bean.getHomeTel()!=null && !bean.getHomeTel().equals("")){
			option.setMobileTel(bean.getHomeTel());
			cstList=xsCstInfo.getXsCstListByEmployee(option);
		    if(cstList.size() != 0) {
		    	log.info("��ͥ���Ѿ�¼���");
		    	return "redirect:/mbem/mcrm/house/customer/customPage.action";
		    }  	
		}
		if(bean.getOfficeTel()!=null && !bean.getOfficeTel().equals("")){
			option.setMobileTel(bean.getOfficeTel());
			cstList=xsCstInfo.getXsCstListByEmployee(option);
		    if(cstList.size() != 0) {
		    	log.info("�칫���Ѿ�¼���");
		    	return "redirect:/mbem/mcrm/house/customer/customPage.action";
		    } 	
		}
		if(bean.getFax()!=null && !bean.getFax().equals("")){
			option.setMobileTel(bean.getFax());
			cstList=xsCstInfo.getXsCstListByEmployee(option);
		    if(cstList.size() != 0) {
		    	log.info("������Ѿ�¼���");
		    	return "redirect:/mbem/mcrm/house/customer/customPage.action";
		    }
		}
	    /*��֤�ֻ����Ƿ��пͻ���Ϣ����(��ǰ��Ŀ��û�л���)������������¿ͻ������������ͻ�*/
		if(bean.getMobileTel()!=null && !bean.getMobileTel().equals("")) {
			searchInfo.setMobileTel(bean.getMobileTel());	
			cstInfo = xsCstInfo.getXsCstBasicInfo(searchInfo);
			if(cstInfo!=null && !cstInfo.equals("")) {
				insertOrUpdate = "update";
			}else{
				if(bean.getHomeTel()!=null && !bean.getHomeTel().equals("")) {
					searchInfo.setMobileTel(bean.getHomeTel());
					cstInfo = xsCstInfo.getXsCstBasicInfo(searchInfo);
					if(cstInfo!=null && !cstInfo.equals("")) {
						insertOrUpdate = "update";
					}else{
						if(bean.getOfficeTel()!=null && !bean.getOfficeTel().equals("")) {
							searchInfo.setMobileTel(bean.getOfficeTel());
							cstInfo = xsCstInfo.getXsCstBasicInfo(searchInfo);
							if(cstInfo!=null && !cstInfo.equals("")) {
								insertOrUpdate = "update";
							}else{
								if(bean.getFax()!=null && !bean.getFax().equals("")) {
									searchInfo.setMobileTel(bean.getFax());
									cstInfo = xsCstInfo.getXsCstBasicInfo(searchInfo);
									if(cstInfo!=null && !cstInfo.equals("")){
										insertOrUpdate = "update";
									}	
								}
							}
						}
					}
				}
			}		
		}
		if(insertOrUpdate.equals("update")) {
			/*if(cstInfo.getMobileTel()!=null && !cstInfo.getMobileTel().equals("")){
				bean.setMobileTel("");
			}
			if(cstInfo.getHomeTel()!=null && !cstInfo.getHomeTel().equals("")){
				bean.setHomeTel("");
			}
			if(cstInfo.getOfficeTel()!=null && !cstInfo.getOfficeTel().equals("")){
				bean.setOfficeTel("");
			}
			if(cstInfo.getFax()!=null && !cstInfo.getFax().equals("")){
				bean.setFax("");
			}*/
			bean.setCstGuid(cstInfo.getCstGuid());
		}
		if(insertOrUpdate.equals("insert")) {
			defaultTel = bean.getDefaultTel();
			phoneNo = bean.getMobileTel();
			switch(defaultTel){
				case "homeTel":
					bean.setMobileTel(bean.getHomeTel());
					bean.setHomeTel(phoneNo);
					break;
				case "officeTel":
					bean.setMobileTel(bean.getOfficeTel());
					bean.setOfficeTel(phoneNo);
					break;
				case "fax":
					bean.setMobileTel(bean.getFax());
					bean.setFax(phoneNo);
					break;
				case "tel":
					break;
			}
		}

		zygw = user.getUsername();
		String buGuid = "42A0C9C9-51EA-E411-BAAF-FCAA145C42F2";//��˾GUID
		Byte by = 1;
		SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String followDate = fm1.format(new Date());
		String userGuid=user.getExtInfo("xs_userGuid");
		bean.setCreatedBy(zygw);
		bean.setCreatedByGuid(userGuid);
		bean.setCreatedOn(fm1.format(new Date()));
		bean.setCstType("����");//�ݶ�Ϊ���������������ʲô��ʶ��Ӧ����ɶ
		bean.setIsReceiveSms(by);
		bean.setIsCreatorUse(by);
		bean.setFirstName(bean.getCstName().substring(0, 1));
		bean.setLastName(bean.getCstName().substring(1));
		//bean1�Ļ�񡢽����̶ȡ���סҵ̬��ҳ���ȡ��������ʱû��
		bean2.setProjGuid(projGuid);
		bean2.setBuGuid(buGuid);
		bean2.setUserGuid(userGuid);
		
		bean3.setProjGuid(projGuid);
		bean3.setBuGuid(buGuid);
		bean3.setCreatedOn(fm1.format(new Date()));
		bean3.setOppSource(bean6.getGjfs());
		bean3.setFirstGfyx(bean3.getGfyx());
		bean3.setIsCreatorUse(by);//��֪��ɶ�õ�
		bean3.setCreatedByGuid(userGuid);
		bean3.setCreatedBy(zygw);
		bean3.setUserGuid(userGuid);//�봴���ߵ�����
		bean3.setZygw(zygw);
		bean3.setZygwGuid(userGuid);
		bean3.setDescription("");//��ͻ����е�description�ֶ�����ͬ����ֹ���ͻ���ǩ���ȥ
		/*XsOppTableEntity entity=xsCstInfo.getStatusFlgFromOppOrtunity(bean3.getOppGuid());
		String nowStatus=entity.getStatus();
		int nowStatusFlg=0;
		if("��ʧ".equals(nowStatus)){
			nowStatusFlg=0;	
		}
		if("��ѯ".equals(nowStatus)){
			nowStatusFlg=1;	
		}if("����".equals(nowStatus)){
			nowStatusFlg=2;	
		}
		if("ԤԼ".equals(nowStatus)){
			nowStatusFlg=3;	
		}
		if("�Ϲ�".equals(nowStatus)){
			nowStatusFlg=4;		
		}
		if("ǩԼ".equals(nowStatus)){
			nowStatusFlg=5;	
		}*/
		if(bean6.getGjfs().equals("����") || bean6.getGjfs().equals("����")) {
			bean6.setGjfs("�ֳ��Ӵ�");
			bean6.setStatus("����");
			bean3.setStatus("����");
			/*if(nowStatusFlg>=2){
				bean3.setStatus("");
			}else{
				bean3.setStatus("����");
			}*/
		}else if(bean6.getGjfs().equals("����")) {
			bean6.setGjfs("�ͻ�����");
			bean6.setStatus("��ѯ");
			bean3.setStatus("��ѯ");
			/*if(nowStatusFlg>=1){
				bean3.setStatus("");
			}else{
				bean3.setStatus("��ѯ");
			}*/
		}else if(bean6.getGjfs().equals("ȥ��")) {
			bean6.setGjfs("�������");
			bean6.setStatus("��ѯ");
			bean3.setStatus("��ѯ");
			/*if(nowStatusFlg>=1){
				bean3.setStatus("");
			}else{
				bean3.setStatus("��ѯ");
			}*/
		}
			
		bean4.setCstNum("1");//��֪����ʲô�ж�����ֵ
		bean4.setProjGuid(projGuid);
		
		bean5.setNum("1");//���,�����ò�֪
		
		bean6.setProjGuid(projGuid);
		bean6.setGjDate(followDate);
		//bean6.setNextDate(bean6.getNextDate()+" "+fm1.format(new Date()));
		bean6.setGjrGuid(userGuid);
		XsCstInfo newCstInfo = new XsCstInfo();
		newCstInfo.setXsCst(bean);
		newCstInfo.setXsCstAttr(bean1);
		newCstInfo.setXsCstAttach(bean2);
		newCstInfo.setXsOpp(bean3);
		newCstInfo.setXsOpp2Cst(bean4);
		newCstInfo.setXsOpp2Room(bean5);
		newCstInfo.setXsOpp2Gjjl(bean6);
		if(insertOrUpdate.equals("insert")) {
			xsCstInfo.insertXsCstAllInfo(newCstInfo);
			List<XsCstInfo> custInfo=getXsCstInfoByTel(bean.getMobileTel());
			String custId=custInfo.get(0).getCstId();
			log.info(custId);
			log.info(bean.getMobileTel());
			 if(bean.getHomeTel() !=null && !"".equals(bean.getHomeTel())){
					ZsBusiCustomLogTable vo = new ZsBusiCustomLogTable();
					vo.setContent("����������ͥ����Ϊ"+bean.getHomeTel());
					vo.setReason("������ͥ��");
					vo.setOperator(zygw);
					vo.setOperDate(followDate);
					vo.setLogType("1");
					vo.setCusId(custId);
					vo.setBelongSys("xs");
					vo.setProjectId(projGuid);
					xsCstInfo.insertXsCstModifyLog(vo);
			}
			 if(bean.getOfficeTel() !=null && !"".equals(bean.getOfficeTel())){
				 	ZsBusiCustomLogTable vo = new ZsBusiCustomLogTable();
					vo.setContent("���������칫����Ϊ"+bean.getOfficeTel());
					vo.setReason("�����칫��");
					vo.setOperator(zygw);
					vo.setOperDate(followDate);
					vo.setLogType("1");
					vo.setCusId(custId);
					vo.setBelongSys("xs");
					vo.setProjectId(projGuid);
					xsCstInfo.insertXsCstModifyLog(vo);
			}
			if(bean.getFax() !=null && !"".equals(bean.getFax())){
					ZsBusiCustomLogTable vo = new ZsBusiCustomLogTable();
					vo.setContent("���������������Ϊ"+bean.getFax());
					vo.setReason("���������");
					vo.setOperator(zygw);
					vo.setOperDate(followDate);
					vo.setLogType("1");
					vo.setCusId(custId);
					vo.setBelongSys("xs");
					vo.setProjectId(projGuid);
					xsCstInfo.insertXsCstModifyLog(vo);
			}
			if(bean.getMobileTel() !=null && !"".equals(bean.getMobileTel())){
				ZsBusiCustomLogTable vo = new ZsBusiCustomLogTable();
				vo.setContent("���������ֻ�����Ϊ"+bean.getMobileTel());
				vo.setReason("�����ֻ���");
				vo.setOperator(zygw);
				vo.setOperDate(followDate);
				vo.setLogType("1");
				vo.setCusId(custId);
				vo.setBelongSys("xs");
				vo.setProjectId(projGuid);
				xsCstInfo.insertXsCstModifyLog(vo);
		}
		}
		if(insertOrUpdate.equals("update")) {
			xsCstInfo.updateXsCstAndInsertOtherInfo(newCstInfo);
			log.info(cstInfo.getMobileTel()+"===="+cstInfo.getHomeTel()+"===="+cstInfo.getOfficeTel()+"===="+cstInfo.getFax());
			if(bean.getMobileTel()!=null && !"".equals(bean.getMobileTel())){
				ZsBusiCustomLogTable vo = new ZsBusiCustomLogTable();
				if(cstInfo.getMobileTel()!=null && !"".equals(cstInfo.getMobileTel())){
					if(!cstInfo.getMobileTel().equals(bean.getMobileTel())){
						vo.setContent("���ֻ�����"+cstInfo.getMobileTel()+"�޸�Ϊ"+bean.getMobileTel());
						vo.setReason("�ֻ���");
						vo.setOperator(zygw);
						vo.setOperDate(followDate);
						vo.setLogType("1");
						vo.setCusId(cstInfo.getCstGuid());
						vo.setBelongSys("xs");
						vo.setProjectId(projGuid);
						xsCstInfo.insertXsCstModifyLog(vo);
					}
				}else{
					vo.setContent("���������ֻ�����Ϊ"+bean.getMobileTel());
					vo.setReason("�����ֻ���");
					vo.setOperator(zygw);
					vo.setOperDate(followDate);
					vo.setLogType("1");
					vo.setCusId(cstInfo.getCstGuid());
					vo.setBelongSys("xs");
					vo.setProjectId(projGuid);
					xsCstInfo.insertXsCstModifyLog(vo);
				}
				
			}
			if(bean.getHomeTel()!=null && !"".equals(bean.getHomeTel())){
				ZsBusiCustomLogTable vo = new ZsBusiCustomLogTable();
				if(cstInfo.getHomeTel()!=null && !"".equals(cstInfo.getHomeTel())){
					if(!cstInfo.getHomeTel().equals(bean.getHomeTel())){
						vo.setContent("����ͥ����"+cstInfo.getHomeTel()+"�޸�Ϊ"+bean.getHomeTel());
						vo.setReason("��ͥ��");
						vo.setOperator(zygw);
						vo.setOperDate(followDate);
						vo.setLogType("1");
						vo.setCusId(cstInfo.getCstGuid());
						vo.setBelongSys("xs");
						vo.setProjectId(projGuid);
						xsCstInfo.insertXsCstModifyLog(vo);
					}
				}else{
					vo.setContent("����������ͥ����Ϊ"+bean.getHomeTel());
					vo.setReason("������ͥ��");
					vo.setOperator(zygw);
					vo.setOperDate(followDate);
					vo.setLogType("1");
					vo.setCusId(cstInfo.getCstGuid());
					vo.setBelongSys("xs");
					vo.setProjectId(projGuid);
					xsCstInfo.insertXsCstModifyLog(vo);
				}	
			}
			if(bean.getOfficeTel()!=null && !"".equals(bean.getOfficeTel())){
				ZsBusiCustomLogTable vo = new ZsBusiCustomLogTable();
				if(cstInfo.getOfficeTel()!=null && !"".equals(cstInfo.getOfficeTel())){
					if(!cstInfo.getOfficeTel().equals(bean.getOfficeTel())){
						vo.setContent("���칫����"+cstInfo.getOfficeTel()+"�޸�Ϊ"+bean.getOfficeTel());
						vo.setReason("�칫��");
						vo.setOperator(zygw);
						vo.setOperDate(followDate);
						vo.setLogType("1");
						vo.setCusId(cstInfo.getCstGuid());
						vo.setBelongSys("xs");
						vo.setProjectId(projGuid);
						xsCstInfo.insertXsCstModifyLog(vo);
					}
				}else{
					vo.setContent("���������칫����Ϊ"+bean.getOfficeTel());
					vo.setReason("�����칫��");
					vo.setOperator(zygw);
					vo.setOperDate(followDate);
					vo.setLogType("1");
					vo.setCusId(cstInfo.getCstGuid());
					vo.setBelongSys("xs");
					vo.setProjectId(projGuid);
					xsCstInfo.insertXsCstModifyLog(vo);
				}	
			}
			if(bean.getFax()!=null && !"".equals(bean.getFax())){
				ZsBusiCustomLogTable vo = new ZsBusiCustomLogTable();
				if(cstInfo.getFax()!=null && !"".equals(cstInfo.getFax())){
					if(!cstInfo.getFax().equals(bean.getFax())){
						vo.setContent("���������"+cstInfo.getFax()+"�޸�Ϊ"+bean.getFax());
						vo.setReason("�����");
						vo.setOperator(zygw);
						vo.setOperDate(followDate);
						vo.setLogType("1");
						vo.setCusId(cstInfo.getCstGuid());
						vo.setBelongSys("xs");
						vo.setProjectId(projGuid);
						xsCstInfo.insertXsCstModifyLog(vo);
					}
				}else{
					vo.setContent("���������������Ϊ"+bean.getFax());
					vo.setReason("���������");
					vo.setOperator(zygw);
					vo.setOperDate(followDate);
					vo.setLogType("1");
					vo.setCusId(cstInfo.getCstGuid());
					vo.setBelongSys("xs");
					vo.setProjectId(projGuid);
					xsCstInfo.insertXsCstModifyLog(vo);
				}	
			}	
		}
		return "redirect:/mbem/mcrm/house/customer/customPage.action";
	}
	
	/**
	 * �����ͻ���Ϣ(��ʱû��)
	 * @param bean
	 * @param bean1
	 * @param bean2
	 * @param bean3
	 * @param bean4
	 * @param bean5
	 * @param bean6
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:customerManage:add")
	@RequestMapping(value="/insertXsCstInfo")
	public String insertCstInfo(XsCstTableEntity bean,XsCstAttrTableEntity bean1,XsCstAttachTableEntity bean2,XsOppTableEntity bean3,XsOpp2CstTableEntity bean4,XsOpp2RoomTableEntity bean5,XsOpp2GjjlTableEntity bean6) {

		String zygw = (String)RbacUtils.subject().getSession().getAttribute("username");
		//String zygw = "������";//������
		XsEmployeeTableEntity employee = new XsEmployeeTableEntity();
		SimpleDateFormat fm1 = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat fm2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String followDate = fm2.format(new Date());
		employee.setUserName(zygw);
		XsEmployeeTableEntity employeeInfo = xsCstInfo.getXsEmployeeInfo(employee);
		bean.setCreatedBy(zygw);
		bean.setCreatedByGuid(employee.getUserGuid());
		xsCstInfo.insertXsCstBasicInfo(bean);
		XsCstTableEntity cstInfo = xsCstInfo.getXsCstBasicInfo(bean);
		log.info("cstGuid=="+cstInfo.getCstGuid());
		bean1.setCstGuid(cstInfo.getCstGuid());
		bean2.setCstGuid(cstInfo.getCstGuid());
		bean2.setUserGuid(employeeInfo.getUserGuid());
		bean3.setCstGuid(cstInfo.getCstGuid());
		bean3.setCreatedByGuid(employeeInfo.getUserGuid());
		bean3.setCreatedBy(zygw);
		bean3.setUserGuid(employeeInfo.getUserGuid());//�봴���ߵ�����
		bean3.setZygw(zygw);
		bean3.setZygwGuid(employeeInfo.getUserGuid());
		bean3.setStatus("��ѯ");//������
		
		xsCstInfo.insertXsCstAttrInfo(bean1);
		xsCstInfo.insertXsCstAttachInfo(bean2);
		xsCstInfo.insertXsOppInfo(bean3);
		XsOppTableEntity oppInfo = xsCstInfo.getXsOppInfo(bean3);
		String oppGuid = oppInfo.getOppGuid();
		log.info("oppGuid=="+oppGuid);
		bean4.setCstGuid(cstInfo.getCstGuid());
		bean4.setOppGuid(oppGuid);
		bean5.setOppGuid(oppGuid);
		bean6.setOppGuid(oppGuid);
		bean6.setGjDate(followDate);
		bean6.setNextDate(bean6.getNextDate()+" "+fm1.format(new Date()));
		bean6.setGjrGuid(employeeInfo.getUserGuid());
		xsCstInfo.insertXsOpp2CstInfo(bean4);
		xsCstInfo.insertXsOpp2RoomInfo(bean5);
		xsCstInfo.insertXsFollowInfo(bean6);		
		return "redirect:/mbem/mcrm/house/customer/customPage.action";
	}
	/**
	 * ȡ�������ͻ�����
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:customerManage:add")
	@RequestMapping(value="/cancleAddCst")
	public String cancleAddCst() {
		log.info("cancleAddCst");
		return "/mbem/mcrm/house/customer/customPage";
	}
	/**
	 * ��ȡ�ͻ��б�
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:customerManage:customer")
	@RequestMapping(value="/customPage")
	public String getXsCstInfo() {
		log.info("getXsCstInfoList");
		/*XsCstSearchOption employee = new XsCstSearchOption();
		User user = (User)RbacUtils.subject().getSession().getAttribute("user");
		String userGUID=user.getExtInfo("xs_userGuid");
		employee.setUserGUID(userGUID);*/
		httpSession.setAttribute("xsAjaxUrl", "/mbem/mcrm/house/customer/loadXsCustomList.action");
		return "/mbem/mcrm/house/customer/customPage";
	}
	/**
	 * 
	 * @param page
	 * @param pageLen
	 * @param type
	 * @param sort
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:customerManage:customer")
	@ResponseBody
	@RequestMapping(value="/loadXsCustomList",method = RequestMethod.POST)
	public Map<String,Object> loadXsCustomList(int page,int pageLen,String type,String sort){
		log.info("loadXsCustomList");
		log.info("type:"+type+" sort:"+sort);
		XsCstSearchOption searchInfo = new XsCstSearchOption();
		List<XsCstInfo> cstList = new ArrayList<XsCstInfo>();
		User user = (User)RbacUtils.subject().getSession().getAttribute("user");
		String userGUID=user.getExtInfo("xs_userGuid");
		String projGuid = user.getExtInfo("projGuid");
		int cstNum = 0;
		if(userGUID!=null && !"".equals(userGUID)) {
			searchInfo.setIsAll(type);
			searchInfo.setSortWay(sort);
			searchInfo.setUserGUID(userGUID);
			searchInfo.setStartIndex((page-1)*pageLen);
			searchInfo.setLength(pageLen);
			searchInfo.setProjGUID(projGuid);
			cstList = xsCstInfo.getXsCstListByEmployee(searchInfo);
			if(page == 1) {
				cstNum = xsCstInfo.getXsCstCountByEmployee(searchInfo);
			}
		}
		log.info("cstNum:"+cstNum);
		Map<String,Object> cstInfo = new HashMap<String,Object>();
		cstInfo.put("cstNum", cstNum);
		cstInfo.put("cstList", cstList);
		return cstInfo;
	}
	
	/**
	 * ���ݿͻ����ƻ�绰��ѯ�ͻ�
	 * @param bean
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:customerManage:customer")
	@ResponseBody
	@RequestMapping(value="/loadXsCustomListByNameOrTel",method = RequestMethod.POST)
	public Map<String,Object> loadXsCustomListByNameOrTel(int page,int pageLen,String sort,String type){
		List<XsCstInfo> cstList = new ArrayList<XsCstInfo>();
		XsCstSearchOption searchInfo = new XsCstSearchOption();
		User user = (User)RbacUtils.subject().getSession().getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		String userGuid = user.getExtInfo("xs_userGuid");
		String nameOrTel = (String) httpSession.getAttribute("nameOrTel");
		searchInfo.setStartIndex((page-1)*pageLen);
		searchInfo.setLength(pageLen);
		searchInfo.setUserGUID(userGuid);
		if(nameOrTel !=null && !"".equals(nameOrTel)){//���ݿͻ����ƺ͵绰ģ����ѯ
			boolean isNum = nameOrTel.matches("[0-9]+");
			if(isNum){
				searchInfo.setMobileTel(nameOrTel);
			}else{
				searchInfo.setCstName(nameOrTel);;
			}
		}
		log.info("name:"+searchInfo.getCstName()+"   tel:"+searchInfo.getMobileTel());
		searchInfo.setSortWay(sort);
		searchInfo.setIsAll(type);
		searchInfo.setProjGUID(projGuid);
		cstList = xsCstInfo.getXsCstListByEmployee(searchInfo);
		Map<String,Object> cstInfo = new HashMap<String,Object>();
		int cstNum = 0;
		if(page == 1) {
			cstNum = xsCstInfo.getXsCstCountByEmployee(searchInfo);
		}
		cstInfo.put("cstNum",cstNum);
		cstInfo.put("cstList", cstList);
		return cstInfo;
	}
	/**
	 * 
	 * @param nameOrTel
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:customerManage:customer")
	@RequestMapping(value="/getXsCstInfoByNameOrTel")
	public String getXsCstInfoByNameOrTel(String nameOrTel) {
		log.info("getXsCstInfoByNameOrTel");
		XsCstSearchOption searchInfo = new XsCstSearchOption();
		User user = (User)RbacUtils.subject().getSession().getAttribute("user");
		if(nameOrTel !=null && !"".equals(nameOrTel)){//���ݿͻ����ƺ͵绰ģ����ѯ
			boolean isNum = nameOrTel.matches("[0-9]+");
			if(isNum){
				searchInfo.setMobileTel(nameOrTel);
				searchInfo.setCstName("");
			}else{
				searchInfo.setCstName(nameOrTel);
			}
		}
		else {
			searchInfo.setCstName("");
		}
		String userGuid = user.getExtInfo("xs_userGuid");
		searchInfo.setUserGUID(userGuid);
		httpSession.setAttribute("nameOrTel", nameOrTel);
		httpSession.setAttribute("xsAjaxUrl", "/mbem/mcrm/house/customer/loadXsCustomListByNameOrTel.action");
		return "/mbem/mcrm/house/customer/customPage";
	}
	/**
	 * ���ݿͻ�id��ѯ�ͻ���Ϣ��������Ϣ
	 * @param cstId
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:customerManage:personal")
	@RequestMapping(value="/getXsCstAndFollowInfoByCstGuid")
	public String getXsCstAndFollowInfoByCstGuid(@RequestParam(value="cstGuid",required=true) String cstGuid,@RequestParam(value="oppGuid",required=false)String oppGuid,Model model) {
		log.info("oppGuid=============="+oppGuid);
		List<String> statusList = new ArrayList<String>();
		XsCstSearchOption cst = new XsCstSearchOption();
		List<XsCstInfo> followInfoList = new ArrayList<XsCstInfo>();
		List<XsCstInfo> cstInfoList = new ArrayList<XsCstInfo>();
		Session session = RBACSubject.getSecurityUtils().getSession();
		String zygw = (String)session.getAttribute("username");
		int visitTimes;
		String firstVisitTime;	
		if(cstGuid == null || cstGuid.equals("")) {
			cstGuid = (String)httpSession.getAttribute("curCstId");
		}else {
			httpSession.setAttribute("curCstId", cstGuid);//���ÿͻ�id������httpSession��
		}//2016.3.11�����޸Ŀͻ���Ϣʱ�ĺ���
		cst.setCstGuid(cstGuid);
		cst.setZygw(zygw);
		cst.setOppGUID(oppGuid);
		followInfoList = xsCstInfo.getXsCstFollowInfoByCstGuid(cst);
		log.info("size"+followInfoList.size());
		
		for(XsCstInfo followInfo:followInfoList) {
			String gjfs = followInfo.getXsOpp2Gjjl().getGjfs();
			if(gjfs == null || "".equals(gjfs))
				continue;
			if(gjfs.indexOf("���") != -1||gjfs.indexOf("ȥ��") != -1) {
				statusList.add("ȥ��");
				break;
			}
		}
		for(XsCstInfo followInfo1:followInfoList) {
			String gjfs = followInfo1.getXsOpp2Gjjl().getGjfs();
			if(gjfs == null || "".equals(gjfs))
				continue;
			if(gjfs.indexOf("����") != -1) {
				statusList.add("����");
				break;
			}
		}
		for(XsCstInfo followInfo2:followInfoList) {
			String gjfs = followInfo2.getXsOpp2Gjjl().getGjfs();
			String status = followInfo2.getXsOpp2Gjjl().getStatus();
			if(status == null || status.equals("")) {
				if(gjfs == null || "".equals(gjfs))
					continue;
				if(gjfs.indexOf("�ֳ��Ӵ�") != -1 || gjfs.indexOf("����") != -1) {
					statusList.add("����");
					break;
				}
			}
			if(gjfs == null || "".equals(gjfs))
				continue;
			if((gjfs.indexOf("�ֳ��Ӵ�") != -1 ||gjfs.indexOf("����") != -1)&& status.indexOf("��ѯ") != -1) {
				statusList.add("����");
				break;
			}
		}
		for(XsCstInfo followInfo3:followInfoList) {
			String status = followInfo3.getXsOpp2Gjjl().getStatus();
			if(status == null || status.equals(""))
				continue;
			if(status.indexOf("����") != -1) {
				statusList.add("����");
				break;
			}
		}
		for(XsCstInfo followInfo4:followInfoList) {
			String status = followInfo4.getXsOpp2Gjjl().getStatus();
			if(status == null || status.equals(""))
				continue;
			if(status.indexOf("�Ϲ�") != -1) {
				statusList.add("�Ϲ�");
				break;
			}
		}
		for(XsCstInfo followInfo5:followInfoList) {
			String status = followInfo5.getXsOpp2Gjjl().getStatus();
			if(status == null || status.equals(""))
				continue;
			if(status.indexOf("ǩԼ") != -1) {
				statusList.add("ǩԼ");
				break;
			}
		}
		if(oppGuid != null && !oppGuid.equals("")){
			cst.setOppGUID(oppGuid);
		}
		cstInfoList = xsCstInfo.getXsCstInfoByCstGuid(cst);
		log.info(cstInfoList.size());
		for(XsCstInfo cstInfo:cstInfoList) {
			String zyNum = cstInfo.getXsCstAttr().getZyNum()==null?"":cstInfo.getXsCstAttr().getZyNum();
			String val = zyNum.substring(0,zyNum.indexOf("."));
			cstInfo.getXsCstAttr().setZyNum(val);//ȥ����ҵ�����е�С������
		}
		String estCloseDate = cstInfoList.get(0).getXsOpp().getEstCloseDate();
		if(estCloseDate != null && !estCloseDate.equals("") && Integer.parseInt(estCloseDate.substring(0, 4))>2000){
			model.addAttribute("isInvalid", "1");
		}else {
			model.addAttribute("isInvalid", "0");
		}
		visitTimes = xsCstInfo.getVisitTimes(cst);
		firstVisitTime = xsCstInfo.getFirstVisitTime(cst);
		httpSession.setAttribute("statusList", statusList);
		httpSession.setAttribute("nextFollowDate", cstInfoList.get(0).getXsOpp().getNextDate());
		httpSession.setAttribute("visitTimes", visitTimes);
		httpSession.setAttribute("firstVisitTime", firstVisitTime);
		httpSession.setAttribute("cstInfoList", cstInfoList);
		httpSession.setAttribute("followInfoList", followInfoList);
		model.addAttribute("oppGuid", oppGuid);
		model.addAttribute("cstGuid", cstGuid);
		return "/mbem/mcrm/house/customer/personal";
	}
	@RequiresPermissions(value="page:saleMobile:customerManage:personal")
	@RequestMapping(value="/getXsCstAndFollowInfoByCstGuidAndZygw_Count")
	public String getXsCstAndFollowInfoByCstGuidAndZygw_Count(@RequestParam(value="cusId",required=true) String cstId,@RequestParam(value="zygw",required=true) String zygw) {
	
		try {
			String zygw1=URLDecoder.decode(zygw, "utf-8");
			//log.info(zygw1);
			String zygw2=new String(zygw.getBytes(), "utf-8");
			String zygw3=new String(zygw.getBytes(), "gbk");
			//log.info(zygw2);
			//log.info(zygw3);
			String zygw4 = new String(zygw.getBytes("ISO-8859-1"),"utf-8");
			//log.info(zygw4);
			String zygw5 = new String(zygw.getBytes("ISO-8859-1"),"gbk");
			//log.info(zygw5);
			String zygw6 = new String(zygw.getBytes("gbk"),"utf-8");
			String zygw7 = new String(zygw.getBytes("gbk"),"gbk");
		} catch (UnsupportedEncodingException e) {
			log.info(e);
		}
		log.info("getXsCstAndFollowInfoByCstGuid,cstId="+cstId+" zygw "+zygw);
		if(cstId==null || cstId.equals(""))
			log.info("cstId����Ϊ��");
		List<String> statusList = new ArrayList<String>();
		XsCstSearchOption cst = new XsCstSearchOption();
		List<XsCstInfo> followInfoList = new ArrayList<XsCstInfo>();
		List<XsCstInfo> cstInfoList = new ArrayList<XsCstInfo>();
		int visitTimes;
		String firstVisitTime;		
		httpSession.setAttribute("curCstId", cstId);//���ÿͻ�id������httpSession��
		cst.setCstGuid(cstId);
		cst.setZygw(zygw);
		followInfoList = xsCstInfo.getXsCstFollowInfoByCstGuid(cst);
		for(XsCstInfo followInfo:followInfoList) {
			String gjfs = followInfo.getXsOpp2Gjjl().getGjfs();
			if(gjfs.indexOf("���") != -1||gjfs.indexOf("ȥ��") != -1) {
				statusList.add("ȥ��");
				break;
			}
		}
		for(XsCstInfo followInfo1:followInfoList) {
			String gjfs = followInfo1.getXsOpp2Gjjl().getGjfs();
			if(gjfs.indexOf("����") != -1) {
				statusList.add("����");
				break;
			}
		}
		for(XsCstInfo followInfo2:followInfoList) {
			String gjfs = followInfo2.getXsOpp2Gjjl().getGjfs();
			String status = followInfo2.getXsOpp2Gjjl().getStatus();
			if(status == null || status.equals("")) {
				if(gjfs.indexOf("�ֳ��Ӵ�") != -1 || gjfs.indexOf("����") != -1) {
					statusList.add("����");
					break;
				}
			}
			if((gjfs.indexOf("�ֳ��Ӵ�") != -1 ||gjfs.indexOf("����") != -1)&& status.indexOf("��ѯ") != -1) {
				statusList.add("����");
				break;
			}
		}
		for(XsCstInfo followInfo3:followInfoList) {
			String status = followInfo3.getXsOpp2Gjjl().getStatus();
			if(status == null || status.equals(""))
				continue;
			if(status.indexOf("����") != -1) {
				statusList.add("����");
				break;
			}
		}
		for(XsCstInfo followInfo4:followInfoList) {
			String status = followInfo4.getXsOpp2Gjjl().getStatus();
			if(status == null || status.equals(""))
				continue;
			if(status.indexOf("�Ϲ�") != -1) {
				statusList.add("�Ϲ�");
				break;
			}
		}
		for(XsCstInfo followInfo5:followInfoList) {
			String status = followInfo5.getXsOpp2Gjjl().getStatus();
			if(status == null || status.equals(""))
				continue;
			if(status.indexOf("ǩԼ") != -1) {
				statusList.add("ǩԼ");
				break;
			}
		}
		cstInfoList = xsCstInfo.getXsCstInfoByCstGuid(cst);
		log.info(cstInfoList.size());
		for(XsCstInfo cstInfo:cstInfoList) {
			String zyNum = cstInfo.getXsCstAttr().getZyNum()==null?"":cstInfo.getXsCstAttr().getZyNum();
			String val = zyNum.substring(0,zyNum.indexOf("."));
			cstInfo.getXsCstAttr().setZyNum(val);//ȥ����ҵ�����е�С������
		}
		visitTimes = xsCstInfo.getVisitTimes(cst);
		firstVisitTime = xsCstInfo.getFirstVisitTime(cst);
		httpSession.setAttribute("statusList", statusList);
		httpSession.setAttribute("nextFollowDate", cstInfoList.get(0).getXsOpp().getNextDate());
		httpSession.setAttribute("visitTimes", visitTimes);
		httpSession.setAttribute("firstVisitTime", firstVisitTime);
		httpSession.setAttribute("cstInfoList", cstInfoList);
		httpSession.setAttribute("followInfoList", followInfoList);
		return "/mbem/mcrm/house/customer/personal";
	}
	
	/**
	 * ����ͻ�����ҳ��
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:customerManage:edit")
	@RequestMapping(value="/updateCustom")
	public String updateXsCstInfo(@RequestParam(value="oppGuid",required=true)String oppGuid,@RequestParam(value="cstGuid",required=false)String cstGuid,Model model) {
		log.info("updateXsCstInfo");
		String optionStr = "";
		String []options = {};
		String valueStr = "";
		String []values = {};
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		List<XsCstInfo> cstListInfo = new ArrayList<XsCstInfo>();
		XsCstSearchOption cstSearchInfo = new XsCstSearchOption();
		XsCstInfo cstInfo = new XsCstInfo();
		cstSearchInfo.setOppGUID(oppGuid);
		cstListInfo = xsCstInfo.getXsCstInfoByCstGuid(cstSearchInfo);
		cstInfo = cstListInfo.get(0);
		
		SearchOptionEntity optionSearchInfo = new SearchOptionEntity();
		XsAddAndUpdateCstOption hh = new XsAddAndUpdateCstOption();
		List<Map<String,String>> cstInfoList = new ArrayList<Map<String,String>>();
		optionSearchInfo.setModule("xsaddcst");
		List<SearchOptionEntity> showOptionList = optionListService.getOptionListByModule(optionSearchInfo);

		for(SearchOptionEntity showOption:showOptionList) {
			String name = showOption.getComment();
			if(showOption.isEdit() == false) {
				continue;
			}
			log.info("name:"+name);
			String mainMedia = "";
			if(showOption.getOptionType().equals("text")){
				switch(name) {
					case "cstName":
						valueStr = cstInfo.getXsCst().getCstName()==null?"":cstInfo.getXsCst().getCstName();
						break;
					case "mobileTel":
						log.info("mobileTel");
						valueStr = cstInfo.getXsCst().getMobileTel()==null?"":cstInfo.getXsCst().getMobileTel();
						httpSession.setAttribute("mobileTel", valueStr.equals("")?"null":valueStr);
						break;
					case "homeTel":
						valueStr = cstInfo.getXsCst().getHomeTel()==null?"":cstInfo.getXsCst().getHomeTel();
						if(valueStr.equals(""))
							continue;
						break;
					case "officeTel":
						valueStr = cstInfo.getXsCst().getOfficeTel()==null?"":cstInfo.getXsCst().getOfficeTel();
						if(valueStr.equals(""))
							continue;
						break;
					case "fax":
						valueStr = cstInfo.getXsCst().getFax()==null?"":cstInfo.getXsCst().getFax();
						if(valueStr.equals(""))
							continue;
						break;
					case "description":
						valueStr = cstInfo.getXsCst().getDescription()==null?"":cstInfo.getXsCst().getDescription();
						break;	
					case "cardId":
						valueStr = cstInfo.getXsCst().getCardId()==null?"":cstInfo.getXsCst().getCardId();
						break;
					case "zyNum":
						String zyNum = cstInfo.getXsCstAttr().getZyNum()==null?"":cstInfo.getXsCstAttr().getZyNum();
						valueStr = zyNum.substring(0,zyNum.indexOf("."));//ȥ����ҵ������С����������
						break;
					case "dfNum":
						if(cstInfo.getXsOpp().getDfNum() == null) {
							valueStr = "";
						}
						else {
							valueStr = cstInfo.getXsOpp().getDfNum().toString();
						}	
						break;
					case "nextDate":
						valueStr = cstInfo.getXsOpp().getNextDate()==null?"":cstInfo.getXsOpp().getNextDate();
						break;
				}
				showOption.setContent(valueStr);	
			}else if(showOption.getOptionType().equals("radio") || showOption.getOptionType().equals("checkbox")) {
				switch(name) {
					case "gender":
						valueStr = cstInfo.getXsCst().getGender()==null?"":cstInfo.getXsCst().getGender();
						break;
					case "ageGroup":
						valueStr = cstInfo.getXsCstAttr().getAgeGroup()==null?"":cstInfo.getXsCstAttr().getAgeGroup();
						break;
					case "gfyt":
						valueStr = cstInfo.getXsOpp().getGfyt()==null?"":cstInfo.getXsOpp().getGfyt();
						break;
					case "yxArea":
						valueStr = cstInfo.getXsOpp2Room().getYxArea()==null?"":cstInfo.getXsOpp2Room().getYxArea();
						break;
					case "gzfm1":
						valueStr = (cstInfo.getXsOpp().getGzfm1()==null?"":cstInfo.getXsOpp().getGzfm1())+(cstInfo.getXsOpp().getGzfm2()==null?"":cstInfo.getXsOpp().getGzfm2())+(cstInfo.getXsOpp().getGzfm3()==null?"":cstInfo.getXsOpp().getGzfm3());
						break;
					case "workArea":
						valueStr = cstInfo.getXsCstAttr().getWorkArea()==null?"":cstInfo.getXsCstAttr().getWorkArea();
						break;
					case "homeArea":
						valueStr = cstInfo.getXsCstAttr().getHomeArea()==null?"":cstInfo.getXsCstAttr().getHomeArea();
						break;
					case "work":
						valueStr = cstInfo.getXsCstAttr().getWork()==null?"":cstInfo.getXsCstAttr().getWork();
						break;
					case "competitor":
						valueStr = cstInfo.getXsOpp().getCompetitor()==null?"":cstInfo.getXsOpp().getCompetitor();
						break;
					case "gfys":
						valueStr = cstInfo.getXsOpp().getGfys()==null?"":cstInfo.getXsOpp().getGfys();
						break;
					case "yxFangXing":
						valueStr = cstInfo.getXsOpp2Room().getYxFangXing()==null?"":cstInfo.getXsOpp2Room().getYxFangXing();
						break;
					case "family":
						valueStr = cstInfo.getXsCstAttr().getFamily()==null?"":cstInfo.getXsCstAttr().getFamily();
						break;
					case "yxYeTai":
						valueStr = cstInfo.getXsOpp2Room().getYxYeTai()==null?"":cstInfo.getXsOpp2Room().getYxYeTai();
						break;
					case "subMediaName":
						valueStr = cstInfo.getXsOpp().getSubMediaName()==null?"":cstInfo.getXsOpp().getSubMediaName();
						mainMedia = cstInfo.getXsOpp().getMainMediaName()==null?"":cstInfo.getXsOpp().getMainMediaName();
						break;
					case "estCloseDate":
						valueStr = cstInfo.getXsOpp().getEstCloseDate();
						if(valueStr != null && !valueStr.equals("")&& Integer.parseInt(valueStr.substring(0, 4))>2000) {
							valueStr = "��Ч";
						}
						else {
							valueStr = "��Ч";
						}
				}
				values = valueStr.split(",");
				optionStr = showOption.getContent();
				options = optionStr.split(",");
				if(mainMedia != null && !mainMedia.equals("")) {
					for(int i=0; i<options.length; i++) {
						if(options[i].indexOf(":") != -1) {
							if(mainMedia.equals(options[i].substring(0, options[i].indexOf(":")))) {
								String []subOptions = {};
								subOptions = options[i].substring(options[i].indexOf(":")+1).split("��");
								for(int j=0; j<subOptions.length; j++) {
									if(values[0].equals(subOptions[j])) {
										subOptions[j] = "y"+subOptions[j];
										break;
									}
								}
								options[i] = options[i].substring(0,options[i].indexOf(":")+1)+StringUtils.join(subOptions, "��");
							}
						}else{
							if(mainMedia.equals(options[i])) {
								options[i] = "y"+options[i];
								break;
							}
						}
					}
				}else{
					for(int i=0; i<values.length; i++) {
						for(int j=0; j<options.length; j++) {
							if(values[i].equals(options[j])){
								options[j] = "y"+options[j];
								break;
							}
						}
					}
				}
				showOption.setContent(StringUtils.join(options,","));
			}else if(showOption.getOptionType().equals("date")) {
				switch(name){
					case "nextDate":
						valueStr = cstInfo.getXsOpp().getNextDate()==null?"":cstInfo.getXsOpp().getNextDate();
					break;
				}
				showOption.setContent(valueStr);
			}else if(showOption.getOptionType().equals("select")) {
				switch(name) {
					case "isInvalid":
						valueStr = cstInfo.getXsOpp().getClosedOn()==null?"��Ч":"��Ч";
					break;
				}
				showOption.setContent(valueStr);
			}
			
			TagOption to = hh.generateHtmlEntity(showOption);
			Map<String,String> cstOption = hh.jointHtmlStr(to);
			cstInfoList.add(cstOption);
		}
		httpSession.setAttribute("updateCstInfoList", cstInfoList);
		String invalidReason=cstInfo.getXsOpp().getStatusReason();
		model.addAttribute("cstGuid", cstGuid);
		model.addAttribute("oppGuid", oppGuid);
		model.addAttribute("mainMediaName", cstInfo.getXsOpp().getMainMediaName());
		model.addAttribute("invalidReason", invalidReason);
		model.addAttribute("projGuid", projGuid);
		return "/mbem/mcrm/house/customer/updateCustom";
	}
	/**
	 * ȡ���ͻ�����
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:customerManage:edit")
	@RequestMapping(value="/cancleUpdateCst")
	public String cancleUpdateCst() {
		log.info("cancleUpdateCst");
		return "/mbem/mcrm/house/customer/personal";
	}
	/**
	 * ����ͻ�������Ϣ
	 * @param bean
	 * @param bean1
	 * @param bean2
	 * @param bean3
	 * @param bean4
	 * @param bean5
	 * @param bean6
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:customerManage:edit")
	@RequestMapping(value="/insertCstUpdateInfo")
	public String insertCstUpdateInfo(@RequestParam(value="oppGuid",required=true)String oppGuid,@RequestParam(value="cstGuid",required=true)String cstGuid,String oldName,String oldTel,String invalidReason,XsCstTableEntity bean,XsCstAttrTableEntity bean1,XsOppTableEntity bean2,XsOpp2RoomTableEntity bean3,Model model) {
		log.info(invalidReason);
		String cusId = httpSession.getAttribute("curCstId").toString();
		XsCstSearchOption cst = new XsCstSearchOption();
		XsCstInfo cstUpdateInfo = new XsCstInfo();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String zygw = (String)RbacUtils.subject().getSession().getAttribute("username");
		String projGuid = user.getExtInfo("projGuid");
		
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String modifyDate = fm.format(new Date());
		cst.setOppGUID(oppGuid);
		List<XsCstInfo> cstInfo = xsCstInfo.getXsCstInfoByCstGuid(cst);
		String _homeTel=bean.getHomeTel();
		String _officeTel=bean.getOfficeTel();
		String _fax=bean.getFax();
		List<XsCstInfo> cstList = new ArrayList<XsCstInfo>();
		XsCstSearchOption option = new XsCstSearchOption();
		option.setStartIndex(0);
		option.setLength(1);
		option.setSortWay("1");
		option.setIsAll("1");
		option.setProjGUID(projGuid);
		
		
		if(bean2.getEstCloseDate().equals("��Ч")){
			String status = cstInfo.get(0).getXsOpp().getStatus();
			if(status.equals("�Ϲ�") || status.equals("ǩԼ")){
				log.info("�Ϲ���ǩԼ�ͻ���������Ϊ��Ч��");
				return "redirect:/mbem/mcrm/house/customer/customPage.action";
			}
		}//�Ϲ���ǩԼ�ͻ��������Ϊ��Ч 2016-8-14
		
		if(bean.getMobileTel()!=null && !bean.getMobileTel().equals("")){
			//�Ƚ��Ƿ��޸�
			if(!bean.getMobileTel().equals(cstInfo.get(0).getXsCst().getMobileTel())){
				option.setMobileTel(bean.getMobileTel());
				cstList=xsCstInfo.getXsCstListByEmployee(option);
			    if(cstList.size() != 0) {
			    	log.info("�ֻ����Ѿ�¼���");
			    	return "redirect:/mbem/mcrm/house/customer/customPage.action";
			    }
			}
			//����޸ģ������
			//���û�й���������
			
		}
		if(bean.getHomeTel()!=null && !bean.getHomeTel().equals("")){
			if(!bean.getHomeTel().equals(cstInfo.get(0).getXsCst().getHomeTel())){
				option.setMobileTel(bean.getHomeTel());
				cstList=xsCstInfo.getXsCstListByEmployee(option);
			    if(cstList.size() != 0) {
			    	log.info("��ͥ���Ѿ�¼���");
			    	return "redirect:/mbem/mcrm/house/customer/customPage.action";
			    } 
			}
			 	
		}
		if(bean.getOfficeTel()!=null && !bean.getOfficeTel().equals("")){
			if(!bean.getOfficeTel().equals(cstInfo.get(0).getXsCst().getOfficeTel())){
				option.setMobileTel(bean.getOfficeTel());
				cstList=xsCstInfo.getXsCstListByEmployee(option);
			    if(cstList.size() != 0) {
			    	log.info("�칫���Ѿ�¼���");
			    	return "redirect:/mbem/mcrm/house/customer/customPage.action";
			    } 
			}
				
		}
		if(bean.getFax()!=null && !bean.getFax().equals("")){
			if(!bean.getFax().equals(cstInfo.get(0).getXsCst().getFax())){
				option.setMobileTel(bean.getFax());
				cstList=xsCstInfo.getXsCstListByEmployee(option);
			    if(cstList.size() != 0) {
			    	log.info("������Ѿ�¼���");
			    	return "redirect:/mbem/mcrm/house/customer/customPage.action";
			    }
			}
			
		}
		if("".equals(cstInfo.get(0).getXsCst().getHomeTel()) || cstInfo.get(0).getXsCst().getHomeTel()==null){
			if(_homeTel !=null && !"".equals(_homeTel)  ){
					ZsBusiCustomLogTable vo = new ZsBusiCustomLogTable();
					vo.setContent("����ͥ����Ϊ"+_homeTel);
					vo.setReason("��ͥ��");
					vo.setOperator(zygw);
					vo.setOperDate(modifyDate);
					vo.setLogType("1");
					vo.setCusId(cusId);
					vo.setBelongSys("xs");
					vo.setProjectId(projGuid);
					xsCstInfo.insertXsCstModifyLog(vo);
			}
		}else if(!"".equals(cstInfo.get(0).getXsCst().getHomeTel()) &&  !cstInfo.get(0).getXsCst().getHomeTel().equals(bean.getHomeTel())){
				ZsBusiCustomLogTable vo = new ZsBusiCustomLogTable();
				vo.setContent("����ͥ����"+cstInfo.get(0).getXsCst().getHomeTel()+"���Ϊ"+_homeTel);
				vo.setReason("��ͥ��");
				vo.setOperator(zygw);
				vo.setOperDate(modifyDate);
				vo.setLogType("1");
				vo.setCusId(cusId);
				vo.setBelongSys("xs");
				vo.setProjectId(projGuid);
				xsCstInfo.insertXsCstModifyLog(vo);
		}
		if("".equals(cstInfo.get(0).getXsCst().getOfficeTel()) || cstInfo.get(0).getXsCst().getOfficeTel()==null){
			if(_officeTel !=null && !"".equals(_officeTel)){
					ZsBusiCustomLogTable vo = new ZsBusiCustomLogTable();
					vo.setContent("���칫����Ϊ"+_officeTel);
					vo.setReason("�칫��");
					vo.setOperator(zygw);
					vo.setOperDate(modifyDate);
					vo.setLogType("1");
					vo.setCusId(cusId);
					vo.setBelongSys("xs");
					vo.setProjectId(projGuid);
					xsCstInfo.insertXsCstModifyLog(vo);
			}
		}else if(!"".equals(cstInfo.get(0).getXsCst().getOfficeTel()) && !cstInfo.get(0).getXsCst().getOfficeTel().equals(bean.getOfficeTel())){
				ZsBusiCustomLogTable vo = new ZsBusiCustomLogTable();
				vo.setContent("���칫����"+cstInfo.get(0).getXsCst().getOfficeTel()+"���Ϊ"+_officeTel);
				vo.setReason("��ͥ��");
				vo.setOperator(zygw);
				vo.setOperDate(modifyDate);
				vo.setLogType("1");
				vo.setCusId(cusId);
				vo.setBelongSys("xs");
				vo.setProjectId(projGuid);
				xsCstInfo.insertXsCstModifyLog(vo);
		}
		if("".equals(cstInfo.get(0).getXsCst().getFax()) || cstInfo.get(0).getXsCst().getFax()==null){
			if(_fax !=null && !"".equals(_fax)){
					ZsBusiCustomLogTable vo = new ZsBusiCustomLogTable();
					vo.setContent("���������Ϊ"+_fax);
					vo.setReason("�����");
					vo.setOperator(zygw);
					vo.setOperDate(modifyDate);
					vo.setLogType("1");
					vo.setCusId(cusId);
					vo.setBelongSys("xs");
					vo.setProjectId(projGuid);
					xsCstInfo.insertXsCstModifyLog(vo);
			}
		}else if(!"".equals(cstInfo.get(0).getXsCst().getFax()) && !cstInfo.get(0).getXsCst().getFax().equals(bean.getFax())){
				ZsBusiCustomLogTable vo = new ZsBusiCustomLogTable();
				vo.setContent("���������"+cstInfo.get(0).getXsCst().getFax()+"���Ϊ"+_fax);
				vo.setReason("�����");
				vo.setOperator(zygw);
				vo.setOperDate(modifyDate);
				vo.setLogType("1");
				vo.setCusId(cusId);
				vo.setBelongSys("xs");
				vo.setProjectId(projGuid);
				xsCstInfo.insertXsCstModifyLog(vo);
		}
		String defaultTel = bean.getDefaultTel();
		String phoneNo = bean.getMobileTel();
		String defaultTelNo = "";
		
		switch(defaultTel){//���
			case "homeTel":
				defaultTelNo = bean.getHomeTel();
				bean.setMobileTel(defaultTelNo);
				bean.setHomeTel(phoneNo);
				break;
			case "officeTel":
				defaultTelNo = bean.getOfficeTel();
				bean.setMobileTel(defaultTelNo);
				bean.setOfficeTel(phoneNo);
				break;
			case "fax":
				defaultTelNo = bean.getFax();
				bean.setMobileTel(defaultTelNo);
				bean.setFax(phoneNo);
				break;
			case "mobileTel":
				defaultTelNo = bean.getMobileTel();
				break;
		}//2016.3.10
		bean.setCstGuid(cusId);
		bean1.setCstGuid(cusId);
		if(bean2.getEstCloseDate().equals("��Ч")) {
			bean2.setEstCloseDate(modifyDate);
			bean2.setStatusReason(invalidReason);
			bean2.setDescription("@"+invalidReason);
		}
		if(bean2.getEstCloseDate().equals("��Ч")) {
			bean2.setEstCloseDate("");
			bean2.setStatusReason("0");//2016-7-7
		}
		bean2.setOppGuid(oppGuid);
		bean3.setOppGuid(oppGuid);
		cstUpdateInfo.setXsCst(bean);
		cstUpdateInfo.setXsCstAttr(bean1);
		cstUpdateInfo.setXsOpp(bean2);
		cstUpdateInfo.setXsOpp2Room(bean3);
		xsCstInfo.updateXsCstInfo(cstUpdateInfo);

		ZsBusiCustomLogTable vo = new ZsBusiCustomLogTable();
		if(!oldName.equals(bean.getCstName()) || !oldTel.equals(defaultTelNo)) {
			if(!oldName.equals(bean.getCstName())&&!oldTel.equals(defaultTelNo)){
				vo.setContent("��������"+oldName+"�޸�Ϊ"+bean.getCstName()+","+"���ֻ�����"+oldTel+"�޸�Ϊ"+defaultTelNo);
				vo.setReason("�����ֻ���");
			}else if(!oldName.equals(bean.getCstName())) {
				vo.setContent("��������"+oldName+"�޸�Ϊ"+bean.getCstName());
				vo.setReason("����");
			}else if(!oldTel.equals(defaultTelNo)) {
				vo.setContent("���ֻ�����"+oldTel+"�޸�Ϊ"+defaultTelNo);
				vo.setReason("�ֻ���");
			}
			vo.setOperator(zygw);
			vo.setOperDate(modifyDate);
			vo.setLogType("1");
			vo.setCusId(cusId);
			vo.setBelongSys("xs");
			vo.setProjectId(projGuid);
			xsCstInfo.insertXsCstModifyLog(vo);
		}
		ZsBusiCustomLogTable voo = new ZsBusiCustomLogTable();
		switch(defaultTel){
			case "homeTel":
				voo.setContent("����ͥ����"+defaultTelNo+"���Ϊ"+bean.getHomeTel());
				voo.setReason("��ͥ��");
				voo.setOperator(zygw);
				voo.setOperDate(modifyDate);
				voo.setLogType("1");
				voo.setCusId(cusId);
				voo.setBelongSys("xs");
				voo.setProjectId(projGuid);
				xsCstInfo.insertXsCstModifyLog(voo);
				break;
			case "officeTel":
				voo.setContent("���칫����"+defaultTelNo+"���Ϊ"+bean.getOfficeTel());
				voo.setReason("�칫��");
				voo.setOperator(zygw);
				voo.setOperDate(modifyDate);
				voo.setLogType("1");
				voo.setCusId(cusId);
				voo.setBelongSys("xs");
				voo.setProjectId(projGuid);
				xsCstInfo.insertXsCstModifyLog(voo);
				break;
			case "fax":
				voo.setContent("���������"+defaultTelNo+"���Ϊ"+bean.getFax());
				voo.setReason("�����");
				voo.setOperator(zygw);
				voo.setOperDate(modifyDate);
				voo.setLogType("1");
				voo.setCusId(cusId);
				voo.setBelongSys("xs");
				voo.setProjectId(projGuid);
				xsCstInfo.insertXsCstModifyLog(voo);
				break;
		}
		if(bean2.getEstCloseDate()!=null && !bean2.getEstCloseDate().equals("")) {
			XsOpp2GjjlTableEntity gjjl = new XsOpp2GjjlTableEntity(); 
			User employee = (User)RbacUtils.subject().getSession().getAttribute("user");
			String userGuid=employee.getExtInfo("xs_userGuid");
			gjjl.setGjfs("�������");
			gjjl.setGjContent("ϵͳ�Զ�����,��Ϊ��Ч");
			gjjl.setGjDate(modifyDate);
			gjjl.setOppGuid(oppGuid);
			gjjl.setGjrGuid(userGuid);
			xsCstInfo.insertXsCstFollowInfo(gjjl);
		}
		return "redirect:/mbem/mcrm/house/customer/getXsCstAndFollowInfoByCstGuid.action?cstGuid="+cstGuid+"&oppGuid="+oppGuid;
	}
	/**
	 * ������������ҳ
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:customerManage:addFollow")
	@RequestMapping(value="/follow")
	public String addFollowInfo(@RequestParam(value="type",required=true)String type,@RequestParam(value="cstGuid",required=true)String cstGuid,@RequestParam(value="oppGuid",required=true)String oppGuid,Model model) {
		SearchOptionEntity option = new SearchOptionEntity();
		List<Map<String,Object>> optionList = new ArrayList<Map<String,Object>>();
		Map<String,Object> optionlistlist = new HashMap<String,Object>();
		option.setModule("xsaddcst");
		optionList = xsCstInfo.addOrUpdateCstInfo("addFollow");
		List<SearchOptionEntity> showOptionList = optionListService.getOptionListByModule(option);
		for(Map<String,Object> option1:optionList) {
			for(SearchOptionEntity showOption:showOptionList) {
				if(option1.get("name").equals(showOption.getName())) {
					option1.put("tagname", showOption.getComment());
				}
			}
		}
		optionlistlist.put("option", optionList);
		String nextFollowDate = xsCstInfo.getNextFollowDate();
		model.addAttribute("defaultNextFollowDate", nextFollowDate.substring(0,10));
		httpSession.setAttribute("xsAddFollowMap", optionlistlist);
		model.addAttribute("cstGuid", cstGuid);
		model.addAttribute("closeType",type);
		model.addAttribute("oppGuid", oppGuid);
		return "/mbem/mcrm/house/customer/follow";
	}
	/**
	 * ��������������Ϣ
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:customerManage:addFollow")
	@RequestMapping(value="/insertFollowInfo")
	public String insertFollowInfo(XsOpp2GjjlTableEntity bean,@RequestParam(value="cstGuid",required=true)String cstGuid,@RequestParam(value="oppGuid",required=true)String oppGuid,@RequestParam(value="type",required=true)String type) {
		SimpleDateFormat fm2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		User employee = (User)RbacUtils.subject().getSession().getAttribute("user");
		String userGuid=employee.getExtInfo("xs_userGuid");
		bean.setGjDate(fm2.format(new Date()));
		bean.setGjrGuid(userGuid);
		bean.setOppGuid(oppGuid);
		XsOppTableEntity entity=xsCstInfo.getStatusFlgFromOppOrtunity(oppGuid);
		String nowStatus=entity.getStatus();
		int nowStatusFlg=0;
		if("��ʧ".equals(nowStatus)){
			nowStatusFlg=0;	
		}
		if("��ѯ".equals(nowStatus)){
			nowStatusFlg=1;	
		}if("����".equals(nowStatus)){
			nowStatusFlg=2;	
		}
		if("ԤԼ".equals(nowStatus)){
			nowStatusFlg=3;	
		}
		if("�Ϲ�".equals(nowStatus)){
			nowStatusFlg=4;		
		}
		if("ǩԼ".equals(nowStatus)){
			nowStatusFlg=5;	
		}
		if(bean.getGjfs().equals("����")) {
			bean.setGjfs("�ͻ�����");
			bean.setGjStatus("��ѯ");
			if(nowStatusFlg>=1){
				bean.setStatus("");
			}else{
				bean.setStatus("��ѯ");
			}
		}else if(bean.getGjfs().equals("ȥ��")) {
			bean.setGjfs("�������");
			bean.setGjStatus("��ѯ");
			if(nowStatusFlg>=1){
				bean.setStatus("");
			}else{
				bean.setStatus("��ѯ");
			}
		}else if(bean.getGjfs().equals("����") || bean.getGjfs().equals("����")) {
			bean.setGjfs("�ֳ��Ӵ�");
			bean.setGjStatus("����");
			if(nowStatusFlg>=2){
				bean.setStatus("");
			}else{
				bean.setStatus("����");
			}
		}
		XsCstSearchOption cstSearchInfo = new XsCstSearchOption();
		cstSearchInfo.setOppGUID(oppGuid);
		List<XsCstInfo> cstListInfo = xsCstInfo.getXsCstInfoByCstGuid(cstSearchInfo);
		String isInvalid = cstListInfo.get(0).getXsOpp().getEstCloseDate();
		if(isInvalid !=null && !isInvalid.equals("")) {
			if(Integer.parseInt(isInvalid.substring(0, 4))>2000){
				XsOppTableEntity oppInfo = new XsOppTableEntity();
				oppInfo.setOppGuid(oppGuid);
				oppInfo.setEstCloseDate("");
				xsCstInfo.updateXsOppInfo(oppInfo);
			}
		}
		if(cstListInfo.get(0).getXsOpp().getStatus().equals("�Ϲ�") || cstListInfo.get(0).getXsOpp().getStatus().equals("ǩԼ")){
			bean.setStatus("");
		}
		xsCstInfo.insertXsCstFollowInfo(bean);
		if(type.equals("3"))
			return "/mbem/mcrm/house/customer/customPage"; 
		else
			return "redirect:/mbem/mcrm/house/customer/getXsCstAndFollowInfoByCstGuid.action?cstGuid="+cstGuid+"&oppGuid="+oppGuid;
	}
	/**
	 * ȡ����������
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:customerManage:addFollow")
	@RequestMapping(value="/cancleAddFollowInfo")
	public String cancleAddFollowInfo() {
		log.info("cancleAddFollowInfo");
		return "/mbem/mcrm/house/customer/personal";
	}
	/**
	 * ����߼�����ҳ
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:customerManage:advancedSearch")
	@RequestMapping(value="/advancedSearch")
	public String advancedSearch(){
		return "/mbem/mcrm/house/customer/advancedSearch";
	}
	/**
	 * �߼���ѯ����
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:customerManage:advancedSearch")
	@RequestMapping(value="/getXsCstInfoByAdvancedSearch")
	public String getXsCstInfoByAdvancedSearch(String telOrName,XsCstSearchOption searchOption){	
		if(telOrName !=null && !"".equals(telOrName)){//���ݿͻ����ƺ͵绰ģ����ѯ
			boolean isNum = telOrName.matches("[0-9]+");
			if(isNum){
				searchOption.setMobileTel(telOrName);
			}else{
				searchOption.setCstName(telOrName);
			}
		}
		String []options = {};
		String optionStr = "";
		if(searchOption.getGfyx() != null && !searchOption.getGfyx().equals("")) {
			options = searchOption.getGfyx().split(",");
			optionStr = "";
			for(int i=0; i<options.length; i++) {
				if(i == 0) {
					optionStr += "\'"+options[i]+"\'";
				}else{
					optionStr += ",\'"+options[i]+"\'";
				}	
			}
			searchOption.setGfyx(optionStr);
		}
		if(searchOption.getStatus() != null && !searchOption.getStatus().equals("")) {
			options = searchOption.getStatus().split(",");
			optionStr = "";
			for(int i=0; i<options.length; i++) {
				if(i == 0) {
					optionStr += "\'"+options[i]+"\'";
				}else{
					optionStr += ",\'"+options[i]+"\'";
				}	
			}
			searchOption.setStatus(optionStr);
		}
		if(searchOption.getAgeGroup() != null && !searchOption.getAgeGroup().equals("")) {
			options = searchOption.getAgeGroup().split(",");
			optionStr = "";
			for(int i=0; i<options.length; i++) {
				if(i == 0) {
					optionStr += "\'"+options[i]+"\'";
				}else{
					optionStr += ",\'"+options[i]+"\'";
				}	
			}
			searchOption.setAgeGroup(optionStr);
		}
		if(searchOption.getGjfs() != null && !searchOption.getGjfs().equals("")) {
			options = searchOption.getGjfs().split(",");
			optionStr = "";
			for(int i=0; i<options.length; i++) {
				if(i == 0) {
					optionStr += "\'"+options[i]+"\'";
				}else{
					optionStr += ",\'"+options[i]+"\'";
				}	
			}
			searchOption.setGjfs(optionStr);
		}
		if(searchOption.getYxArea() != null && !searchOption.getYxArea().equals("")) {
			options = searchOption.getYxArea().split(",");
			optionStr = "";
			for(int i=0; i<options.length; i++) {
				if(i == 0) {
					optionStr += "YxArea like \'%"+options[i]+"%\'";
				}else{
					optionStr += "or YxArea like \'%"+options[i]+"%\'";
				}	
			}
			searchOption.setYxArea(optionStr);
		}
		if(searchOption.getMainMediaName() != null && !searchOption.getMainMediaName().equals("")) {
			options = searchOption.getMainMediaName().split(",");
			optionStr = "";
			for(int i=0; i<options.length; i++) {
				if(i == 0) {
					optionStr += "\'"+options[i]+"\'";
				}else{
					optionStr += ",\'"+options[i]+"\'";
				}	
			}
			searchOption.setMainMediaName(optionStr);
		}
		User employee = (User)RbacUtils.subject().getSession().getAttribute("user");
		String userGUID=employee.getExtInfo("xs_userGuid");
		searchOption.setUserGUID(userGUID);

		httpSession.setAttribute("xsAjaxUrl", "/mbem/mcrm/house/customer/loadXsCustomListByAdvancedSearch.action");
		httpSession.setAttribute("advancedSearchOption", searchOption);
		
		return "/mbem/mcrm/house/customer/customPage";
	}
	/**
	 * 
	 * @param page
	 * @param pageLen
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:customerManage:advancedSearch")
	@ResponseBody
	@RequestMapping(value="/loadXsCustomListByAdvancedSearch")
	public Map<String,Object> loadXsCustomListByAdvancedSearch(int page,int pageLen,String sort,String type) {
		XsCstSearchOption searchOption = new XsCstSearchOption();
		List<XsCstInfo> cstList = new ArrayList<XsCstInfo>();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		searchOption = (XsCstSearchOption)httpSession.getAttribute("advancedSearchOption");
		searchOption.setStartIndex((page-1)*pageLen);
		searchOption.setLength(pageLen);
		searchOption.setSortWay(sort);
		searchOption.setIsAll(type);
		searchOption.setProjGUID(projGuid);
		cstList = xsCstInfo.getXsCstListByEmployee(searchOption);
		Map<String,Object> cstInfo = new HashMap<String,Object>();
		int cstNum = 0;
		if(page == 1) {
			cstNum = xsCstInfo.getXsCstCountByEmployee(searchOption);
		}
		cstInfo.put("cstNum", cstNum);
		cstInfo.put("cstList", cstList);
		return cstInfo;
	}
	/**
	 * ��ѯ������ֻ����Ƿ��Ѿ��Ǽǹ�
	 */
	@RequiresPermissions(value="page:saleMobile:customerManage:customer")
	@RequestMapping(value="/checkTelValidity")
	@ResponseBody
	public String checkTelValidity(@RequestParam(value="tel",required=true) String tel,@RequestParam(value="projGuid",required=true)String projGuid) {
		XsCstSearchOption searchInfo = new XsCstSearchOption();
		searchInfo .setStartIndex(0);
		searchInfo.setLength(1);
		searchInfo.setMobileTel(tel);
		searchInfo.setSortWay("1");
		searchInfo.setIsAll("1");
		searchInfo.setProjGUID(projGuid);
	    List<XsCstInfo> cstList=xsCstInfo.getXsCstListByEmployee(searchInfo);
		if(cstList.size() == 0) {
			return "YES";
		}
		else {
			return "NO";
		}
	}
	@RequiresPermissions(value="page:saleMobile:customerManage:customer")
	@ResponseBody
	@RequestMapping(value="/focusCstOrCancle")
	public int focusCstOrCancle(int flag,String oppGuid) {
		log.info(flag==1?"�����ע":"ȡ����ע"+"  oppGuid:"+oppGuid);
		XsOppTableEntity oppInfo = new XsOppTableEntity();
		oppInfo.setOppGuid(oppGuid);
		oppInfo.setTopic(flag+"");
		int isSuccess = xsCstInfo.updateXsOppInfo(oppInfo);
		return isSuccess;
	}
	public  List<XsCstInfo> getXsCstInfoByTel(String tel){
		XsCstSearchOption searchInfo = new XsCstSearchOption();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		searchInfo .setStartIndex(0);
		searchInfo.setLength(1);
		searchInfo.setMobileTel(tel);
		searchInfo.setSortWay("1");
		searchInfo.setIsAll("1");
		searchInfo.setProjGUID(projGuid);
	    List<XsCstInfo> cstList=xsCstInfo.getXsCstListByEmployee(searchInfo);
	    return cstList;
	}
	
	@ResponseBody
	@RequestMapping(value="checkAllowCopy")
	public XsCstBelongToAjax checkAllowCopy(Model model,String projGuid,String cstGuid,String oppGuid) {
		log.info("projGuid:"+projGuid+"   cstGuid:"+cstGuid);
		XsCstInfo cstInfo = new XsCstInfo();
		cstInfo = xsCstInfo.getXsCstInfoByCstGuidAndProjGuid(projGuid, cstGuid);
		XsCstBelongToAjax ajaxVo = new XsCstBelongToAjax("","");
		if(cstInfo == null) {
				log.info("�¿ͻ�");
				ajaxVo = new XsCstBelongToAjax("0", "/mbem/mcrm/house/customer/copyCstInfo.action?cstGuid="+cstGuid+"&oppGuid="+oppGuid+"&projGuid="+projGuid);
		}
		else {
		    	String zygw = cstInfo.getXsOpp().getZygw();
				Session session = RBACSubject.getSecurityUtils().getSession();
				User user = (User)session.getAttribute("user");
				String curUser = user.getUsername();
		    	if(zygw.equals("�����ͻ�")) {
		    		log.info("�����ͻ�");//�Ƿ���Ҫ�����Ƿ����ֱ�Ӹ���
		    		String selectName=xsCstInfo.checkCusBeFollowedByEmployee();
		    		if(selectName.equals("1")){
		    			ajaxVo= new XsCstBelongToAjax("3",cstInfo.getXsCst().getCstName(), "0", "1", "/mbem/mcrm/house/customer/getXsCstAndFollowInfoByCstGuid.action?cstGuid="+cstInfo.getXsCst().getCstGuid()+"&oppGuid="+oppGuid, cstInfo.getXsCst().getCreatedOn(), "user_name", cstInfo.getXsCst().getCstGuid(), cstInfo.getXsOpp().getOppGuid());	
		    			//new XsCstBelongToAjax("3", "/mbem/mcrm/house/customer/getXsCstAndFollowInfoByCstGuid.action?cstGuid="+cstInfo.getXsCst().getCstGuid()+"&oppGuid="+oppGuid);
		    		}else {
		    			ajaxVo=new XsCstBelongToAjax("3",cstInfo.getXsCst().getCstName(), "1", "1");//����ֱ�Ӹ���
		    		}
		    	}
		    	else if(zygw.equals("������ͻ�")){
		    		ajaxVo=new XsCstBelongToAjax("4",cstInfo.getXsCst().getCstName());//����ֱ�Ӹ���
		    	}
		    	else if(zygw.equals(curUser)) {//֮���õ�¼����ҵ�����滻
		    		log.info("�Լ��Ŀͻ�");
		    		ajaxVo = new XsCstBelongToAjax("1", "/mbem/mcrm/house/customer/getXsCstAndFollowInfoByCstGuid.action?cstGuid="+cstInfo.getXsCst().getCstGuid()+"&oppGuid="+oppGuid);
		    	}
		    	else if(!zygw.equals(curUser) && zygw !=null && !"".equals(zygw)){
					log.info("���˵Ŀͻ� "+zygw);
					ajaxVo=new XsCstBelongToAjax("2", cstInfo.getXsCst().getCstName(), "1",cstInfo.getXsOpp().getStatus() , cstInfo.getXsOpp().getCreatedOn(), zygw,"/mbem/mcrm/house/customer/getXsCstAndFollowInfoByCstGuid.action?cstGuid="+cstInfo.getXsCst().getCstGuid()+"&oppGuid="+oppGuid); 
				}
		    }
		return ajaxVo;
	}
	
	@RequestMapping("/copyCstInfo")
	public String copyCstInfo(Model model,String cstGuid,String oppGuid,String projGuid){
		
		SearchOptionEntity option = new SearchOptionEntity();
		List<SearchOptionEntity> showOptionList = new ArrayList<SearchOptionEntity>();
		XsCstSearchOption searchInfo = new XsCstSearchOption();
		List<XsCstInfo> cstInfoList = new ArrayList<XsCstInfo>();
		String optionStr = "";
		String []options = {};
		String valueStr = "";
		String []values = {};
		XsAddAndUpdateCstOption hh = new XsAddAndUpdateCstOption();
		List<Map<String,String>> cstOptionInfoList = new ArrayList<Map<String,String>>();
		XsCstInfo cstInfo = new XsCstInfo();
		
		option.setModule("xsaddcst");
		showOptionList = optionListService.getOptionListByModule(option);
		searchInfo.setOppGUID(oppGuid);
		cstInfoList = xsCstInfo.getXsCstInfoByCstGuid(searchInfo);
		cstInfo = cstInfoList.get(0);
		for(SearchOptionEntity showOption:showOptionList) {
			if(showOption.getName().equals("�Ƿ���Ч")||showOption.getName().equals("�Ƿ���Ч")){
				continue;
			}
			String name = showOption.getComment();
			if(showOption.getOptionType().equals("text")){
				switch(name) {
					case "cstName":
						valueStr = cstInfo.getXsCst().getCstName()==null?"":cstInfo.getXsCst().getCstName();
						break;
					case "mobileTel":
						log.info("mobileTel");
						valueStr = cstInfo.getXsCst().getMobileTel()==null?"":cstInfo.getXsCst().getMobileTel();
						httpSession.setAttribute("mobileTel", valueStr.equals("")?"null":valueStr);
						break;
					case "homeTel":
						valueStr = cstInfo.getXsCst().getHomeTel()==null?"":cstInfo.getXsCst().getHomeTel();
						if(valueStr.equals(""))
							continue;
						break;
					case "officeTel":
						valueStr = cstInfo.getXsCst().getOfficeTel()==null?"":cstInfo.getXsCst().getOfficeTel();
						if(valueStr.equals(""))
							continue;
						break;
					case "fax":
						valueStr = cstInfo.getXsCst().getFax()==null?"":cstInfo.getXsCst().getFax();
						if(valueStr.equals(""))
							continue;
						break;
					case "description":
						valueStr = cstInfo.getXsCst().getDescription()==null?"":cstInfo.getXsCst().getDescription();
						break;	
					case "cardId":
						valueStr = cstInfo.getXsCst().getCardId()==null?"":cstInfo.getXsCst().getCardId();
						break;
					case "zyNum":
						String zyNum = cstInfo.getXsCstAttr().getZyNum()==null?"":cstInfo.getXsCstAttr().getZyNum();
						valueStr = zyNum.substring(0,zyNum.indexOf("."));//ȥ����ҵ������С����������
						break;
					case "gjContent":
						valueStr = "";
						break;
					case "dfNum":
						valueStr = "";
						break;
				}
				showOption.setContent(valueStr);	
			}else if(showOption.getOptionType().equals("radio") || showOption.getOptionType().equals("checkbox")) {
				switch(name) {
					case "gender":
						valueStr = cstInfo.getXsCst().getGender()==null?"":cstInfo.getXsCst().getGender();
						break;
					case "ageGroup":
						valueStr = cstInfo.getXsCstAttr().getAgeGroup()==null?"":cstInfo.getXsCstAttr().getAgeGroup();
						break;
					case "workArea":
						valueStr = cstInfo.getXsCstAttr().getWorkArea()==null?"":cstInfo.getXsCstAttr().getWorkArea();
						break;
					case "homeArea":
						valueStr = cstInfo.getXsCstAttr().getHomeArea()==null?"":cstInfo.getXsCstAttr().getHomeArea();
						break;
					case "work":
						valueStr = cstInfo.getXsCstAttr().getWork()==null?"":cstInfo.getXsCstAttr().getWork();
						break;
					case "family":
						valueStr = cstInfo.getXsCstAttr().getFamily()==null?"":cstInfo.getXsCstAttr().getFamily();
						break;
				}
				values = valueStr.split(",");
				optionStr = showOption.getContent();
				options = optionStr.split(",");
				for(int i=0; i<values.length; i++) {
					for(int j=0; j<options.length; j++) {
						if(values[i].equals(options[j])){
							options[j] = "y"+options[j];
							break;
						}
					}
				}
				showOption.setContent(StringUtils.join(options,","));
			}
			TagOption to = hh.generateHtmlEntity(showOption);
			Map<String,String> cstInfoOption = hh.jointHtmlStr(to);
			cstOptionInfoList.add(cstInfoOption);
		}
		String nextFollowDate = xsCstInfo.getNextFollowDate();
		model.addAttribute("autoGenNextFollowDate", nextFollowDate.substring(0,10));
		model.addAttribute("addCstInfoList",cstOptionInfoList);
		model.addAttribute("xsNewTel", cstInfo.getXsCst().getMobileTel());
		model.addAttribute("projGuid", projGuid);
		model.addAttribute("actionUrl", "/mbem/mcrm/house/customer/insertCopyCstInfo.action?projGuid="+projGuid+"&cstGuid="+cstGuid+"&oppGuid="+oppGuid);
		return "/mbem/mcrm/house/customer/addCustom";
	}
	
	@RequestMapping(value="/insertCopyCstInfo")
	public String insertCopyCstInfo(@RequestParam(value="projGuid",required=true)String projGuid,
			@RequestParam(value="cstGuid",required=true)String cstGuid,@RequestParam(value="oppGuid",required=true)String oppGuid,
			XsCstTableEntity bean,XsCstAttrTableEntity bean1,XsCstAttachTableEntity bean2,XsOppTableEntity bean3,XsOpp2CstTableEntity bean4,XsOpp2RoomTableEntity bean5,XsOpp2GjjlTableEntity bean6) {
		log.info("projGuid============"+projGuid);
		String zygw;
		String defaultTel;
		//String insertOrUpdate = "update";
		XsCstTableEntity searchInfo = new XsCstTableEntity();
		XsCstTableEntity cstInfo = new XsCstTableEntity();
		List<XsCstInfo> cstList = new ArrayList<XsCstInfo>();
		XsCstSearchOption option = new XsCstSearchOption();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		option.setStartIndex(0);
		option.setLength(1);
		option.setSortWay("1");
		option.setIsAll("1");
		option.setProjGUID(projGuid);
		/*��֤���ֻ����Ƿ��пͻ�����,�й����򷵻�*/
		if(bean.getMobileTel()!=null && !bean.getMobileTel().equals("")){
			option.setMobileTel(bean.getMobileTel());
			cstList=xsCstInfo.getXsCstListByEmployee(option);
		    if(cstList.size() != 0) {
		    	log.info("�ֻ����Ѿ�¼���");
		    	return "redirect:/mbem/mcrm/house/customer/customPage.action";
		    }
		}
		if(bean.getHomeTel()!=null && !bean.getHomeTel().equals("")){
			option.setMobileTel(bean.getHomeTel());
			cstList=xsCstInfo.getXsCstListByEmployee(option);
		    if(cstList.size() != 0) {
		    	log.info("��ͥ���Ѿ�¼���");
		    	return "redirect:/mbem/mcrm/house/customer/customPage.action";
		    }  	
		}
		if(bean.getOfficeTel()!=null && !bean.getOfficeTel().equals("")){
			option.setMobileTel(bean.getOfficeTel());
			cstList=xsCstInfo.getXsCstListByEmployee(option);
		    if(cstList.size() != 0) {
		    	log.info("�칫���Ѿ�¼���");
		    	return "redirect:/mbem/mcrm/house/customer/customPage.action";
		    } 	
		}
		if(bean.getFax()!=null && !bean.getFax().equals("")){
			option.setMobileTel(bean.getFax());
			cstList=xsCstInfo.getXsCstListByEmployee(option);
		    if(cstList.size() != 0) {
		    	log.info("������Ѿ�¼���");
		    	return "redirect:/mbem/mcrm/house/customer/customPage.action";
		    }
		}
		/*if(insertOrUpdate.equals("update")) {
			if(cstInfo.getMobileTel()!=null && !cstInfo.getMobileTel().equals("")){
				bean.setMobileTel("");
			}
			if(cstInfo.getHomeTel()!=null && !cstInfo.getHomeTel().equals("")){
				bean.setHomeTel("");
			}
			if(cstInfo.getOfficeTel()!=null && !cstInfo.getOfficeTel().equals("")){
				bean.setOfficeTel("");
			}
			if(cstInfo.getFax()!=null && !cstInfo.getFax().equals("")){
				bean.setFax("");
			}
			bean.setCstGuid(cstInfo.getCstGuid());
		}*/
		defaultTel = bean.getDefaultTel();
		//phoneNo = bean.getMobileTel();
		String mobileTel = bean.getMobileTel();
		String homeTel = bean.getHomeTel();
		String officeTel = bean.getOfficeTel();
		String fax = bean.getFax();
		
		switch(defaultTel){
			case "homeTel":
				bean.setMobileTel(bean.getHomeTel());
				bean.setHomeTel(mobileTel);
				break;
			case "officeTel":
				bean.setMobileTel(bean.getOfficeTel());
				bean.setOfficeTel(mobileTel);
				break;
			case "fax":
				bean.setMobileTel(bean.getFax());
				bean.setFax(mobileTel);
				break;
			case "tel":
				break;
		}
		zygw = user.getUsername();
		String buGuid = "42A0C9C9-51EA-E411-BAAF-FCAA145C42F2";//��˾GUID
		Byte by = 1;
		SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String followDate = fm1.format(new Date());
		String userGuid=user.getExtInfo("xs_userGuid");
		bean.setCreatedBy(zygw);
		bean.setCreatedByGuid(userGuid);
		bean.setCreatedOn(fm1.format(new Date()));
		bean.setCstType("����");//�ݶ�Ϊ���������������ʲô��ʶ��Ӧ����ɶ
		bean.setIsReceiveSms(by);
		bean.setIsCreatorUse(by);
		bean.setFirstName(bean.getCstName().substring(0, 1));
		bean.setLastName(bean.getCstName().substring(1));
		//bean1�Ļ�񡢽����̶ȡ���סҵ̬��ҳ���ȡ��������ʱû��
		bean2.setProjGuid(projGuid);
		bean2.setBuGuid(buGuid);
		bean2.setUserGuid(userGuid);
		
		bean3.setProjGuid(projGuid);
		bean3.setBuGuid(buGuid);
		bean3.setCreatedOn(fm1.format(new Date()));
		bean3.setOppSource(bean6.getGjfs());
		bean3.setFirstGfyx(bean3.getGfyx());
		bean3.setIsCreatorUse(by);//��֪��ɶ�õ�
		bean3.setCreatedByGuid(userGuid);
		bean3.setCreatedBy(zygw);
		bean3.setUserGuid(userGuid);//�봴���ߵ�����
		bean3.setZygw(zygw);
		bean3.setZygwGuid(userGuid);
		bean3.setDescription("");//��ͻ����е�description�ֶ�����ͬ����ֹ���ͻ���ǩ���ȥ
		/*XsOppTableEntity entity=xsCstInfo.getStatusFlgFromOppOrtunity(bean3.getOppGuid());
		String nowStatus=entity.getStatus();
		int nowStatusFlg=0;
		if("��ʧ".equals(nowStatus)){
			nowStatusFlg=0;	
		}
		if("��ѯ".equals(nowStatus)){
			nowStatusFlg=1;	
		}if("����".equals(nowStatus)){
			nowStatusFlg=2;	
		}
		if("ԤԼ".equals(nowStatus)){
			nowStatusFlg=3;	
		}
		if("�Ϲ�".equals(nowStatus)){
			nowStatusFlg=4;		
		}
		if("ǩԼ".equals(nowStatus)){
			nowStatusFlg=5;	
		}*/
		if(bean6.getGjfs().equals("����") || bean6.getGjfs().equals("����")) {
			bean6.setGjfs("�ֳ��Ӵ�");
			bean6.setStatus("����");
			bean3.setStatus("����");
			/*if(nowStatusFlg>=2){
				bean3.setStatus("");
			}else{
				bean3.setStatus("����");
			}*/
		}else if(bean6.getGjfs().equals("����")) {
			bean6.setGjfs("�ͻ�����");
			bean6.setStatus("��ѯ");
			bean3.setStatus("��ѯ");
			/*if(nowStatusFlg>=1){
				bean3.setStatus("");
			}else{
				bean3.setStatus("��ѯ");
			}*/
		}else if(bean6.getGjfs().equals("ȥ��")) {
			bean6.setGjfs("�������");
			bean6.setStatus("��ѯ");
			bean3.setStatus("��ѯ");
			/*if(nowStatusFlg>=1){
				bean3.setStatus("");
			}else{
				bean3.setStatus("��ѯ");
			}*/
		}
			
		bean4.setCstNum("1");//��֪����ʲô�ж�����ֵ
		bean4.setProjGuid(projGuid);
		
		bean5.setNum("1");//���,�����ò�֪
		
		bean6.setProjGuid(projGuid);
		bean6.setGjDate(followDate);
		//bean6.setNextDate(bean6.getNextDate()+" "+fm1.format(new Date()));
		bean6.setGjrGuid(userGuid);
		XsCstInfo newCstInfo = new XsCstInfo();
		newCstInfo.setXsCst(bean);
		newCstInfo.setXsCstAttr(bean1);
		newCstInfo.setXsCstAttach(bean2);
		newCstInfo.setXsOpp(bean3);
		newCstInfo.setXsOpp2Cst(bean4);
		newCstInfo.setXsOpp2Room(bean5);
		newCstInfo.setXsOpp2Gjjl(bean6);
		
		searchInfo.setCstGuid(cstGuid);
		cstInfo = xsCstInfo.getXsCstBasicInfo(searchInfo);
		
		xsCstInfo.updateXsCstAndInsertOtherInfo(newCstInfo);
		
		log.info(cstInfo.getMobileTel()+"===="+cstInfo.getHomeTel()+"===="+cstInfo.getOfficeTel()+"===="+cstInfo.getFax());
		if(mobileTel != null && !"".equals(mobileTel)){
			ZsBusiCustomLogTable vo = new ZsBusiCustomLogTable();
			if(cstInfo.getMobileTel()!=null && !"".equals(cstInfo.getMobileTel())){
				if(!cstInfo.getMobileTel().equals(mobileTel)){
					vo.setContent("���ֻ�����"+cstInfo.getMobileTel()+"�޸�Ϊ"+mobileTel);
					vo.setReason("�ֻ���");
					vo.setOperator(zygw);
					vo.setOperDate(followDate);
					vo.setLogType("1");
					vo.setCusId(cstInfo.getCstGuid());
					vo.setBelongSys("xs");
					vo.setProjectId(projGuid);
					xsCstInfo.insertXsCstModifyLog(vo);
				}
			}else{
				vo.setContent("���������ֻ�����Ϊ"+mobileTel);
				vo.setReason("�����ֻ���");
				vo.setOperator(zygw);
				vo.setOperDate(followDate);
				vo.setLogType("1");
				vo.setCusId(cstInfo.getCstGuid());
				vo.setBelongSys("xs");
				vo.setProjectId(projGuid);
				xsCstInfo.insertXsCstModifyLog(vo);
			}
		}
		if(homeTel != null && !"".equals(homeTel)){
			ZsBusiCustomLogTable vo = new ZsBusiCustomLogTable();
			if(cstInfo.getHomeTel()!=null && !"".equals(cstInfo.getHomeTel())){
				if(!cstInfo.getHomeTel().equals(homeTel)){
					vo.setContent("����ͥ����"+cstInfo.getHomeTel()+"�޸�Ϊ"+homeTel);
					vo.setReason("��ͥ��");
					vo.setOperator(zygw);
					vo.setOperDate(followDate);
					vo.setLogType("1");
					vo.setCusId(cstInfo.getCstGuid());
					vo.setBelongSys("xs");
					vo.setProjectId(projGuid);
					xsCstInfo.insertXsCstModifyLog(vo);
				}
			}else{
				vo.setContent("����������ͥ����Ϊ"+homeTel);
				vo.setReason("������ͥ��");
				vo.setOperator(zygw);
				vo.setOperDate(followDate);
				vo.setLogType("1");
				vo.setCusId(cstInfo.getCstGuid());
				vo.setBelongSys("xs");
				vo.setProjectId(projGuid);
				xsCstInfo.insertXsCstModifyLog(vo);
			}
		}
		if(officeTel != null && !"".equals(officeTel)){
			ZsBusiCustomLogTable vo = new ZsBusiCustomLogTable();
			if(cstInfo.getOfficeTel()!=null && !"".equals(cstInfo.getOfficeTel())){
				if(!cstInfo.getOfficeTel().equals(officeTel)){
					vo.setContent("���칫����"+cstInfo.getOfficeTel()+"�޸�Ϊ"+officeTel);
					vo.setReason("�칫��");
					vo.setOperator(zygw);
					vo.setOperDate(followDate);
					vo.setLogType("1");
					vo.setCusId(cstInfo.getCstGuid());
					vo.setBelongSys("xs");
					vo.setProjectId(projGuid);
					xsCstInfo.insertXsCstModifyLog(vo);
				}
			}else{
				vo.setContent("���������칫����Ϊ"+officeTel);
				vo.setReason("�����칫��");
				vo.setOperator(zygw);
				vo.setOperDate(followDate);
				vo.setLogType("1");
				vo.setCusId(cstInfo.getCstGuid());
				vo.setBelongSys("xs");
				vo.setProjectId(projGuid);
				xsCstInfo.insertXsCstModifyLog(vo);
			}
		}
		if(fax != null && !"".equals(fax)){
			ZsBusiCustomLogTable vo = new ZsBusiCustomLogTable();
			if(cstInfo.getFax()!=null && !"".equals(cstInfo.getFax())){
				if(!cstInfo.getFax().equals(fax)){
					vo.setContent("���������"+cstInfo.getFax()+"�޸�Ϊ"+fax);
					vo.setReason("�����");
					vo.setOperator(zygw);
					vo.setOperDate(followDate);
					vo.setLogType("1");
					vo.setCusId(cstInfo.getCstGuid());
					vo.setBelongSys("xs");
					vo.setProjectId(projGuid);
					xsCstInfo.insertXsCstModifyLog(vo);
				}
			}else{
				vo.setContent("���������������Ϊ"+fax);
				vo.setReason("���������");
				vo.setOperator(zygw);
				vo.setOperDate(followDate);
				vo.setLogType("1");
				vo.setCusId(cstInfo.getCstGuid());
				vo.setBelongSys("xs");
				vo.setProjectId(projGuid);
				xsCstInfo.insertXsCstModifyLog(vo);
			}
		}
		
		ZsBusiCustomLogTable vo = new ZsBusiCustomLogTable();
		switch(defaultTel){
			case "homeTel":
				vo.setContent("����ͥ����"+homeTel+"�޸�Ϊ"+bean.getHomeTel());
				vo.setReason("��ͥ��");
				vo.setOperator(zygw);
				vo.setOperDate(followDate);
				vo.setLogType("1");
				vo.setCusId(cstInfo.getCstGuid());
				vo.setBelongSys("xs");
				vo.setProjectId(projGuid);
				xsCstInfo.insertXsCstModifyLog(vo);
				vo.setContent("���ֻ�����"+mobileTel+"�޸�Ϊ"+bean.getMobileTel());
				vo.setReason("�ֻ���");
				xsCstInfo.insertXsCstModifyLog(vo);
				break;
			case "officeTel":
				vo.setContent("���칫����"+officeTel+"�޸�Ϊ"+bean.getOfficeTel());
				vo.setReason("�칫��");
				vo.setOperator(zygw);
				vo.setOperDate(followDate);
				vo.setLogType("1");
				vo.setCusId(cstInfo.getCstGuid());
				vo.setBelongSys("xs");
				vo.setProjectId(projGuid);
				xsCstInfo.insertXsCstModifyLog(vo);
				vo.setContent("���ֻ�����"+mobileTel+"�޸�Ϊ"+bean.getMobileTel());
				vo.setReason("�ֻ���");
				xsCstInfo.insertXsCstModifyLog(vo);
				break;
			case "fax":
				vo.setContent("���������"+fax+"�޸�Ϊ"+bean.getFax());
				vo.setReason("�����");
				vo.setOperator(zygw);
				vo.setOperDate(followDate);
				vo.setLogType("1");
				vo.setCusId(cstInfo.getCstGuid());
				vo.setBelongSys("xs");
				vo.setProjectId(projGuid);
				xsCstInfo.insertXsCstModifyLog(vo);
				vo.setContent("���ֻ�����"+mobileTel+"�޸�Ϊ"+bean.getMobileTel());
				vo.setReason("�ֻ���");
				xsCstInfo.insertXsCstModifyLog(vo);
				break;
			case "tel":
				break;
	}
		return "redirect:/mbem/mcrm/house/customer/getXsCstAndFollowInfoByCstGuid.action?cstGuid="+cstGuid+"&oppGuid="+oppGuid;
	}
}
