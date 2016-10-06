package com.zs.common.web.controller;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;

import com.zs.common.entity.DataTablesParameters;
import com.zs.common.util.DateUtil;
import com.zs.common.util.RequestUtil;
import com.zs.common.web.vo.TreeViewForMap;
import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.rbac.entity.Organization;
import com.zs.rbac.entity.Permission;
import com.zs.rbac.entity.Role;
import com.zs.rbac.entity.RolePermission;
import com.zs.rbac.entity.User;
import com.zs.rbac.service.IOrganizationService;
import com.zs.rbac.service.PermissionService;
import com.zs.rbac.service.RoleService;
import com.zs.rbac.service.UserManagerService;
import com.zs.rbac.service.XsTeamGroupService;

@Controller
@RequestMapping("/wbem/system/sysSet")
public class SystemSysSetController {
	
	private Log log = LogFactory.getLog(UserController.class);	
	@Autowired
	UserManagerService userManagerService;
	@Autowired
	PermissionService permissionService;
	@Autowired
	IOrganizationService organizationService;
	@Autowired
	RoleService roleService;
	@Autowired
	XsTeamGroupService xsTeamGroupService;
	@Autowired
	MemcachedClient memcachedClient;
//===============================================Memcached管理===================================================//
	public void memcached管理测试(){}
	@RequestMapping("/memcachedTest")
	public void memcachedTest(){
		log.info("正在执行memcachedTest方法");
		/*if(memcachedClient.get("user")==null){
			memcachedClient.add("users",0, 25);
		}if(memcachedClient.get("users")!=null){
			log.info(memcachedClient.get("users"));
		}*/
	}
	
//===============================================用户管理===================================================//	
   public void 用户管理(){}
	@RequestMapping("/userManager")
    public String userManager(Model model) {
        return "/wbem/system/sysSet/userManager";
    }
	//获得用户列表
	@ResponseBody
	@RequestMapping(value="/getUser")
	public Map<String,Object> getUser(HttpServletRequest request,String userName) {
		try {
			userName=URLDecoder.decode(userName, "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.info(e);
		}
		DataTablesParameters para= RequestUtil.getDTPara(request);
		int rows=para.getLength();
		int startLength=para.getStart();
		String sortName=para.getOrderColumn();
		String sortdes=para.getOrderDir();
		
		User user=new User();
		user.setUsername(userName);
		List<User> list=userManagerService.getUser(user);
		user.setRows(rows);
		user.setStartLength(startLength);
		user.setSidx(sortName);
		user.setSord(sortdes);
		List<User> listData=userManagerService.getUser(user);
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		int i=para.getStart()+1;
		for (User entity : listData) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("index",i+"");
			map.put("realName", entity.getRealName());
			map.put("mobile", entity.getMobile());
			map.put("userName", entity.getUsername());
			map.put("email", entity.getEmail());
			map.put("uStatus", Integer.toString(entity.getuStatus()));
			map.put("locked", Boolean.toString(entity.getLocked()));
			map.put("userID", Integer.toString(entity.getUserID()));
			map.put("password", entity.getPassword());
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
	@RequestMapping(value="/createUser")
	@ResponseBody
	public int createUser(String userName,String  realName, String password, String mobile, String email) {
		User user=new User();
		try {
			userName=URLDecoder.decode(userName, "utf-8");
			realName=URLDecoder.decode(realName, "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.info(e);
		}
		user.setUsername(userName);
		user.setRealName(realName);
		user.setPassword(password);
		user.setMobile(mobile);
		user.setEmail(email);
		user.setuStatus(1);
		user.setLocked(false);
		int _int=userManagerService.createUser(user);
		return _int;
	}
	@RequestMapping(value="/updateUser")
	@ResponseBody
	public int updateUser(int userId,String userName,String  realName, String password, String mobile, String email) {
		User user=new User();
		try {
			userName=URLDecoder.decode(userName, "utf-8");
			realName=URLDecoder.decode(realName, "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.info(e);
		}
		user.setUserID(userId);
		user.setUsername(userName);
		user.setRealName(realName);
		user.setPassword(password);
		user.setMobile(mobile);
		user.setEmail(email);
		user.setuStatus(-1);
		int _int=userManagerService.updateUser(user);
		return _int;
	}
	@RequestMapping(value="/updateUserStatus")
	@ResponseBody
	public int updateUserStatus(int userId,int uStatus) {
		User user=new User();
		user.setUserID(userId);
		user.setuStatus(uStatus);
		int _int=userManagerService.updateUser(user);
		return _int;
	}
	@RequestMapping(value="/updateUserLocked")
	@ResponseBody
	public int updateUserLocked(int userId,boolean locked) {
		User user=new User();
		user.setUserID(userId);
		user.setLocked(locked);
		user.setuStatus(-1);
		int _int=userManagerService.updateUser(user);
		return _int;
	}
	@RequestMapping(value="/deleteUser")
	@ResponseBody
	public int deleteUser(int userId) {
		int _int=userManagerService.deleteUser(userId);
		return _int;
	}
	@RequestMapping(value="/judgeIfExistUserName")
	@ResponseBody
	public int judgeIfExistUserName(String userName) {
		int _int=userManagerService.judgeIfExistUserName(userName);
		return _int;
	}
	@RequestMapping(value="/judgeIfExistMobile")
	@ResponseBody
	public int judgeIfExistMobile(String mobile) {
		int _int=userManagerService.judgeIfExistMobile(mobile);
		return _int;
	}
	@RequestMapping(value="/judgeIfExistEmail")
	@ResponseBody
	public int judgeIfExistEmail(String email) {
		int _int=userManagerService.judgeIfExistEmail(email);
		return _int;
	}
//==========================================系统管理=============================================================================//	
   public void 模块管理(){}
   @RequestMapping("/moduleManager")
   public String moduleManager(Model model){
       return "/wbem/system/sysSet/moduleManager";
   }
	//查询所有子系统
	@ResponseBody
	@RequestMapping(value="/getSubSystemModel")
	public List<TreeViewForMap> getSubSystemModelTree(String parentID,String permissionType,String url){
		List<TreeViewForMap> result = new ArrayList<TreeViewForMap>();
		//第一级为山西兆盛管理信息
		if((parentID==null || "".equals(parentID))&& (permissionType==null || "".equals(permissionType))&&(url==null || "".equals(url))){
			TreeViewForMap tvf = new TreeViewForMap();
			tvf.put("id","0");
			tvf.put("parId","0");
			tvf.put("name","山西兆盛管理信息");
			tvf.put("permissionType","0");
			tvf.put("permissionMark","0");
			tvf.put("url","0");
			tvf.put("icon", "../../../common/images/ztree/b.png");
			tvf.set("isParent", "true");
			result.add(tvf);
		//获得子系统模块
		}else if(("0".equals(parentID))&& ("0".equals(permissionType))&&("0".equals(url))){
			log.info("subsystem");
			Permission permission=new Permission();
			permission.setPermissionType("subsystem");
			List<Permission> list=permissionService.queryPermission(permission);
			for (Permission permission2 : list) {
				TreeViewForMap tvf = new TreeViewForMap();
				tvf.put("id",permission2.getPermissionID());
				tvf.put("parId",permission2.getParentID());
				tvf.put("permissionMark",permission2.getPermissionMark());
				tvf.put("subpermissionMark",permission2.getPermissionMark().substring(permission2.getPermissionMark().lastIndexOf(":")+1));
				tvf.put("name",permission2.getPermissionName());
				tvf.put("permissionType",permission2.getPermissionType());
				tvf.put("priority",permission2.getPriority());
				tvf.put("url",permission2.getUrl());
				tvf.put("available",permission2.getAvailable());
				tvf.put("icon", "../../../common/images/ztree/b.png");
				tvf.set("isParent", "true");
				result.add(tvf);
			}
		//获得菜单和子菜单
		}else if(parentID !=null && !"".equals(parentID) &&(url==null || "".equals(url) || !url.endsWith(".action"))){
			Permission permission=new Permission();
			permission.setPermissionType("menu");
			permission.setParentID(parentID);
			List<Permission> list=permissionService.queryPermission(permission);
			for (Permission permission2 : list) {
				TreeViewForMap tvf = new TreeViewForMap();
				tvf.put("id",permission2.getPermissionID());
				tvf.put("parId",permission2.getParentID());
				tvf.put("permissionMark",permission2.getPermissionMark());
				tvf.put("subpermissionMark",permission2.getPermissionMark().substring(permission2.getPermissionMark().lastIndexOf(":")+1));
				tvf.put("name",permission2.getPermissionName());
				tvf.put("permissionType",permission2.getPermissionType());
				tvf.put("priority",permission2.getPriority());
				tvf.put("url",permission2.getUrl());
				tvf.put("available",permission2.getAvailable());
				tvf.put("icon", "../../../common/images/ztree/c.png");
				if("subsystem".equals(permissionType)|| "subsystemMobile".equals(permissionType)){
					tvf.set("isParent", "true");
				}else if("menu".equals(permissionType)){
					tvf.set("isParent", "false");
				}
				result.add(tvf);
			}
		}
		return result;
		
	}
	//查询所有子系统
	@ResponseBody
	@RequestMapping(value="/getAllModel")
	public Map<String,Object> getAllModel(String modelID,String roleID){
		Map<String,Object> map =new HashMap<String,Object>();
		List<List<Permission>> subList= new ArrayList<List<Permission>>();
		List<List<Permission>> pageList= new ArrayList<List<Permission>>();
		Permission permission=new Permission();
		permission.setPermissionType("menu");
		permission.setParentID(modelID);
		List<Permission> menulist=permissionService.queryPermission(permission);
		for(Permission permission2 : menulist){
			int menuID=permission2.getPermissionID();
			Permission permission3=new Permission();
			permission3.setPermissionType("menu");
			permission3.setParentID(Integer.toString(menuID));
			List<Permission> subMenulist=permissionService.queryPermission(permission3);
			for (Permission permission4 : subMenulist) {
				int subMenuId=permission4.getPermissionID();
				Permission permission5=new Permission();
				permission5.setParentID(Integer.toString(subMenuId));
				List<Permission> pagelist=permissionService.queryPermission(permission5);
				pageList.add(pagelist);
			}
			subList.add(subMenulist);
		}
		List<RolePermission> rolePermisionlist=roleService.ifCor_role_permission(Integer.decode(roleID), 0);
		map.put("menu", menulist);
		map.put("subMenu", subList);
		map.put("pages", pageList);
		map.put("rolePermisionlist", rolePermisionlist);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/getSubPageAndCrduTableList")
	//查询每一个页面下面对应的子页面/增删改查操作(子系统-->菜单-->页面-->子页面，CRDU)
	public Map<String,Object> getTableListByPages(HttpServletRequest request,String parentID){
		DataTablesParameters para= RequestUtil.getDTPara(request);
		int rows=para.getLength();
		int startLength=para.getStart();
		String sortName=para.getOrderColumn();
		String sortdes=para.getOrderDir();
		
		Permission permission=new Permission();
		permission.setParentID(parentID);
		List<Permission> list=permissionService.queryPermission(permission);
		permission.setRows(rows);
		permission.setStartLength(startLength);
		permission.setSidx(sortName);
		permission.setSord(sortdes);
		List<Permission> listData=permissionService.queryPermission(permission);
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		int i=para.getStart()+1;
		for (Permission entity : listData) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("index",i+"");
			map.put("permissionID", Integer.toString(entity.getPermissionID()));
			map.put("permissionName", entity.getPermissionName());
			map.put("permissionMark", entity.getPermissionMark());
			map.put("permissionType", entity.getPermissionType());
			map.put("parentID", entity.getParentID());
			map.put("url", entity.getUrl());
			map.put("priority", Integer.toString(entity.getPriority()));
			map.put("available", Boolean.toString(entity.getAvailable()));
			map.put("createTime", entity.getCreateTime());
			map.put("lastTime", entity.getLastTime());
			map.put("description", entity.getDescription());
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
	
	//新增子系统
	@RequestMapping(value="/createSubSystemModel")
	@ResponseBody	
	public int createSubSystemModel(String permissionName,String permissionMark,String permissionType,String parentID,
    		String url,int priority,Boolean available,String description){
		try {
			permissionName=URLDecoder.decode(permissionName, "utf-8");
		} catch (UnsupportedEncodingException e) {
			
		}
		String createTime=DateUtil.getNowDateSS();
		String lastTime=DateUtil.getNowDateSS();
		int _int=permissionService.createPermission(permissionName, permissionMark, permissionType, parentID, url, priority, available, createTime, lastTime, description);
		return _int;
	}
	//修改子系统
	@RequestMapping(value="/updateSubSystemModel")
	@ResponseBody	
	public int updateSubSystemModel(int permissionID,String permissionName,String permissionMark,String permissionType,String parentID,
    		String url,int priority,Boolean available,String description){
		try {
			permissionName=URLDecoder.decode(permissionName, "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.info(e);
		}
		int _int=permissionService.updateSubSystemModel(permissionID, permissionName, permissionMark, permissionType, parentID, url, priority, available, description);
		return _int;
	}
	//删除子系统
	@RequestMapping(value="/deleteSubSystemModel")
	@ResponseBody	
	public int deleteSubSystemModel(String permissionID,String permissionType,String url){
		int _int=0;
		//点击的模块，获得该模块下的菜单
		if(permissionID !=null && !"".equals(permissionID) &&(url==null || "".equals(url) || !url.endsWith(".action"))&& "subsystem".equals(permissionType)){//SHA
		 	Permission permission=new Permission();
			permission.setPermissionType("menu");
			permission.setParentID(permissionID);
			List<Permission> list=permissionService.queryPermission(permission);
			if (list.size()>0){
				return -1;
			}
		//点击菜单,获得该菜单下的子菜单
		}else if(permissionID !=null && !"".equals(permissionID) &&(url==null || "".equals(url) || !url.endsWith(".action"))&& "menu".equals(permissionType)){
			Permission permission=new Permission();
			permission.setPermissionType("menu");
			permission.setParentID(permissionID);
			List<Permission> list=permissionService.queryPermission(permission);
			if (list.size()>0){
				log.info("aaaa");
				return -2;
			}
		//点击子菜单-获取页面
		}else if(permissionID !=null && !"".equals(permissionID) && url !=null && !"".equals(url)&& url.endsWith(".action")&& "menu".equals(permissionType)){
			Permission permission=new Permission();
			permission.setPermissionType("page");
			permission.setParentID(permissionID);
			List<Permission> list=permissionService.queryPermission(permission);
			if (list.size()>0){
				return -3;
			}
		}
		_int=permissionService.deletePermission(Integer.parseInt(permissionID));
		return _int;
	}
//==========================================系统管理=============================================================================//	
	public void 组织架构管理(){}	
    @RequestMapping("/orgManager")
    public String orgManager(Model model){
        return "/wbem/system/sysSet/orgManager";
    }
	//查询所有子系统
	@ResponseBody
	@RequestMapping(value="/getOrganizationTree")
	public List<TreeViewForMap> getOrganizationTree(String orgid,String parentID){
		List<TreeViewForMap> result = new ArrayList<TreeViewForMap>();
		//第一级为山西兆盛
		if((parentID==null || "".equals(parentID))&& (orgid==null || "".equals(orgid))){
			TreeViewForMap tvf = new TreeViewForMap();
			Organization org=new Organization();
			org.setParentid(0);
			List<Organization> list=organizationService.selectBySelective(org);
			Organization organization=list.get(0);
			tvf.put("id",organization.getOrgid());
			tvf.put("parId",organization.getParentid());
			tvf.put("name",organization.getOrgname());
			tvf.put("orgtype",organization.getOrgtype());
			tvf.put("description",organization.getDescription());
			tvf.put("priority",organization.getPriority());
			tvf.put("available",organization.getAvailable());
			tvf.put("createtime",organization.getCreatetime());
			tvf.put("lasttime",organization.getLasttime());
			tvf.put("icon", "../../../common/images/ztree/c.png");
			tvf.set("isParent", "true");
			result.add(tvf);
		//获得子公司/相应的部门
		}else {
			Organization org=new Organization();
			org.setParentid(Integer.decode(orgid));
			List<Organization> list=organizationService.selectBySelective(org);
			for (Organization organization : list) {
				TreeViewForMap tvf = new TreeViewForMap();
				tvf.put("id",organization.getOrgid());
				tvf.put("parId",organization.getParentid());
				tvf.put("name",organization.getOrgname());
				tvf.put("orgtype",organization.getOrgtype());
				tvf.put("description",organization.getDescription());
				tvf.put("priority",organization.getPriority());
				tvf.put("available",organization.getAvailable());
				tvf.put("createtime",organization.getCreatetime());
				tvf.put("lasttime",organization.getLasttime());
				if("0".equals(parentID)){
					tvf.put("icon", "../../../common/images/ztree/a.png");
					tvf.set("isParent", "true");
				}else if("1".equals(parentID)){
					tvf.put("icon", "../../../common/images/ztree/b.png");
					tvf.set("isParent", "false");
				}
				result.add(tvf);
			}
		}
		return result;
	}
    
	//获得角色
	@ResponseBody
	@RequestMapping(value="/getRole")
	public Map<String,Object> getRole(HttpServletRequest request,int orgid) {
		DataTablesParameters para= RequestUtil.getDTPara(request);
		int rows=para.getLength();
		int startLength=para.getStart();
		String sortName=para.getOrderColumn();
		String sortdes=para.getOrderDir();
		Role role =new Role();
		role.setOrgid(orgid);
		List<Role> list=roleService.getRoles(role);
		role.setRows(rows);
		role.setStartLength(startLength);
		List<Role> listData=roleService.getRoles(role);
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		int i=para.getStart()+1;
		for (Role entity : listData) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("index",i+" "+"<input type='radio' style='margin-top: 0px;' name='rradio'>");
			map.put("roleName", entity.getRoleName());
			map.put("available", Boolean.toString(entity.getAvailable()));
			map.put("description", entity.getDescription());
			map.put("orgid", Integer.toString(entity.getOrgid()));
			map.put("roleid", Integer.toString(entity.getRoleID()));
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
	@RequestMapping(value="/updateRole")
	@ResponseBody
	public int updateRole(int roleID,String roleName,Boolean  available,String description) {
		Role role=new Role();
		try {
			roleName=URLDecoder.decode(roleName, "utf-8");
			description=URLDecoder.decode(description, "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.info(e);
		}
		role.setRoleID(roleID);
		role.setRoleName(roleName);
		role.setAvailable(available);
		role.setDescription(description);
		int _int=roleService.updateRole(role);
		return _int;
	}
	@RequestMapping(value="/createRole")
	@ResponseBody
	public int createRole(String roleName,Boolean  available, String description, String  priority, int orgid) {
		try {
			roleName=URLDecoder.decode(roleName, "utf-8");
			description=URLDecoder.decode(description, "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.info(e);
		}
		String createTime=DateUtil.getNowDateSS();
		String lastTime=DateUtil.getNowDateSS();
		int _int=roleService.createRole(roleName, description, available, createTime, lastTime, priority, orgid);
		return _int;
	}
	//获得用户列表通过角色
	@ResponseBody
	@RequestMapping(value="/getUsersByRoleID")
	public Map<String,Object> getUsersByRoleID(HttpServletRequest request,int roleID) {
		DataTablesParameters para= RequestUtil.getDTPara(request);
		int rows=para.getLength();
		int startLength=para.getStart();
		String sortName=para.getOrderColumn();
		String sortdes=para.getOrderDir();
		Role role =new Role();
		role.setRoleID(roleID);
		List<User> list=roleService.getUsersByRoleID(role);
		role.setRows(rows);
		role.setStartLength(startLength);
		List<User> listData=roleService.getUsersByRoleID(role);
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		int i=para.getStart()+1;
		for (User entity : listData) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("index",i+"");
			map.put("realName", entity.getRealName());
			map.put("mobile", entity.getMobile());
			map.put("userName", entity.getUsername());
			map.put("email", entity.getEmail());
			map.put("uStatus", Integer.toString(entity.getuStatus()));
			map.put("locked", Boolean.toString(entity.getLocked()));
			map.put("userID", Integer.toString(entity.getUserID()));
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
	 * 进入团队中新增用户明细页面
	 */
	@RequestMapping(value="/createUserRole")
	public String createUserRole(Model model ,int roleID){
		model.addAttribute("roleID",roleID );
		return "/wbem/system/sysSet/createUserRole";
	}
	@RequestMapping(value="/createRolePermission")
	public String createRolePermission(Model model ,int roleID){
		model.addAttribute("roleID",roleID );
		List<XsTeamGroupEntity> proList = xsTeamGroupService.getProjectItem();
		
		
		Role role =new Role();
		role.setRoleID(roleID);
		Role role2=roleService.getRoles(role).get(0);
		String pro=role2.getProjGuid();
		
		List<String> list=new ArrayList<>();
		if(pro !=null && !pro.equals("") ){
				list=new ArrayList<String>(Arrays.asList(pro.split(",")));
		}
		for (XsTeamGroupEntity xsTeamGroupEntity : proList) {
			for (String string : list) {
				if(string.equals(xsTeamGroupEntity.getId())){
					xsTeamGroupEntity.setValue("checked");
				}
			}
		}
		model.addAttribute("proList",proList);
		//model.addAttribute("checkedProList",list);
		return "/wbem/system/sysSet/createRolePermission";
	}
	@RequestMapping(value="/correUser_Role")
	@ResponseBody
	public String correUser_Role(String userID,int roleID) {
		String[] userIdAarray=userID.split(",");
		String name="";
		Boolean flg=true;
		for (int i = 0; i < userIdAarray.length; i++) {
			Role role =new Role();
			role.setRoleID(roleID);
			role.setUserID(Integer.decode(userIdAarray[i]));
			List<User> list=roleService.getUsersByRoleID(role);
			if(list.size()>0){
				String realName=list.get(0).getRealName();
				name+=realName+",";
				flg=false;
			}
		}
		if(flg){
			for (int i = 0; i < userIdAarray.length; i++) {
				name=roleService.correUser_Role(Integer.decode(userIdAarray[i]), roleID)+"";
			}
			return name;
		}
		name="{\"data\":\""+name+"\"}";
		return name;
	}
	@RequestMapping(value="/correRole_Permission")
	@ResponseBody
	public String correRole_Permission(String permissionID,int roleID,String modelIds,String projIds) {
		String[] userIdAarray=permissionID.split(",");
		String[] modelIdAarray=modelIds.split(",");
		log.info(projIds);
		//直接更新角色字段
		Role role=new Role();
		role.setProjGuid(projIds);
		role.setRoleID(roleID);
		roleService.updateRole(role);
		//删除不再关联的  思路：找到相应模块的子集，匹配出去掉项目。
		for (String modelId : modelIdAarray) {
			List<String> all_list =permissionService.getMenuSubMenuPageIdByModelID(modelId);//获得所有的
			List<RolePermission> select_list=roleService.ifCor_role_permission(roleID, 0);
			List<String> modelList=new ArrayList<String>();
			List<String> delete_List=new ArrayList<String>();
			for (RolePermission rolePermission : select_list) {
				int perId=rolePermission.getPermissionID();
				for (String pId : all_list) {
					if(pId.equals(Integer.toString(perId))){
						modelList.add(pId);
					}
				}
			}
			for (String sel_model : modelList) {
				boolean _flg=true;
				for (int i = 0; i < userIdAarray.length; i++) {
					if(sel_model.equals(userIdAarray[i])){
						_flg=false;
						break;
					}
				}
				if(_flg){
					delete_List.add(sel_model);
				}
			}
			for (String pid : delete_List) {
				roleService.deleteRolePermission(roleID,Integer.decode(pid));
			}
		}
		
		String name="1";
		for (int i = 0; i < userIdAarray.length; i++) {
			List<RolePermission> list=roleService.ifCor_role_permission(roleID, Integer.decode(userIdAarray[i]));
			if(list.size()==0){//说明之前没绑定过
				name=roleService.correRole_Permission(roleID, Integer.decode(userIdAarray[i]))+"";
			}
		}
		return name;
	}
	@RequestMapping(value="/createOrganization")
	@ResponseBody
	public int createOrganization(String orgname,Integer orgtype,String description,Integer parentid,String priority,Integer available,Date createtime,Date lasttime) {
		try {
			orgname=URLDecoder.decode(orgname, "utf-8");
			description=URLDecoder.decode(description, "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.info(e);
		}
		int _int=organizationService.insert(orgname, orgtype, description, parentid, priority, available, new Date(), new Date());
		return _int;
	}
	@RequestMapping(value="/updateOrganization")
	@ResponseBody
	public int updateOrganization(Integer orgid, String orgname,Integer available,Integer parentID) {
		try {
			orgname=URLDecoder.decode(orgname, "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.info(e);
		}
		int _int=organizationService.updateByPrimaryKeySelective(orgid, orgname, available,parentID);
		return _int;
	}
	//删除子系统
	@RequestMapping(value="/deleteOrgaination")
	@ResponseBody	
	public int deleteOrgaination(String orgid){
		int _int=0;
		Organization org=new Organization();
		org.setParentid(Integer.decode(orgid));
		List<Organization> list=organizationService.selectBySelective(org);
		if(list.size()>0){//子公司下有部门存在
			_int=-1;
			return _int;
		}else{
			Role role =new Role();
			role.setOrgid(Integer.decode(orgid));
			List<Role> rolelist=roleService.getRoles(role);
			if(rolelist.size()>0){//部门下面存在操作项，不能删除
				_int=-2;
				return _int;
			}
		}
		_int=organizationService.deleteByPrimaryKey(Integer.decode(orgid));
		return _int;
	}
	@RequestMapping(value="/deleteUserRole")
	@ResponseBody
	public int deleteUserRole(int userID,int roleID) {
		int _int=roleService.deleteUserRole(userID, roleID);
		return _int;
	}
	public static void main(String[] args) {
		String a="11";
		System.out.println(a.contains("11"));
	}
}
