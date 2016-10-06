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
	 * ��¼��ϵͳ�л�ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping("/selectSys")
	public String selectSys(Model model){
		model.addAttribute("isShow", "0");
		model.addAttribute("sysInfo","��ѡ����Ҫ�������ϵͳ");
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
					roleName = "����ϵͳ����Ա";
					break;
				case 2:
					roleName = "����ϵͳ";
					break;
				case 3:
					roleName = "����ϵͳ";
					break;
			}
			List<Role> roleList = new ArrayList<Role>();
			Role searchInfo = new Role();
			searchInfo.setRoleName(roleName);
			roleList = roleService.getRoles(searchInfo);
			for(Role role:roleList){
				//����role�����е���Ŀ�б�
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
		if(isShow.equals("0")) {//����ָʾϵͳ����ĿΪһ��ʱ�Ƿ���ʾѡ��ҳ�棬0��ʾ����ʾ 1��ʾ��ʾ
			if(projGuid == null || "".equals(projGuid)) {
				if(projectList.size() == 1){//���ֻ��һ����ĿȨ�ޣ���ֱ����ת��ϵͳ��ҳ
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
				if(projectList.size() > 1){//����ж����ĿȨ�ޣ�����ת����Ŀѡ��ҳ
					model.addAttribute("projectInfo", "��ѡ����Ҫ�������Ŀ");//��¼ʱ����Ŀѡ��ҳ��ʾ����ѡ����Ŀ
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
