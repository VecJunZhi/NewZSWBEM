package com.zs.rbac.utils;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import com.zs.rbac.entity.Role;
import com.zs.rbac.service.impl.UserManagerServiceImpl;

public class RbacUtils  {
	static Log log=LogFactory.getLog(RbacUtils.class);
	//��¼��֤
	public static String loginAuthcation(String configFile, String username, String password){
		
		try {
        	login(configFile, username, password);
        	if(subject().isAuthenticated()){
        		if(subject().hasRole("admin")){
        			System.out.println("admin user");
        			return "admin";
        		}else if(subject().hasRole("usual")){
        			System.out.println("usual user");
        			return "usual";
        		}else if(subject().hasRole("zszygw")){
        			return "zszygw";
        		}
        		
        	}
        } catch (Exception e) {
			System.out.println(e.getClass().getName());
			if(e.getClass().getName().endsWith("IncorrectCredentialsException")){
				log.info("�����������");
				return "�����������";
			}else if(e.getClass().getName().endsWith("UnknownAccountException")){
				System.out.println("�û����������");
				return "�û����������";
			}else if(e.getClass().getName().endsWith("LockedAccountException")){
				System.out.println("�ʺű�����");
				return "�ʺű�����";
			}
		}
		return "�û���/�������";
	
	}
	
	//Ȩ����֤
	public static Boolean loginAuthzation(String permission){
		if(subject().isPermitted(permission)){
			return true;
		}else{
			return false;
		}
	}
	
	//login ��securitymanager ע��ȫ�ֱ���securityutils,ͨ��securityutils���subject
	public static void login(String configFile, String username, String password) {
        //1����ȡSecurityManager�������˴�ʹ��Ini�����ļ���ʼ��SecurityManager����ע��reamls��֤�ľ������
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(configFile);
        //2���õ�SecurityManagerʵ�� ���󶨸�SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3���õ�Subject�������û���/���������֤Token�����û����/ƾ֤��
        Subject subject = SecurityUtils.getSubject();
         UsernamePasswordToken token = new UsernamePasswordToken(username, password);
       // token.setRememberMe(true);
        subject.login(token);
    }
	
	/**
	 * ���subjectʵ��
	 * @return
	 */
    public static  Subject subject() {
    	return SecurityUtils.getSubject();
    }
    
    public static void logout(){
    	subject().logout();
    }
    /**
     * �������洫һ����
     * @param userId ���þ���0
     * @param roleId ���þ���0
     */
    public List<Role> getProjByUserIdOrRoleId(int userID,int roleID){
    	UserManagerServiceImpl impl=new UserManagerServiceImpl();
    	List<Role> role=impl.getProjByUserIdOrRoleId(userID, roleID);
    	return role;
    }
}
