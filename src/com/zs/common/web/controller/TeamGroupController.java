package com.zs.common.web.controller;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.busi.utils.LogUtil;
import com.zs.common.entity.DataTablesParameters;
import com.zs.common.util.RequestUtil;
import com.zs.common.web.vo.TreeViewForMap;
import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.rbac.service.XsTeamGroupService;
import com.zs.crm.web.vo.XsTeamGroupVo;
import com.zs.rbac.entity.User;
import com.zs.rbac.entity.UserExt;
import com.zs.rbac.service.UserManagerService;
@Controller
@RequestMapping(value="/wbem/system/sysSet")
public class TeamGroupController {
	
	Log log=LogUtil.getLog();
	@Autowired
	XsTeamGroupService xsTeamGroupService;
	@Autowired
	UserManagerService userManagerService;
	@Autowired
	HttpSession session;
	

	/**
	 * ���������
	 *  1 	���������Ŷӹ�����ҳ����ȡ��Ŀ�����Լ�ÿ����Ŀ��Ӧ�ķ��鼯�ϡ�
	 *  2	���ÿ����Ŀ����Ŀ������ȡ����Ŀ�����б����Ը���Ŀ�����б���в�����
	 *  3	���ÿ����Ŀ�ķ��飬��ȡ����Ŀ�����б����Ը���Ŀ��Ա�б���в�����
	 */
	
