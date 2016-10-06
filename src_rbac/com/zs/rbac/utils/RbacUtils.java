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
	//登录认证
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
				log.info("密码输入错误");
				return "密码输入错误";
			}else if(e.getClass().getName().endsWith("UnknownAccountException")){
				System.out.println("用户名输入错误");
				return "用户名输入错误";
			}else if(e.getClass().getName().endsWith("LockedAccountException")){
				System.out.println("帐号被锁定");
				return "帐号被锁定";
			}
		}
		return "用户名/密码错误";
	
	}
	
	//权限认证
	public static Boolean loginAuthzation(String permission){
		if(subject().isPermitted(permission)){
			return true;
		}else{
			return false;
		}
	}
	
	//login 将securitymanager 注入全局变量securityutils,通过securityutils获得subject
	public static void login(String configFile, String username, String password) {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager，并注入reamls认证的具体过程
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(configFile);
        //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
         UsernamePasswordToken token = new UsernamePasswordToken(username, password);
       // token.setRememberMe(true);
        subject.login(token);
    }
	
	/**
	 * 获得subject实例
	 * @return
	 */
    public static  Subject subject() {
    	return SecurityUtils.getSubject();
    }
    
    public static void logout(){
    	subject().logout();
    }
    /**
     * 两个里面传一个。
     * @param userId 不用就是0
     * @param roleId 不用就是0
     */
    public List<Role> getProjByUserIdOrRoleId(int userID,int roleID){
    	UserManagerServiceImpl impl=new UserManagerServiceImpl();
    	List<Role> role=impl.getProjByUserIdOrRoleId(userID, roleID);
    	return role;
    }
}
