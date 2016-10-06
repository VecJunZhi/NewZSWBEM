package com.zs.common.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.rbac.core.RBACSubject;
import com.zs.rbac.entity.Role;
import com.zs.rbac.entity.User;
import com.zs.rbac.entity.UserExt;
import com.zs.rbac.service.IUserExtService;
import com.zs.rbac.service.RoleService;
import com.zs.rbac.service.UserManagerService;
import com.zs.rbac.service.XsTeamGroupService;

@Controller
@RequestMapping(value="/mbem")
public class MBEMIndexController {
	
	@Autowired
	XsTeamGroupService xsTeamGroupService;
	@Autowired
	IUserExtService userExtService;
	@Autowired
	UserManagerService userManagerService;
	@Autowired
	RoleService roleService;
	/**
	 * 登录后系统切换页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/selectSys")
	public String selectSys(Model model){
		model.addAttribute("isShow", "0");
		model.addAttribute("sysInfo","请选择需要进入的子系统");
		return "/mbem/mcrm/common/selectSys";
	}
	
	@RequestMapping("/selectProject")
	public String selectProject(Model model,@RequestParam(value="url",required=true)String url,@RequestParam(value="isShow",required=true)String isShow,@RequestParam(value="roleId",required=true)int roleID){
		List<XsTeamGroupEntity> projectList = new ArrayList<XsTeamGroupEntity>();
		List<XsTeamGroupEntity> allProjectList = new ArrayList<XsTeamGroupEntity>();
		String resultPage = "";
		Session session = RBACSubject.getSecurityUtils().getSession();
		User user = (User)session.getAttribute("user");
		String projGuid = user.getExtInfo("projGuid");
		allProjectList = xsTeamGroupService.getProjectItem();
		if(roleID == 0){
			projectList = (List<XsTeamGroupEntity>)session.getAttribute("projectList");
		}else {
			Set<String> projectSet = new HashSet<String>();
			String roleName = "";
			switch(roleID){
				case 1:
					roleName = "销售系统管理员";
					break;
				case 2:
					roleName = "销售系统";
					break;
				case 3:
					roleName = "招商系统";
					break;
			}
			List<Role> roleList = new ArrayList<Role>();
			Role searchInfo = new Role();
			searchInfo.setRoleName(roleName);
			roleList = roleService.getRoles(searchInfo);
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
		}
		if(isShow.equals("0")) {//用来指示系统或项目为一个时是否显示选择页面，0表示不显示 1表示显示
			if(projGuid == null || "".equals(projGuid)) {
				if(projectList.size() == 1){//如果只有一个项目权限，则直接跳转到系统首页
					UserExt userExt = new UserExt();
					userExt.setUserId(String.valueOf(user.getUserID()));
					userExt.setProperty("projGuid");
					userExt.setUserName(user.getUsername());
					userExt.setValue(projectList.get(0).getId());  
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
					resultPage = "redirect:"+url;
				}
				if(projectList.size() > 1){//如果有多个项目权限，则跳转到项目选择页
					model.addAttribute("projectInfo", "请选择需要进入的项目");//登录时的项目选择页显示：请选择项目
					resultPage = "/mbem/mcrm/common/selectProject";
				}
			} else {
				resultPage = "redirect:"+url;
			}
		}
		if(isShow.equals("1")) {
			resultPage = "/mbem/mcrm/common/selectProject";
		}
		session.setAttribute("curSys", url);
		model.addAttribute("projectList", projectList);
		return resultPage;
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
			//session.setAttribute("projGuid", "\'"+projGuid+"\'");
		}
		String url = (String)session.getAttribute("curSys");
		return "redirect:"+url;
	}
}
