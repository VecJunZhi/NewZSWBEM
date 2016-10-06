package com.zs.crm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.busi.entity.CountInfoVo;
import com.zs.busi.entity.CountRoomVo;
import com.zs.busi.entity.CountTelAccessVo;
import com.zs.busi.entity.XsInfoVo;
import com.zs.busi.entity.XsMonthCountInfoVo;
import com.zs.crm.entity.XsTeamGroupEntity;
import com.zs.crm.web.vo.XsTeamGroupVo;
@Repository
public interface XsInfoDao {
	
	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	public List<CountTelAccessVo> getCountTelAccess(String startDate,String endDate)throws Exception;
	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @param proJid
	 * @return
	 * @throws Exception
	 */
	public List<CountRoomVo> getCountRoom(String startDate,String endDate,String proJid)throws Exception;
	public List<XsMonthCountInfoVo> getMonthCountMoneyTnumber()throws Exception;
	/**
	 * 获得crm上所有用户的列表信息 
	 * @param vo
	 * @return
	 */
	public List<XsTeamGroupEntity> getUsersFromXsCRM(XsTeamGroupVo vo);

}
