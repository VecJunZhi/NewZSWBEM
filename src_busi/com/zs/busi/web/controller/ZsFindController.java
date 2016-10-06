package com.zs.busi.web.controller;

import com.zs.common.util.DateUtil;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.common.service.ZsUpdateLogService;
import com.zs.crm.entity.XsFindLogMbemEntity;
import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.crm.service.XsFindLogMbemService;
import com.zs.crm.web.vo.XsTeamGroupVo;
import com.zs.rbac.core.RBACSubject;
import com.zs.rbac.core.UserAuthentication;
import com.zs.rbac.entity.User;
import com.zs.rbac.entity.UserExt;
import com.zs.rbac.service.IUserExtService;
import com.zs.rbac.service.XsTeamGroupService;

@Controller
@RequestMapping(value="/mbem/mcrm/business/find")
public class ZsFindController {
	@Autowired
	private XsFindLogMbemService xsFindLogMbemService;
	@Autowired
	XsTeamGroupService xsTeamGroupService;
	@Autowired
	IUserExtService userExtService;
	private Log log = LogFactory.getLog(ZsFindController.class);	
	/**
	 * 发现页面
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:zs:findPage")
	@RequestMapping("/findPage")
	public String findSearch(){
		return "/mbem/mcrm/business/find/findPage";
	}
	/**
	 * 修改密码页
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:zs:changePassword")
	@RequestMapping(value = "/changePassword",method = RequestMethod.GET)
	public String changePassword(){
		return "/mbem/mcrm/business/find/changePassword";
	}
	/**
	 * 更改密码操作、验证密码
	 * @param newPassword
	 * @param oldPassword
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:zs:changePassword")
	@ResponseBody
	@RequestMapping(value="/changePassword",method = RequestMethod.POST)
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
	 * 帮助与建议
	 * @return
	 */
	@RequestMapping(value="/feedback",method = RequestMethod.GET)
	public String feedback(){
		return "/mbem/mcrm/business/find/feedback";
	}
	/**
	 * 创建帮助与建议
	 * @return
	 */
	@RequestMapping(value="/feedback",method = RequestMethod.POST)
	public String createFeedback(){
		return "redirect:/mbem/mcrm/business/find/feedback";
	}
	/**
	 * 用户登出操作
	 * @param response
	 * @return
	 */
	@RequiresPermissions(value="page:mbem:mcrm:zs:findPage")
	@RequestMapping(value = "/logouting")
	public String logout() {
		RBACSubject.logout();
		return "redirect:/mbem/login.action";
	}
	/**
	 * 系统切换页面
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:businessMobile:find:select")
	@RequestMapping("/selectSys")
	public String selectSys(Model model){
		model.addAttribute("isShow", "1");
		return "/mbem/mcrm/business/find/selectSys";
	}
	/**
	 * 招商系统日志
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value="page:businessMobile:find:updateLog")
	@RequestMapping("/updateLog")
	public String updateLog(Model model){
		List<XsFindLogMbemEntity> zsUpdateLogList = new ArrayList<XsFindLogMbemEntity>();
		zsUpdateLogList=xsFindLogMbemService.getXsLogDao();
		model.addAttribute("zsUpdateLogList", zsUpdateLogList);
		for(XsFindLogMbemEntity xsFindLogMbem:zsUpdateLogList){
			String logTime = DateUtil.dateToStringSecond(xsFindLogMbem.getLogTime());
			xsFindLogMbem.setLogTime(logTime);
		}
		return "/mbem/mcrm/business/find/updateLog";
	}
	/**
	 * 手机端招商系统增加日志
	 * @param model
	 * @return
	 */
	/*@RequestMapping("/addBusUpdateLog")
	public String addUpdateLogMbem(Model model){
		User user = (User)SecurityUtils.getSubject().getSession().getAttribute("user");
		String userName=user.getUsername();
		model.addAttribute("userName",userName);
		return "/mbem/mcrm/business/find/addBusUpdateLog";
	}*/
	/**
	 * 手机端招商系统插入日志
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
	
	@RequestMapping("/selectProject")
	public String selectProject(Model model,@RequestParam(value="oppGuid",required=false)String oppGuid,@RequestParam(value="cstGuid",required=false)String cstGuid) {
		//List<XsTeamGroupEntity> projectList = new ArrayList<XsTeamGroupEntity>();
		//projectList = xsTeamGroupService.getProjectItem();//增加权限后该查询应该是查当前系统下有权限的项目列表而不是所有项目列表
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		model.addAttribute("projGuid", projGuid);
		//model.addAttribute("projectList", projectList);
		return "/mbem/mcrm/business/find/selectProject";
	}
	
	@RequestMapping("/sureSelectProject")
	public String sureSelectProject(Model model,@RequestParam(value="projGuid",required=true)String projGuid) {
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
