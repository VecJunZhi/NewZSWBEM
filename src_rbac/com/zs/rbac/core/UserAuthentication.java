package com.zs.rbac.core;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.rbac.dao.impl.UserDaoImpl;
import com.zs.rbac.entity.User;
import com.zs.rbac.service.UserService;
import com.zs.rbac.service.factory.ServiceImplFactory;
import com.zs.rbac.utils.RbacUtils;

/**
 * 提供用户信息通用验证
 * @author JiaRui
 * @caeateTime 2015-11-14
 */
public class UserAuthentication {
	private Log log = LogFactory.getLog(UserAuthentication.class);	

	Subject subject = SecurityUtils.getSubject();
	
	Session session = subject.getSession();
	
	private UserService userService = ServiceImplFactory.getUserServiceInstance();
	
	
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
	public String checkUserLogin(UserLoginBase loginBase){
		log.info("dedd");
		Subject subject = SecurityUtils.getSubject();
		log.info("hleo "+subject);
		UsernamePasswordToken token = new UsernamePasswordToken(loginBase.getUserName(),loginBase.getPassword());
		log.info(token);
		token.setRememberMe(loginBase.isRememberMe());
		log.info(token.getUsername());
		try{
			subject.login(token);
			log.info("jiejid");
		}catch(UnknownAccountException e){  //无效的账号	
			return LoginBaseStatus.UNKNOWNACCOUNT;
		}catch(LockedAccountException e){ //账号被锁定
			return LoginBaseStatus.LOCKEDACCOUNT;
		}catch(AuthenticationException e){  //身份验证失败
			log.info("eeeedd " +e);
			return LoginBaseStatus.AUTHENTICATIONERROR;
		}
		return LoginBaseStatus.AUTHENTICATIONSUCCESS;
	}
	
	/**
	 * 验证密码是否匹配
	 * @param newPassword
	 * @param oldPassword
	 * @return  true  与原密码匹配<br>
	 *          flase 与原密码不匹配 
	 */
	public Boolean checkOldPassword(String password){
		boolean result = false;
		if(password != null && !"".equals(password)){
			User user = (User)session.getAttribute("user");
			String mobile = user.getMobile();
			User curUser = userService.findByUsername(mobile);
			result = curUser.getPassword().equals(password);
		}
		return result;
	}
	
	/**
	 * 修改密码
	 * @param newPassword
	 * @param oldPassword
	 * @return 
	 */
	public Boolean changePassword(String oldPassword,String newPassword,String againPassword){
		boolean result = false;
		if(oldPassword == null || "".equals(oldPassword))
			return result;
		if(newPassword == null || "".equals(newPassword))
			return result;
		if(againPassword == null || "".equals(againPassword))
			return result;
		
		User user = (User)session.getAttribute("user");
		User curUser = userService.findByUsername(user.getMobile());
		if(newPassword.equals(againPassword) && (user.getUserID() > 0)  && curUser.getPassword().equals(oldPassword) ){
			userService.changePassword(user.getUserID(), newPassword);
			result = true;
		}
		return result;
	}
	
	/**
	 * 用户注销
	 * @param session
	 */
	public void logout() {
		session.removeAttribute("username");
		Subject subject = RbacUtils.subject();
		subject.logout();
	}
	
	

}

