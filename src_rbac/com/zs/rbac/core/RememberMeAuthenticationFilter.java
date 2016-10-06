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
 * 实现用户自动登录
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
		// 如果 isAuthenticated 为 false 证明不是登录过的，同时 isRememberd 为true
		// 证明是没登陆直接通过记住我功能进来的

		if (!subject.isAuthenticated() && subject.isRemembered()) {
			// 获取session看看是不是空的
			Session session = subject.getSession(true);
			// 随便拿session的一个属性来看session当前是否是空的，我用userId，你们的项目可以自行发挥
			if (session.getAttribute("username") == null) {
				// 如果是空的才初始化，否则每次都要初始化，项目得慢死
				String username = subject.getPrincipal().toString();
				System.out.println("filter user=" + username);
				System.out.println("自动登录重置Session信息");
				reloadUserInfoToSession(username, subject);
			}
		}
		// 这个方法本来只返回 subject.isAuthenticated() 现在我们加上 subject.isRemembered()
		// 让它同时也兼容remember这种情况
		return subject.isAuthenticated() || subject.isRemembered();
	}
	
	private void reloadUserInfoToSession(String userName,Subject subject){
		//UserService userService = ServiceImplFactory.getUserServiceInstance();
		User user = userService.findUserByLoginName(userName);
		//账号禁用或锁定,禁止自动登录
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
