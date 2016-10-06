package com.zs.rbac.service;

import java.util.Date;
import java.util.List;

import com.zs.rbac.entity.Organization;

public interface IOrganizationService {
	/**
	 * 根据parentid获取组织机构信息
	 * @param parentID
	 * @return
	 */
	//public List<Organization> getOrganizationByParentID(int parentID);
	
	public int deleteByPrimaryKey(Integer orgid);

	public int insert(String orgname,Integer orgtype,String description,Integer parentid,String priority,Integer available,Date createtime,Date lasttime);

	//public int insertSelective(Organization record);

	//public Organization selectByPrimaryKey(Integer orgid);
    
	public List<Organization>selectBySelective(Integer orgid,String orgname,Integer orgtype,String description,Integer parentid,String priority,Integer available,Date createtime,Date lasttime);
	public List<Organization>selectBySelective(Organization record);

	public int updateByPrimaryKeySelective(Integer orgid,String orgname,Integer available,Integer parentid);

	//public int updateByPrimaryKey(Organization record);
    
}
