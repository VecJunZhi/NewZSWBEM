package com.zs.busi.dao;

import java.util.List;

import com.zs.busi.entity.ZsCustomTableVo;
import com.zs.busi.entity.ZsFollowInfoVo;
import com.zs.busi.entity.ZsInfoVo;

public interface ZsfollowInfoDao {
	/**
	 * 
	 * @param startIndex
	 * @param len
	 * @return
	 */
	public List<ZsInfoVo> getZsCustInfoDao(ZsFollowInfoVo zsFollowInfo);
	/**
	 * 
	 * @return
	 */
	public int getZsCustCountDao(ZsFollowInfoVo zsFollowInfo);
	/**
	 * 
	 * @param startIndex
	 * @param len
	 * @return
	 */
	public List<ZsInfoVo> getZsFollowInfoDaoByCusid(ZsFollowInfoVo zsFollowInfo);
	/**
	 * 
	 * @param cusInfo
	 * @return
	 */
	public int getZsFollowInfoCountDaoByCusid(ZsFollowInfoVo custInfo);
	/**
	 * 
	 */
	public ZsCustomTableVo getCusInfo(String name);
}
