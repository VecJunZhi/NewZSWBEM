package com.zs.busi.service;

import java.util.List;

import com.zs.busi.entity.ZsCustomTableVo;
import com.zs.busi.entity.ZsFollowInfoVo;
import com.zs.busi.entity.ZsInfoVo;

public interface ZsFollowInfoService {
	/**
	 * 
	 * @param startIndex
	 * @param len
	 * @return
	 */
	public List<ZsInfoVo> getZsCustInfo(ZsFollowInfoVo zsFollowInfo);
	/**
	 * 
	 * @return
	 */
	public int getZsCustCount(ZsFollowInfoVo zsFollowInfo);
	/**
	 * 
	 * @param startIndex
	 * @param len
	 * @return
	 */
	public List<ZsInfoVo> getZsFollowInfoByCusid(ZsFollowInfoVo zsFollowInfo);
	/**
	 * 
	 * @param custInfo
	 * @return
	 */
	public int getZsFollowInfoCountByCusid(ZsFollowInfoVo zsFollowInfo);
	
	public ZsCustomTableVo getcusInfo(String name);
}
