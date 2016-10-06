package com.zs.rbac.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.common.web.controller.UserController;
import com.zs.rbac.dao.UserDao;
import com.zs.rbac.dao.UserExtDao;
import com.zs.rbac.dao.impl.UserDaoImpl;
import com.zs.rbac.entity.User;
import com.zs.rbac.entity.UserExt;
import com.zs.rbac.service.UserService;


/**
 * <p>User: zj
 * <p>Date: 15-9-5
 * <p>Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

	//@Autowired
    //UserExtDao userExtDao;
	private Log log = LogFactory.getLog(UserServiceImpl.class);	
    private UserDao userDao = new UserDaoImpl();
	//@Autowired
	//private UserDao userDao;
    
    
   // private PasswordHelper passwordHelper = new PasswordHelper();

    /**
     * 创建用户
     * @param user
     */
    public User createUser(User user) {
        //加密密码
      //  passwordHelper.encryptPassword(user);
        return userDao.createUser(user);
    }

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(int userId, String newPassword) {
        User user =userDao.findOne(userId);
        user.setPassword(newPassword);
       // passwordHelper.encryptPassword(user);
        userDao.updateUser(userId,newPassword);
    }

    /**
     * 添加用户-角色关系
     * @param userId
     * @param roleIds
     */
    public void correlationRoles(int userId, int... roleIds) {
        userDao.correlationUser_Roles(userId, roleIds);
    }


    /**
     * 移除用户-角色关系
     * @param userId
     * @param roleIds
     */
    public void uncorrelationRoles(int userId, int... roleIds) {
        userDao.uncorrelationUser_Roles(userId, roleIds);
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username) {
    	if(username == null || "".equals(username)){
    		return new HashSet<String>();
    	}
        return userDao.findRoles(username) == null?new HashSet<String>():userDao.findRoles(username);
    }

    public static void main(String[] args) {
    	 UserDao userDao = new UserDaoImpl();
    	 Set<String> set = userDao.findRoles("15835140852");
    	
   
    }
    
    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username) {
    	if(username == null || "".equals(username)){
    		return new HashSet<String>();
    	}
        return userDao.findPermissions(username);
    }

	@Override
	public User findUserByLoginName(String loginName) {
		log.info("userDAO="+userDao);
		//log.info(userDao.findUserByLoginName(loginName).getPassword());
		return userDao.findUserByLoginName(loginName);
	}


}
