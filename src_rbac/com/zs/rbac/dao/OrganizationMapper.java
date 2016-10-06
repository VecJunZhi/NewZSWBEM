package com.zs.rbac.dao;

import java.util.List;

import com.zs.rbac.entity.Organization;

public interface OrganizationMapper {
	
    int deleteByPrimaryKey(Organization org);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(Integer orgid);
    
    List<Organization>selectBySelective(Organization record);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);
}