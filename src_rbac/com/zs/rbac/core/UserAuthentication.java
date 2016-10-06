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
 * �ṩ�û���Ϣͨ����֤
 * @author JiaRui
 * @caeateTime 2015-11-14
 */
public class UserAuthentication {
	private Log log = LogFactory.getLog(UserAuthentication.class);	

	Subject subject = SecurityUtils.getSubject();
	
	Session session = subject.getSession();
	
	private UserService userService = ServiceImplFactory.getUserServiceInstance();
	
	
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
		}catch(UnknownAccountException e){  //��Ч���˺�	
			return LoginBaseStatus.UNKNOWNACCOUNT;
		}catch(LockedAccountException e){ //�˺ű�����
			return LoginBaseStatus.LOCKEDACCOUNT;
		}catch(AuthenticationException e){  //�����֤ʧ��
			log.info("eeeedd " +e);
			return LoginBaseStatus.AUTHENTICATIONERROR;
		}
		return LoginBaseStatus.AUTHENTICATIONSUCCESS;
	}
	
	/**
	 * ��֤�����Ƿ�ƥ��
	 * @param newPassword
	 * @param oldPassword
	 * @return  true  ��ԭ����ƥ��<br>
	 *          flase ��ԭ���벻ƥ�� 
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
	 * �޸�����
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
	 * �û�ע��
	 * @param session
	 */
	public void logout() {
		session.removeAttribute("username");
		Subject subject = RbacUtils.subject();
		subject.logout();
	}
	
	

}

