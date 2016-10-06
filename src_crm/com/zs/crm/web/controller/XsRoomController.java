package com.zs.crm.web.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zs.crm.entity.XsRoomCountInfoVo;
import com.zs.crm.entity.XsRoomResourceInfoVo;
import com.zs.crm.entity.XsRoomSearchVo;
import com.zs.crm.entity.XsUnitInfoVo;
import com.zs.crm.entity.tableStructure.XsCstTableEntity;
import com.zs.crm.service.XsRoomService;
import com.zs.rbac.core.RBACSubject;
import com.zs.rbac.entity.User;

@Controller
@RequestMapping(value="/mbem/mcrm/house/room")
public class XsRoomController {
	private Log log=LogFactory.getLog(XsCustAnalysisController.class);
	@Autowired
	XsRoomService xsRoomService;
	@Autowired
	HttpSession httpSession;
	/**
	 * ת���ɰٷֱȣ�����һλС��
	 * @param db
	 * @return
	 */
	public String convertToPercent(double db) {
		DecimalFormat df = new DecimalFormat("0.0");
		String doub=df.format(db*100)+"%";
		return doub;
	}
	/**
	 * ת��Ϊǧ�ֺű�ʾ��
	 * @param data
	 * @return
	 */
	public String formatToSepara(String data) {
		Double db = Double.parseDouble(data);
		DecimalFormat df = new DecimalFormat("#,###.00"); 
		return df.format(db);
	}
	/**
	 * ��Դ������ҳ
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:xs:room:index")
	@RequestMapping(value="/index")
	public String roomIndex(Model model){
		
		List<XsUnitInfoVo> roomList = new ArrayList<XsUnitInfoVo>();
		XsRoomSearchVo bean = new XsRoomSearchVo();
		XsRoomCountInfoVo roomCountInfo = new XsRoomCountInfoVo();
		XsRoomCountInfoVo roomPercentInfo = new XsRoomCountInfoVo();
		List<Map<String,Object>> unitInfoList = new ArrayList<Map<String,Object>>();
		List<String> unitNameList = new ArrayList<String>();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		int roomNum = 0;
		List<XsRoomCountInfoVo> bldInfoList = new ArrayList<XsRoomCountInfoVo>();
		bean.setProjGuid(projGuid);
		bldInfoList = xsRoomService.getXsBldInfoByProNo(bean);//��ȡ¥���б�
		String bldGuid = bldInfoList.get(1).getBldGuid();
		String bldFullName = bldInfoList.get(1).getFullName();
		httpSession.setAttribute("bldGuid", bldGuid);
		httpSession.setAttribute("bldFullName", bldFullName);//��¥��guid��¥��ȫ�ƴ���session,�������̻�¥��ʱ����������ֵ
		//model.addAttribute("bldFullName","ʷ��ׯ���д������Ŀ-�����԰-A��-1��¥");
		bean.setBldGuid(bldGuid);//Ĭ�Ͻ����ǵ�ǰ��Ŀ�µĵ�һ��¥��
		
		unitNameList = xsRoomService.getXsUnitInfoByBulidNo(bean);//��ѯ��Ԫ��Ϣ
		roomCountInfo = xsRoomService.getXsCountInfoByBulidNo(bean);//��ѯ¥���µķ���ͳ����Ϣ
		
		for(String unitName:unitNameList) {
			bean.setUnit(unitName);
			roomList = xsRoomService.getXsRoomInfoByUnitNo(bean);//��ѯ�õ�Ԫ�µķ�����Ϣ
			roomNum = 0;
			for(XsUnitInfoVo roomInfo:roomList) {
				roomNum += roomInfo.getRoomList().size();
			}
			log.info(unitName+"�Ĳ���:"+roomList.size()+"   ��������"+roomNum);
			Map<String,Object> unitMap = new HashMap<String,Object>();
			unitMap.put("unitName", unitName);
			unitMap.put("unitInfo", roomList);
			unitInfoList.add(unitMap);	
		}
		
		int sold = Integer.parseInt(roomCountInfo.getSold());
		int unSold = Integer.parseInt(roomCountInfo.getUnSold());
		int preControl = Integer.parseInt(roomCountInfo.getPreControl());
		int total = Integer.parseInt(roomCountInfo.getTotal());
		roomPercentInfo.setSold(convertToPercent(sold*1.0/total)+"");
		roomPercentInfo.setUnSold(convertToPercent(unSold*1.0/total)+"");
		roomPercentInfo.setPreControl(convertToPercent(preControl*1.0/total)+"");//�������ۡ�δ�ۡ����ظ�ռ�İٷֱ�
		
		model.addAttribute("roomCountInfo", roomCountInfo);
		model.addAttribute("roomPercentInfo",roomPercentInfo);
		model.addAttribute("unitNameList", unitNameList);
		model.addAttribute("unitInfoList", unitInfoList);
		return "/mbem/mcrm/house/room/index";
	}
	/**
	 * ��Դ��Ϣ����
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:xs:room:roomInfo")
	@RequestMapping(value="/roomInfo")
	public String roomInfo(Model model,XsRoomSearchVo searchInfo) {
		List<XsRoomResourceInfoVo> roomResourceInfoList = new ArrayList<XsRoomResourceInfoVo>();
		List<XsCstTableEntity> cstInfoList = new ArrayList<XsCstTableEntity>();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		searchInfo.setProjGuid(projGuid);
		roomResourceInfoList = xsRoomService.getXsRoomInfoByRoomNo(searchInfo);
		log.info("roomResourceInfo"+roomResourceInfoList.size());
		for(XsRoomResourceInfoVo roomResource:roomResourceInfoList) {
			XsCstTableEntity cstInfo = new XsCstTableEntity();
			if(roomResource.getBargainInfo() != null) {
				cstInfo.setCstName(roomResource.getBargainInfo().getCstName());
				cstInfo.setMobileTel(roomResource.getBargainInfo().getMobileTel());
				cstInfoList.add(cstInfo);
			}
		}
		XsRoomResourceInfoVo roomResourceInfo = roomResourceInfoList.get(0);
		
		roomResourceInfo.getRoomInfo().setBldArea(formatToSepara(roomResourceInfo.getRoomInfo().getBldArea()));
		roomResourceInfo.getRoomInfo().setTnArea(formatToSepara(roomResourceInfo.getRoomInfo().getTnArea()));
		roomResourceInfo.getRoomInfo().setPrice(formatToSepara(roomResourceInfo.getRoomInfo().getPrice()));;
		roomResourceInfo.getRoomInfo().setTnPrice(formatToSepara(roomResourceInfo.getRoomInfo().getTnPrice()));
		roomResourceInfo.getRoomInfo().setTotal(formatToSepara(roomResourceInfo.getRoomInfo().getTotal()));//ת�������������ʾ��ʽ
		if(roomResourceInfo.getBargainInfo() != null) {
			roomResourceInfo.getBargainInfo().setBldCjPrice(formatToSepara(roomResourceInfo.getBargainInfo().getBldCjPrice()));
			roomResourceInfo.getBargainInfo().setTnCjPrice(formatToSepara(roomResourceInfo.getBargainInfo().getTnCjPrice()));
			roomResourceInfo.getBargainInfo().setRoomTotal(formatToSepara(roomResourceInfo.getBargainInfo().getRoomTotal()));
			roomResourceInfo.getBargainInfo().setQsDate(roomResourceInfo.getBargainInfo().getQsDate().substring(0, 10));
		}
		model.addAttribute("roomResource", roomResourceInfo);
		model.addAttribute("cstInfoList", cstInfoList);
		return "/mbem/mcrm/house/room/roomInfo";
	}
	/**
	 * ���밴��ǰ¥��������Դҳ
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:xs:room:searchRoom")
	@RequestMapping(value="/searchRoom")
	public String searchRoom(Model model,XsRoomSearchVo searchInfo){
		return "/mbem/mcrm/house/room/searchRoom";
	}
	/**
	 * ���밴��ǰ��������ҳ
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:xs:room:searchRoomBulid")
	@RequestMapping(value="/searchRoomBulid")
	public String searchRoomInPro(Model model) {
		return "/mbem/mcrm/house/room/searchRoomBulid";
	}
	/**
	 * ����ѡ��¥��ҳ
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:xs:room:selectRoomBulid")
	@RequestMapping(value="/selectRoomBulid")
	public String selectRoomBulid(Model model){
		XsRoomSearchVo bean = new XsRoomSearchVo();
		List<XsRoomCountInfoVo> bldInfoList = new ArrayList<XsRoomCountInfoVo>();
		XsRoomCountInfoVo totalInfo = new XsRoomCountInfoVo();
		Map<String,Object> areaInfo = new HashMap<String,Object>();
		List<Map<String,Object>> allBldInfo = new ArrayList<Map<String,Object>>();
		List<XsRoomCountInfoVo> countList = new ArrayList<XsRoomCountInfoVo>();
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		bean.setProjGuid(projGuid);
		totalInfo = xsRoomService.getXsCountInfoByBulidNo(bean);//��ȡ����¥���ܵ�ͳ����Ϣ
		bldInfoList = xsRoomService.getXsBldInfoByProNo(bean);//��ȡ¥���б�
		
		for(XsRoomCountInfoVo bldInfo:bldInfoList) {
			if(bldInfo.getSold() == null) {
				log.info("null"+countList.size());
				if(countList.size() != 0){
					areaInfo.put("value", countList);
					allBldInfo.add(areaInfo);
				}
				areaInfo = new HashMap<String,Object>();
				areaInfo.put("name", bldInfo.getName());
				countList = new ArrayList<XsRoomCountInfoVo>();
			}else {
				countList.add(bldInfo);
			}
		}
		areaInfo.put("value", countList);
		allBldInfo.add(areaInfo);
		log.info("allSize=="+allBldInfo.size());
		model.addAttribute("bldInfoList", allBldInfo);
		model.addAttribute("totalInfo", totalInfo);
		return "/mbem/mcrm/house/room/selectRoomBulid";
	}
	
	public void searchRoomInBld(XsRoomSearchVo searchInfo) {
		log.info(searchInfo.getBldName());
		log.info(searchInfo.getProjGuid());
		List<String> unitNameList = new ArrayList<String>();
		List<String> validUnitNameList = new ArrayList<String>();
		XsRoomCountInfoVo roomCountInfo = new XsRoomCountInfoVo();
		List<XsUnitInfoVo> unitInfo = new ArrayList<XsUnitInfoVo>();
		XsRoomCountInfoVo roomPercentInfo = new XsRoomCountInfoVo();
		List<Map<String,Object>> unitInfoList = new ArrayList<Map<String,Object>>();
		int roomNum = 0;
		
		unitNameList = xsRoomService.getXsUnitInfoByBulidNo(searchInfo);
		log.info(unitNameList.size()+"unitSize");
		roomCountInfo = xsRoomService.getXsCountInfoByBulidNo(searchInfo);//��ѯ¥���µķ���ͳ����Ϣ
		if(roomCountInfo == null){
			log.info("null");
			XsRoomCountInfoVo roomCountInfo1 = new XsRoomCountInfoVo();
			roomCountInfo1 .setNoHouse("0");
			roomCountInfo1.setSold("0");
			roomCountInfo1.setUnSold("0");
			roomCountInfo1.setPreControl("0");
			roomCountInfo1.setTotal("1");
			roomCountInfo = roomCountInfo1;
		}
		log.info("sold=="+roomCountInfo.getSold());
		log.info("unsold=="+roomCountInfo.getUnSold());
		log.info("preControl=="+roomCountInfo.getPreControl());
		log.info("total=="+roomCountInfo.getTotal());
		for(String unitName:unitNameList) {
			if(unitName.indexOf("��Ԫ") != -1){
				searchInfo.setUnit(unitName);
			}
			unitInfo = xsRoomService.getXsRoomInfoByUnitNo(searchInfo);//��ѯ�õ�Ԫ�µķ�����Ϣ
			if(unitInfo.size() == 0) {
				continue;	
			}
			validUnitNameList.add(unitName);
			roomNum = 0;
			for(XsUnitInfoVo unit:unitInfo) {
				roomNum += unit.getRoomList().size();
			}
			log.info(unitName+"������"+unitInfo.size()+"   ��������"+roomNum);
			Map<String,Object> unitMap = new HashMap<String,Object>();
			unitMap.put("unitName", unitName);
			unitMap.put("unitInfo", unitInfo);
			unitInfoList.add(unitMap);
		}
		int sold = Integer.parseInt(roomCountInfo.getSold());
		int unSold = Integer.parseInt(roomCountInfo.getUnSold());
		int preControl = Integer.parseInt(roomCountInfo.getPreControl());
		int total = Integer.parseInt(roomCountInfo.getTotal());
		roomPercentInfo.setSold(convertToPercent(sold*1.0/total)+"");
		roomPercentInfo.setUnSold(convertToPercent(unSold*1.0/total)+"");
		roomPercentInfo.setPreControl(convertToPercent(preControl*1.0/total)+"");
		httpSession.setAttribute("roomCountInfo", roomCountInfo);
		httpSession.setAttribute("roomPercentInfo",roomPercentInfo);
		httpSession.setAttribute("unitNameList", validUnitNameList);
		httpSession.setAttribute("unitInfoList", unitInfoList);
	}
	
	/**
	 * �ڵ�ǰ������������¥����Ϣ������ʾ������Ϣ
	 * @param bldInfo
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:xs:room:bulidInfo")
	@RequestMapping(value="/searchRoomInCurProBld")
	public String searchRoomInProBld(Model model,XsRoomSearchVo bldInfo) {
		log.info(bldInfo.getBldName()+"nnn");
		XsRoomSearchVo searchInfo = (XsRoomSearchVo)httpSession.getAttribute("proSearchInfo");
		if(bldInfo.getBldName() != null && bldInfo.getBldGuid() != null) {
			searchInfo.setBldName(bldInfo.getBldName());
			searchInfo.setBldGuid(bldInfo.getBldGuid());
			httpSession.setAttribute("bldGuid", bldInfo.getBldGuid());
			httpSession.setAttribute("bldFullName",bldInfo.getBldFullName());
		}
		else {//���ڴ���鿴������Ϣ��ķ��ز���������ʱbldInfo�и��ֶζ���null
			String bldGuid = (String)httpSession.getAttribute("bldGuid");
			searchInfo.setBldGuid(bldGuid);
		}
		log.info(searchInfo.getStatus());
		log.info(searchInfo.getBldName());
		log.info(searchInfo.getBldGuid());
		log.info(searchInfo.getProjGuid());
		log.info(searchInfo.getRoomStru());
		searchRoomInBld(searchInfo);
		//model.addAttribute("bldFullName",bldInfo.getBldFullName());
		return "/mbem/mcrm/house/room/index";
	}
	/**
	 * �ڵ�ǰ¥������
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:xs:room:searchRoom")
	@RequestMapping(value="/searchRoomInCurBuild")
	public String searchRoomInCurBuild(Model model,XsRoomSearchVo searchInfo) {
		log.info("searchInCurBuild");
		if(searchInfo.getMaxArea() != null) {
			httpSession.setAttribute("bldSearchInfo", searchInfo);
		}
		else {//�����ڵ�ǰ¥��������鿴������Ϣ�ķ��ز���
			searchInfo = (XsRoomSearchVo)httpSession.getAttribute("bldSearchInfo");
		}
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		String bldGuid = (String)httpSession.getAttribute("bldGuid");
		//httpSession.setAttribute("bldName", searchInfo.getBldName());
		searchInfo.setProjGuid(projGuid);//�����԰
		searchInfo.setBldGuid(bldGuid);
		searchRoomInBld(searchInfo);
		log.info(searchInfo.getBldFullName());
		log.info(searchInfo.getStatus());
		//model.addAttribute("bldFullName",searchInfo.getBldFullName());
		//httpSession.setAttribute("bldFullName",searchInfo.getBldFullName());
		return "/mbem/mcrm/house/room/index";
	}
	/**
	 * ѡ��¥�������ʾ������Ϣ
	 * @param model
	 * @param searchInfo
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:xs:room:selectRoomBulid")
	@RequestMapping(value="/selectBulidShow")
	public String selectBulidShow(Model model,XsRoomSearchVo searchInfo) {
		if(searchInfo.getBldGuid() != null && searchInfo.getBldFullName() != null) {
			httpSession.setAttribute("bldGuid", searchInfo.getBldGuid());
			httpSession.setAttribute("bldFullName", searchInfo.getBldFullName());
		}
		else {//���ڴ���ѡ��¥����ѯ������Ϣ��ĺ���
			searchInfo = new XsRoomSearchVo();
			String bldGuid = (String)httpSession.getAttribute("bldGuid");
			String bldFullName = (String)httpSession.getAttribute("bldFullName");
			searchInfo.setBldGuid(bldGuid);
			searchInfo.setBldFullName(bldFullName);
		}
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		//String projGuid = (String)httpSession.getAttribute("projGuid");
		//String bldName = (String)httpSession.getAttribute("bldName");
		
		searchInfo.setProjGuid(projGuid);
		//searchInfo.setBldName(bldName);
		searchRoomInBld(searchInfo);
		log.info(searchInfo.getBldFullName());
		//model.addAttribute("bldFullName",searchInfo.getBldFullName());
		
		return "/mbem/mcrm/house/room/index";
	}
	/**
	 * �ڵ�ǰ��������
	 * @param model
	 * @param searchInfo
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:xs:room:bulidInfo")
	@RequestMapping(value="searchRoomInCurProject")
	public String searchRoomInCurProject(Model model,XsRoomSearchVo searchInfo) {
		
		//String projGuid = (String)httpSession.getAttribute("projGuid");
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		searchInfo.setProjGuid(projGuid);
		//model.addAttribute("proSearchInfo", searchInfo);�����ӽ�ȥsession.getAttributeȡ����
		httpSession.setAttribute("proSearchInfo", searchInfo);
		List<XsRoomCountInfoVo> bldInfoList = xsRoomService.getXsBldInfoByProNo(searchInfo);
		log.info(bldInfoList.size());
		Map<String,Object> areaInfo = new HashMap<String,Object>();
		List<Map<String,Object>> allBldInfo = new ArrayList<Map<String,Object>>();
		List<XsRoomCountInfoVo> countList = new ArrayList<XsRoomCountInfoVo>();
		String name="";
		for(XsRoomCountInfoVo bldInfo:bldInfoList) {
			if(bldInfo.getTotal() == null) {
				if(countList.size() != 0){
					areaInfo.put("value", countList);
					areaInfo.put("name", name);
					allBldInfo.add(areaInfo);
				}
				areaInfo = new HashMap<String,Object>();
				//areaInfo.put("name", bldInfo.getName());
				name = bldInfo.getName();
				log.info("name"+name);
				countList = new ArrayList<XsRoomCountInfoVo>();
			}else {
				if(!bldInfo.getTotal().equals("0")) {
					countList.add(bldInfo);
				}
			}	
		}
		if(countList.size() != 0) {
			areaInfo.put("value", countList);
			areaInfo.put("name", name);
			allBldInfo.add(areaInfo);
		}
		model.addAttribute("searchBldInfoList", allBldInfo);
		return "/mbem/mcrm/house/room/bulidInfo";
	}
}