	/**
	 * �����Ŷӹ���ҳ��
	 * @return
	 */
	@RequestMapping(value="/teamGroupTree")
	public String teamGroupTree(Model model){
		List<XsTeamGroupEntity> projectList=xsTeamGroupService.getProjectItem();
		model.addAttribute("projectList", projectList);
		return "/wbem/system/sysSet/teamGroupTree";
	}
	/**
	 * �����Ŷ��������û���ϸҳ��
	 */
	@RequestMapping(value="/createTeamGroupMember")
	public String createTeamGroupMember(Model model ,String teamGroupId,String isProjectAdmin,String property){
		model.addAttribute("teamGroupId",teamGroupId );
		model.addAttribute("isProjectAdmin",isProjectAdmin);
		model.addAttribute("property",property);
		return "/wbem/system/sysSet/createUser";
	}
	/**
	 * �����û�����ҳ��
	 * @return
	 */
	@RequestMapping(value="/userExtBindingManager")
	public String userManager(){
		return "/wbem/system/sysSet/userExtBindingManager";
	}
	/**
	 * ������û�ҳ��
	 * @return
	 */
	@RequestMapping(value="/bindingUser")
	public String bindingUser(){
		return "/wbem/system/sysSet/bindingUser";
	}
//==========================================�Ŷӹ���================================================//
	/** ������ķ���
	 * @param parentID���ϼ�id
	 * @param isProjectAdmin���Ƿ�Ϊ����Ա
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getTeamGroupTree")
	public List<TreeViewForMap> getteamGroupTree(String parentID,String isProjectAdmin,String groupType){
		log.info(parentID+" "+isProjectAdmin+" "+groupType);
		List<TreeViewForMap> result = new ArrayList<TreeViewForMap>();
		//�����Ŀ
		if(parentID==null || "".equals(parentID)){
			List<XsTeamGroupEntity> list=xsTeamGroupService.getProjectItem();
			for (XsTeamGroupEntity xsTeamGroupEntity : list) {
				TreeViewForMap tvf = new TreeViewForMap();
				tvf.put("id",xsTeamGroupEntity.getId());
				tvf.put("parId",xsTeamGroupEntity.getId());
				tvf.put("name",xsTeamGroupEntity.getName());
				tvf.put("icon", "../../../common/images/ztree/b.png");
				tvf.set("isParent", "true");
				result.add(tvf);
			}
		//���-��Ŀ����
		}else if(parentID !=null && !"".equals(parentID) &&(isProjectAdmin==null || "".equals(isProjectAdmin))){
			List<XsTeamGroupEntity> list=xsTeamGroupService.getTeamGroupFromProject(parentID,"1",groupType);
			for (XsTeamGroupEntity xsTeamGroupEntity : list) {
				TreeViewForMap tvf = new TreeViewForMap();
				tvf.put("parId",xsTeamGroupEntity.getProjectId());
				tvf.put("id",xsTeamGroupEntity.getId());
				tvf.put("projectId",xsTeamGroupEntity.getProjectId());
				tvf.put("name",xsTeamGroupEntity.getName());
				tvf.put("icon", "../../../common/images/ztree/c.png");
				tvf.put("projectName",xsTeamGroupEntity.getProjectName());
				tvf.put("groupType",xsTeamGroupEntity.getGroupType());
				tvf.put("isProjectAdmin", "1");
				tvf.set("isParent", "true");
				result.add(tvf);
			}
		//���-��Ŀ����
		}else if(isProjectAdmin!=null && !"".equals(isProjectAdmin)){
			List<XsTeamGroupEntity> list=xsTeamGroupService.getTeamGroupFromProject(parentID,"0",groupType);
			for (XsTeamGroupEntity xsTeamGroupEntity : list) {
				TreeViewForMap tvf = new TreeViewForMap();
				tvf.put("parId",xsTeamGroupEntity.getProjectId());
				tvf.put("id",xsTeamGroupEntity.getId());
				tvf.put("projectId",xsTeamGroupEntity.getProjectId());
				tvf.put("name",xsTeamGroupEntity.getName());
				tvf.put("icon", "../../../common/images/ztree/a.png");
				tvf.put("projectName",xsTeamGroupEntity.getProjectName());
				tvf.put("groupType",xsTeamGroupEntity.getGroupType());
				tvf.set("isParent", "false");
				result.add(tvf);
			}
		}
		return result;
		
	}
	/**
	 * ���ÿ��ĳ�Ա
	 * @param request
	 * @param teamGroupId����id
	 * @param projectId����Ŀid
	 * @return
	 */
	@RequestMapping(value="/getTeamGroupMember")
	@ResponseBody
	public Map<String, Object> getTeamGroupMember(HttpServletRequest request,String teamGroupId,String projectId,String userName,String property){
		log.info("getTeamGroupMember tid "+teamGroupId+" pid "+projectId+" pro "+property);
		DataTablesParameters para= RequestUtil.getDTPara(request);
		int rows=para.getLength();
		int startLength=para.getStart();
		String sortName=para.getOrderColumn();
		String sortdes=para.getOrderDir();
		try {
			userName=URLDecoder.decode(userName, "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.info(e);
		}
		if("xs".equals(property)){
			property="xs_userGuid";
		}else if("zs".equals(property)){
			property="bs_userGuid";
		}
		List<XsTeamGroupEntity> list=xsTeamGroupService.getUserFromTeamGroup(teamGroupId, projectId,userName,sortName,sortdes,property);
		List<XsTeamGroupEntity> listData=xsTeamGroupService.getUserFromTeamGroup(startLength,rows,teamGroupId, projectId,userName,sortName,sortdes,property);
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		int i=para.getStart()+1;
		for (XsTeamGroupEntity entity : listData) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("index","<label class='checkbox inline index' style='margin-top: -8px;'> <input type='checkbox' style='margin-top: 3px;' name='radios' />"+i+"</label>");
			map.put("userTeamId", entity.getUserTeamId());
			map.put("userName", entity.getUserName());
			map.put("projectId", entity.getProjectId());
			map.put("groupName", entity.getGroupName());
			map.put("userLevelId", entity.getUserLevelId());
			map.put("id", entity.getUserTeamId());
			i++;
			lst.add(map);
		}
		Map<String,Object> jsonResult = new HashMap<String,Object>();
		jsonResult.put("draw", para.getDraw());
		jsonResult.put("recordsTotal", list.size());
		jsonResult.put("recordsFiltered", list.size());
		jsonResult.put("data",lst);
		return jsonResult;
	}
	public Map<String, Object> resultToTable(int page,int rows,int totalRecords,List listData ){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("total", totalRecords/rows==0 ? 1:totalRecords/rows);
		map.put("records", totalRecords);
		map.put("rows", listData);
		return map;
	}
	/**
	 * ���������Ա 
	 * @param userId :�û�id
	 * @param teamGroupId������id
	 * @param userLevelId���û��ȼ� �鳤/��Ա
	 * @param description ������
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insertUserToTeamGroup")
	public Object insertUserToTeamGroup(String userId,String userLevelId,String teamGroupId){
		//String teamGroupId=(String) session.getAttribute("teamGroupId");
		log.info(userLevelId);
		log.info(userId+" ulid "+userLevelId+" tid "+teamGroupId);
		boolean _result;
		String description = null;
		String[] userIdAarray=userId.split(",");
		String[] userLevelIdAarray=userLevelId.split(",");
		List<XsTeamGroupEntity> list=xsTeamGroupService.judgeUserIfExistInTeamGroup(userIdAarray, teamGroupId);
		if(list.size()==0){
			//����û�иó�Ա
			List<XsTeamGroupVo> listvo= new ArrayList<XsTeamGroupVo>();
			XsTeamGroupVo vo=null;
			for (int i = 0; i < userLevelIdAarray.length; i++) {
				if("1".equals(userLevelIdAarray[i])){
					description="�鳤";
				}else if("2".equals(userLevelIdAarray[i])){
					description="��ҵ����";
				}else if ("0".equals(userLevelIdAarray[i])){
					description="����";
				}
				vo=new XsTeamGroupVo(userIdAarray[i], description, teamGroupId, userLevelIdAarray[i]);
				listvo.add(vo);
			}
			log.info(listvo.size());
			int _int=xsTeamGroupService.insertUserToTeamGroup(listvo);
			
			if(_int !=0){
				_result=true;
			}else{
				_result=false;//�����������
			}
			return _result;
		}else{
			return list;
		}
		
	}
	@RequestMapping(value="/1")
	public String getUsersFromXsCRM(String userName){
		List<XsTeamGroupEntity> list=xsTeamGroupService.getUsersFromXsCRM(userName);
		log.info(list.size());
		return null;
	}
	
//===============================��Ա����=====================================//
	/**
	 * �����Ҫ�󶨵���Ա����
	 * @param username 
	 * @param propery : ָ���û���չ���е���������
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getPreConnectUsers")
	public Map<String, Object> getPreConnectUsers(HttpServletRequest request,String userName ,String property){
		try {
			userName=URLDecoder.decode(userName,"utf-8");
		} catch (UnsupportedEncodingException e) {
			log.info(e);
		}
		DataTablesParameters para= RequestUtil.getDTPara(request);
		int rows=para.getLength();
		log.info(rows);
		int startLength=para.getStart();
		log.info(startLength);
		String sortName=para.getOrderColumn();
		String sortdes=para.getOrderDir();
		//property="xs_userGuid";
		List<XsTeamGroupEntity> list=xsTeamGroupService.getPreConnectUsers(userName, property);
		List<XsTeamGroupEntity> listData=xsTeamGroupService.getPreConnectUsers(userName, property,startLength,rows,sortName,sortdes);
		log.info(list.size());
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		int i=para.getStart()+1;
		for (XsTeamGroupEntity entity : listData) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("index","<label class='checkbox inline index' style='margin-top: -5px;'> <input type='checkbox' style='margin-top: 3px;' name='radios' />"+i+"</label>");
			map.put("userId", entity.getUserId());
			map.put("userName", entity.getUserName());
			map.put("realName", entity.getRealName());
			map.put("userLevel", entity.getUserLevelId());
			map.put("userLevelId", entity.getUserLevelId());
			map.put("mobile", entity.getMobile());
			map.put("id", entity.getId());
			i++;
			lst.add(map);
		}
		Map<String,Object> jsonResult = new HashMap<String,Object>();
		jsonResult.put("draw", para.getDraw());
		jsonResult.put("recordsTotal", list.size());
		jsonResult.put("recordsFiltered", list.size());
		jsonResult.put("data",lst);
		
		return jsonResult;
		
	}
	/**
	 * ����Ѿ��󶨵���Ա����
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getConnectedUsers")
	public Map<String, Object> getConnectedUsers(HttpServletRequest request,String userName,String property){
		
		DataTablesParameters para= RequestUtil.getDTPara(request);
		int rows=para.getLength();
		log.info(rows);
		
		int startLength=para.getStart();
		log.info(startLength);
		String sortName=para.getOrderColumn();
		String sortdes=para.getOrderDir();
		//String property="xs_userGuid";
		if("xs".equals(property)){
			property="xs_userGuid";
		}else if("zs".equals(property)){
			property="bs_userGuid";
		}
		try {
			userName=URLDecoder.decode(userName,"utf-8");
		} catch (UnsupportedEncodingException e) {
			log.info(e);
		}
		List<XsTeamGroupEntity> list=xsTeamGroupService.getConnectedUsers(userName, property);
		List<XsTeamGroupEntity> listData=xsTeamGroupService.getConnectedUsers(userName, property,startLength,rows,sortName,sortdes);
		log.info(listData.size());
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		int i=para.getStart()+1;
		for (XsTeamGroupEntity entity : listData) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("index","<label class='checkbox inline index' style='margin-top: -6px;'> <input type='checkbox' style='margin-top: 3px;' name='radios' />"+i+"</label>");
			map.put("userId", entity.getUserId());
			map.put("userName", entity.getUserName());
			map.put("realName", entity.getRealName());
			map.put("userLevel", entity.getUserLevelId());
			map.put("userLevelId", entity.getUserLevelId());
			map.put("mobile", entity.getMobile());
			map.put("id", entity.getId());
			map.put("groupType", entity.getGroupType());
			i++;
			lst.add(map);
		}
		Map<String,Object> jsonResult = new HashMap<String,Object>();
		jsonResult.put("draw", para.getDraw());
		jsonResult.put("recordsTotal", list.size());
		jsonResult.put("recordsFiltered", list.size());
		jsonResult.put("data",lst);
		
		return jsonResult;
		
	}
	/**
	 * ��rbac �û��� crm �����û����а� 
	 * @param userId �û�id
	 * @param userName���û�����
	 * @param property��������
	 * @param value��������ֵ
	 * @param description������������
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insertUserConnectExt")
	public String insertUserConnectExt(String userId,String property ){
		log.info(userId);
		String[] userIdAarray=userId.split(",");
		XsTeamGroupVo vo=null;
		List<XsTeamGroupVo> listvo= new ArrayList<XsTeamGroupVo>();
		boolean _res=true;
		String realName="";
		String result="";
		if("xs_userGuid".equals(property)){
			String description="������crm�������û�id";
			for (int i = 0; i < userIdAarray.length; i++) {
				log.info(userIdAarray.length);
				log.info(userIdAarray[i]);
				realName=xsTeamGroupService.getPreConnectUsersByUserId(userIdAarray[i],property).get(0).getRealName();
				List<XsTeamGroupEntity> crmLIST=xsTeamGroupService.getUsersFromXsCRM(realName);
				if(crmLIST.size()>0){
					log.info("ddssfd");
					String value=crmLIST.get(0).getUserGUID();
					vo=new XsTeamGroupVo();
					vo.setUserId(userIdAarray[i]);
					vo.setProperty(property);
					vo.setValue(value);
					vo.setUserName(realName);
					vo.setDescription(description);
					listvo.add(vo);
				}else{
					log.info("dddd");
					_res=false;
					result="{\"data\":\""+realName+"\"}";
					break;
				}
			}
		}else if("bs_userGuid".equals(property)){
			String description="�������û�id";
			for (int i = 0; i < userIdAarray.length; i++) {
				log.info(userIdAarray.length);
				log.info(userIdAarray[i]);
				realName=xsTeamGroupService.getPreConnectUsersByUserId(userIdAarray[i],property).get(0).getRealName();
				vo=new XsTeamGroupVo();
				vo.setUserId(userIdAarray[i]);
				vo.setProperty(property);
				vo.setValue(userIdAarray[i]);
				vo.setUserName(realName);
				vo.setDescription(description);
				listvo.add(vo);
			}
		}
		if(_res){
			log.info("dd");
			xsTeamGroupService.insertUserConnectExt(listvo);
			result="true";
		}
		log.info(result);
		return result;
	}
	/**
	 *  �����
	 * @param id:�û���չ���е�������id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteUserConnect")
	public int deleteUserConnect(String id){
		log.info(id);
		int _int=xsTeamGroupService.deleteUserConnect(id);
		return _int;
	}
	/**
	 * ���������Ŀ�б� 
	 * @param �� �����б�����ȡ���е���Ŀ
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getProjectItem")
	public List<XsTeamGroupEntity> getProjectItem(){
		List<XsTeamGroupEntity> list=xsTeamGroupService.getProjectItem();
		log.info(list.size());
		return list;
		
	}
	/**
	 * ���������Ŀ��Ӧ���Ŷӷ�����Ϣ
	 * @param �ޣ����б�����ȡÿ����Ŀ��Ӧ����Ŀ����
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getTeamGroupAllFromProject")
	public List<XsTeamGroupEntity> getTeamGroupFromProject(String projectId){
		List<XsTeamGroupEntity> list=xsTeamGroupService.getTeamGroupFromProject(projectId);
		log.info(list.size());
		return list;
		
	}
	/**
	 * �����Ŀ������Ϣ
	 * @param projectId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getAdminTeamGroupAllFromProject")
	public List<XsTeamGroupEntity> getAdminTeamGroupFromProject(String projectId,String isProjectAdmin,String groupType){
		List<XsTeamGroupEntity> list=xsTeamGroupService.getAdminTeamGroupFromProject(projectId,isProjectAdmin,groupType);
		log.info(list.size());
		return list;
		
	}
	@ResponseBody
	@RequestMapping(value="/getTeamGroupAllFromProjectInit")
	public List<XsTeamGroupEntity> getTeamGroupFromProjectInit(){
		String projectId=(String) session.getAttribute("projectId");
		log.info(projectId);
		List<XsTeamGroupEntity> list=xsTeamGroupService.getTeamGroupFromProject(projectId);
		log.info(list.size());
		return list;
		
	}


	/**
	 * ����������ʱ���жϸ÷����Ƿ��Ѿ�����  
	 * @param groupName : ����ķ�������
	 * @param projectId �� ��Ŀ��id��
	 * @return��0 ˵�������ڣ���0 ˵�������Ѿ�����
	 */
	public int judgeTeamGroupIfExist(String groupName,String projectId){
		int _int=xsTeamGroupService.judgeTeamGroupIfExist(groupName, projectId);
		log.info(_int);
		return _int;
	}
	/**
	 *  ����Ӧ��Ŀ���ӷ���
	 * @param groupName : ��������Ŀ��������
	 * @param projectId ����Ŀid
	 * @param isProjectAdmin���Ƿ�Ϊ��Ŀ����Ա
	 * @param description������
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insertTeamGroup")
	public int insertTeamGroup(String groupName,String projectId,String isProjectAdmin,String description,String groupType,String projectName){
		try {
			groupName=URLDecoder.decode(groupName, "utf-8");
			description=URLDecoder.decode(description, "utf-8");
			projectName=URLDecoder.decode(projectName, "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.info(e);
		}
		log.info(groupName+" "+projectId+" "+isProjectAdmin+" "+description);
		int _int=-2;
		if("1".equals(isProjectAdmin)){
			List<XsTeamGroupEntity> list=xsTeamGroupService.getTeamGroupFromProject(projectId,"1",groupType);
			if(list.size()>0){
				_int=-1;
			}else{
				_int=xsTeamGroupService.insertTeamGroup(groupName, projectId, isProjectAdmin, description,groupType,projectName);
				log.info(_int);
			}
		}else{
			_int=xsTeamGroupService.insertTeamGroup(groupName, projectId, isProjectAdmin, description,groupType,projectName);
			log.info(_int);
		}
		return _int;
		
	}
	@ResponseBody
	@RequestMapping(value="/updateTeamGroup")
	public int updateTeamGoup(String teamGroupId,String groupName){
		try {
			groupName=URLDecoder.decode(groupName, "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.info(e);
		}
		int _int=xsTeamGroupService.updateTeamGoup(teamGroupId, groupName);
		log.info(_int);
		return _int;
		
	}
	@ResponseBody
	@RequestMapping(value="/deleteTeamGroup")
	public int deleteTeamGroup(String teamGroupId,String projectId,String groupType){
		int _int=xsTeamGroupService.deleteTeamGoup(teamGroupId, projectId,groupType);
		log.info(_int);
		return _int;
		
	}
	/**
	 * ��������Աʱ���жϸó�Ա�Ƿ��Ѿ������ڸ���Ŀ����
	 * @param userId ��ѡ�е��û���id��
	 * @param teamGroupId ����Ӧ����Ŀ�����id��
	 * @return 0 ˵�������ڣ���0 ˵�������Ѿ�����
	 */
	/*public int judgeUserIfExistInTeamGroup(String userId,String teamGroupId){
		int _int=xsTeamGroupService.judgeUserIfExistInTeamGroup(userId, teamGroupId);
		log.info(_int);
		return _int;
	}*/

