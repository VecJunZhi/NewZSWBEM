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
	 * 需求分析：
	 *  1 	进入销售团队管理首页，获取项目集合以及每个项目对应的分组集合。
	 *  2	点击每个项目的项目经理，获取该项目经理列表，并对该项目经理列表进行操作。
	 *  3	点击每个项目的分组，获取该项目经理列表，并对该项目成员列表进行操作。
	 */
	
	/**
	 * 进入团队管理页面
	 * @return
	 */
	@RequestMapping(value="/teamGroupTree")
	public String teamGroupTree(Model model){
		List<XsTeamGroupEntity> projectList=xsTeamGroupService.getProjectItem();
		model.addAttribute("projectList", projectList);
		return "/wbem/system/sysSet/teamGroupTree";
	}
	/**
	 * 进入团队中新增用户明细页面
	 */
	@RequestMapping(value="/createTeamGroupMember")
	public String createTeamGroupMember(Model model ,String teamGroupId,String isProjectAdmin,String property){
		model.addAttribute("teamGroupId",teamGroupId );
		model.addAttribute("isProjectAdmin",isProjectAdmin);
		model.addAttribute("property",property);
		return "/wbem/system/sysSet/createUser";
	}
	/**
	 * 进入用户管理页面
	 * @return
	 */
	@RequestMapping(value="/userExtBindingManager")
	public String userManager(){
		return "/wbem/system/sysSet/userExtBindingManager";
	}
	/**
	 * 进入绑定用户页面
	 * @return
	 */
	@RequestMapping(value="/bindingUser")
	public String bindingUser(){
		return "/wbem/system/sysSet/bindingUser";
	}
//==========================================团队管理================================================//
	/** 获得树的方法
	 * @param parentID：上级id
	 * @param isProjectAdmin：是否为管理员
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getTeamGroupTree")
	public List<TreeViewForMap> getteamGroupTree(String parentID,String isProjectAdmin,String groupType){
		log.info(parentID+" "+isProjectAdmin+" "+groupType);
		List<TreeViewForMap> result = new ArrayList<TreeViewForMap>();
		//获得项目
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
		//获得-项目经理
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
		//获得-项目分组
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
	 * 获得每组的成员
	 * @param request
	 * @param teamGroupId：组id
	 * @param projectId：项目id
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
	 * 新增组里成员 
	 * @param userId :用户id
	 * @param teamGroupId：分组id
	 * @param userLevelId：用户等级 组长/组员
	 * @param description ：描述
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
			//组里没有该成员
			List<XsTeamGroupVo> listvo= new ArrayList<XsTeamGroupVo>();
			XsTeamGroupVo vo=null;
			for (int i = 0; i < userLevelIdAarray.length; i++) {
				if("1".equals(userLevelIdAarray[i])){
					description="组长";
				}else if("2".equals(userLevelIdAarray[i])){
					description="置业顾问";
				}else if ("0".equals(userLevelIdAarray[i])){
					description="经理";
				}
				vo=new XsTeamGroupVo(userIdAarray[i], description, teamGroupId, userLevelIdAarray[i]);
				listvo.add(vo);
			}
			log.info(listvo.size());
			int _int=xsTeamGroupService.insertUserToTeamGroup(listvo);
			
			if(_int !=0){
				_result=true;
			}else{
				_result=false;//批量插入出错
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
	
//===============================成员管理=====================================//
	/**
	 * 获得需要绑定的人员集合
	 * @param username 
	 * @param propery : 指定用户扩展表中的属性名称
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
	 * 获得已经绑定的人员名单
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
	 * 将rbac 用户与 crm 销售用户进行绑定 
	 * @param userId 用户id
	 * @param userName：用户名称
	 * @param property：绑定属性
	 * @param value：绑定属性值
	 * @param description：绑定属性描述
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
			String description="与销售crm关联的用户id";
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
			String description="与招商用户id";
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
	 *  解除绑定
	 * @param id:用户扩展表中的自增长id
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
	 * 获得销售项目列表 
	 * @param 无 ：进行遍历获取所有的项目
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
	 * 获得销售项目对应的团队分组信息
	 * @param 无：进行遍历获取每个项目对应的项目分组
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
	 * 获得项目分组信息
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
	 * 当新增分组时，判断该分组是否已经存在  
	 * @param groupName : 输入的分组名称
	 * @param projectId ： 项目的id号
	 * @return：0 说明不存在，非0 说明该组已经存在
	 */
	public int judgeTeamGroupIfExist(String groupName,String projectId){
		int _int=xsTeamGroupService.judgeTeamGroupIfExist(groupName, projectId);
		log.info(_int);
		return _int;
	}
	/**
	 *  对相应项目增加分组
	 * @param groupName : 新增的项目分组名称
	 * @param projectId ：项目id
	 * @param isProjectAdmin：是否为项目管理员
	 * @param description：描述
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
	 * 当新增成员时，判断该成员是否已经存在于该项目组中
	 * @param userId ：选中的用户的id号
	 * @param teamGroupId ：对应的项目分组的id号
	 * @return 0 说明不存在，非0 说明该组已经存在
	 */
	/*public int judgeUserIfExistInTeamGroup(String userId,String teamGroupId){
		int _int=xsTeamGroupService.judgeUserIfExistInTeamGroup(userId, teamGroupId);
		log.info(_int);
		return _int;
	}*/

	/**
	 * 编辑组里成员  将该成员移到其他组   将该成员定义为组长或组员 
	 * @param id:要修改的用户在用户_团队分组表中的自增长id
	 * @param teamGroupId ： 团队分组的id号，修改此值可以将成员移到其他组里
	 * @param userLevelId： 用户的级别，组长/组员
	 * @param description：描述
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateUserInTeamGroup")
	public String updateUserInTeamGroup(String id,String teamGroupId,String userLevelId,String property){
		log.info(id+" "+teamGroupId+" "+userLevelId+property);
		String realDesc="";
		String result="";
		if("0".equals(userLevelId)){
			realDesc="经理";
			List<XsTeamGroupEntity> list=xsTeamGroupService.getTeamGroupFromProjectById(teamGroupId);
			if("0".equals(list.get(0).getIsProjectAdmin())){
				return 0+"";
			}
		}else if("1".equals(userLevelId)){
			realDesc="组长";
		}else if("2".equals(userLevelId)){
			realDesc="置业顾问";
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
	 *  移除组里成员
	 * @param id 用户在用户_团队分组表中的自增长id
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
	//-------------------------------用户管理------------------------------------


	
	
}
