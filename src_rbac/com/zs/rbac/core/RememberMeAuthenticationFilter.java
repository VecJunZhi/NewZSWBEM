package com.zs.rbac.core;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.zs.rbac.entity.User;
import com.zs.rbac.service.IUserExtService;
import com.zs.rbac.service.UserService;
import com.zs.rbac.service.factory.ServiceImplFactory;
import com.zs.rbac.service.impl.UserExtServiceImpl;

/**
 * ʵ���û��Զ���¼
 * 
 * @author jiarui
 */
public class RememberMeAuthenticationFilter extends FormAuthenticationFilter {
	
	@Autowired
	IUserExtService userExtService;
	
	@Autowired
	UserService userService;
	
	protected boolean isAccessAllowed(ServletRequest request,ServletResponse response, Object mappedValue) {
		Subject subject = SecurityUtils.getSubject();
		System.out.println("subject.isRemembered()="+subject.isRemembered());
		System.out.println("subject.Authenticated()="+subject.isAuthenticated());
		// ��� isAuthenticated Ϊ false ֤�����ǵ�¼���ģ�ͬʱ isRememberd Ϊtrue
		// ֤����û��½ֱ��ͨ����ס�ҹ��ܽ�����

		if (!subject.isAuthenticated() && subject.isRemembered()) {
			// ��ȡsession�����ǲ��ǿյ�
			Session session = subject.getSession(true);
			// �����session��һ����������session��ǰ�Ƿ��ǿյģ�����userId�����ǵ���Ŀ�������з���
			if (session.getAttribute("username") == null) {
				// ����ǿյĲų�ʼ��������ÿ�ζ�Ҫ��ʼ������Ŀ������
				String username = subject.getPrincipal().toString();
				System.out.println("filter user=" + username);
				System.out.println("�Զ���¼����Session��Ϣ");
				reloadUserInfoToSession(username, subject);
			}
		}
		// �����������ֻ���� subject.isAuthenticated() �������Ǽ��� subject.isRemembered()
		// ����ͬʱҲ����remember�������
		return subject.isAuthenticated() || subject.isRemembered();
	}
	
	private void reloadUserInfoToSession(String userName,Subject subject){
		//UserService userService = ServiceImplFactory.getUserServiceInstance();
		User user = userService.findUserByLoginName(userName);
		//�˺Ž��û�����,��ֹ�Զ���¼
		if(user.getuStatus() == 0 || user.getLocked()){
			user = null;
		}
		if(user!=null){ 
			//IUserExtService userExtService = new UserExtServiceImpl();
			//System.out.println("userExtService=" + userExtService);
			user.setUserList(userExtService.getUsetExtById(user.getUserID())); 
			Session session = subject.getSession();
			session.setAttribute("username", user.getRealName());
			session.setAttribute("user",user);
			
		}
	}
}
