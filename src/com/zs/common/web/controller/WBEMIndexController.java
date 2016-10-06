package com.zs.common.web.controller;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.log.Log;
import com.zs.common.entity.DataTablesParameters;
import com.zs.common.entity.ZsUpdateLogEntity;
import com.zs.common.service.ZsUpdateLogService;
import com.zs.common.util.DateUtil;
import com.zs.common.util.RequestUtil;
import com.zs.crm.entity.XsCustomersManagerEntity;
import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.rbac.core.RBACSubject;
import com.zs.rbac.core.UserAuthentication;
import com.zs.rbac.entity.Menu;
import com.zs.rbac.entity.Role;
import com.zs.rbac.entity.SubSystem;
import com.zs.rbac.entity.User;
import com.zs.rbac.entity.UserExt;
import com.zs.rbac.service.IUserExtService;
import com.zs.rbac.service.UserManagerService;
import com.zs.rbac.service.XsTeamGroupService;
/**
 * PC端主框架
 * @author jiarui
 *
 */
@Controller
@RequestMapping(value="/wbem")
public class WBEMIndexController {
	@Autowired
	ZsUpdateLogService zsUpdateLogService;
	@Autowired
	XsTeamGroupService xsTeamGroupService;
	@Autowired
	IUserExtService userExtService;
	@Autowired
	UserManagerService userManagerService;
	@RequestMapping(value="/index")
	public String indexPage(Model model) {
		Map<String,Map<String,List<Menu>>> menuMap = RBACSubject.getMenu();
		List<SubSystem> sysList =  RBACSubject.getSubSystem();
		Session session = RBACSubject.getSecurityUtils().getSession();
		session.setAttribute("menuMap", menuMap);
		session.setAttribute("sysList", sysList);
		session.setAttribute("menuId", "0");//首页不显示菜单
		
		User user = (User)session.getAttribute("user");
		String userName=user.getUsername();
		model.addAttribute("userName",userName);
		
		/*List<ZsUpdateLogEntity> zsReadLogList = new ArrayList<ZsUpdateLogEntity>();
		ZsUpdateLogEntity zsUpdateLog = new ZsUpdateLogEntity();
		zsReadLogList = zsUpdateLogService.getReadTypeDao(zsUpdateLog);
		model.addAttribute("zsReadLogList", zsReadLogList);*/
		String projGuid = user.getExtInfo("projGuid");
		session.setAttribute("projGuid", "\'"+projGuid+"\'");
		session.setAttribute("systemId", user.getExtInfo("systemId")==null?0:user.getExtInfo("systemId"));
		
		List<XsTeamGroupEntity> allProjectList = new ArrayList<XsTeamGroupEntity>();
		List<XsTeamGroupEntity> projectList = new ArrayList<XsTeamGroupEntity>();
		Set<String> projectSet = new HashSet<String>();
		allProjectList = xsTeamGroupService.getProjectItem();//获得所有项目列表 
		int userID = user.getUserID();
		List<Role> roleList = new ArrayList<Role>();
		roleList = userManagerService.getProjByUserIdOrRoleId(userID, 0);
		for(Role role:roleList){
			//遍历role对象中的项目列表
			if(role.getProjGuid() != null && !"".equals(role.getProjGuid())) {
				List<String> projGuidList = new ArrayList<String>(Arrays.asList(role.getProjGuid().split(",")));
				projectSet.addAll(projGuidList);
			}
		}
		for(XsTeamGroupEntity project:allProjectList) {
			for(String projectId:projectSet) {
				if(project.getId().equals(projectId)) {
					projectList.add(project);
				}
			}
		}
		session.setAttribute("projectList", projectList);
		return "/wbem/index";
	}
	@RequestMapping(value="/frame/tree")
	public String tree() {
		return "/wbem/frame/tree";
	}
	@RequestMapping(value="/frame/msgCenter")
	public String msgCenter() {
		return "/wbem/frame/msgCenter";
	}
	/**
	 * 日志界面
	 * jixh
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index/updateLog")
	public String updateLog(Model model) {
		User user = (User)SecurityUtils.getSubject().getSession().getAttribute("user");
		String userName=user.getUsername();
		model.addAttribute("userName",userName);
		
		List<User> userNameList = new ArrayList<User>();
		userNameList=zsUpdateLogService.getUserNameDao();
		model.addAttribute("userNameList",userNameList);
		
		/*List<ZsUpdateLogEntity> zsReadLogList = new ArrayList<ZsUpdateLogEntity>();
		ZsUpdateLogEntity zsUpdateLog = new ZsUpdateLogEntity();
		zsReadLogList = zsUpdateLogService.getReadTypeDao(zsUpdateLog);
		model.addAttribute("zsReadLogList", zsReadLogList);*/
		return "/wbem/index/updateLog";
	}
	/**
	 * 日志列表
	 * jixh
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index/updateLogContent")
	@ResponseBody
	public Map<String,Object> updateLog(HttpServletRequest request,String logClasses,String userName,String logType) throws UnsupportedEncodingException{
		//获得总数
		ZsUpdateLogEntity zsUpdateLogEntityS = new ZsUpdateLogEntity();
		String logClassesNew =  URLDecoder.decode(logClasses,"utf-8");
		String userNameNew = URLDecoder.decode(userName,"utf-8");
		zsUpdateLogEntityS.setLogClasses(logClassesNew);
		zsUpdateLogEntityS.setUserName(userNameNew);
		int recordsTotal = zsUpdateLogService.getZsUpdateLogDao(zsUpdateLogEntityS).size();
		int recordsFiltered=recordsTotal;
		//分页
		DataTablesParameters para= RequestUtil.getDTPara(request);
		int i=para.getStart()+1;
		int startIndex = para.getStart();
		int length = para.getLength();
		para.setColumnArray(new String[]{"","logTime","","","","","",""});
		List<Map<String,Object>> lst = new ArrayList<Map<String,Object>>();
		List<ZsUpdateLogEntity> zsUpdateLogList = new ArrayList<ZsUpdateLogEntity>();
		ZsUpdateLogEntity zsUpdateLogEntity = new ZsUpdateLogEntity();
		zsUpdateLogEntity.setLogClasses(logClassesNew);
		zsUpdateLogEntity.setUserName(userNameNew);
		zsUpdateLogEntity.setStartIndex(startIndex);
		zsUpdateLogEntity.setLength(length);
		zsUpdateLogList=zsUpdateLogService.getZsUpdateLogDao(zsUpdateLogEntity);
		for(ZsUpdateLogEntity zsUpdateLog:zsUpdateLogList) {
			Map<String,Object> zsUpdateMap = new HashMap<String,Object>();
			zsUpdateMap.put("logId", "<label class='checkbox inline index'> <input type='checkbox' name='radios' />"+i+"</label>");
			zsUpdateMap.put("logTime", zsUpdateLog.getLogTime());
			zsUpdateMap.put("logSubject", zsUpdateLog.getLogSubject());
			zsUpdateMap.put("logContent", zsUpdateLog.getLogContent());
			zsUpdateMap.put("logClasses", zsUpdateLog.getLogClasses());
			zsUpdateMap.put("userName", zsUpdateLog.getUserName());
			zsUpdateMap.put("remark", zsUpdateLog.getRemark());
			i++;
			lst.add(zsUpdateMap);
		}
		Map<String,Object> jsonResult = new HashMap<String,Object>();
		jsonResult.put("draw", para.getDraw());
		jsonResult.put("recordsTotal", recordsTotal);
		jsonResult.put("recordsFiltered", recordsFiltered);
		jsonResult.put("data",lst);
		return jsonResult;
	}
	/**
	 * 新增日志
	 * jixh
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index/addUpdateLog")
	@ResponseBody
	public String addUpdateLog(String data) {
		int k=0;
		try {
			JSONObject object =JSONObject.fromObject(data);
			String logSubject =(String)object.get("logSubject");
			String logContent =(String)object.get("logContent");
			String logClasses =(String)object.get("logClasses");
			String userName =(String)object.get("userName");
			logSubject=URLDecoder.decode(logSubject,"utf-8");
			logContent=URLDecoder.decode(logContent,"utf-8");
			logClasses=URLDecoder.decode(logClasses,"utf-8");
			userName=URLDecoder.decode(userName,"utf-8");
			ZsUpdateLogEntity zsUpdateLog = new ZsUpdateLogEntity();
			zsUpdateLog.setLogSubject(logSubject);
			zsUpdateLog.setLogContent(logContent);
			zsUpdateLog.setLogClasses(logClasses);
			zsUpdateLog.setUserName(userName);
			k=zsUpdateLogService.insertUpdateLogDao(zsUpdateLog);
		}catch (Exception e) {
			k=0;
		}
		return k+"";
	}
	/**
	 * 编辑日志
	 * jixh
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index/editUpdateLog")
	@ResponseBody
	public String editUpdateLog(String data) {
		int k=0;
		try {
			JSONObject object =JSONObject.fromObject(data);
			String logSubject =(String)object.get("logSubject");
			String logContent =(String)object.get("logContent");
			String logTime = (String)object.get("logTime");
			logSubject=URLDecoder.decode(logSubject,"utf-8");
			logContent=URLDecoder.decode(logContent,"utf-8");
			ZsUpdateLogEntity zsUpdateLog = new ZsUpdateLogEntity();
			zsUpdateLog.setLogSubject(logSubject);
			zsUpdateLog.setLogContent(logContent);
			zsUpdateLog.setLogTime(logTime);
			k=zsUpdateLogService.editUpdateLogDao(zsUpdateLog);
		}catch (Exception e) {
			k=0;
		}
		return k+"";
	}
	/**
	 * 删除日志
	 * jixh
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index/delLog")
	@ResponseBody
	public String delLog(String data) {
		int k=0;
		try {
			JSONObject object =JSONObject.fromObject(data);
			String logTime = (String)object.get("logTime");
			ZsUpdateLogEntity zsUpdateLog = new ZsUpdateLogEntity();
			zsUpdateLog.setLogTime(logTime);
			k=zsUpdateLogService.delLogDao(zsUpdateLog);
		}catch (Exception e) {
			k=0;
		}
		return k+"";
	}
	@RequestMapping("/logout")
	public String logout(Model model){
		RBACSubject.logout();
		return "redirect:/loginWBEM.action";
	}
	/**
	 * 修改密码页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index/changePassword",method = RequestMethod.GET)
	public String changePassword(){
		return "/wbem/index/changePassword";
	}
	/**
	 * 更改密码操作、验证密码
	 * @param newPassword
	 * @param oldPassword
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/index/changePassword",method = RequestMethod.POST)
	public String checkChangePsw(String oldPassword,String newPassword,String againPassword){
		UserAuthentication ua = new UserAuthentication();
		//验证原密码是否正确
		if(!ua.checkOldPassword(oldPassword)){
			return "oldPwdFailed";
		}
		if(ua.changePassword(oldPassword, newPassword, againPassword)){
			return "changeSuccess";
		}else{
			return "changeFailed";
		}
	}
	/**
	 * 
	 * 未读日志查询
	 */
	/*@RequestMapping(value="/index/unReadLog")
	public String cstView(Model model) {
		return "/wbem/index/unReadLog";
	}*/
	/**
	 * 
	 * 载入未读日志列表
	 */
	/*@ResponseBody
	@RequestMapping(value="/index/loadUnReadLog")
	public Map<String,Object> loadUnReadLog(HttpServletRequest request) throws UnsupportedEncodingException {
		//获得总数
		ZsUpdateLogEntity zsUpdateLogEntityS = new ZsUpdateLogEntity();
		int recordsTotal = zsUpdateLogService.getReadTypeDao(zsUpdateLogEntityS).size();
		int recordsFiltered=recordsTotal;
		//分页
		DataTablesParameters para= RequestUtil.getDTPara(request);
		int i=para.getStart()+1;
		int startIndex = para.getStart();
		int length = para.getLength();
		para.setColumnArray(new String[]{"","","","","","","",""});
		List<Map<String,Object>> lst = new ArrayList<Map<String,Object>>();
		List<ZsUpdateLogEntity> zsUpdateLogList = new ArrayList<ZsUpdateLogEntity>();
		ZsUpdateLogEntity zsUpdateLogEntity = new ZsUpdateLogEntity();
		zsUpdateLogEntity.setStartIndex(startIndex);
		zsUpdateLogEntity.setLength(length);
		zsUpdateLogList=zsUpdateLogService.getReadTypeDao(zsUpdateLogEntity);
		for(ZsUpdateLogEntity zsUpdateLog:zsUpdateLogList) {
			Map<String,Object> zsUpdateMap = new HashMap<String,Object>();
			zsUpdateMap.put("logId", "<label class='checkbox inline index'> <input type='checkbox' name='radios' />"+i+"</label>");
			zsUpdateMap.put("logSubject", zsUpdateLog.getLogSubject());
			zsUpdateMap.put("logContent", zsUpdateLog.getLogContent());
			zsUpdateMap.put("logClasses", zsUpdateLog.getLogClasses());
			zsUpdateMap.put("userName", zsUpdateLog.getUserName());
			zsUpdateMap.put("remark", zsUpdateLog.getRemark());
			zsUpdateMap.put("logTime", zsUpdateLog.getLogTime());
			i++;
			lst.add(zsUpdateMap);
		}
		Map<String,Object> jsonResult = new HashMap<String,Object>();
		jsonResult.put("draw", para.getDraw());
		jsonResult.put("recordsTotal", recordsTotal);
		jsonResult.put("recordsFiltered", recordsFiltered);
		jsonResult.put("data",lst);
		return jsonResult;		
		
	}*/
	/**
	 *状态由未读改为已读
	 */
	/*@RequestMapping(value="/index/editLogType")
	@ResponseBody
	public String editLogType(String data) {
		int k=0;
		try {
			k=zsUpdateLogService.editLogTypeDao();
		}catch (Exception e) {
			k=0;
		}
		return k+"";
	}*/
	/**
	 * 用户查询日志列表
	 */
	@RequestMapping(value="/index/showLogList")
	public String showLogList(Model model) {
		List<ZsUpdateLogEntity> showLogList = new ArrayList<ZsUpdateLogEntity>();
		showLogList = zsUpdateLogService.showLogListDao();
		for(ZsUpdateLogEntity zsUpdateLog: showLogList){
			String logTime = DateUtil.dateToString( zsUpdateLog.getLogTime());
			zsUpdateLog.setLogTime(logTime);
		}
		model.addAttribute("showLogList", showLogList);
		return "/wbem/index/showLogList";
	}
	/**
	 * 选择项目页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index/selectProject")
	public String selectProject(Model model) {
		//List<XsTeamGroupEntity> projectList = new ArrayList<XsTeamGroupEntity>();
		//projectList = xsTeamGroupService.getProjectItem();
		//model.addAttribute("projectList", projectList);
		return "/wbem/index/selectProject";
	}
	/**
	 * 选择项目的确定按钮操作
	 * @param model
	 * @param projGuid
	 * @return
	 */
	@RequestMapping(value="/index/sureSelectProject")
	@ResponseBody
	public int sureSelectProject(Model model,String projGuid) {
		Session session = RBACSubject.getSecurityUtils().getSession();
		List<XsTeamGroupEntity> projectList = new ArrayList<XsTeamGroupEntity>();
		projectList = (List<XsTeamGroupEntity>)session.getAttribute("projectList");
		int allowSwitch = 0;
		for(XsTeamGroupEntity project:projectList) {
			if(project.getId().equals(projGuid)){
				allowSwitch = 1;
			}
		}
		if(allowSwitch == 0)
			return allowSwitch;
		User user = (User)session.getAttribute("user");
		UserExt userExt = new UserExt();
		userExt.setUserId(String.valueOf(user.getUserID()));
		userExt.setUserName(user.getUsername());
		userExt.setProperty("projGuid");
		userExt.setValue(projGuid);
		List<UserExt> userExtList = new ArrayList<UserExt>();
		userExtList = userExtService.getUserExtInfo(userExt);
		int flag = 0;
		if(userExtList.size() == 0) {
			flag = userExtService.insertUserExtInfo(userExt);
		}else {
			flag = userExtService.updateUserExtInfo(userExt);
		}
		if(flag == 1){
			List<UserExt> extList = new ArrayList<UserExt>();
			extList = userExtService.getUsetExtById(user.getUserID());
			user.setUserList(extList);
			session.setAttribute("user", user);
			session.setAttribute("projGuid", "\'"+projGuid+"\'");
		}
		return flag;
	}
	
	@RequestMapping(value="/index/selectSystem")
	@ResponseBody
	public int selectSystem(Model model,String systemId) {
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		UserExt userExt = new UserExt();
		userExt.setUserId(String.valueOf(user.getUserID()));
		userExt.setUserName(user.getUsername());
		userExt.setProperty("systemId");
		userExt.setValue(systemId);
		List<UserExt> userExtList = new ArrayList<UserExt>();
		userExtList = userExtService.getUserExtInfo(userExt);
		int flag = 0;
		if(userExtList.size() == 0) {
			flag = userExtService.insertUserExtInfo(userExt);
		}else {
			flag = userExtService.updateUserExtInfo(userExt);
		}
		if(flag == 1){
			List<UserExt> extList = new ArrayList<UserExt>();
			extList = userExtService.getUsetExtById(user.getUserID());
			user.setUserList(extList);
			session.setAttribute("user", user);
			session.setAttribute("systemId", systemId);
		}
		return flag;
	}
}
