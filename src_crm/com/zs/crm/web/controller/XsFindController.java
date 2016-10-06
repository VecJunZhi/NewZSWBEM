package com.zs.crm.web.controller;
import com.zs.common.util.DateUtil;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;


import org.apache.commons.logging.Log;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.busi.utils.LogUtil;
import com.zs.rbac.core.RBACSubject;
import com.zs.rbac.core.UserAuthentication;
import com.zs.rbac.entity.User;
import com.zs.rbac.entity.UserExt;
import com.zs.rbac.service.IUserExtService;
import com.zs.rbac.service.XsTeamGroupService;
import com.zs.rbac.utils.RbacUtils;
import com.zs.crm.entity.XsFindLogMbemEntity;
import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.crm.service.XsFindLogMbemService;
@Controller
@RequestMapping(value="/mbem/mcrm/house/find")
public class XsFindController {
	Log log=LogUtil.getLog();
	//@Autowired
	//private UserService userService;
	@Autowired
	private XsFindLogMbemService xsFindLogMbemService;
	@Autowired
	XsTeamGroupService xsTeamGroupService;
	@Autowired
	IUserExtService userExtService;
	/**
	 * 进入发现页
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:find:find")
	@RequestMapping("/findSearch")
	public String findSearch() {
		return "/mbem/mcrm/house/find/findPage";
	}
	/**
	 * 进入修改密码页
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:find:changePassword")
	@RequestMapping(value = "/changePassword",method = RequestMethod.GET)
	public String changePassword(){
		log.info("changpassword");
		return "/mbem/mcrm/house/find/changePassword";
	}
	/**
	 * 
	 * @param newPassword
	 * @param oldPassword
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:find:changePassword")
	@RequestMapping(value="/changePassword",method = RequestMethod.POST)
	@ResponseBody
	public String checkChangePsw(String newPassword,String oldPassword,String againPassword){

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
	 * 用户登出操作
	 * @param response
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:find:find")
	@RequestMapping(value = "/logouting")
	public String logouting(HttpSession session) {
		try {
			session.removeAttribute("username");
			Subject subject = RbacUtils.subject();
			subject.logout();
		} catch (Exception e) {
			log.info(e);
		}
		return "redirect:/mbem/login.action";
	}
	/**
	 * 系统切换页面
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:find:select")
	@RequestMapping("/selectSys")
	public String selectSys(Model model){
		model.addAttribute("isShow", "1");
		return "/mbem/mcrm/house/find/selectSys";
	}
	/**
	 * 手机端销售系统查询日志
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:saleMobile:find:updateLog")
	@RequestMapping("/updateLog")
	public String updateLog(Model model){
		List<XsFindLogMbemEntity> zsUpdateLogList = new ArrayList<XsFindLogMbemEntity>();
		zsUpdateLogList=xsFindLogMbemService.getXsLogDao();
		model.addAttribute("zsUpdateLogList", zsUpdateLogList);
		for(XsFindLogMbemEntity zsFindLogMbem:zsUpdateLogList){
			String logTime = DateUtil.dateToStringSecond(zsFindLogMbem.getLogTime());
			zsFindLogMbem.setLogTime(logTime);
		}
		return "/mbem/mcrm/house/find/updateLog";
	}
	/**
	 * 手机端销售系统增加日志
	 * @param model
	 * @return
	 */
	/*@RequestMapping("/addUpdateLogMbem")
	public String addUpdateLogMbem(Model model){
		User user = (User)SecurityUtils.getSubject().getSession().getAttribute("user");
		String userName=user.getUsername();
		model.addAttribute("userName",userName);
		return "/mbem/mcrm/house/find/addUpdateLogMbem";
	}*/
	/**
	 * 手机端销售系统插入日志
	 * @param model
	 * @return
	 */
	/*@RequestMapping("/insertUpdateLogMbem")
	public String insertUpdateLogMbem(String data){
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
			
			XsFindLogMbemEntity xsFindLogMbem = new XsFindLogMbemEntity();
			xsFindLogMbem.setLogSubject(logSubject);
			xsFindLogMbem.setLogContent(logContent);
			xsFindLogMbem.setLogClasses(logClasses);
			xsFindLogMbem.setUserName(userName);
			k=xsFindLogMbemService.insertUpdateLogMbemDao(xsFindLogMbem);
		}catch (Exception e) {
			k=0;
		}
		return k+"";
		
	}*/
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/selectProject")
	public String selectProject(Model model,@RequestParam(value="flag",required=true)String flag,@RequestParam(value="cstGuid",required=false)String cstGuid,@RequestParam(value="oppGuid",required=false)String oppGuid){
		//List<XsTeamGroupEntity> projectList = new ArrayList<XsTeamGroupEntity>();
		//projectList = xsTeamGroupService.getProjectItem();//增加权限限制后查询的是当前系统下有权限的项目列表而不是所有项目列表,不用查询了，从session中取
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		model.addAttribute("flag", flag);
		//model.addAttribute("projectList", projectList);
		model.addAttribute("projGuid", projGuid);
		model.addAttribute("oppGuid", oppGuid);
		model.addAttribute("cstGuid", cstGuid);
		return "/mbem/mcrm/house/find/selectProject";
	}
	
	@RequestMapping("/sureSelectProject")
	public String sureSelectProject(@RequestParam(value="projGuid",required=true)String projGuid) {
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		UserExt userExt = new UserExt();
		userExt.setUserId(String.valueOf(user.getUserID()));
		userExt.setProperty("projGuid");
		userExt.setUserName(user.getUsername());
		userExt.setValue(projGuid);
		List<UserExt> userExtList = new ArrayList<UserExt>();
		userExtList = userExtService.getUserExtInfo(userExt);
		int flag = 0;
		if(userExtList.size() == 0){
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
		String url = (String)session.getAttribute("curSys");
		return "redirect:"+url;
	}
}