	/**
	 * �༭�����Ա  ���ó�Ա�Ƶ�������   ���ó�Ա����Ϊ�鳤����Ա 
	 * @param id:Ҫ�޸ĵ��û����û�_�Ŷӷ�����е�������id
	 * @param teamGroupId �� �Ŷӷ����id�ţ��޸Ĵ�ֵ���Խ���Ա�Ƶ���������
	 * @param userLevelId�� �û��ļ����鳤/��Ա
	 * @param description������
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateUserInTeamGroup")
	public String updateUserInTeamGroup(String id,String teamGroupId,String userLevelId,String property){
		log.info(id+" "+teamGroupId+" "+userLevelId+property);
		String realDesc="";
		String result="";
		if("0".equals(userLevelId)){
			realDesc="����";
			List<XsTeamGroupEntity> list=xsTeamGroupService.getTeamGroupFromProjectById(teamGroupId);
			if("0".equals(list.get(0).getIsProjectAdmin())){
				return 0+"";
			}
		}else if("1".equals(userLevelId)){
			realDesc="�鳤";
		}else if("2".equals(userLevelId)){
			realDesc="��ҵ����";
		}
		if("xs".equals(property)){
			property="xs_userGuid";
		}else if("zs".equals(property)){
			property="bs_userGuid";
		}
		List<UserExt> ulit=xsTeamGroupService.judgeIfCorSystem(id, property);
		if(ulit.size()==0){
			List<User> userList=userManagerService.getUserByUserExt(Integer.decode(id));
			int userID=userList.get(0).getUserID();
			String userName=userList.get(0).getUsername();
			String realName=userList.get(0).getRealName();
			List<XsTeamGroupVo> listvo= new ArrayList<XsTeamGroupVo>();
			if("xs_userGuid".equals(property)){
				List<XsTeamGroupEntity> crmLIST=xsTeamGroupService.getUsersFromXsCRM(realName);
				if(crmLIST.size()>0){
					String value=crmLIST.get(0).getUserGUID();
					XsTeamGroupVo vo=new XsTeamGroupVo();
					vo.setUserId(userID+"");
					vo.setProperty(property);
					vo.setValue(value);
					vo.setUserName(realName);
					//vo.setDescription(description);
					listvo.add(vo);
					xsTeamGroupService.insertUserConnectExt(listvo);
					int _int=xsTeamGroupService.updateUserInTeamGroup(id, teamGroupId, userLevelId, realDesc);
					result="1";
				}else{
					result="{\"data\":\""+realName+"\"}";
				}
				
			}else if("bs_userGuid".equals(property)){
				XsTeamGroupVo vo=new XsTeamGroupVo();
				vo.setUserId(userID+"");
				vo.setProperty(property);
				vo.setValue(userID+"");
				vo.setUserName(userName);
				//vo.setDescription(description);
				listvo.add(vo);
				xsTeamGroupService.insertUserConnectExt(listvo);
				int _int=xsTeamGroupService.updateUserInTeamGroup(id, teamGroupId, userLevelId, realDesc);
				result="1";
			}
			
			
		}else{
			int _int=xsTeamGroupService.updateUserInTeamGroup(id, teamGroupId, userLevelId, realDesc);
			result="1";
		}
		//log.info("ddddupup  "+_int);
		return result;
	}
	/**
	 *  �Ƴ������Ա
	 * @param id �û����û�_�Ŷӷ�����е�������id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteUserFromTeamGroup")
	public int deleteUserFromTeamGroup(String userTeamId){
		log.info(userTeamId);
		int _int=xsTeamGroupService.deleteUserFromTeamGroup(userTeamId);
		log.info(_int);
		return _int;
		
	}
	//-------------------------------�û�����------------------------------------


	
	
}
