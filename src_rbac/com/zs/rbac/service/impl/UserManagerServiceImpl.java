package com.zs.rbac.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.rbac.dao.UserManagerDao;
import com.zs.rbac.entity.Role;
import com.zs.rbac.entity.User;
import com.zs.rbac.entity.UserExt;
import com.zs.rbac.entity.UserRole;
import com.zs.rbac.service.UserManagerService;

@Service
public class UserManagerServiceImpl implements UserManagerService {
	
	@Autowired
	UserManagerDao dao;

	@Override
	public List<User> getUser(User user) {
		 List<User> list=dao.getUser(user);
		return list;
	}

	@Override
	public int createUser(User user) {
		int _int=dao.createUser(user);
		return _int;
	}

	@Override
	public int updateUser(User user) {
		int _int=dao.updateUser(user);
		return _int;
	}

	@Override
	public int deleteUser(int userId) {
		int _int=dao.deleteUser(userId);
		return _int;
	}

	@Override
	public int judgeIfExistUserName(String userName) {
		int _int=dao.judgeIfExistUserName(userName);
		return _int;
	}

	@Override
	public int judgeIfExistMobile(String mobile) {
		int _int=dao.judgeIfExistMobile(mobile);
		return _int;
	}

	@Override
	public int judgeIfExistEmail(String email) {
		int _int=dao.judgeIfExistEmail(email);
		return _int;
	}

	@Override
	public List<User> getUserByUserExt(int utid) {
		User user =new User();
		user.setUserID(utid);
		 List<User>list=dao.getUserByUserExt(user);
		return list;
	}

	@Override
	public List<Role> getProjByUserIdOrRoleId(int userID, int roleID) {
		// TODO Auto-generated method stub
		UserRole userRole=new UserRole();
		userRole.setUserID(userID);
		userRole.setRoleID(roleID);
		List<Role> list=dao.getProjByUserIdOrRoleId(userRole);
		return list;
	}



}
