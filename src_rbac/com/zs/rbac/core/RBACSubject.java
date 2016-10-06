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
 * rbac�����<br>
 * �ṩ����rbac��ز���
 * @author JiaRui
 */
public class RBACSubject {
	
//	@Autowired
//	private ISysMenuService sysMenuServiceOne;
	ISysMenuService sysMenuService1 = new SysMenuServiceImpl();
//	private static ISysMenuService sysMenuService;
//	//ͨ���ñ�ǩ�Լ��÷���ʵ�ָ�static����ע��
//	@PostConstruct
//	public void init(){
//		RBACSubject.sysMenuService = sysMenuServiceOne;
//	}
	
	/**
	 * �˷���Ϊͨ����֤��¼�ķ�����<br>
	 * ����ʱ�贫��UserLoginBase�����л�����Ϣ<br>
	 * �����֤�ɹ����Ὣ�û���Ϣ����Session�У���ֱ�ӵ���<br>
	 * (User)SecurityUtils.getSubject().getSession().getAttribute("User")ʹ�� <br>
	 * @param loginBase
	 * @return unknownAccount          ��Ч���û���<br>
	 * 		   lockedAccount           �˻�������<br>
	 *         authenticationError     ��֤ʧ��<br>
	 *         authenticationSuccess   ��֤�ɹ�<br>
	 */
	public static String checkUserLogin(UserLoginBase loginBase){
		return new UserAuthentication().checkUserLogin(loginBase);
	}

	/**
	 * �޸��û�����
	 * @param oldPassword
	 * @param newPassword
	 * @param againPassword
	 * @return
	 */
	public static Boolean changePassword(String oldPassword,String newPassword,String againPassword){
		return new UserAuthentication().changePassword(oldPassword, newPassword, againPassword);
	}
	
	/**
	 * ��֤�����Ƿ�ƥ��
	 * @param password
	 * @return
	 */
	public static Boolean checkOldPassword(String password){
		return new UserAuthentication().checkOldPassword(password);
	}
	
	
	/**
	 * 
	 * @return  Map<id, Map<���˵�,List<�Ӳ˵�>>>
	 */
	public static Map<String, Map<String,List<Menu>>> getMenu(){
		Session Session = getSecurityUtils().getSession();
		User user = (User)Session.getAttribute("user");
		
		ISysMenuService sysMenuService = new SysMenuServiceImpl();
		System.out.println("sysMenu"+sysMenuService);
		return  sysMenuService.getMenuByUserID(user.getUserID());
	}
	
	/**
	 * ���ϵͳ��ϵͳ�б�
	 * @return List<SubSystem>
	 */
	public static List<SubSystem> getSubSystem() {
		Session Session = getSecurityUtils().getSession();
		User user = (User)Session.getAttribute("user");
		ISysMenuService sysMenuService = new SysMenuServiceImpl();
		return  sysMenuService.getSubSystemID(user.getUserID());
	}
	
	/**
	 * �û�ע��
	 */
	public static void logout() { 
		new UserAuthentication().logout();
	}
	
	/**
	 * �õ��û������Ϣ
	 * @return
	 */
	public static UserCore getUserAllInfo(){
		return new UserCore();
	}
	
	/**
	 * �õ��û�����ϵͳ����
	 * @return
	 */
	public static Map<String,String> getMobleSystem(){
		Subject subject = RBACSubject.getSecurityUtils();
		Map<String,String> sysList = new LinkedHashMap<String,String>();		//<��ɫ����,URL>
		//if(subject.hasRole("����ϵͳ����Ա"))	sysList.put("����ϵͳ����Ա","/bbb.action");	
		if(subject.hasRole("����ϵͳ����Ա")) 	sysList.put("����ϵͳ����Ա","/mbem/mcrm/house/saleManager/index.action");
		if(subject.hasRole("����ϵͳ"))			sysList.put("����ϵͳ","/mbem/mcrm/business/backlogPage.action");
		if(subject.hasRole("����ϵͳ"))			sysList.put("����ϵͳ","/mbem/mcrm/house/customer/backlogPage.action");
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
