package com.zs.rbac.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.busi.utils.LogUtil;
import com.zs.rbac.dao.UserExtDao;
import com.zs.rbac.entity.UserExt;
import com.zs.rbac.service.IUserExtService;
@Service
public class UserExtServiceImpl implements IUserExtService {

	Log log=LogUtil.getLog();
	@Autowired
	private UserExtDao userExtDao;
	
	@Override
	public List<UserExt> getUsetExtById(int userId) {
		
		log.info("dao========" + userExtDao);
		try {
			if(userId<=0)
				return new ArrayList<UserExt>();
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e);
		}
		try {
			List<UserExt> list=userExtDao.getUsetExtById(userId);
			return list;
		} catch (Exception e) {
			log.info(e);
		}
		
		return null;
	}

	
	public List<UserExt> getUserExtInfo(UserExt userExt) {
		List<UserExt> userExtList = new ArrayList<UserExt>();
		userExtList = userExtDao.getUserExtInfoDao(userExt);
		return userExtList;
	}
	
	public int insertUserExtInfo(UserExt userExt) {
		int flag = 0;
		flag = userExtDao.insertUserExtInfoDao(userExt);
		return flag;
	}
	
	public int updateUserExtInfo(UserExt userExt) {
		int flag = 0;
		flag = userExtDao.updateUserExtInfoDao(userExt);
		return flag;
	}

}
