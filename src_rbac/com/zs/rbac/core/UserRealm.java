package com.zs.rbac.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.zs.rbac.entity.User;
import com.zs.rbac.service.IUserExtService;
import com.zs.rbac.service.UserService;
import com.zs.rbac.service.factory.ServiceImplFactory;

/**
 * �Զ���Realm,��������Դ����<p>
 * User: jiarui<p>
 * Date: 15-12-9<p>
 * Version: 1.1
 */
public class UserRealm extends AuthorizingRealm {

	Log log = LogFactory.getLog(UserRealm.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IUserExtService userExtService;
	

	/**
	 * ֻ����Ҫ��֤Ȩ��ʱ�Ż����, ��Ȩ��ѯ�ص�����, ���м�Ȩ�����������û�����Ȩ��Ϣʱ����.�����л��������£�ֻ����һ��.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userService.findRoles(userName));
		authorizationInfo.setStringPermissions(userService.findPermissions(userName));
		return authorizationInfo;
	}

	
	/**
	 * ��֤�ص�����,��¼ʱ����
	 * ���ȸ��ݴ�����û�����ȡUser��Ϣ��Ȼ�����userΪ�գ���ô�׳�û�ҵ��ʺ��쳣UnknownAccountException��
	 * ���user�ҵ����������׳������쳣LockedAccountException���������AuthenticationInfo��Ϣ��
	 * ������Ӹ���AuthenticatingRealmʹ��CredentialsMatcher�����ж������Ƿ�ƥ�䣬
	 * �����ƥ�佫�׳���������쳣IncorrectCredentialsException��
	 * ��������������Դ˴�̫�ཫ�׳��������Դ����쳣ExcessiveAttemptsException��
	 * ����װSimpleAuthenticationInfo��Ϣʱ�� ��Ҫ���룺�����Ϣ���û�������ƾ�ݣ��������룩���Σ�username+salt����
	 * CredentialsMatcherʹ���μ��ܴ������������ʹ˴��������������ƥ�䡣
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String)token.getPrincipal();
		log.info(userService+"Realm===userName======="+userName);
		User user = userService.findUserByLoginName(userName);
		
		//û���ҵ��˺�
		if (user == null) {
			throw new UnknownAccountException();
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, user.getPassword(), getName());
		
		//�˺ű�����
		if (Boolean.TRUE.equals(user.getLocked())) {
			log.info(user.getLocked());
			throw new LockedAccountException();
		}
		
		user.setUserList(userExtService.getUsetExtById(user.getUserID()));  //��ȡ�û���չ��Ϣ
		Session session =  SecurityUtils.getSubject().getSession();
		session.setAttribute("username",user.getRealName());
		session.setAttribute("user",user);
		log.info("user=" + user);
		return authenticationInfo;
		
	}
	
	/**
     * �����û���Ȩ��Ϣ����.
     */
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}
	/**
     * �����û���Ϣ����.
     */
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	/**
	 * ����û���Ȩ��Ϣ����.
	 */
	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	/**
	 * ����û���Ϣ����.
	 */
	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}
	
	/**
	 * ������л���
	 */
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}


	/**
	 * ���������֤����
	 */
	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

}
