package com.zs.rbac.core;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.zs.rbac.entity.Menu;
import com.zs.rbac.entity.SubSystem;
import com.zs.rbac.entity.User;
import com.zs.rbac.entity.UserCore;
import com.zs.rbac.service.ISysMenuService;
import com.zs.rbac.service.impl.SysMenuServiceImpl;

/**
 * rbac入口类<br>
 * 提供所有rbac相关操作
 * @author JiaRui
 */
public class RBACSubject {
	
//	@Autowired
//	private ISysMenuService sysMenuServiceOne;
	ISysMenuService sysMenuService1 = new SysMenuServiceImpl();
//	private static ISysMenuService sysMenuService;
//	//通过该标签以及该方法实现给static属性注入
//	@PostConstruct
//	public void init(){
//		RBACSubject.sysMenuService = sysMenuServiceOne;
//	}
	
	/**
	 * 此方法为通用验证登录的方法。<br>
	 * 调用时需传递UserLoginBase对象中基本信息<br>
	 * 如果认证成功，会将用户信息放入Session中，可直接调用<br>
	 * (User)SecurityUtils.getSubject().getSession().getAttribute("User")使用 <br>
	 * @param loginBase
	 * @return unknownAccount          无效的用户名<br>
	 * 		   lockedAccount           账户被锁定<br>
	 *         authenticationError     认证失败<br>
	 *         authenticationSuccess   认证成功<br>
	 */
	public static String checkUserLogin(UserLoginBase loginBase){
		return new UserAuthentication().checkUserLogin(loginBase);
	}

	/**
	 * 修改用户密码
	 * @param oldPassword
	 * @param newPassword
	 * @param againPassword
	 * @return
	 */
	public static Boolean changePassword(String oldPassword,String newPassword,String againPassword){
		return new UserAuthentication().changePassword(oldPassword, newPassword, againPassword);
	}
	
	/**
	 * 验证密码是否匹配
	 * @param password
	 * @return
	 */
	public static Boolean checkOldPassword(String password){
		return new UserAuthentication().checkOldPassword(password);
	}
	
	
	/**
	 * 
	 * @return  Map<id, Map<主菜单,List<子菜单>>>
	 */
	public static Map<String, Map<String,List<Menu>>> getMenu(){
		Session Session = getSecurityUtils().getSession();
		User user = (User)Session.getAttribute("user");
		
		ISysMenuService sysMenuService = new SysMenuServiceImpl();
		System.out.println("sysMenu"+sysMenuService);
		return  sysMenuService.getMenuByUserID(user.getUserID());
	}
	
	/**
	 * 获得系统主系统列表
	 * @return List<SubSystem>
	 */
	public static List<SubSystem> getSubSystem() {
		Session Session = getSecurityUtils().getSession();
		User user = (User)Session.getAttribute("user");
		ISysMenuService sysMenuService = new SysMenuServiceImpl();
		return  sysMenuService.getSubSystemID(user.getUserID());
	}
	
	/**
	 * 用户注销
	 */
	public static void logout() { 
		new UserAuthentication().logout();
	}
	
	/**
	 * 得到用户相关信息
	 * @return
	 */
	public static UserCore getUserAllInfo(){
		return new UserCore();
	}
	
	/**
	 * 得到用户端子系统集合
	 * @return
	 */
	public static Map<String,String> getMobleSystem(){
		Subject subject = RBACSubject.getSecurityUtils();
		Map<String,String> sysList = new LinkedHashMap<String,String>();		//<角色名称,URL>
		//if(subject.hasRole("招商系统管理员"))	sysList.put("招商系统管理员","/bbb.action");	
		if(subject.hasRole("销售系统管理员")) 	sysList.put("销售系统管理员","/mbem/mcrm/house/saleManager/index.action");
		if(subject.hasRole("招商系统"))			sysList.put("招商系统","/mbem/mcrm/business/backlogPage.action");
		if(subject.hasRole("销售系统"))			sysList.put("销售系统","/mbem/mcrm/house/customer/backlogPage.action");
		return sysList;
	}
	
	
	/**
	 * SecurityUtils.getSubject()
	 * @return org.apache.shiro.subject.Subject
	 */
	public static Subject getSecurityUtils(){
		return SecurityUtils.getSubject();
	}
	
	/**
	 * SecurityUtils.getSecurityManager()
	 * @return org.apache.shiro.mgt.SecurityManager
	 */
	public static org.apache.shiro.mgt.SecurityManager getSecurityManager(){
		return SecurityUtils.getSecurityManager();
	}
	
	/**
	 * SecurityUtils.setSecurityManager
	 * @param org.apache.shiro.mgt.SecurityManager
	 */
	public static void setSecurityManager(SecurityManager securityManager){
		SecurityUtils.setSecurityManager((org.apache.shiro.mgt.SecurityManager) securityManager);
	}
}
