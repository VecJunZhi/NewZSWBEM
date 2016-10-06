package com.zs.common.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zs.common.dao.ZsUpdateLogDao;
import com.zs.common.entity.ZsUpdateLogEntity;
import com.zs.common.service.ZsUpdateLogService;
import com.zs.rbac.entity.User;
@Service
public class ZsUpdateLogServiceImpl implements ZsUpdateLogService{
	@Autowired
	ZsUpdateLogDao zsUpdateLogDao;
	@Override
	public int editLogTypeDao(){
		return zsUpdateLogDao.editLogType();
	};
	@Override
	public List<ZsUpdateLogEntity> getReadTypeDao(ZsUpdateLogEntity zsUpdateLog){
		List<ZsUpdateLogEntity> zsReadLogList = new ArrayList<ZsUpdateLogEntity>();
		zsReadLogList = zsUpdateLogDao.getReadType(zsUpdateLog);
		return zsReadLogList;
	}
	@Override
	public List<User> getUserNameDao(){
		List<User> userNameList = new ArrayList<User>();
		userNameList = zsUpdateLogDao.getUserName();
		return userNameList;
	}
	@Override
	public List<ZsUpdateLogEntity> getZsUpdateLogDao(ZsUpdateLogEntity zsUpdateLog) {
		List<ZsUpdateLogEntity> zsUpdateLogList = new ArrayList<ZsUpdateLogEntity>();
		zsUpdateLogList = zsUpdateLogDao.getZsUpdateLog(zsUpdateLog);
		return zsUpdateLogList;
	}
	@Override
	public int insertUpdateLogDao(ZsUpdateLogEntity zsUpdateLog){
		return zsUpdateLogDao.insertUpdateLog(zsUpdateLog);
	}
	@Override
	public int editUpdateLogDao(ZsUpdateLogEntity zsUpdateLog){
		return zsUpdateLogDao.editUpdateLog(zsUpdateLog);
	}
	@Override
	public int delLogDao(ZsUpdateLogEntity zsUpdateLog){
		return zsUpdateLogDao.delLog(zsUpdateLog);
	}
	@Override
	public List<ZsUpdateLogEntity> showLogListDao(){
		List<ZsUpdateLogEntity> showLogList = new ArrayList<ZsUpdateLogEntity>();
		showLogList=zsUpdateLogDao.showLogList();
		return showLogList;
	}
}



