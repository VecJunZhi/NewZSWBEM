package com.zs.crm.service;

import java.util.List;

import com.zs.crm.entity.XsCustomTableVo;
import com.zs.crm.entity.XsCustomersManagerEntity;
import com.zs.crm.entity.XsFollowInfoVo;
import com.zs.crm.entity.XsInfoVo;
import com.zs.crm.web.vo.XsCustomersManagerVo;

public interface XsFollowInfoService {
	/**
	 * 
	 * @param startIndex
	 * @param len
	 * @return
	 */
	public List<XsCustomersManagerEntity> getXsCustInfo(XsCustomersManagerVo xsFollowInfo);
	/**
	 * 
	 * @return
	 */
	public int getXsCustCount(XsCustomersManagerVo xsCustomersManagerVo );
	/**
	 * 
	 * @param startIndex
	 * @param len
	 * @return
	 */
	public List<XsCustomersManagerEntity> getXsFollowInfoByCusid(XsCustomersManagerVo xsFollowInfo);
	/**
	 * 
	 * @param cusInfo
	 * @return
	 */
	public int getXsFollowInfoCountByCusid(XsCustomersManagerVo custInfo);

}
