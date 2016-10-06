package com.zs.rbac.service;

import java.util.List;
import java.util.Set;

import com.zs.rbac.entity.User;
import com.zs.rbac.entity.UserExt;


/**
 * <p>User: zj
 * <p>Date: 15-9-5
 * <p>Version: 1.0
 */
public interface UserService {

    /**
     *
     * @param user
     */
    public User createUser(User user);

    /**
     * 
     * @param userId
     * @param newPassword
     */
    public void changePassword(int userId, String newPassword);

    /**
     * 
     * @param userId
     * @param roleIds
     */
    public void correlationRoles(int userId, int... roleIds);


    /**
     * 
     * @param userId
     * @param roleIds
     */
    public void uncorrelationRoles(int userId, int... roleIds);

    /**
     * 
     *  @param username
     * @return
     */
    public User findByUsername(String username);
    
    
    /**
     * 跟据用户名查询用户（用户名指userName,mobile,email）
     * @param loginName
     * @return
     */
    public User findUserByLoginName(String loginName);

    /**
     * 
     * @param username
     * @return
     */
    public Set<String> findRoles(String mobile);

    /**
     * 
     * @param username
     * @return
     */
    public Set<String> findPermissions(String mobile);
    

}
