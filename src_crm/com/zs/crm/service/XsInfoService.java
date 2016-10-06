package com.zs.crm.service;

import java.util.List;





import com.zs.busi.entity.CountRoomVo;
import com.zs.busi.entity.CountTelAccessVo;
import com.zs.busi.entity.XsMonthCountInfoVo;

public interface XsInfoService {
	
	public CountTelAccessVo getCountTelAccess(String startDate,String endDate)throws Exception;
	public CountRoomVo getCountRoom(String startDate,String endDate,String proJid)throws Exception;
	public List<XsMonthCountInfoVo> getMonthCountMoneyTnumber()throws Exception;
}
