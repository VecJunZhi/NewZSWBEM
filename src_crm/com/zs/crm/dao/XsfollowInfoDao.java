package com.zs.crm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zs.crm.entity.XsCustomTableVo;
import com.zs.crm.entity.XsCustomersManagerEntity;
import com.zs.crm.entity.XsFollowInfoVo;
import com.zs.crm.entity.XsInfoVo;
import com.zs.crm.web.vo.XsCustomersManagerVo;
@Repository
public interface XsfollowInfoDao {
	/**
	 * 
	 * @param startIndex
	 * @param len
	 * @return
	 */
	public List<XsCustomersManagerEntity> getXsCustInfoDao(XsCustomersManagerVo xsCustomersManagerVo);
	/**
	 * 
	 * @return
	 */
	public int getXsCustCountDao(XsCustomersManagerVo xsCustomersManagerVo);
	/**
	 * 
	 * @param startIndex
	 * @param len
	 * @return
	 */
	public List<XsCustomersManagerEntity> getXsFollowInfoDaoByCusid(XsCustomersManagerVo xsFollowInfo);
	/**
	 * 
	 * @param cusInfo
	 * @return
	 */
	public int getXsFollowInfoCountDaoByCusid(XsCustomersManagerVo custInfo);

}
