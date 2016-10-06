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
     * �����û�
     * @param user
     */
    public User createUser(User user) {
        //��������
      //  passwordHelper.encryptPassword(user);
        return userDao.createUser(user);
    }

    /**
     * �޸�����
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
     * ����û�-��ɫ��ϵ
     * @param userId
     * @param roleIds
     */
    public void correlationRoles(int userId, int... roleIds) {
        userDao.correlationUser_Roles(userId, roleIds);
    }


    /**
     * �Ƴ��û�-��ɫ��ϵ
     * @param userId
     * @param roleIds
     */
    public void uncorrelationRoles(int userId, int... roleIds) {
        userDao.uncorrelationUser_Roles(userId, roleIds);
    }

    /**
     * �����û��������û�
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    /**
     * �����û����������ɫ
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
     * �����û���������Ȩ��
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
